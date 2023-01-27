import { useState } from 'react';
import axios from 'axios';

import TextField from '@mui/material/TextField';
import DialogContent from '@mui/material/DialogContent';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';

export default function SignUpNicknameForm(props) {
  // 기본 url
  const baseUrl = 'http://localhost:8080';
  const [userName, setUserName] = useState('');
  const [userNameError, setUserNameError] = useState(false);
  // input 작성하는 닉네임 정보를 부모 컴포넌트
  const changeNameHandler = e => {
    setUserName(e.target.value);
    setUserNameError(false);
  };

  const handleBackStep = () => {
    props.setActiveStep(1);
  };

  const submitNickName = async e => {
    e.preventDefault();

    try {
      const response = await axios.get(
        `${baseUrl}/users/nickname?nickname=${userName}`,
      );
      console.log(response.data);
      props.setActiveStep(3);
      props.nameHandler(userName);
    } catch (error) {
      console.log(error);
      setUserNameError(true);
    }
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
          onChange={changeNameHandler}
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
          >
            다음
          </Button>
        </Box>
      </DialogContent>
    </div>
  );
}
