import { useDispatch, useSelector } from 'react-redux';
import { handleSuccessState } from 'redux/snack';
import { Snackbar, Alert, Slide } from '@mui/material';

function SlideTransition(props) {
  return <Slide {...props} direction="left" />;
}

export default function MoaSnackbar() {
  const vertical = useSelector(state => state.snack.vertical);
  const horizontal = useSelector(state => state.snack.horizontal);
  const open = useSelector(state => state.snack.open);
  const message = useSelector(state => state.snack.message);
  const severity = useSelector(state => state.snack.severity);

  const dispatch = useDispatch();

  const handleClose = () => {
    dispatch(handleSuccessState({ open: false, massage: '' }));
  };

  return (
    <>
      <Snackbar
        open={open}
        autoHideDuration={2000} // SetTimeOut
        anchorOrigin={{ vertical, horizontal }}
        TransitionComponent={SlideTransition}
        onClose={handleClose}
        key={vertical + horizontal}
      >
        <Slide direction="right">
          <Alert elevation={6} severity={severity}>
            {message}
          </Alert>
        </Slide>
      </Snackbar>
    </>
  );
}
