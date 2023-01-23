import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import TeamMemberSearchList from 'components/common/card/TeamMemberSearchList';
import Grid from '@mui/material/Grid';

const theme = createTheme();

export default function TeamSearchPage() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Container maxWidth="lg">
        <main>
          <Grid>
            검색 결과
            <TeamMemberSearchList></TeamMemberSearchList>
          </Grid>
        </main>
      </Container>
    </ThemeProvider>
  );
}
