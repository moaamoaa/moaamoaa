import Cookies from 'js-cookie';
const removeData = () => {
  localStorage.clear();

  Cookies.remove('REFRESH_TOKEN');
  Cookies.remove('access_token');
};

export default removeData;
