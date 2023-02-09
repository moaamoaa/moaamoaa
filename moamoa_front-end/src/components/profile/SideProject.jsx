import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import styled from '@emotion/styled';
import CloseIcon from '@mui/icons-material/Close';
import {
  Typography,
  Grid,
  Container,
  IconButton,
  TextField,
  MenuItem,
  Select,
  FormControl,
  InputLabel,
} from '@mui/material';

import LongMenu from './LongMenu';
import AddIcon from '@mui/icons-material/Add';
import CreateIcon from '@mui/icons-material/Create';
import TechStackSeletor from 'components/profile/TechStackSeletor';
import customAxios from 'utils/axios';
import { handleSuccessSidProject } from 'redux/profile';

export default function SideProject() {
  const [isAdd, setIsAdd] = useState(false);

  const userPk = useSelector(state => state.user.userPk);
  const profile = useSelector(state => state.profile.userProfile);

  const sideProjects = useSelector(state => state.profile.sideProjects);
  const techStacks = useSelector(state => state.profile.techStacks);

  const curYear = new Date().getFullYear();
  const [year, setYear] = useState(curYear);
  const [name, setName] = useState('');
  const [selectedValue, setSelectedValue] = useState(null);
  const [context, setContext] = useState('');
  const handleChangeYear = event => {
    setYear(event.target.value);
  };
  const handleAddSideProject = () => {
    setIsAdd(true);
  };

  const handleCloseAddSideProject = () => {
    setYear(curYear);
    setName('');
    setSelectedValue(null);
    setContext('');
    setIsAdd(false);
  };
  const dispatch = useDispatch();

  const handleCreateSidProject = () => {
    console.log(year, name, selectedValue, context);
    customAxios.authAxios
      .post(`profile/sidepjt/${userPk}`, {
        year: String(year),
        name: name,
        pjt_tech_stack: selectedValue,
        context: context,
      })
      .then(response => {
        console.log(response);
        dispatch(handleSuccessSidProject({ sideProjects: response.data }));
        setIsAdd(false);
      })
      .catch(error => {
        console.log(error);
      });
  };

  const handleChangeName = event => {
    if (name.length <= 16) {
      setName(event.target.value);
    } else {
      setName(name.slice(0, 16));
    }
  };
  const limit = 100;

  const handleChangeContext = event => {
    if (context.length <= limit) {
      setContext(event.target.value);
    } else {
      setContext(context.slice(0, limit));
    }
  };

  console.log(sideProjects);

  if (isAdd) {
    return (
      <>
        <ContentTitle color="initial">주요 프로젝트</ContentTitle>
        <MoaContainer container>
          <Grid item container xs={12} alignItems="start">
            <Grid item xs={3} md={2}>
              <FormControl size="small">
                <InputLabel id="demo-select-small">Year</InputLabel>
                <Select
                  labelId="demo-select-small"
                  id="demo-select-small"
                  value={year}
                  label="Year"
                  onChange={handleChangeYear}
                >
                  <MenuItem value={curYear}>
                    <em>{curYear}</em>
                  </MenuItem>
                  <MenuItem value={curYear - 1}>{curYear - 1}</MenuItem>
                  <MenuItem value={curYear - 2}>{curYear - 2}</MenuItem>
                  <MenuItem value={curYear - 3}>{curYear - 3}</MenuItem>
                  <MenuItem value={curYear - 4}>{curYear - 4}</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={8} md={9}>
              <TextField
                fullWidth
                id="standard-multiline-flexible"
                placeholder="프로젝트 이름"
                onChange={handleChangeName}
                InputProps={{
                  disableUnderline: true,
                }}
                variant="standard"
                value={name}
                sx={{
                  paddingBottom: '1rem',
                }}
              />

              <TechStackSeletor
                setSelectedValue={setSelectedValue}
              ></TechStackSeletor>

              <TextField
                variant="standard"
                fullWidth
                autoFocus
                multiline
                InputProps={{
                  disableUnderline: true,
                }}
                onChange={handleChangeContext}
                placeholder="프로젝트에 대한 자세한 설명을 작성해 주세요."
                value={context}
                sx={{
                  paddingTop: '1rem',
                }}
              />
            </Grid>
            <Grid item xs={1} justifyContent="end" sx={{ display: 'flex' }}>
              <IconButton
                onClick={handleCreateSidProject}
                sx={{ padding: '0' }}
              >
                <CreateIcon />
              </IconButton>
            </Grid>
          </Grid>
          <Grid item xs={12} sx={{ display: 'flex' }} justifyContent="end">
            <Typography
              variant="caption"
              color="initial"
              justifyContent="end"
              sx={{ display: 'flex' }}
            >
              {context.length} / {limit}
            </Typography>
          </Grid>
          <IconButton
            onClick={handleCloseAddSideProject}
            sx={{ position: 'absolute', right: '.5rem', top: '-3rem' }}
          >
            <CloseIcon />
          </IconButton>
        </MoaContainer>
      </>
    );
  } else {
    return (
      <>
        <ContentTitle color="initial">주요 프로젝트</ContentTitle>
        <MoaContainer container sx={{ padding: '0 !important' }}>
          <Container sx={{ padding: '0 !important' }}>
            {sideProjects.length !== 0 ? (
              sideProjects.map((sideProject, idx) => (
                <Grid
                  key={idx}
                  item
                  container
                  alignItems="start"
                  paddingX={2}
                  paddingTop={1}
                  sx={{ backgroundColor: idx % 2 ? '#88888820' : '#none' }}
                >
                  <Grid item xs={1}>
                    <SideProjectYear>{sideProject.year}</SideProjectYear>
                  </Grid>
                  <Grid item xs={10}>
                    <SideProjectTitle variant="body1" color="initial">
                      {sideProject.name}
                    </SideProjectTitle>
                    <Grid container>
                      {sideProject.pjt_tech_stack.map((techStack, idx) => (
                        <TechStackGrid key={idx}>
                          {techStack.name}
                        </TechStackGrid>
                      ))}
                    </Grid>
                    <SideProjectContext>
                      {sideProject.context}
                    </SideProjectContext>
                  </Grid>
                  <Grid
                    item
                    xs={1}
                    justifyContent="end"
                    sx={{ display: 'flex' }}
                  >
                    <ProfileLongMenu></ProfileLongMenu>
                  </Grid>
                </Grid>
              ))
            ) : (
              <Typography
                variant="h6"
                sx={{
                  width: '100%',
                  position: 'absolute',
                  top: '50%',
                  left: '50%',
                  transform: 'translate(-50%, -50%)',
                  fontWeight: '600',
                  opacity: '0.5',
                }}
                textAlign="center"
                gutterBottom
              >
                프로젝트를 추가해 보세요.
              </Typography>
            )}
          </Container>
          <IconButton
            onClick={handleAddSideProject}
            sx={{ position: 'absolute', right: '.5rem', top: '-3rem' }}
          >
            <AddIcon />
          </IconButton>
        </MoaContainer>
      </>
    );
  }
}

const ContentTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.5em;

  margin-bottom: 0.5rem;
`;

const MoaContainer = styled(Grid)`
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;

  width: 100%;
  min-height: 8rem;

  margin-bottom: 4rem;
  padding: 1rem;

  border: 1px solid black;
  border-radius: 5px;
`;

const SideProjectYear = styled(Typography)`
  font-weight: bolder;
  font-size: calc(0.8rem + 0.2vw);

  margin-top: calc(0.2rem + 0.2vw);
  color: gray;
`;

const SideProjectTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.2em;

  margin-bottom: 0.5rem;
`;

const TechStackGrid = styled(Grid)`
  position: relative;
  font-weight: 400;
  font-size: calc(0.1em + 0.5vw);

  background-color: #888888;
  color: #ffffff;

  padding: 0.4rem;
  margin-right: 0.4rem;

  border-radius: 0.5rem;
`;

const SideProjectContext = styled(Typography)`
  position: relative;
  font-weight: 600;
  font-size: 1em;

  margin: 1rem 0;
`;

const ProfileLongMenu = styled(LongMenu)`
  position: absolute;
`;
