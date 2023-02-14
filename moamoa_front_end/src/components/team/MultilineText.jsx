import * as React from 'react';
import { forwardRef } from 'react';

import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';

const MultilineText = forwardRef((props, ref) => {
  const handleChange = e => {
    ref.current = e.target.value;
  };
  return (
    <Box component="form" noValidate autoComplete="off">
      <FormControl fullWidth>
        <TextField
          id="outlined-multiline-static"
          placeholder={props.placeholder}
          multiline
          rows={6}
          onChange={handleChange}
        />
      </FormControl>
    </Box>
  );
});
export default MultilineText;
