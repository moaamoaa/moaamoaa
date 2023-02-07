import { useSelector } from 'react-redux';

import Review from 'components/profile/Review';
import { Container, Typography } from '@mui/material';
import styled from '@emotion/styled';

function ReviewList() {
  const reviews = useSelector(state => state.profile.reviews);
  return (
    <>
      <ContentTitle color="initial">댓글</ContentTitle>
      <MoaContainer spacing={0}>
        {reviews ? (
          reviews.map((review, idx) => {
            <Review key={idx} review={review}></Review>;
          })
        ) : (
          <></>
        )}
      </MoaContainer>
    </>
  );
}

const ContentTitle = styled(Typography)`
  font-weight: bolder;
  font-size: 1.5em;

  margin-bottom: 0.5rem;
`;

const MoaContainer = styled(Container)`
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
