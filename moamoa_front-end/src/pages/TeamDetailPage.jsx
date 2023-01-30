import Container from '@mui/material/Container';
import TeamInfo from 'components/team/TeamInfo';
import TeamIntroduce from 'components/team/TeamIntroduce';
import TeamMembers from 'components/team/TeamMembers';

export default function TeamDetailPage() {
  return (
    <Container fixed>
      <TeamInfo></TeamInfo>
      <TeamIntroduce></TeamIntroduce>
      <TeamMembers></TeamMembers>
    </Container>
  );
}
