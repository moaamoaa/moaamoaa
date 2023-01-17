import HomePage from './pages/HomePage';

function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/TeamMemberSearchPage">TeamMemberSearch</Link>
            </li>
            <li>
              <Link to="/TeamSearchPage">TeamSearch</Link>
            </li>
          </ul>
        </nav>

        <Switch>
          <Route path="/TeamMemberSearchPage">
            <TeamMemberSearchPage />
          </Route>
          <Route path="/TeamSearchPage">
            <TeamSearchPage />
          </Route>
          <Route path="/">
            <HomePage />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
