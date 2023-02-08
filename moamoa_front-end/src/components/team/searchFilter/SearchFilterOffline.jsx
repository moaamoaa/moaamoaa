import React, { useState } from 'react';
import { Box, InputLabel, MenuItem, FormControl, Select } from '@mui/material/';

export default function SearchFilterOffline(props) {
  const [region, setregion] = useState('');
  const handleRegion = event => {
    setregion(event.target.value);
    console.log(event.target.value);
    props.handleRegion(event.target.value);
  };
  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">지역선택</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={region}
          label="region"
          onChange={handleRegion}
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
}
