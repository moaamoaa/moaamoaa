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
  Badge,
  Tooltip,
  Fade,
} from '@mui/material';

import { searchStatusChange, handleEditProfile } from 'redux/profile';
import { useNavigate } from 'react-router-dom';
import scrollToTop from 'utils/scrollToTop';
import customAxios from 'utils/axios';
import useMobile from 'hooks/useMobile';

export default function Profile(props) {
  const isMobile = useMobile();
  const [badgeInfo, setBadgeInfo] = useState({
    color: 'primary',
    context: '온라인 오프라인 팀을 구하고 있습니다.',
  });

  const userPk = useSelector(state => state.user.userPk);

  const sites = useSelector(state => state.profile.sites);
  const techStacks = useSelector(state => state.profile.techStacks);
  const userProfile = useSelector(state => state.profile.userProfile[0]);

  const navigate = useNavigate();

  const dispatch = useDispatch();

  const handleOpenEditPage = () => {
    navigate('/ProfileEditPage');
    scrollToTop();
  };

  const handleOpenOfferList = () => {};

  const handleEditSuccess = () => {
    customAxios.authAxios.post('/profile', {});
    dispatch(handleEditProfile({}));
  };

  const handleCloseEditPage = () => {
    navigate('/ProfilePage');
    scrollToTop();
  };

  const handleBadge = () => {
    if (userPk !== userProfile.id) return;

    if (badgeInfo.color === 'primary') {
      setBadgeInfo({
        color: 'secondary',
        context: '온라인 팀을 구하고 있습니다.',
      });
    } else if (badgeInfo.color === 'secondary') {
      setBadgeInfo({
        color: 'success',
        context: '오프라인 팀을 구하고 있습니다.',
      });
    } else if (badgeInfo.color === 'success') {
      setBadgeInfo({
        color: 'warning',
        context: '팀을 구하고 있지 않습니다.',
      });
    } else if (badgeInfo.color === 'warning') {
      setBadgeInfo({
        color: 'primary',
        context: '온라인 오프라인 팀을 구하고 있습니다.',
      });
    }
    dispatch(
      searchStatusChange({
        profileSearchStatus: userProfile.profileSearchStatus,
      }),
    );
  };

  const userButtons = [
    <ProfileButton key="offer" onClick={handleOpenEditPage} variant="outlined">
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
    <ProfileButton key="offer" onClick={handleEditSuccess} variant="outlined">
      수정 완료
    </ProfileButton>,
    <ProfileButton key="chat" onClick={handleCloseEditPage} variant="outlined">
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
            width: { md: '280px', xl: '320px' },
            padding: '0 !important',
          }}
        >
          <MoaSkeleton
            variant="circular"
            sx={{
              width: { md: '280px', xl: '320px' },
              height: { md: '280px', xl: '320px' },
            }}
          />

          <TextField
            fullWidth
            placeholder={userProfile.nickname}
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
                placeholder={userProfile?.nickname}
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
            width: { md: '280px', xl: '320px' },
            padding: '0 !important',
          }}
        >
          <Tooltip
            title={badgeInfo.context}
            TransitionComponent={Fade}
            TransitionProps={{ timeout: 600 }}
            placement="top-end"
            followCursor
            onClick={handleBadge}
          >
            <Badge
              badgeContent=" "
              color={badgeInfo.color}
              overlap="circular"
              sx={{
                '& .MuiBadge-badge': {
                  fontSize: 9,
                  height: 50,
                  minWidth: 50,
                  borderRadius: 50,
                },
              }}
            >
              <MoaSkeleton
                variant="circular"
                sx={{
                  width: { md: '280px', xl: '320px' },
                  height: { md: '280px', xl: '320px' },
                }}
              />
            </Badge>
          </Tooltip>

          <Typography variant="h4" color="initial" fontWeight={900} margin={2}>
            {userProfile.nickname}
          </Typography>

          <CardList type={'tech'} cards={techStacks}></CardList>
          <hr />
          <CardList type={'link'} cards={sites}></CardList>

          <ProfileButtonContainer>
            {userProfile.id === userPk ? userButtons : otherButtons}
          </ProfileButtonContainer>
        </MoaProfile>

        {/* 반응형 md 미만 */}
        <MoaProfile
          component="article"
          sx={{ display: { xs: 'block', md: 'none' } }}
        >
          <Grid container>
            <Grid item xs={4}>
              <Tooltip
                title={badgeInfo.context}
                TransitionComponent={Fade}
                TransitionProps={{ timeout: 600 }}
                onClick={handleBadge}
                followCursor
              >
                <Badge
                  badgeContent=" "
                  color={badgeInfo.color}
                  overlap="circular"
                  sx={{
                    '& .MuiBadge-badge': {
                      fontSize: 9,
                      height: '30px',
                      minWidth: '30px',
                      borderRadius: 5,
                    },
                  }}
                >
                  <MoaSkeleton
                    variant="circular"
                    sx={{
                      width: isMobile ? '100px' : '160px',
                      height: isMobile ? '100px' : '160px',
                    }}
                  />
                </Badge>
              </Tooltip>
              <Typography
                variant={isMobile ? 'body1' : 'h6'}
                color="initial"
                fontWeight={600}
              >
                {userProfile.nickname}
              </Typography>
            </Grid>
            <Grid item xs={8}>
              <CardList type={'tech'} cards={techStacks}></CardList>
              <hr />
              <CardList type={'link'} cards={sites}></CardList>
            </Grid>
          </Grid>

          <ProfileButtonContainer>
            {userPk === userProfile.id ? userButtons : otherButtons}
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
