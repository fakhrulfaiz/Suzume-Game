///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
package ds.station;

import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.assignment.TicTacToePanel;
import ds.ui.MyButton;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//public class TicTacToe extends JFrame implements ActionListener {
public class TicTacToe extends JFrame{    
    GamePanel gp;
    TicTacToePanel tttp;
    TicTacToe5x5Engine engine5X5;
    public MyButton[][] tttbuttons;
     public MyButton button;
    public boolean xTurn;
    public boolean hasWinner = false;
   
    public int turnCount;
    public BufferedImage image;
    boolean playerTurn;
    Font font;
    public String winner;
    private String loser;
    int x,y,size;
    private int count;
    Graphics2D g2;
    public TicTacToe(GamePanel gp) {
        this.gp = gp;
        this.size = gp.size;
        
        engine5X5 = new TicTacToe5x5Engine(gp);
        font = new Font("SansSerif", Font.BOLD, 20);
         x = gp.tttM.x + 40;
         y = gp.tttM.y + 40;
        
          tttbuttons = new MyButton[5][5];
          int tttsize = size *9;
           int width = (tttsize-size-33)/5;
           int height = (tttsize-size-33)/5;
            for (int i = 0; i < tttbuttons.length; i++) {
            for (int j = 0; j < tttbuttons[i].length; j++) {
                 tttbuttons[i][j] = new MyButton( x + j*width, y + i*height, width, height);
                
            }
        }
         int buttonX = gp.ScreenWidth / 2 ;
    int buttonY = gp.screenHeight / 2;
    int buttonWidth = 200;
    int buttonHeight = 90;
    // Draw the button
    button = new MyButton( buttonX, buttonY, buttonWidth, buttonHeight);     

        xTurn = true;
        turnCount = 0;
        count = 30;
    }
    
    public TicTacToe(TicTacToePanel tttp) {
        this.tttp = tttp;
        this.size = 48;
        int ScreenWidth = size*20;
        int screenHeight = size*12; 
        engine5X5 = new TicTacToe5x5Engine(tttp);
        font = new Font("SansSerif", Font.BOLD, 20);
         x = tttp.tttM.x + 40;
         y = tttp.tttM.y + 40;
        
          tttbuttons = new MyButton[5][5];
          int tttsize = size *9;
        
           int width = (tttsize-size-33)/5;
           int height = (tttsize-size-33)/5;
            for (int i = 0; i < tttbuttons.length; i++) {
            for (int j = 0; j < tttbuttons[i].length; j++) {
                 tttbuttons[i][j] = new MyButton( x + j*width, y + i*height, width, height);
                
            }
        }
         int buttonX = ScreenWidth / 2 ;
    int buttonY = screenHeight / 2;
    int buttonWidth = 200;
    int buttonHeight = 90;
    // Draw the button
    button = new MyButton( buttonX, buttonY, buttonWidth, buttonHeight);     

        xTurn = true;
        turnCount = 0;
        count = 30;
    }
    

    public void drawSomething(){
      
           for (int i = 0; i < tttbuttons.length; i++) {
            for (int j = 0; j < tttbuttons[i].length; j++) { 
                
               if( tttbuttons[i][j].clicked == false){
                tttbuttons[i][j].draw1(g2);
               }
               else if( tttbuttons[i][j].clicked == true ){

                    int tttsize = size *9;
                 int width = (tttsize-size-33)/5;
                  int height = (tttsize-size-33)/5;
                    if(tttbuttons[i][j].isX == true){
                        g2.drawImage( gp.tttM.tttComp[1].image, x + j*width, y + i*height, width, height, null);
                        g2.setColor(Color.red);
            
                        g2.drawRect(x + j*width, y + i*height, width, height);
                        tttbuttons[i][j].isDrawed = true;  
                      
                   }
                    if(tttbuttons[i][j].isX == false){
                        g2.drawImage( gp.tttM.tttComp[0].image, x + j*width, y + i*height, width, height, null);
                        g2.setColor(Color.red);
      
                         g2.drawRect(x + j*width, y + i*height, width, height);
                         
                         
                   }
 
                   
               }
            
                
            }

}    
       drawEndGameButton();
}
    
    public void drawTTTModeComp(){
          
               for (int i = 0; i < tttbuttons.length; i++) {
            for (int j = 0; j < tttbuttons[i].length; j++) { 
                
               if( tttbuttons[i][j].clicked == false){
                tttbuttons[i][j].draw1(g2);
               
               }
               else if( tttbuttons[i][j].clicked == true ){

                    int tttsize = size *9;
                 int width = (tttsize-size-33)/5;
                  int height = (tttsize-size-33)/5;
                    if(tttbuttons[i][j].isX == true){
                        g2.drawImage( tttp.tttM.tttComp[1].image, x + j*width, y + i*height, width, height, null);
                        g2.setColor(Color.red);
            
                        g2.drawRect(x + j*width, y + i*height, width, height);
                        tttbuttons[i][j].isDrawed = true;  
                      
                   }
                    if(tttbuttons[i][j].isX == false){
                        g2.drawImage( tttp.tttM.tttComp[0].image, x + j*width, y + i*height, width, height, null);
                        g2.setColor(Color.red);
      
                         g2.drawRect(x + j*width, y + i*height, width, height);
                         
                         
                   }
 
                   
               }
            
                
            }

}
  
       drawEndGameButton2();
}
    public void drawEndGameButton(){
           if (hasWinner && winner != null  && !(gp.playState == gp.gameState) && 
                   gp.tttM.hasEnded == true) {
         loser = winner.equals("X") ? "O" : "X";
    String message = "Player " + winner + " wins!";
    button.text = message;
    button.draw(g2);
    button.drawText(g2);
         }else if ((!hasWinner && "draw".equals(winner))){
     String message = "Its Draw!!";
    button.text = message;
    button.draw(g2);
    button.drawText(g2);         
         }    
    }
    public void drawEndGameButton2(){
           if (hasWinner && winner != null && tttp.tttM.hasEnded == true) {
         loser = winner.equals("X") ? "O" : "X";
    String message = "Player " + winner + " wins!";
    button.text = message;
    button.draw(g2);
    button.drawText(g2);
         }else if ((!hasWinner && "draw".equals(winner))){
     String message = "Its Draw!!";
    button.text = message;
    button.draw(g2);
    button.drawText(g2);         
         }   
    }
    
    public void drawText(){

       String text , type ;
       g2.setFont(g2.getFont().deriveFont(Font.BOLD,38));
       type = "5x5 Tic-Tac-Toe";
       g2.setColor(Color.black);
       g2.drawString(type,size,size - 2);
       g2.setColor(Color.white);
       g2.drawString(type,size,size - 5 );
         String turn;
        if(Main.mode.equals("simulation")){
         if(xTurn){
          turn = gp.tttM.LABEL_X;   
         }else{
          turn = gp.tttM.LABEL_O;   
         }     
        }
        else{
         if(xTurn){
          turn = tttp.tttM.LABEL_X;   
         }else{
          turn = tttp.tttM.LABEL_O;   
         }     
        }
        
            
       text = turn + "'s Turn";
      g2.setFont(font);
       g2.setColor(Color.white);
       g2.drawString(text,420,520);
    }
private void showWinner(String winner) {
    
   
    if(Main.mode.equals("simulation")){
      if(winner.equalsIgnoreCase("X")){
     this.winner = gp.tttM.LABEL_X;   
    }
    else if(winner.equalsIgnoreCase("O")){
     this.winner = gp.tttM.LABEL_O;   
    }else{
       this.winner = winner;   
    }  
      gp.tttM.hasEnded = true;  
    }
    else{
      if(winner.equalsIgnoreCase("X")){
     this.winner = tttp.tttM.LABEL_X;   
    }
    else if(winner.equalsIgnoreCase("O")){
     this.winner = tttp.tttM.LABEL_O;   
    }else{
       this.winner = winner;   
    }  
     tttp.tttM.hasEnded = true;     
    }
    hasWinner(winner);
    
}
public void hasWinner(String player){
    this.hasWinner = !(winner.equals("draw"));
}


public boolean checkWin(String player) {
    // check rows
    for (int row = 0; row < 5; row++) {
         for (int col = 0; col < 3; col++) {
        if (tttbuttons[row][col].text.equals(player) &&
                tttbuttons[row][col+1].text.equals(player) &&
                tttbuttons[row][col+2].text.equals(player)) {
            return true;
        }
         }
    }
    
    // check columns
    
     for (int col = 0; col < 5; col++) {
         for (int row = 0; row < 3; row++) {
        if (tttbuttons[row][col].text.equals(player) &&
                tttbuttons[row+1][col].text.equals(player) &&
                tttbuttons[row+2][col].text.equals(player)) {
            return true;
        }
      }
     }
    
    // check diagonals
     for (int row = 0; row < 3; row++) {
         for (int col = 0; col < 3; col++) {
        if (tttbuttons[row][col].text.equals(player) &&
                tttbuttons[row+1][col+1].text.equals(player) &&
                tttbuttons[row+2][col+2].text.equals(player)) {
            return true;
         }
       }
    }
     for (int row = 0; row < 3; row++) {
         for (int col = 4; col > 1; col--) {
        if (tttbuttons[row][col].text.equals(player) &&
                tttbuttons[row+1][col-1].text.equals(player) &&
                tttbuttons[row+2][col-2].text.equals(player)) {
            return true;
         }
       }
    }
    for (int row = 4; row < 1; row--) {
         for (int col = 0; col < 3; col++) {
        if (tttbuttons[row][col].text.equals(player) &&
                tttbuttons[row-1][col+1].text.equals(player) &&
                tttbuttons[row-2][col+2].text.equals(player)) {
            return true;
         }
       }
    } 
       for (int row = 4; row < 1; row--) {
         for (int col = 4; col > 1; col--) {
        if (tttbuttons[row][col].text.equals(player) &&
                tttbuttons[row-1][col-1].text.equals(player) &&
                tttbuttons[row-2][col-2].text.equals(player)) {
            return true;
         }
       }
    }

    
    // no winner
    return false;
}
   public void engineMove() {
     int i = -1;
     int j = -1; 
       count--; 

      if(count == 0){
        if(!hasWinner){  
       String difficulty = "medium";
    if(Main.mode.equals("simulation")){
        difficulty = gp.difficulty;
    }
    else{
       if(tttp.tttM.specialMode != tttp.tttM.eveMode){           
          difficulty = tttp.difficulty; 
          
       }else if(tttp.tttM.specialMode == tttp.tttM.eveMode){
           
             if(tttp.tttM.currentTurn.equals(tttp.tttM.PLAYER)){
                         
                 difficulty = tttp.tttM.engine1;  

             }else if(tttp.tttM.currentTurn.equals(tttp.tttM.ENGINE)){    
 
                 difficulty = tttp.tttM.engine2;     
            } 
       }
    }

    int[] move = engine5X5.makeMove(tttbuttons, difficulty);
     i = move[0];
     j = move[1];

    if (xTurn) {
        this.tttbuttons[i][j].clicked = true;
        this.tttbuttons[i][j].text = "X";
        this.tttbuttons[i][j].isX = true;
        this.tttbuttons[i][j].isEngine = true;
        xTurn = false;
        turnCount++;   

       
        
    } else {
        this.tttbuttons[i][j].clicked = true;
        this.tttbuttons[i][j].text = "O";
        this.tttbuttons[i][j].isX = false;
        this.tttbuttons[i][j].isEngine = true;
        xTurn = true;
        turnCount++;


           }
       count = 30;
        }
          if(Main.mode.equals("simulation")){
        if(i != -1 && j!= -1){
            gp.tttM.backList.push(tttbuttons[i][j]); 
               if(!gp.tttM.fowardList.isEmpty()){
                         gp.tttM.fowardList.clear();
                         gp.tttM.fowardStore.clear();
            }
          }  
        gp.tttM.currentTurn = gp.tttM.PLAYER;
    }
      else{
        if(i != -1 && j!= -1){
            
            tttp.tttM.backList.push(tttbuttons[i][j]);
               if(!tttp.tttM.fowardList.isEmpty()){
                         tttp.tttM.fowardList.clear();
                         tttp.tttM.fowardStore.clear();
            }
          }     
        if(tttp.tttM.specialMode != tttp.tttM.eveMode){           
           tttp.tttM.currentTurn = tttp.tttM.PLAYER; 
          
       }else if(tttp.tttM.specialMode == tttp.tttM.eveMode){
           
             if(tttp.tttM.currentTurn.equals(tttp.tttM.PLAYER)){
                 
                  tttp.tttM.currentTurn = tttp.tttM.ENGINE;   
                 
             }else if(tttp.tttM.currentTurn.equals(tttp.tttM.ENGINE)){    
                     tttp.tttM.currentTurn = tttp.tttM.PLAYER;  
            } 
       }
    }
      }
    
   
}
      void playerMove() {
       this.playerTurn = true;
    }
public void playerMove(int x , int y) {

    if(playerTurn)  { 
    for (int i = 0; i < 5;i++) {
            for (int j = 0; j < 5; j++) {
              if(tttbuttons[i][j].getBounds().contains(x, y) && tttbuttons[i][j].clicked == false &&
                     !hasWinner ){
                  
  
                  if(xTurn == true){
                      tttbuttons[i][j].clicked = true;
                      tttbuttons[i][j].text = "X";
                      tttbuttons[i][j].isX = true;
                      xTurn = false;
                      turnCount++;
                  //   gp.revTTT.checkState();

                    
                  }else{
                      tttbuttons[i][j].clicked = true;
                       tttbuttons[i][j].text = "O";
                       tttbuttons[i][j].isX = false;
                       xTurn = true;
                       turnCount++;
                     //  gp.revTTT.checkState();

                  }
                  checkState();
                   if(Main.mode.equals("simulation")){
                      gp.tttM.backList.push(tttbuttons[i][j]);   
                      gp.tttM.currentTurn = gp.tttM.ENGINE;
                       if(!gp.tttM.fowardList.isEmpty()){
                          gp.tttM.fowardList.clear();
                          gp.tttM.fowardStore.clear();
            }
                      }
                   else{
                      tttp.tttM.backList.push(tttbuttons[i][j]); 
                      if(!tttp.tttM.fowardList.isEmpty()){
                         tttp.tttM.fowardList.clear();
                         tttp.tttM.fowardStore.clear();
            }
                      tttp.tttM.currentTurn = tttp.tttM.ENGINE; 
                      
                   }
              }    
            
             else if(tttbuttons[i][j].getBounds().contains(x, y) && tttbuttons[i][j].clicked == true &&
               !hasWinner ){
                if(Main.mode.equals("simulation")){
               gp.tttM.storeGame = gp.tttM.ttt5x5state;
                gp.tttState = gp.tttM.errorMoveState;
                 }
               else{
                tttp.tttM.storeGame = tttp.tttM.ttt5x5state;
                tttp.tttState = tttp.tttM.errorMoveState;    

                 }
              }
            }
         }
     
   }
    
}
public void checkState(){
   
                 if ( checkWin("X")) {
              showWinner("X");        
        } else if ( checkWin("O")) {
            showWinner("O");
        } else if (turnCount == 25) {
           
           showWinner("draw");
          //  messageLabel.setText("Tie Game!");
         //   disableButtons();
        }
     }
public void resetBoard() {
    for (int i = 0; i < tttbuttons.length; i++) {
        for (int j = 0; j < tttbuttons[i].length; j++) {
            tttbuttons[i][j].text = "";
            tttbuttons[i][j].clicked = false;
            turnCount = 0;
        }
    }
   if(Main.mode.equals("simulation")){
       gp.tttM.hasSetGame = false;
       gp.tttState = 0; 
    }
    else{
     tttp.tttM.hasSetGame = false;
     tttp.tttState = 0;  
     tttp.tttM.hasEnded = true;     
    }
  
  this.hasWinner = false;

}
public boolean check(MyButton[][] ttt5x5) {
    
    int count = 0;
         for (int i = 0; i < ttt5x5.length; i++) {
        for (int j = 0; j < ttt5x5[i].length; j++) {
           if(ttt5x5[i][j].clicked == false && ttt5x5[i][j].text.equals("")){          
            count++;
           }
        }
    }
         return count - 1 < 0;
}

 public void draw(Graphics2D g2) {
       this.g2 = g2;
    }

}
