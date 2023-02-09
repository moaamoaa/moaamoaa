import { Autocomplete, TextField } from '@mui/material';
import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';

function TechStackSeletor(props) {
  let techs = [];
  const tech = useSelector(state => state.search.tech);
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [isMobile, setIsMobile] = useState(window.innerWidth <= 500);

  tech.map(category => {
    techs.push(...category.techStacks);
  });

  const deduplicatedTechs = techs.reduce((acc, current) => {
    const found = acc.find(obj => obj.name === current.name);
    if (!found || current.id < found.id) {
      return [...acc, current];
    }
    return acc;
  }, []);

  const handleSelectedTech = (event, value) => {
    // const selectValue = value.map(sideProject => {
    //   return {
    //     tech_stack_no: sideProject.id,
    //     logo: sideProject.logo,
    //     name: sideProject.name,
    //   };
    // });
    props.setSelectedValue(value);
  };

  useEffect(() => {
    const handleWindowResize = () => setWindowWidth(window.innerWidth);
    setIsMobile(windowWidth < 500);

    window.addEventListener('resize', handleWindowResize);
    return () => window.removeEventListener('resize', handleWindowResize);
  }, [windowWidth]);

  return (
    <Autocomplete
      fullWidth
      multiple={true}
      id="tags-standard"
      options={deduplicatedTechs}
      getOptionLabel={option => option.name}
      onChange={handleSelectedTech}
      renderInput={params => (
        <TextField
          {...params}
          fullWidth
          variant="standard"
          placeholder={
            isMobile ? '기술스택' : '검색을 통해 기술스택 선택하세요.'
          }
        />
      )}
    />
  );
}

export default TechStackSeletor;
