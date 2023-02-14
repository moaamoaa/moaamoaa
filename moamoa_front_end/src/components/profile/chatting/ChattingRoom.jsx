import * as React from 'react';
import { Paper, Box, Drawer, IconButton } from '@mui/material/';
import useMobile from 'hooks/useMobile';
import TelegramIcon from '@mui/icons-material/Telegram';

export default function ChattingRoom() {
  const isMobile = useMobile();

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
    // ListItemIcon 버튼을 눌렀을 때, 드로우어를 닫지 않도록 함
    // if (event.target.tagName === 'svg') return;
    // 얘는 태그네임이 뭘까? 있을까?
    setState({ ...state, [anchor]: open });
  };

  const list = anchor => (
    <Box
      sx={{ width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 360 }}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <Paper>
        <h1>채팅내용</h1>
      </Paper>
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
            <TelegramIcon>
              {/* 오픈버튼 -> 다른 컴포넌트에 삽입 */}
            </TelegramIcon>
          </IconButton>
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
