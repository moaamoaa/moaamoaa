import { useSelector } from 'react-redux';
import styled from '@emotion/styled';

import { Grid, Typography } from '@mui/material';

import Review from 'components/profile/Review';
import ReviewCreation from './ReviewCreation';

function ReviewList() {
  const reviews = useSelector(state => state.profile.reviews);
  console.log(reviews);
  return (
    <>
      <ContentTitle color="initial">댓글</ContentTitle>
      <MoaContainer container>
        {reviews.length !== 0 ? (
          reviews.map((review, idx) => {
            <Grid item xs={12}>
              <Review key={idx} review={review}></Review>
            </Grid>;
          })
        ) : (
          <>
            <Grid item xs={12}>
              <Review name="임싸피" review="안녕" date="2023-02-08" />
            </Grid>
            <Grid item xs={12}>
              <Review name="장싸피" review="반가워" date="2023-02-08" />
            </Grid>
            <Grid item xs={12}>
              <Review name="황싸피" review="또 보자" date="2023-02-08" />
            </Grid>
            <Grid item xs={12}>
              <Review name="유싸피" review="잘가" date="2023-02-08" />
            </Grid>
          </>
        )}
        <Grid item xs={12}>
          <ReviewCreation></ReviewCreation>
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

export default ReviewList;
