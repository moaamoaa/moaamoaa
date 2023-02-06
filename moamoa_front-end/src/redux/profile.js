import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  areas: [],
  reviews: [],
  sideProject: [
    {
      year: new Date().getFullYear(),
      title: '프로젝트명',
      techStacks: ['HTML', 'CSS', 'JavaScript'],
      context: '프로젝트소개',
    },
  ],
  sites: [],
  techStacks: [],
  userProfile: {
    context: '안녕하세요.',
    userPk: null,
    nickName: '싸피인',
    profileOnOffStatus: 'online',
    profileSearchStatus: 'all',
    img: null,
  },
};

const profileSlice = createSlice({
  name: 'profile',
  initialState: initialStateValue,
  reducers: {
    profileOpenSuccess: (state, action) => {
      state.areas = action.payload.areas;
      state.reviews = action.payload.reviews;
      state.sideProject = action.payload.sideProject;
      state.sites = action.payload.sites;
      state.techStacks = action.payload.techStacks;

      state.userProfile.context = action.payload.userProfile.context;
      state.userProfile.userPk = action.payload.userProfile.id;
      state.userProfile.img = action.payload.userProfile.img;
      state.userProfile.profileOnOffStatus =
        action.payload.userProfile.profileOnOffStatus;
      state.userProfile.nickName = action.payload.userProfile.nickname;
      state.userProfile.profileSearchStatus =
        action.payload.userProfile.profileSearchStatus;
    },
    profileCloseSuccess: state => {},
  },
});

export const { profileOpenSuccess, profileCloseSuccess } = profileSlice.actions;

export default profileSlice.reducer;
