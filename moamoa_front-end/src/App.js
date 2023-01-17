import HomePage from './pages/HomePage';
import TeamSearchPage from './pages/TeamSearchPage';
import TeamMemberSearchPage from './pages/TeamMemberSearchPage';
import Navbar from './components/Navbar';

import { BrowserRouter, Routes, Route } from 'react-router-dom';

export default function App() {
  return (
    <BrowserRouter>
      <Navbar></Navbar>
      <Routes>
        <Route
          path="/TeamMemberSearchPage"
          element={<TeamMemberSearchPage />}
        />
        <Route path="/TeamSearchPage" element={<TeamSearchPage />} />
        <Route path="/" element={<HomePage />} />
      </Routes>
    </BrowserRouter>
  );
}
