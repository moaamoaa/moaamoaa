import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@mui/material/Typography';

export default function SelfIntroduction() {
  const context = useSelector(state => state.profile.userProfile.context);
  console.log(context);
  return (
    <Typography variant="body1" color="initial">
      {context}
    </Typography>
  );
}
