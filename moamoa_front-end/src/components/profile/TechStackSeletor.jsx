import { Autocomplete, TextField } from '@mui/material';
import { useState } from 'react';
import { useSelector } from 'react-redux';

function TechStackSeletor(props) {
  let techs = [];
  const tech = useSelector(state => state.search.tech);

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
    console.log(value);
  };

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
          placeholder="기술스택"
        />
      )}
    />
  );
}

export default TechStackSeletor;
