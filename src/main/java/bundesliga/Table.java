package bundesliga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * TODO: Ergänzen Sie das Attribut entries und die Methoden zum Sortieren
 *   
 */

/** Represents a table with the ranking of the teams */
public class Table {

    /** Each team is represented in a TableEntry.  */
    //TODO: Initialisieren Sie das Attribut entries direkt bei der Deklaration
    private List<TableEntry> entries = new ArrayList<TableEntry>();

    /** The league to which this object belongs */
    private final League league;

    /** The table includes all games up to this matchday. 
     * If matchDay is null, all matchdays are included
    */
    private Integer matchDay = null;

    /** The concrete value for the TableType enum  */
    private TableType type;

    /** A table can contain only home games, only away games or both */
    public enum TableType {HOME, AWAY, BOTH}

    /**
     * Calls Table(League league, Integer matchDay, TableType type) Constructor
     * with matchDay = null and type = TableType.Both
     * 
     * @param league the league for which this table is created
     */
    public Table(League league){
        this(league,null, TableType.BOTH);
    }


    /**
     * Creates a new Table and all TableEntry-Objects. Creates for each team in league 
     * a TableEntry-Object
     * 
     * @param the league for which this table is created
     * @param matchDay The maximum matchday that should be taken into account when 
     * calculating the table. If matchDay is null, all games are taken into account.
     * @param type the TableType value 
     * 
     * @throws IllegalArgumentException if league is null
     * @throws IllegalArgumentException if matchDay < 1
     * 
     */
    public Table(League league, Integer matchDay, TableType type){
        if(league == null) throw new IllegalArgumentException("league is null");
        if(matchDay!= null && matchDay < 1) throw new IllegalArgumentException("matchDay must be greater than 0 or null");
        if(type == null) type = TableType.BOTH;
        this.league = league;
        this.matchDay = matchDay;
        this.type = type;
        for(Team team:this.league.getTeams()){
            this.entries.add(new TableEntry(league, team, matchDay, type));
        }
    }

    /**
     * Sorts the TableEntry objects in the entries List attribute according to their 
     * natural order
     * 
     * @return an unmodifiable list of TableEntry sorted by the natural order of 
     * TableEntry
     */
    public List<TableEntry> getTableNaturalSorted(){
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs
        List<TableEntry> result = entries;
        Collections.sort(result);
        return Collections.unmodifiableList(result);
    }

    /**
     * Sorts the TableEntry objects in the entries attribute in ascending order 
     * by the name of the teams
     * 
     * @return an unmodifiable list of TableEntry sorted ascending by the name of the teams 
     */
    public List<TableEntry> getTableSortedByName(){
        /*
         * TODO: Implementieren Sie die Methode entsprechend des JavaDocs. Verwenden Sie eine 
         * anonyme Comparator-Klasse für die Sortierung
         */ 
        entries.sort(new Comparator<TableEntry>() {
            @Override
            public int compare(TableEntry x, TableEntry y) {
                return (x.getTeam().getName()).compareTo((y.getTeam().getName()));
            }
        });
        return Collections.unmodifiableList(entries);
    }

    /**
     * Sorts the TableEntry objects in the entries attribute in descending order 
     * by the Goal-Difference
     * 
     * @return an unmodifiable list of TableEntry sorted descending by the goal difference 
     */
    public List<TableEntry> getTableSortedByGoalDifference(){
        /*
         * TODO: Implementieren Sie die Methode entsprechend des JavaDocs. Verwenden Sie eine 
         * anonyme Comparator-Klasse für die Sortierung
         */ 
        entries.sort(new Comparator<TableEntry>() {
            @Override
            public int compare(TableEntry x, TableEntry y) {
                return Integer.compare(y.getGoalDifference(), x.getGoalDifference());
            }
        });
        return Collections.unmodifiableList(entries);
    }
    

    /**
     * Sorts the TableEntry objects in the entries attribute in descending order 
     * by the Goals-For (the goals scored by the specific team)
     * 
     * @return an unmodifiable list of TableEntry sorted descending by the goal difference 
     */
    public List<TableEntry> getTableSortedByGoalsFor(){
        /*
         * TODO: Implementieren Sie die Methode entsprechend des JavaDocs. Verwenden Sie eine 
         * anonyme Comparator-Klasse für die Sortierung
         */ 
        entries.sort(new Comparator<TableEntry>() {
            @Override
            public int compare(TableEntry x, TableEntry y) {
                return Integer.compare(y.getGoalsFor(), x.getGoalsFor());
            }
        });
        return Collections.unmodifiableList(entries);
    }
        
    


    /**
     * Prints a List of TableEntries
     * 
     * @param entries a list of TableEntries
     */
    public void printEntries(List<TableEntry> entries){
        //Header
        System.out.print("\n\n|" + this.league.getName());
        if(this.matchDay!=null)
            System.out.print(" (Spieltag " + matchDay + ")");
        if(this.type==TableType.AWAY)
            System.out.print(" Auswärts");
        if(this.type==TableType.HOME)
            System.out.print(" Heim");
        System.out.printf("\n|%s|%s|%s|%s|%s|%s|%s|%s|\n--------------------------------------------------------\n",
            Helper.fill("Verein",25),
            Helper.fill("Sp",2),
            Helper.fill("S",2),
            Helper.fill("U",2),
            Helper.fill("N",2),
            Helper.fill("Pkte",4),
            Helper.fill("+/-",5),
            Helper.fill("Diff.",5)
            );

        //Einträge
        for(TableEntry entry: entries){
            System.out.printf("|%s|%s|%s|%s|%s|%s|%s|%s|\n",
            Helper.fill(entry.getTeam().getName(),25),
            Helper.fill(String.valueOf(entry.getGamesCount()),2),
            Helper.fill(String.valueOf(entry.getWon()),2),
            Helper.fill(String.valueOf(entry.getDraw()),2),
            Helper.fill(String.valueOf(entry.getLost()),2),
            Helper.fill(String.valueOf(entry.getPoints()),4),
            Helper.fill(String.valueOf(entry.getGoalsFor())+":"+String.valueOf(entry.getGoalsAgainst()),5),
            Helper.fill(String.valueOf(entry.getGoalDifference()),5)
            );
        }
    }
        
}
    

