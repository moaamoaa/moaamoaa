import { useState } from 'react';
import { useSelector } from 'react-redux';

import styled from '@emotion/styled';
import { Typography, Grid, Container, IconButton } from '@mui/material';

import AddIcon from '@mui/icons-material/Add';
import SideProject from 'components/profile/SideProject';

import SideProjectEditor from 'components/profile/SideProjectEditor';

export default function SideProjectContainer() {
  const [isAdd, setIsAdd] = useState(false);

  const sideProjects = useSelector(state => state.profile.sideProjects);

  const handleAddSideProject = () => {
    setIsAdd(true);
  };

  console.log(sideProjects);

  if (isAdd) {
    return (
      <>
        <ContentTitle color="initial">주요 프로젝트</ContentTitle>
        <MoaContainer container>
          <SideProjectEditor setIsAdd={setIsAdd}></SideProjectEditor>
        </MoaContainer>
      </>
    );
  } else {
    return (
      <>
        <ContentTitle color="initial">주요 프로젝트</ContentTitle>
        <MoaContainer container sx={{ padding: '0 !important' }}>
          <Container sx={{ padding: '0 !important' }}>
            {sideProjects ? (
              sideProjects.map((sideProject, idx) => (
                <SideProject
                  key={idx}
                  idx={idx}
                  sideProject={sideProject}
                ></SideProject>
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
