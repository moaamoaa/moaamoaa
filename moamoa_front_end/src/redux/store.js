import { configureStore, combineReducers } from '@reduxjs/toolkit';
import storage from 'redux-persist/lib/storage';
import { persistStore, persistReducer } from 'redux-persist';

import userReducer from 'redux/user';
import searchReducer from 'redux/search';
import profileReducer from 'redux/profile';
import teamReducer from 'redux/team';
import snackReducer from 'redux/snack';

const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['user', 'search', 'profile', 'team', 'snack'],
};

const reducers = combineReducers({
  user: userReducer,
  search: searchReducer,
  profile: profileReducer,
  team: teamReducer,
  snack: snackReducer,
});

const persistedReducer = persistReducer(persistConfig, reducers);

const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

const persistor = persistStore(store);

export default { store, persistor };
