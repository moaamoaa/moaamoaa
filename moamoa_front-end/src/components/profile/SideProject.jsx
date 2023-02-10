import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import customAxios from 'utils/axios';
import { handleSuccessSidProject } from 'redux/profile';
import styled from '@emotion/styled';
import { Typography, Grid } from '@mui/material';
import LongMenu from 'components/profile/LongMenu';

import SideProjectEditor from 'components/profile/SideProjectEditor';

function SideProject(props) {
  const sideProject = props.sideProject;
  const profileId = useSelector(state => state.profile.userProfile[0].id);
  const [isEdit, setIsEdit] = useState(false);

  const dispatch = useDispatch();

  const handleOpenEdit = () => {
    setIsEdit(true);
  };
  const handleDelete = () => {
    customAxios.authAxios
      .delete(`profile/sidepjt/${sideProject.id}`)
      .then(response => {
        console.log(response);
        dispatch(handleSuccessSidProject({ sideProjects: response.data }));
      })
      .catch(error => {
        console.log(error);
      });
  };

  return (
    <Grid
      item
      container
      alignItems="start"
      paddingX={2}
      paddingTop={1}
      sx={{ backgroundColor: props.idx % 2 ? '#88888820' : '#none' }}
    >
      {isEdit ? (
        <>
          <SideProjectEditor
            sideProject={sideProject}
            setIsEdit={setIsEdit}
            isEdit={isEdit}
          ></SideProjectEditor>
        </>
      ) : (
        <>
          <Grid item xs={1}>
            <SideProjectYear>{sideProject.year}</SideProjectYear>
          </Grid>
          <Grid item xs={10}>
            <SideProjectTitle variant="body1" color="initial">
              {sideProject.name}
            </SideProjectTitle>
            <Grid container>
              {sideProject.pjt_tech_stack.map((techStack, idx) => (
                <TechStackGrid key={idx}>{techStack.name}</TechStackGrid>
              ))}
            </Grid>
            <SideProjectContext>{sideProject.context}</SideProjectContext>
          </Grid>
          <Grid item xs={1} justifyContent="end" sx={{ display: 'flex' }}>
            <ProfileLongMenu
              isEdit={isEdit}
              handleOpenEdit={handleOpenEdit}
              handleDelete={handleDelete}
            ></ProfileLongMenu>
          </Grid>
        </>
      )}
    </Grid>
  );
}

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

export default SideProject;
