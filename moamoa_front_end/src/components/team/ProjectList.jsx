import * as React from 'react';
import List from '@mui/material/List';
import ProjectItem from 'components/team/ProjectItem';

export default function ProjectList(props) {
  if (props.type === 'project') {
    return (
      <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {/* 프로젝트 */}
        {props.projects &&
          props.projects.map((project, idx) => (
            <span key={idx}>
              <ProjectItem
                projectstudy={project}
                type={props.type}
              ></ProjectItem>
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
              <ProjectItem projectstudy={study} type={props.type}></ProjectItem>
            </span>
          ))}
      </List>
    );
  }
}
