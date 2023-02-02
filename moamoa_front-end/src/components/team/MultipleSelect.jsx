import * as React from 'react';
import { forwardRef } from 'react';

import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Chip from '@mui/material/Chip';

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

const teches = [
  'C', // 숫자 1로 백에 전달해야
  'C#',
  'C++',
  'Java',
  'Python',
  'Kotlin',
  'Go',
  'Nest.js',
  'Node.js',
  'Django',
  'Spring',
  'MySQL',
  'GraphQL',
  'Redis',
  'MongoDB',
  'Firebase',
  'Javascript',
  'Typescript',
  'React',
  'Vue',
  'Angular',
  'Svelte',
  'Swift',
  'SwiftUI',
  'Flutter',
  'AWS',
  'Docker',
  'Kubernetes',
  'Jenkins',
  'Figma',
  'Git',
  'Unity',
];

function getStyles(tech, personTech, theme) {
  return {
    fontWeight:
      personTech.indexOf(tech) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

const MultipleSelect = forwardRef((props, ref) => {
  const theme = useTheme();
  const [personTech, setPersonTech] = React.useState([]);

  const handleChange = event => {
    const {
      target: { value },
    } = event;
    setPersonTech(
      // On autofill we get a stringified value.
      typeof value === 'number' ? value.split(',') : value, // 값을 string -> number 로 변경*
    );
    ref.current = event.target.value;
  };

  return (
    <div>
      <FormControl fullWidth>
        <InputLabel id="demo-multiple-chip-label">복수 선택</InputLabel>
        <Select
          labelId="demo-multiple-chip-label"
          id="demo-multiple-chip"
          multiple
          value={personTech}
          onChange={handleChange}
          input={<OutlinedInput id="select-multiple-chip" label="Chip" />}
          renderValue={selected => (
            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
              {selected.map(value => (
                <Chip key={value} label={value} />
              ))}
            </Box>
          )}
          MenuProps={MenuProps}
        >
          {teches.map(tech => (
            <MenuItem
              key={tech}
              value={teches.indexOf(tech) + 1} // 숫자 리스트로 보내줘야 해서 {tech}를 이렇게 변경*
              style={getStyles(tech, personTech, theme)}
            >
              {tech}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
});
export default MultipleSelect;
