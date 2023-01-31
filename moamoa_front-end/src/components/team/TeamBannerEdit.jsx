import * as React from 'react';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import styled from 'styled-components';
import ImageUploader from 'components/common/carousel/ImageUploader';

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
        height: 'calc(200px + 40vw)', // 반응형 웹 calc
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
            <Grid container>
              <Stack
                direction="row"
                spacing={2}
                justifyContent="flex-start"
                sx={{ pt: 4 }}
              >
                <Button size="small" variant="contained" color="primary">
                  지원 및 제안 확인
                </Button>
                <Button size="small" variant="contained" color="primary">
                  등록
                </Button>
                <Button size="small" variant="contained" color="primary">
                  취소
                </Button>
              </Stack>
            </Grid>
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
