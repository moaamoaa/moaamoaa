import * as React from 'react';
import {
  Paper,
  Box,
  Drawer,
  IconButton,
  TextField,
  Button,
} from '@mui/material/';
import useMobile from 'hooks/useMobile';
import TelegramIcon from '@mui/icons-material/Telegram';
import { useSelector } from 'react-redux';

export default function ChattingRoom() {
  const isMobile = useMobile();

  // 나의 아이디
  const userId = useSelector(state => state.user.userPk);
  // 상대 아이디
  const profile = useSelector(state => state.profile.userProfile[0].id);

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
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <Paper>
        <h1>누구누구님과 채팅을 시작합니다</h1>
      </Paper>
      <Paper>
        <h2>{name}안녕?</h2>
      </Paper>
      <TextField></TextField>
      <Button>전송</Button>
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
            채팅
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

// const chatBox = styled(CommonBox)`
//   background-color: #ffffff;
//   border: 1px solid #c4c4c4;
//   border-radius: 0.5rem 0 0 0.5rem;
//   height: 100%;
//   min-height: 3rem;
//   width: 100%;
//   // 이건 박스 안에 맞게 줄바꿈해주는 css
//   flex-flow: row-reverse wrap;
// `;
