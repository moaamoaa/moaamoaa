import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Typography from '@mui/material/Typography';
import ChattingRoom from 'components/profile/chatting/ChattingRoom';
import IconButton from '@mui/material/IconButton';
import useMobile from 'hooks/useMobile';
export default function ChattingItem(props) {
  const isMobile = useMobile();

  return (
    <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
      <ListItemAvatar>
        {/* 채팅 상대 프사 */}
        {/* <Avatar alt="" src={props.chats.profileImg} /> */}
      </ListItemAvatar>
      <ListItemText
        // 채팅 상대 닉네임
        // primary={props.chats.nickname}
        secondary={
          <React.Fragment>
            <Typography
              sx={{ display: 'inline' }}
              component="span"
              variant="body2"
              color="text.primary"
            >
              {/* 채팅 내용 최근 메시지 */}
              {/* {props.offerapply.profileContext} */}
            </Typography>
            <IconButton size="small" variant="contained" color="primary">
              {/* 채팅방 return 해주는 component 비행기 아이콘*/}
              <ChattingRoom isMobile={isMobile}></ChattingRoom>
            </IconButton>
          </React.Fragment>
        }
      />
    </ListItemButton>
  );
}
