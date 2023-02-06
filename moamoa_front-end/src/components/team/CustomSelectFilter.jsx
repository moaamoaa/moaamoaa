import React from 'react';
import { searchState } from 'redux/search';
import { useDispatch, useSelector } from 'react-redux';
import { Button, MenuItem } from '@mui/material/';
import HoverMenu from 'material-ui-popup-state/HoverMenu';
import {
  usePopupState,
  bindHover,
  bindMenu,
} from 'material-ui-popup-state/hooks';

const CustomSelectFilter = props => {
  const filterDrop = props.filterDrop;
  const dispatch = useDispatch();
  const area = useSelector(state => state.search.area);
  const tech = useSelector(state => state.search.tech);
  const popupState = usePopupState({ variant: 'popover', popupId: 'demoMenu' });
  const handleSandManu = e => {
    popupState.close();
    dispatch(
      searchState({
        area: area,
        tech: tech,
        menu: e.target.outerText,
      }),
    );

    console.log(e.target.outerText);
  };
  return (
    <React.Fragment>
      <Button variant="contained" {...bindHover(popupState)}>
        {filterDrop.title}
      </Button>
      <HoverMenu
        {...bindMenu(popupState)}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'left' }}
        transformOrigin={{ vertical: 'top', horizontal: 'left' }}
      >
        {filterDrop.menus.map((menu, idx) => {
          return (
            <MenuItem key={idx} onClick={handleSandManu} value={menu}>
              {menu}
            </MenuItem>
          );
        })}
      </HoverMenu>
    </React.Fragment>
  );
};

export default CustomSelectFilter;
