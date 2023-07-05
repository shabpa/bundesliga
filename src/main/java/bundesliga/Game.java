package bundesliga;

/*
 * TODO: Ergänzen Sie die compareTo-Methode
 *   
 */

/** Represents a game. Implements a natural order with the interface Comparable */
public class Game implements Comparable<Game>{

    /** The league in which this game took place */
    private final League league;

    /** The match day on which the game took place.*/
    private final int matchDay;

    /** The home team */
    private final Team homeTeam;

    /** The away team */
    private final Team awayTeam;

    /** The goals of the home team */
    private int homeGoals;

    /** The goals of the away team */
    private int awayGoals;


    /**
     * The Constructor of the Class game. Creates a game and adds it to the games Set of the given league 
     * 
     * @param leagueName the name of the league in which the game took place
     * @param matchDay The match day on which the game took place. For example, the Bundesliga has 34 match days
     * @param homeTeamName the name of the home team. Must 
     * @param awayTeamName the name of the away team
     * @param gameResult
     * 
     * @throws IllegalArgumentException if leagueName is null or empty
     * @throws IllegalArgumentException if no league for the leagueName is found
     * @throws IllegalArgumentException if matchDay is less than 1
     * @throws IllegalArgumentException if homeTeamName is null or empty
     * @throws IllegalArgumentException if no team for the homeTeamName is found
     * @throws IllegalArgumentException if homeTeam is not member of teams in league
     * @throws IllegalArgumentException if awayTeamName is null or empty
     * @throws IllegalArgumentException if no team for the awayTeamName is found
     * @throws IllegalArgumentException if awayTeam is not member of teams in league
     * @throws IllegalArgumentException if gameResult is null or empty
     * @throws IllegalArgumentException if gameResult is not in the regex-format \d+:\d+
     */
    public Game(String leagueName, int matchDay, String homeTeamName, String awayTeamName, String gameResult){
       
        League league = League.getLeague(leagueName);
        Team homeTeam = league.getTeam(homeTeamName);
        Team awayTeam = league.getTeam(awayTeamName);

        if(matchDay < 1) throw new IllegalArgumentException("matchDay must be greater than 0");

        if(gameResult == null || gameResult.trim().isEmpty())
            throw new IllegalArgumentException("gameResult is null or empty");
        gameResult = gameResult.trim();
        if(!gameResult.matches("\\d+:\\d+")) throw new IllegalArgumentException("result is not a valid result");

        this.league = league;
        this.matchDay = matchDay;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        
        String[] goals = gameResult.split(":");
        this.homeGoals = Integer.parseInt(goals[0]);
        this.awayGoals = Integer.parseInt(goals[1]);

        league.addGame(this);

    }

    public League getLeague(){
        return league;
    }
  
    public int getMatchDay() {
        return matchDay;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }


    @Override
    public int compareTo(Game other){
        /* TODO: Implementieren Sie compareTo entsprechend der Vorgaben aus der Vorlesung
         * Die natürliche Ordnung von Spielen sortiert selbige zuerst aufsteigend nach dem 
         * matchDay und danach alphabetisch aufsteigend nach dem Namen des home teams
         */
        int result = Integer.compare(this.getMatchDay(), other.getMatchDay());
        if (result == 0) result = (this.getHomeTeam().getName()).compareTo(other.getHomeTeam().getName());
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((league == null) ? 0 : league.hashCode());
        result = prime * result + matchDay;
        result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
        result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
        result = prime * result + homeGoals;
        result = prime * result + awayGoals;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        if (league == null) {
            if (other.league != null)
                return false;
        } else if (!league.equals(other.league))
            return false;
        if (matchDay != other.matchDay)
            return false;
        if (homeTeam == null) {
            if (other.homeTeam != null)
                return false;
        } else if (!homeTeam.equals(other.homeTeam))
            return false;
        if (awayTeam == null) {
            if (other.awayTeam != null)
                return false;
        } else if (!awayTeam.equals(other.awayTeam))
            return false;
        if (homeGoals != other.homeGoals)
            return false;
        if (awayGoals != other.awayGoals)
            return false;
        return true;
    }


    
}
