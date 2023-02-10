import { createSlice } from '@reduxjs/toolkit';

// 리덕스에 array 형태로 다 넣을 수 있음
const initialStateValue = {
  open: false,
  vertical: 'top',
  horizontal: 'rigth',
  message: '',
  severity: 'success',
};

const snackSlice = createSlice({
  name: 'snack',
  initialState: initialStateValue,
  reducers: {
    handleSuccessState: (state, action) => {
      state.open = action.payload.open;
      state.message = action.payload.message;
    },
  },
});

export const { handleSuccessState } = snackSlice.actions;

export default snackSlice.reducer;
