import styled from '@emotion/styled';
import { Container, Typography } from '@mui/material';
import React from 'react';

function ProfileEditContent() {
  return (
    <>
      <ContentTitle color="initial">기본 정보 수정</ContentTitle>
      <MoaContainer spacing={0}>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloremque,
        totam error dolorem blanditiis at nostrum eum possimus voluptate
        corrupti distinctio consequatur iste perspiciatis ullam dolore. Sequi
        similique sapiente ea nihil.
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

export default ProfileEditContent;
