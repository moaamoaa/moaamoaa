import * as React from 'react';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CancelIcon from '@mui/icons-material/Cancel';
import DoDisturbOnIcon from '@mui/icons-material/DoDisturbOn';
import { useDispatch, useSelector } from 'react-redux';
import { handleSuccessState } from 'redux/snack';
import customAxios from 'utils/axios';

// 팀에서 개인에게 제안을 보내거나 (철회)하고 개인으로부터 지원을 받고 (수락)(거절) 한다.

export default function TeamItem(props) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  // Item 클릭하면, 해당 사람의 프로필로 이동할 수 있어야 함!
  // 제안을 보냈던 사람의 프로필로 offerId
  const goToOfferProfile = () => {
    navigate(`/profile/${props.ask.offerId}`);
  };
  // 지원을 보낸 사람의 프로필로 applyId
  const goToApplyProfile = () => {
    navigate(`/profile/${props.ask.applyId}`);
  };

  // 보낸 제안 철회
  const handleWithdrawOffer = () => {
    // customAxios
    //   .authAxios({
    //     method: 'DELETE',
    //     url: '/offer/project',
    //     data: {
    //       userId: 리덕스,
    //       projectId: props.ask.projectId,
    //       offerId: props.ask.offerId,
    //     },
    //   })
    //   .then(response => {
    //     dispatch(
    //       handleSuccessState({
    //         open: true,
    //         message: '제안이 철회 되었습니다.',
    //         severity: 'success',
    //       }),
    //     );
    //     console.log(response.data);
    //     console.log('제안 철회 완료!');
    //   })
    //   .catch(error => {
    //     console.log(error);
    //   });
  };

  // 받은 지원 수락
  const handleAcceptApply = () => {
    // customAxios
    //   .authAxios({
    //     method: 'PUT',
    //     url: '/apply/project',
    //     data: {
    //       userId: 리덕스,
    //       projectId: props.ask.projectId,
    //       applyId: props.ask.applyId,
    //     },
    //   })
    //   .then(response => {
    //     dispatch(
    //       handleSuccessState({
    //         open: true,
    //         message: '지원이 수락 되었습니다.',
    //         severity: 'success',
    //       }),
    //     );
    //     console.log(response.data);
    //     console.log('지원 수락 완료!');
    //   })
    //   .catch(error => {
    //     console.log(error);
    //   });
  };

  // 받은 지원 거절
  const handleDeclineApply = () => {
    customAxios
      .authAxios({
        method: 'DELETE',
        url: '/apply/project',
        data: {
          projectId: props.ask.projectId,
          applyId: props.ask.applyId,
        },
      })
      .then(response => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '지원이 거절 되었습니다.',
            severity: 'success',
          }),
        );
        console.log(response.data);
        console.log('지원 거절 완료!');
      })
      .catch(error => {
        console.log(error);
      });
  };

  // 팀에서 개인에게 보낸 제안 ( 사람이 뜸 ) - 철회-
  if (props.type === 'offer') {
    return (
      <ListItem sx={{ pl: 4 }} alignItems="flex-start">
        <ListItemAvatar onClick={goToOfferProfile}>
          <Avatar alt="" src={props.ask.profileImg} />
        </ListItemAvatar>
        <ListItemText
          primary={props.ask.nickname}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.ask.profileContext}
              </Typography>
              <Button onClick={handleWithdrawOffer}>
                {/* - 아이콘 보낸 제안 철회 */}
                <DoDisturbOnIcon></DoDisturbOnIcon>
              </Button>
            </React.Fragment>
          }
        />
      </ListItem>
    );

    // 팀에서 개인에게 받은 지원 (사람이 뜸) - 수락v 거절x
  } else if (props.type === 'apply') {
    return (
      <ListItem sx={{ pl: 4 }} alignItems="flex-start">
        <ListItemAvatar onClick={goToApplyProfile}>
          <Avatar alt="" src={props.ask.profileImg} />
        </ListItemAvatar>
        <ListItemText
          primary={props.ask.nickname}
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {props.ask.profileContext}
              </Typography>
              <Stack direction="row" sx={{ pt: 4 }}>
                <Button onClick={handleAcceptApply}>
                  {/* v 아이콘 받은 지원 수락 */}
                  <CheckCircleIcon></CheckCircleIcon>
                </Button>
                <Button onClick={handleDeclineApply}>
                  {/* x 아이콘 받은 지원 거절 */}
                  <CancelIcon></CancelIcon>
                </Button>
              </Stack>
            </React.Fragment>
          }
        />
      </ListItem>
    );
  }
}
