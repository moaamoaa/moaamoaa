import { configureStore, combineReducers } from '@reduxjs/toolkit';
import storage from 'redux-persist/lib/storage';
import { persistStore, persistReducer } from 'redux-persist';

import userReducer from 'redux/user';
import searchReducer from 'redux/search';
import profileReducer from 'redux/profile';
import teamReducer from 'redux/team';
import snackReducer from 'redux/snack';
import memberReducer from 'redux/member';

const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['user', 'search', 'profile', 'team', 'snack', 'member'],
};

const reducers = combineReducers({
  user: userReducer,
  search: searchReducer,
  profile: profileReducer,
  team: teamReducer,
  snack: snackReducer,
  member: memberReducer,
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
