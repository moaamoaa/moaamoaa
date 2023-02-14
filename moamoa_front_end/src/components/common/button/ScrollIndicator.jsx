import React from 'react';
import Typography from '@mui/material/Typography';
import { Grid } from '@mui/material';
import styled from '@emotion/styled';
import KeyboardDoubleArrowDownRoundedIcon from '@mui/icons-material/KeyboardDoubleArrowDownRounded';
import useIsAtTop from 'hooks/useIsAtTop';

const ScrollIndicator = () => {
  const isAtTop = useIsAtTop();

  return (
    <ScrollIndicatorIcon
      container
      sx={{
        position: 'absolute',
        display: { xs: 'none', md: 'block' },
        bottom: '30px',
        left: '50%',
        transform: 'translateX(-50%)',
        zIndex: '1',
        opacity: isAtTop ? 1 : 0,
        textShadow: '0px 0px 5px #00000050',
        color: '#fff',
      }}
      rowSpacing={2}
    >
      <Grid item xs={12} display={'flex'} justifyContent="center">
        <ScrollIndiactorTypography variant="h6" color="initial">
          SCROLL DOWN
        </ScrollIndiactorTypography>
      </Grid>
      <Icon
        item
        xs={12}
        paddingTop={2}
        display={'flex'}
        justifyContent="center"
      >
        <KeyboardDoubleArrowDownRoundedIcon
          fontSize="large"
          sx={{ filter: ' drop-shadow(0px 0px 2px rgb(0 0 0 / 0.4))' }}
        />
      </Icon>
    </ScrollIndicatorIcon>
  );
};

const ScrollIndicatorIcon = styled(Grid)`
  animation: blink 1.4s linear infinite;

  @keyframes blink {
    0% {
      opacity: 0.8;
    }
    50% {
      opacity: 0;
    }
    100% {
      opacity: 0.8;
    }
  }
`;

const ScrollIndiactorTypography = styled(Typography)`
  color: #fff;
  margin-right: 10px;
`;

const Icon = styled(Grid)`
  animation: bounce 1.4s ease infinite;
  @keyframes bounce {
    50% {
      transform: translateY(-25%);
    }
  }
`;
export default ScrollIndicator;
