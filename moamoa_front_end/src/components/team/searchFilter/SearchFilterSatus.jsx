import React, { useState } from 'react';
import { Box, InputLabel, MenuItem, FormControl, Select } from '@mui/material/';

export default function SearchFilterStatus(props) {
  const [status, setStatus] = useState('');
  const handleStatus = event => {
    setStatus(event.target.value);
    if (event.target.value === 'all') props.handleStatus('');
    else props.handleStatus(event.target.value);
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">진행방식</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={status}
          label="status"
          onChange={handleStatus}
        >
          <MenuItem value={'all'}>전체</MenuItem>
          <MenuItem value={'ONLINE'}>온라인</MenuItem>
          <MenuItem value={'OFFLINE'}>오프라인</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}
