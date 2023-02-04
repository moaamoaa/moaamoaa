import * as React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import CustomAxios from 'utils/axios';

import { Container, Paper, Button, Stack, Grid, Link } from '@mui/material/';

import TeamBanner from 'components/team/TeamBanner';
import TeamMemberSearchList from 'components/common/card/TeamMemberSearchList';

import Typography from '@mui/material/Typography';

// axios 입력값을 불러와서 띄우기

export default function TeamDetailPage() {
  //READ
  const [isLoaded, setIsLoaded] = useState(false);
  const [detail, setDetail] = useState([]);

  // axios
  useEffect(() => {
    CustomAxios.authAxios
      // .get(`/projects/detail?projectId=${projectId}`) // 팀페이지 open ${projectId}를 받아오거나 1 입력하면 조회됨
      .get(`/projects/detail?projectId=1`)
      .then(response => {
        setDetail(response.data);
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
    setIsLoaded(true);
  }, [isLoaded]);

  // 배너
  const teamBanner = {
    title: <p>{detail.title}</p>,
    leader: '팀장 이름',
    image: <p>{detail.img}</p>,
  };

  return (
    <>
      <Container fixed>
        <TeamBanner post={teamBanner} />
      </Container>
      <Container fixed>
        <Grid container justifyContent="flex-end">
          <Stack
            direction="row"
            spacing={2}
            // justifyContent="flex-end"
            sx={{ pt: 4 }}
          >
            <Button size="small" variant="contained" color="primary">
              지원 보내기 / 제안 및 지원 확인
            </Button>
            <Link href="http://localhost:3000/TeamUpdatePage">
              <Button size="small" variant="contained" color="primary">
                팀 수정
              </Button>
            </Link>
            <Button
              size="small"
              variant="contained"
              color="primary"
              onClick={async () => {
                await CustomAxios.authAxios.delete('/projects'); // 프로젝트스터디 삭제
              }} // 팀 삭제 버튼 클릭 시, 삭제 요청보내기
            >
              팀 삭제
            </Button>
          </Stack>
        </Grid>
      </Container>
      <Container fixed>
        <h2>모집 정보</h2>
        <Paper
          variant="outlined"
          elevation={0}
          style={{
            padding: 20,
            // border: '1px solid black',
          }}
        >
          <h4>모집 구분</h4>
          <Typography variant="body1" color="initial">
            {detail.category}
          </Typography>
          <h4>모집 정원</h4>
          <Typography variant="body1" color="initial">
            {detail.totalPeople}
          </Typography>
          <h4>마감 날짜</h4>
          <Typography variant="body1" color="initial">
            {detail.endDate}
          </Typography>
          <h4>진행 방식</h4>
          <Typography variant="body1" color="initial">
            {detail.projectStatus}
          </Typography>
          <h4>지역</h4>
          <Typography variant="body1" color="initial">
            GET
          </Typography>
          <h4>기술 스택</h4>
          <Typography component="div" variant="body1" color="initial">
            {detail.techStacks &&
              detail.techStacks.map(tech => (
                <p key={tech.name}>
                  {tech.name} 로고는 {tech.logo}
                </p> // 로고가 이미지가 아니네!
              ))}
          </Typography>
        </Paper>
      </Container>
      <Container fixed>
        <h2>팀 소개</h2>
        <Paper
          variant="outlined"
          elevation={0}
          style={{
            padding: 20,
            // border: '1px solid black',
          }}
        >
          <Typography variant="body1" color="initial">
            {detail.contents}
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
