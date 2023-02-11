import * as React from 'react';

import styled from 'styled-components';

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import CardActions from '@mui/material/CardActions';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import Skeleton from '@mui/material/Skeleton';
import Avatar from '@mui/material/Avatar';

import CardList from 'components/common/card/CardList';
import { Link } from '@mui/material';

import { useNavigate } from 'react-router-dom';
import { handleOpenTeamDetail } from 'redux/team';
import { useDispatch } from 'react-redux';
import { handleProfilePk } from 'redux/profile';
import scrollToTop from 'utils/scrollToTop';

const user = {
  id: 0,
  name: '김싸피',
  tech: [
    ['front-end_icons', 'javascript'],
    ['front-end_icons', 'typescript'],
  ],
  link: {
    github: 'https://github.com/LimSB-dev',
    tistory: '',
    velog: '',
  },
};

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
        {props.card.thumbnailUrl ? (
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
          <Typography variant="body2" color="text.secondary">
            팀장 {props.card.leaderName}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {props.card.hit} views
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {props.card.currentPeople} / {props.card.totalPeople}
          </Typography>
          {/* <CardList type='tech' cards={ techStacks}>기술스택리스트</CardList> */}
          <InfoTypography variant="body2" color="text.secondary">
            {props.card.contents}
            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Id,
            corporis nobis ea beatae et reprehenderit dolorum iure libero atque,
            eum, aliquam sequi delectus deleniti alias quasi quis veritatis!
            Quis, quidem.
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
              <Button
                size="small"
                variant="contained"
                color="primary"
                // sx={{ display: 'none' }}
              >
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
            sx={{ borderRadius: '50%', width: '100px', margin: '0 auto' }}
          />
        ) : (
          <MoaSkeleton variant="circular" width={100} height={100} />
        )}
        <CardContent>
          <MoaTypography gutterBottom variant="h5" component="div">
            {props.card.nickname}
          </MoaTypography>
          <MoaTypography variant="body2" color="text.secondary">
            {/* {props.card.area.map(a => {
              return <div>{a}</div>;
            })} */}
            {props.card ? props.card.area : ''}
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
    return <MoaImg src={props.card.img} />;
  } else if (props.type === 'link') {
    if (props.card[1] !== '') {
      return (
        <Link href={props.card[1]} target="_blank">
          <MoaImg src={props.card.site} />
        </Link>
      );
    } else {
      return <></>;
    }
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
