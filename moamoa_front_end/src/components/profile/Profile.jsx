import React, { useEffect, useState } from 'react';
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
  IconButton,
} from '@mui/material';

import { useNavigate } from 'react-router-dom';
import scrollToTop from 'utils/scrollToTop';
import customAxios from 'utils/axios';
import useMobile from 'hooks/useMobile';
import ProfileApplyOffer from './ProfileApplyOffer';
import { PhotoCamera } from '@mui/icons-material';
import { handleSuccessState } from 'redux/snack';
import ChattingRoom from './chatting/ChattingRoom';

export default function Profile(props) {
  const isMobile = useMobile();

  const userPk = useSelector(state => state.user.userPk);

  const sites = useSelector(state => state.profile.sites);
  const techStacks = useSelector(state => state.profile.techStacks);
  const userProfile = useSelector(state => state.profile.userProfile[0]);
  const updateProfile = useSelector(state => state.profile.userProfile[1]);
  const [previewImage, setPreviewImage] = useState(userProfile.img);
  const [image, setImage] = useState('');
  const [updateNickname, setUpdateNickname] = useState(updateProfile.nickname);
  const searchstatus = useSelector(
    state => state.profile.userProfile[0].profileSearchStatus,
  );
  const [badgeInfo, setBadgeInfo] = useState({});

  const navigate = useNavigate();

  const dispatch = useDispatch();

  const handleOpenEditPage = () => {
    navigate('/ProfileEditPage');
    scrollToTop();
  };

  const handleNickname = event => {
    setUpdateNickname(event.target.value);
  };

  const handleEditSuccess = () => {
    if (updateNickname.length < 2) {
      dispatch(
        handleSuccessState({
          open: true,
          message: '2글자 이상의 닉네임을 입력해 주세요.',
          severity: 'error',
        }),
      );
      return;
    }

    if (updateNickname !== userProfile.nickname) {
      customAxios.basicAxios
        .get(`/users/nickname?nickname=${updateNickname}`)
        .then(response => {})
        .catch(error => {
          dispatch(
            handleSuccessState({
              open: true,
              message: '중복된 닉네임이 존재합니다.',
              severity: 'error',
            }),
          );
          return;
        });
    }

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

    const blob = new Blob([JSON.stringify(value)], {
      type: 'application/json',
    });
    formData.append('profilePageForm', blob);

    customAxios.imageAxios
      .post('/profile', formData)
      .then(response => {
        navigate('/ProfilePage');
        scrollToTop();
      })
      .catch(error => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '이미지의 용량이 너무 큽니다.',
            severity: 'error',
          }),
        );
      });
  };

  const handleCloseEditPage = () => {
    navigate('/ProfilePage');
    scrollToTop();
  };

  const handleOpenDeletePage = () => {
    navigate('/ProfileDeletePage');
    scrollToTop();
  };

  const handleBadge = () => {
    if (userPk !== userProfile.id) return;

    customAxios.authAxios
      .put('/profile/search-state', {
        id: userPk,
        searchstatus: badgeInfo.name,
      })
      .then(response => {})
      .catch(error => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '검색 상태 변경에 실패했습니다.',
            severity: 'error',
          }),
        );
      });

    if (badgeInfo.name === 'ALL') {
      setBadgeInfo({
        name: 'PROJECT',
        color: 'secondary',
        context: '프로젝트 팀을 구하고 있습니다.',
      });
    } else if (badgeInfo.name === 'PROJECT') {
      setBadgeInfo({
        name: 'STUDY',
        color: 'success',
        context: '스터디 팀을 구하고 있습니다.',
      });
    } else if (badgeInfo.name === 'STUDY') {
      setBadgeInfo({
        name: 'NONE',
        color: 'warning',
        context: '팀을 구하고 있지 않습니다.',
      });
    } else if (badgeInfo.name === 'NONE') {
      setBadgeInfo({
        name: 'ALL',
        color: 'primary',
        context: '모든 팀을 구하고 있습니다.',
      });
    }
  };

  useEffect(() => {
    if (searchstatus === 'ALL') {
      setBadgeInfo({
        name: 'ALL',
        color: 'primary',
        context: '모든 팀을 구하고 있습니다.',
      });
    } else if (searchstatus === 'PROJECT') {
      setBadgeInfo({
        name: 'PROJECT',
        color: 'secondary',
        context: '프로젝트 팀을 구하고 있습니다.',
      });
    } else if (searchstatus === 'STUDY') {
      setBadgeInfo({
        name: 'STUDY',
        color: 'success',
        context: '스터디 팀을 구하고 있습니다.',
      });
    } else if (searchstatus === 'NONE') {
      setBadgeInfo({
        name: 'NONE',
        color: 'warning',
        context: '팀을 구하고 있지 않습니다.',
      });
    }
  }, [searchstatus]);

  const handleDrop = event => {
    event.preventDefault();
    const files = event.dataTransfer.files;
    setPreviewImage(URL.createObjectURL(files[0]));
    setImage(files[0]);
  };

  const handleChange = event => {
    const files = event.target.files;
    setPreviewImage(URL.createObjectURL(files[0]));
    setImage(files[0]);
  };

  const userButtons = [
    <ProfileButton key="eidt" onClick={handleOpenEditPage} variant="outlined">
      수정
    </ProfileButton>,
    <ProfileApplyOffer key="offer" isMobile={isMobile}></ProfileApplyOffer>,
  ];

  const otherButtons = [
    <MyProjectStudy key="offer" isMobile={isMobile}></MyProjectStudy>,
    <ChattingRoom key="chat" variant="outlined"></ChattingRoom>,
  ];

  const editButtons = [
    <ProfileButton
      key="success"
      onClick={handleEditSuccess}
      color="primary"
      variant="outlined"
    >
      수정 완료
    </ProfileButton>,
    <ProfileButton
      key="cancel"
      onClick={handleCloseEditPage}
      color="primary"
      variant="outlined"
    >
      수정 취소
    </ProfileButton>,
    <ProfileButton
      key="delete"
      onClick={handleOpenDeletePage}
      color="error"
      variant="contained"
    >
      회원 탈퇴
    </ProfileButton>,
  ];

  if (props.type === 'edit') {
    return (
      <>
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
            justifyContent="center"
            onDrop={handleDrop}
            onDragOver={event => event.preventDefault()}
          >
            <Grid item>
              <Avatar
                src={previewImage}
                variant="circular"
                sx={{
                  width: '280px',
                  height: '280px',
                  boxShadow: '0px 0px 10px 4px #888888',
                  opacity: image === '' ? 0.5 : 1,
                }}
              />
            </Grid>
            <Grid
              item
              container
              sx={{
                position: 'absolute',
                top: '140px',
                left: '50%',
                display: 'flex',
                fontWeight: '600',
                transform: 'translate(-50%,-50%)',
                justifyContent: 'center',
                alignItems: 'center',
                textShadow: '0 0 3px #fff',
                opacity: image === '' ? 1 : 0,
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
            <Grid item xs={4} display="flex" justifyContent={'end'}>
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
            <Grid item xs={4}>
              <Typography color="primary" variant="body1" textAlign={'center'}>
                이미지 변경
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
                  opacity: image === '' ? 0.5 : 1,
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
                    color="primary"
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
