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
    {
      nickname: '',
      profileOnOffStatus: 'ALL',
      sites: [
        { link: '', name: 'github' },
        { link: '', name: 'tistory' },
        { link: '', name: 'velog' },
        { link: '', name: 'project' },
      ],
      areas: [],
      techStacks: [],
    },
  ],
  sideProjects: [],
  techStacks: [],
};

const profileSlice = createSlice({
  name: 'profile',
  initialState: initialStateValue,
  reducers: {
    handleProfilePk: (state, action) => {
      state.userProfile[0].id = action.payload.id;
      state.areas = [];
      state.reviews = [];
      state.sites = [];

      state.sideProjects = [];
      state.techStacks = [];
    },
    handleSuccessContext: (state, action) => {
      state.userProfile[0].context = action.payload.context;
    },
    handleSuccessSidProject: (state, action) => {
      state.sideProjects = action.payload.sideProjects;
    },
    handleSuccessReview: (state, action) => {
      state.reviews = action.payload.reviews;
    },
    handleChangeState: (state, action) => {
      state.userProfile[1].img = action.payload.img;
      state.userProfile[1].nickname = action.payload.nickname;
      state.userProfile[1].profileOnOffStatus =
        action.payload.profileOnOffStatus;
    },
    handleEditProfile: (state, action) => {
      state.userProfile[1].areas = action.payload.areas;
      state.userProfile[1].sites = action.payload.sites;
      state.userProfile[1].techStacks = action.payload.techStacks;
      state.userProfile[1].profileOnOffStatus =
        action.payload.profileOnOffStatus;
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
      state.techStacks = action.payload.profile.techstacks;
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
    searchStatusChange: (state, action) => {
      if (action.payload.profileSearchStatus === 'ALL') {
        state.userProfile[0].profileSearchStatus = 'PROJECT';
      } else if (action.payload.profileSearchStatus === 'PROJECT') {
        state.userProfile[0].profileSearchStatus = 'STUDY';
      } else if (action.payload.profileSearchStatus === 'STUDY') {
        state.userProfile[0].profileSearchStatus = 'NONE';
      } else if (action.payload.profileSearchStatus === 'NONE') {
        state.userProfile[0].profileSearchStatus = 'ALL';
      }
    },
    handleSearchStatus: (state, action) => {
      state.userProfile[0].profileSearchStatus = action.payload.searchstatus;
    },
  },
});

export const {
  handleSuccessSidProject,
  handleSuccessContext,
  handleProfilePk,
  handleSuccessReview,
  handleEditProfile,
  handleEditReview,
  profileOpenSuccess,
  handleChangeState,
  profileCloseSuccess,
  profileEditSuccess,
  searchStatusChange,
  handleSearchStatus,
} = profileSlice.actions;

export default profileSlice.reducer;
