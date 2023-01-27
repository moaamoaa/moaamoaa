import * as React from 'react';
import Container from '@mui/material/Container';
import TeamSearchList from 'components/common/card/TeamSearchList';

export default function TeamSearchPage() {
  return (
    <Container fixed sx={{ py: 4 }}>
      <TeamSearchList></TeamSearchList>
    </Container>
  );
}
