import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';

export default function ProjectItem(props) {
  const navigate = useNavigate();
  const goToDetail = () => {
    // 주소가 정확히 어떻게 되지?
    navigate(`/projects/detail?projectId=${props.project.projectId}`);
  };

  if (props.type === 'project') {
    return (
      // 버튼에 온클릭 넣어서 팀 페이지 open으로 가게 (파라미터 가진)
      <ListItemButton
        onClick={goToDetail}
        sx={{ pl: 4 }}
        alignItems="flex-start"
      >
        <ListItemAvatar>
          <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
        </ListItemAvatar>
        <ListItemText
          primary={props.project.title}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.project.projectId}
              </Typography>
              {props.project.contents}
            </React.Fragment>
          }
        />
      </ListItemButton>
    );
  } else if (props.type === 'study') {
    return (
      <ListItemButton
        onClick={goToDetail}
        sx={{ pl: 4 }}
        alignItems="flex-start"
      >
        <ListItemAvatar>
          <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
        </ListItemAvatar>
        <ListItemText
          primary={props.project.title}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.project.projectId}
              </Typography>
              {props.project.contents}
            </React.Fragment>
          }
        />
      </ListItemButton>
    );
  }
}
