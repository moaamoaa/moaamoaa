import React from 'react';

import { TextField } from '@mui/material/';

function TeamSearchbar(props) {
  const handleQuery = event => {
    props.handleQuery(event.target.value);
  };
  return (
    <TextField
      fullWidth
      id="outlined-basic"
      label="검색"
      variant="outlined"
      onChange={handleQuery}
    />
  );
}

export default TeamSearchbar;
