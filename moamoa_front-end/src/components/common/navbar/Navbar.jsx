import {
  AppBar,
  Toolbar,
  Container,
  Accordion,
  AccordionDetails,
  Typography,
  ListItem,
  Grid,
} from '@mui/material/';

import NavbarLogo from 'components/common/navbar/NavbarLogo';
import NavbarAccount from 'components/common/navbar/NavbarAccount';
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
    <AppBar position="fixed">
      <Container fixed>
        <MoaAccordion sx={{ border: 0 }}>
          <Toolbar disableGutters>
            <NavbarLogo></NavbarLogo>

            <NavbarAccount></NavbarAccount>
          </Toolbar>
          <AccordionDetails sx={{ padding: '0' }}>
            <Grid>
              {pages.map((page, idx) => (
                <ListItem key={idx} sx={{ paddingLeft: '0' }}>
                  <Typography textAlign="center">
                    <MoaLink to={page.link}>{page.text}</MoaLink>
                  </Typography>
                </ListItem>
              ))}
            </Grid>
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
