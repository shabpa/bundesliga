package bundesliga;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class GameTest {

    @Test
    public void testCompareTo(){
        Team sge = new Team("Eintracht Frankfurt");
        Team fcu = new Team("1. FC Union Berlin");
        Team bmg = new Team("Borussia Mönchengladbach");
        Team wob = new Team("VfL Wolfsburg");
        Team boc = new Team("VfL Bochum");
        League bl = new League("Bundesliga");
        bl.addTeam("Eintracht Frankfurt");
        bl.addTeam("1. FC Union Berlin");
        bl.addTeam("Borussia Mönchengladbach");
        bl.addTeam("VfL Wolfsburg");
        bl.addTeam("VfL Bochum");
 
        
        Game game1 = new Game("Bundesliga", 5, "VfL Wolfsburg", "Borussia Mönchengladbach", "1:2");
        Game game2 = new Game("Bundesliga", 1, "VfL Bochum", "Borussia Mönchengladbach","2:3");
        Game game3 = new Game("Bundesliga",1, "1. FC Union Berlin", "Eintracht Frankfurt", "1:1");

        Set<Game> games = bl.getGames();
        ArrayList<Game> original = new ArrayList<Game>(games);
        assertEquals("Natural order in game does not work properly", game1, original.get(2));
        assertEquals("Natural order in game does not work properly", game2, original.get(1));
        assertEquals("Natural order in game does not work properly", game3, original.get(0));

        Team.removeTeam("Eintracht Frankfurt");
        Team.removeTeam("1. FC Union Berlin");
        Team.removeTeam("Borussia Mönchengladbach");
        Team.removeTeam("VfL Wolfsburg");
        Team.removeTeam("VfL Bochum");
        League.removeLeague("Bundesliga");
        
    }
    
}
