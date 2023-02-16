import {
  Paper,
  Typography,
  Link,
  Grid,
  Container,
  AvatarGroup,
  Avatar,
  Fade,
} from '@mui/material';
import { useEffect, useState } from 'react';

function Footer() {
  const [isAtBottom, setIsAtBottom] = useState(false);

  useEffect(() => {
    const handleWindowScroll = () => {
      if (
        window.innerHeight + window.pageYOffset >=
        document.body.offsetHeight
      ) {
        setIsAtBottom(true);
      } else {
        setIsAtBottom(false);
      }
    };

    window.addEventListener('scroll', handleWindowScroll);
    return () => window.removeEventListener('scroll', handleWindowScroll);
  }, [isAtBottom]);

  return (
    <Paper
      sx={{
        height: '400px',
        backgroundColor: '#f5f5f7',
        display: 'flex',
        alignItems: 'end',
      }}
    >
      <Fade in={isAtBottom}>
        <Container fixed>
          <Grid container paddingY={4}>
            <Grid container marginBottom={2}>
              <Grid item xs={12}>
                개발자
              </Grid>
              <AvatarGroup max={6}>
                <Avatar
                  alt="김동욱"
                  src={`${process.env.PUBLIC_URL}/images/common/developer/dong.png`}
                  sx={{
                    width: 56,
                    height: 56,
                    backgroundColor: '#fff',
                    boxShadow: 2,
                  }}
                />
                <Avatar
                  alt="유지연"
                  src={`${process.env.PUBLIC_URL}/images/common/developer/you.png`}
                  sx={{
                    width: 56,
                    height: 56,
                    backgroundColor: '#fff',
                    boxShadow: 2,
                  }}
                />
                <Avatar
                  alt="임성빈"
                  src={`${process.env.PUBLIC_URL}/images/common/developer/bin.png`}
                  sx={{
                    width: 56,
                    height: 56,
                    backgroundColor: '#fff',
                    boxShadow: 2,
                  }}
                />
                <Avatar
                  alt="장유하"
                  src={`${process.env.PUBLIC_URL}/images/common/developer/ha.png`}
                  sx={{
                    width: 56,
                    height: 56,
                    backgroundColor: '#fff',
                    boxShadow: 2,
                  }}
                />
                <Avatar
                  alt="정여진"
                  src={`${process.env.PUBLIC_URL}/images/common/developer/jung.png`}
                  sx={{
                    width: 56,
                    height: 56,
                    backgroundColor: '#fff',
                    boxShadow: 2,
                  }}
                />
                <Avatar
                  alt="황다솔"
                  src={`${process.env.PUBLIC_URL}/images/common/developer/da.png`}
                  sx={{
                    width: 56,
                    height: 56,
                    backgroundColor: '#fff',
                    boxShadow: 2,
                  }}
                />
              </AvatarGroup>
            </Grid>
            <Grid
              item
              xs={12}
              display={'flex'}
              alignItems={'flex-end'}
              marginBottom={2}
            >
              <Typography variant="body1" color="initial">
                문제를 발견한 경우
              </Typography>
              <Link
                href="mailto:
        moamoaofficial0@gmail.com"
                sx={{ textDecoration: 'none' }}
              >
                MoaaMoaa_official
              </Link>
              <Typography variant="body1" color="initial">
                로 메일을 보내주세요.
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="caption" color="initial">
                Copyright © 2023 SSAFY Inc. All rights reserved.
              </Typography>
            </Grid>
          </Grid>
        </Container>
      </Fade>
    </Paper>
  );
}

export default Footer;
