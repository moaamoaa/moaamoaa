import * as React from 'react';
import List from '@mui/material/List';
import MyProjectItem from 'components/team/MyProjectItem';

export default function MyProjectList(props) {
  if (props.type === 'project') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 프로젝트 */}
        {props.projects &&
          props.projects.map((project, idx) => (
            <span key={idx}>
              <MyProjectItem
                projectstudy={project}
                type={props.type}
              ></MyProjectItem>
            </span>
          ))}
      </List>
    );
  } else if (props.type === 'study') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 스터디 */}
        {props.studies &&
          props.studies.map((study, idx) => (
            <span key={idx}>
              <MyProjectItem
                projectstudy={study}
                type={props.type}
              ></MyProjectItem>
            </span>
          ))}
      </List>
    );
  }
}
