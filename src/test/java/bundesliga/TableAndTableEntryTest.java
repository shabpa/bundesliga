package bundesliga;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TableAndTableEntryTest {

    @Test
    public void testGetTableNaturalSorted(){
        Team sge = new Team("Eintracht Frankfurt");
        Team fcu = new Team("1. FC Union Berlin");
        Team bmg = new Team("Borussia Mönchengladbach");
        Team wob = new Team("VfL Wolfsburg");

        League bl = new League("Bundesliga");
        bl.addTeam("Eintracht Frankfurt");
        bl.addTeam("1. FC Union Berlin");
        bl.addTeam("Borussia Mönchengladbach");
        bl.addTeam("VfL Wolfsburg");
        
        new Game("Bundesliga",1,"Eintracht Frankfurt","1. FC Union Berlin","1:1");
        new Game("Bundesliga",2,"Eintracht Frankfurt","Borussia Mönchengladbach","3:1");
        new Game("Bundesliga",3,"VfL Wolfsburg","Eintracht Frankfurt","1:2");
        new Game("Bundesliga",2,"VfL Wolfsburg","1. FC Union Berlin","3:4");
        new Game("Bundesliga",3,"1. FC Union Berlin","Borussia Mönchengladbach","2:0");
        new Game("Bundesliga",1,"Borussia Mönchengladbach","VfL Wolfsburg","2:0");

        Table table = bl.getTable();
        ArrayList<TableEntry> natSortEntries = new ArrayList<TableEntry>(table.getTableNaturalSorted()); 

        // |Bundesliga
        // |Verein                   |Sp|S |U |N |Pkte|+/-  |Diff.|
        // --------------------------------------------------------
        // |1. FC Union Berlin       |3 |2 |1 |0 |7   |7:4  |3    |
        // |Eintracht Frankfurt      |3 |2 |1 |0 |7   |6:3  |3    |
        // |Borussia Mönchengladbach |3 |1 |0 |2 |3   |3:5  |-2   |
        // |VfL Wolfsburg            |3 |0 |0 |3 |0   |4:8  |-4   |

        assertSame("Natural order of TableEntry is not correct", fcu, natSortEntries.get(0).getTeam());
        assertSame("Natural order of TableEntry is not correct", sge, natSortEntries.get(1).getTeam());
        assertSame("Natural order of TableEntry is not correct", bmg, natSortEntries.get(2).getTeam());
        assertSame("Natural order of TableEntry is not correct", wob, natSortEntries.get(3).getTeam());

        Team.removeTeam("Eintracht Frankfurt");
        Team.removeTeam("1. FC Union Berlin");
        Team.removeTeam("Borussia Mönchengladbach");
        Team.removeTeam("VfL Wolfsburg");

        League.removeLeague("Bundesliga");
    }

    @Test
    public void testGetTableSortedByName(){
        Team sge = new Team("Eintracht Frankfurt");
        Team fcu = new Team("1. FC Union Berlin");
        Team bmg = new Team("Borussia Mönchengladbach");
        Team wob = new Team("VfL Wolfsburg");

        League bl = new League("Bundesliga");
        bl.addTeam("Eintracht Frankfurt");
        bl.addTeam("1. FC Union Berlin");
        bl.addTeam("Borussia Mönchengladbach");
        bl.addTeam("VfL Wolfsburg");
        
        new Game("Bundesliga",1,"Eintracht Frankfurt","1. FC Union Berlin","1:1");
        new Game("Bundesliga",2,"Eintracht Frankfurt","Borussia Mönchengladbach","3:1");
        new Game("Bundesliga",3,"VfL Wolfsburg","Eintracht Frankfurt","1:2");
        new Game("Bundesliga",2,"VfL Wolfsburg","1. FC Union Berlin","3:4");
        new Game("Bundesliga",3,"1. FC Union Berlin","Borussia Mönchengladbach","2:0");
        new Game("Bundesliga",1,"Borussia Mönchengladbach","VfL Wolfsburg","2:0");

        Table table = bl.getTable();
        ArrayList<TableEntry> nameSortEntries = new ArrayList<TableEntry>(table.getTableSortedByName()); 

        // |Bundesliga
        // |Verein                   |Sp|S |U |N |Pkte|+/-  |Diff.|
        // --------------------------------------------------------
        // |1. FC Union Berlin       |3 |2 |1 |0 |7   |7:4  |3    |
        // |Borussia Mönchengladbach |3 |1 |0 |2 |3   |3:5  |-2   |
        // |Eintracht Frankfurt      |3 |2 |1 |0 |7   |6:3  |3    |
        // |VfL Wolfsburg            |3 |0 |0 |3 |0   |4:8  |-4   |

        assertSame("Natural order of TableEntry is not correct", fcu, nameSortEntries.get(0).getTeam());
        assertSame("Natural order of TableEntry is not correct", bmg, nameSortEntries.get(1).getTeam());
        assertSame("Natural order of TableEntry is not correct", sge, nameSortEntries.get(2).getTeam());
        assertSame("Natural order of TableEntry is not correct", wob, nameSortEntries.get(3).getTeam());

        Team.removeTeam("Eintracht Frankfurt");
        Team.removeTeam("1. FC Union Berlin");
        Team.removeTeam("Borussia Mönchengladbach");
        Team.removeTeam("VfL Wolfsburg");

        League.removeLeague("Bundesliga");
    }
    

    @Test
    public void testGetTableSortedByGoalDifference(){
        Team sge = new Team("Eintracht Frankfurt");
        Team fcu = new Team("1. FC Union Berlin");
        Team bmg = new Team("Borussia Mönchengladbach");
        Team wob = new Team("VfL Wolfsburg");

        League bl = new League("Bundesliga");
        bl.addTeam("Eintracht Frankfurt");
        bl.addTeam("1. FC Union Berlin");
        bl.addTeam("Borussia Mönchengladbach");
        bl.addTeam("VfL Wolfsburg");
        
        new Game("Bundesliga",1,"Eintracht Frankfurt","1. FC Union Berlin","1:1");
        new Game("Bundesliga",2,"Eintracht Frankfurt","Borussia Mönchengladbach","3:1");
        new Game("Bundesliga",3,"VfL Wolfsburg","Eintracht Frankfurt","1:3");
        new Game("Bundesliga",2,"VfL Wolfsburg","1. FC Union Berlin","3:4");
        new Game("Bundesliga",3,"1. FC Union Berlin","Borussia Mönchengladbach","2:0");
        new Game("Bundesliga",1,"Borussia Mönchengladbach","VfL Wolfsburg","2:0");

        Table table = bl.getTable();
        ArrayList<TableEntry> goalDifEntries = new ArrayList<TableEntry>(table.getTableSortedByGoalDifference()); 

        // |Bundesliga
        // |Verein                   |Sp|S |U |N |Pkte|+/-  |Diff.|
        // --------------------------------------------------------
        // |Eintracht Frankfurt      |3 |2 |1 |0 |7   |7:3  |4    |
        // |1. FC Union Berlin       |3 |2 |1 |0 |7   |7:4  |3    |
        // |Borussia Mönchengladbach |3 |1 |0 |2 |3   |3:5  |-2   |
        // |VfL Wolfsburg            |3 |0 |0 |3 |0   |4:9  |-5   |

        assertSame("Natural order of TableEntry is not correct", sge, goalDifEntries.get(0).getTeam());
        assertSame("Natural order of TableEntry is not correct", fcu, goalDifEntries.get(1).getTeam());
        assertSame("Natural order of TableEntry is not correct", bmg, goalDifEntries.get(2).getTeam());
        assertSame("Natural order of TableEntry is not correct", wob, goalDifEntries.get(3).getTeam());

        Team.removeTeam("Eintracht Frankfurt");
        Team.removeTeam("1. FC Union Berlin");
        Team.removeTeam("Borussia Mönchengladbach");
        Team.removeTeam("VfL Wolfsburg");

        League.removeLeague("Bundesliga");
    }

    @Test
    public void testGetTableSortedByGoalsFor(){
        Team sge = new Team("Eintracht Frankfurt");
        Team fcu = new Team("1. FC Union Berlin");
        Team bmg = new Team("Borussia Mönchengladbach");
        Team wob = new Team("VfL Wolfsburg");

        League bl = new League("Bundesliga");
        bl.addTeam("Eintracht Frankfurt");
        bl.addTeam("1. FC Union Berlin");
        bl.addTeam("Borussia Mönchengladbach");
        bl.addTeam("VfL Wolfsburg");
        
        new Game("Bundesliga",1,"Eintracht Frankfurt","1. FC Union Berlin","1:1");
        new Game("Bundesliga",2,"Eintracht Frankfurt","Borussia Mönchengladbach","3:1");
        new Game("Bundesliga",3,"VfL Wolfsburg","Eintracht Frankfurt","1:3");
        new Game("Bundesliga",2,"VfL Wolfsburg","1. FC Union Berlin","3:4");
        new Game("Bundesliga",3,"1. FC Union Berlin","Borussia Mönchengladbach","2:0");
        new Game("Bundesliga",1,"Borussia Mönchengladbach","VfL Wolfsburg","22:0");

        Table table = bl.getTable();
        ArrayList<TableEntry> goalsForEntries = new ArrayList<TableEntry>(table.getTableSortedByGoalsFor()); 

        // |Bundesliga
        // |Verein                   |Sp|S |U |N |Pkte|+/-  |Diff.|
        // --------------------------------------------------------
        // |Borussia Mönchengladbach |3 |1 |0 |2 |3   |23:5 |18   |
        // |1. FC Union Berlin       |3 |2 |1 |0 |7   |7:4  |3    |
        // |Eintracht Frankfurt      |3 |2 |1 |0 |7   |7:3  |4    |
        // |VfL Wolfsburg            |3 |0 |0 |3 |0   |4:29 |-25  |

        assertSame("Natural order of TableEntry is not correct", bmg, goalsForEntries.get(0).getTeam());
        assertSame("Natural order of TableEntry is not correct", fcu, goalsForEntries.get(1).getTeam());
        assertSame("Natural order of TableEntry is not correct", sge, goalsForEntries.get(2).getTeam());
        assertSame("Natural order of TableEntry is not correct", wob, goalsForEntries.get(3).getTeam());

        Team.removeTeam("Eintracht Frankfurt");
        Team.removeTeam("1. FC Union Berlin");
        Team.removeTeam("Borussia Mönchengladbach");
        Team.removeTeam("VfL Wolfsburg");

        League.removeLeague("Bundesliga");
    }
}
