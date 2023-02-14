import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { handleOpenTeamDetail } from 'redux/team';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';

// 나의 팀 관리 (네브바) 따로 버튼 아이콘 없음!

export default function ProjectItem(props) {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const goToDetail = () => {
    dispatch(handleOpenTeamDetail({ projectId: props.projectstudy.projectId })); // 오픈 했을 때, 값을 바꿔주고 그걸 디테일로 보내
    // 팀 관리에서 팀을 눌렀을 때 이동할 프론트 디테일 페이지 주소
    navigate(`/TeamDetailPage/?projectId=${props.projectstudy.projectId}`);
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
          <Avatar alt="" src={props.projectstudy.img} />
        </ListItemAvatar>
        <ListItemText
          primary={props.projectstudy.title}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.projectstudy.contents}
              </Typography>
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
          <Avatar alt="" src={props.projectstudy.img}></Avatar>
        </ListItemAvatar>
        <ListItemText
          primary={props.projectstudy.title}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.projectstudy.contents}
              </Typography>
              <Stack direction="row" sx={{ pt: 4 }}>
                <Button>
                  <CheckCircleIcon></CheckCircleIcon>
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
