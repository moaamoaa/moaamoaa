import React from 'react';

import styled from '@emotion/styled';

import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';

export default function ProfileContentContainer(props) {
  return (
    <ProfileContent>
      <ContentTitle color="initial">{props.title}</ContentTitle>
      <MoaContainer>{props.content}</MoaContainer>
    </ProfileContent>
  );
}

const ProfileContent = styled(Container)`
  padding: 0 !important;
`;

const ContentTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.5em;

  margin-bottom: 0.5rem;
`;

const MoaContainer = styled(Container)`
  position: relative;

  width: 100%;
  min-height: 8rem;

  margin-bottom: 4rem;
  padding: 1rem;

  border: 1px solid black;
  border-radius: 5px;
`;
