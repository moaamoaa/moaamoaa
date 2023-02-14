import * as React from 'react';
import { useState, useEffect } from 'react';
import CustomAxios from 'utils/axios';
import { useSelector } from 'react-redux';
import {
  Box,
  Drawer,
  Button,
  List,
  Divider,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  ListSubheader,
  Collapse,
} from '@mui/material/';

import InboxIcon from '@mui/icons-material/MoveToInbox';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import useMobile from 'hooks/useMobile';
// 자식
import ProfileList from 'components/profile/ProfileList';

export default function ProfileApplyOffer() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [offers, setOffers] = useState([]);
  const [applies, setApplies] = useState([]);
  const isMobile = useMobile();
  const projectId = useSelector(state => state.team.projectId);

  useEffect(() => {
    console.log(projectId);
    if (isLoaded) {
      CustomAxios.authAxios
        .get('/offer/user')
        .then(response => {
          setOffers(response.data);
          console.log(response.data);
          console.log('받은 제안 조회 완료!');
        })
        .catch(error => {
          console.log(error);
        });
    } else {
      setIsLoaded(true);
    }
  }, [isLoaded]);

  useEffect(() => {
    if (isLoaded) {
      CustomAxios.authAxios
        .get('/apply/user')
        .then(response => {
          setApplies(response.data);
          console.log(response.data);
          console.log('보낸 지원 조회 완료!');
        })
        .catch(error => {
          console.log(error);
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
      sx={{ width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 360 }}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List
        sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}
        component="nav"
        aria-labelledby="nested-list-subheader"
        subheader={
          <ListSubheader component="div" id="nested-list-subheader">
            개인의 지원 및 제안 관리
          </ListSubheader>
        }
      >
        <ListItemButton onClick={handleProjectsClick}>
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <ListItemText primary="받은 제안" />
          {openProjects ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
        <Collapse in={openProjects} timeout="auto" unmountOnExit>
          <Divider />
          <List component="div" disablePadding>
            <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
              {/* 받은 제안 리스트 컴포넌트 */}
              <ProfileList offers={offers} type={'offer'}></ProfileList>
            </ListItemButton>
          </List>
        </Collapse>
        <ListItemButton onClick={handleStudiesClick}>
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <ListItemText primary="보낸 지원" />
          {openStudies ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
        <Collapse in={openStudies} timeout="auto" unmountOnExit>
          <Divider />
          <List component="div" disablePadding>
            <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
              {/* 보낸 지원 리스트 컴포넌트 */}
              <ProfileList applies={applies} type={'apply'}></ProfileList>
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
          <Button
            onClick={toggleDrawer(anchor, true)}
            sx={{
              mr: isMobile ? 0 : 2,
              scale: isMobile ? '.8' : '1',
            }}
          >
            지원 및 제안
          </Button>
          <Drawer
            anchor={anchor}
            open={state[anchor]}
            onClose={toggleDrawer(anchor, false)}
            onClick={toggleDrawer(anchor, !state[anchor])}
          >
            {list(anchor)}
          </Drawer>
        </React.Fragment>
      ))}
    </div>
  );
}
