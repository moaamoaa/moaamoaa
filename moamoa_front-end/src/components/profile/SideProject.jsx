import React from 'react';

import styled from '@emotion/styled';

import { Typography, Grid, Container } from '@mui/material';

export default function SideProject(props) {
  console.log(props);
  return (
    <>
      <Grid container spacing={2}>
        <Grid item xs={1}>
          <SideProjectYear item>{props.year}</SideProjectYear>
        </Grid>
        <Grid item xs={11}>
          <SideProjectTitle variant="body1" color="initial">
            {props.title}
          </SideProjectTitle>
          {props.techStacks ? (
            props.techStacks.map((techStack, idx) => (
              <TechStackContainer key={idx}>{techStack}</TechStackContainer>
            ))
          ) : (
            <></>
          )}
          <SideProjectContext>{props.context}</SideProjectContext>
        </Grid>
      </Grid>
    </>
  );
}

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

const TechStackContainer = styled(Container)`
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

const SideProjectContext = styled(Typography)`
  position: relative;
  font-weight: 600;
  font-size: 1em;

  margin: 1rem 0;
`;
