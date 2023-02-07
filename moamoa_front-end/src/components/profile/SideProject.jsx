import React from 'react';

import styled from '@emotion/styled';

import { Typography, Grid, Container } from '@mui/material';
import LongMenu from './LongMenu';
import { useSelector } from 'react-redux';

export default function SideProject() {
  const SideProjects = useSelector(state => state.profile.SideProjects);
  const techStacks = useSelector(state => state.profile.techStacks);

  return (
    <>
      <ContentTitle color="initial">주요 프로젝트</ContentTitle>
      <MoaContainer container>
        {SideProjects ? (
          SideProjects.map((SideProject, idx) => {
            <Grid key={idx} item container alignItems="start">
              <Grid item xs={1} md={1}>
                <SideProjectYear>{SideProject.year}</SideProjectYear>
              </Grid>
              <Grid item xs={10} md={11}>
                <SideProjectTitle variant="body1" color="initial">
                  {SideProject.title}
                </SideProjectTitle>
                <Grid container>
                  {techStacks ? (
                    techStacks.map((techStack, idx) => (
                      <TechStackGrid key={idx}>{techStack}</TechStackGrid>
                    ))
                  ) : (
                    <>
                      <TechStackGrid>HTML</TechStackGrid>
                      <TechStackGrid>CSS</TechStackGrid>
                      <TechStackGrid>JavaScript</TechStackGrid>
                    </>
                  )}
                </Grid>
                <SideProjectContext>프로젝트 소개</SideProjectContext>
              </Grid>
              <Grid item xs={1} justifyContent="end" sx={{ display: 'flex' }}>
                <ProfileLongMenu></ProfileLongMenu>
              </Grid>
            </Grid>;
          })
        ) : (
          <Grid item container alignItems="start">
            <Grid item xs={1}>
              <SideProjectYear>2023</SideProjectYear>
            </Grid>
            <Grid item xs={10}>
              <SideProjectTitle variant="body1" color="initial">
                프로젝트명
              </SideProjectTitle>
              <Grid container>
                {techStacks ? (
                  techStacks.map((techStack, idx) => (
                    <TechStackGrid key={idx}>{techStack}</TechStackGrid>
                  ))
                ) : (
                  <>
                    <TechStackGrid>HTML</TechStackGrid>
                    <TechStackGrid>CSS</TechStackGrid>
                    <TechStackGrid>JavaScript</TechStackGrid>
                  </>
                )}
              </Grid>
              <SideProjectContext>프로젝트 소개</SideProjectContext>
            </Grid>
            <Grid item xs={1} justifyContent="end" sx={{ display: 'flex' }}>
              <ProfileLongMenu></ProfileLongMenu>
            </Grid>
          </Grid>
        )}
      </MoaContainer>
    </>
  );
}

const ContentTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.5em;

  margin-bottom: 0.5rem;
`;

const MoaContainer = styled(Grid)`
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
