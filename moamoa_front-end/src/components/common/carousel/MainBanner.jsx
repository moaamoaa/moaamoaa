import * as React from 'react';

import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import Link from '@mui/material/Link';
import Box from '@mui/material/Box';

import styled from 'styled-components';
import Container from '@mui/material/Container';

export default function MainBanner() {
  const post = {
    img: '',
    title: '모아모아에 오신 것을 환영합니다.',
    description: '',
    linkText: '깃랩으로 가기',
  };

  return (
    <Paper
      sx={{
        position: 'relative',
        color: '#fff',
        mb: 4,
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        backgroundImage: `${post.img}`,
        height: 'calc(200px + 40vw)',
        maxHeight: 'calc(100vh - 56px)',
        borderRadius: 0,
      }}
    >
      <Dim />
      <Container fixed>
        <Grid container>
          <Grid item md={8}>
            <Box
              sx={{
                position: 'relative',
                py: { xs: 3, md: 6 },
                pr: { md: 0 },
              }}
            >
              <Typography
                component="h1"
                variant="h3"
                color="inherit"
                sx={{
                  fontSize: { xs: 24, md: 32, xl: 56 },
                }}
                gutterBottom
              >
                {post.title}
              </Typography>
              <Typography variant="h5" color="inherit" paragraph>
                {post.description}
              </Typography>
              <Link
                variant="subtitle1"
                href="https://lab.ssafy.com/s08-webmobile2-sub2/S08P12B110/-/tree/develop"
              >
                {post.linkText}
              </Link>
            </Box>
          </Grid>
        </Grid>
      </Container>
    </Paper>
  );
}

const Dim = styled(Box)`
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.2);
  border-radius: 0;
`;
