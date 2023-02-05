import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  userPk: null,
  isLogged: false,
  error: null,
};

const userSlice = createSlice({
  name: 'user',
  initialState: initialStateValue,
  reducers: {
    loginSuccess: (state, action) => {
      state.userPk = action.payload.userPk;
      state.isLogged = true;
    },
    logoutSuccess: state => {
      console.log(state);
      state.userPk = null;
      state.isLogged = false;
    },
    loginFailure: (state, action) => {
      state.error = action.payload;
    },
  },
});

export const { loginSuccess, logoutSuccess, loginFailure } = userSlice.actions;

export default userSlice.reducer;
