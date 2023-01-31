import * as React from 'react';
import Container from '@mui/material/Container';
import TeamSearchList from 'components/common/card/TeamSearchList';
import CustomSelect from 'components/common/option/CustomSelect';
import Link from '@mui/material/Link';

export default function TeamSearchPage() {
  return (
    <p>
      <Container fixed sx={{ py: 4 }}>
        <Link href="http://localhost:3000/TeamCreatePage">
          팀을 생성하시겠습니까?
        </Link>
      </Container>
      <Container fixed sx={{ py: 4 }}>
        <CustomSelect></CustomSelect>
      </Container>
      <Container fixed sx={{ py: 4 }}>
        <TeamSearchList></TeamSearchList>
      </Container>
    </p>
  );
}
