import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { Container, Link, Box, styled } from '@mui/material/';
// 검색 상단 컴포넌트
import SearchFilterCategory from 'components/team/searchFilter/SearchFilterCategory';
import SearchFilterStatus from 'components/team/searchFilter/SearchFilterSatus';
import SearchFilterTech from 'components/team/searchFilter/SearchFilterTech';
import TeamSearchbar from 'components/team/searchFilter/TeamSearchbar';
import SearchFilterOffline from 'components/team/searchFilter/SearchFilterOffline';

import TeamSearchList from 'components/common/card/TeamSearchList';

export default function TeamSearchPage(props) {
  //usestate
  const [status, setStatus] = useState('');
  const [techstack, setTechstack] = useState('');
  const [category, setCategory] = useState('');
  const [region, setRegion] = useState('');

  // 진행방식
  const handleStatus = statusChange => {
    setStatus(statusChange);
  };
  // 기술스택
  const handleTechstack = techstackChange => {
    setTechstack(techstackChange);
  };
  // 모집구분
  const handleCategory = categoryChange => {
    setCategory(categoryChange);
  };
  // 오프라인일 때 지역선택
  const handleRegion = regionChange => {
    setRegion(regionChange);
  };

  //redux
  const area = useSelector(state => state.search.area);
  const tech = useSelector(state => state.search.tech);

  const check = () => {
    console.log(status);
  };

  return (
    <>
      <Container fixed sx={{ py: 4 }}>
        <Link href="http://localhost:3000/TeamCreatePage">
          팀을 생성하시겠습니까?
        </Link>
      </Container>

      <Container>
        <TeamSearchbar></TeamSearchbar>
        <SearchFilterTech handleTechstack={handleTechstack}></SearchFilterTech>
        <SearchFilterCategory
          handleCategory={handleCategory}
        ></SearchFilterCategory>
        <SearchFilterStatus handleStatus={handleStatus}></SearchFilterStatus>
        {status === 'offline' ? (
          <SearchFilterOffline
            handleRegion={handleRegion}
          ></SearchFilterOffline>
        ) : (
          <></>
        )}
        <CommonBox></CommonBox>
      </Container>
      <Container fixed sx={{ py: 4 }}>
        <TeamSearchList></TeamSearchList>
      </Container>
    </>
  );
}

const CommonBox = styled(Box)`
  background-color: #ffffff;
  border: 1px solid #c4c4c4;
  border-radius: 0.5rem;
  height: 8rem;
  width: 100%;
  // 이건 박스 안에 맞게 줄바꿈해주는 css
  flex-flow: row-reverse wrap;
`;
