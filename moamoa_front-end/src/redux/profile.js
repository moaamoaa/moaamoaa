import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  areas: null,
  profile: [
    {
      context: null,
      id: null,
      img: null,
      nickName: null,
      profileOnOffStatus: null,
      profileSearchStatus: null,
    },
  ],
  reviews: null,
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
  },
});

export const { profileOpenSuccess, profileCloseSuccess, profileEditSuccess } =
  profileSlice.actions;

export default profileSlice.reducer;
