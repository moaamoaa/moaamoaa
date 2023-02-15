import * as React from 'react';
import { useState, useEffect } from 'react';
import customAxios from 'utils/axios';

import {
  Box,
  Drawer,
  IconButton,
  List,
  Divider,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  ListSubheader,
  Collapse,
  Grid,
} from '@mui/material/';

import InboxIcon from '@mui/icons-material/MoveToInbox';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import Diversity3Icon from '@mui/icons-material/Diversity3';
import useMobile from 'hooks/useMobile';
import ProjectList from 'components/team/ProjectList';
import ClearRoundedIcon from '@mui/icons-material/ClearRounded';

export default function ProjectStudy() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [projects, setProjects] = useState([]);
  const [studies, setStudies] = useState([]);
  const isMobile = useMobile();

  useEffect(() => {
    if (isLoaded) {
      customAxios.authAxios
        .get('/projects/project')
        .then(response => {
          setProjects(response.data);
          // console.log(response.data);
          // console.log('프로젝트조회완료!');
        })
        .catch(error => {
          console.log(error.message);
        });
    } else {
      setIsLoaded(true);
    }
  }, [isLoaded]);

  useEffect(() => {
    if (isLoaded) {
      customAxios.authAxios
        .get('/projects/study')
        .then(response => {
          setStudies(response.data);
          // console.log(response.data);
          // console.log('스터디조회완료!');
        })
        .catch(error => {
          console.log(error.message);
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
      sx={{ width: anchor === 'top' || isMobile ? '100vw' : 360 }}
      role="presentation"
      // onClick={toggleDrawer(anchor, false)}
      // onKeyDown={toggleDrawer(anchor, false)}
    >
      <List
        sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}
        component="nav"
        aria-labelledby="nested-list-subheader"
        subheader={
          <Grid
            container
            sx={{ display: 'flex', justifyContent: 'space-between' }}
          >
            <ListSubheader component="div" id="nested-list-subheader">
              나의 팀 관리
            </ListSubheader>
            <IconButton
              onClick={toggleDrawer(anchor, false)}
              sx={{ display: isMobile ? 'flex' : 'none' }}
            >
              <ClearRoundedIcon />
            </IconButton>
          </Grid>
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
              <ProjectList projects={projects} type={'project'}></ProjectList>
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
              <ProjectList studies={studies} type={'study'}></ProjectList>
            </ListItemButton>
          </List>
        </Collapse>
      </List>
    </Box>
  );

  return (
    <div>
      {['right'].map(anchor => (
        <React.Fragment key={anchor}>
          <IconButton
            onClick={toggleDrawer(anchor, true)}
            sx={{
              mr: isMobile ? 0 : 2,
              scale: isMobile ? '.8' : '1',
            }}
          >
            <Diversity3Icon />
          </IconButton>
          <Drawer
            anchor={anchor}
            open={state[anchor]}
            onClose={toggleDrawer(anchor, false)}
            // onClick={toggleDrawer(anchor, !state[anchor])} 각 리스트 컴포넌트 ListItemButton쪽으로
          >
            {list(anchor)}
          </Drawer>
        </React.Fragment>
      ))}
    </div>
  );
}
