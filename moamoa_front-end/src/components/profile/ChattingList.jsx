import React from 'react';
import {
  List,
  Divider,
  ListItemText,
  ListItemAvatar,
  ListItemButton,
  Avatar,
  Typography,
} from '@mui/material';

const ChattingList = () => {
  return (
    <List style={{ width: '100%', maxWidth: 360, backgroundColor: '#fafafa' }}>
      {/* 1st */}
      <ListItemButton alignItems="flex-start">
        <ListItemAvatar>
          <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
        </ListItemAvatar>
        <ListItemText
          primary="안녕하세요!"
          secondary={
            <>
              <Typography
                style={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="primary"
              >
                장유하
              </Typography>
              {'프로필이동'}
            </>
          }
        />
      </ListItemButton>
      <Divider variant="inset" component="li" />
      {/* 2nd */}
      <ListItemButton alignItems="flex-start">
        <ListItemAvatar>
          <Avatar alt="Travis Howard" src="/static/images/avatar/2.jpg" />
        </ListItemAvatar>
        <ListItemText
          primary="팀구하셨나요?"
          secondary={
            <>
              <Typography
                style={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="primary"
              >
                임성빈
              </Typography>
              {'프로필이동'}
            </>
          }
        />
      </ListItemButton>
      <Divider variant="inset" component="li" />
      {/* 3rd */}
      <ListItemButton alignItems="flex-start">
        <ListItemAvatar>
          <Avatar alt="Cindy Baker" src="/static/images/avatar/3.jpg" />
        </ListItemAvatar>
        <ListItemText
          primary="팀원구해요~"
          secondary={
            <>
              <Typography
                style={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="primary"
              >
                황다솔
              </Typography>
              {'프로필이동'}
            </>
          }
        />
      </ListItemButton>
    </List>
  );
};

export default ChattingList;
