import * as React from 'react';
import PropTypes from 'prop-types';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

export default function SignUpPasswordForm() {
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
        />
        <Box sx={{ mt: 1 }} />
        <TextField
          id="outlined-basic"
          label="비밀번호 확인 "
          fullWidth
          variant="outlined"
        />
      </DialogContent>
    </div>
  );
}
