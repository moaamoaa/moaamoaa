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
        <InputLabel id="demo-simple-select-label">
          진행 방식이 오프라인일 경우 선택해주세요.
        </InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={select}
          label="Select"
          onChange={handleChange}
        >
          <MenuItem value={1}>서울특별시</MenuItem>
          <MenuItem value={2}>부산광역시</MenuItem>
          <MenuItem value={3}>대구광역시</MenuItem>
          <MenuItem value={4}>인천광역시</MenuItem>
          <MenuItem value={5}>광주광역시</MenuItem>
          <MenuItem value={6}>대전광역시</MenuItem>
          <MenuItem value={7}>울산광역시</MenuItem>
          <MenuItem value={8}>세종특별자치시</MenuItem>
          <MenuItem value={9}>경기도</MenuItem>
          <MenuItem value={10}>강원도</MenuItem>
          <MenuItem value={11}>충청북도</MenuItem>
          <MenuItem value={12}>충청남도</MenuItem>
          <MenuItem value={13}>전라북도</MenuItem>
          <MenuItem value={14}>전라남도</MenuItem>
          <MenuItem value={15}>경상북도</MenuItem>
          <MenuItem value={16}>경상남도</MenuItem>
          <MenuItem value={17}>제주특별자치도</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
});
export default SingleSelectRegion;
