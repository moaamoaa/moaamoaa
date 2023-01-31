import * as React from 'react';

import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Calendar from 'components/team/Calendar';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Grid from '@mui/material/Grid';

import TeamBannerEdit from 'components/team/TeamBannerEdit';
import TeamMemberSearchList from 'components/common/card/TeamMemberSearchList';

import TextField from 'components/team/TextField';
import MultipleSelect from 'components/team/MultipleSelect';
import MultilineText from 'components/team/MultilineText';
import SingleSelect from 'components/team/SingleSelect';
import SingleSelectNumber from 'components/team/SingleSelectNumber';
import SingleSelectOnOff from 'components/team/SingleSelectOnOff';
import SingleSelectRegion from 'components/team/SingleSelectRegion';

export default function TeamUpdatePage() {
  const teamBannerEdit = {
    title: <TextField></TextField>, // project_title POST
    leader: '팀장 이름', // GET
    image: 'https://source.unsplash.com/random', // project_image POST
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
            <Button size="small" variant="contained" color="primary">
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
          <SingleSelect></SingleSelect>
          <h4>모집 인원</h4>
          <SingleSelectNumber></SingleSelectNumber>
          <h4>모집 기간</h4>
          <Calendar></Calendar>
          <h4>진행 방식</h4>
          <SingleSelectOnOff></SingleSelectOnOff>
          <h4>지역</h4>
          <SingleSelectRegion></SingleSelectRegion>
          <h4>기술 스택</h4>
          <MultipleSelect></MultipleSelect>
        </Box>
      </Container>
      <Container fixed>
        <h2>팀 소개</h2>
        <MultilineText></MultilineText>
      </Container>
      <Container fixed>
        <h2>팀원 소개</h2>
        <TeamMemberSearchList></TeamMemberSearchList>
      </Container>
    </>
  );
}
