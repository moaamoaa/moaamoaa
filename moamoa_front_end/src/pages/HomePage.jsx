import { useRef, useEffect, useState } from 'react';
import styled from '@emotion/styled';

import { Container, Grid, Typography } from '@mui/material';

import CardList from 'components/common/card/CardList';
import MainBanner from 'components/common/carousel/MainBanner';
import { searchState } from 'redux/search';
import { useDispatch } from 'react-redux';
import customAxios from 'utils/axios';
import Footer from 'components/common/footer/Footer';

export default function HomePageSample() {
  // redux
  const dispatch = useDispatch();

  // axios
  useEffect(() => {
    customAxios.basicAxios
      .get('/search')
      .then(response => {
        const areas = response.data.areas;
        const techs = response.data.techs;
        dispatch(
          searchState({
            area: areas,
            tech: techs,
          }),
        );
      })
      .catch(error => {
        console.log(error);
      });
  });

  // reponse data로 넘어오는 값을 자식에게 넘겨줌
  const [recoProject, setRecoProject] = useState([]);
  const [recoStudy, setRecoStudy] = useState([]);
  const [recoMember, setRecoMember] = useState([]);
  const [check, setCheck] = useState(false);

  // 금주의 프로젝트 추천
  useEffect(() => {
    customAxios.basicAxios
      .get('/search/project?&size=3&sort=offer,desc&category=PROJECT')
      .then(response => {
        setRecoProject(response.data);
      })
      .catch(error => {
        console.log(error.data);
      });
    setCheck(true);
  }, [check]);

  // 금주의 스터디 추천
  useEffect(() => {
    customAxios.basicAxios
      .get('/search/project?&size=3&sort=offer,desc&category=STUDY')
      .then(response => {
        setRecoStudy(response.data);
      })
      .catch(error => {
        console.log(error.data);
      });
    setCheck(true);
  }, [check]);

  // 금주의 팀원 추천
  useEffect(() => {
    customAxios.basicAxios
      .get('/search/profile?&size=4&sort=offer,desc&category=STUDY')
      .then(response => {
        setRecoMember(response.data);
      })
      .catch(error => {
        console.log(error.data);
      });
    setCheck(true);
  }, [check]);

  const [windowHeight, setWindowHeight] = useState(window.pageYOffset);
  const heightRef = useRef(window.pageYOffset);

  // 현재 scroll height
  useEffect(() => {
    const handleWindowScroll = () => {
      heightRef.current = window.pageYOffset;
      requestAnimationFrame(() => {
        setWindowHeight(heightRef.current);
      });
    };

    window.addEventListener('scroll', handleWindowScroll);
    return () => window.removeEventListener('scroll', handleWindowScroll);
  }, []);

  return (
    <>
      <MainBanner />
      <Container
        fixed
        sx={{ paddingTop: '56px', display: { xs: 'none', lg: 'flex' } }}
      >
        <Grid container>
          <MoaGrid
            item
            xs={12}
            sx={{
              position: 'relative',
              willChange: 'left',
              left:
                windowHeight < 600
                  ? `calc(1200px - ${windowHeight * 2}px)`
                  : '0',
            }}
          >
            <MoaTypography id="title">추천 프로젝트 섹션</MoaTypography>
            <CardList cards={recoProject} type={'team'}></CardList>
          </MoaGrid>
          <MoaGrid
            item
            xs={12}
            sx={{
              position: 'relative',
              willChange: 'left',
              left:
                windowHeight < 1200
                  ? `calc(2400px - ${windowHeight * 2}px)`
                  : '0',
            }}
          >
            <MoaTypography id="title">추천 스터디 섹션</MoaTypography>
            <CardList cards={recoStudy} type={'team'}></CardList>
          </MoaGrid>
          <MoaGrid
            item
            xs={12}
            sx={{
              position: 'relative',
              willChange: 'left',
              left:
                windowHeight < 1600
                  ? `calc(3200px - ${windowHeight * 2}px)`
                  : '0',
            }}
          >
            <MoaTypography id="title">추천 팀원 섹션</MoaTypography>
            <CardList cards={recoMember} type={'member'}></CardList>
          </MoaGrid>
        </Grid>
      </Container>
      {/* 반응형 md 이하 */}
      <Container
        fixed
        sx={{ paddingTop: '56px', display: { xs: 'flex', lg: 'none' } }}
      >
        <Grid container>
          <MoaGrid item xs={12}>
            <MoaTypography id="title">금주의 추천 프로젝트 섹션</MoaTypography>
            <CardList cards={recoProject} type={'team'}></CardList>
          </MoaGrid>
          <MoaGrid
            item
            xs={12}
            sx={{ borderTop: '1px solid #888', paddingTop: '3rem' }}
          >
            <MoaTypography id="title">금주의 추천 스터디 섹션</MoaTypography>
            <CardList cards={recoStudy} type={'team'}></CardList>
          </MoaGrid>
          <MoaGrid
            item
            xs={12}
            borderTop={1}
            paddingTop={4}
            sx={{ borderTop: '1px solid #888', paddingTop: '3rem' }}
          >
            <MoaTypography id="title">금주의 추천 팀원 섹션</MoaTypography>
            <CardList cards={recoMember} type={'member'}></CardList>
          </MoaGrid>
        </Grid>
      </Container>
      <Footer></Footer>
    </>
  );
}

const MoaGrid = styled(Grid)`
  min-height: 200px;
`;

const MoaTypography = styled(Typography)`
  font-size: x-large;
  font-weight: 900;
  margin-bottom: 16px;
`;
