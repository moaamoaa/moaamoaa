import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  userPk: null,
  isLogged: false,
  error: null,
  userImg: null,
  userNickname: '',
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
      state.userPk = null;
      state.isLogged = false;
    },
    handleUserProfile: (state, action) => {
      state.userImg = action.payload.userImg;
      state.userNickname = action.payload.userNickname;
    },
  },
});

export const { loginSuccess, logoutSuccess, handleUserProfile } =
  userSlice.actions;

export default userSlice.reducer;
