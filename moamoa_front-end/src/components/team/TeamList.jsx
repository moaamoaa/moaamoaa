import * as React from 'react';
import List from '@mui/material/List';
import TeamItem from 'components/team/TeamItem';

export default function TeamList(props) {
  if (props.type === 'offer') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 보낸 제안 */}
        {props.offers &&
          props.offers.map((offer, idx) => (
            <span key={idx}>
              <TeamItem offerapply={offer} type={props.type}></TeamItem>
            </span>
          ))}
      </List>
    );
  } else if (props.type === 'apply') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 받은 지원 */}
        {props.applies &&
          props.applies.map((apply, idx) => (
            <span key={idx}>
              <TeamItem offerapply={apply} type={props.type}></TeamItem>
            </span>
          ))}
      </List>
    );
  }
}
