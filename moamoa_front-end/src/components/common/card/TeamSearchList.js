import { useState, useEffect } from 'react';

import Container from '@mui/material/Container';

import axios from 'axios';

import CardList from 'components/common/card/CardList';

export default function TeamSearchList() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [cards, setCards] = useState([]);

  // axios api 3가지 방법
  useEffect(() => {
    if (isLoaded) {
      axios
        .get('https://jsonplaceholder.typicode.com/photos')
        .then(response => {
          setCards(response.data.slice(0, 10));
          console.log(response.data.slice(0, 10));
        })
        .catch(error => {
          console.log(error.data);
        });
    } else {
      setIsLoaded(true);
    }
  }, [isLoaded]);

  // useEffect(() => {
  //   axios({
  //     method: 'GET',
  //     url: 'https://jsonplaceholder.typicode.com/photos',
  //   }).then(response => setCards(response.data));
  // });

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
    <Container sx={{ py: 8 }} maxWidth="md">
      <CardList cards={cards} type={'team'}></CardList>
    </Container>
  );
}
