import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Container, Box, styled, Button, Grid } from '@mui/material/';
import customAxios from 'utils/axios';
// 검색 상단 컴포넌트
import SearchFilterCategory from 'components/team/searchFilter/SearchFilterCategory';
import SearchFilterStatus from 'components/team/searchFilter/SearchFilterSatus';
import SearchFilterTech from 'components/team/searchFilter/SearchFilterTech';
import MemberSearchbar from 'components/team/searchFilter/MemberSearchbar';
import SearchFilterOffline from 'components/team/searchFilter/SearchFilterOffline';

import TeamMemberSearchList from 'components/common/card/TeamMemberSearchList';

export default function TeamSearchPage(props) {
  // 기술스택 id 리스트를 스트링으로 바꾼 값을 담음
  let axiosStackId = '';
  // usestate
  // 자식에서 받는 값
  const [status, setStatus] = useState('');
  const [techstack, setTechstack] = useState('');
  const [category, setCategory] = useState('');
  const [region, setRegion] = useState('');
  const [query, setQuery] = useState('');

  // axios로 보내줄 기술스택 id값
  const [stackId, setStackId] = useState([]);

  // 기술스택 아이콘 리스트
  const [techNameList, setTechNameList] = useState([]);

  // 기술스택 이름이 box에 쌓임
  const handleSearchStack = event => {
    const newTechNameList = [...techNameList, event];
    const newStackId = [...stackId, event.id];
    setTechNameList([...new Set(newTechNameList)]);
    setStackId([...new Set(newStackId)]);
  };

  // box에 있는 기술 스택 클릭시 제거
  const removeTechNameList = removeItem => {
    const delTechNameList = techNameList.filter(name => name !== removeItem);
    const delStackId = stackId.filter(id => id !== removeItem.id);
    setTechNameList(delTechNameList);
    setStackId(delStackId);
  };

  // 검색바
  const handleQuery = queryChange => {
    setQuery(queryChange);
  };

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

  // redux에 담아둔 tech스택의 array를 저장
  const [filterArray, setFilterArray] = useState([]);

  //redux
  const tech = useSelector(state => state.search.tech);

  const search = () => {
    axiosStackId = stackId.join(',');
    console.log(axiosStackId);
    customAxios.basicAxios
      .get(
        `/search/profile?&stack=${axiosStackId}&category=${category}&status=${status}&area=${region}&query=${query}`,
      )
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
  };

  useEffect(() => {
    if (techstack === 'BackEnd') {
      setFilterArray(tech[0].techStacks);
    } else if (techstack === 'FrontEnd') {
      setFilterArray(tech[1].techStacks);
    } else if (techstack === 'Mobile') {
      setFilterArray(tech[2].techStacks);
    } else if (techstack === 'Etc') {
      setFilterArray(tech[3].techStacks);
    }
  }, [techstack]);

  return (
    <Container fixed sx={{ py: 4 }}>
      <Grid container spacing={2}>
        <Grid item xs={4}>
          <MemberSearchbar handleQuery={handleQuery}></MemberSearchbar>
        </Grid>
        <Grid container item xs={8} spacing={1}>
          <Grid item xs={4}>
            <SearchFilterTech
              handleTechstack={handleTechstack}
            ></SearchFilterTech>
          </Grid>
          <Grid item xs={4}>
            <SearchFilterCategory
              handleCategory={handleCategory}
            ></SearchFilterCategory>
          </Grid>
          <Grid item xs={4}>
            <SearchFilterStatus
              handleStatus={handleStatus}
            ></SearchFilterStatus>
          </Grid>
        </Grid>
      </Grid>
      {status === 'OFFLINE' ? (
        <SearchFilterOffline handleRegion={handleRegion}></SearchFilterOffline>
      ) : (
        <></>
      )}

      <CommonBox direction="row">
        {filterArray.map((techstack, idx) => (
          <Button
            key={techstack.id}
            direction="row"
            sx={{ display: 'inline-flex', justifyContent: 'space-between' }}
            onClick={() => {
              handleSearchStack(techstack);
            }}
          >
            {techstack.logo}
            {techstack.name}
          </Button>
        ))}
      </CommonBox>
      <Box direction="row" style={{ display: 'flex' }}>
        <SearchBox direction="row">
          {techNameList.length !== 0 &&
            techNameList.map(name => {
              return (
                <Button
                  variant="contained"
                  key={name.id}
                  value={name}
                  onClick={() => {
                    removeTechNameList(name);
                  }}
                >
                  {name.name}
                  {name.id}
                </Button>
              );
            })}
        </SearchBox>
        <Button variant="contained" onClick={search}>
          검색
        </Button>
      </Box>

      <Container sx={{ paddingTop: '4rem', paddingX: '0 !important' }}>
        <TeamMemberSearchList></TeamMemberSearchList>
      </Container>
    </Container>
  );
}

const CommonBox = styled(Box)`
  background-color: #ffffff;
  height: 6rem;
  width: 100%;
  // 이건 박스 안에 맞게 줄바꿈해주는 css
  flex-flow: row-reverse wrap;
`;

const SearchBox = styled(CommonBox)`
  background-color: #ffffff;
  border: 1px solid #c4c4c4;
  border-radius: 0.5rem;
  height: 5rem;
  width: 100%;
  // 이건 박스 안에 맞게 줄바꿈해주는 css
  flex-flow: row-reverse wrap;
`;
