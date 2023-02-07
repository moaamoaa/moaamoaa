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
      state.userProfile[0].id = action.payload.id;
    },
    profileOpenSuccess: (state, action) => {
      state.areas = action.payload.curProfile.areas;
      state.reviews = action.payload.curProfile.reviews;
      state.sites = action.payload.curProfile.sites;

      state.userProfile[0].context = action.payload.curProfile.profile.context;
      state.userProfile[0].id = action.payload.curProfile.profile.id;
      state.userProfile[0].img = action.payload.curProfile.profile.img;
      state.userProfile[0].nickname =
        action.payload.curProfile.profile.nickname;
      state.userProfile[0].profileOnOffStatus =
        action.payload.curProfile.profile.profileOnOffStatus;
      state.userProfile[0].profileSearchStatus =
        action.payload.curProfile.profile.profileSearchStatus;

      state.sideProjects = action.payload.curProfile.sideprojects;
      state.techStacks = action.payload.curProfile.techStacks;
    },
    profileCloseSuccess: state => {
      state.areas = null;
      state.reviews = null;
      state.sites = null;

      state.userProfile.context = null;
      state.userProfile.id = null;
      state.userProfile.img = null;
      state.userProfile.nickname = null;
      state.userProfile.profileOnOffStatus = null;
      state.userProfile.profileSearchStatus = null;

      state.sideProjects = null;
    },
    profileEditSuccess: (state, action) => {
      state.areas = action.payload.areas;
      state.sites = action.payload.sites;

      state.userProfile.img = action.payload.userProfile.img;
      state.userProfile.nickname = action.payload.userProfile.nickname;
      state.userProfile.profileOnOffStatus =
        action.payload.userProfile.profileOnOffStatus;

      state.sideProjects = action.payload.userProfile;
    },
    contextEditSuccess: (state, action) => {
      state.userProfile.context = action.payload.context;
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
