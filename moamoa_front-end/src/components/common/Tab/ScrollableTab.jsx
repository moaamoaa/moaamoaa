import { useState } from 'react';

import { Tabs, Box, Tab } from '@mui/material/';
import CardItem from 'components/common/card/CardItem';

export default function ScrollableTab(props) {
  const [value, setValue] = useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Box sx={{ maxWidth: { xs: 320, sm: 480 }, bgcolor: 'background.paper' }}>
      <Tabs
        value={value}
        onChange={handleChange}
        variant="scrollable"
        scrollButtons="auto"
        aria-label="scrollable auto tabs example"
      >
        {props.cards.map(card => (
          <Tab label={<CardItem card={card} type={props.type}></CardItem>} />
        ))}
      </Tabs>
    </Box>
  );
}
