import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import DialogContent from '@mui/material/DialogContent';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';

export default function SignUpPasswordForm(props) {
  //비밀번호 상태
  const [password, setPassword] = useState('');
  const [checkPassword, setcheckPassword] = useState('');

  //비밀번호 상태 바꾸는 함수
  const changePasswordHandler = e => {
    setPassword(e.target.value);
  };
  const changeCheckPasswordHandler = e => {
    setcheckPassword(e.target.value);
  };

  const handleBackStep = () => {
    props.setActiveStep(2);
  };

  //비밀번호 일치 확인 함수
  useEffect(() => {
    if (password !== '' && password !== checkPassword) {
      console.log('비밀번호가 일치하지 않습니다');
    } else if (password !== '' && password === checkPassword) {
      console.log('부모님께 드립니다');
      props.passwordHandler(password);
    }
  }, [checkPassword]);

  return (
    <div>
      <DialogContent dividers>
        <Typography gutterBottom>김동동님 반갑습니다.</Typography>
        <Typography gutterBottom>
          마지막으로 비밀번호를 설정해주세요!
        </Typography>
        <TextField
          id="outlined-basic"
          label="비밀번호 설정"
          fullWidth
          variant="outlined"
          required={true}
          value={password}
          onChange={changePasswordHandler}
        />
        <Box sx={{ mt: 1 }} />
        <TextField
          id="outlined-basic"
          label="비밀번호 확인 "
          fullWidth
          variant="outlined"
          required={true}
          value={checkPassword}
          onChange={changeCheckPasswordHandler}
        />

        <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
          <Button onClick={handleBackStep} sx={{ mt: 3, ml: 1 }}>
            이전
          </Button>
          <Button
            variant="contained"
            onClick={props.userSignUp}
            sx={{ mt: 3, ml: 1 }}
          >
            회원가입
          </Button>
        </Box>
      </DialogContent>
    </div>
  );
}
