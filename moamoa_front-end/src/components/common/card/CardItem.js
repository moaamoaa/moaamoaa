import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import CardActions from '@mui/material/CardActions';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';

export default function CardItem() {
  return (
    <Card sx={{ maxWidth: 345 }}>
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
        image="https://source.unsplash.com/random"
        alt="random"
      />
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
}
