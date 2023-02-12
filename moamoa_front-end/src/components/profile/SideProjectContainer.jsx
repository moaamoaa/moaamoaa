import { useState } from 'react';
import { useSelector } from 'react-redux';

import styled from '@emotion/styled';
import { Typography, Grid, Container, IconButton } from '@mui/material';

import AddIcon from '@mui/icons-material/Add';
import SideProject from 'components/profile/SideProject';

import SideProjectEditor from 'components/profile/SideProjectEditor';

export default function SideProjectContainer() {
  const userPk = useSelector(state => state.user.userPk);
  const profileId = useSelector(state => state.profile.userProfile[0].id);
  const sideProjects = useSelector(state => state.profile.sideProjects);
  const [isAdd, setIsAdd] = useState(false);
  const flag = userPk === profileId;

  const handleAddSideProject = () => {
    setIsAdd(true);
  };

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
            {sideProjects.length !== 0 ? (
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
                {flag
                  ? '프로젝트를 추가해 보세요.'
                  : '프로젝트가 아직 등록되지 않았습니다.'}
              </Typography>
            )}
          </Container>
          {flag ? (
            <IconButton
              onClick={handleAddSideProject}
              sx={{ position: 'absolute', right: '.5rem', top: '-3rem' }}
            >
              <AddIcon />
            </IconButton>
          ) : (
            <></>
          )}
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
