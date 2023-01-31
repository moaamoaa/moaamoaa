import React from 'react';
import { configureStore } from '@reduxjs/toolkit';
import userReducer from './User';

export default configureStore({
  reducer: {
    User: userReducer,
  },
});
