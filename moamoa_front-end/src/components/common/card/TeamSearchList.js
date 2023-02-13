import React from 'react';
import CardList from 'components/common/card/CardList';

export default function TeamSearchList(props) {
  // console.log(props);
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

  return <CardList cards={props.cards} type="team"></CardList>;
}
