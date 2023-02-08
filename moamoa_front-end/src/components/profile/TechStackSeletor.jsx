import { Autocomplete, TextField } from '@mui/material';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';

function TechStackSeletor() {
  let tech = useSelector(state => state.search.tech);

  useEffect(() => {
    let techs = tech.map(category => {
      console.log(category.techStacks);
      return category.techStacks;
    });

    console.log(techs);
  }, []);
  return (
    <Autocomplete
      fullWidth
      multiple
      id="tags-standard"
      options={tech}
      getOptionLabel={option => option.title}
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
