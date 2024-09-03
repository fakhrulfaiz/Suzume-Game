/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.data;

import ds.assignment.Main;
import ds.assignment.TicTacToePanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class TTTSaveLoad {
    TicTacToePanel tttp;
    LeaderboardDataStorage dataStorage;
    public TTTSaveLoad(TicTacToePanel tttp) {
        this.tttp = tttp;
    }

    public void save() {
      
      
       LocalDateTime time = LocalDateTime.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
      String timeSaved = time.format(format);  
      tttp.calculateScore();
    String filename = "leaderboard.dat";
   
    try (FileOutputStream fos = new FileOutputStream(new File(filename));
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {



        // Create a new LeaderboardEntry object with the player's data
        LeaderboardEntry entry = new LeaderboardEntry(
                    tttp.username,
                    tttp.score,
                    tttp.winCount,
                    tttp.drawCount,
                    tttp.loseCount,
                    timeSaved);
         boolean usernameExists;
        // Check if the username exists in the leaderboard
        if(dataStorage != null){
         usernameExists = dataStorage.isUsernameExist(tttp.username);    
        }
        else{
           dataStorage = new LeaderboardDataStorage();
          usernameExists = false;  
        }
        

        // If the username exists, replace the existing entry
        if (usernameExists) {
            List<LeaderboardEntry> leaderboard = dataStorage.getLeaderboard();
            for (int i = 0; i < leaderboard.size(); i++) {
                if (leaderboard.get(i).getName().equals(tttp.username)) {
                    leaderboard.set(i, entry);
                    break;
                }
            }
        } else {
            // If the username doesn't exist, add a new entry
            dataStorage.addLeaderboardEntry(entry);
        }

        // Write the TTTDataStorage object to the leaderboard.dat file
        oos.writeObject(dataStorage);
        System.out.println("Leaderboard data saved successfully.");
    } catch (IOException e) {
        System.out.println("Leaderboard save exception.");
    }   

}
     

    public void load() {
    String filename = "leaderboard.dat";

    try (FileInputStream fis = new FileInputStream(filename);
         ObjectInputStream ois = new ObjectInputStream(fis)) {

        // Read the TTTDataStorage object from the leaderboard.dat file
        this.dataStorage = (LeaderboardDataStorage) ois.readObject();
        
        
        if(!dataStorage.isUsernameExist(tttp.username) || tttp.username.equals("") || 
            ( dataStorage.isUsernameExist(tttp.username)&& Main.game.equalsIgnoreCase("new"))){
            setDefultValues();
        }
        else{
         
            // Get the leaderboard entries
        List<LeaderboardEntry> leaderboard = dataStorage.getLeaderboard();

        // Sort the leaderboard based on the score
        leaderboard.sort(Comparator.comparingInt(LeaderboardEntry::getScore).reversed());

        // Process the leaderboard entries as needed
        for (LeaderboardEntry entry : leaderboard) {
            // Do something with each entry
            String name = entry.getName();
            int win = entry.getWin();
            int draw = entry.getDraw();
            int lose = entry.getLose();
            int score = entry.getScore();
            
            if(name.equalsIgnoreCase(tttp.username)){
            tttp.score = score;
            tttp.winCount = win;
            tttp.loseCount = lose;
            tttp.drawCount = draw;  
            }
         
            System.out.println("Name: " + entry.getName());
            System.out.println("Score: " + score);
            System.out.println("Win: " + win);
            System.out.println("Draw: " + draw);
            System.out.println("Lose: " + lose);
            System.out.println("Last Saved: " + entry.getTimeSaved());
        }
        System.out.println("Leaderboard data loaded and sorted successfully.");
        }

       

        
    } catch (IOException e) {
        System.out.println("Leaderboard load exception.");
    } catch (ClassNotFoundException ex) {
        System.out.println("Class not found exception.");
    }
}

  
    public void setDefultValues(){
      tttp.score = 0;
      tttp.winCount = 0;
      tttp.drawCount = 0; 
      tttp.loseCount = 0;   
          
    }
    

}