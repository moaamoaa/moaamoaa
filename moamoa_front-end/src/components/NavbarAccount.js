import * as React from 'react';
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
  const [anchorElUser, setAnchorElUser] = React.useState(null);
  const [isLogIn, setisLogIn] = React.useState(false);

  const handleOpenUserMenu = event => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const handleUserToken = () => {
    setisLogIn(true);
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
      <Box sx={{ flexGrow: 0 }}>
        <Button variant="text" onClick={handleUserToken} color="secondary">
          Log in
        </Button>
      </Box>
    );
  }
}