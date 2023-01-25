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
          <Grid item key={card.id} xs={12} md={6} lg={3}>
            <CardItem card={card} type={props.type}></CardItem>
          </Grid>
        ))}
      </MoaGrid>
    );
  }
}

const MoaGrid = styled(Grid)`
  margin-bottom: 48px;
`;
