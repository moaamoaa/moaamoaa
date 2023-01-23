import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import { useState, useEffect } from 'react';

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CardActions from '@mui/material/CardActions';
import Button from '@mui/material/Button';

// 표시할 카드 개수 지정 방법이 따로 있을 것 같다!
// const cards = [1, 2, 3];
const theme = createTheme();

export default function TeamMemberSearchList() {
  const [cards, setCards] = useState([]);
  useEffect(() => {
    axios
      .get('https://jsonplaceholder.typicode.com/photos')
      .then(response => setCards(response.data));
  });
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AppBar position="relative">
        <Toolbar>
          <Typography variant="h6" color="inherit" noWrap>
            금주의 추천 프로젝트
          </Typography>
        </Toolbar>
      </AppBar>
      <main>
        <Container sx={{ py: 8 }} maxWidth="md">
          <Grid container spacing={4}>
            {cards.map(card => (
              <Grid item key={card.id} xs={12} sm={6} md={4}>
                {/* key 값이 id 값 (중복되지 않는) */}
                <Card
                  sx={{
                    height: '100%',
                    display: 'flex',
                    flexDirection: 'column',
                  }}
                >
                  <CardActions>
                    <Grid container>
                      <Grid item xs>
                        <Button
                          size="small"
                          variant="contained"
                          color="primary"
                          sx={{ display: 'none' }} // 조건에 따라
                        >
                          권한위임
                        </Button>
                      </Grid>
                      <Grid item xs>
                        <Button
                          size="small"
                          variant="contained"
                          color="primary"
                        >
                          강퇴하기 / 제안하기
                        </Button>
                      </Grid>
                    </Grid>
                  </CardActions>
                  <CardMedia>
                    <img
                      sx={{
                        // 16:9
                        pt: '56.25%',
                      }}
                      src={card.thumbnailUrl}
                      alt="random"
                    />
                  </CardMedia>
                  <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                      팀 이름 / 팀원 이름
                      {card.title}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      팀장 이름 / 포지션
                      {card.id}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      조회수 / 없음
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      팀원수 / 없음
                    </Typography>
                    {/* <TechStackList>기술스택리스트</TechStackList> */}
                    <Typography variant="body2" color="text.secondary">
                      팀 소개 / 자기소개
                    </Typography>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </Container>
      </main>
    </ThemeProvider>
  );
}
