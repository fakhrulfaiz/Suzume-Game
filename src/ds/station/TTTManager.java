/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.station;

import ds.tile.*;
import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.assignment.PixelReading;
import ds.assignment.TicTacToePanel;
import ds.ui.MyButton;
import ds.ui.MyButtonStore;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class TTTManager {
    public Stack<MyButton> backList = new Stack<>();
    public Stack<MyButton> fowardList  = new Stack<>();
    public Stack<MyButtonStore> fowardStore = new Stack<>();
    public int ttt5x5state = 21;
    public int reversedTTTstate = 22;
    public int pyramidTTTstate = 23;
    public int errorMoveState = 24;
    public int storeGame;
    public int specialMode = 0;
    public int pvpMode = 30;
    public int eveMode = 31;
    public boolean hasSetGame = false;
    public boolean hasEnded = false;
    public boolean backState = false;
    public String currentTurn  = "player";
    public String PLAYER = "player";
    public String ENGINE = "engine";
    public  String LABEL_X;
    public  String LABEL_O;
    public  String engine1;
    public  String engine2;
    Graphics2D g2;
    GamePanel gp;
    TTTComponent[] tttComp;
    TicTacToePanel tttp;
    public int x = 40;
    public int y = 48;
    Random r = new Random();
    public TTTManager(GamePanel gp){
        this.gp = gp;
        tttComp = new  TTTComponent[5];
        
        getTileImage();
}
    public TTTManager(TicTacToePanel tttp){
        this.tttp = tttp;
        tttComp = new  TTTComponent[5];
        
        getTileImage();
}
  
  
    public final void getTileImage(){
        try{
            
            tttComp[0] = new TTTComponent();
            tttComp[0].text = "O";
            tttComp[0].image = ImageIO.read(getClass().getResource("/res/game/O_image.png")) ;
            
            tttComp[1] = new TTTComponent();
            tttComp[1].text = "X";
            tttComp[1].image = ImageIO.read(getClass().getResource("/res/game/X_image.png")) ;
            
            tttComp[2] = new TTTComponent();
            tttComp[2].text = "tttborder";
            tttComp[2].image = ImageIO.read(getClass().getResource("/res/game/tttborder.png")) ;
            
            tttComp[3] = new TTTComponent();
            tttComp[3].text = "X";
            tttComp[3].image = ImageIO.read(getClass().getResource("/res/game/tictactoe.png")) ;
            
            tttComp[4] = new TTTComponent();
            tttComp[4].image = ImageIO.read(getClass().getResource("/res/game/suzume-tttbackground.jpg")) ;
        
        }catch(IOException e){
            
        }
    }
    public void playerMove(int x, int y){
       
        if(Main.mode.equals("simulation")){
 
         if(gp.gameState == gp.stationState && gp.tttState == ttt5x5state){
             if(currentTurn.equals(PLAYER)){
             gp.ttt5x5.playerMove(x , y);
    //         gp.ttt5x5.drawSomething();
             gp.ttt5x5.drawText();
           
             }
         }
         else if(gp.gameState == gp.stationState && gp.tttState == reversedTTTstate ){
            if(currentTurn.equals(PLAYER)){
             gp.revTTT.playerMove(x , y);
    //         gp.revTTT.drawSomething();
             gp.revTTT.drawText();
     
            }
         }
          else if(gp.gameState == gp.stationState && gp.tttState == pyramidTTTstate ){
            if(currentTurn.equals(PLAYER)){
             gp.pyTTT.playerMove(x , y);
    //         gp.revTTT.drawSomething();
             gp.pyTTT.drawText();
     
            }
         }
        }
        else{
           if(tttp.tttState == ttt5x5state){
              if(currentTurn.equals(PLAYER) || ((tttp.tttM.specialMode == tttp.tttM.pvpMode) && currentTurn.equals(ENGINE))){
                        tttp.ttt5x5.playerMove(x , y);
    //         tttp.ttt5x5.drawTTTModeComp();
          
              }  

             } 
           else if(tttp.tttState == reversedTTTstate){
              if(currentTurn.equals(PLAYER) || ((tttp.tttM.specialMode == tttp.tttM.pvpMode) && currentTurn.equals(ENGINE))){
                        tttp.revTTT.playerMove(x , y);
                       
              }          
             } 
           else if(tttp.tttState == pyramidTTTstate){
              if(currentTurn.equals(PLAYER) || ((tttp.tttM.specialMode == tttp.tttM.pvpMode) && currentTurn.equals(ENGINE))){
                        tttp.pyTTT.playerMove(x , y);
                       
               }          
            }   
        }
    }
    
     
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
         
       
        if(Main.mode.equals("simulation")){
          gp.ttt5x5.draw(g2);
          gp.revTTT.draw(g2);
          gp.pyTTT.draw(g2);
          drawSimulState();  
        }
        else{
           tttp.ttt5x5.draw(g2); 
           tttp.revTTT.draw(g2); 
           tttp.pyTTT.draw(g2);
            drawTTTState();  

        }   
          
    }
    public void drawSimulState(){
         if(gp.gameState == gp.stationState && (gp.tttState ==ttt5x5state 
                 ||(gp.tttState == errorMoveState && storeGame == ttt5x5state ))){
        
           
       drawBorder(g2);
       gp.ttt5x5.drawSomething();
       gp.ttt5x5.drawText();
       gp.ui.drawPauseButton();
        
       }
       else if(gp.gameState == gp.stationState && (gp.tttState ==reversedTTTstate || 
               (gp.tttState == errorMoveState && storeGame == reversedTTTstate ))){

         drawBorder(g2);
         gp.revTTT.drawSomething();
         gp.revTTT.drawText();
          gp.ui.drawPauseButton();
        
       
       }
       else if(gp.gameState == gp.stationState && (gp.tttState == pyramidTTTstate || 
               (gp.tttState == errorMoveState && storeGame == pyramidTTTstate ))){

        
         gp.pyTTT.drawSomething();
         gp.pyTTT.drawText();
          gp.ui.drawPauseButton();
          
       
       }  
    }
    public void drawTTTState(){
       drawBackground(g2);
       if(tttp.gameState == tttp.playState &&(tttp.tttState ==ttt5x5state || 
               (tttp.tttState == errorMoveState && storeGame == ttt5x5state ))){
          
       drawBorder2(g2);
       tttp.ttt5x5.drawTTTModeComp();
       tttp.ttt5x5.drawText();

        
       }
       
       else if(tttp.gameState == tttp.playState && (tttp.tttState ==reversedTTTstate ||
               (tttp.tttState == errorMoveState && storeGame == reversedTTTstate ))){
       // 
         
         drawBorder2(g2);
         tttp.revTTT.drawTTTModeComp();
         tttp.revTTT.drawText();
        
         
       
       }
       else if(tttp.gameState == tttp.playState && (tttp.tttState ==pyramidTTTstate ||
               (tttp.tttState == errorMoveState && storeGame == pyramidTTTstate ))){
       //  
       
         tttp.pyTTT.drawTTTModeComp();
         tttp.pyTTT.drawText();
     
          
       
       }
    }
//    public void drawBorder(Graphics2D g2){
//
//            int tttsize = gp.size *9;
//         g2.drawImage(tttComp[2].image, 40, 40, tttsize-gp.size-33, tttsize-gp.size-33, null);
//         g2.drawImage(tttComp[3].image, 0, 0, tttsize, tttsize, null);
//      
//
//            }
     public void drawBackground(Graphics2D g2){
         g2.drawImage(tttComp[4].image,  0, 0, tttp.ScreenWidth, tttp.screenHeight, null);
     }
     public void drawBorder(Graphics2D g2){
          int tttsize = gp.size *9;
        // g2.drawImage(tttComp[3].image, 40, 40, tttsize-gp.size-33, tttsize-gp.size-33, null);
         g2.drawImage(tttComp[2].image,  x, y, tttsize, tttsize, null);
        
         
     }
     public void drawBorder2(Graphics2D g2){
          int tttsize = tttp.size *9;
        // g2.drawImage(tttComp[3].image, 40, 40, tttsize-gp.size-33, tttsize-gp.size-33, null);
         g2.drawImage(tttComp[2].image,  x, y, tttsize, tttsize, null);
        
         
     }
// EXPLORATION STATE
   public void getGame() {
    if (!hasSetGame) {
        int select = r.nextInt(3);
       //int select = 1;
        if (select == 0) {
            System.out.println("Selected 5x5 Tic-Tac-Toe");
            gp.tttState = ttt5x5state;
                
        } else if (select == 1) {
            System.out.println("Selected Reversed 3x3 Tic-Tac-Toe");
            gp.tttState = reversedTTTstate;
        }
        else if (select == 2) {
            System.out.println("Selected Pyramid Tic-Tac-Toe");
            gp.tttState = pyramidTTTstate;
        }
        hasSetGame = true;
    }
}
 public void updatePlayer() {
    if(Main.mode.equalsIgnoreCase("simulation")){
    if (gp.tttState == reversedTTTstate && currentTurn.equals(PLAYER) && !gp.revTTT.hasWinner) {
        if(gp.revTTT.xTurn){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }else{
            LABEL_X = ENGINE;
            LABEL_O = PLAYER;
        }
        gp.revTTT.playerMove();
         
       
    
    } else if (gp.tttState == ttt5x5state && currentTurn.equals(PLAYER) && !gp.ttt5x5.hasWinner) {
         if(gp.ttt5x5.xTurn){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }else{
            LABEL_X = ENGINE;
            LABEL_O = PLAYER;
        }
        gp.ttt5x5.playerMove();
        
    
      }
      else if (gp.tttState == pyramidTTTstate && currentTurn.equals(PLAYER) && !gp.pyTTT.hasWinner) {
          if(gp.pyTTT.xTurn){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }else{
            LABEL_X = ENGINE;
            LABEL_O = PLAYER;
        } 
        gp.pyTTT.playerMove();
        
 
      }
    }
    else{
     if (tttp.tttState == reversedTTTstate && !tttp.revTTT.hasWinner) {
        if(currentTurn.equals(PLAYER) || ((tttp.tttM.specialMode == tttp.tttM.pvpMode) && currentTurn.equals(ENGINE))){
             if(tttp.revTTT.xTurn){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }else{
            if(tttp.tttM.specialMode != tttp.tttM.pvpMode){
            LABEL_X = ENGINE;
            LABEL_O = PLAYER;  
            }
        } 
        tttp.revTTT.playerMove();
        }
    }
    else if (tttp.tttState == ttt5x5state &&  !tttp.ttt5x5.hasWinner) {
        if(currentTurn.equals(PLAYER) || ((tttp.tttM.specialMode == tttp.tttM.pvpMode) && currentTurn.equals(ENGINE))){
             if(tttp.ttt5x5.xTurn){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }else{
             if(tttp.tttM.specialMode != tttp.tttM.pvpMode){
            LABEL_X = ENGINE;
            LABEL_O = PLAYER;  
            }
        } 
        tttp.ttt5x5.playerMove();
        }
       
 
    }
    else if (tttp.tttState == pyramidTTTstate && !tttp.pyTTT.hasWinner) {
        if(currentTurn.equals(PLAYER) || ((tttp.tttM.specialMode == tttp.tttM.pvpMode) && currentTurn.equals(ENGINE))){
             if(tttp.pyTTT.xTurn){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }else{
             if(tttp.tttM.specialMode != tttp.tttM.pvpMode){
            LABEL_X = ENGINE;
            LABEL_O = PLAYER;  
            }
        } 
        tttp.pyTTT.playerMove();
        }
 
    } 
    }
}

public void updateEngine() {
  if(Main.mode.equalsIgnoreCase("simulation")){  
    if (gp.tttState == reversedTTTstate && currentTurn.equals(ENGINE) && !gp.revTTT.check(gp.revTTT.tttbuttons) && !gp.revTTT.hasWinner) {
        
        gp.revTTT.engineMove();
        gp.revTTT.checkState(); 
       
    } else if (gp.tttState == ttt5x5state && currentTurn.equals(ENGINE) && !gp.ttt5x5.check(gp.ttt5x5.tttbuttons) && !gp.ttt5x5.hasWinner) {
        gp.ttt5x5.engineMove();
        gp.ttt5x5.checkState();
        
    }
     else if (gp.tttState == pyramidTTTstate && currentTurn.equals(ENGINE) && !gp.pyTTT.check(gp.pyTTT.tttbuttons) && !gp.pyTTT.hasWinner) {
        gp.pyTTT.engineMove();
        gp.pyTTT.checkState();
        
    }
  }
  else{
      
      
     if (tttp.tttState == reversedTTTstate && !tttp.revTTT.check(tttp.revTTT.tttbuttons) && !tttp.revTTT.hasWinner) {
       if(currentTurn.equals(ENGINE) || ((tttp.tttM.specialMode == tttp.tttM.eveMode) && currentTurn.equals(PLAYER))){
           if(tttp.revTTT.xTurn && (tttp.tttM.specialMode == tttp.tttM.eveMode)){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }
           
        tttp.revTTT.engineMove();
        tttp.revTTT.checkState();    
       }  
 
       
    } else if (tttp.tttState == ttt5x5state &&!tttp.ttt5x5.check(tttp.ttt5x5.tttbuttons) && !tttp.ttt5x5.hasWinner) {
          if(currentTurn.equals(ENGINE) || ((tttp.tttM.specialMode == tttp.tttM.eveMode) && currentTurn.equals(PLAYER))){
         if(tttp.ttt5x5.xTurn && (tttp.tttM.specialMode == tttp.tttM.eveMode)){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }
        tttp.ttt5x5.engineMove();
        tttp.ttt5x5.checkState();   
          }
       
        
    }
    else if (tttp.tttState == pyramidTTTstate && !tttp.pyTTT.check(tttp.pyTTT.tttbuttons) && !tttp.pyTTT.hasWinner) {
        if(currentTurn.equals(ENGINE) || ((tttp.tttM.specialMode == tttp.tttM.eveMode) && currentTurn.equals(PLAYER))){
         if(tttp.pyTTT.xTurn && (tttp.tttM.specialMode == tttp.tttM.eveMode)){
            LABEL_X = PLAYER;
            LABEL_O = ENGINE;            
        }else{
            LABEL_X = ENGINE;
            LABEL_O = PLAYER;
        }

        tttp.pyTTT.engineMove();
        tttp.pyTTT.checkState();   
        }
        
        
    } 
  }
}       
}


