import { AppBar, Toolbar, Container } from '@mui/material/';

import NavbarLogo from 'components/common/navbar/NavbarLogo';
import NavbarAccount from 'components/common/navbar/NavbarAccount';

export default function ResponsiveAppBar() {
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
