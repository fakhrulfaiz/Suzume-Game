/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.data;

import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.tile.MapTile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class SaveLoad {
    GamePanel gp;
    LeaderboardDataStorage dataStorage;
    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    public void save() {
        saveLeaderboard();
        if(!gp.isAutoSimulation){
          String filename = gp.username + "_" + gp.difficulty + ".dat";  // Assuming the filename follows the username pattern

        try (FileOutputStream fos = new FileOutputStream(new File(filename));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             
            DataStorage ds = new DataStorage();
                      
            ds.worldX = gp.player.worldX;
            ds.worldY = gp.player.worldY;
            ds.scX = gp.player.scX;
            ds.scY = gp.player.scY;
            ds.x = gp.player.x;
            ds.y = gp.player.y;
            ds.stationCount = gp.stationCount;
            ds.hasEnded = gp.tttM.hasEnded;
           
            // Copy the MapTile data from gp.tileM.a to ds.a
         
             
            // Copy the MapTile data from gp.tileM.a to ds.a
            ds.a = new MapTile[gp.tileM.a.length][gp.tileM.a[0].length];
            for (int i = 0; i < gp.tileM.a.length; i++) {
                for (int j = 0; j < gp.tileM.a[i].length; j++) {
                    ds.a[i][j] = new MapTile();
                    ds.a[i][j].mapTile = gp.tileM.a[i][j].mapTile;
                    ds.a[i][j].visited = gp.tileM.a[i][j].visited;
                }
            }
            
            // Write the DataStorage object to the .dat file
            oos.writeObject(ds);
            System.out.println("Game data saved successfully.");
        } catch (IOException e) {
            System.out.println("Game save exception.");
        }  
        }
        
    }
    public void saveLeaderboard() {
      
      
       
      String difficulty = gp.difficulty;  
      
    String filename = "gameLeaderboard.dat";
   
    try (FileOutputStream fos = new FileOutputStream(new File(filename));
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {



        // Create a new LeaderboardEntry object with the player's data
        LeaderboardEntry entry = new LeaderboardEntry(
                    gp.username,
                    gp.score,
                    gp.winCount,
                    gp.loseCount,
                    difficulty);
   
         boolean userDifficultyExists;
        // Check if the username exists in the leaderboard
        if(dataStorage != null){

         userDifficultyExists = dataStorage.isDifficultyExist(difficulty,gp.username); 
        }
        else{
           dataStorage = new LeaderboardDataStorage();

          userDifficultyExists = false;
        }
        

        // If the username exists, replace the existing entry
        if (userDifficultyExists) {
            List<LeaderboardEntry> leaderboard = dataStorage.getLeaderboard();
            for (int i = 0; i < leaderboard.size(); i++) {
                if (leaderboard.get(i).getName().equals(gp.username)) {
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
        System.out.println("Game Leaderboard data saved successfully.");
    } catch (IOException e) {
        System.out.println("Game Leaderboard save exception.");
    }   

}
    public void loadLeaderboard() {
    String filename = "gameLeaderboard.dat";

    try (FileInputStream fis = new FileInputStream(filename);
         ObjectInputStream ois = new ObjectInputStream(fis)) {

        // Read the TTTDataStorage object from the leaderboard.dat file
        this.dataStorage = (LeaderboardDataStorage) ois.readObject();
        
        
        if(!dataStorage.isUsernameExist(gp.username) || gp.username.equals("") || 
            ( dataStorage.isUsernameExist(gp.username)&& Main.game.equalsIgnoreCase("new"))){
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
            int lose = entry.getLose();
            int score = entry.getScore();
            
            if(name.equalsIgnoreCase(gp.username)){
            gp.score = score;
            gp.winCount = win;
            gp.loseCount = lose;

            }
         
            System.out.println("Name: " + entry.getName());
            System.out.println("Score: " + score);
            System.out.println("Win: " + win);
            System.out.println("Lose: " + lose);
            System.out.println("Difficulty: " + entry.getDifficulty());
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
     
      gp.score = 0;
      gp.winCount = 0;
      gp.loseCount = 0;   
          
    }
     public void load() {
        loadLeaderboard(); 
        if(!gp.isAutoSimulation){ 
        String filename = gp.username + "_" + gp.difficulty + ".dat";  // Assuming the filename follows the username pattern
  
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Read the DataStorage object from the .dat file
            DataStorage ds = (DataStorage) ois.readObject();
            gp.player.y = ds.y;
            gp.player.worldX = ds.worldX;
            gp.player.worldY = ds.worldY;
            gp.player.scX = ds.scX;
            gp.player.scY = ds.scY;
            gp.player.x = ds.x;
            gp.stationCount = ds.stationCount;
            gp.tttM.hasEnded = ds.hasEnded;
             // Copy the MapTile data from ds.a to gp.tileM.a
            gp.tileM.a = new MapTile[ds.a.length][ds.a[0].length];
            for (int i = 0; i < ds.a.length; i++) {
                for (int j = 0; j < ds.a[i].length; j++) {
                    gp.tileM.a[i][j] = new MapTile();
                    gp.tileM.a[i][j].mapTile = ds.a[i][j].mapTile;
                    gp.tileM.a[i][j].visited = ds.a[i][j].visited;
                }
            }
            //gp.player.direction = ds.direction;

        } catch (IOException e) {
            System.out.println("Load Exception");
        } catch (ClassNotFoundException ex) {
            
        }

       }
        else{
            gp.player.setDefultValues();
        }
     } 
}
