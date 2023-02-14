import { useState } from 'react';
import customAxios from 'utils/axios';

import {
  TextField,
  DialogContent,
  Typography,
  Button,
  Box,
} from '@mui/material/';

export default function SignUpNicknameForm(props) {
  const [userName, setUserName] = useState(props.name);
  const [userNameError, setUserNameError] = useState(false);

  const handleName = e => {
    setUserName(e.target.value);
    setUserNameError(false);
  };

  const handleBackStep = () => {
    props.setActiveStep(1);
  };

  const submitNickName = () => {
    customAxios.basicAxios
      .get(`/users/nickname?nickname=${userName}`)
      .then(response => {
        props.setActiveStep(3);
        props.handleName(userName);
      })
      .catch(error => {
        setUserNameError(true);
      });
  };

  return (
    <div>
      <DialogContent dividers>
        <Typography gutterBottom>인증해주셔서 감사합니다.</Typography>
        <Typography gutterBottom>닉네임을 설정해 볼까요?</Typography>
        <TextField
          id="outlined-basic"
          label="닉네임 입력"
          variant="outlined"
          fullWidth
          required={true}
          value={userName}
          onChange={handleName}
          helperText={userNameError ? '중복된 닉네임입니다' : ''}
        />
        <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
          <Button onClick={handleBackStep} sx={{ mt: 3, ml: 1 }}>
            이전
          </Button>

          <Button
            onClick={submitNickName}
            variant="contained"
            sx={{ mt: 3, ml: 1 }}
            disabled={userName.length >= 2 ? false : true}
          >
            다음
          </Button>
        </Box>
      </DialogContent>
    </div>
  );
}
