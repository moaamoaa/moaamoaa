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

export default function ProfilePage(props) {
  const userPK = useSelector(state => state.user.userPK);
  const userProfile = useSelector(state => state.profile.userProfile);
  const sideProject = useSelector(state => state.profile.sideProject);
  const review = useSelector(state => state.profile.review);

  const dispatch = useDispatch();
  useEffect(() => {
    // console.log(userProfile);
    // console.log(sideProject);
    // console.log(review);
    customAxios.basicAxios
      .get(`/profile/nickName?nickName=${userPK}`)
      .then(response => {
        console.log(response);

        dispatch(profileOpenSuccess({ userPk: userProfile.userPk }));
      })
      .catch(error => {
        console.log(error);
      });
  }, [userProfile.userPk]);

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
      content: <SideProject></SideProject>,
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
