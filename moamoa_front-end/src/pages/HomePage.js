import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import CardList from '../components/CardList';
import MainBanner from './MainBanner';

const theme = createTheme();

const mainBanner = {
  title: '모아모아 홈화면 배너 이미지입니다!',
  description: '이미지가 정해지면 넣을거예요!',
  image: 'https://source.unsplash.com/random',
  imageText: 'main image description',
};

export default function HomePageSample() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Container maxWidth="lg">
        <main>
          <MainBanner post={mainBanner} />
          <Grid container>
            <Grid>
              금주의 추천 프로젝트 섹션
              <CardList></CardList>
            </Grid>
            <Grid>
              금주의 추천 스터디 섹션
              <CardList></CardList>
            </Grid>
            <Grid>
              금주의 추천 팀원 섹션
              <CardList></CardList>
            </Grid>
          </Grid>
        </main>
      </Container>
    </ThemeProvider>
  );
}
