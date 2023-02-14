import { useNavigate } from 'react-router-dom';
import { handleOpenTeamDetail } from 'redux/team';
import { useDispatch } from 'react-redux';
import { handleProfilePk } from 'redux/profile';

import styled from 'styled-components';

import {
  Card,
  CardContent,
  CardMedia,
  Typography,
  CardActions,
  Button,
  Grid,
  Skeleton,
  Avatar,
} from '@mui/material';

import CardList from 'components/common/card/CardList';
import { Link } from '@mui/material';

import scrollToTop from 'utils/scrollToTop';

import VisibilityOutlinedIcon from '@mui/icons-material/VisibilityOutlined';
import GroupsOutlinedIcon from '@mui/icons-material/GroupsOutlined';

export default function CardItem(props) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const goToDetail = () => {
    if (props.type === 'team') {
      dispatch(handleOpenTeamDetail({ projectId: props.card.id }));
      navigate(`/TeamDetailPage/?projectId=${props.card.id}`);
      scrollToTop();
    } else if (props.type === 'member') {
      dispatch(handleProfilePk({ id: props.card.id }));
      navigate('/ProfilePage');
      scrollToTop();
    }
  };
  if (props.type === 'team') {
    return (
      <MoaCard onClick={goToDetail}>
        {props.card.img ? (
          <CardMedia
            component="img"
            src={props.card.img}
            alt="random"
            sx={{
              position: 'relative',
              backgroundSize: 'cover',
              backgroundRepeat: 'no-repeat',
              backgroundPosition: 'center',
              height: `200px`,
              margin: '0 auto',
            }}
          />
        ) : (
          <Skeleton variant="rectangular" height={200} />
        )}
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {props.card.title}
          </Typography>
          <Grid container paddingY={1}>
            <Grid item xs={4} display={'flex'} alignItems="center">
              <Typography variant="body2" color="#888">
                팀장 {props.card.leaderName}
              </Typography>
            </Grid>
            <Grid item xs={2} display={'flex'} alignItems="center">
              <VisibilityOutlinedIcon
                fontSize="small"
                sx={{ color: '#888' }}
              ></VisibilityOutlinedIcon>
              <Typography variant="body2" color="#888">
                {props.card.hit}
              </Typography>
            </Grid>
            <Grid item xs={6} display={'flex'} alignItems="center">
              <GroupsOutlinedIcon
                fontSize="small"
                sx={{ color: '#888' }}
              ></GroupsOutlinedIcon>
              <Typography variant="body2" color="#888">
                {props.card.currentPeople} / {props.card.totalPeople}
              </Typography>
            </Grid>
          </Grid>
          <CardList type="tech" cards={props.card.techStacks}></CardList>
          <InfoTypography paddingTop={1} variant="body2" color="#000">
            {props.card.contents
              ? props.card.contents
              : `${props.card.title}팀 입니다.`}
          </InfoTypography>
        </CardContent>
      </MoaCard>
    );
  } else if (props.type === 'member') {
    return (
      <MoaCard onClick={goToDetail}>
        <CardActions>
          <Grid container>
            <Grid item xs>
              <Button
                size="small"
                variant="contained"
                color="primary"
                sx={{ display: 'none' }}
              >
                권한위임
              </Button>
            </Grid>
            <Grid item xs>
              <Button size="small" variant="contained" color="primary">
                제안하기
              </Button>
            </Grid>
          </Grid>
        </CardActions>
        {props.card.img ? (
          <CardMedia
            component="img"
            src={props.card.img}
            alt="random"
            sx={{
              borderRadius: '50%',
              width: '100px',
              height: '100px',
              margin: '0 auto',
              boxShadow: 5,
            }}
          />
        ) : (
          <MoaSkeleton variant="circular" width={100} height={100} />
        )}
        <CardContent>
          <MoaTypography gutterBottom variant="h5" component="div">
            {props.card.nickname}
          </MoaTypography>
          <MoaTypography variant="body2" color="text.secondary">
            {props.card.area ? props.card.area : '온라인 / 전 지역'}
          </MoaTypography>
          <InfoTypography variant="body2" color="text.secondary">
            {props.card.contents
              ? props.card.contents
              : `안녕하세요. ${props.card.nickname}입니다.`}
          </InfoTypography>

          <CardList type={'tech'} cards={props.card.techStacks}></CardList>
        </CardContent>
      </MoaCard>
    );
  } else if (props.type === 'tech') {
    return <MoaImg src={props.card.logo} />;
  } else if (props.type === 'link') {
    return (
      <Link href={props.card.site} target="_blank">
        <MoaImg src={props.card.site} />
      </Link>
    );
  }
}

const MoaCard = styled(Card)`
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
  transition: 0.4s;
  &:hover {
    scale: 1.04;
    box-shadow: 0px 4px 16px rgba(0, 0, 0, 0.2);
    transition: 0.4s;
  }
  cursor: pointer;
`;

const MoaSkeleton = styled(Skeleton)`
  width: 100px;
  height: 100px;
  margin: 0 auto;
`;

const InfoTypography = styled(Typography)`
  text-align: justify;
  height: 60px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
`;

const MoaTypography = styled(Typography)`
  display: flex;
  justify-content: center;
`;

const MoaImg = styled(Avatar)`
  min-width: 40px;
  min-height: 40px;
  box-shadow: 0px 4px 5px rgba(0, 0, 0, 0.25);
`;
