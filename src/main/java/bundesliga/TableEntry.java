package bundesliga;

//TODO: Implementieren Sie das Comparable-Interface entsprechend der Vorgaben.

/** Each TableEntry object represents an team in the table. TableEntry has a natural order. */
public class TableEntry implements Comparable<TableEntry>{
    
    /** The number of games taken into account for the calculation of this entry */
    private int gamesCount = 0;

    /** The number of games won by the team */
    private int won = 0;

    /** The number of games that ended in a draw */
    private int draw = 0;

    /** The number of games lost by the team */
    private int lost = 0;

    /** The number of points earned by the team */
    private int points = 0;

    /** The number of goals scored by the team */
    private int goalsFor = 0;

    /** The number of goals against the team */
    private int goalsAgainst = 0;

    /** The difference between goalsFor and goalsAgainst */
    private int goalDifference = 0;

    /** The Team all calculations are made for */
    private final Team team;

    /**
     * Creates a TableEntry-Object and calculates all values
     * 
     * @param league the league for which the parent table is created
     * @param team the team for which the TableEntry-Object is created
     * @param matchDay The maximum matchday that should be taken into account when 
     * calculating the TableEntry. If matchDay is null, all games are taken into account.
     * @param type TableType.HOME = only home games
     *             TableType.AWAY = only away games 
     *             TableType.BOTH = all games
     */
    TableEntry(League league, Team team, Integer matchDay, Table.TableType type ){
        if(league == null) throw new IllegalArgumentException("league is null");
        if(team == null) throw new IllegalArgumentException("team is null");
        if(matchDay!= null && matchDay < 1) throw new IllegalArgumentException("matchDay must be greater than 0 or null");
        if(type == null) type = Table.TableType.BOTH;

        this.team = team;
        for(Game g: league.getGames()){
            if(matchDay!=null && g.getMatchDay()>matchDay)
                break;

            if((type==Table.TableType.HOME || type==Table.TableType.BOTH) && g.getHomeTeam() == team){
                this.goalsFor += g.getHomeGoals();
                this.goalsAgainst += g.getAwayGoals();
                //wenn gewonnen
                if(g.getHomeGoals() > g.getAwayGoals())
                    won++;
                if(g.getHomeGoals() == g.getAwayGoals())
                    draw++;
                if(g.getHomeGoals() < g.getAwayGoals())
                    lost++;
                gamesCount++;
            }

            if((type==Table.TableType.AWAY || type==Table.TableType.BOTH) && g.getAwayTeam() == team){
                this.goalsFor += g.getAwayGoals();
                this.goalsAgainst += g.getHomeGoals();
                //wenn gewonnen
                if(g.getHomeGoals() < g.getAwayGoals())
                    won++;
                if(g.getHomeGoals() == g.getAwayGoals())
                    draw++;
                if(g.getHomeGoals() > g.getAwayGoals())
                    lost++;
                gamesCount++;
            }
        }
        points = won*3+draw;
        goalDifference = goalsFor-goalsAgainst;
    }

    public int getGamesCount() {
        return gamesCount;
    }

    public int getWon() {
        return won;
    }

    public int getDraw() {
        return draw;
    }

    public int getLost() {
        return lost;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public Team getTeam() {
        return team;
    }

    /* 
     * TODO: Implementieren Sie eine compareTo-Methode für die natürliche Ordnung der Objekte
     * Die natürliche Ordnung sortiert
     *      - zunächst nach den Punkten
     *      - dann nach der Tordifferenz
     *      - dann nach den erzielten Toren
     */ 
    @Override
    public int compareTo(TableEntry other) {
        if (other == null) throw new IllegalArgumentException("The table entry to be compared shouldn't be null.");
        int result = other.getPoints() - this.getPoints();
        if (result == 0) result = other.getGoalDifference() - this.getGoalDifference();
        if (result == 0) result = other.getGoalsFor() - this.getGoalsFor();
        return result;
    }

    
}
