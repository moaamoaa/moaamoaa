import React from 'react';

import styled from '@emotion/styled';

import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';

const SideProjectInfo = {
  year: '2023',
  title: '프로젝트명',
  techStacks: ['JavaScript', 'TypeScript', 'React'],
  content: '프로젝트 소개',
};

export default function SideProject(props) {
  return (
    <>
      <Grid container spacing={2}>
        <SideProjectYear item>{SideProjectInfo.year}</SideProjectYear>
        <Grid item>
          <SideProjectTitle variant="body1" color="initial">
            {SideProjectInfo.title}
          </SideProjectTitle>
          {SideProjectInfo.techStacks.map((techStack, idx) => (
            <TechStackContainer key={idx}>{techStack}</TechStackContainer>
          ))}
          <SideProjectContent>{SideProjectInfo.content}</SideProjectContent>
        </Grid>
      </Grid>
    </>
  );
}

const SideProjectYear = styled(Grid)`
  font-weight: bolder;
  font-size: 1.2rem;

  color: gray;
`;

const SideProjectTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.2em;

  margin-bottom: 0.5rem;
`;

const TechStackContainer = styled.span`
  position: relative;
  font-weight: 400;
  font-size: 0.8em;

  background-color: #888;
  color: #ffffff;

  padding: 0.5rem;
  margin: 5rem 0;
  margin-right: 0.5rem;

  border-radius: 0.5rem;
`;

const SideProjectContent = styled(Typography)`
  position: relative;
  font-weight: 600;
  font-size: 1em;

  margin: 1rem 0;
`;
