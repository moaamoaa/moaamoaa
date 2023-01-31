import { createSlice } from '@reduxjs/toolkit';

// state의 초기값 (유저정보)
const initalState = {
  isLogged: false,
  userNickname: '',
  userPk: '',
};

// userSlice라는 이름으로 유저 Slice 생성
export const userSlice = createSlice({
  name: 'User',
  initalState,
  reducers: {
    // 로그인시 액션
    loginAccount(state, action) {
      state.isLogged = true;
      state.userNickname = action.payload.userNickname;
      state.userPk = action.payload.userPk;
    },
    // 로그아웃시 액션
    logoutAccount(state) {
      state.isLogged = false;
      state.userNickname = '';
      state.userPk = '';
    },
  },
});

// dispatch로 액션을 전달해 상태를 어떻게 변화시킬지 결정함.
export const { loginAccount, logoutAccount } = userSlice.actions;

// reducer
export default userSlice.reducer;
