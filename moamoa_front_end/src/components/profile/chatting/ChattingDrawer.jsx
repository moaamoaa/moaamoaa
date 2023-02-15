import * as React from 'react';
import { useState, useEffect } from 'react';
import CustomAxios from 'utils/axios';
import {
  Box,
  Drawer,
  IconButton,
  List,
  // Divider,
  ListItemButton,
  ListSubheader,
  // Collapse,
} from '@mui/material/';
import ChatIcon from '@mui/icons-material/Chat';
import useMobile from 'hooks/useMobile';
import ChattingList from 'components/profile/chatting/ChattingList';

export default function ChattingDrawer() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [chats, setChats] = useState([]);
  const isMobile = useMobile();

  useEffect(() => {
    if (isLoaded) {
      CustomAxios.authAxios
        .get('/projects/project') //채팅 목록 조회하는 api로 바꿔주기!!!
        .then(response => {
          setChats(response.data);
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

  const toggleDrawer = (anchor, open) => event => {
    if (
      event.type === 'keydown' &&
      (event.key === 'Tab' || event.key === 'Shift')
    ) {
      return;
    }
    setState({ ...state, [anchor]: open });
  };

  const list = anchor => (
    <Box
      sx={{ width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 360 }}
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
            채팅 목록
          </ListSubheader>
        }
      >
        <List component="div" disablePadding>
          {/* onClick */}
          <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
            <ChattingList chats={chats}></ChattingList>
          </ListItemButton>
        </List>
      </List>
    </Box>
  );

  return (
    <div>
      {['right'].map(anchor => (
        <React.Fragment key={anchor}>
          <IconButton
            onClick={toggleDrawer(anchor, true)}
            sx={{ mr: isMobile ? 0 : 2, scale: isMobile ? '.8' : '1' }}
          >
            <ChatIcon />
          </IconButton>
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
    </div>
  );
}
