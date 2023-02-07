import React from 'react';

import { TextField, Grid } from '@mui/material/';
import SearchIcon from '@mui/icons-material/Search';
import IconButton from '@mui/material/IconButton';

function TeamSearchbar() {
  return (
    <div>
      <Grid item xs={6}>
        <IconButton type="submit" sx={{ p: '10px' }} aria-label="search">
          <SearchIcon />
        </IconButton>

        <TextField
          fullWidth
          id="outlined-basic"
          label="검색"
          variant="outlined"
        />
      </Grid>
    </div>
  );
}

export default TeamSearchbar;
