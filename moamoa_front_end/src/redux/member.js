import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
  memberId: null,
};

const memberSlice = createSlice({
  name: 'member',
  initialState: initialStateValue,
  reducers: {
    handleMemberId: (state, action) => {
      state.memberId = action.payload.memberId;
    },
  },
});

export const { handleMemberId } = memberSlice.actions;

export default memberSlice.reducer;
