import HomePage from './pages/HomePage';
import TeamSearchPage from './pages/TeamSearchPage';
import TeamMemberSearchPage from './pages/TeamMemberSearchPage';
import TeamDetailPage from './pages/TeamDetailPage';
import ProfilePage from './pages/ProfilePage';

import Navbar from './components/common/navbar/Navbar';

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import styled from 'styled-components';

export default function App() {
  return (
    <BrowserRouter>
      <Navbar></Navbar>
      <Moa>
        <Routes>
          <Route path="/TeamDetailPage" element={<TeamDetailPage />} />
          <Route path="/ProfilePage" element={<ProfilePage />} />
          <Route
            path="/TeamMemberSearchPage"
            element={<TeamMemberSearchPage />}
          />
          <Route path="/TeamSearchPage" element={<TeamSearchPage />} />
          <Route path="/" element={<HomePage />} />
        </Routes>
      </Moa>
    </BrowserRouter>
  );
}

const Moa = styled.main`
  position: relative;
  top: 56px;
`;
