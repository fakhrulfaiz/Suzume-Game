/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class LeaderboardDataStorage implements Serializable {
    private List<LeaderboardEntry> leaderboard;

    public LeaderboardDataStorage() {
        leaderboard = new ArrayList<>();
    }

    public void addLeaderboardEntry(LeaderboardEntry entry) {
        leaderboard.add(entry);
    }

    public List<LeaderboardEntry> getLeaderboard() {
        return leaderboard;
    }

    public boolean isUsernameExist(String username) {
        for (LeaderboardEntry entry : leaderboard) {
            if (entry.getName().equals(username) ) {
                return true;
            }
        }
        return false;
    }
    public boolean isDifficultyExist(String difficulty,String username ) {
        for (LeaderboardEntry entry : leaderboard) {

             if (entry.getName().equals(username) ) {
        
                if (entry.getDifficulty().equals(difficulty) ) {

                return true;
               }
            }
            
        }

        return false;
    }
}

 