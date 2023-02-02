import React from 'react';
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

export default function Profile(props) {
  const user = {
    id: 0,
    name: '김싸피',
    tech: [
      ['front-end_icons', 'javascript'],
      ['front-end_icons', 'typescript'],
    ],
    link: {
      github: 'https://github.com/LimSB-dev',
      tistory: '',
      velog: '',
    },
  };

  const userButtons = [
    <ProfileButton key="offer" href="/ProfileEditPage" variant="outlined">
      수정
    </ProfileButton>,
    <ProfileButton key="chat" variant="outlined">
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
    <ProfileButton key="offer" variant="outlined">
      수정 완료
    </ProfileButton>,
    <ProfileButton key="chat" variant="outlined">
      수정 취소
    </ProfileButton>,
  ];

  if (props.type === 'edit') {
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

          <TextField fullWidth placeholder={user.name} onChange={null} />

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
              <TextField fullWidth placeholder={user.name} onChange={null} />
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
            {user.name}
          </Typography>

          <CardList type={'tech'} cards={user.tech}></CardList>
          <hr />
          <CardList type={'link'} cards={user.link}></CardList>

          <ProfileButtonContainer>
            {user.id ? otherButtons : userButtons}
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
              <Typography variant="h4" color="initial" fontWeight={600}>
                {user.name}
              </Typography>

              <CardList type={'tech'} cards={user.tech}></CardList>
              <hr />
              <CardList type={'link'} cards={user.link}></CardList>
            </Grid>
          </Grid>

          <ProfileButtonContainer>
            {user.id ? otherButtons : userButtons}
          </ProfileButtonContainer>
        </MoaProfile>
      </>
    );
  }
}

const MoaProfile = styled(Container)`
  padding: 0;
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
