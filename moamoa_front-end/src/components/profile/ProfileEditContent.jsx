import styled from '@emotion/styled';
import {
  Grid,
  Typography,
  TextField,
  ToggleButtonGroup,
  ToggleButton,
  Autocomplete,
  Avatar,
} from '@mui/material';
import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { handleEditProfile } from 'redux/profile';
import TechStackSelector from 'components/profile/TechStackSelector';

function ProfileEditContent() {
  const profile = useSelector(state => state.profile);
  const areas = useSelector(state => state.search.area);

  const [selectedValue, setSelectedValue] = useState(profile.techStacks);
  const [selectedArea, setSelectedArea] = useState(areas);

  const [githubLink, setGithubLink] = useState('');
  const [tistoryLink, setTistoryLink] = useState('');
  const [velogLink, setVelogLink] = useState('');
  const [projectLink, setProjectLink] = useState('');

  const [progress, setProgress] = useState('ALL');

  const dispatch = useDispatch();

  const handleChange = setter => event => {
    setter(event.target.value);

    if (progress === 'ONLINE') setSelectedArea([]);

    dispatch(
      handleEditProfile({
        sites: [
          { link: githubLink, nmae: 'github' },
          { link: tistoryLink, nmae: 'tistory' },
          { link: velogLink, nmae: 'velog' },
          { link: projectLink, nmae: 'project' },
        ],
        areas: selectedArea,
        techStacks: selectedValue,
      }),
    );
  };

  return (
    <>
      <ContentTitle color="initial">기본 정보 수정</ContentTitle>
      <MoaContainer container>
        {/* 기술스택 */}
        <Grid
          id="tech"
          item
          container
          alignItems={'start'}
          marginBottom={'4rem'}
          xs={12}
        >
          <Grid item paddingTop={'.3rem'} xs={2}>
            <Typography textAlign={'start'} variant="body1" color="initial">
              기술스택
            </Typography>
          </Grid>
          <Grid item xs={10}>
            <TechStackSelector
              setSelectedValue={setSelectedValue}
              techs={selectedValue}
              sx={{ minHeight: '4rem' }}
            ></TechStackSelector>
          </Grid>
        </Grid>
        {/* 링크 */}
        <Grid
          id="link"
          item
          container
          alignItems={'start'}
          marginBottom={'4rem'}
          xs={12}
        >
          <Grid item paddingTop={'.3rem'} xs={2}>
            <Typography textAlign={'start'} variant="body1" color="initial">
              링크
            </Typography>
          </Grid>
          <Grid id="link_container" item container rowSpacing={2} xs={10}>
            {/* Github */}
            <Grid item xs={12} display={'flex'} alignItems={'center'}>
              <Avatar
                alt="github"
                src={`${process.env.PUBLIC_URL}/images/blog_icons/github@4x.png`}
                sx={{
                  maxHeight: '2rem',
                  maxWidth: '2rem',
                  boxShadow: '3',
                  marginRight: '1rem',
                }}
              />
              <TextField
                id="github"
                variant="standard"
                fullWidth
                InputProps={{ disableUnderline: true }}
                placeholder="URL"
                onChange={handleChange(setGithubLink)}
                value={githubLink}
                type="url"
              />
            </Grid>
            {/* Tistory */}
            <Grid item xs={12} display={'flex'} alignItems={'center'}>
              <Avatar
                alt="tistory"
                src={`${process.env.PUBLIC_URL}/images/blog_icons/tistory@4x.png`}
                sx={{
                  maxHeight: '2rem',
                  maxWidth: '2rem',
                  boxShadow: '3',
                  marginRight: '1rem',
                }}
              />
              <TextField
                id="tistory"
                variant="standard"
                fullWidth
                InputProps={{ disableUnderline: true }}
                placeholder="URL"
                onChange={handleChange(setTistoryLink)}
                value={tistoryLink}
              />
            </Grid>
            {/* Velog */}
            <Grid item xs={12} display={'flex'} alignItems={'center'}>
              <Avatar
                alt="velog"
                src={`${process.env.PUBLIC_URL}/images/blog_icons/velog@4x.png`}
                sx={{
                  maxHeight: '2rem',
                  maxWidth: '2rem',
                  boxShadow: '3',
                  marginRight: '1rem',
                }}
              />
              <TextField
                id="velog"
                variant="standard"
                fullWidth
                InputProps={{ disableUnderline: true }}
                placeholder="URL"
                onChange={handleChange(setVelogLink)}
                value={velogLink}
              />
            </Grid>
            {/* Project */}
            <Grid item xs={12} display={'flex'} alignItems={'center'}>
              <Avatar
                alt="project"
                sx={{
                  maxHeight: '2rem',
                  maxWidth: '2rem',
                  boxShadow: '3',
                  marginRight: '1rem',
                }}
              />
              <TextField
                id="Project"
                variant="standard"
                fullWidth
                InputProps={{ disableUnderline: true }}
                placeholder="URL"
                onChange={handleChange(setProjectLink)}
                value={projectLink}
              />
            </Grid>
          </Grid>
        </Grid>
        {/* 진행방식 */}
        <Grid
          id="progress"
          item
          container
          alignItems={'start'}
          marginBottom={'4rem'}
          xs={12}
        >
          <Grid item paddingTop={'.5rem'} xs={2}>
            <Typography textAlign={'start'} variant="body1" color="initial">
              진행방식
            </Typography>
          </Grid>
          <Grid item xs={10}>
            <ToggleButtonGroup
              color="primary"
              value={progress}
              size="small"
              exclusive
              onChange={handleChange(setProgress)}
              aria-label="Platform"
              sx={{ marginBottom: '1rem' }}
            >
              <ToggleButton value="ALL">전체</ToggleButton>
              <ToggleButton value="ONLINE">온라인</ToggleButton>
              <ToggleButton value="OFFLINE">오프라인</ToggleButton>
            </ToggleButtonGroup>
            <Autocomplete
              sx={{ display: `${progress === 'ONLINE' ? 'none' : 'flex'}` }}
              fullWidth
              multiple={true}
              id="tags-standard"
              options={areas}
              getOptionLabel={option => option.name}
              onChange={handleChange(setSelectedArea)}
              renderInput={params => (
                <TextField
                  {...params}
                  fullWidth
                  variant="standard"
                  placeholder={'최대 3개의 지역을 선택 가능합니다.'}
                />
              )}
            />
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

export default ProfileEditContent;
