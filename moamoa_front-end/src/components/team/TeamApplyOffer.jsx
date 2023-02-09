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
import ReceiveApply from 'components/team/ReceiveApply';
import SendOffer from 'components/team/SendOffer';

export default function ApplyOffer() {
  // 오른쪽에 사이드바 열리는
  const [state, setState] = React.useState({
    right: false,
  });
  // 리스트가 열리는
  const [open, setOpen] = React.useState(true);
  // 리스트가 열리는
  const handleClick = () => {
    setOpen(!open);
  };

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
            팀의 지원 및 제안 관리
          </ListSubheader>
        }
      >
        {/* 리스트 열리게 만들거야 */}
        <ListItemButton onClick={handleClick}>
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <ListItemText primary="받은 지원" />
          {open ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
        <Collapse in={open} timeout="auto" unmountOnExit>
          <Divider />
          <List component="div" disablePadding>
            <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
              {/* 받은 지원 컴포넌트 */}
              <ReceiveApply></ReceiveApply>
            </ListItemButton>
          </List>
        </Collapse>
        {/* 리스트 열리게 만들어야 */}
        <ListItemButton onClick={handleClick}>
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <ListItemText primary="보낸 제안" />
          {open ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
        <Collapse in={open} timeout="auto" unmountOnExit>
          <Divider />
          <List component="div" disablePadding>
            <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
              {/* 보낸 제안 컴포넌트 */}
              <SendOffer></SendOffer>
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
          <Button onClick={toggleDrawer(anchor, true)}>팀의지원및제안</Button>
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
