import { configureStore, combineReducers } from '@reduxjs/toolkit';
import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';

import userReducer from 'redux/user';
import searchReducer from 'redux/search';

const reducers = combineReducers({
  user: userReducer,
  search: searchReducer,
});

// persisit 설정
const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['user', 'search'],
};
const persistedReducer = persistReducer(persistConfig, reducers);

const store = configureStore({
  reducer: persistedReducer,
  // non-serializable value was detected 라고 뜨는 에러 처리를 위해 사용
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

export default store;
