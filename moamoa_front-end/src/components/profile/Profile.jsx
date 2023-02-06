import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from '@emotion/styled';

import CardList from 'components/common/card/CardList';
import {
  Button,
  Typography,
  Skeleton,
  Container,
  Box,
  Grid,
  TextField,
} from '@mui/material';

import ScrollableTab from 'components/common/tab/ScrollableTab';

export default function Profile() {
  const sites = useSelector(state => state.profile.sites);
  const techStacks = useSelector(state => state.profile.techStacks);
  const userProfile = useSelector(state => state.profile.userProfile);
  const userPk = useSelector(state => state.user.userPk);

  const [type, setType] = useState('normal');

  const dispatch = useDispatch();

  const handleOpenEditPage = () => {};

  const handleOpenOfferList = () => {};

  const handleEditSuccess = () => {
    setType('normal');
  };

  const handleCloseEditPage = () => {
    setType('normal');
  };

  const userButtons = [
    <ProfileButton
      key="offer"
      href="/ProfileEditPage"
      onClick={handleOpenEditPage}
      variant="outlined"
    >
      수정
    </ProfileButton>,
    <ProfileButton key="chat" onClick={handleOpenOfferList} variant="outlined">
      신청 목록
    </ProfileButton>,
  ];

  const otherButtons = [
    <ProfileButton key="offer" variant="outlined">
      제안
    </ProfileButton>,
    <ProfileButton key="chat" variant="outlined">
      채팅
    </ProfileButton>,
  ];

  const editButtons = [
    <ProfileButton
      key="offer"
      href="/ProfilePage"
      onClick={handleEditSuccess}
      variant="outlined"
    >
      수정 완료
    </ProfileButton>,
    <ProfileButton
      key="chat"
      href="/ProfilePage"
      onClick={handleCloseEditPage}
      variant="outlined"
    >
      수정 취소
    </ProfileButton>,
  ];

  if (type === 'edit') {
    return (
      <>
        {/* 반응형 md 이상 */}
        <MoaProfile
          component="article"
          sx={{
            display: { xs: 'none', md: 'block' },
            position: 'fixed',
            width: '320px',
            padding: '0 !important',
          }}
        >
          <MoaSkeleton
            variant="circular"
            sx={{ width: '320px', height: '320px' }}
          />

          <TextField
            fullWidth
            placeholder={userProfile.nickName}
            onChange={null}
          />

          <ProfileButtonContainer>{editButtons}</ProfileButtonContainer>
        </MoaProfile>

        {/* 반응형 md 미만 */}
        <MoaProfile
          component="article"
          sx={{ display: { xs: 'block', md: 'none' } }}
        >
          <Grid container spacing={10} sx={{ alignItems: 'center' }}>
            <Grid item xs={4}>
              <MoaSkeleton
                variant="circular"
                sx={{ width: '10rem', height: '10rem' }}
              />
            </Grid>
            <Grid item xs={8}>
              <TextField
                fullWidth
                placeholder={userProfile?.nickName}
                onChange={null}
              />
            </Grid>
          </Grid>

          <ProfileButtonContainer>{editButtons}</ProfileButtonContainer>
        </MoaProfile>
      </>
    );
  } else {
    return (
      <>
        {/* 반응형 md 이상 */}
        <MoaProfile
          component="article"
          sx={{
            display: { xs: 'none', md: 'block' },
            position: 'fixed',
            width: '320px',
            padding: '0 !important',
          }}
        >
          <MoaSkeleton
            variant="circular"
            sx={{ width: '320px', height: '320px' }}
          />
          <Typography variant="h4" color="initial" fontWeight={900}>
            {userProfile.nickName}
          </Typography>

          <CardList type={'tech'} cards={techStacks}></CardList>
          <hr />
          <CardList type={'link'} cards={sites}></CardList>

          <ProfileButtonContainer>
            {userProfile.userPk === userPk ? userButtons : otherButtons}
          </ProfileButtonContainer>
        </MoaProfile>

        {/* 반응형 md 미만 */}
        <MoaProfile
          component="article"
          sx={{ display: { xs: 'block', md: 'none' } }}
        >
          <Grid container spacing={10}>
            <Grid item xs={4}>
              <MoaSkeleton
                variant="circular"
                sx={{ width: '10rem', height: '10rem' }}
              />
            </Grid>
            <Grid item xs={8}>
              <Typography variant="h5" color="initial" fontWeight={600}>
                {userProfile.nickName}
              </Typography>

              <ScrollableTab type={'tech'} cards={techStacks}></ScrollableTab>
              {/* <Carousel type={'tech'} cards={tech}></Carousel> */}
              <hr />
              <CardList type={'link'} cards={sites}></CardList>
            </Grid>
          </Grid>

          <ProfileButtonContainer>
            {userPk === userProfile.userPk ? userButtons : otherButtons}
          </ProfileButtonContainer>
        </MoaProfile>
      </>
    );
  }
}

const MoaProfile = styled(Container)`
  padding: 0 !important;
`;

const MoaSkeleton = styled(Skeleton)`
  margin: 0 auto;
  max-width: 320px;
  max-height: 320px;
`;

const ProfileButtonContainer = styled(Box)`
  width: 100%;
`;

const ProfileButton = styled(Button)`
  width: 100%;
  margin-top: 1rem;
`;
