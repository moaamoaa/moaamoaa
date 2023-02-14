import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from '@emotion/styled';

import CardList from 'components/common/card/CardList';
import MyProjectStudy from 'components/team/MyProjectStudy';
import {
  Button,
  Typography,
  Container,
  Box,
  Grid,
  TextField,
  Badge,
  Tooltip,
  Fade,
  Divider,
  Avatar,
} from '@mui/material';

import { searchStatusChange } from 'redux/profile';
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
  const updateProfile = useSelector(state => state.profile.userProfile[1]);
  const [image, setImage] = useState(userProfile.img);

  const navigate = useNavigate();

  const dispatch = useDispatch();

  const handleOpenEditPage = () => {
    navigate('/ProfileEditPage');
    scrollToTop();
  };

  const handleOpenOfferList = () => {};

  const handleEditSuccess = () => {
    const formData = new FormData();

    formData.append('file', image);

    console.log(updateProfile);
    const value = {
      areas: updateProfile.areas,
      nickname: updateProfile.nickname,
      sites: updateProfile.sites,
      techstacks: updateProfile.techStacks,
    };
    const blob = new Blob([JSON.stringify(value)], {
      type: 'application/json',
    });
    formData.append('projectForm', blob);

    customAxios.imageAxios
      .post('/profile', {
        data: formData,
      })
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
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

  const handleDrop = event => {
    event.preventDefault();
    const files = event.dataTransfer.files;
    console.log(files);
    if (files && files.length > 0) {
      const file = files[0];
      if (file.type.startsWith('image/')) {
        const reader = new FileReader();
        reader.onload = event => {
          setImage(event.target.result);
        };
        reader.readAsDataURL(file);
      }
    }
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
      <MyProjectStudy isMobile={isMobile}></MyProjectStudy>
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
          <Grid container display={'flex'}>
            <Grid item>
              <Avatar
                onDrop={handleDrop}
                onDragOver={event => event.preventDefault()}
                src={image}
                variant="circular"
                sx={{
                  width: { md: '280px', xl: '320px' },
                  height: { md: '280px', xl: '320px' },
                  boxShadow: '0px 0px 10px 4px #888888',
                  opacity: 0.5,
                  backgroundColor: 'rgba(0, 0, 0, 1)',
                }}
              />
            </Grid>
            <Grid
              item
              container
              sx={{
                position: 'absolute',
                display: 'flex',
                fontWeight: '600',
                top: { md: '33%', xl: '35%' },
                left: '50%',
                transform: 'translate(-50%,-50%)',
                justifyContent: 'center',
                alignItems: 'center',
                textShadow: '0 0 3px #fff',
              }}
            >
              <Grid
                item
                xs={12}
                sx={{
                  display: 'flex',
                  justifyContent: 'center',
                  alignItems: 'center',
                }}
              >
                <Typography variant="h6" color="initial">
                  드래그 드랍으로
                </Typography>
              </Grid>

              <Grid
                item
                xs={12}
                sx={{
                  display: 'flex',
                  justifyContent: 'center',
                  alignItems: 'center',
                }}
              >
                <Typography variant="h6" color="initial">
                  이미지를 수정해 보세요.
                </Typography>
              </Grid>
            </Grid>
          </Grid>

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
              <Avatar
                src={image}
                variant="circular"
                sx={{
                  width: isMobile ? '100px' : '160px',
                  height: isMobile ? '100px' : '160px',
                  boxShadow: '0px 0px 10px 0px #888888',
                  opacity: 0.5,
                  backgroundColor: 'rgba(0, 0, 0, 1)',
                }}
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
                  boxShadow:
                    '0px 0px 10px 0px #88888888, 0px 2px 10px 0px rgba(100,0,0,0.5), 0px -2px 5px 0px rgba(0,100,0,0.5)',
                },
              }}
            >
              <Avatar
                src={userProfile.img}
                variant="circular"
                sx={{
                  width: { md: '280px', xl: '320px' },
                  height: { md: '280px', xl: '320px' },
                  boxShadow:
                    '0px 0px 10px 0px #88888888, 0px 2px 10px 0px rgba(100,0,0,0.5), 0px -2px 5px 0px rgba(0,100,0,0.5)',
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
          <Grid container rowSpacing={2}>
            <Grid item xs={12}>
              <Typography
                variant={isMobile ? 'body1' : 'h6'}
                color="initial"
                fontWeight={600}
              >
                {userProfile.nickname}
              </Typography>
            </Grid>
            <Grid container item xs={12} display="flex" alignItems={'center'}>
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
                        width: isMobile ? '1rem' : '2rem',
                        minHeight: isMobile ? '1rem' : '2rem',
                        borderRadius: '50%',
                      },
                    }}
                  >
                    <Avatar
                      src={userProfile.img}
                      variant="circular"
                      sx={{
                        width: isMobile ? '5rem' : '8rem',
                        height: isMobile ? '5rem' : '8rem',
                        boxShadow:
                          '0px 0px 10px 0px #88888888, 0px 2px 10px 0px rgba(100,0,0,0.5), 0px -2px 5px 0px rgba(0,100,0,0.5)',
                      }}
                    />
                  </Badge>
                </Tooltip>
              </Grid>
              <Grid item xs={8}>
                <CardList type={'tech'} cards={techStacks}></CardList>
                <Divider sx={{ marginY: 2 }} />
                <CardList type={'link'} cards={sites}></CardList>
              </Grid>
            </Grid>

            <Grid item xs={12}>
              <ProfileButtonContainer>
                {userPk === userProfile.id ? userButtons : otherButtons}
              </ProfileButtonContainer>
            </Grid>
          </Grid>
        </MoaProfile>
      </>
    );
  }
}

const MoaProfile = styled(Container)`
  padding: 0 !important;
`;

const ProfileButtonContainer = styled(Box)`
  width: 100%;
  margin-bottom: 3rem;
`;

const ProfileButton = styled(Button)`
  width: 100%;
  margin-top: 1rem;
`;
