import { TextField, Button, Grid, Typography } from '@mui/material';
import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { handleCreateReview } from 'redux/profile';
import { handleSuccessState } from 'redux/snack';
import customAxios from 'utils/axios';

function ReviewCreation() {
  const [context, setContext] = useState('');
  const userPk = useSelector(state => state.user.userPk);
  const profile = useSelector(state => state.profile.userProfile[0]);

  const dispatch = useDispatch();

  const handleChangeContext = event => {
    if (context.length <= 100) {
      setContext(event.target.value);
    } else {
      setContext(context.slice(0, 100));
    }
  };

  const handleClickButton = () => {
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
        .post(`/profile/review`, {
          context: context,
          profileId: profile.id,
        })
        .then(response => {
          dispatch(handleCreateReview({ review: response.data.review }));
          setContext('');
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  const handleOpenLogin = () => {};
  return (
    <Grid container>
      {typeof userPk === 'number' ? (
        <>
          <Grid item xs={12}>
            <TextField
              fullWidth
              multiline
              placeholder="댓글을 남겨 보세요."
              rows={3}
              onChange={handleChangeContext}
              helperText={`${context.length}/100`}
              value={context}
            ></TextField>
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
            <Button onClick={handleClickButton} variant="text" color="primary">
              등록
            </Button>
          </Grid>
        </>
      ) : (
        <Typography
          variant="h6"
          sx={{
            width: '100%',
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            fontWeight: '600',
            opacity: '0.5',
            cursor: 'pointer',
          }}
          textAlign="center"
          gutterBottom
          onClick={handleOpenLogin}
        >
          로그인 후 댓글을 남겨주세요.
        </Typography>
      )}
    </Grid>
  );
}

export default ReviewCreation;
