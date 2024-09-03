///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
package ds.station;

import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.assignment.TicTacToePanel;
import ds.ui.MyButton;
import ds.ui.PyTTTButton;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//public class TicTacToe extends JFrame implements ActionListener {
public class PyramidTicTacToe{    
    GamePanel gp;
    TicTacToePanel tttp;
    PyramidTicTacToeEngine engine;
    int x,y;
    public PyTTTButton[][] tttbuttons;
     public MyButton button;
    public boolean xTurn;
    public boolean hasWinner = false;
    public boolean hasEnded = false;
    public int turnCount;
    private int count;
    public BufferedImage image;
     boolean playerTurn;
    Font font;
    Graphics2D g2;
    public String winner;
    private String loser;
    private int size;
    
    public PyramidTicTacToe(GamePanel gp) {
        this.gp = gp;
        this.size = 48;
        engine = new PyramidTicTacToeEngine(gp);
        font = new Font("SansSerif", Font.BOLD, 20);
         x = gp.tttM.x + 40;
         y = gp.tttM.y + 40;
          tttbuttons = new PyTTTButton[3][5];
          int tttsize = size *9;
           int width = (tttsize-size-33)/5;
           int height = (tttsize-size-33)/5;
            for (int i = 0; i < tttbuttons.length; i++) {
            for (int j = 0; j < tttbuttons[i].length; j++) {
                tttbuttons[i][j] = new PyTTTButton( x + j*width, y + i*height, width, height);
                 if (j < (2 - i) || j >= (5 - (2 - i))) {
                    tttbuttons[i][j].setEnabled(false);
                     tttbuttons[i][j].text = "disabled"; 
                }
            }
        }
 
       int buttonX = gp.ScreenWidth / 2 ;
    int buttonY = gp.screenHeight / 2;
    int buttonWidth = 200;
    int buttonHeight = 90;
    // Draw the button
    button = new MyButton( buttonX, buttonY, buttonWidth, buttonHeight);     
     //   messageLabel = new JLabel();
     //   messageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
     //   getContentPane().add(messageLabel, BorderLayout.SOUTH);
//
//        setVisible(true);
        xTurn = true;
        turnCount = 0;
        count = 30;
    }
      public PyramidTicTacToe(TicTacToePanel tttp) {
        this.tttp = tttp;
        this.size = 48;
        int ScreenWidth = size*20;
        int screenHeight = size*12;
        engine = new PyramidTicTacToeEngine(tttp);
        font = new Font("SansSerif", Font.BOLD, 20);
         x = tttp.tttM.x + 40;
         y = tttp.tttM.y + 40;
          tttbuttons = new PyTTTButton[3][5];
          int tttsize = size *9;
           int width = (tttsize-size-33)/5;
           int height = (tttsize-size-33)/5;
           
             for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                tttbuttons[i][j] = new PyTTTButton( x + j*width, y + i*height, width, height);
                 if (j < (2 - i) || j >= (5 - (2 - i))) {
                    tttbuttons[i][j].setEnabled(false);
                    tttbuttons[i][j].text = "disabled";
                }
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
                
               if( this.tttbuttons[i][j].clicked == false && tttbuttons[i][j].isEnabled == true){
                tttbuttons[i][j].draw1(g2);
            
               }
               else if( tttbuttons[i][j].clicked == true  && tttbuttons[i][j].isEnabled == true){

                    int tttsize = size *9;
                 int width = (tttsize-size-33)/5;
                 int height = (tttsize-size-33)/5;
                   if(tttbuttons[i][j].isX == true){
                        g2.drawImage( gp.tttM.tttComp[1].image,   x + j*width, y + i*height, width, height, null);
                        g2.setColor(Color.red);
            
                        g2.drawRect( x + j*width, y + i*height, width, height);
                        tttbuttons[i][j].isDrawed = true;  
                      
                   }
                    if(tttbuttons[i][j].isX == false){
                        g2.drawImage( gp.tttM.tttComp[0].image,   x + j*width, y + i*height, width, height, null);
                        g2.setColor(Color.red);
      
                         g2.drawRect( x + j*width, y + i*height, width, height);
                         
                         
                   }
 
                   
               }
              
            
                
            }

}
           
    drawEndGameButton();


     }

    public void drawTTTModeComp(){
        
           for (int i = 0; i < tttbuttons.length; i++) {
            for (int j = 0; j < tttbuttons[i].length; j++) { 
                
                if( this.tttbuttons[i][j].clicked == false && tttbuttons[i][j].isEnabled == true){
                tttbuttons[i][j].draw1(g2);
               }
               else if( tttbuttons[i][j].clicked == true && tttbuttons[i][j].isEnabled == true ){

                    int tttsize = tttp.size *9;
                 int width = (tttsize-size-33)/5;
                 int height = (tttsize-size-33)/5;
                   if(tttbuttons[i][j].isX == true){
                        g2.drawImage( tttp.tttM.tttComp[1].image,  x + j*width, y + i*height, width, height, null);
                        g2.setColor(Color.red);
            
                        g2.drawRect(x + j*width, y + i*height, width, height);
                        tttbuttons[i][j].isDrawed = true;  
                      
                   }
                    if(tttbuttons[i][j].isX == false){
                        g2.drawImage( tttp.tttM.tttComp[0].image,  x + j*width, y + i*height, width, height, null);
                        g2.setColor(Color.red);
      
                         g2.drawRect(x + j*width, y + i*height, width, height);
                         
                         
                   }
 
                   
               }
              
            
                
            }

}
     drawEndGameButton2() ;     
     }  
    
    public void drawEndGameButton(){
           if (hasWinner && winner != null && !(gp.playState == gp.gameState) &&
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
       type = "Pyramid  Tic-Tac-Toe";
        g2.setColor(Color.black);
       g2.drawString(type ,size,size - 2);
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
      gp.tttM.hasEnded = true;  
      if(winner.equalsIgnoreCase("X")){
     this.winner = gp.tttM.LABEL_X;   
    }
    else if(winner.equalsIgnoreCase("O")){
     this.winner = gp.tttM.LABEL_O;   
    }else{
       this.winner = winner;   
    }
    }
    else{
     tttp.tttM.hasEnded = true; 
     if(winner.equalsIgnoreCase("X")){
     this.winner = tttp.tttM.LABEL_X;   
    }
    else if(winner.equalsIgnoreCase("O")){
     this.winner = tttp.tttM.LABEL_O;   
    }else{
       this.winner = winner;   
    }
    }
    
    hasWinner(winner);
    
}
public void hasWinner(String player){
    this.hasWinner = !(winner.equals("draw"));
}
public boolean checkWin(String player) {
    // check rows
    for (int row = 0; row < 3; row++) {
         for (int col = 0; col < 3; col++) {
        if (tttbuttons[row][col].text.equals(player) &&
                tttbuttons[row][col+1].text.equals(player) &&
                tttbuttons[row][col+2].text.equals(player)) {
            return true;
        }
         }
    }
    
    // check columns
    

        if (tttbuttons[0][2].text.equals(player) &&
                tttbuttons[1][2].text.equals(player) &&
                tttbuttons[2][2].text.equals(player)) {
            return true;
        }
      
     
     if(tttbuttons[0][2].text.equals(player) && tttbuttons[0][2].isEnabled &&
                   tttbuttons[1][1].text.equals(player) && tttbuttons[1][1].isEnabled &&
                   tttbuttons[2][0].text.equals(player) && tttbuttons[2][0].isEnabled){
             return true;      
                }
         if(tttbuttons[0][2].text.equals(player) && tttbuttons[0][2].isEnabled &&
                   tttbuttons[1][3].text.equals(player) && tttbuttons[1][3].isEnabled &&
                   tttbuttons[2][4].text.equals(player) && tttbuttons[2][4].isEnabled){
             return true;        
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
    int[] move = engine.makeMove(tttbuttons, difficulty);
     i = move[0];
     j = move[1];
    if(i == -1 && j == -1){

    }
    if (xTurn) {
        tttbuttons[i][j].clicked = true;
        tttbuttons[i][j].text = "X";
        tttbuttons[i][j].isX = true;
        tttbuttons[i][j].isEngine = true;
        xTurn = false;
        turnCount++;   

       
        
    } else {
        tttbuttons[i][j].clicked = true;
        tttbuttons[i][j].text = "O";
        tttbuttons[i][j].isX = false;
        tttbuttons[i][j].isEngine = true;
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
    for (int i = 0; i < 3;i++) {
            for (int j = 0; j < 5; j++) {
              if(tttbuttons[i][j].getBounds().contains(x, y) && tttbuttons[i][j].clicked == false &&
                     !hasWinner  && tttbuttons[i][j].isEnabled){
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
                  if(Main.mode.equals("simulation")){
                      gp.tttM.backList.push(tttbuttons[i][j]); 
                        if(!gp.tttM.fowardList.isEmpty()){
                         gp.tttM.fowardList.clear();
                         gp.tttM.fowardStore.clear();
            }
                      gp.tttM.currentTurn = gp.tttM.ENGINE;
                      }
                   else{
                      tttp.tttM.backList.push(tttbuttons[i][j]);
                        if(!tttp.tttM.fowardList.isEmpty()){
                         tttp.tttM.fowardList.clear();
                         tttp.tttM.fowardStore.clear();
            }
                      tttp.tttM.currentTurn = tttp.tttM.ENGINE; 
                   }
                  checkState();
              }    
            
             else if(tttbuttons[i][j].getBounds().contains(x, y) && tttbuttons[i][j].clicked == true &&
               !hasWinner  && tttbuttons[i][j].isEnabled){
                if(Main.mode.equals("simulation")){
               gp.tttM.storeGame = gp.tttM.pyramidTTTstate;
                gp.tttState = gp.tttM.errorMoveState;
                 }
               else{
                tttp.tttM.storeGame = tttp.tttM.pyramidTTTstate;
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
        } else if (turnCount == 9) {
           
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
  this.winner = "";
  hasWinner = false;

}
public boolean check(PyTTTButton[][] pyTTT) {
    
    int count = 0;
         for (int i = 0; i < pyTTT.length; i++) {
        for (int j = 0; j < pyTTT[i].length; j++) {
           if(pyTTT[i][j].clicked == false && pyTTT[i][j].text.equals("") &&pyTTT[i][j].isEnabled ){          
            count++;
           }
        }
    }
         return count - 1 < 0;
}

 public void draw(Graphics2D g2) {
       this.g2 = g2;
    }
 public void drawTTT(Graphics2D g2) {
       this.g2 = g2;
    }
}