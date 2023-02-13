import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import DoDisturbOnIcon from '@mui/icons-material/DoDisturbOn';
import Button from '@mui/material/Button';
// import ChattingRoom from 'components/profile/ChattingRoom';

export default function ChattingItem(props) {
  // 1:1 채팅방 열리게
  const handleOpenChattingRoom = () => {};

  return (
    <ListItemButton
      onClick={handleOpenChattingRoom}
      sx={{ pl: 4 }}
      alignItems="flex-start"
    >
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
          </React.Fragment>
        }
      />
    </ListItemButton>
  );
}
