import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  teamInfo: {
    title: null,
    leader: null,
    img: null,
    areaId: null,
    category: null,
    contents: null,
    endDate: null,
    projectId: null,
    projectStatus: null,
    totalPeople: null,
    userid: null,
  },
  techStacks: [{ id: null, name: null, logo: null }],
};

const teamSlice = createSlice({
  name: 'profile',
  initialState: initialStateValue,
  reducers: {
    teamOpenSuccess: (state, action) => {
      console.log(action);
      state.teamInfo = action.payload.teamInfo;
      state.techStacks = action.payload.techStacks;
    },
    teamCloseSuccess: state => {
      state.teamInfo = null;
      state.techStacks = null;
    },
  },
});

export const { teamOpenSuccess, teamCloseSuccess } = teamSlice.actions;

export default teamSlice.reducer;
