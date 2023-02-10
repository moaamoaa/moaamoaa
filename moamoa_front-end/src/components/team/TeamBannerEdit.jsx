import * as React from 'react';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import styled from 'styled-components';
import ImageUploader from 'components/team/ImageUploader';

export default function TeamBannerEdit(props) {
  const { post } = props;

  return (
    <Paper
      sx={{
        position: 'relative',
        color: '#fff',
        mb: 4,
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        backgroundImage: `url(${post.image})`,
        height: 'calc(400px + 10vw)', // 반응형 웹 calc
        maxHeight: 'calc(100vh - 56px)',
      }}
    >
      <Dim />
      <Grid container>
        <Grid item md={6}>
          <Box
            sx={{
              position: 'relative',
              p: { xs: 3, md: 6 },
              pr: { md: 0 },
            }}
          >
            <ImageUploader></ImageUploader>
            <Typography
              component="h1"
              variant="h2"
              color="inherit"
              gutterBottom
            >
              {post.title}
            </Typography>
            <Typography variant="h4" color="inherit" paragraph>
              {post.leader}
            </Typography>
          </Box>
        </Grid>
      </Grid>
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
`;
