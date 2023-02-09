import { useNavigate } from 'react-router-dom';

import { Typography } from '@mui/material/';
import scrollToTop from 'utils/scrollToTop';

function ResponsiveAppBar() {
  const navigate = useNavigate();
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

  const handleOpenHome = () => {
    navigate('/');
    scrollToTop();
  };

  return (
    <>
      <Typography
        variant="h5"
        noWrap
        onClick={handleOpenHome}
        sx={{
          flexGrow: 1,
          fontFamily: 'monospace',
          fontWeight: 700,
          letterSpacing: '.3rem',
          color: 'inherit',
          textDecoration: 'none',
          cursor: 'pointer',
          textAlign: 'center',
        }}
      >
        MOAMOA
      </Typography>
    </>
  );
}

export default ResponsiveAppBar;
