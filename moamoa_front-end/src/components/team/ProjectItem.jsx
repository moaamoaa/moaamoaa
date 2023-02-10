import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { handleOpenTeamDetail } from 'redux/team';

export default function ProjectItem(props) {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const goToDetail = () => {
    dispatch(handleOpenTeamDetail({ projectId: props.project.projectId })); // 오픈 했을 때, 값을 바꿔주고 그걸 디테일로 보내
    // 팀 관리에서 팀을 눌렀을 때 이동할 프론트 디테일 페이지 주소
    navigate(`/TeamDetailPage/?projectId=${props.project.projectId}`);
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
