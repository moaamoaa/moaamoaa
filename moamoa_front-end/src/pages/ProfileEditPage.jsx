import React from 'react';

import styled from '@emotion/styled';

import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

import Profile from 'components/profile/Profile';
import ProfileContentContainer from 'components/profile/ProfileContentContainer';
import ProfileEditContent from 'components/profile/ProfileEditContent';

export default function ProfilePage(props) {
  return (
    <ProfilePageContainer fixed>
      <Grid container spacing={10}>
        <Grid item xs={12} md={6} lg={4}>
          <Profile type="edit"></Profile>
        </Grid>
        <Grid item xs={12} md={6} lg={8}>
          <ProfileContentContainer
            title="기본 프로필 수정"
            content={<ProfileEditContent></ProfileEditContent>}
          ></ProfileContentContainer>
        </Grid>
      </Grid>
    </ProfilePageContainer>
  );
}

const ProfilePageContainer = styled(Container)`
  position: relative;
  top: 4rem;
`;
