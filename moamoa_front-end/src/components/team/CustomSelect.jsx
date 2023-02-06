import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import {
  styled,
  InputBase,
  Stack,
  TextField,
  Box,
  Grid,
  Container,
  Avatar,
} from '@mui/material/';
import SearchIcon from '@mui/icons-material/Search';
import IconButton from '@mui/material/IconButton';
import CustomSelectFilter from './CustomSelectFilter';

const BootstrapInput = styled(InputBase)(({ theme }) => ({
  'label + &': {
    marginTop: theme.spacing(3),
  },
  '& .MuiInputBase-input': {
    borderRadius: 4,
    position: 'relative',
    backgroundColor: theme.palette.background.paper,
    border: '1px solid #ced4da',
    fontSize: 16,
    padding: '10px 26px 10px 12px',
    transition: theme.transitions.create(['border-color', 'box-shadow']),
    // Use the system font instead of the default Roboto font.
    fontFamily: [
      '-apple-system',
      'BlinkMacSystemFont',
      '"Segoe UI"',
      'Roboto',
      '"Helvetica Neue"',
      'Arial',
      'sans-serif',
      '"Apple Color Emoji"',
      '"Segoe UI Emoji"',
      '"Segoe UI Symbol"',
    ].join(','),
    '&:focus': {
      borderRadius: 4,
      borderColor: '#80bdff',
      boxShadow: '0 0 0 0.2rem rgba(0,123,255,.25)',
    },
  },
}));

export default function CustomizedSelects() {
  const menu = useSelector(state => state.search.menu);
  const area = useSelector(state => state.search.area);
  const tech = useSelector(state => state.search.tech);
  console.log(menu);
  const [filterArray, setFilterArray] = useState([]);
  const [category, setCategory] = useState('');
  const [searchList, setSearchList] = useState([]);
  const handlersearchInput = logo => {
    const newSearchList = [...searchList, logo.name];
    setSearchList([...new Set(newSearchList)]);
  };

  const removeSearchList = removeItem => {
    const delSearchList = searchList.filter((m, a) => m !== removeItem);
    setSearchList(delSearchList);
  };
  useEffect(() => {
    if (
      menu === '전체' ||
      menu === '프로젝트' ||
      menu === '스터디' ||
      menu === '상관없음' ||
      menu === '온라인'
    ) {
      const newSearchList = [...searchList, menu];
      setSearchList([...new Set(newSearchList)]);
    }
  }, [menu]);

  const filterDrops = [
    {
      title: '기술스택',
      menus: ['백엔드', '프론트엔드', '모바일', '기타'],
    },
    {
      title: '모집구분',
      menus: ['전체', '프로젝트', '스터디'],
    },
    {
      title: '진행방식',
      menus: ['상관없음', '온라인', '오프라인'],
    },
  ];
  useEffect(() => {
    if (menu === '백엔드') {
      setCategory('back-end_icons');
      setFilterArray(tech[0].techStacks);
    } else if (menu === '프론트엔드') {
      setCategory('front-end_icons');
      setFilterArray(tech[1].techStacks);
    } else if (menu === '모바일') {
      setCategory('mobile_icons');
      setFilterArray(tech[2].techStacks);
    } else if (menu === '기타') {
      setCategory('ect_icons');
      setFilterArray(tech[3].techStacks);
    }
  }, [menu]);

  return (
    <Container>
      <Grid>
        <Grid item xs={6}>
          <IconButton type="submit" sx={{ p: '10px' }} aria-label="search">
            <SearchIcon />
          </IconButton>

          <TextField
            fullWidth
            id="outlined-basic"
            label="검색"
            variant="outlined"
          />
        </Grid>

        <Grid item xs={2}>
          <Stack
            spacing={2}
            direction="row"
            sx={{ display: 'flex', justifyContent: 'space-between' }}
          >
            {filterDrops.map((filterDrop, idx) => {
              return <CustomSelectFilter key={idx} filterDrop={filterDrop} />;
            })}
          </Stack>
        </Grid>
      </Grid>

      <CommonBox direction="row">
        {menu === '오프라인'
          ? area.map((v, idx) => {
              return (
                <div
                  key={idx}
                  onClick={() => {
                    handlersearchInput(v);
                  }}
                >
                  {v.name}
                </div>
              );
            })
          : filterArray.map((logo, idx) => (
              <Stack
                key={logo.name}
                onClick={() => {
                  handlersearchInput(logo);
                }}
                direction="row"
                sx={{ display: 'inline-flex', justifyContent: 'space-between' }}
              >
                <MoaImg
                  key={idx}
                  src={`${process.env.PUBLIC_URL}/images/tech-stack_icons/${category}/${logo.logo}@4x.png`}
                />
                <span>{logo.name}</span>
              </Stack>
            ))}
      </CommonBox>

      <Stack
        spacing={2}
        direction="row"
        sx={{ display: 'flex', justifyContent: 'space-between' }}
      >
        <CommonBox>
          {searchList.length !== 0 &&
            searchList.map(v => {
              return (
                <div
                  key={v}
                  onClick={() => {
                    removeSearchList(v);
                  }}
                >
                  {v}
                </div>
              );
            })}
        </CommonBox>

        <IconButton type="submit" sx={{ p: '10px' }} aria-label="search">
          <SearchIcon />
        </IconButton>
      </Stack>
    </Container>
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

const MoaImg = styled(Avatar)`
  min-width: 40px;
  min-height: 40px;
  box-shadow: 0px 4px 5px rgba(0, 0, 0, 0.25);
`;
