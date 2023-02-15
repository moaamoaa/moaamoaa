import Grid from '@mui/material/Grid';

import CardItem from 'components/common/card/CardItem';
import styled from '@emotion/styled';
import { Typography } from '@mui/material';
import useMobile from 'hooks/useMobile';

export default function CardList(props) {
  const isMobile = useMobile();

  if (props.type === 'team') {
    return (
      <MoaGrid container spacing={10}>
        {props.cards &&
          props.cards.map((card, idx) => (
            <Grid item key={idx} xs={12} md={6} lg={4}>
              <CardItem
                card={card}
                isDetail={true}
                type={props.type}
              ></CardItem>
            </Grid>
          ))}
      </MoaGrid>
    );
  } else if (props.type === 'member') {
    return (
      <MoaGrid container spacing={5}>
        {props.cards &&
          props.cards.map((card, idx) => (
            <Grid item key={idx} xs={12} sm={6} md={4} lg={3}>
              <CardItem
                card={card}
                isDetail={true}
                type={props.type}
              ></CardItem>
            </Grid>
          ))}
      </MoaGrid>
    );
  } else if (props.type === 'tech') {
    return (
      <Grid
        container
        sx={{
          height: '50px',
          alignItems: 'center',
        }}
      >
        {props.cards ? (
          props.cards &&
          props.cards.map((card, idx) => (
            <Grid item key={idx} mr={2}>
              <CardItem
                card={card}
                isDetail={true}
                type={props.type}
              ></CardItem>
            </Grid>
          ))
        ) : (
          <Typography
            variant={isMobile ? 'caption' : 'body1'}
            sx={{
              width: '100%',
              fontWeight: '600',
              opacity: '0.5',
            }}
            textAlign="center"
          >
            기술스택이 등록되지 않았습니다.
          </Typography>
        )}
      </Grid>
    );
  } else if (props.type === 'link') {
    return (
      <Grid container sx={{ height: '50px', alignItems: 'center' }}>
        {props.cards.length !== 0 ? (
          props.cards &&
          Object.entries(props.cards).map((card, idx) => (
            <Grid item key={idx} xs={3}>
              <CardItem
                card={card}
                isDetail={true}
                type={props.type}
              ></CardItem>
            </Grid>
          ))
        ) : (
          <Typography
            variant={isMobile ? 'caption' : 'body1'}
            sx={{
              width: '100%',
              fontWeight: '600',
              opacity: '0.5',
            }}
            textAlign="center"
          >
            사이트가 등록되지 않았습니다.
          </Typography>
        )}
      </Grid>
    );
  }
}

const MoaGrid = styled(Grid)`
  margin-bottom: 48px;
`;
