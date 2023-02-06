import React from 'react';

import styled from '@emotion/styled';

import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';

import LongMenu from 'components/profile/LongMenu';
import { useSelector } from 'react-redux';

export default function ProfileContentContainer(props) {
  return (
    <>
      <ContentTitle color="initial">{props.title}</ContentTitle>
      <MoaContainer spacing={0}>{props.content}</MoaContainer>
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
