package bundesliga;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeamTest {

    

    @Test
    public void test_constructorValid(){
        Team fcb = new Team("  FC Bayern München");
        assertEquals("Name not properly set. Whitespaces must be trimmed.", "FC Bayern München", fcb.getName());
        Team.removeTeam("FC Bayern München");
    }

    @Test
    public void test_constructorInvalid(){
        Team fcb = new Team("  FC Bayern München");
        assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class, () -> new Team(null));
        assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class, () -> new Team(" "));
        assertThrows("Must throw IllegalArgumentException on team already in team store", IllegalArgumentException.class, () -> new Team("FC Bayern München"));
        Team.removeTeam("FC Bayern München");
    }

    @Test
    public void test_contains(){
        Team fcb = new Team("  FC Bayern München");
        assertTrue("contains must return true, when team is in team store", Team.contains("FC Bayern München"));
        assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class, () -> Team.contains(null));
        assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class, () -> Team.contains(" "));
        Team.removeTeam("FC Bayern München");
    }

    @Test
    public void test_getTeam(){
        Team fcb = new Team("  FC Bayern München");
        assertSame("Team from team-store is wrong", fcb, Team.getTeam("FC Bayern München"));
        assertSame("Team from team-store is wrong", fcb, Team.getTeam("  FC bAYERN    mÜNCHEN    "));
        assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class, () -> Team.getTeam(null));
        assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class, () -> Team.getTeam(" "));
        assertThrows("Must throw IllegalArgumentException if no team for the name is found", IllegalArgumentException.class, () -> Team.getTeam("Jahn Regensburg"));
        Team.removeTeam("FC Bayern München");

    }

    
}
