import { useState, useEffect } from 'react';

import axios from 'axios';

import Container from '@mui/material/Container';
import CardList from 'components/common/card/CardList';

export default function TeamMemberSearchList() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [cards, setCards] = useState([]);

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

  return <CardList cards={cards} type={'member'}></CardList>;
}
