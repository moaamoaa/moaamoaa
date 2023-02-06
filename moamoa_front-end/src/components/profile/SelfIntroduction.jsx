import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@mui/material/Typography';
import styled from '@emotion/styled';
import LongMenu from './LongMenu';
import { Container, Grid } from '@mui/material';

export default function SelfIntroduction() {
  const curProfile = useSelector(state => state.profile.userProfile);
  return (
    <>
      <ContentTitle color="initial">자기 소개</ContentTitle>
      <MoaContainer spacing={0}>
        <Grid container spacing={5}>
          <Grid item xs={10}>
            <Typography variant="body1" color="initial">
              {curProfile.context
                ? curProfile.context
                : `안녕하세요. ${curProfile.nickName}입니다.`}
            </Typography>
          </Grid>
          <Grid item xs={1}>
            <ProfileLongMenu></ProfileLongMenu>
          </Grid>
        </Grid>
      </MoaContainer>
    </>
  );
}
const ContentTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.5em;

  margin-bottom: 0.5rem;
`;

const MoaContainer = styled(Container)`
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;

  width: 100%;
  min-height: 8rem;

  margin-bottom: 4rem;
  padding: 1rem;

  border: 1px solid black;
  border-radius: 5px;
`;

const ProfileLongMenu = styled(LongMenu)`
  position: absolute;
`;
