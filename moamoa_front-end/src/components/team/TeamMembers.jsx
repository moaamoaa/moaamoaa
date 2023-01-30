import Container from '@mui/material/Container';
import TeamMemberList from 'components/team/TeamMemberList';

export default function TeamMembers() {
  return (
    <>
      <Container fixed>
        <h2>팀원 소개</h2>
        <TeamMemberList></TeamMemberList>
      </Container>
    </>
  );
}
