import styled from '@emotion/styled';
import { Paper } from '@mui/material';

export default function ErrorPage() {
  return (
    <BackGroundImage
      sx={{
        position: 'relative',
        color: '#000',
        mb: 4,
        height: '100vh',
        borderRadius: 0,
        backgroundImage: `url(${process.env.PUBLIC_URL}/images/common/500.jpg)`,
        boxShadow: '0px 0px 0px 0px rgb(0 0 0 / 0%)',
        zIndex: -1,
        backgroundPosition: 'center center',
      }}
    ></BackGroundImage>
  );
}

const BackGroundImage = styled(Paper)`
  @media (max-width: 768px) {
    background-image: url('${process.env.PUBLIC_URL}/images/common/500m.jpg');
  }
`;
