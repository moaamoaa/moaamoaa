import { TextField, Button, Container, Grid } from '@mui/material';
import React from 'react';
import { useSelector } from 'react-redux';

function ReviewCreation() {
  return (
    <Grid container>
      <Grid item xs={12}>
        <TextField
          fullWidth
          multiline
          placeholder="댓글을 남겨 보세요."
          rows={3}
        ></TextField>
      </Grid>
      <Grid item xs={12} sx={{ display: 'flex', justifyContent: 'end' }}>
        <Button variant="text" color="primary">
          등록
        </Button>
      </Grid>
    </Grid>
  );
}

export default ReviewCreation;
