import React from 'react';

import styled from '@emotion/styled';

import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

import Profile from 'components/profile/Profile';
import ProfileContentContainer from 'components/profile/ProfileContentContainer';
import SelfIntroduction from 'components/profile/SelfIntroduction';
import SideProject from 'components/profile/SideProject';
import { useEffect } from 'react';

const userProfileContents = [
  {
    title: '자기 소개',
    content: <SelfIntroduction></SelfIntroduction>,
  },
  {
    title: '주요 프로젝트',
    content: <SideProject></SideProject>,
  },
  {
    title: '댓글',
    content: '댓글입니다.',
  },
];

export default function ProfilePage(props) {
  useEffect(() => {}, []);

  return (
    <ProfilePageContainer fixed>
      <Grid container spacing={10}>
        <Grid item xs={12} md={6} lg={4}>
          <Profile></Profile>
        </Grid>
        <Grid item xs={12} md={6} lg={8}>
          {userProfileContents.map((userProfileContent, idx) => (
            <ProfileContentContainer
              key={idx}
              title={userProfileContent.title}
              content={userProfileContent.content}
            ></ProfileContentContainer>
          ))}
        </Grid>
      </Grid>
    </ProfilePageContainer>
  );
}

const ProfilePageContainer = styled(Container)`
  position: relative;
  top: 4rem;
`;
