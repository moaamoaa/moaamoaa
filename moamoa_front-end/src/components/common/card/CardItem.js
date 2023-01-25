import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import CardActions from '@mui/material/CardActions';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import Skeleton from '@mui/material/Skeleton';
import styled from 'styled-components';

export default function CardItem(props) {
  console.log(props.card);

  if (props.type === 'team') {
    return (
      <Card sx={{ minWidth: 300 }}>
        <CardActions>
          <Grid container>
            <Grid item xs>
              <Button
                size="small"
                variant="contained"
                color="primary"
                sx={{ display: 'none' }} // 조건에 따라
              >
                권한위임
              </Button>
            </Grid>
            <Grid item xs>
              <Button size="small" variant="contained" color="primary">
                강퇴하기 / 제안하기
              </Button>
            </Grid>
          </Grid>
        </CardActions>
        {props.card.thumbnailUrl ? (
          <CardMedia
            component="img"
            src={props.card.thumbnailUrl}
            alt="random"
          />
        ) : (
          <Skeleton variant="rectangular" height={200} />
        )}
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            팀 이름 / 팀원 이름
          </Typography>
          <Typography variant="body2" color="text.secondary">
            팀장 이름 / 포지션
          </Typography>
          <Typography variant="body2" color="text.secondary">
            조회수 / 없음
          </Typography>
          <Typography variant="body2" color="text.secondary">
            팀원수 / 없음
          </Typography>
          {/* <TechStackList>기술스택리스트</TechStackList> */}
          <Typography variant="body2" color="text.secondary">
            팀 소개 / 자기소개
          </Typography>
        </CardContent>
      </Card>
    );
  } else if (props.type === 'member') {
    return (
      <Card>
        <CardActions>
          <Grid container>
            <Grid item xs>
              <Button
                size="small"
                variant="contained"
                color="primary"
                sx={{ display: 'none' }} // 조건에 따라
              >
                권한위임
              </Button>
            </Grid>
            <Grid item xs>
              <Button size="small" variant="contained" color="primary">
                강퇴하기 / 제안하기
              </Button>
            </Grid>
          </Grid>
        </CardActions>
        {props.card.thumbnailUrl ? (
          <CardMedia
            component="img"
            src={props.card.thumbnailUrl}
            alt="random"
          />
        ) : (
          <MoaSkeleton variant="circular" width={100} height={100} />
        )}
        <MoaCardContent>
          <MoaTypography gutterBottom variant="h5" component="div">
            팀원 이름
          </MoaTypography>
          <MoaTypography variant="body2" color="text.secondary">
            지역
          </MoaTypography>
          <MoaTypography variant="body2" color="text.secondary">
            자기소개
          </MoaTypography>
          <MoaTypography variant="body2" color="text.secondary">
            기술스택
          </MoaTypography>
          {/* <TechStackList>기술스택리스트</TechStackList> */}
        </MoaCardContent>
      </Card>
    );
  } else {
    return (
      <Card sx={{ maxWidth: 450 }}>
        <CardActions>
          <Grid container>
            <Grid item xs>
              <Button
                size="small"
                variant="contained"
                color="primary"
                sx={{ display: 'none' }} // 조건에 따라
              >
                권한위임
              </Button>
            </Grid>
            <Grid item xs>
              <Button size="small" variant="contained" color="primary">
                강퇴하기 / 제안하기
              </Button>
            </Grid>
          </Grid>
        </CardActions>
        <CardMedia
          component="img"
          sx={{
            // 16:9
            pt: '56.25%',
          }}
          src="https://source.unsplash.com/random"
          alt="random"
        />
        <MoaCardContent>
          <Typography gutterBottom variant="h5" component="div">
            팀 이름 / 팀원 이름
          </Typography>
          <Typography variant="body2" color="text.secondary">
            팀장 이름 / 포지션
          </Typography>
          <Typography variant="body2" color="text.secondary">
            조회수 / 없음
          </Typography>
          <Typography variant="body2" color="text.secondary">
            팀원수 / 없음
          </Typography>
          {/* <TechStackList>기술스택리스트</TechStackList> */}
          <Typography variant="body2" color="text.secondary">
            팀 소개 / 자기소개
          </Typography>
        </MoaCardContent>
      </Card>
    );
  }
}

const MoaSkeleton = styled(Skeleton)`
  width: 100px;
  height: 100px;
  margin: 0 auto;
`;

const MoaCardContent = styled(CardContent)``;

const MoaTypography = styled(Typography)`
  display: flex;
  justify-content: center;
`;
