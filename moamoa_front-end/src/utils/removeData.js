const removeData = () => {
  localStorage.clear();
  document.cookie = '';
};

export default removeData;
