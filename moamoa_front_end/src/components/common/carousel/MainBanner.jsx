import { Paper, Typography, Grid, Box } from '@mui/material';
import Link from '@mui/material/Link';

import styled from 'styled-components';
import Container from '@mui/material/Container';
import ScrollIndicator from '../button/ScrollIndicator';

export default function MainBanner() {
  const post = {
    img: `${process.env.PUBLIC_URL}/images/common/banner/main_img.svg`,
    title: 'MoaaMoaa',
    description: '모든 개발자를 위한',
    description_1: '팀 빌딩 서비스 모아모아입니다.',
    linkText: '지금까지 경험하지 못한',
    linkText_1: '최고의 팀과 팀원을 찾아보세요.',
  };

  const yellowCircle = `${process.env.PUBLIC_URL}/images/common/banner/yellow_circle.svg`;
  const blueCircle = `${process.env.PUBLIC_URL}/images/common/banner/blue_circle.svg`;
  const purpleCircle = `${process.env.PUBLIC_URL}/images/common/banner/purple_circle.svg`;
  const mainImg = `${process.env.PUBLIC_URL}/images/common/banner/main_image.svg`;

  return (
    <Paper
      sx={{
        position: 'relative',
        color: '#000',
        mb: 4,
        height: 'calc(200px + 40vw)',
        maxHeight: 'calc(100vh - 56px)',
        borderRadius: 0,
        boxShadow: '0px 0px 0px 0px rgb(0 0 0 / 0%)',
        zIndex: -1,
      }}
    >
      <ScrollIndicator></ScrollIndicator>
      <CJ>
        <YellowCircle src={yellowCircle}></YellowCircle>
        <BlueCircle src={blueCircle}></BlueCircle>
        <PurpleCircle src={purpleCircle}></PurpleCircle>
      </CJ>
      <MainImg src={mainImg}></MainImg>
      {/* <Dim /> */}
      <Container fixed>
        <Grid container>
          <Grid item md={8}>
            <Box
              sx={{
                position: 'relative',
                py: { xs: 3, md: 6, xl: 9 },
                pr: { md: 0 },
              }}
            >
              <Typography
                id="title"
                component="h1"
                variant="h1"
                color="#FDF3D8"
                sx={{
                  fontSize: { xs: 48, md: 64, xl: 120 },
                  textShadow: {
                    xs: '5px 5px 1px #f3d112',
                    md: '10px 10px 1px #f3d112',
                  },
                }}
                gutterBottom
              >
                {post.title}
              </Typography>
              <Typography
                variant="h5"
                color="inherit"
                paragraph
                sx={{
                  fontSize: { xs: 24, md: 36, xl: 40 },
                  fontWeight: '600',
                }}
              >
                {post.description}
              </Typography>
              <Typography
                variant="h5"
                color="inherit"
                paragraph
                sx={{
                  fontSize: { xs: 24, md: 36, xl: 40 },
                  fontWeight: '600',
                }}
              >
                {post.description_1}
              </Typography>
              <Typography
                color="#888"
                sx={{
                  fontSize: { xs: 12, md: 18 },
                }}
              >
                {post.linkText}
              </Typography>
              <Typography
                color="#888"
                sx={{
                  fontSize: { xs: 12, md: 18 },
                }}
              >
                {post.linkText_1}
              </Typography>
            </Box>
          </Grid>
        </Grid>
      </Container>
    </Paper>
  );
}

const CJ = styled(Container)`
  @keyframes move {
    from {
      transform: translate(-50%, -50%) rotate(0deg);
    }
    to {
      transform: translate(-50%, -50%) rotate(360deg);
    }
  }
`;

const YellowCircle = styled.img`
  position: absolute;
  top: calc(40% + 15vw);
  left: calc(30% + 15vw);
  transform: translate(-50%, -50%);
  z-index: -1;
  width: 50vw;
`;

const BlueCircle = styled.img`
  position: absolute;
  top: calc(40% - 10vw);
  left: calc(50% + 15vw);
  transform: translate(-50%, -50%);
  z-index: -1;
  width: 50vw;
`;

const PurpleCircle = styled.img`
  position: absolute;
  top: calc(50% + 15vw);
  left: calc(60% + 15vw);
  transform: translate(-50%, -50%);
  z-index: -1;
  width: 50vw;
`;

const MainImg = styled.img`
  position: absolute;
  z-index: 0;
  top: 60%;
  left: 70%;
  transform: translate(-50%, -50%);
  width: 40vw;
`;

const Dim = styled(Box)`
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.2);
  border-radius: 0;
`;
