import * as React from 'react';
import PropTypes from 'prop-types';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';

const BootstrapDialog = styled(Dialog)(({ theme }) => ({
  '& .MuiDialogContent-root': {
    padding: theme.spacing(2),
  },
  '& .MuiDialogActions-root': {
    padding: theme.spacing(1),
  },
}));

function BootstrapDialogTitle(props) {
  const { children, onClose, ...other } = props;

  // const handleSubmit = (event) => {
  //   event.preventDefault();
  //   const data = new FormData(event.currentTarget);
  //   console.log({
  //     email: data.get('email'),
  //     password: data.get('password'),
  //   });
  // };

  return (
    <DialogTitle sx={{ m: 0, p: 2 }} {...other}>
      {children}
      {onClose ? (
        <IconButton
          aria-label="close"
          onClick={onClose}
          sx={{
            position: 'absolute',
            right: 8,
            top: 8,
            color: theme => theme.palette.grey[500],
          }}
        >
          <CloseIcon />
        </IconButton>
      ) : null}
    </DialogTitle>
  );
}

BootstrapDialogTitle.propTypes = {
  children: PropTypes.node,
  onClose: PropTypes.func.isRequired,
};

export default function CustomizedDialogs(props) {
  const handleCloseSignIndialog = () => {
    props.setSignInDialog(false);
  };

  const handleOpenSignUpDialog = () => {
    props.setSignInDialog(false);
    props.setSignUpDialog(true);
  };

  return (
    <BootstrapDialog
      onClose={handleCloseSignIndialog}
      aria-labelledby="customized-dialog-title"
      open={props.signInDialog}
    >
      <BootstrapDialogTitle
        id="customized-dialog-title"
        onClose={handleCloseSignIndialog}
      >
        모아모아에 오신 것을 환영합니다.
      </BootstrapDialogTitle>
      <DialogContent dividers>
        <TextField
          label="이메일을 입력해주세요"
          required
          fullWidth
          name="email"
          autoComplete="email"
        />

        <TextField
          label="비밀번호를 입력해주세요"
          type="password"
          required
          fullWidth
          name="password"
          autoComplete="current-password"
        />

        <Button type="submit" fullWidth variant="contained" sx={{ mt: 3 }}>
          로그인
        </Button>

        <Grid container justifyContent="flex-end">
          <Grid item>
            <Button
              variant="text"
              color="primary"
              onClick={handleOpenSignUpDialog}
            >
              비밀번호 찾기
            </Button>
          </Grid>
          <Grid item>
            <Button
              variant="text"
              color="primary"
              onClick={handleOpenSignUpDialog}
            >
              회원가입
            </Button>
          </Grid>
        </Grid>
      </DialogContent>
    </BootstrapDialog>
  );
}
