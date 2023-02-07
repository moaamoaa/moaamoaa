import styled from '@emotion/styled';
import { AccordionSummary, Box, Button, IconButton } from '@mui/material';
import { Link } from 'react-router-dom';
import MenuIcon from '@mui/icons-material/Menu';

function NavbarMenu() {
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
    <>
      <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
        {pages.map(page => (
          <Button
            key={page.text}
            sx={{ my: 2, color: 'white', display: 'block' }}
          >
            <MoaLink to={page.link}>{page.text}</MoaLink>
          </Button>
        ))}
      </Box>
      <Box sx={{ display: { xs: 'flex', md: 'none' } }}>
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
    </>
  );
}

const MoaLink = styled(Link)`
  text-decoration: none;
  color: white;
`;

export default NavbarMenu;
