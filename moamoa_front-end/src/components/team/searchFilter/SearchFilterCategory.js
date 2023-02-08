import React, { useState } from 'react';
import { Box, InputLabel, MenuItem, FormControl, Select } from '@mui/material/';

export default function SearchFilterCategory(props) {
  const [category, setCategory] = useState('');

  const handleCategory = event => {
    setCategory(event.target.value);
    console.log(event.target.value);
    if (event.target.value === 'all') props.handleCategory('');
    else props.handleCategory(event.target.value);
  };
  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">모집구분</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={category}
          label="category"
          onChange={handleCategory}
        >
          <MenuItem value={'all'}>전체</MenuItem>
          <MenuItem value={'PROJECT'}>프로젝트</MenuItem>
          <MenuItem value={'STUDY'}>스터디</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}
