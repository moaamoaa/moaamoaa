import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@mui/material/Typography';
import styled from '@emotion/styled';
import LongMenu from './LongMenu';
import { Grid } from '@mui/material';

export default function SelfIntroduction() {
  const userProfile = useSelector(state => state.profile.userProfile);
  console.log(userProfile);
  return (
    <Grid container spacing={5}>
      <Grid item xs={10}>
        <Typography variant="body1" color="initial">
          {userProfile.context
            ? userProfile.context
            : `안녕하세요. ${userProfile.nickName}입니다.`}
        </Typography>
      </Grid>
      <Grid item xs={1}>
        <ProfileLongMenu></ProfileLongMenu>
      </Grid>
    </Grid>
  );
}

const ProfileLongMenu = styled(LongMenu)`
  position: absolute;
`;
