import React from 'react';

import styled from '@emotion/styled';

import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

import Profile from 'components/profile/Profile';
import ProfileEditContent from 'components/profile/ProfileEditContent';

export default function ProfilePage() {
  return (
    <ProfilePageContainer fixed>
      <Grid container spacing={10}>
        <Grid item xs={12} md={5} lg={4}>
          <Profile type="edit"></Profile>
        </Grid>
        <Grid item xs={12} md={7} lg={8}>
          <ProfileEditContent></ProfileEditContent>
        </Grid>
      </Grid>
    </ProfilePageContainer>
  );
}

const ProfilePageContainer = styled(Container)`
  position: relative;
  top: 4rem;
`;
