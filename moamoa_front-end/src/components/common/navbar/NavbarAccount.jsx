import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { handleUserProfile, logoutSuccess } from 'redux/user';
import { handleProfilePk } from 'redux/profile';
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
import ProjectStudy from 'components/team/ProjectStudy';
import LogInDialog from 'components/logIn/LogInDialog';
import CheckoutDialog from 'components/signUp/CheckoutDialog';
import FindPasswordDialog from 'components/logIn/FindPasswordDialog';
import scrollToTop from 'utils/scrollToTop';
import customAxios from 'utils/axios';
import useMobile from 'hooks/useMobile';

export default function NavbarAccount() {
  const isMobile = useMobile();
  const [anchorElUser, setAnchorElUser] = useState(null);
  const [logInDialog, setLogInDialog] = useState(false);
  const [signUpDialog, setSignUpDialog] = useState(false);
  const [findPasswordDialog, setFindPasswordDialog] = useState(false);

  const userPk = useSelector(state => state.user.userPk);
  const isLogIn = useSelector(state => state.user.isLogged);
  const userImg = useSelector(state => state.user.userImg);

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
    dispatch(handleProfilePk({ id: userPk }));
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
    customAxios.authAxios
      .get(`/profile/${userPk}`)
      .then(response => {
        // console.log(response.data);
        dispatch(
          handleUserProfile({
            userImg: response.data.profile.img,
            userNickname: response.data.profile.nickname,
          }),
        );
      })
      .catch(error => {
        console.log(error);
      });
  }, [userPk]);

  if (isLogIn) {
    return (
      <>
        <Box sx={{ display: 'flex' }} justifyContent="end">
          {/* 팀관리아이콘 */}
          <ProjectStudy isMobile={isMobile}></ProjectStudy>
          {/* 채팅아이콘 */}
          <IconButton
            onClick={null}
            sx={{ mr: isMobile ? 0 : 2, scale: isMobile ? '.8' : '1' }}
          >
            <ChatIcon />
          </IconButton>
          {/* 아바타버튼 */}
          <IconButton
            onClick={handleOpenUserMenu}
            sx={{ p: 0, scale: isMobile ? '.5' : '1' }}
          >
            <Avatar
              alt="User Profile"
              src={userImg}
              style={{ objectFit: 'cover' }}
            />
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
        <Box sx={{ display: 'flex' }} justifyContent="end">
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
