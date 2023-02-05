import { configureStore, combineReducers } from '@reduxjs/toolkit';
import storage from 'redux-persist/lib/storage';
import { persistStore, persistReducer } from 'redux-persist';

import userReducer from 'redux/user';
import searchReducer from 'redux/search';

const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['user', 'search'],
};

const reducers = combineReducers({
  user: userReducer,
  search: searchReducer,
});

const persistedReducer = persistReducer(persistConfig, reducers);

const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});

const persistor = persistStore(store);

export default { store, persistor };
