import React, { useState, useEffect } from 'react';
import customAxios from 'utils/axios';
import CardList from 'components/common/card/CardList';

export default function TeamSearchList(props) {
  const [cards, setCards] = useState([]);

  useEffect(() => {
    console.log(props.searchResult.data);
    if (props.searchResult.data === undefined) {
      customAxios.basicAxios
        .get('/search/project?&size=12')
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

  // axios api 3가지 방법
  // useEffect(() => {
  //   axios
  //     .get('https://jsonplaceholder.typicode.com/photos')
  //     .then(response => {
  //       setCards(response.data.slice(0, 10));
  //       console.log(response.data.slice(0, 10));
  //     })
  //     .catch(error => {
  //       console.log(error.data);
  //     });
  //   setIsLoaded(true);
  // }, [isLoaded]);

  return <CardList cards={cards} type="team"></CardList>;
}
