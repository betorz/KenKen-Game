package main.domain.classes;

import java.util.HashMap;

public class User {
    
    private String username;
    private String password;

    private Stats stats;
    private HashMap<Integer, Game> savedGames;
    
//CONSTRUCTORS
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.stats = new Stats();
        this.savedGames = new HashMap<>();
    }

   
//METHODS
     
    public void addSavedGame(Integer boardId, Game game) {
        savedGames.put(boardId,game);
    }

    public int getPoints() {
        return stats.getPoints();
    }

    public long getTimes(Integer kenkenSize, Integer difficulty) {
        return stats.getTimes(kenkenSize, difficulty);
    }
    
    public int getSolvedDifficulty(Integer kenkenDifficulty) {
        return stats.getSolvedDifficulty(kenkenDifficulty);
    }

    public int getSolvedSize(Integer kenkenSize) {
        return stats.getSolvedSize(kenkenSize);
    }
//GETTERS

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<Integer, Game> getSavedGames() {
        return savedGames;
    }

    public Stats getStats() {
        return stats;
    }
    
//SETTERS
    public void setPassword(String password) {
        this.password = password;
    }
}
