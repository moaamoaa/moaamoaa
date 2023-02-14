import React from 'react';
import CardList from 'components/common/card/CardList';

export default function TeamMemberSearchList(props) {
  console.log(props);

  return <CardList cards={props.cards} type="member"></CardList>;
}
