import * as React from 'react';
import { forwardRef } from 'react';

import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';

const SingleTextField = forwardRef((props, ref) => {
  const handleChange = e => {
    ref.current = e.target.value;
  };
  return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField
        onChange={handleChange}
        id="outlined-basic"
        label="Outlined"
        variant="outlined"
      />
    </Box>
  );
});
export default SingleTextField;
