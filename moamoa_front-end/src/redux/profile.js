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
      img: '',
      nickname: '',
      profileOnOffStatus: '',
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
      console.log(action.payload.id);
      state.userProfile[0].id = action.payload.id;
    },
    handleSuccessContext: (state, action) => {
      state.userProfile[0].context = action.payload.context;
    },
    handleSuccessSidProject: (state, action) => {
      state.sideProjects = action.payload.sideProjects;
    },
    handleCreateReview: (state, action) => {
      state.reviews.push(action.payload.review);
    },
    handleEditReview: (state, action) => {
      state.reviews = state.reviews.map(review => {
        if (review.id !== action.payload.review.id) return review;

        return action.payload.review;
      });
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
      console.log(action.payload);
      state.userProfile[1].areas = action.payload.areas;
      state.userProfile[1].sites = action.payload.sites;
      state.userProfile[1].techStacks = action.payload.techStacks;
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
        state.userProfile[0].profileSearchStatus = 'ONLINE';
      } else if (action.payload.profileSearchStatus === 'ONLINE') {
        state.userProfile[0].profileSearchStatus = 'OFFLINE';
      } else if (action.payload.profileSearchStatus === 'OFFLINE') {
        state.userProfile[0].profileSearchStatus = 'NONE';
      } else if (action.payload.profileSearchStatus === 'NONE') {
        state.userProfile[0].profileSearchStatus = 'ALL';
      }
    },
  },
});

export const {
  handleSuccessSidProject,
  handleSuccessContext,
  handleProfilePk,
  handleSuccessReview,
  handleCreateReview,
  handleEditProfile,
  handleEditReview,
  profileOpenSuccess,
  handleChangeState,
  profileCloseSuccess,
  profileEditSuccess,
  searchStatusChange,
} = profileSlice.actions;

export default profileSlice.reducer;
