import { useState } from 'react';
import customAxios from 'utils/axios';

import {
  TextField,
  DialogContent,
  Typography,
  Button,
  Box,
} from '@mui/material/';

export default function SignUpEmailForm(props) {
  const [userEmail, setUserEmail] = useState(props.email);
  const [emailMessage, setEmailMessage] = useState('');
  const [resStatus, setResStatus] = useState(false);

  const handleEmail = e => {
    setUserEmail(e.target.value);
  };

  const getCode = () => {
    // 로딩창 보여주기
    setResStatus(true);
    customAxios.basicAxios
      .get(`/users/email?email=${userEmail}`)
      .then(response => {
        setResStatus(false);
        props.setActiveStep(1);

        props.handleEmail(userEmail);
        props.handleCode(response.data);
        console.log(response.data);
      })
      .catch(error => {
        setResStatus(false);
        const errorStatus = error.response.data.status;

        if (errorStatus === 409) setEmailMessage('이미 가입된 이메일 입니다.');
        if (errorStatus === 500)
          setEmailMessage('잘못된 형식의 이메일 입니다.');
      });
  };

  if (props.type === 'signUpEmail') {
    return (
      <DialogContent dividers>
        <Typography gutterBottom>모아모아에 오신 것을 환영합니다.</Typography>
        <Typography gutterBottom>이메일을 알려주세요!</Typography>
        <TextField
          margin="dense"
          id="outlined-basic"
          label="이메일 작성"
          type="email"
          fullWidth
          variant="outlined"
          required={true}
          value={userEmail}
          onChange={handleEmail}
          helperText={emailMessage}
        />
        {resStatus ? (
          <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
            <Button
              variant="contained"
              disabled
              sx={{ mt: 3, ml: 1 }}
              onClick={getCode}
            >
              로딩중...
            </Button>
          </Box>
        ) : (
          <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
            <Button variant="contained" sx={{ mt: 3, ml: 1 }} onClick={getCode}>
              다음
            </Button>
          </Box>
        )}
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
