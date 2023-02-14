import { useState } from 'react';
import PropTypes from 'prop-types';
import {
  styled,
  Dialog,
  DialogTitle,
  IconButton,
  Button,
} from '@mui/material/';

import customAxios from 'utils/axios';

import CloseIcon from '@mui/icons-material/Close';
import SignUpEmailForm from 'components/signUp/SignUpEmailForm';

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

export default function FindPasswordDialog(props) {
  const [email, setEmail] = useState('');

  // setEmail 함수에 인자로 이메일 값을 전달합니다.
  const handleEmail = email => {
    setEmail(email);
  };

  const handleClose = () => {
    props.setFindPasswordDialog(false);
  };

  const handleAlert = () => {
    if (email) {
      customAxios.basicAxios
        .put(`/users/email/`, {
          email: email,
        })
        .then(response => {
          console.log(response.data);
          alert('입력하신 이메일로 임시 비밀번호를 발급하였습니다.');
          handleClose();
        })
        .catch(error => {
          console.log(error);
          alert('등록되지 않은 이메일입니다.');
        });
    } else {
      alert('이메일을 입력해주세요.');
    }
  };

  return (
    <BootstrapDialog
      onClose={handleClose}
      aria-labelledby="customized-dialog-title"
      open={props.open}
    >
      <SignUpEmailForm type={'findPassword'} handleEmail={handleEmail} />
      <Button variant="text" color="primary" onClick={handleAlert}>
        비밀번호 찾기
      </Button>
    </BootstrapDialog>
  );
}
