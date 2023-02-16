import axios from 'axios';
import Cookies from 'js-cookie';
import { useSelector } from 'react-redux';
import removeData from './removeData';

let baseURL;

if (process.env.NODE_ENV === 'development') {
  baseURL = 'http://localhost:8080/api';
} else {
  baseURL = 'https://moaamoaa.com/api';
}

const accessToken = Cookies.get('access_token');

// refresh token을 사용해 access token을 재발급 받는 함수
const reissueAccessToken = async () => {
  const refreshToken = Cookies.get('REFRESH_TOKEN');
  if (!refreshToken) {
    throw new Error('No refresh token found.');
  }

  const response = await axios.post(
    '/users/reissue',
    { refreshToken },
    { withCredentials: true },
  );
  const { accessToken } = response.data;
  Cookies.set('access_token', accessToken, { expires: 1 });
  return accessToken;
};

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

// interceptor 추가
authAxios.interceptors.response.use(
  response => response,
  async error => {
    // 요청 실패 시 401 에러인 경우에만 실행
    if (error.response && error.response.status === 401) {
      try {
        const accessToken = await reissueAccessToken();
        // 재발급 받은 access token을 헤더에 추가
        error.config.headers.Authorization = `Bearer ${accessToken}`;
        // 재시도
        return authAxios.request(error.config);
      } catch (err) {
        // refresh token이 유효하지 않은 경우 로컬 스토리지를 비우며 로그아웃 처리
        localStorage.clear();
      }
    }
    return Promise.reject(error);
  },
);

imageAxios.interceptors.response.use(
  response => response,
  async error => {
    if (error.response && error.response.status === 401) {
      try {
        const accessToken = await reissueAccessToken();
        error.config.headers.Authorization = `Bearer ${accessToken}`;
        return authAxios.request(error.config);
      } catch (err) {
        // refresh token이 유효하지 않은 경우 로그아웃 처리 등
        removeData();
      }
    }
    return Promise.reject(error);
  },
);

const customAxios = { basicAxios, authAxios, imageAxios };
export default customAxios;
