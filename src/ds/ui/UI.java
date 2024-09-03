/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.ui;

import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.assignment.TicTacToePanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 *
 * @author User
 */
public class UI {
    MyButton pauseButton ,simulationButton, startButton,simulButton,quitButton, errorButton,revTTTStartButton
            ,ttt5x5StartButton,pyTTTStartButton,pvpButton, eveButton , engine1Button,engine2Button ;
    

    int engine1Difficulty = 0;
    int engine2Difficulty = 0;
    int changeDifficulty = 0;
    String storeDifficulty;
    MyButton backButton   = new MyButton("<<", 48*2,485, 70, 48 );
    MyButton forwardButton = new MyButton(">>", 48*3+20,485, 70, 48 ); 
    MyButton changeDifficultyButton = new MyButton("DIFFICULTY", 48 * 20/2 - 48*2 - 40, 48*6 - 48, 260, 48 );
    MyButton resumeButton = new MyButton("RESUME", 48 * 20/2 - 48*2 - 40, 48*6 , 260, 48 );
    MyButton switchButton = new MyButton(""      , 48 * 20/2 - 48*2 - 40, 48*6 + 48, 260, 48 );
    MyButton quitModeButton = new MyButton("QUIT", 48 * 20/2 - 48*2 - 40, 48*6 + 2*48 , 260, 48 );
    GamePanel gp;
    TicTacToePanel tttp;
    Font font, custFont;
   // Font michroma;
    BufferedImage[] ttt = new BufferedImage[2];
   Graphics2D g2;

    public UI(GamePanel gp){

        this.gp = gp;
        //            InputStream is =getClass().getResourceAsStream("/res/font/Michroma-Regular.ttf");
//            michroma = Font.createFont(Font.TRUETYPE_FONT, is);
         custFont = new Font("SansSerif", Font.BOLD, 30); 
         font = new Font("SansSerif", Font.BOLD, 20);
         pauseButton = new MyButton("P", gp.ScreenWidth-gp.size, 8, 40, 40);
         startButton = new MyButton("START GAME", gp.ScreenWidth/2 - gp.size*2 - 40, gp.size*6 + 100, 260, gp.size );
         simulationButton = new MyButton("SIMULATION", gp.ScreenWidth/2 - gp.size*2 - 40, gp.size*6 + 100 + gp.size, 260, gp.size );
         quitButton = new MyButton("QUIT", gp.ScreenWidth/2 - gp.size*2 - 40, gp.size*6 + 100 + 2*gp.size , 260, gp.size );
     //    simulButton = new MyButton("START GAME", gp.ScreenWidth/2 - gp.size*2 - 40, (gp.size*6 + 10)*2, 260, gp.size );
           
    
        // Draw the button
        errorButton = new MyButton("ERROR MOVE!", gp.ScreenWidth / 2 , gp.screenHeight / 2, 200, 90); 
    }
    public UI(TicTacToePanel tttp){

        this.tttp = tttp;
         font = new Font("SansSerif", Font.BOLD, 20);
       
     

    int size = 48; //48x48
    pvpButton = new MyButton("PvP", 50          , size*4 + 100-size, 200, size );
    eveButton = new MyButton("EvE", size*4 + 100-43 , size*4 + 100-size, 200, size );
    engine1Button = new MyButton("Engine 1", size*4 + 100-43          , size*4 + 4       , 100, size );
    engine2Button = new MyButton("Engine 2", size*4 + 100-43 + 100    , size*4 + 4       , 100 , size );
    revTTTStartButton = new MyButton("REVERSE 3x3 TICTACTOE", 50, size*4 + 100, 400, size );
    ttt5x5StartButton = new MyButton("5x5 TICTACTOE", 50, size*4 + 100 + size, 400, size );
    pyTTTStartButton = new MyButton("PYRAMID TICTACTOE", 50, size*4 + 100 + 2*size , 400, size );
    
    int ScreenWidth = size*20; // 
    int screenHeight = size*12; // 
        pauseButton = new MyButton("P", ScreenWidth - size, 8, 40, 40);
        errorButton = new MyButton("ERROR MOVE!", ScreenWidth / 2 , screenHeight / 2, 200, 90);
        storeDifficulty = tttp.difficulty;
    }
    
     public void mouseClickedTitleScreen(int x,int y){
        if(Main.mode.equals("simulation")){
         //START GAME BUTTON
         if(startButton.getBounds().contains(x, y) && gp.gameState == gp.titleState){
           
           gp.isAutoSimulation = false; 
           gp.setupGame();
           gp.gameState = gp.playState;
           
        }
         if(simulationButton.getBounds().contains(x, y) && gp.gameState == gp.titleState){
           gp.isAutoSimulation = true; 
            gp.setupGame();
           gp.gameState = gp.playState;
           
        }
         //QUIT BUTTON
         if(quitButton.getBounds().contains(x, y) && gp.gameState == gp.titleState){
          System.exit(0);
           
        }    
        }  
        
     }
     public void mouseClickedTTTMode(int x, int y){
         
         
           //CHANGE DIFFICULTY BUTTON
            if(changeDifficultyButton.getBounds().contains(x, y) && tttp.gameState == tttp.pauseState){
              changeDifficulty++;
                 
                 if (changeDifficulty > 3) {
                   changeDifficulty = 1; }
                 if (changeDifficulty == 1) {
                    changeDifficultyButton.setText("Easy");                  
                    tttp.difficulty = "easy";
               } else if (changeDifficulty == 2) {
                    changeDifficultyButton.setText("Medium");
                     tttp.difficulty = "medium";
               } else if (changeDifficulty == 3) {
                    changeDifficultyButton.setText("Hard");
                     tttp.difficulty = "hard";
                   }else{
                   changeDifficultyButton.setText("DIFFICULTY");
                     tttp.difficulty = storeDifficulty;
               }
             
             return;
          }
           //EVE BUTTON
            if(eveButton.getBounds().contains(x, y) && tttp.gameState == tttp.titleState){
           if((tttp.tttM.specialMode == tttp.tttM.eveMode) ){
               tttp.tttM.specialMode = 0;
               tttp.tttM.PLAYER = tttp.username;
              tttp.tttM.currentTurn =  tttp.tttM.PLAYER;
              tttp.tttM.engine1 = "";
              tttp.tttM.engine2 = "";
              tttp.tttM.ENGINE = "Engine";
              engine1Button.text = "Engine 1";
              engine2Button.text = "Engine 2";
              engine1Difficulty = 0;
              engine2Difficulty = 0;
              
           }else{
            tttp.tttM.specialMode = tttp.tttM.eveMode;
            tttp.tttM.PLAYER = "Engine 1";
            tttp.tttM.currentTurn =  tttp.tttM.PLAYER;
            tttp.tttM.ENGINE = "Engine 2";
           }
        }
           // Default difficulty is easy
        if (engine1Button.getBounds().contains(x, y) && tttp.tttM.specialMode == tttp.tttM.eveMode) {
        // Increment the difficulty level
                engine1Difficulty++;

                 if (engine1Difficulty > 3) {
                   engine1Difficulty = 0; }
                 if (engine1Difficulty == 1) {
                    engine1Button.setText("Easy");
                    tttp.tttM.engine1 = "easy";
               } else if (engine1Difficulty == 2) {
                    engine1Button.setText("Medium");
                    tttp.tttM.engine1 = "medium";
               } else if (engine1Difficulty == 3) {
                    engine1Button.setText("Hard");
                    tttp.tttM.engine1 = "hard";
                   }else{
                   engine1Button.setText("Engine 1");
                    tttp.tttM.engine1 = "medium";
               }
            }  
             // Default difficulty is easy
        if (engine2Button.getBounds().contains(x, y) && tttp.tttM.specialMode == tttp.tttM.eveMode) {
        // Increment the difficulty level
                engine2Difficulty++;

                 if (engine2Difficulty > 3) {
                   engine2Difficulty = 0; }
                 if (engine2Difficulty == 1) {
                    engine2Button.setText("Easy");
                    tttp.tttM.engine2 = "easy";
               } else if (engine2Difficulty == 2) {
                    engine2Button.setText("Medium");
                    tttp.tttM.engine2 = "medium";
               } else if (engine2Difficulty == 3) {
                    engine2Button.setText("Hard");
                    tttp.tttM.engine2 = "hard";
                   }else{
                   engine2Button.setText("Engine 2");
                   tttp.tttM.engine2 = "medium";
               }
            }   
            //PVPBUTTON
         if(pvpButton.getBounds().contains(x, y) && tttp.gameState == tttp.titleState){
           if(tttp.tttM.specialMode == tttp.tttM.pvpMode){
               tttp.tttM.specialMode = 0;
               tttp.tttM.PLAYER = tttp.username;
              tttp.tttM.currentTurn =  tttp.tttM.PLAYER;
              tttp.tttM.ENGINE = "Engine";
           }else{
            tttp.tttM.specialMode = tttp.tttM.pvpMode; 
            tttp.tttM.PLAYER = "Player 1";
             tttp.tttM.currentTurn =  tttp.tttM.PLAYER;
            tttp.tttM.ENGINE = "Player 2";
           }
        }
            
            // FOWARD BUTTON
            if(forwardButton.getBounds().contains(x, y) && tttp.gameState == tttp.playState){
           if(!tttp.tttM.fowardList.isEmpty()){
              
              int forward;
              if(tttp.tttM.fowardList.peek().isEngine){
                  forward = 0; 
               }else{
                  forward = 1; 
               }
               do{
                   if(tttp.tttM.currentTurn.equalsIgnoreCase(tttp.tttM.LABEL_X)){
                       tttp.tttM.currentTurn = tttp.tttM.LABEL_O;
                   }
                 MyButton button = tttp.tttM.fowardList.pop();
                 MyButtonStore buttonStore = tttp.tttM.fowardStore.pop();
                 if(tttp.tttState == tttp.tttM.reversedTTTstate){
                   for (int i = 0; i < tttp.revTTT.tttbuttons.length; i++) {
                      for (int j = 0; j < tttp.revTTT.tttbuttons[i].length; j++) {
                     if (button == tttp.revTTT.tttbuttons[i][j]) {
                        tttp.revTTT.tttbuttons[i][j].clicked = buttonStore.clicked;
                        tttp.revTTT.tttbuttons[i][j].isEngine = buttonStore.isEngine; 
                        tttp.revTTT.tttbuttons[i][j].isX = buttonStore.isX; 
                        tttp.revTTT.tttbuttons[i][j].text = buttonStore.text;
                        tttp.tttM.backList.push(tttp.revTTT.tttbuttons[i][j]);
                          break;
                         }
                        }
                        }
                    } 
                    else if(tttp.tttState == tttp.tttM.pyramidTTTstate){
                        for (int i = 0; i < tttp.pyTTT.tttbuttons.length; i++) {
                      for (int j = 0; j < tttp.pyTTT.tttbuttons[i].length; j++) {
                     if (button == tttp.pyTTT.tttbuttons[i][j]) {
                       tttp.pyTTT.tttbuttons[i][j].clicked = buttonStore.clicked;
                        tttp.pyTTT.tttbuttons[i][j].isEngine = buttonStore.isEngine; 
                        tttp.pyTTT.tttbuttons[i][j].isX = buttonStore.isX; 
                        tttp.pyTTT.tttbuttons[i][j].text = buttonStore.text;
                        tttp.tttM.backList.push(tttp.pyTTT.tttbuttons[i][j]);
                          break;
                         }
                        }
                        }
                    }
                    else if(tttp.tttState == tttp.tttM.ttt5x5state){
                       for (int i = 0; i < tttp.ttt5x5.tttbuttons.length; i++) {
                      for (int j = 0; j < tttp.ttt5x5.tttbuttons[i].length; j++) {
                     if (button == tttp.ttt5x5.tttbuttons[i][j]) {
                        tttp.ttt5x5.tttbuttons[i][j].clicked = buttonStore.clicked;
                        tttp.ttt5x5.tttbuttons[i][j].isEngine = buttonStore.isEngine; 
                        tttp.ttt5x5.tttbuttons[i][j].isX = buttonStore.isX; 
                        tttp.ttt5x5.tttbuttons[i][j].text = buttonStore.text; 
                        tttp.tttM.backList.push(tttp.ttt5x5.tttbuttons[i][j]);
                          break;
                         }
                        }
                       }
                    }

                 if(button.isX){
                    if(tttp.tttState == tttp.tttM.reversedTTTstate){
                        tttp.revTTT.xTurn = false;
                        tttp.revTTT.turnCount++;
                        tttp.revTTT.checkState();
                    }
                    else if(tttp.tttState == tttp.tttM.pyramidTTTstate){
                        tttp.pyTTT.xTurn = false;
                        tttp.pyTTT.turnCount++;
                        tttp.pyTTT.checkState();
                    }
                    else if(tttp.tttState == tttp.tttM.ttt5x5state){
                        tttp.ttt5x5.xTurn = false;
                        tttp.ttt5x5.turnCount++;
                        tttp.ttt5x5.checkState();
                    }
                 }else{
                    if(tttp.tttState == tttp.tttM.reversedTTTstate){
                        tttp.revTTT.xTurn = true;
                        tttp.revTTT.turnCount++;
                        tttp.revTTT.checkState();
                    }
                    else if(tttp.tttState == tttp.tttM.pyramidTTTstate){
                        tttp.pyTTT.xTurn = true;
                        tttp.pyTTT.turnCount++;
                        tttp.pyTTT.checkState();
                    }
                    else if(tttp.tttState == tttp.tttM.ttt5x5state){
                        tttp.ttt5x5.xTurn = true;
                        tttp.ttt5x5.turnCount++;
                        tttp.ttt5x5.checkState();
                    } 
                 } 
                forward--;  
               }while(forward >= 0 && !tttp.tttM.fowardList.isEmpty());
              
             }
           }
        
         
            if(backButton.getBounds().contains(x, y) && tttp.gameState == tttp.playState){
              
             if(tttp.tttM.hasEnded){
                 tttp.tttM.hasEnded = false;
                   if(tttp.tttState == tttp.tttM.reversedTTTstate){
                        tttp.revTTT.hasWinner = false;
                    }
                    else if(tttp.tttState == tttp.tttM.pyramidTTTstate){
                        tttp.pyTTT.hasWinner = false;
                    }
                    else if(tttp.tttState == tttp.tttM.ttt5x5state){
                        tttp.ttt5x5.hasWinner = false;
                    } 
             }
             if(!tttp.tttM.backList.isEmpty()){
                 tttp.tttM.backState = true; 
                 
              
               int back = 1;
               if(tttp.tttM.backList.peek().isEngine || !(tttp.tttM.specialMode == tttp.tttM.pvpMode)){
                  back = 1; 
               }else{
                  back = 0; 
               }
  
               do{
                   
                 MyButton button = tttp.tttM.backList.pop();
                 MyButtonStore buttonStore = new MyButtonStore();
                 buttonStore.clicked = button.clicked;
                 buttonStore.text = button.text;
                 buttonStore.isEngine = button.isEngine;
                 buttonStore.isX = button.isX;
    
                 
                 tttp.tttM.fowardList.push(button);
                 tttp.tttM.fowardStore.push(buttonStore);
                 button.clicked = false;
                 button.text = "";
                 button.isEngine = false;
                 if(button.isX){
                    if(tttp.tttState == tttp.tttM.reversedTTTstate){
                        tttp.revTTT.xTurn = true;
                        tttp.revTTT.turnCount--;
                    }
                    else if(tttp.tttState == tttp.tttM.pyramidTTTstate){
                        tttp.pyTTT.xTurn = true;
                        tttp.pyTTT.turnCount--;
                    }
                    else if(tttp.tttState == tttp.tttM.ttt5x5state){
                        tttp.ttt5x5.xTurn = true;
                        tttp.ttt5x5.turnCount--;
                    }
                 }else{
                    if(tttp.tttState == tttp.tttM.reversedTTTstate){
                        tttp.revTTT.xTurn = false;
                        tttp.revTTT.turnCount--;
                    }
                    else if(tttp.tttState == tttp.tttM.pyramidTTTstate){
                        tttp.pyTTT.xTurn = false;
                        tttp.pyTTT.turnCount--;
                    }
                    else if(tttp.tttState == tttp.tttM.ttt5x5state){
                        tttp.ttt5x5.xTurn = false;
                        tttp.ttt5x5.turnCount--;
                    } 
                 } 
               back--;    
               }while(back >= 0);
             }
            

        }
          if(pauseButton.getBounds().contains(x, y)){
             if (tttp.gameState != tttp.pauseState) {
               tttp.storeState = tttp.gameState;  
               tttp.gameState = tttp.pauseState;
           } else if (tttp.gameState == tttp.pauseState) {

               tttp.gameState = tttp.storeState;
               tttp.storeState = 0;
            }
        }
          //RESUME
         if(resumeButton.getBounds().contains(x, y) && tttp.gameState == tttp.pauseState){
           changeDifficulty = 0;
           if(!changeDifficultyButton.text.equalsIgnoreCase("DIFFICULTY")){
               changeDifficultyButton.setText("DIFFICULTY");
           }
               
           tttp.gameState = tttp.storeState;
           tttp.storeState = 0;
  
        }
         //SWITCH
        if (switchButton.getBounds().contains(x, y) && tttp.gameState == tttp.pauseState) {
               String username = tttp.username;
               String mode = "simulation";
               String difficulty = tttp.difficulty;
               String game;
    
              if (!username.equals("")) {
                   tttp.saveLoad.save();
                    }
    
              if (tttp.isFileExist(username, difficulty)) {
                   game = "continue";
               } else {
               game = "new";
                }

            tttp.sound.stop();
              // Close the TicTacToePanel
               JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(tttp);
               frame.dispose();
    
              // Call Main again with the specified arguments
                Main.main(new String[]{difficulty, username, mode, game});

  
}

         //QUIT
         if(quitModeButton.getBounds().contains(x, y) && tttp.gameState == tttp.pauseState){
           if(tttp.storeState == tttp.titleState){
               System.exit(0);
           }
           else if(tttp.storeState == tttp.gameState){
               tttp.tttState = 0;
               tttp.gameState = tttp.titleState;
           }
           
        }
        if (revTTTStartButton.getBounds().contains(x, y) && tttp.gameState == tttp.titleState) {
 
     tttp.tttState = tttp.tttM.reversedTTTstate;
     tttp.gameState = tttp.playState;
   
   
    }

    if (ttt5x5StartButton.getBounds().contains(x, y) && tttp.gameState == tttp.titleState) {
         tttp.tttState = tttp.tttM.ttt5x5state;
         tttp.gameState = tttp.playState;
    
   
        }

if (pyTTTStartButton.getBounds().contains(x, y) && tttp.gameState == tttp.titleState) {
   tttp.tttState = tttp.tttM.pyramidTTTstate;
    tttp.gameState = tttp.playState;
 
       try {
        Thread.sleep(200); // Pause for 0.1 seconds (100 milliseconds)
    } catch (InterruptedException e) {
       
    }
}
 

         //REVTTT
       if(tttp.tttState == tttp.tttM.reversedTTTstate){
        if(tttp.revTTT.button.getBounds().contains(x, y) 
                && tttp.tttM.hasEnded){
      
         tttp.gameState = tttp.titleState;
          
         String result = tttp.revTTT.winner;
         if(tttp.tttM.backState == false){
            if(result.equals(tttp.tttM.PLAYER)){
             tttp.winCount++;
         }
         else if(result.equals(tttp.tttM.ENGINE)){
             tttp.loseCount++;
         }
         else{
             tttp.drawCount++;
         }  
         }  
         tttp.revTTT.resetBoard();
         tttp.tttM.hasEnded = false;
         
          tttp.tttM.backList.clear();
         tttp.tttM.fowardList.clear();
         tttp.tttM.fowardStore.clear();
         tttp.calculateScore();
         if(!tttp.username.equals("")){
             tttp.saveLoad.save();
         }
         tttp.tttM.backState = false;
        }
//        else if(tttp.revTTT.button.getBounds().contains(x, y)){
//           //     && !(gp.gameState == gp.playState) && (!gp.revTTT.hasWinner && gp.tttM.hasEnded)){
//          tttp.revTTT.winner = "";
//          tttp.revTTT.resetBoard();   
//          tttp.tttM.hasEnded = false;
//     
//        }
         }
        //TTT5X5
         if(tttp.tttState == tttp.tttM.ttt5x5state){
         if(tttp.ttt5x5.button.getBounds().contains(x, y)
              && tttp.tttM.hasEnded){
         tttp.gameState = tttp.titleState;
          
         String result = tttp.ttt5x5.winner;
         
         if(tttp.tttM.backState == false){
            if(result.equals(tttp.tttM.PLAYER)){
             tttp.winCount++;
         }
         else if(result.equals(tttp.tttM.ENGINE)){
             tttp.loseCount++;
         }
         else{
             tttp.drawCount++;
         }  
         }
          
         tttp.ttt5x5.resetBoard();
         tttp.tttM.hasEnded = false; 
         
         tttp.tttM.backList.clear();
         tttp.tttM.fowardList.clear();
         tttp.tttM.fowardStore.clear();
             tttp.calculateScore();
         if(!tttp.username.equals("")){
             tttp.saveLoad.save();
         }
         tttp.tttM.backState = false;
        }
       
         }
         //PYTTT
         if(tttp.tttState == tttp.tttM.pyramidTTTstate){
         if(tttp.pyTTT.button.getBounds().contains(x, y)
             && ( tttp.tttM.hasEnded)){
         
         tttp.gameState = tttp.titleState;    
         String result = tttp.pyTTT.winner;
           if(tttp.tttM.backState == false){
            if(result.equals(tttp.tttM.PLAYER)){
             tttp.winCount++;
         }
         else if(result.equals(tttp.tttM.ENGINE)){
             tttp.loseCount++;
         }
         else{
             tttp.drawCount++;
         }  
         }  
         tttp.pyTTT.resetBoard();
         tttp.tttM.hasEnded = false;  
         tttp.tttM.backList.clear();
         tttp.tttM.fowardList.clear();
         tttp.tttM.fowardStore.clear();
         tttp.calculateScore();
         if(!tttp.username.equals("")){
             tttp.saveLoad.save();
         }
         tttp.tttM.backState = false;
        }
        
         }
        
//        //ERROR BUTTON
       if(errorButton.getBounds().contains(x, y) && tttp.tttState == tttp.tttM.errorMoveState){
           tttp.tttState = tttp.tttM.storeGame;
           tttp.tttM.storeGame = 0;
        }
    }
     public void mouseClickedSimulMode(int x , int y) {

      
         if(gp.gameState == gp.gameOver && gp.isAutoSimulation){
               gp.gameState = gp.titleState;
               gp.loseCount++;
               gp.calculateScore();
               gp.saveLoad.saveLeaderboard();
           }
         if(gp.gameState == gp.goalReachState ){
               gp.gameState = gp.titleState;
               if(gp.isAutoSimulation){
                 gp.winCount++;
                 gp.calculateScore();
                 gp.saveLoad.saveLeaderboard();
               }
              
           }
         if(backButton.getBounds().contains(x, y) && gp.gameState == gp.stationState){
                
             if(gp.tttM.hasEnded){
                 gp.tttM.hasEnded = false;
                  if(gp.tttState == gp.tttM.reversedTTTstate){
                        gp.revTTT.hasWinner = false;
                    }
                    else if(gp.tttState == gp.tttM.pyramidTTTstate){
                        gp.pyTTT.hasWinner = false;
                    }
                    else if(gp.tttState == gp.tttM.ttt5x5state){
                        gp.ttt5x5.hasWinner = false;
                    }
             }
             if(!gp.tttM.backList.isEmpty()){
               int back = 1;
               if(gp.tttM.backList.peek().isEngine){
                  back = 1; 
               }else{
                  back = 0; 
               }
                gp.tttM.backState = true;
               do{
                  MyButton button = gp.tttM.backList.pop();
                 MyButtonStore buttonStore = new MyButtonStore();
                 buttonStore.clicked = button.clicked;
                 buttonStore.text = button.text;
                 buttonStore.isEngine = button.isEngine;
                 buttonStore.isX = button.isX;
    
                 
                 gp.tttM.fowardList.push(button);
                 gp.tttM.fowardStore.push(buttonStore);
                 button.clicked = false;
                 button.text = "";
                 button.isEngine = false;
                 if(button.isX){
                    if(gp.tttState == gp.tttM.reversedTTTstate){
                        gp.revTTT.xTurn = true;
                        gp.revTTT.turnCount--;
                    }
                    else if(gp.tttState == gp.tttM.pyramidTTTstate){
                        gp.pyTTT.xTurn = true;
                        gp.pyTTT.turnCount--;
                    }
                    else if(gp.tttState == gp.tttM.ttt5x5state){
                        gp.ttt5x5.xTurn = true;
                        gp.ttt5x5.turnCount--;
                    }
                 }else{
                    if(gp.tttState == gp.tttM.reversedTTTstate){
                        gp.revTTT.xTurn = false;
                        gp.revTTT.turnCount--;
                    }
                    else if(gp.tttState == gp.tttM.pyramidTTTstate){
                        gp.pyTTT.xTurn = false;
                        gp.pyTTT.turnCount--;
                    }
                    else if(gp.tttState == gp.tttM.ttt5x5state){
                        gp.ttt5x5.xTurn = false;
                        gp.ttt5x5.turnCount--;
                    } 
                 } 
               back--;    
               }while(back >= 0);
             }
            

        }
         if(forwardButton.getBounds().contains(x, y) && gp.gameState == gp.stationState){
           if(!gp.tttM.fowardList.isEmpty()){
              
              int forward;
              if(gp.tttM.fowardList.peek().isEngine){
                  forward = 0; 
               }else{
                  forward = 1; 
               }
               do{
                 if(gp.tttM.currentTurn.equalsIgnoreCase(gp.tttM.LABEL_X)){
                       gp.tttM.currentTurn = gp.tttM.LABEL_O;
                   }  
                 MyButton button = gp.tttM.fowardList.pop();
                 MyButtonStore buttonStore = gp.tttM.fowardStore.pop();
                 if(gp.tttState == gp.tttM.reversedTTTstate){
                   for (int i = 0; i < gp.revTTT.tttbuttons.length; i++) {
                      for (int j = 0; j < gp.revTTT.tttbuttons[i].length; j++) {
                     if (button == gp.revTTT.tttbuttons[i][j]) {
                        gp.revTTT.tttbuttons[i][j].clicked = buttonStore.clicked;
                        gp.revTTT.tttbuttons[i][j].isEngine = buttonStore.isEngine; 
                        gp.revTTT.tttbuttons[i][j].isX = buttonStore.isX; 
                        gp.revTTT.tttbuttons[i][j].text = buttonStore.text;
                        gp.tttM.backList.push(gp.revTTT.tttbuttons[i][j]);
                          break;
                         }
                        }
                        }
                    } 
                    else if(gp.tttState == gp.tttM.pyramidTTTstate){
                        for (int i = 0; i < gp.pyTTT.tttbuttons.length; i++) {
                      for (int j = 0; j < gp.pyTTT.tttbuttons[i].length; j++) {
                     if (button == gp.pyTTT.tttbuttons[i][j]) {
                       gp.pyTTT.tttbuttons[i][j].clicked = buttonStore.clicked;
                        gp.pyTTT.tttbuttons[i][j].isEngine = buttonStore.isEngine; 
                        gp.pyTTT.tttbuttons[i][j].isX = buttonStore.isX; 
                        gp.pyTTT.tttbuttons[i][j].text = buttonStore.text;
                        gp.tttM.backList.push(gp.pyTTT.tttbuttons[i][j]);
                          break;
                         }
                        }
                        }
                    }
                    else if(gp.tttState == gp.tttM.ttt5x5state){
                       for (int i = 0; i < gp.ttt5x5.tttbuttons.length; i++) {
                      for (int j = 0; j < gp.ttt5x5.tttbuttons[i].length; j++) {
                     if (button == tttp.ttt5x5.tttbuttons[i][j]) {
                        gp.ttt5x5.tttbuttons[i][j].clicked = buttonStore.clicked;
                        gp.ttt5x5.tttbuttons[i][j].isEngine = buttonStore.isEngine; 
                        gp.ttt5x5.tttbuttons[i][j].isX = buttonStore.isX; 
                        gp.ttt5x5.tttbuttons[i][j].text = buttonStore.text; 
                        gp.tttM.backList.push(gp.ttt5x5.tttbuttons[i][j]);
                          break;
                         }
                        }
                       }
                    }

                 if(button.isX){
                    if(gp.tttState == gp.tttM.reversedTTTstate){
                        gp.revTTT.xTurn = false;
                        gp.revTTT.turnCount++;
                        gp.revTTT.checkState();
                    }
                    else if(gp.tttState == gp.tttM.pyramidTTTstate){
                        gp.pyTTT.xTurn = false;
                        gp.pyTTT.turnCount++;
                        gp.pyTTT.checkState();
                    }
                    else if(gp.tttState == gp.tttM.ttt5x5state){
                        gp.ttt5x5.xTurn = false;
                        gp.ttt5x5.turnCount++;
                        gp.ttt5x5.checkState();
                    }
                 }else{
                    if(gp.tttState == gp.tttM.reversedTTTstate){
                        gp.revTTT.xTurn = true;
                        gp.revTTT.turnCount++;
                        gp.revTTT.checkState();
                    }
                    else if(gp.tttState == gp.tttM.pyramidTTTstate){
                        gp.pyTTT.xTurn = true;
                        gp.pyTTT.turnCount++;
                        gp.pyTTT.checkState();
                    }
                    else if(gp.tttState == gp.tttM.ttt5x5state){
                        gp.ttt5x5.xTurn = true;
                        gp.ttt5x5.turnCount++;
                        gp.ttt5x5.checkState();
                    } 
                 } 
                forward--;  
               }while(forward >= 0 && !gp.tttM.fowardList.isEmpty());
              
             }
           }
         
          //RESUME
         if(resumeButton.getBounds().contains(x, y) && gp.gameState == gp.pauseState){
           
           gp.gameState = gp.storeState;
           gp.storeState = 0;
  
        }
        //SWITCH
if (switchButton.getBounds().contains(x, y) && gp.gameState == gp.pauseState) {
    String username = gp.username;
    String mode = "tictactoe";
    String difficulty = gp.difficulty;
    String game = "continue";
    
    if (!username.equals("") && gp.gameState != gp.titleState) {
        gp.saveLoad.save();
    }
    gp.sound.stop();

    // Close the TicTacToePanel
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(gp);
    frame.dispose();

    // Call Main again with the specified arguments
    Main.main(new String[]{difficulty, username, mode, game});


}

         //QUIT
         if(quitModeButton.getBounds().contains(x, y) && gp.gameState == gp.pauseState){
           if(gp.storeState == gp.titleState){
               System.exit(0);
           }else{
              if(!gp.username.equals("") && !gp.isAutoSimulation){
                  gp.saveLoad.save();
              }
              gp.gameState = gp.titleState ;
           
           }
         
        }
          //PAUSE BUTTON
            if(pauseButton.getBounds().contains(x, y) && gp.gameState != gp.titleState){
                 
 
             if (gp.gameState != gp.pauseState) {
               gp.storeState = gp.gameState;  
               gp.gameState = gp.pauseState;

           } else if (gp.gameState == gp.pauseState) {

               gp.gameState = gp.storeState;
               gp.storeState = 0;
            }
        }     
         //REVTTT
       if(gp.tttState == gp.tttM.reversedTTTstate){
        if(gp.revTTT.button.getBounds().contains(x, y) && !(gp.gameState == gp.playState)
               && (gp.revTTT.hasWinner && gp.tttM.hasEnded) ){
         if(gp.revTTT.winner.equals(gp.tttM.PLAYER)){
             gp.gameState = gp.playState;
             gp.endState = 0;
         }else if(gp.revTTT.winner.equals(gp.tttM.ENGINE)){
            gp.gameState = gp.playState; 
            gp.stationCount--;
            if(gp.isAutoSimulation){
             gp.endState = gp.loseState;
           
            int[] item = gp.sChecker.stations.pop();
            gp.sChecker.current = item;
            if(!gp.sChecker.stations.isEmpty()){
               item = gp.sChecker.stations.pop();
               gp.sChecker.previous = item; 
            }
            else if(gp.sChecker.stations.isEmpty()){
               gp.gameState = gp.gameOver;
            }   
            }
            
            
         }   
         
         gp.revTTT.resetBoard();
         gp.tttM.hasEnded = false;
         
         gp.tttM.backList.clear();
         gp.tttM.fowardList.clear();
         gp.tttM.fowardStore.clear();
         if(!gp.username.equals("")){
             gp.saveLoad.save();
         }
         gp.tttM.backState = false;
        
        }
        else if(gp.revTTT.button.getBounds().contains(x, y) && !(gp.gameState == gp.playState)
                 && (!gp.revTTT.hasWinner && gp.tttM.hasEnded)){
          gp.revTTT.winner = "";
          gp.revTTT.resetBoard();   
          gp.tttM.hasEnded = false;
          gp.tttM.backState = false;
          gp.tttM.backList.clear();
          gp.tttM.fowardList.clear();
          gp.tttM.fowardStore.clear();
          
        }
         }
        //TTT5X5
        if(gp.tttState == gp.tttM.ttt5x5state){
         if(gp.ttt5x5.button.getBounds().contains(x, y) && !(gp.gameState == gp.playState)
             && (gp.ttt5x5.hasWinner && gp.tttM.hasEnded)){
            if(gp.ttt5x5.winner.equals(gp.tttM.PLAYER)){
             gp.gameState = gp.playState;
             gp.endState = 0;
         }else if(gp.ttt5x5.winner.equals(gp.tttM.ENGINE)){
            gp.gameState = gp.playState; 
            gp.stationCount--;
            if(gp.isAutoSimulation){
             gp.endState = gp.loseState;
           
            int[] item = gp.sChecker.stations.pop();
            gp.sChecker.current = item;
            if(!gp.sChecker.stations.isEmpty()){
               item = gp.sChecker.stations.pop();
               gp.sChecker.previous = item; 
            }
            else if(gp.sChecker.stations.isEmpty()){
               gp.gameState = gp.gameOver;
            }   
            }
            
         }
         gp.ttt5x5.resetBoard();
         gp.tttM.hasEnded = false;
          gp.tttM.backList.clear();
         gp.tttM.fowardList.clear();
         gp.tttM.fowardStore.clear();
         gp.tttM.backList.clear();
              if(!gp.username.equals("")){
             gp.saveLoad.save();
         }
          gp.tttM.backState = false;     
        }
        else if(gp.ttt5x5.button.getBounds().contains(x, y) && !(gp.gameState == gp.playState)
               && (!gp.ttt5x5.hasWinner && gp.tttM.hasEnded)){

            gp.ttt5x5.winner = "";
          gp.ttt5x5.resetBoard();  
          gp.tttM.hasEnded = false;
          gp.tttM.backState = false;
          gp.tttM.backList.clear();
         gp.tttM.fowardList.clear();
         gp.tttM.fowardStore.clear(); 
     
          }
        }
        
        //PYTTT
        if(gp.tttState == gp.tttM.pyramidTTTstate){
         if(gp.pyTTT.button.getBounds().contains(x, y) && !(gp.gameState == gp.playState)
             && (gp.pyTTT.hasWinner && gp.tttM.hasEnded)){
             if(gp.pyTTT.winner.equals(gp.tttM.PLAYER)){
             gp.gameState = gp.playState;
             gp.endState = 0;
         }else if(gp.pyTTT.winner.equals(gp.tttM.ENGINE)){
            gp.gameState = gp.playState; 
             if(gp.isAutoSimulation){
             gp.endState = gp.loseState;
           
            int[] item = gp.sChecker.stations.pop();
            gp.sChecker.current = item;
            if(!gp.sChecker.stations.isEmpty()){
               item = gp.sChecker.stations.pop();
               gp.sChecker.previous = item; 
            }
            else if(gp.sChecker.stations.isEmpty()){
               gp.gameState = gp.gameOver;
            }   
            }
            
         }
         gp.pyTTT.resetBoard();
         gp.tttM.hasEnded = false;
         
         gp.tttM.backList.clear();
              if(!gp.username.equals("")){
             gp.saveLoad.save();
         }
        gp.tttM.backState = false;      
        }
        else if(gp.pyTTT.button.getBounds().contains(x, y) && !(gp.gameState == gp.playState)
               && (!gp.pyTTT.hasWinner && gp.tttM.hasEnded)){

            gp.ttt5x5.winner = "";
          gp.pyTTT.resetBoard();  
          gp.tttM.hasEnded = false;
          gp.tttM.backState = false;
         gp.tttM.backList.clear();
         gp.tttM.fowardList.clear();
         gp.tttM.fowardStore.clear();
          }
        }
        //ERROR BUTTON
        if(errorButton.getBounds().contains(x, y) && gp.tttState == gp.tttM.errorMoveState){
           gp.tttState = gp.tttM.storeGame;
           gp.tttM.storeGame = 0;
        }
     }
     public void mouseMovedSimul(int x, int y) {
      
        if(gp.gameState == gp.stationState && gp.tttState == gp.tttM.ttt5x5state){ 
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                gp.ttt5x5.tttbuttons[i][j].mouseOver = false;
              if(gp.ttt5x5.tttbuttons[i][j].getBounds().contains(x, y)){
                   gp.ttt5x5.tttbuttons[i][j].mouseOver = true;
                  }
              }     
            }
        }
        else if(gp.gameState == gp.stationState && gp.tttState == gp.tttM.reversedTTTstate){ 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                gp.revTTT.tttbuttons[i][j].mouseOver = false;
              if(gp.revTTT.tttbuttons[i][j].getBounds().contains(x, y)){
                   gp.revTTT.tttbuttons[i][j].mouseOver = true;
                  }
              }     
            }
        }
        else if(gp.gameState == gp.stationState && gp.tttState == gp.tttM.pyramidTTTstate){ 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {

                gp.pyTTT.tttbuttons[i][j].mouseOver = false;
              if(gp.pyTTT.tttbuttons[i][j].getBounds().contains(x, y) && gp.pyTTT.tttbuttons[i][j].isEnabled){
                   gp.pyTTT.tttbuttons[i][j].mouseOver = true;
                  }
              }     
            }
        }
        
        if(gp.gameState == gp.titleState ){
           
            startButton.mouseOver = false;
            if(startButton.getBounds().contains(x, y)){

                startButton.mouseOver = true;
            }
            simulationButton.mouseOver = false;
            if(simulationButton.getBounds().contains(x, y)){
                simulationButton.mouseOver = true;
            }
            quitButton.mouseOver = false;
            if(quitButton.getBounds().contains(x, y)){
                quitButton.mouseOver = true;
            }
        }
        if(gp.pauseState == gp.gameState){
           pauseButton.mouseOver = false;
            if(pauseButton.getBounds().contains(x, y)){
                pauseButton.mouseOver = true;
            } 
          }
        }
     public void mouseMovedTTT(int x, int y) {
         if(tttp.gameState == tttp.titleState){
            MyButton[] buttons = {revTTTStartButton, ttt5x5StartButton, pyTTTStartButton, pvpButton, eveButton};

         for (MyButton button : buttons) {
            button.mouseOver = false;
    
         if (button.getBounds().contains(x, y)) {
          button.mouseOver = true;
    }
}

           
          }
        if(tttp.tttState == tttp.tttM.ttt5x5state){ 
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                tttp.ttt5x5.tttbuttons[i][j].mouseOver = false;
              if(tttp.ttt5x5.tttbuttons[i][j].getBounds().contains(x, y)){
                   tttp.ttt5x5.tttbuttons[i][j].mouseOver = true;
                  }
              }     
            }
        }
        else if(tttp.tttState == tttp.tttM.reversedTTTstate){ 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                tttp.revTTT.tttbuttons[i][j].mouseOver = false;
              if(tttp.revTTT.tttbuttons[i][j].getBounds().contains(x, y)){
                   tttp.revTTT.tttbuttons[i][j].mouseOver = true;
                  }
              }     
            }
        }
        else if(tttp.tttState == tttp.tttM.pyramidTTTstate){ 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {

                tttp.pyTTT.tttbuttons[i][j].mouseOver = false;
              if(tttp.pyTTT.tttbuttons[i][j].getBounds().contains(x, y)&& tttp.pyTTT.tttbuttons[i][j].isEnabled){
                   tttp.pyTTT.tttbuttons[i][j].mouseOver = true;
                  }
              }     
            }
        }
        }
      
    
    
    public void draw(Graphics2D g2){
       this.g2 = g2; 
 
       if(Main.mode.equals("simulation")){
           if(gp.gameState == gp.titleState){
           drawTitleScreen();
           drawStartGameButton();
           drawMenuButton();
           drawQuitButton();  

          
         }
           else if(gp.gameState == gp.gameOver){
            drawStationText();
            if(gp.isAutoSimulation){
             drawDirectionText();   
            }
            
            drawPauseButton();     
            drawGameOver();    
           }
           else if(gp.gameState == gp.goalReachState){
            drawPauseButton();     
            drawYouWin();  
           }
           else {
           if(gp.tttState ==gp.tttM.errorMoveState){
            gp.ui.drawErrorButton();
             
            }     
           if(gp.gameState == gp.stationState){
            drawBackButton();
            drawForwardButton();  
           }
          if(gp.gameState != gp.pauseState){
            drawStationText();  
          }
          
          if(gp.isAutoSimulation){
             drawDirectionText();   
            }
          if(gp.gameState == gp.pauseState){
                  drawPauseScreen();
              }
           drawPauseButton(); 
       }  
       }
       else{
           if(tttp.gameState == tttp.titleState){
              
              drawTitleScreen();
              drawPVPButton();
              drawEVEButton();
              drawRevTTTStartButton();
              drawTTT5x5StartButton();
              drawPyTTTStartButton();
              drawPauseButton();
           }
           else{
               if(tttp.tttState ==tttp.tttM.errorMoveState ){
            tttp.ui.drawErrorButton();
             
             } 
              if(tttp.gameState == tttp.pauseState){
                  drawPauseScreen();
              }
              if(tttp.gameState == tttp.playState){
              drawBackButton();
              drawForwardButton();
              }
              
              drawPauseButton(); 
           }
          
       }
     
     
    }
    public void drawPauseScreen() {
    int screenWidth = 48*20;  
    int screenHeight = 48*12; 
    g2.setColor(new Color(0, 0, 0, 150)); 
    g2.fillRect(0, 0, screenWidth, screenHeight); 
    drawChangeDifficultyButton();
    drawResumeButton();
    drawSwitchButton();
    drawQuitModeButton();
}
    public void drawChangeDifficultyButton(){
    g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));     
    changeDifficultyButton.draw(g2);
        
    }
    public void drawResumeButton() {
     g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));     
    resumeButton.draw(g2);
}

public void drawSwitchButton() {
     g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F)); 
     
    if(Main.mode.equals("simulation")){
        switchButton.text = "TICTACTOE";
    }
    else{
        switchButton.text = "SIMULATION"; 
    }
    switchButton.draw(g2);
}

public void drawQuitModeButton() {
     g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F)); 
    quitModeButton.draw(g2);
}
     public void drawDirectionText() {
    int currentRow = gp.player.worldY;
    int currentCol = gp.player.worldX;
    int[] nextTile = gp.player.nextTile;
    if(nextTile != null){
       int targetRow = nextTile[0] * gp.size;
    int targetCol = nextTile[1] * gp.size;

    String text = "";

    if (currentCol < targetCol) {
        text = "Right";
    } else if (currentCol > targetCol) {
        text = "Left";
    } else if (currentRow < targetRow) {
        text = "Down";
    } else if (currentRow > targetRow) {
        text = "Up";
    }
    
    g2.setFont(font);
       g2.setColor(Color.black);
       g2.drawString(text,553,53);
       g2.setColor(Color.white);
       g2.drawString(text,550,50);  
    }
   
    

}   
        
    public void drawStationText( ){
       g2.setFont(font);
        g2.setColor(Color.black);
       g2.drawString("Station = " + gp.stationCount,423,553);
       g2.setColor(Color.white);
       g2.drawString("Station = " + gp.stationCount,420,550);
    }
    public void drawGameOver(){
       g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Game Over";
        int x = getXforCenteredText(text);
       g2.setColor(Color.black);  
       g2.drawString(text,x+5,gp.screenHeight/2+5);
       g2.setColor(Color.white);
       g2.drawString(text,x,gp.screenHeight/2);
  
    }
    public void drawYouWin(){
       g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "You Win";
        int x = getXforCenteredText(text);
       g2.setColor(Color.black);  
       g2.drawString(text,x+5,gp.screenHeight/2+5);
       g2.setColor(Color.white);
       g2.drawString(text,x,gp.screenHeight/2);
  
    }
    public void drawPVPButton() {
     
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
    pvpButton.draw(g2);
    
    if(tttp.tttM.specialMode == tttp.tttM.pvpMode){
    g2.drawString("MODE : PvP", 60, pvpButton.getY() - pvpButton.getHeight());
    g2.setColor(new Color(0, 0, 0, 150));
    g2.fillRoundRect( 50, 48*4 + 100-48, 200, 48, 10, 10);    
    }
    
}
     public void drawEVEButton() {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
    eveButton.draw(g2);
    
    if(tttp.tttM.specialMode == tttp.tttM.eveMode){
     g2.drawString("MODE : EvE", 60, eveButton.getY() - eveButton.getHeight());
    g2.setColor(new Color(0, 0, 0, 150));
    g2.fillRoundRect( 48*4 + 100-43, 48*4 + 100-48, 200, 48, 10, 10); 
    drawE2Button();
    drawE1Button();
    }else if(tttp.tttM.specialMode == 0){
           
    g2.drawString("MODE : Default(PvE)", 60, eveButton.getY() - eveButton.getHeight());   
         }
     
}
     

    
        public void drawE1Button() {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
;
    engine1Button.draw(g2);
}
       public void drawE2Button() {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

    engine2Button.draw(g2);
}
    public void drawBackButton() {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
    backButton.draw(g2);
}

public void drawForwardButton() {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
    forwardButton.draw(g2);
}
     public void drawPauseButton( ){
      g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));   
      pauseButton.draw(g2);
    }
     public void drawStartGameButton( ){
      g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));   
      startButton.draw(g2);
    }
    public void drawMenuButton( ){
      g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));   
      simulationButton.draw(g2);
    } 
    public void drawQuitButton( ){
      g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));   
      quitButton.draw(g2);
    }
    public void drawErrorButton(){
       g2.setFont(g2.getFont().deriveFont(Font.BOLD,25F)); 
      errorButton.draw(g2);   
    }
    public void drawRevTTTStartButton() {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
    revTTTStartButton.draw(g2);
}

    public void drawTTT5x5StartButton() {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
    ttt5x5StartButton.draw(g2);
    }

    public void drawPyTTTStartButton() {
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
    pyTTTStartButton.draw(g2);
    }
 
    
    private void drawTitleScreen() {
        
        if(Main.mode.equalsIgnoreCase("simulation")){
        g2.drawImage(gp.tileM.tile[4].image, 0, 0, gp.ScreenWidth, gp.screenHeight, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
       g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
       String text = "DS Assignment";
       int x = getXforCenteredText(text);
       int y = gp.size *3;
       g2.setColor(Color.black);
       g2.drawString(text,x+5,y+5);
       g2.setColor(new Color(220, 220, 180));
       g2.drawString(text,x,y);  
        }else{
       
         tttp.tttM.drawBackground(g2);
         
         
            
        }
        
       
       
    }
    public int getXforCenteredText(String text){
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.ScreenWidth/2 - length/2;
        return x;
    }   
}
