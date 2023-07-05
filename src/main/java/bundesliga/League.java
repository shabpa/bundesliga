package bundesliga;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * TODO: Ergänzen Sie die Klasse, wo angegeben. Für die Erzeugung eines Schlüssels aus dem Namen 
 * können Sie die Methode getKeyByName() aus der Helper-Klasse verwenden. Schlüssel sollten 
 * grundsätzlich keine Leerzeichen enthalten. Schlüssel sollten auch nur aus Großbuchstaben bestehen.
 * Damit kann sichergestellt werden, dass auch ein String " b UNDES LIGA" der richtigen Liga zugeordnet 
 * wird
 *   
 */

/** represents a league. Furthermore, it contains a static store of all created leagues */ 
public class League {

    /** the private static store of all leagues. The key is a String and the value is a league */
    //TODO: Deklarieren und Initialisieren Sie eine statische Map entsprechend des JavaDocs
    static Map<String, League> leagues = new HashMap<String, League>();
    
    /** The name of the league */
    private String name;

    /** The teams playing in the league */
    /* TODO: Deklarieren und initialisieren Sie teams mit einer passenden Collection.
     * Jedes Team darf nur ein einziges mal in der Collection vorkommen.
     * Die Sortierung ist dabei irrelevant
     */  
    Set<Team> teams = new HashSet<Team>();

    /** The games played in the league */
    /* TODO: Deklarieren und initialisieren Sie games mit einer passenden Collection.
     * Jedes Game darf nur ein einziges mal in der Collection vorkommen.
     * Die Sortierung ist dabei sehr wichtig. Implementieren Sie hierfür 
     * auch die natürliche Ordnung von Game (vgl Game).
     */  
    Set<Game> games = new TreeSet<>(new Comparator<Game>() {
        @Override
        public int compare(Game x, Game y) {
            if (x == null || y == null) {
                throw new IllegalArgumentException("One of the compared games is a instance of null.");
            }
            int result = Integer.compare(x.getMatchDay(), y.getMatchDay());
            if (result == 0) result = (x.getHomeTeam().getName()).compareTo((y.getHomeTeam().getName()));
            return result;
        }
    });

    /**
     * The Constructor of the team League. Checks if there is already a 
     * league whose key matches the passed name. Writes the new league in 
     * the league store.
     * 
     * @param name the name of the new league
     * @throws IllegalArgumentException if there is already a 
     * league whose key matches the passed name. 
     * @throws IllegalArgumentException if name is null or empty
     * @throws IllegalArgumentException if league with the given name already exists
     */
    public League(String name){
        //TODO: Implementieren Sie den Konstruktor entsprechend des JavaDocs
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("League name shouldn't not be null or empty.");

        if(leagues.containsKey(Helper.getKeyByName(name)))
            throw new IllegalArgumentException("League already exists.");
        
        this.name = name.trim();

        League.leagues.put(Helper.getKeyByName(name),this);
    }

    /**
     * Adds a team to the local set teams
     * 
     * @param team the team to add
     * @throws IllegalArgumentException if team is null
     * @throws IllegalArgumentException in team is already in Set
     */
    public void addTeam(Team team){
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs
        if (team == null)
            throw new IllegalArgumentException("Team shouldn't be null.");
        if (teams.contains(team))
            throw new IllegalArgumentException("Team has already been added.");

        this.teams.add(team);
    }

    /**
     * Fetches a team from the static team-store based on the name passed
     * and calls addTeam(Team team) method
     * 
     * @param name the name of the team
     */
    public void addTeam(String name){
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs
        this.addTeam(Team.teams.get(Helper.getKeyByName(name)));
    }

    /**
     * Adds a game to the local set games
     * 
     * @param game the game to add
     * @throws IllegalArgumentException if game is null
     * @throws IllegalArgumentException in game is already in Set
     */
    public void addGame(Game game){
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs
        if (game == null)
            throw new IllegalArgumentException("Game shouldn't be null");
        if (this.games.contains(game))
            throw new IllegalArgumentException("Game has already been added.");
        
        this.games.add(game);
    }

    

    /**
     * Fetches the matching team from the static team-store based on the name passed. 
     * Checks if team is in local Set teams. If not IllegalArgumentException is thrown.
     * 
     * @param name the name of the team
     * @return the team that matches the name given name
     * @throws IllegalArgumentException if name is null or empty
     * @throws IllegalArgumentException if no team for the name is found
     * @throws IllegalArgumentException if team is not member of the local Set teams
     */
    public Team getTeam(String name){
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name of the team shouldn't be null or empty.");
        if (!Team.teams.containsKey(Helper.getKeyByName(name)))
            throw new IllegalArgumentException("Team doesn't exist.");
        if (!this.teams.contains(Team.teams.get(Helper.getKeyByName(name))))
            throw new IllegalArgumentException("Team is not in league.");
        
        return Team.teams.get(Helper.getKeyByName(name));
    }

    
    public Table getTable(){
        return getTable(null);
    }

    public Table getTable(Integer matchDay){
        return getTable(matchDay, Table.TableType.BOTH);
    }

    public Table getTable(Integer matchDay, Table.TableType type){
        return new Table(this, matchDay, type);
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a statement whether there is a league in the static league-store whose 
     * key matches the passed name
     * 
     * @param name the name of the league 
     * @return a statement whether there is a league in the league-store whose 
     * key matches the passed name
     * @throws IllegalArgumentException if name is null or empty
     */
    public static boolean containsLeague(String name){
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs 
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name of the league shouldn't be null or empty.");
        
        return League.leagues.containsKey(Helper.getKeyByName(name));
    }

    /**
     * Fetches the matching league from the static league-store based on the name passed. 
     * 
     * @param name the name of the league
     * @return the league that matches the given name
     * @throws IllegalArgumentException if name is null or empty
     * @throws IllegalArgumentException if no league for the name is found
     */
    public static League getLeague(String name){
        //TODO: Implementieren Sie den Konstruktor entsprechend des JavaDocs 
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name of the league shouldn't be null or empty.");
        if (!League.leagues.containsKey(Helper.getKeyByName(name)))
            throw new IllegalArgumentException("No such league found.");
        
        return League.leagues.get(Helper.getKeyByName(name));

    }

    /**
     * Removes a league with the given name from the static league-store
     * 
     * @param name the name of the league that should be removed
     */
    public static void removeLeague(String name){
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs
        League.leagues.remove(Helper.getKeyByName(name));
    }

    /**
     * returns a unmodifiable Copy of the local Set teams
     * 
     * @return a unmodifiable Copy of the local Set teams
     */
    public Set<Team> getTeams() {
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs
        return Collections.unmodifiableSet(teams);
    }

    /**
     * returns a unmodifiable Copy of the local Set games
     * 
     * @return a unmodifiable Copy of the local Set games
     */
    public Set<Game> getGames() {
        //TODO: Implementieren Sie die Methode entsprechend des JavaDocs
        return Collections.unmodifiableSet(games);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        League other = (League) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
 
}
