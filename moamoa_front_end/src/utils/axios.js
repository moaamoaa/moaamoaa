import axios from 'axios';
import Cookies from 'js-cookie';
import removeData from './removeData';

let baseURL;

if (process.env.NODE_ENV === 'development') {
  baseURL = 'http://localhost:8080/api';
} else {
  baseURL = 'https://moaamoaa.com/api';
}

const accessToken = Cookies.get('access_token');

const basicAxios = axios.create({
  baseURL: baseURL,
});

const authAxios = axios.create({
  baseURL: baseURL,
  headers: {
    Authorization: `Bearer ${accessToken}`,
  },
});

const imageAxios = axios.create({
  baseURL: baseURL,
  mode: 'cors',
  headers: {
    Authorization: `Bearer ${accessToken}`,
    'Content-Type': 'multipart/form-data',
  },
});

authAxios.interceptors.response.use(
  response => {
    return response;
  },
  async error => {
    const {
      config,
      response: { status },
    } = error;

    const originalRequest = config;

    if (status === 401) {
      try {
        authAxios
          .post('/users/reissue', {}, { withCredentials: true })
          .then(response => {
            const token = response.data.accessToken;
            Cookies.set('access_token', token, { expires: 1 });
            console.log(token);
          })
          .catch(error => {
            console.log(error);
            removeData();
            //로그아웃
          });

        location.reload();

        setTimeout(() => {
          return authAxios(originalRequest);
        }, 500);
      } catch (err) {
        new Error(err);
      }
    }
    return Promise.reject(error);
  },
);

imageAxios.interceptors.response.use(
  response => {
    return response;
  },
  async error => {
    const {
      config,
      response: { status },
    } = error;

    const originalRequest = config;

    if (status === 401) {
      try {
        authAxios
          .post('/users/reissue', {}, { withCredentials: true })
          .then(response => {
            const token = response.data.accessToken;
            Cookies.set('access_token', token, { expires: 1 });
            console.log(token);
          })
          .catch(error => {
            console.log(error);
            removeData();
            //로그아웃
          });

        location.reload();

        setTimeout(() => {
          return authAxios(originalRequest);
        }, 500);
      } catch (err) {
        new Error(err);
      }
    }
    return Promise.reject(error);
  },
);

const customAxios = { basicAxios, authAxios, imageAxios };
export default customAxios;
