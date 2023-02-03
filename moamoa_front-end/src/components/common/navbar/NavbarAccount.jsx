import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { loginSuccess, logoutSuccess, loginFailure } from 'redux/User';
import Cookies from 'js-cookie';

import styled from '@emotion/styled';

import {
  Box,
  IconButton,
  Typography,
  Menu,
  Avatar,
  MenuItem,
  Button,
} from '@mui/material/';

import ChatIcon from '@mui/icons-material/Chat';

import SignInDialog from 'components/signIn/SignInDialog';
import CheckoutDialog from 'components/signUp/CheckoutDialog';
import FindPasswordDialog from 'components/signIn/FindPasswordDialog';
import scrollToTop from 'utils/scrollToTop';

export default function NavbarAccount() {
  const [anchorElUser, setAnchorElUser] = useState(null);
  const [signInDialog, setSignInDialog] = useState(false);
  const [signUpDialog, setSignUpDialog] = useState(false);
  const [findPasswordDialog, setFindPasswordDialog] = useState(false);
  // 임시 로그인 확인. 나중에 지울 것
  const isLogIn = useSelector(state => state.User.isLogged);
  const dispatch = useDispatch();

  const navigate = useNavigate();

  const handleOpenUserMenu = event => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const handleClickOpen = () => {
    setSignInDialog(true);
  };

  const handleNavigate = () => {
    handleCloseUserMenu();
    navigate('/ProfilePage');
    scrollToTop();
  };

  const handleUserToken = () => {
    handleCloseUserMenu();
    dispatch(logoutSuccess());
    Cookies.remove('access_token');
  };

  const settings = [
    {
      text: '프로필',
      icon: 'LogoutIcon',
      handler: handleNavigate,
    },
    {
      text: '로그아웃',
      icon: 'LogoutIcon',
      handler: handleUserToken,
    },
  ];

  if (isLogIn) {
    return (
      <Box sx={{ flexGrow: 0 }}>
        <IconButton onClick={null} sx={{ mr: 2 }}>
          <ChatIcon />
        </IconButton>

        <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
          <Avatar alt="User Profile" src="/static/images/avatar/2.jpg" />
        </IconButton>

        <Menu
          sx={{ mt: '45px' }}
          id="menu-appbar"
          anchorEl={anchorElUser}
          anchorOrigin={{
            vertical: 'top',
            horizontal: 'right',
          }}
          keepMounted
          transformOrigin={{
            vertical: 'top',
            horizontal: 'right',
          }}
          open={Boolean(anchorElUser)}
          onClose={handleCloseUserMenu}
        >
          {settings.map(setting => (
            <MenuItem key={setting.text} onClick={setting.handler}>
              <Typography textAlign="center">{setting.text}</Typography>
            </MenuItem>
          ))}
        </Menu>
      </Box>
    );
  } else {
    return (
      <>
        <Box sx={{ flexGrow: 0 }}>
          <MoaButton variant="text" onClick={handleClickOpen}>
            로그인
          </MoaButton>
        </Box>
        <SignInDialog
          signInDialog={signInDialog}
          setSignInDialog={setSignInDialog}
          setSignUpDialog={setSignUpDialog}
          setFindPasswordDialog={setFindPasswordDialog}
        ></SignInDialog>
        <FindPasswordDialog
          open={findPasswordDialog}
          setFindPasswordDialog={setFindPasswordDialog}
        />
        <CheckoutDialog open={signUpDialog} setSignUpDialog={setSignUpDialog} />
      </>
    );
  }
}

const MoaButton = styled(Button)`
  color: white;
`;
