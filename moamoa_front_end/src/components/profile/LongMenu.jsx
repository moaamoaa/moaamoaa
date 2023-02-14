import { useState, useEffect } from 'react';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import MoreVertIcon from '@mui/icons-material/MoreVert';

export default function LongMenu(props) {
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  const handleClick = event => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => setAnchorEl(null);

  const handleOptionClick = handler => {
    handleClose();
    setTimeout(handler, 500);
  };

  const handleOpenEdit = () => handleOptionClick(props.handleOpenEdit);
  const handleDelete = () => handleOptionClick(props.handleDelete);
  const handleSuccessEdit = () => handleOptionClick(props.handleSuccessEdit);
  const handleCancelEdit = () => handleOptionClick(props.handleCancelEdit);

  const options = props.isEdit
    ? [
        { title: '완료', handler: handleSuccessEdit },
        { title: '취소', handler: handleCancelEdit },
      ]
    : [
        { title: '수정', handler: handleOpenEdit },
        { title: '삭제', handler: handleDelete },
      ];

  useEffect(() => {
    const handleWindowResize = () => setWindowWidth(window.innerWidth);
    handleClose();

    window.addEventListener('resize', handleWindowResize);
    return () => window.removeEventListener('resize', handleWindowResize);
  }, [windowWidth]);
  return (
    <>
      <IconButton
        aria-label="more"
        id="long-button"
        aria-controls={open ? 'long-menu' : undefined}
        aria-expanded={open ? 'true' : undefined}
        aria-haspopup="true"
        onClick={handleClick}
        sx={{ padding: '0', display: 'flex', alignItems: 'start' }}
      >
        <MoreVertIcon />
      </IconButton>
      <Menu
        id="long-menu"
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'right',
        }}
        transformOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        MenuListProps={{
          'aria-labelledby': 'long-button',
        }}
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        PaperProps={{
          elevation: 0,
          sx: {
            overflow: 'visible',
            filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
            mt: 1.5,
          },
        }}
      >
        {options.map(option => (
          <MenuItem
            key={option.title}
            onClick={option.handler}
            sx={{
              width: '6rem',
            }}
            color={option.title === '삭제' ? 'warning' : ''}
          >
            {option.title}
          </MenuItem>
        ))}
      </Menu>
    </>
  );
}
