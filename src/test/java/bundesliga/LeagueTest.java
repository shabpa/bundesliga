package bundesliga;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;

public class LeagueTest {
    
    @Test
    public void testConstructor() {
        League league = new League("   Bundesliga");
        
        assertEquals("Name not properly set. Whitespaces must be trimmed.", "Bundesliga", league.getName());
        assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class, () -> new League(null));
        assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class, () -> new League(" "));
        assertThrows("Must throw IllegalArgumentException on league already in league store", IllegalArgumentException.class, () -> new League("Bundesliga"));
        League.removeLeague("Bundesliga");
    }


    @Test
    public void testAddTeam() {
        League league = new League("Bundesliga");
        Team team = new Team("FC Bayern München");
        league.addTeam(team);

        assertTrue("The team should have been added to the league", league.getTeams().contains(team));

        Team team2 = null;
        assertThrows("Must throw IllegalArgumentException on team is null", IllegalArgumentException.class, ()->league.addTeam(team2));
        assertThrows("Must throw IllegalArgumentException on team already in list", IllegalArgumentException.class, ()->league.addTeam(team));
        Team.removeTeam("FC Bayern München");
        League.removeLeague("Bundesliga");
    }

    @Test
    public void testAddTeamString() {
        League league = new League("Bundesliga");
        Team team = new Team("FC Bayern München");

        league.addTeam("FC Bayern München");
        assertTrue("The team should have been added to the league", league.getTeams().contains(team));

        String team2 = null;
        assertThrows("Must throw IllegalArgumentException on team is null", IllegalArgumentException.class, ()->league.addTeam(team2));
        assertThrows("Must throw IllegalArgumentException on team already in list", IllegalArgumentException.class, ()->league.addTeam("Bayern München"));
        
        
        Team.removeTeam("FC Bayern München");
        League.removeLeague("Bundesliga");
    }



    @Test
    public void testAddGame() {
        League league = new League("Bundesliga");
        Team homeTeam = new Team("FC Bayern München");
        Team awayTeam = new Team("Borussia Dortmund");
        league.addTeam(homeTeam);
        league.addTeam(awayTeam);

        Game game = new Game("Bundesliga", 1, "FC Bayern München", "Borussia Dortmund", "11:0");

        assertTrue("The game should have been added to the league", league.getGames().contains(game));
        assertThrows("Must throw IllegalArgumentException on game is null", IllegalArgumentException.class, ()->league.addGame(null));
        assertThrows("Must throw IllegalArgumentException on game is already in Set", IllegalArgumentException.class, ()->league.addGame(game));
        Team.removeTeam("FC Bayern München");
        Team.removeTeam("Borussia Dortmund");
        League.removeLeague("Bundesliga");
    }

    @Test
    public void testGetTeam(){
        League league = new League("Bundesliga");
        Team team = new Team("FC Bayern München");
        Team team2 = new Team("Borussia Dortmund");
        league.addTeam(team);

        assertSame("The fetched team must be the same than the local team", team, league.getTeam("FC Bayern München"));
        assertThrows("Must throw IllegalArgumentException on team not in local list of teams", IllegalArgumentException.class, ()->league.getTeam("Borussia Dortmund"));
        assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class, () -> league.getTeam(null));
        assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class, () -> league.getTeam(" "));

        Team.removeTeam("FC Bayern München");
        Team.removeTeam("Borussia Dortmund");
        League.removeLeague("Bundesliga");
    }


    @Test
    public void testContainsLeague(){
        League league = new League("Bundesliga");

        assertTrue("league must be in league-store", League.containsLeague("Bundesliga"));
        assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class, () -> League.containsLeague(null));
        assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class, () -> League.containsLeague(" "));

        League.removeLeague("Bundesliga");
    }

    @Test
    public void testGetLeague(){
        League league = new League("Bundesliga");

        assertSame("The fetched league must be the same than the local league", league, League.getLeague("Bundesliga"));
        assertThrows("Must throw IllegalArgumentException on league not in static league-store", IllegalArgumentException.class, ()->League.getLeague("Premier League"));
        assertThrows("Must throw IllegalArgumentException on null name", IllegalArgumentException.class, () -> League.getLeague(null));
        assertThrows("Must throw IllegalArgumentException on empty name", IllegalArgumentException.class, () -> League.getLeague(" "));

        League.removeLeague("Bundesliga");
    }

    @Test
    public void testRemoveLeague(){
        League league = new League("Bundesliga");
        League.removeLeague("Bundesliga");

        assertFalse("League still in store", League.containsLeague("Bundesliga"));
            }



    @Test
    public void testGetTeams(){
        League league = new League("Bundesliga");
        Team homeTeam = new Team("FC Bayern München");
        Team awayTeam = new Team("Borussia Dortmund");
        Team awayTeam2 = new Team("RB Leipzig");
        league.addTeam(homeTeam);
        league.addTeam(awayTeam);

        Set<Team> teams = league.getTeams();
        assertThrows("getTeams must return a unmodifiable Set", UnsupportedOperationException.class,()->teams.add(awayTeam2));

        
        Team.removeTeam("FC Bayern München");
        Team.removeTeam("Borussia Dortmund");
        Team.removeTeam("RB Leipzig");
        League.removeLeague("Bundesliga");
    }

    @Test
    public void testGetGames(){
        League league = new League("Bundesliga");
        Team homeTeam = new Team("FC Bayern München");
        Team awayTeam = new Team("Borussia Dortmund");
        Team awayTeam2 = new Team("RB Leipzig");
        league.addTeam(homeTeam);
        league.addTeam(awayTeam);
        league.addTeam(awayTeam2);

        Game game = new Game("Bundesliga", 1, "FC Bayern München", "Borussia Dortmund", "11:0");
        Game game2 = new Game("Bundesliga", 2, "FC Bayern München", "RB Leipzig", "3:0");
        Set<Game> games = league.getGames();
        
        assertThrows("getGames must return a unmodifiable Set", UnsupportedOperationException.class,()->games.add(game2));

        
        Team.removeTeam("FC Bayern München");
        Team.removeTeam("Borussia Dortmund");
        Team.removeTeam("RB Leipzig");
        League.removeLeague("Bundesliga");
    }
}
