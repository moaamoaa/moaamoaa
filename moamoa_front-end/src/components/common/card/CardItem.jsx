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
  if (props.type === 'team') {
    return (
      <MoaCard>
        {props.card.thumbnailUrl ? (
          <CardMedia
            component="img"
            src={props.card.thumbnailUrl}
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
            팀 이름
          </Typography>
          <Typography variant="body2" color="text.secondary">
            팀장 이름
          </Typography>
          <Typography variant="body2" color="text.secondary">
            1,600 views
          </Typography>
          <Typography variant="body2" color="text.secondary">
            0 / 6
          </Typography>
          {/* <TechStackList>기술스택리스트</TechStackList> */}
          <InfoTypography variant="body2" color="text.secondary">
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
      <MoaCard>
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
        {props.card.thumbnailUrl ? (
          <CardMedia
            component="img"
            src={props.card.thumbnailUrl}
            alt="random"
            sx={{ borderRadius: '50%', width: '100px', margin: '0 auto' }}
          />
        ) : (
          <MoaSkeleton variant="circular" width={100} height={100} />
        )}
        <CardContent>
          <MoaTypography gutterBottom variant="h5" component="div">
            팀원 이름
          </MoaTypography>
          <MoaTypography variant="body2" color="text.secondary">
            지역
          </MoaTypography>
          <InfoTypography variant="body2" color="text.secondary">
            Lorem, ipsum dolor sit consectetur adipisicing elit. Ducimus culpa
            debitis optio voluptates laborum accusamus ab officia facilis dicta
            quos placeat perspiciatis dolore eaque mollitia adipisci impedit,
            quam laboriosam alias.
          </InfoTypography>
          <CardList type={'tech'} cards={user.tech}></CardList>
          {/* <TechStackList>기술스택리스트</TechStackList> */}
        </CardContent>
      </MoaCard>
    );
  } else if (props.type === 'tech') {
    return (
      <MoaImg
        src={`${process.env.PUBLIC_URL}/images/tech-stack_icons/${props.card[0]}/${props.card[1]}@4x.png`}
      />
    );
  } else if (props.type === 'link') {
    if (props.card[1] !== '') {
      return (
        <Link href={props.card[1]} target="_blank">
          <MoaImg
            src={`${process.env.PUBLIC_URL}/images/blog_icons/${props.card[0]}@4x.png`}
          />
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
