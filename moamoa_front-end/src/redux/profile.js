import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  areas: null,
  reviews: null,
  sites: null,
  userProfile: [
    {
      context: null,
      id: null,
      img: null,
      nickName: null,
      profileOnOffStatus: null,
      profileSearchStatus: null,
    },
  ],
  sideprojects: [{}],
};

const profileSlice = createSlice({
  name: 'profile',
  initialState: initialStateValue,
  reducers: {
    profileOpenSuccess: (state, action) => {
      state = action.payload.curProfile;
    },
    profileCloseSuccess: state => {
      state = null;
    },
    profileEditSuccess: (state, action) => {
      state = action.payload;
    },
    contextEditSuccess: (state, action) => {
      state.userProfile.context = action.payload.context;
    },
  },
});

export const {
  profileOpenSuccess,
  profileCloseSuccess,
  profileEditSuccess,
  contextEditSuccess,
} = profileSlice.actions;

export default profileSlice.reducer;
