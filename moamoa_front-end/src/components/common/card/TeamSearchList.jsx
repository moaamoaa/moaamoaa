import React from 'react';
import CardList from 'components/common/card/CardList';

export default function TeamSearchList(props) {
  return <CardList cards={props.cards} type="team"></CardList>;
}
