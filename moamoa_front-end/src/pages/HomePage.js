import * as React from 'react';

import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';

import CardList from 'components/common/card/CardList';
import MainBanner from 'components/common/carousel/MainBanner';

import styled from 'styled-components';

const mainBanner = {
  title: '모아모아 홈 화면 배너 이미지입니다!',
  description: '이미지가 정해지면 넣을거예요!',
  image: 'https://source.unsplash.com/random',
  imageText: 'main image description',
  linkText: '깃랩으로',
};

const cards = [
  { id: 0 },
  { id: 1 },
  { id: 2 },
  { id: 3 },
  { id: 4 },
  { id: 5 },
  { id: 6 },
  { id: 7 },
  { id: 8 },
  { id: 9 },
  { id: 10 },
];

export default function HomePageSample() {
  return (
    <>
      <MainBanner post={mainBanner} />
      <Container fixed>
        <Grid container>
          <MoaGrid item xs={12}>
            <MoaTypography>금주의 추천 프로젝트 섹션</MoaTypography>
            <CardList cards={cards.slice(0, 3)} type={'team'}></CardList>
          </MoaGrid>
          <MoaGrid item xs={12}>
            <MoaTypography>금주의 추천 스터디 섹션</MoaTypography>
            <CardList cards={cards.slice(0, 3)} type={'team'}></CardList>
          </MoaGrid>
          <MoaGrid item xs={12}>
            <MoaTypography>금주의 추천 팀원 섹션</MoaTypography>
            <CardList cards={cards.slice(0, 4)} type={'member'}></CardList>
          </MoaGrid>
        </Grid>
      </Container>
    </>
  );
}

const MoaGrid = styled(Grid)`
  min-height: 200px;
`;

const MoaTypography = styled(Typography)`
  font-size: x-large;
  font-weight: 900;
  margin-bottom: 16px;
`;
