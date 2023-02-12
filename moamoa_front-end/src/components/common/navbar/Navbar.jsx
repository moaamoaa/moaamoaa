import {
  AppBar,
  Toolbar,
  Container,
  Accordion,
  AccordionDetails,
  Typography,
  ListItem,
  Grid,
  Divider,
} from '@mui/material/';

import NavbarLogo from 'components/common/navbar/NavbarLogo';
import NavbarAccount from 'components/common/navbar/NavbarAccount';
import NavbarMenu from 'components/common/navbar/NavbarMenu';

import { Link } from 'react-router-dom';
import styled from 'styled-components';

export default function ResponsiveAppBar() {
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
  return (
    <AppBar position="fixed" sx={{ backgroundColor: '#blue' }}>
      <Container fixed>
        <MoaAccordion sx={{ border: 0 }}>
          <Toolbar disableGutters>
            <Grid
              container
              spacing={0}
              alignItems="center"
              sx={{ display: { xs: 'flex', md: 'none' } }}
            >
              <Grid item xs={4}>
                <NavbarMenu></NavbarMenu>
              </Grid>
              <Grid item xs={4}>
                <NavbarLogo></NavbarLogo>
              </Grid>
              <Grid item xs={4}>
                <NavbarAccount></NavbarAccount>
              </Grid>
            </Grid>
            <Grid
              container
              spacing={0}
              alignItems="center"
              sx={{ display: { xs: 'none', md: 'flex' } }}
            >
              <Grid
                item
                container
                xs={6}
                alignItems="center"
                sx={{ display: 'flex' }}
              >
                <NavbarLogo></NavbarLogo>
                <NavbarMenu></NavbarMenu>
              </Grid>
              <Grid item xs={6} sx={{ display: 'flex' }} justifyContent="end">
                <NavbarAccount></NavbarAccount>
              </Grid>
            </Grid>
          </Toolbar>

          <AccordionDetails
            sx={{ padding: '0', display: { xs: '', md: 'none' } }}
          >
            {pages.map((page, idx) => (
              <Grid key={idx}>
                <Divider />
                <ListItem sx={{ padding: '0', py: '1rem' }}>
                  <Typography textAlign="center">
                    <MoaLink to={page.link}>{page.text}</MoaLink>
                  </Typography>
                </ListItem>
              </Grid>
            ))}
          </AccordionDetails>
        </MoaAccordion>
      </Container>
    </AppBar>
  );
}

const MoaAccordion = styled(Accordion)`
  background: none;

  box-shadow: none;

  color: #fff;
`;

const MoaLink = styled(Link)`
  text-decoration: none;
  color: #fff;
`;
