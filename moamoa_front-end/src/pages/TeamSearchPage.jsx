import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import {
  Container,
  Box,
  styled,
  Button,
  Grid,
  Chip,
  Avatar,
} from '@mui/material/';
import customAxios from 'utils/axios';
// 검색 상단 컴포넌트
import SearchFilterCategory from 'components/team/searchFilter/SearchFilterCategory';
import SearchFilterStatus from 'components/team/searchFilter/SearchFilterSatus';
import SearchFilterTech from 'components/team/searchFilter/SearchFilterTech';
import TeamSearchbar from 'components/team/searchFilter/TeamSearchbar';
import SearchFilterOffline from 'components/team/searchFilter/SearchFilterOffline';

import TeamSearchList from 'components/common/card/TeamSearchList';
import { useNavigate } from 'react-router-dom';

export default function TeamSearchPage(props) {
  // 팀생성 링크
  const navigate = useNavigate();
  const goToCreate = () => {
    if (isLogged === false) {
      alert('로그인이 필요한 서비스입니다');
    } else {
      navigate('/TeamCreatePage');
    }
  };

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
  const handleSearchStack = (event, value) => {
    const newTechNameList = [...techNameList, event];
    const newStackId = [...stackId, event.id];
    setTechNameList([...new Set(newTechNameList)]);
    setStackId([...new Set(newStackId)]);
    // console.log(value);
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
  const isLogged = useSelector(state => state.user.isLogged);

  // reponse data로 넘어오는 값을 자식에게 넘겨줌
  const [searchResult, setSearchResult] = useState([]);
  const [check, setCheck] = useState(false);

  useEffect(() => {
    customAxios.basicAxios
      .get('/search/project?&size=12')
      .then(response => {
        setSearchResult(response.data);
        console.log(response);
      })
      .catch(error => {
        console.log(error.data);
      });
    setCheck(true);
  }, [check]);

  const search = () => {
    axiosStackId = stackId.join(',');
    console.log(axiosStackId);
    customAxios.basicAxios
      .get(
        `/search/project?&stack=${axiosStackId}&category=${category}&status=${status}&area=${region}&query=${query}`,
      )
      .then(response => {
        setSearchResult(response.data);
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
    <Container fixed sx={{ paddingTop: '4rem' }}>
      <Button onClick={goToCreate}>팀을 생성하시겠습니까?</Button>
      <Grid container spacing={2}>
        <Grid item xs={4}>
          <div onKeyPress={search}>
            <TeamSearchbar handleQuery={handleQuery}></TeamSearchbar>
          </div>
        </Grid>
        <Grid item xs={8} container spacing={1}>
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

      <CommonBox direction="row" sx={{ paddingTop: '1rem' }}>
        {filterArray.map((techstack, idx) => (
          <Chip
            variant="outlined"
            label={techstack.name}
            avatar={<Avatar alt="logo" src={techstack.logo} />}
            key={techstack.id}
            direction="row"
            sx={{
              display: 'inline-flex',
              justifyContent: 'space-between',
              margin: 1,
            }}
            onClick={() => {
              handleSearchStack(techstack);
            }}
          />
        ))}
      </CommonBox>

      <Box direction="row" style={{ display: 'flex', paddingTop: '1rem' }}>
        <SearchBox direction="row">
          {techNameList.length !== 0 &&
            techNameList.map(name => {
              return (
                <Chip
                  label={name.name}
                  variant="outlined"
                  key={name.id}
                  value={name}
                  sx={{ margin: 1 }}
                  onDelete={() => {
                    removeTechNameList(name);
                  }}
                />
              );
            })}
        </SearchBox>
        <Button
          variant="contained"
          onClick={search}
          sx={{ borderRadius: '0 .5rem .5rem 0' }}
        >
          검색
        </Button>
      </Box>
      <Container sx={{ paddingTop: '4rem', paddingX: '0 !important' }}>
        <TeamSearchList cards={searchResult}></TeamSearchList>
      </Container>
    </Container>
  );
}

const CommonBox = styled(Box)`
  background-color: #ffffff;
  height: 100%;
  width: 100%;
  // 이건 박스 안에 맞게 줄바꿈해주는 css
  flex-flow: row-reverse wrap;
`;

const SearchBox = styled(CommonBox)`
  background-color: #ffffff;
  border: 1px solid #c4c4c4;
  border-radius: 0.5rem 0 0 0.5rem;
  height: 100%;
  min-height: 3rem;
  width: 100%;
  // 이건 박스 안에 맞게 줄바꿈해주는 css
  flex-flow: row-reverse wrap;
`;
