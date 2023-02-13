import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { handleOpenTeamDetail } from 'redux/team';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';
import DoDisturbOnIcon from '@mui/icons-material/DoDisturbOn';

export default function TeamItem(props) {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  // Item 클릭하면, 해당 사람의 프로필로 이동할 수 있어야 함!
  // const goToProfile = () => {}

  // 팀에서 개인에게 보낸 제안 ( 사람이 뜸 ) - 철회-
  if (props.type === 'offer') {
    return (
      <ListItemButton
        // onClick={goToDeProfile}
        sx={{ pl: 4 }}
        alignItems="flex-start"
      >
        <ListItemAvatar>
          <Avatar alt="" src={props.offerapply.profileImg} />
        </ListItemAvatar>
        <Button>
          <DoDisturbOnIcon></DoDisturbOnIcon>
        </Button>
        <ListItemText
          primary={props.offerapply.nickname}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.offerapply.profileContext}
              </Typography>
            </React.Fragment>
          }
        />
      </ListItemButton>
    );
    // 팀에서 개인에게 받은 지원 (사람이 뜸) - 수락v 거절x
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
          primary={props.offerapply.nickname}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.offerapply.profileContext}
              </Typography>
              <Stack direction="row" sx={{ pt: 4 }}>
                <Button>
                  <CheckCircleIcon ㅣ뮤></CheckCircleIcon>
                </Button>
                <Button>
                  <CancelIcon></CancelIcon>
                </Button>
              </Stack>
            </React.Fragment>
          }
        />
      </ListItemButton>
    );
  }
}
