import * as React from 'react';
import Container from '@mui/material/Container';
import TeamMemberSearchList from 'components/common/card/TeamMemberSearchList';

export default function TeamSearchPage() {
  return (
    <Container fixed>
      <TeamMemberSearchList></TeamMemberSearchList>
    </Container>
  );
}
