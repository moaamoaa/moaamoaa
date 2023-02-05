import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  userProfile: {
    userPk: null,
    nickName: null,
    profileSearchStatus: 'all',
    img: null,
    context: '안녕하세요.',
  },
  sideProject: [{ name: null, context: null, year: null }],
  review: [{ name: null, context: null, time: null }],
};

const profileSlice = createSlice({
  name: 'profile',
  initialState: initialStateValue,
  reducers: {
    profileOpenSuccess: (state, action) => {
      state.userProfile = action.payload.userProfile;
      state.sideProject = action.payload.sideProject;
      state.review = action.payload.review;
    },
    profileCloseSuccess: state => {
      state.userProfile = null;
      state.sideProject = null;
      state.review = null;
    },
  },
});

export const { profileOpenSuccess, profileCloseSuccess } = profileSlice.actions;

export default profileSlice.reducer;
