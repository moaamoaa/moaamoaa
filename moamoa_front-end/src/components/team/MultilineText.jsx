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
    <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '125ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <FormControl>
        <TextField
          id="outlined-multiline-static"
          placeholder="팀소개를 입력해주세요."
          multiline
          rows={6}
          onChange={handleChange}
        />
      </FormControl>
    </Box>
  );
});
export default MultilineText;
