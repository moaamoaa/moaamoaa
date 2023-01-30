import * as React from 'react';
import Grid from '@mui/material/Grid';
import TeamMemberItem from 'components/team/TeamMemberItem';
import styled from '@emotion/styled';

export default function TeamMemberCard(props) {
  if (props.type === 'team') {
    return (
      <MoaGrid container spacing={10}>
        {props.cards.map(card => (
          <Grid item key={card.id} xs={12} md={6} lg={4}>
            <TeamMemberItem card={card} type={props.type}></TeamMemberItem>
          </Grid>
        ))}
      </MoaGrid>
    );
  } else if (props.type === 'member') {
    return (
      <MoaGrid container spacing={5}>
        {props.cards.map(card => (
          <Grid item key={card.id} xs={12} sm={6} md={4} lg={3}>
            <TeamMemberItem card={card} type={props.type}></TeamMemberItem>
          </Grid>
        ))}
      </MoaGrid>
    );
  } else if (props.type === 'tech') {
    return (
      <Grid container>
        {props.cards.map(card => (
          <Grid item key={card[1]} xs={3}>
            <TeamMemberItem card={card} type={props.type}></TeamMemberItem>
          </Grid>
        ))}
      </Grid>
    );
  } else if (props.type === 'link') {
    return (
      <Grid container>
        {Object.entries(props.cards).map(card => (
          <Grid item key={card[0]} xs={3}>
            <TeamMemberItem card={card} type={props.type}></TeamMemberItem>
          </Grid>
        ))}
      </Grid>
    );
  }
}

const MoaGrid = styled(Grid)`
  margin-bottom: 48px;
`;
