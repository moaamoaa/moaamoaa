import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { profileOpenSuccess } from 'redux/profile';

import styled from '@emotion/styled';

import customAxios from 'utils/axios';

import { Container, Grid } from '@mui/material/';

import Profile from 'components/profile/Profile';
import SelfIntroduction from 'components/profile/SelfIntroduction';
import SideProjectContainer from 'components/profile/SideProjectContainer';
import ReviewList from 'components/profile/ReviewList';

export default function ProfilePage() {
  const profileId = useSelector(state => state.profile.userProfile[0].id);
  const dispatch = useDispatch();

  useEffect(() => {
    customAxios.authAxios
      .get(`/profile/${profileId}`, { withCredentials: true })
      .then(response => {
        dispatch(profileOpenSuccess({ profile: response.data }));
      })
      .catch(error => {
        console.log(error);
      });
  }, [profileId]);

  return (
    <ProfilePageContainer fixed>
      <Grid container sx={{ paddingY: '0px !important' }}>
        <Grid item xs={12} md={5} lg={4}>
          <Profile type="normal"></Profile>
        </Grid>
        <Grid item xs={12} md={7} lg={8}>
          <SelfIntroduction></SelfIntroduction>

          <SideProjectContainer></SideProjectContainer>

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
