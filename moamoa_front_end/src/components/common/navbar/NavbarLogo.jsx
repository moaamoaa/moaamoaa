import { useNavigate } from 'react-router-dom';

import { Typography } from '@mui/material/';
import scrollToTop from 'utils/scrollToTop';
import { useEffect, useState } from 'react';

function ResponsiveAppBar() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [isMobile, setIsMobile] = useState(window.innerWidth <= 500);
  const navigate = useNavigate();

  const handleOpenHome = () => {
    navigate('/');
    scrollToTop();
  };

  useEffect(() => {
    const handleWindowResize = () => setWindowWidth(window.innerWidth);
    setIsMobile(windowWidth < 500);

    window.addEventListener('resize', handleWindowResize);
    return () => window.removeEventListener('resize', handleWindowResize);
  }, [windowWidth]);

  return (
    <>
      <Typography
        variant={isMobile ? 'body1' : 'h6'}
        noWrap
        onClick={handleOpenHome}
        sx={{
          fontFamily: 'monospace',
          fontWeight: 600,
          letterSpacing: '.2rem',
          color: 'inherit',
          textDecoration: 'none',
          cursor: 'pointer',
          textAlign: { xs: 'center', md: 'start' },
          mr: { xs: 0, md: 2 },
        }}
      >
        MOAAMOAA
      </Typography>
    </>
  );
}

export default ResponsiveAppBar;
