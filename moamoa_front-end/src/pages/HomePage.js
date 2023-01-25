import * as React from 'react';

import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

import CardList from 'components/common/card/CardList';
import MainBanner from 'components/common/carousel/MainBanner';

import styled from 'styled-components';

const mainBanner = {
  title: '모아모아 홈화면 배너 이미지입니다!',
  description: '이미지가 정해지면 넣을거예요!',
  image: 'https://source.unsplash.com/random',
  imageText: 'main image description',
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
            금주의 추천 프로젝트 섹션
            <MoaCardList cards={cards.slice(0, 3)} type={'team'}></MoaCardList>
          </MoaGrid>
          <MoaGrid item xs={12}>
            금주의 추천 스터디 섹션
            <MoaCardList cards={cards.slice(0, 3)} type={'team'}></MoaCardList>
          </MoaGrid>
          <MoaGrid item xs={12}>
            금주의 추천 팀원 섹션
            <MoaCardList
              cards={cards.slice(0, 4)}
              type={'member'}
            ></MoaCardList>
          </MoaGrid>
        </Grid>
      </Container>
    </>
  );
}

const MoaGrid = styled(Grid)`
  min-height: 200px;
`;

const MoaCardList = styled(CardList)`
  margin: 48px;
`;
