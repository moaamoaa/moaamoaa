import React from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import styled from '@emotion/styled';
import { Grid, Button } from '@mui/material';
import customAxios from 'utils/axios';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { handleSuccessState } from 'redux/snack';
import scrollToTop from 'utils/scrollToTop';
import removeData from 'utils/removeData';

function ProfileDeletePage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleDeleteAccount = () => {
    customAxios.authAxios
      .delete('/users')
      .then(() => {
        navigate('/');
        scrollToTop();
        removeData();
        dispatch(
          handleSuccessState({
            open: true,
            message: '계정이 삭제 되었습니다.',
            severity: 'success',
          }),
        );
      })
      .catch(() => {
        dispatch(
          handleSuccessState({
            open: true,
            message: '탈퇴에 실패하였습니다.',
            severity: 'error',
          }),
        );
      });
  };
  return (
    <DeleteContainer fixed>
      <DeleteGridContainer container>
        <DeleteGrid item xs={12}>
          <Typography id="title" variant="h6" color="initial">
            이 선택은 되돌릴 수 없습니다.
          </Typography>
        </DeleteGrid>
        <DeleteGrid item xs={12}>
          <Button
            id="title"
            variant="contained"
            color="error"
            onClick={handleDeleteAccount}
          >
            회원 탈퇴
          </Button>
        </DeleteGrid>
      </DeleteGridContainer>
    </DeleteContainer>
  );
}

const DeleteContainer = styled(Container)`
  height: 100vh;
  width: 100vw;
`;

const DeleteGridContainer = styled(Grid)`
  position: absolute;
  width: 50%;
  height: 20%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  @media (max-width: 500px) {
    width: 100%;
  }
`;

const DeleteGrid = styled(Grid)`
  position: relative;
  display: flex;
  justify-content: center;
  height: 3rem;
`;

export default ProfileDeletePage;
