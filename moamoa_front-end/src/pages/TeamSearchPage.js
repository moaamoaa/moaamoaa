import { React, useEffect, useState } from 'react';
import { Container, Link } from '@mui/material/';
import TeamSearchList from 'components/common/card/TeamSearchList';
import CustomSelect from 'components/team/CustomSelect';

import CustomAxios from 'utils/axios';
import { searchState } from 'redux/search';
import { useDispatch } from 'react-redux';

export default function TeamSearchPage() {
  // redux
  const dispatch = useDispatch();

  // axios
  useEffect(() => {
    CustomAxios.basicAxios
      .get('/search')
      .then(response => {
        console.log(response);
        const areas = response.data.areas;
        const techs = response.data.techs;
        dispatch(
          searchState({
            area: areas,
            tech: techs,
            menu: '',
          }),
        );
      })
      .catch(error => {
        console.log(error);
      });
  });

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
