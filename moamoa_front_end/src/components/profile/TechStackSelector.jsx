import { Autocomplete, TextField } from '@mui/material';
import { useSelector } from 'react-redux';
import useMobile from 'hooks/useMobile';

function TechStackSelector(props) {
  let techs = [];
  const tech = useSelector(state => state.search.tech);
  const isMobile = useMobile();

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
    props.setSelectedValue(value);
  };

  return (
    <>
      <Autocomplete
        fullWidth
        multiple={true}
        id="tags-standard"
        options={deduplicatedTechs}
        getOptionLabel={option => option.name}
        onChange={handleSelectedTech}
        sx={{ display: 'flex', alignItems: 'end' }}
        renderInput={params => (
          <TextField
            {...params}
            fullWidth
            placeholder={
              isMobile ? '기술스택' : '검색을 통해 기술스택 선택하세요.'
            }
          />
        )}
      />
    </>
  );
}

export default TechStackSelector;
