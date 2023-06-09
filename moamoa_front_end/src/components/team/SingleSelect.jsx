import * as React from 'react';
import { forwardRef } from 'react';

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

const SingleSelect = forwardRef((props, ref) => {
  // const handleChange = e => {
  //   ref.current = e.target.value;
  // };

  const [select, setSelect] = React.useState('');

  const handleChange = event => {
    setSelect(event.target.value);
    ref.current = event.target.value;
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">
          프로젝트 혹은 스터디를 선택해주세요.
        </InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={select}
          label="Select"
          onChange={handleChange}
        >
          <MenuItem value={'PROJECT'}>프로젝트</MenuItem>
          <MenuItem value={'STUDY'}>스터디</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
});
export default SingleSelect;
