import * as React from 'react';
import { useState, useEffect } from 'react';
import customAxios from 'utils/axios';
import HandshakeRoundedIcon from '@mui/icons-material/HandshakeRounded';
import {
  Box,
  Drawer,
  List,
  Divider,
  ListItemIcon,
  ListItemButton,
  ListItemText,
  ListSubheader,
  Collapse,
  Button,
} from '@mui/material/';

import InboxIcon from '@mui/icons-material/MoveToInbox';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import useMobile from 'hooks/useMobile';
import MyProjectList from 'components/team/MyProjectList';
import { useSelector } from 'react-redux';

export default function MyProjectStudy() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [projects, setProjects] = useState([]);
  const [studies, setStudies] = useState([]);
  const isMobile = useMobile();
  const isLogged = useSelector(state => state.user.isLogged);

  useEffect(() => {
    if (isLoaded && isLogged) {
      customAxios.authAxios
        .get('/projects/project')
        .then(response => {
          setProjects(response.data);
        })
        .catch(error => {
          // console.log(error.message);
        });
      customAxios.authAxios
        .get('/projects/study')
        .then(response => {
          setStudies(response.data);
        })
        .catch(error => {
          // console.log(error.message);
        });
    } else {
      setIsLoaded(true);
    }
  }, [isLoaded]);

  // 오른쪽에 사이드바 열리는
  const [state, setState] = React.useState({
    right: false,
  });

  // 리스트가 열리는 (Projects)
  const [openProjects, setOpenProjects] = useState(false);
  const handleProjectsClick = () => {
    setOpenProjects(!openProjects);
  };

  // 리스트가 열리는 (Studies)
  const [openStudies, setOpenStudies] = useState(false);
  const handleStudiesClick = () => {
    setOpenStudies(!openStudies);
  };

  const toggleDrawer = (anchor, open) => event => {
    if (
      event.type === 'keydown' &&
      (event.key === 'Tab' || event.key === 'Shift')
    ) {
      return;
    }
    // ListItemIcon 버튼을 눌렀을 때, 드로우어를 닫지 않도록 함
    if (event.target.tagName === 'svg') return;
    setState({ ...state, [anchor]: open });
  };

  const list = anchor => (
    <Box
      sx={{
        width: anchor === 'top' || isMobile ? '100vw' : 360,
      }}
      role="presentation"
      // onClick={toggleDrawer(anchor, false)}
      // onKeyDown={toggleDrawer(anchor, false)}
    >
      <List
        sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}
        component="nav"
        aria-labelledby="nested-list-subheader"
        subheader={
          <ListSubheader component="div" id="nested-list-subheader">
            나의 팀 관리
          </ListSubheader>
        }
      >
        <ListItemButton onClick={handleProjectsClick}>
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <ListItemText primary="프로젝트" />
          {openProjects ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
        <Collapse in={openProjects} timeout="auto" unmountOnExit>
          <Divider />
          <List component="div" disablePadding>
            <ListItemButton
              sx={{ pl: 4 }}
              alignItems="flex-start"
              onClick={toggleDrawer(anchor, !state[anchor])}
            >
              {/* 프로젝트 리스트 컴포넌트 */}
              <MyProjectList
                projects={projects}
                type={'project'}
              ></MyProjectList>
            </ListItemButton>
          </List>
        </Collapse>
        <ListItemButton onClick={handleStudiesClick}>
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <ListItemText primary="스터디" />
          {openStudies ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
        <Collapse in={openStudies} timeout="auto" unmountOnExit>
          <Divider />
          <List component="div" disablePadding>
            <ListItemButton
              sx={{ pl: 4 }}
              alignItems="flex-start"
              onClick={toggleDrawer(anchor, !state[anchor])}
            >
              {/* 스터디 리스트 컴포넌트 */}
              <MyProjectList studies={studies} type={'study'}></MyProjectList>
            </ListItemButton>
          </List>
        </Collapse>
      </List>
    </Box>
  );

  return (
    <>
      {['right'].map(anchor => (
        <React.Fragment key={anchor}>
          <Button
            fullWidth
            size="medium"
            variant="outlined"
            color="primary"
            onClick={toggleDrawer(anchor, true)}
            sx={{
              marginTop: '1rem',
              paddingY: '5px',
            }}
          >
            <HandshakeRoundedIcon
              sx={{ marginRight: 1 }}
            ></HandshakeRoundedIcon>
            제안 보내기
          </Button>
          <Drawer
            anchor={anchor}
            open={state[anchor]}
            onClose={toggleDrawer(anchor, false)}
            // onClick={toggleDrawer(anchor, !state[anchor])}
          >
            {list(anchor)}
          </Drawer>
        </React.Fragment>
      ))}
    </>
  );
}
