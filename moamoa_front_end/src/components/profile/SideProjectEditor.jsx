import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { handleSuccessSidProject } from 'redux/profile';
import customAxios from 'utils/axios';

import {
  Typography,
  Grid,
  IconButton,
  TextField,
  MenuItem,
  Select,
  FormControl,
} from '@mui/material';

import CloseIcon from '@mui/icons-material/Close';
import CreateIcon from '@mui/icons-material/Create';
import TechStackSelector from 'components/profile/TechStackSelector';
import { handleSuccessState } from 'redux/snack';

function SideProjectEditor(props) {
  const sideProject = props?.sideProject;
  const curYear = new Date().getFullYear();
  const [year, setYear] = useState(sideProject ? sideProject.year : curYear);
  const [name, setName] = useState(sideProject ? sideProject.name : '');
  const [selectedValue, setSelectedValue] = useState(
    sideProject ? sideProject.pjt_tech_stack : [],
  );
  const [context, setContext] = useState(
    sideProject ? sideProject.context : '',
  );

  const profileId = useSelector(state => state.profile.userProfile[0].id);

  const handleCloseAddSideProject = () => {
    setYear(curYear);
    setName('');
    setSelectedValue(null);
    setContext('');

    if (props.isEdit) props.setIsEdit(false);
    props.setIsAdd(false);
  };
  const dispatch = useDispatch();

  const handleChangeYear = event => {
    setYear(event.target.value);
  };

  const handleEditSideProject = () => {
    if (!name.trim()) {
      dispatch(
        handleSuccessState({
          open: true,
          message: '프로젝트 이름을 작성해 주세요.',
          severity: 'error',
        }),
      );
    } else if (!context.trim()) {
      dispatch(
        handleSuccessState({
          open: true,
          message: '기술 스택을 선택해 주세요.',
          severity: 'error',
        }),
      );
    } else if (!context.trim()) {
      dispatch(
        handleSuccessState({
          open: true,
          message: '프로젝트 소개를 작성해 주세요.',
          severity: 'error',
        }),
      );
    } else {
      customAxios.authAxios
        .put('/profile/sidepjt', {
          year: String(year),
          name: name,
          pjt_tech_stack: selectedValue,
          context: context,
          id: sideProject.id,
        })
        .then(response => {
          dispatch(
            handleSuccessState({
              open: true,
              message: '프로젝트가 수정 되었습니다.',
              severity: 'success',
            }),
          );
          dispatch(handleSuccessSidProject({ sideProjects: response.data }));
          props.setIsEdit(false);
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  const handleCreateSidProject = () => {
    if (!name.trim()) {
      dispatch(
        handleSuccessState({
          open: true,
          message: '프로젝트 이름을 작성해 주세요.',
          severity: 'error',
        }),
      );
    } else if (selectedValue.length === 0) {
      dispatch(
        handleSuccessState({
          open: true,
          message: '기술 스택을 선택해 주세요.',
          severity: 'error',
        }),
      );
    } else if (!context.trim()) {
      dispatch(
        handleSuccessState({
          open: true,
          message: '프로젝트 소개를 작성해 주세요.',
          severity: 'error',
        }),
      );
    } else {
      customAxios.authAxios
        .post(`/profile/sidepjt`, {
          year: String(year),
          name: name,
          pjt_tech_stack: selectedValue,
          context: context,
          profileId: profileId,
        })
        .then(response => {
          dispatch(
            handleSuccessState({
              open: true,
              message: '프로젝트가 생성 되었습니다.',
              severity: 'success',
            }),
          );
          dispatch(handleSuccessSidProject({ sideProjects: response.data }));
          props.setIsAdd(false);
        })
        .catch(error => {
          console.log(error);
        });
    }
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
  return (
    <>
      <Grid item container xs={12} alignItems="start">
        {/* Year */}
        <Grid item xs={3} md={2}>
          <FormControl size="small">
            <Select
              labelId="demo-select-small"
              id="demo-select-small"
              variant="outlined"
              value={year}
              onChange={handleChangeYear}
            >
              <MenuItem value={curYear}>{curYear}</MenuItem>
              <MenuItem value={curYear - 1}>{curYear - 1}</MenuItem>
              <MenuItem value={curYear - 2}>{curYear - 2}</MenuItem>
              <MenuItem value={curYear - 3}>{curYear - 3}</MenuItem>
              <MenuItem value={curYear - 4}>{curYear - 4}</MenuItem>
            </Select>
          </FormControl>
        </Grid>
        {/* Main */}
        <Grid item xs={8} md={9}>
          {/* Name */}
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
          {/* Tech */}
          <TechStackSelector
            setSelectedValue={setSelectedValue}
            techs={selectedValue}
          ></TechStackSelector>
          {/* Context */}
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
        {/* Button */}
        <Grid item xs={1} sx={{ display: 'flex' }} justifyContent="end">
          <IconButton onClick={handleCloseAddSideProject} sx={{ padding: '0' }}>
            <CloseIcon />
          </IconButton>
        </Grid>
        <Grid
          item
          container
          xs={12}
          sx={{ display: 'flex' }}
          justifyContent="end"
        >
          <Grid item xs={11}>
            <Typography
              variant="caption"
              color="initial"
              justifyContent="end"
              sx={{ display: 'flex' }}
            >
              {context.length} / {limit}
            </Typography>
          </Grid>
          <Grid item xs={1} sx={{ display: 'flex' }} justifyContent="end">
            <IconButton
              onClick={
                props?.isEdit ? handleEditSideProject : handleCreateSidProject
              }
              sx={{ padding: '0' }}
            >
              <CreateIcon />
            </IconButton>
          </Grid>
        </Grid>
      </Grid>
    </>
  );
}

export default SideProjectEditor;
