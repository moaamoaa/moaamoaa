import styled from '@emotion/styled';
import {
  Button,
  Grid,
  TextField,
  Container,
  Typography,
  Tooltip,
} from '@mui/material';
import LongMenu from 'components/profile/LongMenu';
import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import {
  handleEditReview,
  handleProfilePk,
  handleSuccessReview,
} from 'redux/profile';
import { handleSuccessState } from 'redux/snack';
import customAxios from 'utils/axios';
import scrollToTop from 'utils/scrollToTop';

function Review(props) {
  console.log(props);
  const userPk = useSelector(state => state.user.userPk);
  const [isEdit, setIsEdit] = useState(false);
  const [context, setContext] = useState(
    props.review.context ? props.review.context : '',
  );
  const flag = userPk === props.review.senderId;

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleOpenEdit = () => {
    setIsEdit(true);
  };

  const handleSuccessDelete = () => {
    customAxios.authAxios
      .delete(`/profile/review/${props.review.id}`)
      .then(response => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '댓글이 삭제 되었습니다.',
            severity: 'success',
          }),
        );
        dispatch(
          handleSuccessReview({
            reviews: response.data.review,
          }),
        );

        setIsEdit(false);
      })
      .catch(error => {
        console.log(error);
      });
  };

  const handleSuccessEdit = () => {
    if (!context.trim()) {
      dispatch(
        handleSuccessState({
          open: true,
          message: '내용을 입력해 주세요.',
          severity: 'error',
        }),
      );
    } else {
      customAxios.authAxios
        .put('/profile/review', {
          id: props.review.id,
          context: context,
          profileId: userPk,
        })
        .then(response => {
          dispatch(
            handleSuccessState({
              open: true,
              message: '댓글이 수정 되었습니다.',
              severity: 'success',
            }),
          );
          dispatch(
            handleSuccessReview({
              reviews: response.data.review,
            }),
          );

          setIsEdit(false);
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  const handleCancelEdit = () => {
    setIsEdit(false);
    setContext(props.review.context);
  };

  const limit = 100;

  const handleChangeContext = event => {
    if (event.target.value.length <= limit) {
      setContext(event.target.value);
    } else {
      setContext(context.slice(0, limit - 1));
    }
  };

  const handleOpenUserProfile = () => {
    dispatch(handleProfilePk({ id: props.review.senderId }));
    navigate('/ProfilePage');
    scrollToTop();
  };

  return (
    <Grid container sx={{ paddingBottom: '.5rem' }}>
      {isEdit ? (
        <>
          <Grid item xs={12}>
            <TextField
              fullWidth
              multiline
              helperText={`${context.length}/${limit}`}
              onChange={handleChangeContext}
              value={context}
              sx={{ minHeight: '4rem' }}
            />
          </Grid>
          <Grid
            item
            xs={12}
            sx={{
              display: 'flex',
              justifyContent: 'end',
              marginTop: '-1.5rem',
            }}
          >
            <Button onClick={handleSuccessEdit} variant="text" color="primary">
              수정
            </Button>
            <Button onClick={handleCancelEdit} variant="text" color="primary">
              취소
            </Button>
          </Grid>
        </>
      ) : (
        <Container sx={{ padding: '0 !important' }}>
          <Grid
            item
            xs={12}
            justifyContent="space-between"
            alignItems="start"
            sx={{ display: 'flex' }}
          >
            <Tooltip title="프로필로 이동">
              <Typography
                variant="body1"
                color="initial"
                sx={{ fontWeight: '600', cursor: 'pointer' }}
                onClick={handleOpenUserProfile}
              >
                {props.review.sender}
              </Typography>
            </Tooltip>
            {flag ? (
              <LongMenu
                isEdit={isEdit}
                handleOpenEdit={handleOpenEdit}
                handleDelete={handleSuccessDelete}
              ></LongMenu>
            ) : (
              <></>
            )}
          </Grid>
          <Grid item xs={12}>
            <ReviewTextField
              fullWidth
              defaultValue={props.review.context}
              InputProps={{
                readOnly: true,
              }}
              variant="standard"
              multiline
              helperText={props.review.time}
              rows={2}
            />
          </Grid>
        </Container>
      )}
    </Grid>
  );
}

const ReviewTextField = styled(TextField)`
  padding-bottom: 1rem;
`;

export default Review;
