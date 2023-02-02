import * as React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';

import { Container, Paper, Button, Stack, Grid, Link } from '@mui/material/';

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

  //spring boot url
  const baseUrl = 'http://localhost:8080';

  //get url
  const Id = 1; //프로젝트 아이디

  //READ
  const [isLoaded, setIsLoaded] = useState(false);
  const [detail, setDetail] = useState([]);

  // axios
  useEffect(() => {
    axios
      .get(`${baseUrl}/projects/${Id}`)
      .then(response => {
        setDetail(response);
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
    setIsLoaded(true);
  }, [isLoaded]);

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
                await axios.delete(`${baseUrl}/projects/${Id}`);
              }} // 팀 삭제 버튼 클릭 시, 삭제 요청보내기 ( 해당 id의 팀 data가 삭제 됨 )
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
          elevation={0}
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
