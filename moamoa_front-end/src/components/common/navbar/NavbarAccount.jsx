import { useEffect, useState } from 'react';
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
import Diversity3Icon from '@mui/icons-material/Diversity3';

import LogInDialog from 'components/logIn/LogInDialog';
import CheckoutDialog from 'components/signUp/CheckoutDialog';
import FindPasswordDialog from 'components/logIn/FindPasswordDialog';
import scrollToTop from 'utils/scrollToTop';

export default function NavbarAccount() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [isMobile, setIsMobile] = useState(window.innerWidth <= 500);
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

  const handleOpenProfile = () => {
    dispatch(changeProfilePk({ id: userPk }));
    handleCloseUserMenu();
    navigate('/ProfilePage');
    scrollToTop();
  };

  const handleLogOut = () => {
    handleCloseUserMenu();
    dispatch(logoutSuccess());
    navigate('/');
    Cookies.remove('access_token');
    scrollToTop();
  };

  const settings = [
    {
      text: '프로필',
      icon: 'LogoutIcon',
      handler: handleOpenProfile,
    },
    {
      text: '로그아웃',
      icon: 'LogoutIcon',
      handler: handleLogOut,
    },
  ];

  useEffect(() => {
    const handleWindowResize = () => setWindowWidth(window.innerWidth);
    setIsMobile(windowWidth < 500);

    window.addEventListener('resize', handleWindowResize);
    return () => window.removeEventListener('resize', handleWindowResize);
  }, [windowWidth]);

  if (isLogIn) {
    return (
      <>
        <Box sx={{ display: 'flex', scale: isMobile ? '0.5' : '1' }}>
          {/* 팀관리아이콘 */}
          <IconButton onClick={null} sx={{ mr: 2 }}>
            <Diversity3Icon />
          </IconButton>
          {/* 채팅아이콘 */}
          <IconButton onClick={null} sx={{ mr: 2 }}>
            <ChatIcon />
          </IconButton>
          {/* 아바타버튼 */}
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
