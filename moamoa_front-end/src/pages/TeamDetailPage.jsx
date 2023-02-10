import * as React from 'react';
import { useEffect, useState } from 'react';
// import axios from 'axios';
import CustomAxios from 'utils/axios';
import { useSelector, useDispatch } from 'react-redux';
import { teamOpenSuccess, teamCloseSuccess } from 'redux/team';
import { handleUpdate, handleCloseTeamDetail } from 'redux/team';
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
import { useNavigate } from 'react-router-dom';

// axios 입력값을 불러와서 띄우기

export default function TeamDetailPage() {
  //navigation
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const goToUpdate = () => {
    // 팀 수정 눌렀을 때, 이동할 프론트 주소
    navigate(`/TeamUpdatePage`);
    dispatch(handleUpdate({ projectId: projectId }));
  };

  // const [isLoaded, setIsLoaded] = useState(false);
  const [detail, setDetail] = useState([]);

  // redux
  const projectId = useSelector(state => state.team.projectId);

  // axios
  useEffect(() => {
    CustomAxios.basicAxios
      // 해당 id의 프로젝트 조회됨 axios 주소
      .get(`/projects/detail?projectId=${projectId}`)
      .then(response => {
        setDetail(response.data);
        console.log(response);
        console.log('조회성공!');
      })
      .catch(error => {
        console.log(error);
      });
    // setIsLoaded(true);
  }, [projectId]);

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
            <Button
              onClick={goToUpdate}
              size="small"
              variant="contained"
              color="primary"
            >
              팀 수정
            </Button>
            <Button
              size="small"
              variant="contained"
              color="primary"
              onClick={async () => {
                console.log(projectId); // 잘 뜸
                await CustomAxios.authAxios
                  .delete('/projects', {
                    data: {
                      projectId: projectId,
                    },
                    // areaId: null,
                    // category: null,
                    // endDate: null,
                    // img: null,
                    // projectStatus: null,
                    // techStacks: [],
                    // title: null,
                    // totalPeople: null,
                    // userId: null,
                  })
                  .then(e => {
                    // dispatch(teamCloseSuccess({ projectId: projectId }));
                    console.log(e);
                    console.log('삭제완료!');
                    alert('게시물이 삭제되었습니다.');
                    navigate('/'); // 삭제 후 홈으로 보내기 (프론트 주소)
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
                <Stack
                  key={tech.name}
                  direction="row"
                  sx={{
                    display: 'inline-flex',
                    justifyContent: 'space-between',
                  }}
                >
                  <span>{tech.name}</span>
                  <MoaImg
                    // src={`${process.env.PUBLIC_URL}/images/whole_icons/${tech.logo}@4x.png`} // 다른 폴더에 있는 건 어쩌지?
                    src={tech.logo}
                  />
                </Stack>
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
      <hr></hr>
    </>
  );
}

const MoaImg = styled(Avatar)`
  min-width: 40px;
  min-height: 40px;
  box-shadow: 0px 4px 5px rgba(0, 0, 0, 0.25);
`;
