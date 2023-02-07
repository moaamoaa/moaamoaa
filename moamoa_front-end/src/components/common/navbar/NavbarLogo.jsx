import { Link } from 'react-router-dom';

import styled from '@emotion/styled';

import {
  Box,
  IconButton,
  Button,
  Typography,
  AccordionSummary,
} from '@mui/material/';

import MenuIcon from '@mui/icons-material/Menu';

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
        <AccordionSummary sx={{ padding: '0' }}>
          <IconButton
            size="large"
            aria-label="account of current user"
            aria-controls="menu-appbar"
            aria-haspopup="true"
            color="inherit"
            sx={{ padding: '0' }}
          >
            <MenuIcon />
          </IconButton>
        </AccordionSummary>
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
