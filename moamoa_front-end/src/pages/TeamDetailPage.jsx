import * as React from 'react';
import { useEffect, useState } from 'react';
// import axios from 'axios';
import CustomAxios from 'utils/axios';

import {
  Container,
  Paper,
  Button,
  Stack,
  Grid,
  Link,
  styled,
  Avatar,
} from '@mui/material/';

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
      // 해당 id의 프로젝트 조회됨
      // .get(`/projects/detail?projectId=${projectId}`) // 팀페이지 open ${projectId}를 받아오거나 1 입력하면 조회됨
      .get(`/projects/detail?projectId=1`)
      .then(response => {
        setDetail(response.data);
        console.log(response);
        console.log('조회완료!');
      })
      .catch(error => {
        console.log(error);
      });
    setIsLoaded(true);
  }, [isLoaded]);

  // 배너
  const teamBanner = {
    title: <p>{detail.title}</p>,
    leader: <span>{detail.leaderNickname}</span>,
    image: <span>{detail.img}</span>,
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
                console.log(`프로젝트아이디는 ${detail.projectId}`);
                console.log(detail.projectId);
                await CustomAxios.authAxios
                  .delete('/project', {
                    projectId: detail.projectId, // 팀관리에서 선택
                  })
                  .then(e => {
                    console.log(e);
                    console.log('삭제완료!');
                  });
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
            {detail.areaId}
            {/* 매칭해줘야하나 */}
          </Typography>
          <h4>기술 스택</h4>
          <Typography component="div" variant="body1" color="initial">
            {detail.projectTechStacks &&
              detail.projectTechStacks.map(tech => (
                <span key={tech.name}>
                  <span>{tech.name}</span>
                  <MoaImg
                    src={`${process.env.PUBLIC_URL}/images/whole_icons/${tech.logo}@4x.png`} // 다른 폴더에 있는 건 어쩌지?
                  />
                </span>
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

const MoaImg = styled(Avatar)`
  min-width: 40px;
  min-height: 40px;
  box-shadow: 0px 4px 5px rgba(0, 0, 0, 0.25);
`;
