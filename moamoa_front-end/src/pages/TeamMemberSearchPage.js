import * as React from 'react';
import Container from '@mui/material/Container';
import TeamMemberSearchList from 'components/common/card/TeamMemberSearchList';
import CustomSelect from 'components/team/CustomSelect';

export default function TeamSearchPage() {
  return (
    <div>
      <Container fixed sx={{ py: 4 }}>
        <CustomSelect></CustomSelect>
      </Container>
      <Container fixed sx={{ py: 4 }}>
        <TeamMemberSearchList></TeamMemberSearchList>
      </Container>
    </div>
  );
}
