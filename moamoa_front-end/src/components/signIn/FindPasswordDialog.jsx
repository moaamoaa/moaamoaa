import * as React from 'react';
import PropTypes from 'prop-types';
import {
  styled,
  Dialog,
  DialogTitle,
  IconButton,
  Button,
} from '@mui/material/';

import CloseIcon from '@mui/icons-material/Close';

import SignUpEmailDialog from 'components/signUp/SignUpEmailForm';

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
  const handleClose = () => {
    props.setFindPasswordDialog(false);
  };

  const handleAlert = () => {
    alert('입렵하신 이메일로 임시 비밀번호를 발급하였습니다.');
    handleClose();
  };

  return (
    <BootstrapDialog
      onClose={handleClose}
      aria-labelledby="customized-dialog-title"
      open={props.open}
    >
      <SignUpEmailDialog type={'findPassword'} />
      <Button variant="text" color="primary" onClick={handleAlert}>
        Find
      </Button>
    </BootstrapDialog>
  );
}
