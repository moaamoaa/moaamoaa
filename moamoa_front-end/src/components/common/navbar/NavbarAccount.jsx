import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { logoutSuccess } from 'redux/user';
import { changeProfilePk } from 'redux/profile';

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

import LogInDialog from 'components/logIn/LogInDialog';
import CheckoutDialog from 'components/signUp/CheckoutDialog';
import FindPasswordDialog from 'components/logIn/FindPasswordDialog';
import scrollToTop from 'utils/scrollToTop';

export default function NavbarAccount() {
  const [anchorElUser, setAnchorElUser] = useState(null);
  const [logInDialog, setLogInDialog] = useState(false);
  const [signUpDialog, setSignUpDialog] = useState(false);
  const [findPasswordDialog, setFindPasswordDialog] = useState(false);

  const userPk = useSelector(state => state.user.userPk);
  const isLogIn = useSelector(state => state.user.isLogged);

  const dispatch = useDispatch();

  const navigate = useNavigate();

  const handleOpenUserMenu = event => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const handleClickOpen = () => {
    setLogInDialog(true);
  };

  const handleNavigate = () => {
    dispatch(changeProfilePk({ id: userPk }));
    handleCloseUserMenu();
    navigate('/ProfilePage');
    scrollToTop();
  };

  const handleUserToken = () => {
    handleCloseUserMenu();
    dispatch(logoutSuccess());
    Cookies.remove('access_token');
    navigate('/HomePage');
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
      <>
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
        <LogInDialog
          logInDialog={logInDialog}
          setLogInDialog={setLogInDialog}
          setSignUpDialog={setSignUpDialog}
          setFindPasswordDialog={setFindPasswordDialog}
        ></LogInDialog>
        <FindPasswordDialog
          open={findPasswordDialog}
          setFindPasswordDialog={setFindPasswordDialog}
        />
        <CheckoutDialog open={signUpDialog} setSignUpDialog={setSignUpDialog} />
      </>
    );
  } else {
    return (
      <>
        <Box sx={{ flexGrow: 0 }}>
          <MoaButton variant="text" onClick={handleClickOpen}>
            로그인
          </MoaButton>
        </Box>
        <LogInDialog
          logInDialog={logInDialog}
          setLogInDialog={setLogInDialog}
          setSignUpDialog={setSignUpDialog}
          setFindPasswordDialog={setFindPasswordDialog}
        ></LogInDialog>
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
