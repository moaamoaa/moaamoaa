import { useState } from 'react';
import axios from 'axios';

import TextField from '@mui/material/TextField';
import DialogContent from '@mui/material/DialogContent';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';

export default function SignUpEmailForm(props) {
  // 기본 url
  const baseUrl = 'http://localhost:8080';
  // input에 작성하는 이메일 정보
  const [userEmail, setUserEmail] = useState(props.email);
  const changeEmailHandler = e => {
    setUserEmail(e.target.value);
  };

  const getCode = async e => {
    try {
      const response = await axios.get(
        `${baseUrl}/users/email?email=${userEmail}`,
      );
      // 이메일 중복 검사에 걸리지 않았다면, stageHandler를 다음 단계로 넘어가게함
      console.log(response.data);
      props.setActiveStep(1);
      // 부모 컴포넌트에 이메일과 인증코드를 props로 넘겨줌
      props.emailHandler(userEmail);
      props.codeHandler(response.data);
    } catch (error) {
      // 이메일 중복검사에서 중복된 이메일이면 409 에러와 함께 백에서 메시지 넘어옴
      console.log(error);
    }
  };

  if (props.type === 'signUpEmail') {
    return (
      <DialogContent dividers>
        <Typography gutterBottom>모아모아에 오신 것을 환영합니다.</Typography>
        <Typography gutterBottom>이메일을 알려주세요!</Typography>
        <TextField
          margin="dense"
          id="name"
          label="Email Address"
          type="email"
          fullWidth
          variant="standard"
          required={true}
          value={userEmail}
          onChange={changeEmailHandler}
        />
        <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
          <Button variant="contained" sx={{ mt: 3, ml: 1 }} onClick={getCode}>
            다음
          </Button>
        </Box>
      </DialogContent>
    );
  } else if (props.type === 'findPassword') {
    return (
      <DialogContent dividers>
        <Typography gutterBottom>
          비밀번호를 찾고자하는 이메일을 알려주세요!
        </Typography>
        <TextField
          autoFocus
          margin="dense"
          id="name"
          label="Email Address"
          type="email"
          fullWidth
          variant="standard"
        />
      </DialogContent>
    );
  }
}
