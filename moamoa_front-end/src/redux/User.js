import { createSlice } from '@reduxjs/toolkit';

// state의 초기값 (유저정보)
const initialStateValue = {
  userPk: null,
  isLogged: false,
  error: null,
};

// userSlice라는 이름으로 유저 Slice 생성
const userSlice = createSlice({
  name: 'user',
  initialState: initialStateValue,
  reducers: {
    // 로그인시 액션
    loginSuccess: (state, action) => {
      state.userPk = action.payload.userPk;
      state.isLogged = true;
    },
    // 로그아웃시 액션
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

// dispatch로 액션을 전달해 상태를 어떻게 변화시킬지 결정함.
export const { loginSuccess, logoutSuccess, loginFailure } = userSlice.actions;

// reducer
export default userSlice.reducer;
