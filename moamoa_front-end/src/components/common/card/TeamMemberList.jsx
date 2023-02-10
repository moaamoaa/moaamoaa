import { useState, useEffect } from 'react';
import CardList from 'components/common/card/CardList';
import axios from 'axios';

export default function TeamMemberList(props) {
  const [cards, setCards] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);

  // axios api 3가지 방법
  useEffect(() => {
    axios
      .get('https://jsonplaceholder.typicode.com/photos')
      .then(response => {
        setCards(response.data.slice(0, 10)); // 팀원 최대 열명
        console.log(response.data.slice(0, 10));
      })
      .catch(error => {
        console.log(error.data);
      });
    setIsLoaded(true);
  }, [isLoaded]);

  return <CardList cards={cards} type="member"></CardList>;
}
