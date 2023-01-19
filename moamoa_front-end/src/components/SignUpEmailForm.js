import * as React from 'react';
import TextField from '@mui/material/TextField';
import DialogContent from '@mui/material/DialogContent';
import Typography from '@mui/material/Typography';

export default function SignUpEmailForm(props) {
  if (props.type === 'signUpEmail') {
    return (
      <DialogContent dividers>
        <Typography gutterBottom>모아모아에 오신 것을 환영합니다.</Typography>
        <Typography gutterBottom>이메일을 알려주세요!</Typography>
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
