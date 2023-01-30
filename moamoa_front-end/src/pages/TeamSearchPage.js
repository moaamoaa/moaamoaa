import * as React from 'react';
import Container from '@mui/material/Container';
import TeamSearchList from 'components/common/card/TeamSearchList';
import CustomSelect from 'components/common/option/CustomSelect';

export default function TeamSearchPage() {
  return (
    <p>
      <Container fixed sx={{ py: 4 }}>
        <CustomSelect></CustomSelect>
      </Container>
      <Container fixed sx={{ py: 4 }}>
        <TeamSearchList></TeamSearchList>
      </Container>
    </p>
  );
}
