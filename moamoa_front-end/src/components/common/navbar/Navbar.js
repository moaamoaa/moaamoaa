import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';

import NavbarLogo from 'components/common/navbar/NavbarLogo';
import NavbarAccount from 'components/common/navbar/NavbarAccount';

export default function ResponsiveAppBar() {
  console.log(window);

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
