import React from 'react';
import {
  CarouselProvider,
  Slider,
  Slide,
  ButtonBack,
  ButtonNext,
} from 'pure-react-carousel';
import 'pure-react-carousel/dist/react-carousel.es.css';

import CardItem from 'components/common/card/CardItem';

function Carousel(props) {
  return (
    <CarouselProvider
      naturalSlideWidth={100}
      naturalSlideHeight={50}
      totalSlides={3}
    >
      <Slider>
        {props.cards.map((card, idx) => (
          <Slide index={idx} xs={12} sm={6} md={4}>
            <CardItem card={card} type={props.type}></CardItem>
          </Slide>
        ))}
      </Slider>
    </CarouselProvider>
  );
}

export default Carousel;
