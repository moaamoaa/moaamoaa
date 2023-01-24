import * as React from 'react';
import dayjs from 'dayjs';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';

export default function ResponsiveDatePickers() {
  const [value, setValue] = React.useState(dayjs('2022-04-07'));

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Stack spacing={1}>
        <DesktopDatePicker
          label="For desktop"
          value={value}
          minDate={dayjs('2017-01-01')}
          onChange={newValue => {
            setValue(newValue);
          }}
          renderInput={params => <TextField {...params} />}
        />
      </Stack>
      <hr></hr>
    </LocalizationProvider>
  );
}
