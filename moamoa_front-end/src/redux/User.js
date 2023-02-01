import { createSlice } from '@reduxjs/toolkit';

// state의 초기값 (유저정보)
const initialStateValue = {
  isLogged: false,
  userPk: null,
};

// userSlice라는 이름으로 유저 Slice 생성
export const UserSlice = createSlice({
  name: 'User',
  initialState: initialStateValue,
  reducers: {
    // 로그인시 액션
    loginAccount(state, action) {
      state.isLogged = true;
      state.userPk = action.payload.userPk;
    },
    // 로그아웃시 액션
    logoutAccount(state) {
      state.isLogged = false;
      state.userPk = null;
    },
  },
});

// dispatch로 액션을 전달해 상태를 어떻게 변화시킬지 결정함.
export const { loginAccount, logoutAccount } = UserSlice.actions;

// reducer
export default UserSlice.reducer;
