import { useState } from 'react';

import {
  TextField,
  DialogContent,
  Typography,
  Box,
  Button,
} from '@mui/material/';
import AutorenewIcon from '@mui/icons-material/Autorenew';

export default function SignUpCertificationform(props) {
  // 입력받은 코드를 저장할 state
  const [userVaildCode, setUserVaildCode] = useState('');
  const [codeError, setCodeError] = useState(false);

  // 입력받은 코드를 state에 변경해서 저장
  const changeCodeHandler = e => {
    setUserVaildCode(e.target.value);
    setCodeError(false);
  };

  const handleBackStep = () => {
    props.setActiveStep(0);
  };

  // 인증코드를 체크하는 함수
  const codeCheck = e => {
    e.preventDefault();
    if (props.code === Number(userVaildCode)) {
      props.setActiveStep(2);
    } else {
      console.log('인증번호가 틀렸습니다');
      setCodeError(true);
    }
  };

  return (
    <div>
      <DialogContent dividers>
        <Typography gutterBottom>
          해당 이메일로 인증코드를 보냈습니다.
        </Typography>
        <Typography gutterBottom>코드를 입력해서 인증해주세요.</Typography>

        <TextField
          id="outlined-basic"
          label="인증코드입력"
          variant="outlined"
          fullWidth
          required={true}
          value={userVaildCode}
          onChange={changeCodeHandler}
          helperText={codeError ? '잘못된 인증코드입니다' : ''}
        />
        <AutorenewIcon fontSize="large" />

        <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
          <Button onClick={handleBackStep} sx={{ mt: 3, ml: 1 }}>
            이전
          </Button>

          <Button variant="contained" onClick={codeCheck} sx={{ mt: 3, ml: 1 }}>
            다음
          </Button>
        </Box>
      </DialogContent>
    </div>
  );
}
