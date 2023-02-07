import styled from '@emotion/styled';
import { TextField } from '@mui/material';

function Review(props) {
  const [name, review, date] = [props.name, props.review];
  return (
    <ReviewTextField
      fullWidth
      label={name}
      defaultValue={review}
      InputProps={{
        readOnly: true,
      }}
      variant="standard"
      multiline
      helperText={date}
      rows={2}
    />
  );
}

const ReviewTextField = styled(TextField)`
  padding-bottom: 1rem;
`;

export default Review;
