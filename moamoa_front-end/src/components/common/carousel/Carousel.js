import React from 'react';
import CardItem from './CardItem';
// import Grid from '@mui/material/Grid';
import 'pure-react-carousel/dist/react-carousel.es.css';
import {
  CarouselProvider,
  Slider,
  Slide,
  ButtonBack,
  ButtonNext,
} from 'pure-react-carousel';

// const cards = [1, 2, 3];

const Carousel = () => {
  return (
    <CarouselProvider
      naturalSlideWidth={100}
      naturalSlideHeight={50}
      totalSlides={3}
    >
      <Slider>
        {/* {cards.map(card => (
          <Grid item key={card} xs={12} sm={6} md={4}>
            <CardItem>
              CardItem컴포넌트 props 대신 redux 이용해서 넣을 부분
            </CardItem>
          </Grid>
        ))} */}
        <Slide index={0}>
          <CardItem></CardItem>
        </Slide>
        <Slide index={1}>
          <CardItem></CardItem>
        </Slide>
        <Slide index={2}>
          <CardItem></CardItem>
        </Slide>
        {/* 값을 하나씩 받아와야 한다는 단점이 있다  */}
      </Slider>
      <ButtonBack>Back</ButtonBack>
      <ButtonNext>Next</ButtonNext>
    </CarouselProvider>
  );
};

export default Carousel;
