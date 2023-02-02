import * as React from 'react';
import { useRef } from 'react';
import axios from 'axios';
import dayjs from 'dayjs';

import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Calendar from 'components/team/Calendar';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Grid from '@mui/material/Grid';

import TeamBannerEdit from 'components/team/TeamBannerEdit';
import TeamMemberSearchList from 'components/common/card/TeamMemberSearchList';

import SingleTextField from 'components/team/SingleTextField';
import MultipleSelect from 'components/team/MultipleSelect';
import MultilineText from 'components/team/MultilineText';
import SingleSelect from 'components/team/SingleSelect';
import SingleSelectNumber from 'components/team/SingleSelectNumber';
import SingleSelectOnOff from 'components/team/SingleSelectOnOff';
import SingleSelectRegion from 'components/team/SingleSelectRegion';

export default function TeamCreatePage() {
  //spring boot url
  const baseUrl = 'http://localhost:8080';
  //프로젝트 수정 url
  const id = 1; // 프로젝트 아이디인가?
  //ref
  const inputRef = useRef('');
  const classRef = useRef('');
  const numberRef = useRef('');
  const onoffRef = useRef('');
  const regionRef = useRef('');
  const titleRef = useRef('');
  const dateRef = useRef('');
  const techRef = useRef('');

  //handler
  const handleClick = () => {
    // 팀 이름 string
    console.log(titleRef.current);
    console.log(typeof titleRef.current);
    // 모집 구분 string
    console.log(classRef.current);
    console.log(typeof classRef.current);
    // 모집 인원 1~10 number
    console.log(numberRef.current);
    console.log(typeof numberRef.current);
    // 마감 날짜 2023-02-02 string
    console.log(dayjs(dateRef.current).format('YYYY-MM-DD'));
    console.log(typeof dayjs(dateRef.current).format('YYYY-MM-DD'));
    // 진행 방식 string
    console.log(onoffRef.current);
    console.log(typeof onoffRef.current);
    // 지역 number
    console.log(regionRef.current);
    console.log(typeof regionRef.current);
    // 기술 스택 list[number]
    console.log(techRef.current);
    console.log(typeof techRef.current);
    // 팀 소개 string
    console.log(inputRef.current);
    console.log(typeof inputRef.current);

    // 배열에 정보를 담아서 POST... image, content, techstack : null ok
    axios({
      method: 'put',
      url: `${baseUrl}/projects/${id}`,
      data: {
        areaId: regionRef.current,
        category: classRef.current,
        contents: inputRef.current,
        countOffer: 0,
        createDate: 'string',
        currentPeople: 0,
        endDate: dayjs(dateRef.current).format('YYYY-MM-DD'),
        hit: 0,
        img: null,
        isLeader: true,
        locked: true,
        projectId: 0,
        projectStatus: onoffRef.current,
        startDate: 'string',
        techStacks: techRef.current,
        title: titleRef.current,
        totalPeople: numberRef.current,
        userid: 1,
      },
    })
      .then(e => {
        console.log(e);
        console.log('포스트완료!');
      })
      .catch(error => {
        console.log(error);
      });
  };

  const teamBannerEdit = {
    title: <SingleTextField ref={titleRef}></SingleTextField>, // project_title POST
    leader: '팀장 이름', // GET
    image: '',
  };

  return (
    <>
      <Container fixed>
        <TeamBannerEdit post={teamBannerEdit} />
      </Container>
      <Container fixed>
        <Grid container justifyContent="flex-end">
          <Stack
            direction="row"
            spacing={2}
            // justifyContent="flex-end"
            sx={{ pt: 4 }}
          >
            {/* <Button size="small" variant="contained" color="primary">
              제안 및 지원 확인
            </Button> */}
            <Button
              onClick={handleClick}
              size="small"
              variant="contained"
              color="primary"
            >
              등록
            </Button>
            <Button size="small" variant="contained" color="primary">
              취소
            </Button>
          </Stack>
        </Grid>
      </Container>
      <Container fixed>
        <h2>모집 정보</h2>
        <Box>
          <h4>모집 구분</h4>
          <SingleSelect ref={classRef}></SingleSelect>
          <h4>모집 인원</h4>
          <SingleSelectNumber ref={numberRef}></SingleSelectNumber>
          <h4>마감 날짜</h4>
          <Calendar ref={dateRef}></Calendar>
          <h4>진행 방식</h4>
          <SingleSelectOnOff ref={onoffRef}></SingleSelectOnOff>
          <h4>지역</h4>
          <SingleSelectRegion ref={regionRef}></SingleSelectRegion>
          <h4>기술 스택</h4>
          <MultipleSelect ref={techRef}></MultipleSelect>
        </Box>
      </Container>
      <Container fixed>
        <h2>팀 소개</h2>
        <MultilineText
          placeholder="팀 소개를 작성해 주세요."
          ref={inputRef}
        ></MultilineText>
      </Container>
      <Container fixed>
        <h2>팀원 소개</h2>
        <TeamMemberSearchList></TeamMemberSearchList>
      </Container>
    </>
  );
}
