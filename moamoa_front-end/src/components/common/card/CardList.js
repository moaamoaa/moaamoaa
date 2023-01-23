import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
// import CardItem from 'components/common/card/CardItem';
import axios from 'axios';
import { useState, useEffect } from 'react';

// 표시할 카드 개수 지정
const cards = [1, 2, 3];

const theme = createTheme();

export default function CardList() {
  // axios api 3가지 방법
  const [cards, setCards] = useState([]);
  // useEffect(() => {
  //   axios({
  //     method: 'GET',
  //     url: 'https://jsonplaceholder.typicode.com/photos',
  //   }).then(response => setCards(response.data));
  // });
  useEffect(() => {
    axios
      .get('https://jsonplaceholder.typicode.com/photos')
      .then(response => setCards(response.data));
  });
  // useEffect(async () => {
  //   try {
  //     const response = await axios.get(
  //       'https://jsonplaceholder.typicode.com/photos',
  //     );
  //     setCards(response.data);
  //   } catch (error) {
  //     console.log(error);
  //   }
  // });
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
              <Grid item key={card} xs={12} sm={6} md={4}>
                <li key={card.id}>
                  <div>{card.title}</div>
                  <div>
                    <img src={card.thumbnailUrl} />
                  </div>
                </li>
                {/* <CardItem>
                  </CardItem> */}
              </Grid>
            ))}
          </Grid>
        </Container>
      </main>
    </ThemeProvider>
  );
}
