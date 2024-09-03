/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import ds.ai.PathFinder;
import ds.data.SaveLoad;
import ds.ui.UI;
import ds.entity.Player;
import ds.station.PyramidTicTacToe;
import ds.station.ReverseTicTacToe;
import ds.station.Station;
import ds.station.TTTManager;
import ds.station.TicTacToe;
//import ds.tile.Station;
import ds.tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author User
 */
public class GamePanel extends JPanel implements Runnable{
    PixelReading map = new PixelReading();
    public int winCount ;
    public int loseCount ;
    public int score ;
    //SCREEN SETTINGS
    int originalSize = 16; //16x16
    int scale = 3;
    public int size = scale*originalSize; //48x48
    public int maxCol = 20;
    public int maxRow = 12;
//    public int maxCol = map.getWidth();
//    public int maxRow = map.getHeight();
    public int ScreenWidth = size*maxCol; // 
    public int screenHeight = size*maxRow; // 
    // WORLD SETTINGS
    public final int maxWorldCol = map.getWidth();
    public final int maxWorldRow = map.getHeight();
    public final int worldWidth = size*maxWorldCol;
    public final int worldHeight = size*maxWorldRow;
    int FPS = 60; //FPS
    public CollisionChecker cChecker = new CollisionChecker(this);
    public StationChecker sChecker = new StationChecker(this);
    public Sound sound = new Sound();
    KeyHandle keyH = new KeyHandle(this);
    MouseHandler mouseH = new MouseHandler(this);
    public UI ui = new UI(this);
    public TTTManager tttM = new TTTManager(this);
    Thread gameThread;
    ShortestPath shortestPath = new ShortestPath();
    //public Station station = new Station(this);
    public Player player = new Player(this,keyH);
    public TileManager tileM = new TileManager(this);
    public TicTacToe ttt5x5 = new TicTacToe(this);
    public ReverseTicTacToe revTTT = new ReverseTicTacToe(this);
    public PyramidTicTacToe pyTTT = new PyramidTicTacToe(this);   
    
   // PauseButton pause = new PauseButton(this);
    public SaveLoad saveLoad = new SaveLoad(this);
    //GAME STATE
    public String difficulty, username;
    public  int gameState, storeState;
    public  int tttState;
    public  int endState = 0;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int stationState = 3;
    public final int loseState = 4;
    public final int goalReachState = 5;
    public final int gameOver = -1;
    public boolean isAutoSimulation;
    int speed = 1;
    public int stationCount = 0;
    public PathFinder pFinder = new PathFinder(this);


    public GamePanel(){
        
        this.setLayout(null);
     //   pause.setBounds(0, 10, size, 500);
        this.setPreferredSize(new Dimension(ScreenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.addKeyListener(keyH);
        this.setFocusable(true);
       
      //  this.add(pause);
       
    }
    public GamePanel(String difficulty,String username) {

        this(); // Call the default constructor using this()
       this.difficulty = difficulty;
       this.username = username;
       if(!username.equals("")){
           tttM.PLAYER = username;
           tttM.currentTurn = username;
       }
       if(Main.mode.equals("simulation")){
        playMusic();   
       }
      
       
      
       
    }
    public final void setupGame(){

        player.setInitialValues(this);
        
    }
     public void calculateScore() {
       this.score = (winCount*3 + -1*loseCount) * 100;
    } 
      public  boolean isFileExist(String username, String difficulty) {
      String filename = username + "_" + difficulty + ".dat"; 
         System.out.println("Filename = " + filename);
        File file = new File(filename);
        return file.exists();
    }
    
    public void startThread(){
        
        gameThread = new Thread(this);
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

    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; //draw 60 frame per second , 0.016666s   
        double nextDrawTime = System.nanoTime() + drawInterval;
        int drawCount = 0;
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

        if(gameState==titleState){
            
        }
        if(gameState==playState){
            
            if(!isAutoSimulation){
                player.updatePlayer();
            }else{
               player.updateSimulation();  
            }
     
        }
        if(gameState==pauseState){ 
       
        }
        if(gameState==stationState){
            
         if(tttM.hasSetGame == false){
             
           tttM.getGame();
         }

           if(tttM.hasEnded == false && (tttState != tttM.errorMoveState)){

            if(tttM.currentTurn.equals(tttM.PLAYER)){
             tttM.updatePlayer();    
            }
            else if(tttM.currentTurn.equals(tttM.ENGINE)){
             tttM.updateEngine();
            }
           }
        }
         if(gameState == goalReachState){
            
        }
        if(gameState == gameOver){
            
        }
        
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
        
        //TITLE SCREEN
        if(gameState == titleState){
           ui.draw(g2); 
        }

        else{
            //TILE
        tileM.draw(g2);
       
        tileM.drawPath(g2);
        
        //PLAYER
        player.draw(g2);
        //UI
        ui.draw(g2);
        //STATION
       if(gameState == stationState){
          
         tttM.draw(g2);
       }  
        }
      
       g2.dispose();
  
    }
  public void playMusic(){
      sound.play();
      sound.loop();
  }


    
  
 
 
       
}
