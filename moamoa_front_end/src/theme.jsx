import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: '#006FFF',
    },
    secondary: {
      main: '#FDC500',
    },
  },
  typography: {
    fontFamily: "'Noto Sans KR', sans-serif",
    h2: {
      fontSize: '2rem',
      textAlign: 'center',
      fontWeight: 'bold',
      marginBottom: 8,
    },
    caption: {
      fontSize: '0.8rem',
    },
  },
});

export default theme;
