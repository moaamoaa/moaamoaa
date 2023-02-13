import * as React from 'react';
import List from '@mui/material/List';
import ProfileItem from 'components/profile/ProfileItem';

export default function ProfileList(props) {
  if (props.type === 'offer') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 받은 제안 */}
        {props.offers &&
          props.offers.map((offer, idx) => (
            <span key={idx}>
              <ProfileItem offerapply={offer} type={props.type}></ProfileItem>
            </span>
          ))}
      </List>
    );
  } else if (props.type === 'apply') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 보낸 지원 */}
        {props.applies &&
          props.applies.map((apply, idx) => (
            <span key={idx}>
              <ProfileItem offerapply={apply} type={props.type}></ProfileItem>
            </span>
          ))}
      </List>
    );
  }
}
