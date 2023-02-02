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
import PopupState, { bindTrigger, bindMenu } from 'material-ui-popup-state';
import IconButton from '@mui/material/IconButton';

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
  const [select, setSelect] = React.useState('');
  const handleChange = event => {
    setSelect(event.target.value);
  };

  return (
    <div>
      <Grid container spacing={2}>
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
          <PopupState variant="popover" popupId="demo-popup-menu">
            {popupState => (
              <React.Fragment>
                <Button
                  fullWidth
                  variant="contained"
                  {...bindTrigger(popupState)}
                >
                  기술스택
                </Button>
                <Menu fullWidth {...bindMenu(popupState)}>
                  <MenuItem onClick={popupState.close}>프론트엔드</MenuItem>
                  <MenuItem onClick={popupState.close}>백엔드</MenuItem>
                  <MenuItem onClick={popupState.close}>모바일</MenuItem>
                  <MenuItem onClick={popupState.close}>기타</MenuItem>
                </Menu>
              </React.Fragment>
            )}
          </PopupState>
        </Grid>
        <Grid item xs={2}>
          <PopupState variant="popover" popupId="demo-popup-menu">
            {popupState => (
              <React.Fragment>
                <Button
                  fullWidth
                  variant="contained"
                  {...bindTrigger(popupState)}
                >
                  모집구분
                </Button>
                <Menu fullWidth {...bindMenu(popupState)}>
                  <MenuItem onClick={popupState.close}>프로젝트</MenuItem>
                  <MenuItem onClick={popupState.close}>스터디</MenuItem>
                  <MenuItem onClick={popupState.close}>무관</MenuItem>
                </Menu>
              </React.Fragment>
            )}
          </PopupState>
        </Grid>
        <Grid item xs={2}>
          <PopupState variant="popover" popupId="demo-popup-menu">
            {popupState => (
              <React.Fragment>
                <Button
                  fullWidth
                  variant="contained"
                  {...bindTrigger(popupState)}
                >
                  진행방식
                </Button>
                <Menu fullWidth {...bindMenu(popupState)}>
                  <MenuItem onClick={popupState.close}>오프라인</MenuItem>
                  <MenuItem onClick={popupState.close}>온라인</MenuItem>
                  <MenuItem onClick={popupState.close}>무관</MenuItem>
                </Menu>
              </React.Fragment>
            )}
          </PopupState>
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
    </div>
  );
}
