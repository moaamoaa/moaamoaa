import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export default function SingleSelectNumber() {
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
          <MenuItem value={10}>1</MenuItem>
          <MenuItem value={20}>2</MenuItem>
          <MenuItem value={30}>3</MenuItem>
          <MenuItem value={40}>4</MenuItem>
          <MenuItem value={50}>5</MenuItem>
          <MenuItem value={60}>6</MenuItem>
          <MenuItem value={70}>7</MenuItem>
          <MenuItem value={80}>8</MenuItem>
          <MenuItem value={90}>9</MenuItem>
          <MenuItem value={100}>10</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
}
