import React from 'react';
import {
  configureStore,
  combineReducers,
  getDefaultMiddleware,
} from '@reduxjs/toolkit';
import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';

import UserReducer from './User';
import searchReducer from './search';

const reducers = combineReducers({
  User: UserReducer,
  search: searchReducer,
});
// persisit 설정
const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['User', 'search'],
};
const persistedReducer = persistReducer(persistConfig, reducers);

const Store = configureStore({
  reducer: persistedReducer,
  // non-serializable value was detected 라고 뜨는 에러 처리를 위해 사용
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

export default Store;
