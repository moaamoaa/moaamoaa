import React from 'react';

import styled from 'styled-components';

import Container from '@mui/material/Container';
import Skeleton from '@mui/material/Skeleton';
import Typography from '@mui/material/Typography';
import ButtonGroup from '@mui/material/ButtonGroup';
import Button from '@mui/material/Button';

import CardList from 'components/common/card/CardList';

const userButtons = [
  <Button key="offer">수정</Button>,
  <Button key="chat">신청 목록</Button>,
];

const otherButtons = [
  <Button key="offer">제안</Button>,
  <Button key="chat">채팅</Button>,
];

const user = {
  id: 0,
  name: '김싸피',
  tech: [
    ['front-end_icons', 'javascript'],
    ['front-end_icons', 'typescript'],
  ],
  link: {
    github: 'https://github.com/LimSB-dev',
    tistory: '',
    velog: '',
  },
};

export default function Profile(props) {
  return (
    <MoaProfile component="article">
      <MoaSkeleton variant="circular" />
      <Typography variant="h4" color="initial" fontWeight={900}>
        {user.name}
      </Typography>

      <CardList type={'tech'} cards={user.tech}></CardList>
      <hr />
      <CardList type={'link'} cards={user.link}></CardList>

      <MoaButtons orientation="vertical" variant="contained" color="primary">
        {user.id ? userButtons : otherButtons}
      </MoaButtons>
    </MoaProfile>
  );
}

const MoaProfile = styled(Container)`
  position: relative;
  width: 320px;
  height: 400px;
  padding: 0;
`;

const MoaSkeleton = styled(Skeleton)`
  margin: 0 auto;
  width: calc(160px + 8vw);
  height: calc(160px + 8vw);
  max-width: 320px;
  max-height: 320px;
`;

const MoaButtons = styled(ButtonGroup)`
  width: 100%;
`;
