import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import styled from '@emotion/styled';

import { Container, Grid, TextField, Typography } from '@mui/material';
import LongMenu from 'components/profile/LongMenu';
import customAxios from 'utils/axios';
import { contextEditSuccess } from 'redux/profile';

export default function SelfIntroduction() {
  const userProfile = useSelector(state => state.profile.userProfile[0]);
  const [isEdit, setIsEdit] = useState(false);
  const [context, setContext] = useState(
    userProfile.context ? userProfile.context : '',
  );

  const dispatch = useDispatch();
  const handleOpenEdit = () => {
    setIsEdit(true);
  };

  const handleChangeContext = event => {
    if (context.length <= 100) {
      setContext(event.target.value);
    } else {
      setContext(context.slice(0, 100));
    }
  };

  const handleCloseEdit = () => {};

  const handleSuccessEdit = () => {
    customAxios.authAxios
      .put(`/profile/context/${userProfile.id}`, {
        context: context,
      })
      .then(response => {
        console.log(response);
        dispatch(contextEditSuccess({ context: response.data.context }));
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
    <>
      <ContentTitle color="initial">자기 소개</ContentTitle>
      <MoaContainer spacing={0}>
        <Grid container spacing={0}>
          <Grid item xs={11}>
            {isEdit ? (
              <TextField
                variant="standard"
                fullWidth
                autoFocus
                multiline
                InputProps={{
                  disableUnderline: true,
                }}
                onChange={handleChangeContext}
                placeholder="자신을 소개해 보세요."
                value={context}
              ></TextField>
            ) : (
              <Typography variant="body1" color="initial">
                {userProfile.context
                  ? userProfile.context
                  : `안녕하세요. ${userProfile.nickname}입니다.`}
              </Typography>
            )}
          </Grid>
          <Grid item xs={1}>
            <LongMenu
              isEdit={isEdit}
              handleOpenEdit={handleOpenEdit}
              handleCloseEdit={handleCloseEdit}
              handleSuccessEdit={handleSuccessEdit}
              handleCancelEdit={handleCancelEdit}
            ></LongMenu>
            {isEdit ? (
              <Typography variant="caption" color="gray">
                {context.length}/100
              </Typography>
            ) : (
              <></>
            )}
          </Grid>
        </Grid>
      </MoaContainer>
    </>
  );
}
const ContentTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.5em;

  margin-bottom: 0.5rem;
`;

const MoaContainer = styled(Container)`
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
