import React, { useState, useEffect } from 'react';
import customAxios from 'utils/axios';
import CardList from 'components/common/card/CardList';

export default function TeamMemberSearchList(props) {
  const [cards, setCards] = useState([]);

  useEffect(() => {
    console.log(props.searchResult.data);
    if (props.searchResult.data === undefined) {
      customAxios.basicAxios
        .get('/search/profile?&size=12')
        .then(response => {
          setCards(response.data);
          console.log(response.data);
        })
        .catch(error => {
          console.log(error.data);
        });
      setCards([]);
    } else {
      console.log(props.searchResult.data);
      setCards(props.searchResult.data);
      // setIsLoaded(true);
      //이부분이 바로 반영이 안 되는듯
    }
  }, [props]);

  return <CardList cards={cards} type="member"></CardList>;
}
