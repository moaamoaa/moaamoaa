import * as React from 'react';
import List from '@mui/material/List';
import ChattingItem from 'components/profile/ChattingItem';

export default function ChattingList(props) {
  return (
    <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
      {/* 전체 채팅  */}
      {props.chats &&
        props.chats.map((chat, idx) => (
          <span key={idx}>
            <ChattingItem chat={chat}></ChattingItem>
          </span>
        ))}
    </List>
  );
}
