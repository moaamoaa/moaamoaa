import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import TeamSearchList from 'components/common/card/TeamSearchList';

const theme = createTheme();

export default function TeamSearchPage() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Container maxWidth="lg">
        <main>
          <>
            <TeamSearchList></TeamSearchList>
          </>
        </main>
      </Container>
    </ThemeProvider>
  );
}
