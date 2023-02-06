import React from 'react';
import { Container, Link } from '@mui/material/';
import TeamSearchList from 'components/common/card/TeamSearchList';
import CustomSelect from 'components/team/CustomSelect';

export default function TeamSearchPage() {
  return (
    <>
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
    </>
  );
}
