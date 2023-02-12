import * as React from 'react';

import Grid from '@mui/material/Grid';

import CardItem from 'components/common/card/CardItem';
import styled from '@emotion/styled';

export default function CardList(props) {
  if (props.type === 'team') {
    return (
      <MoaGrid container spacing={10}>
        {props.cards &&
          props.cards.map((card, idx) => (
            <Grid item key={idx} xs={12} md={6} lg={4}>
              <CardItem card={card} type={props.type}></CardItem>
            </Grid>
          ))}
      </MoaGrid>
    );
  } else if (props.type === 'member') {
    return (
      <MoaGrid container spacing={5}>
        {props.cards &&
          props.cards.map((card, idx) => (
            <Grid item key={idx} xs={12} sm={6} md={4} lg={3}>
              <CardItem card={card} type={props.type}></CardItem>
            </Grid>
          ))}
      </MoaGrid>
    );
  } else if (props.type === 'tech') {
    return (
      <Grid
        container
        overflow="scroll"
        sx={{ height: '50px', alignItems: 'center' }}
      >
        {props.cards ? (
          props.cards &&
          props.cards.map((card, idx) => (
            <Grid item key={idx} mr={2}>
              <CardItem card={card} type={props.type}></CardItem>
            </Grid>
          ))
        ) : (
          <>
            {[0, 1, 2, 3].map(idx => (
              <Grid
                key={idx}
                item
                xs={3}
                sx={{ display: 'flex', justifyContent: 'center' }}
              >
                <CardItem card={{ logo: null }} type={props.type}></CardItem>
              </Grid>
            ))}
          </>
        )}
      </Grid>
    );
  } else if (props.type === 'link') {
    return (
      <Grid container sx={{ height: '50px', alignItems: 'center' }}>
        {props.cards ? (
          props.cards &&
          Object.entries(props.cards).map((card, idx) => (
            <Grid item key={idx} xs={3}>
              <CardItem card={card} type={props.type}></CardItem>
            </Grid>
          ))
        ) : (
          <Grid container sx={{ height: '50px', alignItems: 'center' }}>
            {[0, 1, 2, 3].map(idx => (
              <Grid
                key={idx}
                item
                xs={3}
                sx={{ display: 'flex', justifyContent: 'center' }}
              >
                <CardItem card={{ site: null }} type={props.type}></CardItem>
              </Grid>
            ))}
          </Grid>
        )}
      </Grid>
    );
  }
}

const MoaGrid = styled(Grid)`
  margin-bottom: 48px;
`;
