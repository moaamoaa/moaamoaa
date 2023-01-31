import * as React from 'react';

import Container from '@mui/material/Container';
// import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';

import TeamBanner from 'components/team/TeamBanner';
import TeamMemberSearchList from 'components/common/card/TeamMemberSearchList';

import Typography from '@mui/material/Typography';

// axios 입력값을 불러와서 띄우기

export default function TeamDetailPage() {
  const teamBanner = {
    title: '팀이름', // project_title GET
    leader: '팀장 이름', // GET
    image: 'https://source.unsplash.com/random', // project_image GET
  };

  return (
    <>
      <TeamBanner post={teamBanner} />
      <Container fixed>
        <h2>모집 정보</h2>
        <Paper
          variant="outlined"
          elevation={50}
          style={{
            padding: 20,
            // border: '1px solid black',
          }}
        >
          <h4>모집 구분</h4>
          <Typography variant="body1" color="initial">
            GET
          </Typography>
          <h4>모집 인원</h4>
          <Typography variant="body1" color="initial">
            GET
          </Typography>
          <h4>모집 기간</h4>
          <Typography variant="body1" color="initial">
            GET
          </Typography>
          <h4>진행 방식</h4>
          <Typography variant="body1" color="initial">
            GET
          </Typography>
          <h4>지역</h4>
          <Typography variant="body1" color="initial">
            GET
          </Typography>
          <h4>기술 스택</h4>
          <Typography variant="body1" color="initial">
            GET
          </Typography>
        </Paper>
      </Container>
      <Container fixed>
        <h2>팀 소개</h2>
        <Paper
          variant="outlined"
          elevation={50}
          style={{
            padding: 20,
            // border: '1px solid black',
          }}
        >
          <Typography variant="body1" color="initial">
            GET 우리 팀 소개입니다. GET 우리 팀 소개입니다.GET 우리 팀
            소개입니다.GET 우리 팀 소개입니다.GET 우리 팀 소개입니다.GET 우리 팀
            소개입니다.GET 우리 팀 소개입니다.GET 우리 팀 소개입니다.GET 우리 팀
            소개입니다.
          </Typography>
        </Paper>
      </Container>
      <Container fixed>
        <h2>팀원 소개</h2>
        <TeamMemberSearchList></TeamMemberSearchList>
      </Container>
    </>
  );
}
