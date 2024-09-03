/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import ds.data.SaveLoad;
import ds.data.TTTSaveLoad;
import ds.entity.Player;
import ds.station.PyramidTicTacToe;
import ds.station.ReverseTicTacToe;
import ds.station.TTTManager;
import ds.station.TicTacToe;
import ds.tile.TileManager;
import ds.ui.UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class TicTacToePanel extends JPanel implements Runnable{
 
    
    public UI ui = new UI(this);
    public TTTManager tttM = new TTTManager(this);
    public TicTacToe ttt5x5 = new TicTacToe(this);
    public ReverseTicTacToe revTTT = new ReverseTicTacToe(this);
     public PyramidTicTacToe pyTTT = new PyramidTicTacToe(this);   
    public MouseHandler mouseH = new MouseHandler(this);
    public TTTSaveLoad saveLoad = new TTTSaveLoad(this);
    public Sound sound = new Sound();
     Thread gameThread = new Thread(this);
    //GAME STATE
    public String difficulty, username;
    public  int gameState;
    public final int titleState = 0 ;
     public final int playState = 1;
    public final int pauseState = 2;
    public int storeState ;
    public  int tttState;
    public  int  score,  winCount,  drawCount,  loseCount;
   //public final int titleState = 0;
   
     int FPS = 60; //FPS
 
    int originalSize = 16; //16x16
    int scale = 3;
    public final int size = scale*originalSize; //48x48
    public final int maxCol = 20;
    public final int maxRow = 12;

    public final int ScreenWidth = size*maxCol; // 
    public final int screenHeight = size*maxRow; // 

    public TicTacToePanel(){
        
        this.setLayout(null);
     //   pause.setBounds(0, 10, size, 500);
        this.setPreferredSize(new Dimension(ScreenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);

        this.setFocusable(true);
       
      //  this.add(pause);
       
    }
    public TicTacToePanel(String difficulty,String username) {

        this(); // Call the default constructor using this()
       this.difficulty = difficulty;
       this.username = username;
       if(!username.equals("")){
           tttM.PLAYER = username;
           tttM.currentTurn = username;
       }
       setupGame();
      
       
    }
    public final void setupGame(){
        if(Main.mode.equals("tictactoe")){
           playMusic();  
        }
    
        gameState = titleState;
       
        saveLoad.load();
    }
   
    public void calculateScore() {
       this.score = (winCount*3 + drawCount*1 + loseCount*-3) * 100;
    }  
    
    public void startThread(){
        
       
        gameThread.start();
    }
    public void stopThread() {
    if (gameThread != null) {
        try {
            gameThread.join();
        } catch (InterruptedException e) {
           
        }
        gameThread = null;
    }
}

    
     public  boolean isFileExist(String username, String difficulty) {
      String filename = username + "_" + difficulty + ".dat"; 
         System.out.println("Filename = " + filename);
        File file = new File(filename);
        return file.exists();
    }
    @Override
    
    public void run() {
        
        
        double drawInterval = 1000000000/FPS; //draw 60 frame per second , 0.016666s   
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            
            // UPDATE : update position
            update();
            //DRAW : draw
            repaint();
           
            
            try {
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime = remainTime/1000000;
                if(remainTime < 0){
                    remainTime = 0;
                }
                 Thread.sleep((long) remainTime);
                 nextDrawTime += drawInterval;
                 
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void update(){

      if(gameState== titleState){
            
        }
      if(gameState==pauseState){ 
       
      }
//        if(gameState==playState){
//            player.updatePlayer();
//            
//         //   player.updateSimulation();
//        }
//        if(gameState==pauseState){ 
//        }
        if( gameState== playState){
           
         if(tttM.hasSetGame == false){
//          

             
           
         }

        if(tttM.hasEnded == false && (tttState != tttM.errorMoveState)){
          if(tttM.specialMode == 0){
                if(tttM.currentTurn.equals(tttM.PLAYER)){
                  tttM.updatePlayer();    
            }
                else if(tttM.currentTurn.equals(tttM.ENGINE)){
                  tttM.updateEngine();
            }
             
              
          }else if(tttM.specialMode == tttM.eveMode){
                if(tttM.currentTurn.equals(tttM.PLAYER)){
                  tttM.updateEngine();    
            }
                 else if(tttM.currentTurn.equals(tttM.ENGINE)){
                  tttM.updateEngine();
            }
          }
          
          
          else if(tttM.specialMode == tttM.pvpMode){
                 if(tttM.currentTurn.equals(tttM.PLAYER)){
                   tttM.updatePlayer();    
                    }
                 else if(tttM.currentTurn.equals(tttM.ENGINE)){
                   tttM.updatePlayer();
                   }   
          }
            
           } 
        }  
   
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
       
        //STATION
        if(gameState == titleState){
           ui.draw(g2); 
        }else{
            
            tttM.draw(g2); 
            ui.draw(g2); 
        }
         
         
         
        
          
       
      
       g2.dispose();
  
    }
 public void playMusic(){
      sound.play();
      sound.loop();
  }
    
       
}
