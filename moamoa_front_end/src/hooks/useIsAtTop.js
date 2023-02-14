import { useState, useEffect } from 'react';

const useIsAtTop = () => {
  const [isAtTop, setIsAtTop] = useState(true);

  useEffect(() => {
    const handleWindowScroll = () => {
      setIsAtTop(window.pageYOffset <= 200);
    };
    window.addEventListener('scroll', handleWindowScroll);
    return () => {
      window.removeEventListener('scroll', handleWindowScroll);
    };
  }, []);

  return isAtTop;
};

export default useIsAtTop;
