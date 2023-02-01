import * as React from 'react';
import { forwardRef, useEffect } from 'react';

import dayjs from 'dayjs';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';

const Calendar = forwardRef((props, ref) => {
  const today = new Date();
  const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
  const formatted = today.toLocaleDateString('en-US', options);
  const [value, setValue] = React.useState(dayjs(formatted));

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
          label="For desktop"
          value={value}
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
