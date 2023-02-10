import React from 'react';
import Grid from '@mui/material/Grid';
import { useState } from 'react';
import customAxios from 'utils/axios';
import { useDispatch } from 'react-redux';
import { handleSuccessEditSidProject } from 'redux/profile';
import styled from '@emotion/styled';
import { Typography } from '@mui/material';
import LongMenu from 'components/profile/LongMenu';

import SideProjectEditor from 'components/profile/SideProjectEditor';

function SideProject(props) {
  const sideProject = props.sideProject;
  const [isEdit, setIsEdit] = useState(false);

  const dispatch = useDispatch();

  const handleOpenEdit = () => {
    setIsEdit(true);
  };
  const handleDelete = () => {};

  const handleSuccessEdit = () => {
    customAxios.authAxios
      .put(`/profile/sidepjt`, {
        context: sideProject.context,
        name: sideProject.name,
        year: sideProject.year,
        pjt_tech_stack: sideProject.selectedValue,
        profileId: sideProject.userProfile.id,
      })
      .then(response => {
        console.log(response);
        dispatch(handleSuccessEditSidProject({ sideProject: response.data }));
        setIsEdit(false);
      })
      .catch(error => {
        console.log(error);
      });
  };

  const handleCancelEdit = () => {
    setIsEdit(false);
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
              handleSuccessEdit={handleSuccessEdit}
              handleCancelEdit={handleCancelEdit}
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
