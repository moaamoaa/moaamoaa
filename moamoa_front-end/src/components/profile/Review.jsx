import styled from '@emotion/styled';
import {
  Button,
  Grid,
  TextField,
  Container,
  Typography,
  Tooltip,
} from '@mui/material';
import LongMenu from 'components/profile/LongMenu';
import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { handleEditReview, handleSuccessReview } from 'redux/profile';
import customAxios from 'utils/axios';
import scrollToTop from 'utils/scrollToTop';

function Review(props) {
  const [isEdit, setIsEdit] = useState(false);
  const [context, setContext] = useState(
    props.review.context ? props.review.context : '',
  );

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleOpenEdit = () => {
    setIsEdit(true);
  };

  const handleSuccessDelete = () => {
    customAxios.authAxios
      .delete(`/profile/review/${props.review.id}`)
      .then(response => {
        dispatch(
          handleSuccessReview({
            reviews: response.data,
          }),
        );

        setIsEdit(false);
      })
      .catch(error => {
        console.log(error);
      });
  };

  const handleSuccessEdit = () => {
    console.log(props.review.id, context);
    customAxios.authAxios
      .put(`/profile/review`, {
        id: props.review.id,
        context: context,
      })
      .then(response => {
        console.log(response);
        dispatch(
          handleEditReview({
            review: response.data.review,
          }),
        );

        setIsEdit(false);
      })
      .catch(error => {
        console.log(error);
      });
  };

  const handleCancelEdit = () => {
    setIsEdit(false);
    setContext(props.review.context);
  };

  const limit = 100;

  const handleChangeContext = event => {
    if (event.target.value.length <= limit) {
      setContext(event.target.value);
    } else {
      setContext(context.slice(0, limit - 1));
    }
  };

  const handleOpenUserProfile = () => {
    navigate(`/ProfilePage/?profileId=${props.review.id}`);
    scrollToTop();
  };

  return (
    <Grid container sx={{ paddingBottom: '.5rem' }}>
      {isEdit ? (
        <>
          <Grid item xs={12}>
            <TextField
              fullWidth
              multiline
              helperText={`${context.length}/${limit}`}
              onChange={handleChangeContext}
              value={context}
              sx={{ minHeight: '4rem' }}
            />
          </Grid>
          <Grid
            item
            xs={12}
            sx={{
              display: 'flex',
              justifyContent: 'end',
              marginTop: '-1.5rem',
            }}
          >
            <Button onClick={handleSuccessEdit} variant="text" color="primary">
              수정
            </Button>
            <Button onClick={handleCancelEdit} variant="text" color="primary">
              취소
            </Button>
          </Grid>
        </>
      ) : (
        <Container sx={{ padding: '0 !important' }}>
          <Grid
            item
            xs={12}
            justifyContent="space-between"
            alignItems="start"
            sx={{ display: 'flex' }}
          >
            <Tooltip title="프로필로 이동">
              <Typography
                variant="body1"
                color="initial"
                sx={{ fontWeight: '600', cursor: 'pointer' }}
                onClick={handleOpenUserProfile}
              >
                {props.review.sender}
              </Typography>
            </Tooltip>
            <LongMenu
              isEdit={isEdit}
              handleOpenEdit={handleOpenEdit}
              handleDelete={handleSuccessDelete}
            ></LongMenu>
          </Grid>
          <Grid item xs={12}>
            <ReviewTextField
              fullWidth
              defaultValue={props.review.context}
              InputProps={{
                readOnly: true,
              }}
              variant="standard"
              multiline
              helperText={props.review.time}
              rows={2}
            />
          </Grid>
        </Container>
      )}
    </Grid>
  );
}

const ReviewTextField = styled(TextField)`
  padding-bottom: 1rem;
`;

export default Review;
