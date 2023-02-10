import { useState } from 'react';
import { useDispatch } from 'react-redux';
import {
  handleSuccessEditSidProject,
  handleSuccessSidProject,
} from 'redux/profile';
import customAxios from 'utils/axios';

import {
  Typography,
  Grid,
  IconButton,
  TextField,
  MenuItem,
  Select,
  FormControl,
  InputLabel,
} from '@mui/material';

import CloseIcon from '@mui/icons-material/Close';
import CreateIcon from '@mui/icons-material/Create';
import TechStackSeletor from 'components/profile/TechStackSeletor';

function SideProjectEditor(props) {
  const sideProject = props?.sideProject;
  const curYear = new Date().getFullYear();
  const [year, setYear] = useState(sideProject ? sideProject.year : curYear);
  const [name, setName] = useState(sideProject ? sideProject.name : '');
  const [selectedValue, setSelectedValue] = useState(
    sideProject ? sideProject.pjt_tech_stack : null,
  );
  const [context, setContext] = useState(
    sideProject ? sideProject.context : '',
  );

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
    customAxios.authAxios
      .put('/profile/sidepjt', {
        year: String(year),
        name: name,
        pjt_tech_stack: selectedValue,
        context: context,
        id: sideProject.id,
      })
      .then(response => {
        dispatch(handleSuccessEditSidProject({ sideProjects: response.data }));
        props.setIsEdit(false);
      })
      .catch(error => {
        console.log(error);
      });
  };

  const handleCreateSidProject = () => {
    console.log(String(year), name, selectedValue, context);
    customAxios.authAxios
      .post(`/profile/sidepjt`, {
        year: String(year),
        name: name,
        pjt_tech_stack: selectedValue,
        context: context,
      })
      .then(response => {
        props.setIsAdd(false);
        dispatch(
          handleSuccessSidProject({ sideProjects: response.data.sideProjects }),
        );
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
  return (
    <>
      <Grid item container xs={12} alignItems="start">
        <Grid item xs={3} md={2}>
          <FormControl size="small">
            <InputLabel id="demo-select-small">년도</InputLabel>
            <Select
              labelId="demo-select-small"
              id="demo-select-small"
              value={year}
              label="Year"
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
            techs={selectedValue}
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
        <Grid
          item
          xs={1}
          container
          rowGap={9}
          justifyContent="end"
          sx={{ display: 'flex' }}
        >
          <Grid item xs={12} justifyContent="end" sx={{ display: 'flex' }}>
            <IconButton
              onClick={handleCloseAddSideProject}
              sx={{ padding: '0' }}
            >
              <CloseIcon />
            </IconButton>
          </Grid>
          <Grid item xs={12} justifyContent="end" sx={{ display: 'flex' }}>
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
      </Grid>
    </>
  );
}

export default SideProjectEditor;
