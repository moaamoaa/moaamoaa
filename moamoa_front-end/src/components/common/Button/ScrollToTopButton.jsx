import { useState, useEffect } from 'react';

import IconButton from '@mui/material/IconButton';
import ArrowUpwardIcon from '@mui/icons-material/ArrowUpward';

import scrollToTop from 'utils/scrollToTop';
import styled from '@emotion/styled';

const ShowComponentOnScroll = ({ children, threshold = 200 }) => {
  const [shouldShow, setShouldShow] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      if (window.pageYOffset > threshold) {
        setShouldShow(true);
      } else {
        setShouldShow(false);
      }
    };

    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, [threshold]);

  return shouldShow ? children : null;
};

export default function ScrollToTopButton() {
  return (
    <ShowComponentOnScroll threshold={1000}>
      <FloatingButton aria-label="" onClick={scrollToTop}>
        <ArrowUpwardIcon></ArrowUpwardIcon>
      </FloatingButton>
    </ShowComponentOnScroll>
  );
}

const FloatingButton = styled(IconButton)`
  position: fixed;
  bottom: 5rem;
  right: 5rem;
`;
