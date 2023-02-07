import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  areas: [],
  reviews: [],
  sites: [],
  userProfile: [
    {
      context: '',
      id: 0,
      img: '',
      nickname: '',
      profileOnOffStatus: '',
      profileSearchStatus: '',
    },
  ],
  sideProjects: [],
  techStacks: [],
};

const profileSlice = createSlice({
  name: 'profile',
  initialState: initialStateValue,
  reducers: {
    changeProfilePk: (state, action) => {
      console.log(action.payload.id);
      state.userProfile[0].id = action.payload.id;
    },
    profileOpenSuccess: (state, action) => {
      state.areas = action.payload.profile.areas;
      state.reviews = action.payload.profile.reviews;
      state.sites = action.payload.profile.sites;

      state.userProfile[0].context = action.payload.profile.profile.context;
      state.userProfile[0].id = action.payload.profile.profile.id;
      state.userProfile[0].img = action.payload.profile.profile.img;
      state.userProfile[0].nickname = action.payload.profile.profile.nickname;
      state.userProfile[0].profileOnOffStatus =
        action.payload.profile.profile.profileOnOffStatus;
      state.userProfile[0].profileSearchStatus =
        action.payload.profile.profile.profileSearchStatus;

      state.sideProjects = action.payload.profile.sideprojects;
      state.techStacks = action.payload.profile.techStacks;
    },
    profileCloseSuccess: state => {
      state.areas = null;
      state.reviews = null;
      state.sites = null;

      state.userProfile[0].context = null;
      state.userProfile[0].id = null;
      state.userProfile[0].img = null;
      state.userProfile[0].nickname = null;
      state.userProfile[0].profileOnOffStatus = null;
      state.userProfile[0].profileSearchStatus = null;

      state.sideProjects = null;
      state.techStacks = null;
    },
    profileEditSuccess: (state, action) => {
      state.areas = action.payload.areas;
      state.sites = action.payload.sites;

      state.userProfile[0].img = action.payload.userProfile.img;
      state.userProfile[0].nickname = action.payload.userProfile.nickname;
      state.userProfile[0].profileOnOffStatus =
        action.payload.userProfile.profileOnOffStatus;

      state.sideProjects = action.payload.userProfile;
    },
    contextEditSuccess: (state, action) => {
      state.userProfile[0].context = action.payload.context;
    },
  },
});

export const {
  changeProfilePk,
  profileOpenSuccess,
  profileCloseSuccess,
  profileEditSuccess,
  contextEditSuccess,
} = profileSlice.actions;

export default profileSlice.reducer;
