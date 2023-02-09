import styled from '@emotion/styled';
import { TextField } from '@mui/material';

function Review(props) {
  console.log(props);
  return (
    <ReviewTextField
      fullWidth
      label={props.sender}
      defaultValue={props.context}
      InputProps={{
        readOnly: true,
      }}
      variant="standard"
      multiline
      helperText={props.time}
      rows={2}
    />
  );
}

const ReviewTextField = styled(TextField)`
  padding-bottom: 1rem;
`;

export default Review;
