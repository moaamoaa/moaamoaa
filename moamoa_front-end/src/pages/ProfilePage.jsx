import { useEffect } from 'react';
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
  const userPk = useSelector(state => state.user.userPk);
  const curProfile = useSelector(state => state.profile);

  const defaultSideProject = {
    year: new Date().getFullYear(),
    title: '프로젝트명',
    techStacks: ['HTML', 'CSS', 'JavaScript'],
    context: '프로젝트소개',
  };
  const dispatch = useDispatch();

  const handleClick = () => {
    console.log('hi');
  };

  const userProfileContents = [
    {
      type: 'read',
      title: '자기 소개',
      content: <SelfIntroduction></SelfIntroduction>,
      handler: [handleClick, handleClick],
    },
    {
      type: 'read',
      title: '주요 프로젝트',
      content: curProfile.sideProject ? (
        curProfile.sideProject.map(project => {
          <SideProject props={project}></SideProject>;
        })
      ) : (
        <SideProject props={defaultSideProject}></SideProject>
      ),
      handler: [handleClick, handleClick],
    },
    {
      title: '댓글',
      content: (
        <CommentList
          comments={[
            { name: '임싸피', context: '안녕하세요', time: '2023-02-05' },
          ]}
        />
      ),
      handler: null,
    },
  ];

  useEffect(() => {
    customAxios.basicAxios
      .get(`/profile/${userPk}`)
      .then(response => {
        const [areas, profile, reviews, sideProject, sites, techStacks] = [
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
            userProfile: profile,
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
          {userProfileContents.map((userProfileContent, idx) => (
            <ProfileContentContainer
              key={idx}
              type={userProfileContent.type}
              title={userProfileContent.title}
              content={userProfileContent.content}
              handler={userProfileContent.handler}
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
