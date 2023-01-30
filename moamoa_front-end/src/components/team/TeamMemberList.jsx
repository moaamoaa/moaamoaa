import { useState, useEffect } from 'react';
import axios from 'axios';
import TeamMemberCard from 'components/team/TeamMemberCard';

export default function TeamMemberList() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [cards, setCards] = useState([]);

  useEffect(() => {
    if (isLoaded) {
      axios
        .get('https://jsonplaceholder.typicode.com/photos')
        .then(response => {
          setCards(response.data.slice(0, 4));
          console.log(response.data.slice(0, 4));
        })
        .catch(error => {
          console.log(error.data);
        });
    } else {
      setIsLoaded(true);
    }
  }, [isLoaded]);

  return <TeamMemberCard cards={cards} type={'member'}></TeamMemberCard>;
}
