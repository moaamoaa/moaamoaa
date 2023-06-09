import * as React from 'react';
import { forwardRef } from 'react';

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

const SingleSelectOnOff = forwardRef((props, ref) => {
  const [select, setSelect] = React.useState('');

  const handleChange = event => {
    setSelect(event.target.value);
    ref.current = event.target.value;
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">
          온라인 혹은 오프라인을 선택해주세요.
        </InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={select}
          label="Select"
          onChange={handleChange}
        >
          <MenuItem value={'ONLINE'}>온라인</MenuItem>
          <MenuItem value={'OFFLINE'}>오프라인</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
});
export default SingleSelectOnOff;
