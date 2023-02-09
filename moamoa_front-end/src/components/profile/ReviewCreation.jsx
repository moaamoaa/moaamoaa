import { TextField, Button, Container, Grid, Typography } from '@mui/material';
import React from 'react';
import { useState } from 'react';
import { useSelector } from 'react-redux';
import customAxios from 'utils/axios';

function ReviewCreation() {
  const [context, setContext] = useState('');
  const userPk = useSelector(state => state.user.userPk);
  const profile = useSelector(state => state.profile.userProfile[0]);

  const handleChangeContext = event => {
    if (context.length <= 100) {
      setContext(event.target.value);
    } else {
      setContext(context.slice(0, 100));
    }
  };

  const handleClickButton = () => {
    customAxios.authAxios
      .post(`/profile/review/${profile.id}`, {
        context: context,
        senderId: profile.id,
      })
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
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
            ></TextField>
          </Grid>
          <Grid item xs={12} sx={{ display: 'flex', justifyContent: 'end' }}>
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
