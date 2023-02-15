import * as React from 'react';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { handleOpenTeamDetail } from 'redux/team';
import Button from '@mui/material/Button';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import { handleSuccessState } from 'redux/snack';
import customAxios from 'utils/axios';
import Stack from '@mui/material/Stack';
// 제안 보낼 때, 제안 할 해당 프로젝트를 선택 (+) 해야함 (버튼 존재)

export default function MyProjectItem(props) {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const memberId = useSelector(state => state.member.memberId);
  const goToDetail = () => {
    dispatch(handleOpenTeamDetail({ projectId: props.projectstudy.projectId })); // 오픈 했을 때, 값을 바꿔주고 그걸 디테일로 보내
    // 팀 관리에서 팀을 눌렀을 때 이동할 프론트 디테일 페이지 주소
    navigate(`/TeamDetailPage/?projectId=${props.projectstudy.projectId}`);
  };
  // 팀에서 개인에게 제안 보내기 ( + 아이콘 클릭)
  const handleOffer = () => {
    console.log(props.projectstudy.projectId);
    // 카드의 props.type이 member이면 props.card.id가 userId임
    customAxios
      .authAxios({
        method: 'POST',
        url: '/offer',
        // userId 는 제안을 받을 사람 ID : 카드 주인의 ID
        data: {
          projectId: props.projectstudy.projectId,
          userId: memberId,
        },
      })
      .then(response => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '제안이 완료 되었습니다.',
            severity: 'success',
          }),
        );
        console.log(response.data);
        console.log('제안 완료!');
      })
      .catch(error => {
        console.log(error);
      });
  };

  if (props.type === 'project') {
    return (
      // 버튼에 온클릭 넣어서 팀 페이지 open으로 가게 (파라미터 가진)
      <ListItem sx={{ pl: 4 }} alignItems="flex-start">
        <ListItemAvatar onClick={goToDetail}>
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
        <Stack direction="row" sx={{ pt: 4 }}>
          <Button onClick={handleOffer}>
            <AddCircleIcon></AddCircleIcon>
          </Button>
        </Stack>
      </ListItem>
    );
  } else if (props.type === 'study') {
    return (
      <ListItem sx={{ pl: 4 }} alignItems="flex-start">
        <ListItemAvatar onClick={goToDetail}>
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
                <Button onClick={handleOffer}>
                  <AddCircleIcon></AddCircleIcon>
                </Button>
              </Stack>
            </React.Fragment>
          }
        />
      </ListItem>
    );
  }
}
