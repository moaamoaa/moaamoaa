import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { profileOpenSuccess, profileCloseSuccess } from 'redux/profile';

import styled from '@emotion/styled';

import customAxios from 'utils/axios';

import { Container, Grid } from '@mui/material/';

import Profile from 'components/profile/Profile';
import ProfileContentContainer from 'components/profile/ProfileContentContainer';
import SelfIntroduction from 'components/profile/SelfIntroduction';
import SideProject from 'components/profile/SideProject';
import CommentList from 'components/profile/CommontList';

export default function ProfilePage() {
  const curProfile = useSelector(state => state.profile);

  const dispatch = useDispatch();

  const handleCancel = () => {
    console.log('hi');
  };

  useEffect(() => {
    customAxios.basicAxios
      .get(`/profile/${curProfile.userProfile.userPk}`)
      .then(response => {
        const [areas, userProfile, reviews, sideProject, sites, techStacks] = [
          response.data.areas,
          response.data.profile,
          response.data.reviews,
          response.data.sideproject,
          response.data.sites,
          response.data.techstacks,
        ];

        dispatch(
          profileOpenSuccess({
            areas: areas,
            userProfile: userProfile,
            reviews: reviews,
            sideProject: sideProject,
            sites: sites,
            techStacks: techStacks,
          }),
        );
      })
      .catch(error => {
        console.log(error);
      });
  }, [curProfile.userProfile.userPk]);

  return (
    <ProfilePageContainer fixed>
      <Grid container spacing={10}>
        <Grid item xs={12} md={6} lg={4}>
          <Profile></Profile>
        </Grid>
        <Grid item xs={12} md={6} lg={8}>
          <ProfileContentContainer
            title={'자기 소개'}
            content={<SelfIntroduction></SelfIntroduction>}
          ></ProfileContentContainer>

          <ProfileContentContainer
            title={'주요 프로젝트'}
            content={<SideProject></SideProject>}
          ></ProfileContentContainer>

          <ProfileContentContainer title={'댓글'}>
            <CommentList
              comments={[
                { name: '임싸피', context: '안녕하세요', time: '2023-02-05' },
              ]}
            ></CommentList>
          </ProfileContentContainer>
        </Grid>
      </Grid>
    </ProfilePageContainer>
  );
}

const ProfilePageContainer = styled(Container)`
  position: relative;
  top: 4rem;
`;
