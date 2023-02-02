import { useState, useEffect } from 'react';

import { loginAccount } from 'redux/User';
import { useDispatch, useSelector } from 'react-redux';

import {
  Button,
  Dialog,
  DialogContent,
  TextField,
  Grid,
  Box,
} from '@mui/material/';

import DialogHeader from 'components/common/dialog/DialogHeader';
import Cookies from 'js-cookie';
import axios from 'axios';

export default function SignInDialog(props) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch();
  const isLogin = useSelector(state => state.User.isLogged);
  const handleCloseSignIndialog = () => {
    props.setSignInDialog(false);
  };

  const baseURL = 'http://localhost:8080';

  useEffect(handleCloseSignIndialog, [isLogin]);

  const handleLogIn = () => {
    axios
      .post(`${baseURL}/users/login`, {
        email: email,
        password: password,
      })
      .then(response => {
        console.log('로그인 성공');
        console.log(response);
        const token = response.data.accessToken;
        const user_pk = response.data.id;
        dispatch(loginAccount({ userPk: user_pk }));

        // Set the access token
        Cookies.set('access_token', token, { expires: 1 });
      })
      .catch(error => {
        console.log('로그인 실패');
        console.log(error.data);
      });
  };

  const handleEmail = e => {
    setEmail(e.target.value);
  };

  const handlePassword = e => {
    setPassword(e.target.value);
  };

  const handleOpenSignUpDialog = () => {
    props.setSignInDialog(false);
    props.setSignUpDialog(true);
  };

  const handleOpenFindPasswordDialog = () => {
    props.setSignInDialog(false);
    props.setFindPasswordDialog(true);
  };

  return (
    <Dialog onClose={handleCloseSignIndialog} open={props.signInDialog}>
      <DialogHeader onClose={handleCloseSignIndialog}>
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
