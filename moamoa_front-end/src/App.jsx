import { BrowserRouter, Routes, Route } from 'react-router-dom';
import styled from '@emotion/styled';

import HomePage from 'pages/HomePage';
import TeamSearchPage from 'pages/TeamSearchPage';
import TeamMemberSearchPage from 'pages/TeamMemberSearchPage';
import TeamCreatePage from 'pages/TeamCreatePage';
import TeamDetailPage from 'pages/TeamDetailPage';
import TeamUpdatePage from 'pages/TeamUpdatePage';
import ProfilePage from 'pages/ProfilePage';
import ProfileEditPage from 'pages/ProfileEditPage';
import NotFoundPage from 'pages/NotFoundPage';
import ErrorPage from 'pages/ErrorPage';

import Navbar from 'components/common/navbar/Navbar';
import ScrollToTopButton from 'components/common/button/ScrollToTopButton';
import Snackbar from 'components/common/snackbar/Snackbar';

export default function App() {
  // window.onbeforeunload = removeData;
  // 이거있으면 리덕스 상태유지 안 됩니다

  return (
    <BrowserRouter>
      <Snackbar></Snackbar>
      <Navbar></Navbar>
      <Moa>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/TeamSearchPage" element={<TeamSearchPage />} />
          <Route
            path="/TeamMemberSearchPage"
            element={<TeamMemberSearchPage />}
          />
          <Route path="/TeamCreatePage" element={<TeamCreatePage />} />
          <Route path="/TeamDetailPage" element={<TeamDetailPage />} />
          <Route path="/TeamUpdatePage" element={<TeamUpdatePage />} />
          <Route path="/ProfilePage" element={<ProfilePage />} />
          <Route path="/ProfileEditPage" element={<ProfileEditPage />} />
          <Route path="/ErrorPage" element={<ErrorPage />} />
          <Route path="/*" element={<NotFoundPage />} />
        </Routes>
        <ScrollToTopButton></ScrollToTopButton>
      </Moa>
    </BrowserRouter>
  );
}

const Moa = styled.main`
  position: relative;
  top: 56px;
`;
