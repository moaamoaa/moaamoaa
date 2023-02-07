import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { profileOpenSuccess, profileCloseSuccess } from 'redux/profile';

import styled from '@emotion/styled';

import customAxios from 'utils/axios';

import { Container, Grid } from '@mui/material/';

import Profile from 'components/profile/Profile';
import SelfIntroduction from 'components/profile/SelfIntroduction';
import SideProject from 'components/profile/SideProject';
import ReviewList from 'components/profile/ReviewList';

export default function ProfilePage() {
  const profileId = useSelector(state => state.profile.userProfile[0].id);
  const dispatch = useDispatch();

  useEffect(() => {
    customAxios.basicAxios
      .get(`/profile/${profileId}`)
      .then(response => {
        dispatch(profileOpenSuccess({ profile: response.data }));
      })
      .catch(error => {
        console.log(error);
      });
  }, [profileId]);

  return (
    <ProfilePageContainer fixed>
      <Grid container spacing={10}>
        <Grid item xs={12} md={6} lg={4}>
          <Profile></Profile>
        </Grid>
        <Grid item xs={12} md={6} lg={8}>
          <SelfIntroduction></SelfIntroduction>

          <SideProject></SideProject>

          <ReviewList></ReviewList>
        </Grid>
      </Grid>
    </ProfilePageContainer>
  );
}

const ProfilePageContainer = styled(Container)`
  position: relative;
  top: 4rem;
`;
