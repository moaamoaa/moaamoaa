import { useState } from 'react';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import Avatar from '@mui/material/Avatar';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import ChatIcon from '@mui/icons-material/Chat';
import Button from '@mui/material/Button';
import SvgIcon from '@mui/material/SvgIcon';

import SignInDialog from 'components/signIn/SignInDialog';
import CheckoutDialog from 'components/signUp/CheckoutDialog';
import FindPasswordDialog from 'components/signIn/FindPasswordDialog';
import styled from '@emotion/styled';

import { useDispatch, useSelector } from 'react-redux';
import { logoutAccount } from 'redux/User';

const settings = [
  {
    text: 'Profile',
    icon: 'LogoutIcon',
  },
  {
    text: 'Logout',
    icon: 'LogoutIcon',
  },
];

export default function NavbarAccount() {
  const [anchorElUser, setAnchorElUser] = useState(null);
  const [isLogIn, setisLogIn] = useState(false);
  const [signInDialog, setSignInDialog] = useState(false);
  const [signUpDialog, setSignUpDialog] = useState(false);
  const [findPasswordDialog, setFindPasswordDialog] = useState(false);
  // 임시 로그인 확인. 나중에 지울 것
  const isLogin = useSelector(state => state.User.isLogged);
  const dispatch = useDispatch();

  const handleOpenUserMenu = event => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const handleClickOpen = () => {
    setSignInDialog(true);
  };

  if (isLogIn) {
    return (
      <Box sx={{ flexGrow: 0 }}>
        <Tooltip title="message">
          <IconButton onClick={null} sx={{ mr: 2 }}>
            <ChatIcon />
          </IconButton>
        </Tooltip>

        <Tooltip title="hi">
          <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
            <Avatar alt="User Profile" src="/static/images/avatar/2.jpg" />
          </IconButton>
        </Tooltip>
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
            <MenuItem key={setting} onClick={handleCloseUserMenu}>
              <SvgIcon component={setting.icon} inheritViewBox />
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
          {!isLogin ? (
            <MoaButton variant="text" onClick={handleClickOpen}>
              Log in
            </MoaButton>
          ) : (
            <MoaButton
              variant="text"
              onClick={() => {
                dispatch(logoutAccount());
              }}
            >
              LogOut
            </MoaButton>
          )}
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
