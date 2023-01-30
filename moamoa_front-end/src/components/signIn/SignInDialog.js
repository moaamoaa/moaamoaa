import { useState } from 'react';

import axios from 'axios';

import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';

import DialogHeader from 'components/common/dialog/DialogHeader';

const baseUrl = 'http://localhost:8080';

export default function SignInDialog(props) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleCloseSignIndialog = () => {
    props.setSignInDialog(false);
  };

  const handleLogIn = () => {
    axios
      .post(`${baseUrl}/users/login`, {
        email: email,
        password: password,
      })
      .then(response => {
        console.log('로그인 성공');
        console.log(response);
        const token = response.data.accessToken;
        const user_pk = response.data.user_pk;

        console.log(token);
        // 로그인 성공 시 유저 정보 axios 요청
        axios
          .get(
            `${baseUrl}/`,
            {
              user_pk: user_pk,
            },
            {
              headers: {
                Authorization: `Token ${token}`,
              },
            },
          )
          .then(response => {
            console.log(response);
          })
          .catch(error => {
            console.log(error);
          });
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
