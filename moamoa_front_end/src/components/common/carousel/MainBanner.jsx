import { Paper, Typography, Grid, Box } from '@mui/material';
import Link from '@mui/material/Link';

import styled from 'styled-components';
import Container from '@mui/material/Container';
import ScrollIndicator from '../button/ScrollIndicator';

export default function MainBanner() {
  const post = {
    img: `${process.env.PUBLIC_URL}/images/common/banner/main_img.svg`,
    title: '모아모아에 오신 것을 환영합니다.',
    description: '',
    linkText: '깃랩으로 가기',
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
                py: { xs: 3, md: 6 },
                pr: { md: 0 },
              }}
            >
              <Typography
                component="h1"
                variant="h3"
                color="inherit"
                sx={{
                  fontSize: { xs: 24, md: 32, xl: 40 },
                }}
                gutterBottom
              >
                {post.title}
              </Typography>
              <Typography variant="h5" color="inherit" paragraph>
                {post.description}
              </Typography>
              <Link
                variant="subtitle1"
                href="https://lab.ssafy.com/s08-webmobile2-sub2/S08P12B110/-/tree/develop"
              >
                {post.linkText}
              </Link>
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
