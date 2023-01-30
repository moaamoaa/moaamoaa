import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export default function SingleSelectRegion() {
  const [select, setSelect] = React.useState('');

  const handleChange = event => {
    setSelect(event.target.value);
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Select</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={select}
          label="Select"
          onChange={handleChange}
        >
          <MenuItem value={10}>서울특별시</MenuItem>
          <MenuItem value={20}>대전광역시</MenuItem>
          <MenuItem value={30}>광주광역시</MenuItem>
          <MenuItem value={40}>대구광역시</MenuItem>
          <MenuItem value={50}>세종특별자치시</MenuItem>
          <MenuItem value={60}>제주특별자치도</MenuItem>
          <MenuItem value={70}>부산광역시</MenuItem>
          <MenuItem value={80}>울산광역시</MenuItem>
          <MenuItem value={90}>인천광역시</MenuItem>
          <MenuItem value={100}>경기도</MenuItem>
          <MenuItem value={110}>강원도</MenuItem>
          <MenuItem value={120}>충청남도</MenuItem>
          <MenuItem value={130}>충청북도</MenuItem>
          <MenuItem value={140}>전라북도</MenuItem>
          <MenuItem value={150}>전라남도</MenuItem>
          <MenuItem value={160}>경상북도</MenuItem>
          <MenuItem value={170}>경상남도</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}
