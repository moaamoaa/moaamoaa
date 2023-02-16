import * as React from 'react';
import { forwardRef } from 'react';

import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';

const SingleTextField = forwardRef((props, ref) => {
  const handleChange = e => {
    ref.current = e.target.value;
  };
  return (
    <Box component="form" noValidate autoComplete="off">
      <FormControl fullWidth>
        <TextField
          onChange={handleChange}
          id="outlined-basic"
          placeholder="팀이름을 입력해주세요."
          variant="outlined"
          color="primary"
        />
      </FormControl>
    </Box>
  );
});
export default SingleTextField;
