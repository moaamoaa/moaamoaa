import axios from 'axios';

const authAxios = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    Authorization: `Bearer ${getCookie('access_token')}`,
  },
});

const basicAxios = axios.create({
  baseURL: 'http://localhost:8080',
});

function getCookie(cname) {
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
}

export default { authAxios, basicAxios };
