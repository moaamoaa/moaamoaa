import * as React from 'react';
import {
  styled,
  InputBase,
  Button,
  Menu,
  MenuItem,
  Stack,
  TextField,
  Box,
  Grid,
} from '@mui/material/';
import SearchIcon from '@mui/icons-material/Search';
import IconButton from '@mui/material/IconButton';
import CustomSelectFilter from './CustomSelectFilter';

const BootstrapInput = styled(InputBase)(({ theme }) => ({
  'label + &': {
    marginTop: theme.spacing(3),
  },
  '& .MuiInputBase-input': {
    borderRadius: 4,
    position: 'relative',
    backgroundColor: theme.palette.background.paper,
    border: '1px solid #ced4da',
    fontSize: 16,
    padding: '10px 26px 10px 12px',
    transition: theme.transitions.create(['border-color', 'box-shadow']),
    // Use the system font instead of the default Roboto font.
    fontFamily: [
      '-apple-system',
      'BlinkMacSystemFont',
      '"Segoe UI"',
      'Roboto',
      '"Helvetica Neue"',
      'Arial',
      'sans-serif',
      '"Apple Color Emoji"',
      '"Segoe UI Emoji"',
      '"Segoe UI Symbol"',
    ].join(','),
    '&:focus': {
      borderRadius: 4,
      borderColor: '#80bdff',
      boxShadow: '0 0 0 0.2rem rgba(0,123,255,.25)',
    },
  },
}));

const CommonBox = styled(Box)`
  background-color: #ffffff;
  border: 1px solid #c4c4c4;
  border-radius: 0.5rem;
  height: 4rem;
  width: 100%;
`;

export default function CustomizedSelects() {
  const filterDrops = [
    {
      title: '기술스택',
      menus: ['백엔드', '프론트엔드', '모바일', '기타'],
    },
    {
      title: '모집구분',
      menus: ['전체', '프로젝트', '스터디'],
    },
    {
      title: '진행방식',
      menus: ['전체', '온라인', '오프라인'],
    },
  ];

  return (
    <>
      <Grid spacing={2}>
        <Grid item xs={6}>
          <IconButton type="submit" sx={{ p: '10px' }} aria-label="search">
            <SearchIcon />
          </IconButton>

          <TextField
            fullWidth
            id="outlined-basic"
            label="검색"
            variant="outlined"
          />
        </Grid>

        <Grid item xs={2}>
          <Stack
            spacing={2}
            direction="row"
            sx={{ display: 'flex', justifyContent: 'space-between' }}
          >
            {filterDrops.map((filterDrop, idx) => {
              return <CustomSelectFilter key={idx} filterDrop={filterDrop} />;
            })}
          </Stack>
        </Grid>
      </Grid>

      <Box sx={{ height: 88 }}></Box>

      <Stack
        spacing={2}
        direction="row"
        sx={{ display: 'flex', justifyContent: 'space-between' }}
      >
        <CommonBox></CommonBox>
        <IconButton type="submit" sx={{ p: '10px' }} aria-label="search">
          <SearchIcon />
        </IconButton>
      </Stack>
    </>
  );
}
