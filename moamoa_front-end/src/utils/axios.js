import axios from 'axios';
import Cookies from 'js-cookie';

let baseURL;

if (process.env.NODE_ENV === 'development') {
  baseURL = 'http://localhost:8080';
} else {
  baseURL = 'back_server:8080/api';
  // baseURL = 'https://moaamoaa.com/';
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
    // 'Content-Type': 'multipart/form-data',
  },
});

const customAxios = { basicAxios, authAxios, imageAxios };
export default customAxios;
