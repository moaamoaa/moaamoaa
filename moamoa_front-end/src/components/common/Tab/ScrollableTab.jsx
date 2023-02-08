import { useState } from 'react';

import { Tabs, Tab } from '@mui/material/';
import CardItem from 'components/common/card/CardItem';

// change folder name

export default function ScrollableTab(props) {
  const [value, setValue] = useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Tabs
      value={value}
      onChange={handleChange}
      variant="scrollable"
      scrollButtons="auto"
    >
      {props.cards ? (
        props.cards.map((card, idx) => (
          <Tab
            key={idx}
            label={<CardItem card={card} type={props.type}></CardItem>}
          />
        ))
      ) : (
        <Tab></Tab>
      )}
    </Tabs>
  );
}
