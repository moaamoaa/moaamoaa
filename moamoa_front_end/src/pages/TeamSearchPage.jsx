import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
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

import { useNavigate } from 'react-router-dom';
import { handleCursorId } from 'redux/search';
import CardList from 'components/common/card/CardList';

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
    const delTechNameList = techNameList
      .slice()
      .filter(name => name !== removeItem);
    const delStackId = stackId.slice().filter(id => id !== removeItem.id);
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
  // const [check, setCheck] = useState(false);
  //redux
  const tech = useSelector(state => state.search.tech);
  const isLogged = useSelector(state => state.user.isLogged);

  // reponse data로 넘어오는 값을 자식에게 넘겨줌
  const [searchResult, setSearchResult] = useState([]);

  // 무한 스크롤 마지막 커서
  const cursorId = useSelector(state => state.search.cursorId);

  const dispatch = useDispatch();

  useEffect(() => {
    console.log(`첫 useEffect ${cursorId}`);
    customAxios.basicAxios
      .get('/search/project?&size=12&sort=hit,desc')
      .then(response => {
        console.log(response);
        setSearchResult(response.data);
        dispatch(handleCursorId({ cursorId: response.data[11].cursorId }));
      })
      .catch(error => {
        console.log(error.data);
      });
    // setCheck(true);
  }, []);

  // 무한스크롤
  const [isFetching, setIsFetching] = useState(false);

  // 만약 스크롤 높이의 0.8이 넘었을 때 isFetching를 true로 바꿈
  const handleScroll = () => {
    if (
      window.innerHeight + window.scrollY >=
      document.body.offsetHeight * 0.8
    ) {
      setIsFetching(true);
    }
  };

  // 화면이 랜더링될 때 돔에 스크롤 이벤트가 발생했을 때, 이벤트 삭제함
  useEffect(() => {
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  // 화면 랜더됐을 때 스크롤 내릴 시 마지막 커서를 백으로 보내서 get
  useEffect(() => {
    console.log(`그다음 useEffect ${cursorId}`);
    axiosStackId = stackId.join(',');
    if (!isFetching) return;
    customAxios.basicAxios
      .get(
        `/search/project?&stack=${axiosStackId}&category=${category}&status=${status}&area=${region}&query=${query}&sort=hit,desc&size=12&cursorId=${cursorId}`,
      )
      .then(response => {
        console.log(searchResult);
        console.log(response.data);
        // 이 둘이 다를 경우, concat으로 추가시키기 (조건문)
        // if (Set(searchResult.data) !== Set(response.data)) {
        setSearchResult(searchResult.concat(...response.data));
        setIsFetching(false);
        // }
        dispatch(
          handleCursorId({
            cursorId: response.data[response.data.length - 1].cursorId,
          }),
        );
      })
      .catch(error => {
        console.log(error.data);
      });
  }, [isFetching]);

  // 검색 axios

  const search = () => {
    axiosStackId = stackId.join(',');
    // console.log(axiosStackId);
    if (window.event.keyCode === 13) {
      customAxios.basicAxios
        .get(
          `/search/project?&stack=${axiosStackId}&category=${category}&status=${status}&area=${region}&query=${query}&sort=hit,desc`,
        )
        .then(response => {
          setSearchResult([...response.data]);
          dispatch(
            handleCursorId({
              cursorId: response.data[response.data.length - 1].cursorId,
            }),
          );
          setIsFetching(false);
        })
        .catch(error => {
          console.log(error);
        });
    } else {
      customAxios.basicAxios
        .get(
          `/search/project?&stack=${axiosStackId}&category=${category}&status=${status}&area=${region}&query=${query}&sort=hit,desc`,
        )
        .then(response => {
          setSearchResult([...response.data]);
          dispatch(
            handleCursorId({
              cursorId: response.data[response.data.length - 1].cursorId,
            }),
          );
          setIsFetching(false);
        })
        .catch(error => {
          console.log(error);
        });
    }
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
          <div onKeyUp={search}>
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
        <Box sx={{ paddingTop: '1rem' }}>
          <SearchFilterOffline
            handleRegion={handleRegion}
          ></SearchFilterOffline>
        </Box>
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
        <CardList cards={searchResult} type="team"></CardList>
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
