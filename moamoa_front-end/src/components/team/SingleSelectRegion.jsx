import * as React from 'react';
import { forwardRef } from 'react';

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

const SingleSelectRegion = forwardRef((props, ref) => {
  const [select, setSelect] = React.useState('');

  const handleChange = event => {
    setSelect(event.target.value);
    ref.current = event.target.value;
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">선택</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={select}
          label="Select"
          onChange={handleChange}
        >
          <MenuItem value={'서울'}>서울특별시</MenuItem>
          <MenuItem value={'대전'}>대전광역시</MenuItem>
          <MenuItem value={'광주'}>광주광역시</MenuItem>
          <MenuItem value={'대구'}>대구광역시</MenuItem>
          <MenuItem value={'세종'}>세종특별자치시</MenuItem>
          <MenuItem value={'제주'}>제주특별자치도</MenuItem>
          <MenuItem value={'부산'}>부산광역시</MenuItem>
          <MenuItem value={'울산'}>울산광역시</MenuItem>
          <MenuItem value={'인천'}>인천광역시</MenuItem>
          <MenuItem value={'경기'}>경기도</MenuItem>
          <MenuItem value={'강원'}>강원도</MenuItem>
          <MenuItem value={'충남'}>충청남도</MenuItem>
          <MenuItem value={'충북'}>충청북도</MenuItem>
          <MenuItem value={'전북'}>전라북도</MenuItem>
          <MenuItem value={'전남'}>전라남도</MenuItem>
          <MenuItem value={'경북'}>경상북도</MenuItem>
          <MenuItem value={'경남'}>경상남도</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
});
export default SingleSelectRegion;
