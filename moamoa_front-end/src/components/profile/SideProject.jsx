import { useState } from 'react';
import { useSelector } from 'react-redux';

import styled from '@emotion/styled';

import {
  Typography,
  Grid,
  Container,
  IconButton,
  TextField,
} from '@mui/material';

import LongMenu from './LongMenu';
import AddIcon from '@mui/icons-material/Add';
import CreateIcon from '@mui/icons-material/Create';
import TechStackSeletor from 'components/profile/TechStackSeletor';
import customAxios from 'utils/axios';

export default function SideProject() {
  const [isAdd, setIsAdd] = useState(false);

  const profileId = useSelector(state => state.user.userPk);

  const SideProjects = useSelector(state => state.profile.SideProjects);
  const techStacks = useSelector(state => state.profile.techStacks);

  const [year, setYear] = useState(String(new Date().getFullYear()));
  const [name, setName] = useState('');
  const [selectedValue, setSelectedValue] = useState(null);
  const [context, setContext] = useState(
    SideProjects ? SideProjects[0].context : '',
  );

  const handleChangeName = event => {
    setName(event.target.value);
  };

  const handleAddSidProject = () => {
    setIsAdd(true);
  };

  const handleCreateSidProject = () => {
    console.log(year, name, selectedValue, context);
    customAxios.authAxios
      .post(`profile/sidepjt/${profileId}`, {
        year: year,
        name: name,
        pjt_tech_stack: selectedValue,
        context: context,
      })
      .then(response => {
        console.log(response);
        setIsAdd(false);
      })
      .catch(error => {
        console.log(error);
      });
  };

  const limit = 100;

  const handleChangeContext = event => {
    console.log(context, context.length);
    if (context.length <= limit) {
      setContext(event.target.value);
    } else {
      setContext(context.slice(0, limit));
    }
  };

  if (isAdd) {
    return (
      <>
        <ContentTitle color="initial">주요 프로젝트</ContentTitle>
        <MoaContainer container>
          <Grid item container xs={12} alignItems="start">
            <Grid item xs={1}>
              <SideProjectYear></SideProjectYear>
            </Grid>
            <Grid item xs={10}>
              <TextField
                fullWidth
                id="standard-multiline-flexible"
                placeholder="프로젝트 이름"
                onChange={handleChangeName}
                minRows={3}
                InputProps={{
                  disableUnderline: true,
                }}
                variant="standard"
                value={name}
              />
              <Grid container>
                <TechStackSeletor
                  setSelectedValue={setSelectedValue}
                ></TechStackSeletor>
              </Grid>

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
        </MoaContainer>
      </>
    );
  } else {
    return (
      <>
        <ContentTitle color="initial">주요 프로젝트</ContentTitle>
        <MoaContainer container>
          {SideProjects ? (
            SideProjects.map((SideProject, idx) => {
              <Grid key={idx} item container alignItems="start">
                <Grid item xs={1} md={1}>
                  <SideProjectYear>{SideProject.year}</SideProjectYear>
                </Grid>
                <Grid item xs={10} md={11}>
                  <SideProjectTitle variant="body1" color="initial">
                    {SideProject.title}
                  </SideProjectTitle>
                  <Grid container>
                    {techStacks ? (
                      techStacks.map((techStack, idx) => (
                        <TechStackGrid key={idx}>{techStack}</TechStackGrid>
                      ))
                    ) : (
                      <>
                        <TechStackGrid>HTML</TechStackGrid>
                        <TechStackGrid>CSS</TechStackGrid>
                        <TechStackGrid>JavaScript</TechStackGrid>
                      </>
                    )}
                  </Grid>
                  <SideProjectContext>프로젝트 소개</SideProjectContext>
                </Grid>
                <Grid item xs={1} justifyContent="end" sx={{ display: 'flex' }}>
                  <ProfileLongMenu></ProfileLongMenu>
                </Grid>
              </Grid>;
            })
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
          <IconButton
            onClick={handleAddSidProject}
            sx={{ position: 'absolute', right: '.5rem', bottom: '.5rem' }}
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
  font-size: 1.2rem;

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
  font-size: 0.8em;

  background-color: #888;
  color: #ffffff;

  padding: 0.5rem;
  margin-right: 0.5rem;

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
