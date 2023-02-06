import React from 'react';

import styled from '@emotion/styled';

import { Typography, Grid, Container } from '@mui/material';
import LongMenu from './LongMenu';

export default function SideProject() {
  return (
    <>
      <ContentTitle color="initial">주요 프로젝트</ContentTitle>
      <MoaContainer spacing={0}>
        <Grid container spacing={5}>
          <Grid item xs={1} md={1}>
            <SideProjectYear>{}</SideProjectYear>
          </Grid>
          <Grid item xs={10} md={11}>
            <SideProjectTitle variant="body1" color="initial">
              {}
            </SideProjectTitle>
            <Grid container>
              {/* {techStacks.map((techStack, idx) => (
            <TechStackGrid key={idx}>{techStack}</TechStackGrid>
          ))} */}
            </Grid>
            <SideProjectContext>{}</SideProjectContext>
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

const SideProjectYear = styled(Typography)`
  font-weight: bolder;
  font-size: 1.2rem;

  color: gray;
`;

const SideProjectTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.2em;

  margin-bottom: 0.5rem;
`;

const TechStackGrid = styled(Grid)`
  position: relative;
  font-weight: 400;
  font-size: 0.8em;

  background-color: #888;
  color: #ffffff;

  padding: 0.5rem;
  margin-right: 0.5rem;

  border-radius: 0.5rem;
`;

const SideProjectContext = styled(Typography)`
  position: relative;
  font-weight: 600;
  font-size: 1em;

  margin: 1rem 0;
`;

const ProfileLongMenu = styled(LongMenu)`
  position: absolute;
`;
