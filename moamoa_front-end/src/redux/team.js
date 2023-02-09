import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  areaForm: { id: null, name: null },
  category: null,
  contents: null,
  endDate: null,
  img: null,
  leader: false,
  leaderId: null,
  leaderNickname: null,
  profileResultDtoList: [],
  projectId: null,
  projectStatus: null,
  totalPeople: null,
  projectTechStacks: [{ id: null, name: null, logo: null }],
  startDate: null,
  title: null,
  userid: null,
};

const teamSlice = createSlice({
  name: 'team',
  initialState: initialStateValue,
  reducers: {
    handleOpenTeamDetail: (state, action) => {
      state.projectId = action.payload.projectId;
    },
    handleOpenTeamUpdate: (state, action) => {
      state.projectId = action.payload.projectId;
    },
    teamOpenSuccess: (state, action) => {
      console.log(action);
      state.areaForm = action.payload.areaForm;
      state.category = action.payload.category;
      state.contents = action.payload.contents;
      state.endDate = action.payload.endDate;
      state.img = action.payload.img;
      state.leader = action.payload.leader;
      state.leaderId = action.payload.leaderId;
      state.leaderNickname = action.payload.leaderNickname;
      state.profileResultDtoList = action.payload.profileResultDtoList;
      state.projectId = action.payload.projectId;
      state.projectStatus = action.payload.projectStatus;
      state.totalPeople = action.payload.totalPeople;
      state.projectTechStacks = action.payload.projectTechStacks;
      state.startDate = action.payload.startDate;
      state.title = action.payload.title;
      state.userid = action.payload.userid;
    },
    teamCloseSuccess: state => {
      state.areaForm = null;
      state.category = null;
      state.contents = null;
      state.endDate = null;
      state.img = null;
      state.leader = true;
      state.leaderId = null;
      state.leaderNickname = null;
      state.profileResultDtoList = null;
      state.projectId = null;
      state.projectStatus = null;
      state.totalPeople = null;
      state.projectTechStacks = null;
      state.startDate = null;
      state.title = null;
      state.userid = null;
    },
  },
});

export const {
  handleOpenTeamDetail,
  handleOpenTeamUpdate,
  teamOpenSuccess,
  teamCloseSuccess,
} = teamSlice.actions;

export default teamSlice.reducer;
