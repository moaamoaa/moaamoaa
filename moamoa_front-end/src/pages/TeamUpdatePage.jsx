import * as React from 'react';
import { useRef, useState } from 'react';
import CustomAxios from 'utils/axios';
import dayjs from 'dayjs';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Calendar from 'components/team/Calendar';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Grid from '@mui/material/Grid';
import { useNavigate } from 'react-router-dom';
import IconButton from '@mui/material/IconButton';
import PhotoCamera from '@mui/icons-material/PhotoCamera';
import SingleTextField from 'components/team/SingleTextField';
import MultipleSelect from 'components/team/MultipleSelect';
import MultilineText from 'components/team/MultilineText';
import SingleSelect from 'components/team/SingleSelect';
import SingleSelectNumber from 'components/team/SingleSelectNumber';
import SingleSelectOnOff from 'components/team/SingleSelectOnOff';
import SingleSelectRegion from 'components/team/SingleSelectRegion';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';

export default function TeamCreatePage() {
  //ref
  const inputRef = useRef('');
  const classRef = useRef('');
  const numberRef = useRef('');
  const onoffRef = useRef('');
  const regionRef = useRef('');
  const titleRef = useRef('');
  const dateRef = useRef('');
  const techRef = useRef('');

  // redux
  const { userPk } = useSelector(state => state.user.userPk);
  const projectId = useSelector(state => state.team.projectId);
  const [fileImageURL, setFileImageURL] = useState('');
  const [fileImage, setFileImage] = useState('');

  //navigation
  const navigate = useNavigate();
  const goBackToDetail = () => {
    // 수정 취소 버튼 눌렀을 때, 이동할 프론트 주소 : 디테일 페이지
    navigate(`/TeamDetailPage/?projectId=${projectId}`);
  };

  // handler : 이미지 파일 업로드
  const handleChange = event => {
    const files = event.target.files;
    // 미리보기용
    setFileImageURL(URL.createObjectURL(files[0]));
    // axios용
    setFileImage(files[0]);
  };

  //handler
  const handleClick = async event => {
    // FormData 객체 생성
    const formData = new FormData();
    // file이라는 key값에 value로 이미지 파일 담기
    formData.append('file', fileImage); // fileImage는 files[0]를 담은 useState값
    // projectForm이라는 key값에 value로 담기
    const value = {
      areaId: regionRef.current,
      category: classRef.current,
      contents: inputRef.current,
      endDate: dayjs(dateRef.current).format('YYYY-MM-DD'),
      img: '',
      projectId: projectId, // 생성 요청 시에 줄 수 있는 값은 아니니까
      projectStatus: onoffRef.current,
      techStacks: techRef.current,
      title: titleRef.current,
      totalPeople: numberRef.current,
      userid: userPk,
    };
    const blob = new Blob([JSON.stringify(value)], {
      type: 'application/json',
    });
    formData.append('projectForm', blob);
    // OR 백엔드 요청 방식에 따라
    // formData.append('projectForm', JSON.stringify(value));
    // 찍어보기
    console.log(formData.get('projectForm'), '프로젝트폼');
    console.log(formData.get('file'), '파일');
    // Axios
    await CustomAxios.imageAxios({
      method: 'POST',
      url: '/projects',
      mode: 'cors',
      data: formData, // 요거 하나만 보내기!
      // header: { 'Content-Type': 'multipart/form-data' },
    })
      .then(e => {
        console.log(e);
        console.log('수정완료!');
        navigate(`/TeamDetailPage/?projectId=${projectId}`); // 수정 완료 후 디테일 페이지로
      })
      .catch(error => {
        console.log(error);
      });
  };

  return (
    <>
      <Container fixed>
        <Paper
          sx={{
            position: 'relative',
            color: '#fff',
            mb: 4,
            backgroundSize: 'cover',
            backgroundRepeat: 'no-repeat',
            backgroundPosition: 'center',
            backgroundImage: `url(${fileImageURL})`,
            height: 'calc(400px + 10vw)', // 반응형 웹 calc
            maxHeight: 'calc(100vh - 56px)',
          }}
        >
          <Dim />
          <Grid container>
            <Grid item md={6}>
              <Box
                sx={{
                  position: 'relative',
                  p: { xs: 3, md: 6 },
                  pr: { md: 0 },
                }}
              >
                <Typography
                  component="h1"
                  variant="h2"
                  color="inherit"
                  gutterBottom
                >
                  {/* 팀 이름  */}
                  <SingleTextField ref={titleRef}></SingleTextField>
                </Typography>
                <Typography variant="h4" color="inherit" paragraph>
                  {/* 팀장 이름 */}
                </Typography>
                <div>
                  <input
                    accept="image/*"
                    type="file" // 파일
                    id="select-image"
                    style={{ display: 'none' }}
                    onChange={handleChange} // input에 onchange
                    multiple="multiple"
                  />
                  <label htmlFor="select-image">
                    <IconButton
                      color="primary"
                      aria-label="upload picture"
                      component="span"
                    >
                      <input hidden accept="image/*" type="file" />
                      <PhotoCamera />
                      {/* 파일 업로드 창 오픈되는 카메라 아이콘 */}
                    </IconButton>
                  </label>
                </div>
              </Box>
            </Grid>
          </Grid>
        </Paper>
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
              완료
            </Button>
            <Button
              onClick={goBackToDetail}
              size="small"
              variant="contained"
              color="primary"
            >
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
          <h4>모집 정원</h4>
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
      <hr></hr>
    </>
  );
}
const Dim = styled(Box)`
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.2);
`;
