import * as React from 'react';

import Grid from '@mui/material/Grid';

import CardItem from 'components/common/card/CardItem';
import styled from '@emotion/styled';

export default function CardList(props) {
  if (props.type === 'team') {
    return (
      <MoaGrid container spacing={10}>
        {props.cards.map(card => (
          <Grid item key={card.id} xs={12} md={6} lg={4}>
            <CardItem card={card} type={props.type}></CardItem>
          </Grid>
        ))}
      </MoaGrid>
    );
  } else if (props.type === 'member') {
    return (
      <MoaGrid container spacing={5}>
        {props.cards.map(card => (
          <Grid item key={card.id} xs={12} sm={6} md={4} lg={3}>
            <CardItem card={card} type={props.type}></CardItem>
          </Grid>
        ))}
      </MoaGrid>
    );
  } else if (props.type === 'tech') {
    return (
      <Grid container>
        {props.cards.map(card => (
          <Grid item key={card[1]} xs={3}>
            <CardItem card={card} type={props.type}></CardItem>
          </Grid>
        ))}
      </Grid>
    );
  } else if (props.type === 'link') {
    return (
      <Grid container>
        {Object.entries(props.cards).map(card => (
          <Grid item key={card[0]} xs={3}>
            <CardItem card={card} type={props.type}></CardItem>
          </Grid>
        ))}
      </Grid>
    );
  }
}

const MoaGrid = styled(Grid)`
  margin-bottom: 48px;
`;
