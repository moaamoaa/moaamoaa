import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';

import NavbarLogo from './NavbarLogo';
import NavbarAccount from './NavbarAccount';

function ResponsiveAppBar() {
  return (
    <AppBar position="fixed">
      <Container fixed>
        <Toolbar disableGutters>
          <NavbarLogo></NavbarLogo>

          <NavbarAccount></NavbarAccount>
        </Toolbar>
      </Container>
    </AppBar>
  );
}
export default ResponsiveAppBar;
