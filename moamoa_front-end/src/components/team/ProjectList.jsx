import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import ProjectItem from 'components/team/ProjectItem';

export default function ProjectList(props) {
  if (props.type === 'project') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 프로젝트 */}
        {props.projects &&
          props.projects.map((project, idx) => (
            <span key={idx}>
              <ProjectItem project={project} type={props.type}></ProjectItem>
            </span>
          ))}
      </List>
    );
  } else if (props.type === 'study') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 프로젝트 */}
        {props.studies &&
          props.studies.map((study, idx) => (
            <span key={idx}>
              <ProjectItem project={study} type={props.type}></ProjectItem>
            </span>
          ))}
      </List>
    );
  }
}

//   return (
//     <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
//       {/* 첫번째 */}
//       <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
//         <ListItemAvatar>
//           <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
//         </ListItemAvatar>
//         <ListItemText
//           primary="빅데이터!"
//           secondary={
//             <React.Fragment>
//               <Typography
//                 sx={{ display: 'inline' }}
//                 component="span"
//                 variant="body2"
//                 color="text.primary"
//               >
//                 팀소개
//               </Typography>
//               {'팀페이지이동'}
//             </React.Fragment>
//           }
//         />
//       </ListItemButton>
//       {/* 두번째 */}
//       <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
//         <ListItemAvatar>
//           <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
//         </ListItemAvatar>
//         <ListItemText
//           primary="빅데이터!"
//           secondary={
//             <React.Fragment>
//               <Typography
//                 sx={{ display: 'inline' }}
//                 component="span"
//                 variant="body2"
//                 color="text.primary"
//               >
//                 팀소개
//               </Typography>
//               {'팀페이지이동'}
//             </React.Fragment>
//           }
//         />
//       </ListItemButton>
//       {/* 세번째 */}
//       <ListItemButton sx={{ pl: 4 }} alignItems="flex-start">
//         <ListItemAvatar>
//           <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
//         </ListItemAvatar>
//         <ListItemText
//           primary="빅데이터!"
//           secondary={
//             <React.Fragment>
//               <Typography
//                 sx={{ display: 'inline' }}
//                 component="span"
//                 variant="body2"
//                 color="text.primary"
//               >
//                 팀소개
//               </Typography>
//               {'팀페이지이동'}
//             </React.Fragment>
//           }
//         />
//       </ListItemButton>
//       {/* 리스트 종료 */}
//     </List>
//   );
// }
