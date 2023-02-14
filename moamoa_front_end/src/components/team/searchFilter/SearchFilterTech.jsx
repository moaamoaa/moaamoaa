import React, { useState } from 'react';
import { Box, InputLabel, MenuItem, FormControl, Select } from '@mui/material/';

export default function SearchFilterTech(props) {
  const [tech, setTech] = useState('');
  const handleTechstack = event => {
    setTech(event.target.value);
    console.log(event.target.value);
    props.handleTechstack(event.target.value);
  };
  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">기술스택</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={tech}
          label="tech"
          onChange={handleTechstack}
        >
          <MenuItem value={'BackEnd'}>백엔드</MenuItem>
          <MenuItem value={'FrontEnd'}>프론트엔드</MenuItem>
          <MenuItem value={'Mobile'}>모바일</MenuItem>
          <MenuItem value={'Etc'}>기타</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}
