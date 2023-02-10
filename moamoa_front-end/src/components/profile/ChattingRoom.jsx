import React, { useState } from 'react';
import { Box, Drawer, Button, List } from '@mui/material/';
import ChattingList from 'components/profile/ChattingList';

const ChattingRoom = () => {
  const [open, setOpen] = useState(false);

  const toggleDrawer = event => {
    if (
      event.type === 'keydown' &&
      (event.key === 'Tab' || event.key === 'Shift')
    ) {
      return;
    }

    setOpen(!open);
  };

  return (
    <div>
      <Button onClick={toggleDrawer}>채팅아이콘</Button>
      <Drawer anchor="right" open={open} onClose={toggleDrawer}>
        <Box
          sx={{ width: 500 }}
          role="presentation"
          onClick={toggleDrawer}
          onKeyDown={toggleDrawer}
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
          </List>
        </Box>
      </Drawer>
    </div>
  );
};

export default ChattingRoom;
