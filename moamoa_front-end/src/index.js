import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from 'reportWebVitals';
import { Provider } from 'react-redux';
import Store from './redux/Store';

import { PersistGate } from 'redux-persist/integration/react';
import { persistStore } from 'redux-persist';

// store에 persist 적용, 아래에 loading은 로딩시 보여줄 컴포넌트 설정
export const persistor = persistStore(Store);

ReactDOM.render(
  <React.StrictMode>
    <Provider store={Store}>
      <PersistGate loading={null} persistor={persistor}>
        <App />
      </PersistGate>
    </Provider>
  </React.StrictMode>,
  document.getElementById('root'),
);

reportWebVitals();
