import { useState } from 'react';
import { Link } from 'react-router-dom';

import styled from '@emotion/styled';

import {
  Box,
  IconButton,
  Menu,
  Button,
  MenuItem,
  Typography,
} from '@mui/material/';

import MenuIcon from '@mui/icons-material/Menu';

// import TemporaryDrawer from 'components/common/drawer/TemporaryDrawer';

const pages = [
  {
    text: '팀 구하기',
    link: 'TeamSearchPage',
  },
  {
    text: '팀원 구하기',
    link: 'TeamMemberSearchPage',
  },
];

function ResponsiveAppBar() {
  const [anchorElNav, setAnchorElNav] = useState(null);

  const handleOpenNavMenu = event => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  return (
    <>
      <Typography
        variant="h6"
        noWrap
        component="a"
        href="/"
        sx={{
          mr: 2,
          display: { xs: 'none', md: 'flex' },
          fontFamily: 'monospace',
          fontWeight: 700,
          letterSpacing: '.3rem',
          color: 'inherit',
          textDecoration: 'none',
        }}
      >
        MOAMOA
      </Typography>

      <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
        <IconButton
          size="large"
          aria-label="account of current user"
          aria-controls="menu-appbar"
          aria-haspopup="true"
          onClick={handleOpenNavMenu}
          color="inherit"
        >
          <MenuIcon />
        </IconButton>

        {/* <TemporaryDrawer position={'left'}></TemporaryDrawer> */}

        <Menu
          id="menu-appbar"
          anchorEl={anchorElNav}
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'left',
          }}
          keepMounted
          transformOrigin={{
            vertical: 'top',
            horizontal: 'left',
          }}
          open={Boolean(anchorElNav)}
          onClose={handleCloseNavMenu}
          sx={{
            display: { xs: 'block', md: 'none' },
          }}
        >
          {pages.map(page => (
            <MenuItem key={page.text} onClick={handleCloseNavMenu}>
              <Typography textAlign="center">
                <MoaLink to={page.link}>{page.text}</MoaLink>
              </Typography>
            </MenuItem>
          ))}
        </Menu>
      </Box>

      <Typography
        variant="h5"
        noWrap
        component="a"
        href="/"
        sx={{
          display: { xs: 'flex', md: 'none' },
          flexGrow: 1,
          fontFamily: 'monospace',
          fontWeight: 700,
          letterSpacing: '.3rem',
          color: 'inherit',
          textDecoration: 'none',
        }}
      >
        MOAMOA
      </Typography>

      <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
        {pages.map(page => (
          <Button
            key={page.text}
            onClick={handleCloseNavMenu}
            sx={{ my: 2, color: 'white', display: 'block' }}
          >
            <MoaLink to={page.link}>{page.text}</MoaLink>
          </Button>
        ))}
      </Box>
    </>
  );
}
export default ResponsiveAppBar;

const MoaLink = styled(Link)`
  text-decoration: none;
  color: white;
`;
