import { useState, useEffect } from 'react';

import HomePage from 'pages/HomePage';
import TeamSearchPage from 'pages/TeamSearchPage';
import TeamMemberSearchPage from 'pages/TeamMemberSearchPage';
import TeamDetailPage from 'pages/TeamDetailPage';
import TeamCreatePage from './pages/TeamCreatePage';
import TeamUpdatePage from './pages/TeamUpdatePage';
import ProfileEditPage from 'pages/ProfileEditPage';
import ProfilePage from 'pages/ProfilePage';
import NotFoundPage from 'pages/NotFoundPage';
import ErrorPage from 'pages/ErrorPage';

import Navbar from 'components/common/navbar/Navbar';
import ScrollToTopButton from 'components/common/Button/ScrollToTopButton';

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import styled from 'styled-components';

export default function App() {
  return (
    <BrowserRouter>
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
