import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { handleEditProfile } from 'redux/profile';

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

import TechStackSelector from 'components/profile/TechStackSelector';
import useMobile from 'hooks/useMobile';

function ProfileEditContent() {
  const profile = useSelector(state => state.profile);
  const areas = useSelector(state => state.search.area);

  const [selectedValue, setSelectedValue] = useState(
    profile.techStacks ? profile.techStacks : [],
  );
  const [selectedArea, setSelectedArea] = useState(
    profile.areas ? profile.areas : [],
  );

  const [githubLink, setGithubLink] = useState('');
  const [tistoryLink, setTistoryLink] = useState('');
  const [velogLink, setVelogLink] = useState('');
  const [projectLink, setProjectLink] = useState('');

  const [progress, setProgress] = useState('ALL');

  const isMobile = useMobile();

  const dispatch = useDispatch();

  const handleGithubLink = event => {
    setGithubLink(event.target.value);

    dispatch(
      handleEditProfile({
        sites: [
          { link: event.target.value, name: 'Github' },
          { link: tistoryLink, name: 'Tistory' },
          { link: velogLink, name: 'Velog' },
          { link: projectLink, name: 'Custom' },
        ],
        areas: selectedArea,
        techStacks: selectedValue,
        profileOnOffStatus: progress,
      }),
    );
  };
  const handleTistoryLink = event => {
    setTistoryLink(event.target.value);

    dispatch(
      handleEditProfile({
        sites: [
          { link: githubLink, name: 'Github' },
          { link: event.target.value, name: 'Tistory' },
          { link: velogLink, name: 'Velog' },
          { link: projectLink, name: 'Custom' },
        ],
        areas: selectedArea,
        techStacks: selectedValue,
        profileOnOffStatus: progress,
      }),
    );
  };
  const handleVelogLink = event => {
    setVelogLink(event.target.value);

    dispatch(
      handleEditProfile({
        sites: [
          { link: githubLink, name: 'Github' },
          { link: tistoryLink, name: 'Tistory' },
          { link: event.target.value, name: 'Velog' },
          { link: projectLink, name: 'Custom' },
        ],
        areas: selectedArea,
        techStacks: selectedValue,
        profileOnOffStatus: progress,
      }),
    );
  };
  const handleProjectLink = event => {
    setProjectLink(event.target.value);

    dispatch(
      handleEditProfile({
        sites: [
          { link: githubLink, name: 'Github' },
          { link: tistoryLink, name: 'Tistory' },
          { link: velogLink, name: 'Velog' },
          { link: event.target.value, name: 'Custom' },
        ],
        areas: selectedArea,
        techStacks: selectedValue,
        profileOnOffStatus: progress,
      }),
    );
  };

  const handleProgress = event => {
    setProgress(event.target.value);

    if (event.target.value === 'ONLINE') {
      dispatch(
        handleEditProfile({
          sites: [
            { link: githubLink, name: 'Github' },
            { link: tistoryLink, name: 'Tistory' },
            { link: velogLink, name: 'Velog' },
            { link: projectLink, name: 'Custom' },
          ],
          areas: [],
          techStacks: selectedValue,
          profileOnOffStatus: event.target.value,
        }),
      );
    } else {
      dispatch(
        handleEditProfile({
          sites: [
            { link: githubLink, name: 'Github' },
            { link: tistoryLink, name: 'Tistory' },
            { link: velogLink, name: 'Velog' },
            { link: projectLink, name: 'Custom' },
          ],
          areas: selectedArea,
          techStacks: selectedValue,
          profileOnOffStatus: event.target.value,
        }),
      );
    }
  };

  const handleSelectedArea = (event, value) => {
    setSelectedArea(value);

    dispatch(
      handleEditProfile({
        sites: [
          { link: githubLink, name: 'Github' },
          { link: tistoryLink, name: 'Tistory' },
          { link: velogLink, name: 'Velog' },
          { link: projectLink, name: 'Custom' },
        ],
        areas: value,
        techStacks: selectedValue,
        profileOnOffStatus: progress,
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
            <Typography
              textAlign={'start'}
              variant={isMobile ? 'caption' : 'body1'}
              color="initial"
            >
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
            <Typography
              textAlign={'start'}
              variant={isMobile ? 'caption' : 'body1'}
              color="initial"
            >
              링크
            </Typography>
          </Grid>
          <Grid id="link_container" item container rowSpacing={3} xs={10}>
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
                onChange={handleGithubLink}
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
                onChange={handleTistoryLink}
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
                onChange={handleVelogLink}
                value={velogLink}
              />
            </Grid>
            {/* Project */}
            <Grid item xs={12} display={'flex'} alignItems={'center'}>
              <Avatar
                alt="project"
                src={`${process.env.PUBLIC_URL}/images/blog_icons/moaa@4x.png`}
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
                onChange={handleProjectLink}
                value={projectLink}
              />
            </Grid>
          </Grid>
        </Grid>
        {/* 진행방식 */}
        <Grid id="progress" item container alignItems={'start'} xs={12}>
          <Grid item paddingTop={'.5rem'} xs={2}>
            <Typography
              textAlign={'start'}
              variant={isMobile ? 'caption' : 'body1'}
              color="initial"
            >
              진행방식
            </Typography>
          </Grid>
          <Grid item xs={10}>
            <ToggleButtonGroup
              color="primary"
              value={progress}
              size="small"
              exclusive
              onChange={handleProgress}
              aria-label="Platform"
              sx={{ marginBottom: '1rem' }}
            >
              <ToggleButton value="ALL">전체</ToggleButton>
              <ToggleButton value="ONLINE">온라인</ToggleButton>
              <ToggleButton value="OFFLINE">오프라인</ToggleButton>
            </ToggleButtonGroup>

            <Autocomplete
              disabled={progress === 'ONLINE' ? true : false}
              fullWidth
              multiple={true}
              id="tags-standard"
              options={areas}
              getOptionLabel={option => option.name}
              getOptionDisabled={options => (options.length > 3 ? true : false)}
              onChange={handleSelectedArea}
              renderInput={params => (
                <TextField
                  {...params}
                  fullWidth
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
