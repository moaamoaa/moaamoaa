import { useState, useEffect } from 'react';
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

export default function SideProject() {
  const [context, setContext] = useState('');

  const [isAdd, setIsAdd] = useState(false);

  const SideProjects = useSelector(state => state.profile.SideProjects);
  const techStacks = useSelector(state => state.profile.techStacks);

  const handleAddSidProject = () => {
    setIsAdd(true);
  };

  const handleChangeContext = event => {
    if (context.length <= 100) {
      setContext(event.target.value);
    } else {
      setContext(context.slice(0, 200));
    }
  };

  if (isAdd) {
    return (
      <>
        <ContentTitle color="initial">주요 프로젝트</ContentTitle>
        <MoaContainer container>
          <Grid item container alignItems="start">
            <Grid item xs={1} md={1}>
              <SideProjectYear></SideProjectYear>
            </Grid>
            <Grid item xs={10} md={11}>
              <TextField
                fullWidth
                id="standard-multiline-flexible"
                placeholder="프로젝트 이름"
                InputProps={{
                  disableUnderline: true,
                }}
                variant="standard"
              />
              <Grid container>
                <TechStackSeletor></TechStackSeletor>
              </Grid>

              <TextField
                variant="standard"
                fullWidth
                autoFocus
                multiline
                maxRows={4}
                InputProps={{
                  disableUnderline: true,
                }}
                onChange={handleChangeContext}
                placeholder="프로젝트에 대한 자세한 설명을 작성해 주세요."
              >
                {context}
              </TextField>
            </Grid>
          </Grid>
          <IconButton
            onClick={handleAddSidProject}
            sx={{ position: 'absolute', right: '.5rem', top: '.5rem' }}
          >
            <CreateIcon />
          </IconButton>
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
