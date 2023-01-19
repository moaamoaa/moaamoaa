import * as React from 'react';
import TextField from '@mui/material/TextField';
import DialogContent from '@mui/material/DialogContent';
import Typography from '@mui/material/Typography';

export default function SignUpNicknameDialog() {
  return (
    <div>
      <DialogContent dividers>
        <Typography gutterBottom>인증해주셔서 감사합니다.</Typography>
        <Typography gutterBottom>닉네임을 설정해 볼까요?</Typography>
        <TextField id="outlined-basic" label="닉네임 입력" variant="outlined" />
      </DialogContent>
    </div>
  );
}
