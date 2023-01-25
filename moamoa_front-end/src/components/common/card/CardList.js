import * as React from 'react';

import Grid from '@mui/material/Grid';

import CardItem from 'components/common/card/CardItem';

export default function CardList(props) {
  if (props.type === 'team') {
    return (
      <Grid container spacing={10}>
        {props.cards.map(card => (
          <Grid item key={card.id} xs={12} sm={6} md={4}>
            <CardItem card={card} type={props.type}></CardItem>
          </Grid>
        ))}
      </Grid>
    );
  } else if (props.type === 'member') {
    return (
      <Grid container spacing={5}>
        {props.cards.map(card => (
          <Grid item key={card.id} xs={3}>
            <CardItem card={card} type={props.type}></CardItem>
          </Grid>
        ))}
      </Grid>
    );
  }
}
