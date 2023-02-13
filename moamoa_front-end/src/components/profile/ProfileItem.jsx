import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';
import DoDisturbOnIcon from '@mui/icons-material/DoDisturbOn';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';

export default function TeamItem(props) {
  const navigate = useNavigate();
  const projectId = useSelector(state => state.team.projectId);
  // Item 클릭하면, 해당 팀 페이지로 이동할 수 있어야 함!
  const goToDetail = () => {
    navigate(`/TeamDetailPage/?projectId=${projectId}`);
  };

  //내가 받은 제안
  if (props.type === 'offer') {
    return (
      <ListItemButton
        onClick={goToDetail}
        sx={{ pl: 4 }}
        alignItems="flex-start"
      >
        <ListItemAvatar>
          <Avatar alt="" src={props.offerapply.projectImg} />
        </ListItemAvatar>
        <Button>
          <DoDisturbOnIcon></DoDisturbOnIcon>
        </Button>
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
                {props.offerapply.projectContents}
              </Typography>
            </React.Fragment>
          }
        />
      </ListItemButton>
    );
    // 내가 팀에 보낸 지원
  } else if (props.type === 'apply') {
    return (
      <ListItemButton
        onClick={goToDetail}
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
                {props.offerapply.projectContents}
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
