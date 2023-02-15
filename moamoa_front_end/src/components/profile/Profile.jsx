import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from '@emotion/styled';

import CardList from 'components/common/card/CardList';
import MyProjectStudy from 'components/team/MyProjectStudy';
import {
  ButtonBase,
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
  IconButton,
} from '@mui/material';

import { searchStatusChange } from 'redux/profile';
import { useNavigate } from 'react-router-dom';
import scrollToTop from 'utils/scrollToTop';
import customAxios from 'utils/axios';
import useMobile from 'hooks/useMobile';
import ProfileApplyOffer from './ProfileApplyOffer';
import { PhotoCamera } from '@mui/icons-material';
import ClearRoundedIcon from '@mui/icons-material/ClearRounded';

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
  const [previewImage, setPreviewImage] = useState(userProfile.img);
  const [image, setImage] = useState('');
  const [updateNickname, setUpdateNickname] = useState('');

  const navigate = useNavigate();

  const dispatch = useDispatch();

  const handleOpenEditPage = () => {
    navigate('/ProfileEditPage');
    scrollToTop();
  };

  const handleOpenOfferList = () => {};

  const handleNickname = event => {
    setUpdateNickname(event.target.value);
  };

  const handleEditSuccess = () => {
    const formData = new FormData();

    if (previewImage !== userProfile.img) {
      formData.append('file', image);
    } else {
      formData.append('file', null);
    }

    const updateUserProfile = {
      userId: userProfile.id,
      nickname: updateNickname,
      profileOnOffStatus: updateProfile.profileOnOffStatus,
      context: userProfile.context,
    };

    const value = {
      profile: updateUserProfile,
      techstacks: updateProfile.techStacks,
      sites: updateProfile.sites,
      areas: updateProfile.areas,
    };

    console.log(value);

    const blob = new Blob([JSON.stringify(value)], {
      type: 'application/json',
    });

    formData.append('profilePageForm', blob);

    console.log(formData.get('profilePageForm'), '프로젝트폼');
    console.log(formData.get('file'), '파일');

    customAxios.imageAxios
      .post('/profile', formData)
      .then(response => {
        console.log(response.data);
        navigate('/profilepage');
        scrollToTop();
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
    setPreviewImage(URL.createObjectURL(files[0]));
    setImage(files[0]);
  };

  const handleChange = event => {
    const files = event.target.files;
    console.log(files[0]);
    // 미리보기용
    setPreviewImage(URL.createObjectURL(files[0]));
    // axios용
    setImage(files[0]);
  };

  const userButtons = [
    <ProfileButton key="offer" onClick={handleOpenEditPage} variant="outlined">
      수정
    </ProfileButton>,
    <ProfileButton key="chat" onClick={handleOpenOfferList} variant="outlined">
      {/* 개인이 받은 지원 및 제안 확인 */}
      <ProfileApplyOffer isMobile={isMobile}></ProfileApplyOffer>
    </ProfileButton>,
  ];

  const otherButtons = [
    <ProfileButton key="offer" variant="outlined">
      {/* 타인에게 제안 보내기 버튼 */}
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
          <Grid
            container
            display={'flex'}
            onDrop={handleDrop}
            onDragOver={event => event.preventDefault()}
          >
            <Grid item>
              <Avatar
                src={previewImage}
                variant="circular"
                sx={{
                  width: { md: '280px', xl: '320px' },
                  height: { md: '280px', xl: '320px' },
                  boxShadow: '0px 0px 10px 4px #888888',
                  opacity: 0.5,
                }}
              />
            </Grid>
            <Grid
              item
              container
              sx={{
                position: 'absolute',
                top: { md: '140px', xl: '160px' },
                left: '50%',
                display: 'flex',
                fontWeight: '600',
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

          <Grid
            container
            display={'flex'}
            alignItems={'center'}
            height={'5rem'}
          >
            <Grid item xs={1}>
              <input
                accept="image/*"
                type="file" // 파일
                id="select-image"
                style={{ display: 'none' }}
                onChange={handleChange}
                multiple="multiple"
              />
              <label htmlFor="select-image">
                <IconButton
                  color="primary"
                  aria-label="upload picture"
                  component="span"
                >
                  <input hidden accept="image/*" type="file" />
                  <PhotoCamera fontSize="small" />
                </IconButton>
              </label>
            </Grid>
            <Grid item xs={5}>
              <Typography variant="body1" color="initial" textAlign={'center'}>
                이미지 변경
              </Typography>
            </Grid>
            <Grid item xs={1}>
              <IconButton
                color="primary"
                aria-label="upload picture"
                component="span"
              >
                <ClearRoundedIcon />
              </IconButton>
            </Grid>
            <Grid item xs={5}>
              <Typography variant="body1" color="initial" textAlign={'center'}>
                변경 취소
              </Typography>
            </Grid>
          </Grid>

          <TextField
            fullWidth
            placeholder={userProfile.nickname}
            value={updateNickname}
            onChange={handleNickname}
          />

          <ProfileButtonContainer>{editButtons}</ProfileButtonContainer>
        </MoaProfile>

        {/* 반응형 md 미만 */}
        <MoaProfile
          component="article"
          sx={{
            display: { xs: 'block', md: 'none' },
          }}
        >
          <Grid container spacing={10} sx={{ alignItems: 'center' }}>
            <Grid item xs={4}>
              <Avatar
                src={previewImage}
                variant="circular"
                sx={{
                  width: isMobile ? '100px' : '160px',
                  height: isMobile ? '100px' : '160px',
                  boxShadow: '0px 0px 10px 0px #888888',
                  opacity: 0.5,
                }}
              />
            </Grid>

            <Grid item container xs={8}>
              <Grid
                container
                item
                xs={12}
                sx={{ display: 'flex', alignItems: 'center' }}
              >
                <Grid item xs={2}>
                  <input
                    accept="image/*"
                    type="file" // 파일
                    id="select-image"
                    style={{ display: 'none' }}
                    onChange={handleChange}
                    multiple="multiple"
                  />
                  <label htmlFor="select-image">
                    <IconButton
                      color="primary"
                      aria-label="upload picture"
                      component="span"
                    >
                      <input hidden accept="image/*" type="file" />
                      <PhotoCamera fontSize="small" />
                    </IconButton>
                  </label>
                </Grid>
                <Grid item xs={10}>
                  <Typography
                    variant="caption"
                    color="initial"
                    textAlign={'center'}
                  >
                    이미지 변경
                  </Typography>
                </Grid>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  placeholder={userProfile.nickname}
                  onChange={handleNickname}
                />
              </Grid>
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

          <Typography variant="h4" color="initial" fontWeight={900} marginY={1}>
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
