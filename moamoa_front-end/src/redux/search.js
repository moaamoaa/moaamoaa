import { createSlice } from '@reduxjs/toolkit';

// 리덕스에 array 형태로 다 넣을 수 있음
const initialStateValue = {
  area: [],
  tech: [],
  menu: '',
};

const searchSlice = createSlice({
  name: 'search',
  initialState: initialStateValue,
  reducers: {
    searchState: (state, action) => {
      state.area = action.payload.area;
      state.tech = action.payload.tech;
      state.menu = action.payload.menu;
    },
  },
});

export const { searchState } = searchSlice.actions;

export default searchSlice.reducer;
