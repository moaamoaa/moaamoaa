import { useState, useEffect } from 'react';

import { loginSuccess } from 'redux/user';
import { useDispatch, useSelector } from 'react-redux';

import Cookies from 'js-cookie';
import customAxios from 'utils/axios';

import {
  Button,
  Dialog,
  DialogContent,
  TextField,
  Grid,
  Box,
} from '@mui/material/';

import DialogHeader from 'components/common/dialog/DialogHeader';

export default function LogInDialog(props) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch();
  const isLogin = useSelector(state => state.user.isLogged);
  const handleCloseLogIndialog = () => {
    props.setLogInDialog(false);
  };

  useEffect(handleCloseLogIndialog, [isLogin]);

  const handleLogIn = () => {
    customAxios.basicAxios
      .post(`/users/login`, {
        email: email,
        password: password,
      })
      .then(response => {
        const token = response.data.accessToken;
        const user_pk = response.data.id;

        // Set the access token
        Cookies.set('access_token', token, { expires: 1 });
        dispatch(loginSuccess({ userPk: user_pk }));
      })
      .catch(error => {
        console.log(error);
      });
  };

  const handleEmail = e => {
    setEmail(e.target.value);
  };

  const handlePassword = e => {
    setPassword(e.target.value);
  };

  const handleOpenSignUpDialog = () => {
    props.setLogInDialog(false);
    props.setSignUpDialog(true);
  };

  const handleOpenFindPasswordDialog = () => {
    props.setLogInDialog(false);
    props.setFindPasswordDialog(true);
  };

  return (
    <Dialog onClose={handleCloseLogIndialog} open={props.logInDialog}>
      <DialogHeader onClose={handleCloseLogIndialog}>
        모아모아에 오신 것을 환영합니다.
      </DialogHeader>
      <DialogContent dividers>
        <Box component="form">
          <TextField
            label="이메일을 입력해주세요"
            fullWidth
            name="email"
            autoComplete="email"
            value={email}
            onChange={handleEmail}
          />
          <TextField
            label="비밀번호를 입력해주세요"
            type="password"
            fullWidth
            name="password"
            autoComplete="current-password"
            sx={{ mt: 2 }}
            onChange={handlePassword}
          />

          <Button
            fullWidth
            variant="contained"
            sx={{ mt: 3 }}
            onClick={handleLogIn}
          >
            로그인
          </Button>

          <Grid container justifyContent="flex-end">
            <Grid item>
              <Button
                variant="text"
                color="primary"
                onClick={handleOpenFindPasswordDialog}
              >
                비밀번호 찾기
              </Button>
            </Grid>
            <Grid item>
              <Button
                variant="text"
                color="primary"
                onClick={handleOpenSignUpDialog}
              >
                회원가입
              </Button>
            </Grid>
          </Grid>
        </Box>
      </DialogContent>
    </Dialog>
  );
}
