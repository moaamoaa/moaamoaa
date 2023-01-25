import * as React from 'react';
import Container from '@mui/material/Container';
import TeamSearchList from 'components/common/card/TeamSearchList';

export default function TeamSearchPage() {
  return (
    <Container maxWidth="lg">
      <TeamSearchList></TeamSearchList>
    </Container>
  );
}
