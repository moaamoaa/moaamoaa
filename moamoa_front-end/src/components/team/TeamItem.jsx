import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { handleOpenTeamDetail } from 'redux/team';

export default function TeamItem(props) {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  // Item 클릭하면, 해당 사람의 프로필로 이동할 수 있어야 함!
  // const goToProfile = () => {}

  // 팀에서 개인에게 보낸 제안 ( 사람이 뜸 )
  if (props.type === 'offer') {
    return (
      <ListItemButton
        // onClick={goToDeProfile}
        sx={{ pl: 4 }}
        alignItems="flex-start"
      >
        <ListItemAvatar>
          <Avatar alt="" src={props.offerapply.projectImg} />
        </ListItemAvatar>
        <ListItemText
          primary={props.offerapply.title}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.offerapply.nickname}
              </Typography>
              {props.offerapply.time}
            </React.Fragment>
          }
        />
      </ListItemButton>
    );
    // 팀에서 개인에게 받은 지원 (사람이 뜸)
  } else if (props.type === 'apply') {
    return (
      <ListItemButton
        // onClick={goToProfile}
        sx={{ pl: 4 }}
        alignItems="flex-start"
      >
        <ListItemAvatar>
          <Avatar alt="" src={props.offerapply.profileImg} />
        </ListItemAvatar>
        <ListItemText
          primary={props.offerapply.title}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.offerapply.nickname}
              </Typography>
              {props.offerapply.time}
            </React.Fragment>
          }
        />
      </ListItemButton>
    );
  }
}