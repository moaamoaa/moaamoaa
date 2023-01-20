import * as React from 'react';
import TextField from '@mui/material/TextField';
import DialogContent from '@mui/material/DialogContent';
import Typography from '@mui/material/Typography';
import AutorenewIcon from '@mui/icons-material/Autorenew';

export default function SignUpCertificationform() {
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
        />
        <AutorenewIcon fontSize="large" />
      </DialogContent>
    </div>
  );
}
