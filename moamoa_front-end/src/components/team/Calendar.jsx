import * as React from 'react';
import { forwardRef, useEffect } from 'react';

import dayjs from 'dayjs';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';

const Calendar = forwardRef((props, ref) => {
  // 미선택시, 오늘 날짜 띄우기
  const today = new Date();
  const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
  const formatted = today.toLocaleDateString('en-US', options);
  const [value, setValue] = React.useState(dayjs(formatted)); // formatted = 초기값

  // const handleChange = e => {
  //   ref.current = e.target.value;
  // };

  useEffect(() => {
    ref.current = value;
  }, [value, ref]);

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Stack spacing={1}>
        <DesktopDatePicker
          label="달력"
          value={value} // 보내지는 값
          minDate={dayjs('2023-01-01')}
          onChange={newValue => {
            setValue(newValue);
          }}
          renderInput={params => <TextField {...params} />}
        />
      </Stack>
    </LocalizationProvider>
  );
});
export default Calendar;
