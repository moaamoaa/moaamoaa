import React from 'react';
import { Button, Menu, MenuItem } from '@mui/material/';
import PopupState, { bindTrigger, bindMenu } from 'material-ui-popup-state';

function CustomSelectFilter(props) {
  const filterDrop = props.filterDrop;

  return (
    <>
      <PopupState variant="popover" popupId="demo-popup-menu">
        {popupState => (
          <React.Fragment>
            {
              <Button
                fullWidth
                variant="contained"
                {...bindTrigger(popupState)}
              >
                {filterDrop.title}
              </Button>
            }

            <Menu {...bindMenu(popupState)}>
              {filterDrop.menus.map((menu, idx) => {
                <MenuItem key={idx} onClick={popupState.close}>
                  {menu}
                </MenuItem>;
              })}
            </Menu>
          </React.Fragment>
        )}
      </PopupState>
    </>
  );
}

export default CustomSelectFilter;
