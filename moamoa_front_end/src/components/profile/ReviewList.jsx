import { useSelector } from 'react-redux';
import styled from '@emotion/styled';

import { Grid, Typography } from '@mui/material';

import Review from 'components/profile/Review';
import ReviewCreation from 'components/profile/ReviewCreation';

function ReviewList() {
  const reviews = useSelector(state => state.profile.reviews);

  return (
    <>
      <ContentTitle color="initial">댓글</ContentTitle>
      <MoaContainer container>
        {reviews.length !== 0 ? (
          reviews.map((review, idx) => {
            return (
              <Grid key={idx} item xs={12}>
                <Review review={review} />
              </Grid>
            );
          })
        ) : (
          <></>
        )}
        <Grid item xs={12}>
          <ReviewCreation />
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
