import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import ListSubheader from '@mui/material/ListSubheader';
import Collapse from '@mui/material/Collapse';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import SendIcon from '@mui/icons-material/Send';
import DraftsIcon from '@mui/icons-material/Drafts';
import StarBorder from '@mui/icons-material/StarBorder';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import ChattingList from 'components/profile/ChattingList';

export default function ChattingRoom() {
  // 오른쪽에 사이드바 열리는
  const [state, setState] = React.useState({
    right: false,
  });
  // // 리스트가 열리는
  // const [open, setOpen] = React.useState(true);
  // // 리스트가 열리는
  // const handleClick = () => {
  //   setOpen(!open);
  // };

  // 오른쪽에 사이드바 열리는
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
      sx={{ width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 500 }}
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
            채팅방목록
          </ListSubheader>
        }
      >
        <ListItemButton sx={{ pl: 4 }}>
          <ChattingList></ChattingList>
        </ListItemButton>
        {/* <ListItemButton onClick={handleClick}>
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <ListItemText primary="채팅 목록 열기" />
          {open ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
        <Collapse in={open} timeout="auto" unmountOnExit>
          <Divider />
          <List component="div" disablePadding>
            <ListItemButton sx={{ pl: 4 }}>
              <ChattingList></ChattingList>
            </ListItemButton>
          </List>
        </Collapse> */}
      </List>
    </Box>
  );

  return (
    <div>
      {['right'].map(anchor => (
        <React.Fragment key={anchor}>
          <Button onClick={toggleDrawer(anchor, true)}>채팅아이콘</Button>
          <Drawer
            anchor={anchor}
            open={state[anchor]}
            onClose={toggleDrawer(anchor, false)}
          >
            {list(anchor)}
          </Drawer>
        </React.Fragment>
      ))}
    </div>
  );
}
