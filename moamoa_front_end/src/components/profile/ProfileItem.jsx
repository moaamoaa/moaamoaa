import * as React from 'react';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';
import DoDisturbOnIcon from '@mui/icons-material/DoDisturbOn';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import { useDispatch, useSelector } from 'react-redux';
import { handleSuccessState } from 'redux/snack';
import customAxios from 'utils/axios';

// 개인이 팀에게 지원을 보내거나 (철회)하고 제안 받은 것을 (수락)(거절) 한다.

export default function ProfileItem(props) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  // Item 클릭하면, 해당 팀 페이지로 이동할 수 있어야 함!
  const goToDetail = () => {
    navigate(`/TeamDetailPage/?projectId=${props.ask.projectId}`);
  };

  // 보낸 지원 철회
  const handleWithdrawApply = () => {
    customAxios
      .authAxios({
        method: 'DELETE',
        url: '/apply/user',
        data: {
          projectId: props.ask.projectId,
          applyId: props.ask.applyId,
        },
      })
      .then(response => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '지원이 철회 되었습니다.',
            severity: 'success',
          }),
        );
        console.log(response.data);
        console.log('지원 철회 완료!');
      })
      .catch(error => {
        console.log(error);
      });
  };

  // 받은 제안 수락
  const handleAcceptOffer = () => {
    customAxios
      .authAxios({
        method: 'PUT',
        url: '/offer/user',
        data: {
          projectId: props.ask.projectId,
          offerId: offerId,
        },
      })
      .then(response => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '제안이 수락 되었습니다.',
            severity: 'success',
          }),
        );
        console.log(response.data);
        console.log('제안 수락 완료!');
      })
      .catch(error => {
        console.log(error);
      });
  };

  // 받은 제안 거절
  const handleDeclineOffer = () => {
    customAxios
      .authAxios({
        method: 'DELETE',
        url: '/offer/user',
        data: {
          projectId: props.ask.projectId,
          offerId: offerId,
        },
      })
      .then(response => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '제안이 거절 되었습니다.',
            severity: 'success',
          }),
        );
        console.log(response.data);
        console.log('제안 거절 완료!');
      })
      .catch(error => {
        console.log(error);
      });
  };

  //내가 받은 제안
  if (props.type === 'offer') {
    return (
      <ListItem sx={{ pl: 4 }} alignItems="flex-start">
        <ListItemAvatar onClick={goToDetail}>
          <Avatar alt="" src={props.ask.projectImg} />
        </ListItemAvatar>
        <ListItemText
          primary={props.ask.title}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.ask.projectContents}
              </Typography>
              <Stack direction="row" sx={{ pt: 4 }}>
                <Button onClick={handleAcceptOffer}>
                  {/* v 아이콘 받은 제안 수락 */}
                  <CheckCircleIcon></CheckCircleIcon>
                </Button>
                <Button onClick={handleDeclineOffer}>
                  {/* x 아이콘 받은 제안 거절 */}
                  <CancelIcon></CancelIcon>
                </Button>
              </Stack>
            </React.Fragment>
          }
        />
      </ListItem>
    );
    // 내가 팀에 보낸 지원
  } else if (props.type === 'apply') {
    return (
      <ListItem sx={{ pl: 4 }} alignItems="flex-start">
        <ListItemAvatar onClick={goToDetail}>
          <Avatar alt="" src={props.ask.projectImg} />
        </ListItemAvatar>
        <ListItemText
          primary={props.ask.title}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.ask.projectContents}
              </Typography>
              <Button onChange={handleWithdrawApply}>
                {/* - 아이콘 보낸 지원 철회 */}
                <DoDisturbOnIcon></DoDisturbOnIcon>
              </Button>
            </React.Fragment>
          }
        />
      </ListItem>
    );
  }
}
