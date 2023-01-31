import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';

export default function MultilineText() {
  return (
    <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '125ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <FormControl fullwidth>
        <TextField
          id="outlined-multiline-static"
          placeholder="팀소개를 입력해주세요."
          multiline
          rows={6}
        />
      </FormControl>
    </Box>
  );
}
