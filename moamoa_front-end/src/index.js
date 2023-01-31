import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from 'reportWebVitals';
import { Provider } from 'react-redux';
import Store from './redux/Store';

ReactDOM.render(
  <React.StrictMode>
    <Provider Store={Store}>
      <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root'),
);

reportWebVitals();
