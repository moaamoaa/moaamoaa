import axios from 'axios';

let baseURL;

if (process.env.NODE_ENV === 'development') {
  baseURL = 'http://localhost:8080';
} else {
  baseURL = 'back_server:8080';
  // baseURL = 'https://moaamoaa.com/';
}

const getCookie = cname => {
  const name = cname + '=';
  const decodedCookie = decodeURIComponent(document.cookie);
  const ca = decodedCookie.split(';');
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) === 0) {
      return c.substring(name.length, c.length);
    }
  }
  return '';
};

const basicAxios = axios.create({
  baseURL: baseURL,
});

const authAxios = axios.create({
  baseURL: baseURL,
  headers: {
    Authorization: `Bearer ${getCookie('access_token')}`,
  },
});

const imageAxios = axios.create({
  baseURL: baseURL,
  headers: {
    Authorization: `Bearer ${getCookie('access_token')}`,
    'Content-Type': 'multipart/form-data',
  },
});

const customAxios = { basicAxios, authAxios, imageAxios };
export default customAxios;
