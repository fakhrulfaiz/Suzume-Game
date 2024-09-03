/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.entity;

import ds.assignment.GamePanel;
import ds.assignment.GraphAlgoritm;
import ds.assignment.KeyHandle;
import ds.assignment.Main;
import ds.assignment.MapFormation;
import ds.assignment.PixelReading;
import ds.assignment.ShortestPath;
//import ds.assignment.TicTacToe;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
/**
 *
 * @author User
 */
public final class Player extends Entity{
    GamePanel gp;
    KeyHandle keyH;
    GraphAlgoritm graph;
//    TicTacToe game;
    MapFormation mapf = new MapFormation();
    public int scX;
    public int scY;
    ShortestPath shortestPath;
    public List<int[]> path;
    int map[][];
    private final int size;
    public int stations;
    public int[] nextTile;
    public int i;
  //  public boolean goalReach;
    public Player(GamePanel gp, KeyHandle keyH) {
      this.gp = gp;
      this.keyH = keyH;  
      size = gp.size;
      speed = 2;
      i = 0;
      
      getPlayerImage();
      map = mapf.getRealMap();
      path = ShortestPath.getRandomShortestPath(map);       
      
      graph = new GraphAlgoritm();
      solidArea = new Rectangle(12,12,24,24);
     }
   
    
//     public Player(GamePanel gp, PixelReading pixel) {
//      this.gp = gp;
//      this.pixel = pixel;
//      setDefultValues();
//      getPlayerImage();
//
//      solidArea = new Rectangle(8,16,32,32);
//      
////      game = new TicTacToe();
//     }
     public void setInitialValues(GamePanel gp){
        direction = "down"; 
      
        if(gp.isFileExist(gp.username, gp.difficulty) && !gp.isAutoSimulation && Main.game.equalsIgnoreCase("continue")){
            System.out.println("FILE EXIST");
            gp.saveLoad.load();
           }
        else{
          setDefultValues();   
        }
    }
    public void setDefultValues(){
        
        worldX =  0;
        worldY =  0;
        scX = 0;
        scY = 0;
        x = y = 0;
        gp.stationCount = 0;
        
    }
    public void getPlayerImage(){
        
        try{
           up1 = ImageIO.read(getClass().getResource("/res/player/suzume_up_1.png")) ;
           up2 = ImageIO.read(getClass().getResource("/res/player/suzume_up_2.png")) ;
           down1 = ImageIO.read(getClass().getResource("/res/player/suzume_down_1.png")) ;
           down2 = ImageIO.read(getClass().getResource("/res/player/suzume_down_2.png")) ;
           left1 = ImageIO.read(getClass().getResource("/res/player/suzume_left_1.png")) ;
           left2 = ImageIO.read(getClass().getResource("/res/player/suzume_left_2.png")) ;
           right1 = ImageIO.read(getClass().getResource("/res/player/suzume_right_1.png")) ;
           right2 = ImageIO.read(getClass().getResource("/res/player/suzume_right_2.png")) ;
        }catch(IOException e){}
    }
    public void updatePlayer(){
        if(keyH.up==true){
           direction = "up" ; 
        }
        else if(keyH.down==true){
           direction = "down" ; 
        }
        else if(keyH.right==true){
           direction = "right" ;
        }
        else if(keyH.left==true){
           direction = "left" ;
        }
        
       
        if(!goalReach){
          searchPath() ;   
         }
          //CHECK COLLISION
         checkCollision();
      
//         if(!goalReach){
//          searchPath() ;   
//         }
          
             if(worldY < gp.screenHeight/2){   
             scX = worldX;
             scY = worldY;   
             }
             else if(worldY > gp.screenHeight/2){
             scX = worldX;
                if(worldY < gp.worldHeight - gp.screenHeight/2 ){
                   scY = gp.screenHeight/2;}
                else{
                   scY = gp.screenHeight/2 + (worldY - (gp.worldHeight - gp.screenHeight/2));}
                    
             }
            // IF STATION TRUE
            if(stationOn == true){
                gp.gameState = gp.stationState;
            }
             if(goalReach == true){
                gp.gameState = gp.goalReachState;
            }


               
        counter++;
        if(counter > 15){
            if(num ==1){
               num = 2; 
            }
            else if(num == 2){
               num = 1; 
            }
        counter = 0;    
        }
    }
    
    public void checkCollision(){ 
        //CHECK COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            stationOn = false;
            goalReach = false;
            gp.sChecker.checkStation(this);
            
            
            
            // IF COLLISION FALSSE, PLAYER MOVE
            if(collisionOn == false && (keyH.up || keyH.down ||keyH.right || keyH.left)){
                switch(direction){
             case "up":worldY -= speed;break;
             case "down":worldY += speed; break;               
             case "right":worldX += speed;break;
             case "left":worldX -= speed;break;
         } 
            }
    }
    
    
    public void searchPath(){
        int startCol = (worldX + solidArea.x)/gp.size;
        int startRow = (worldY + solidArea.y)/gp.size;
        
        gp.pFinder.setNodes(startRow, startCol, gp.maxWorldRow-1, gp.maxWorldCol-1);
        
        if(gp.pFinder.search() == true){
 
            gp.tileM.updatedDrawPath = true;
        }
    }
    
public List<int[]> getPath() {
    List<int[]> pathList = new ArrayList<>();
    path.forEach(coordinate -> {
        pathList.add(coordinate);
        });
    return pathList;
}

public void updateSimulation(){
   
    if (path.size() > i) {
        
        
        int currentRow = worldY;
        int currentCol = worldX;
        nextTile = path.get(i);
        
        int targetRow = nextTile[0]*size;
        int targetCol = nextTile[1]*size;
        

        if (currentRow == targetRow && currentCol == targetCol) {
            //path.remove(i);
            if( gp.endState != gp.loseState){
                i++;
            }
            else if(gp.endState == gp.loseState ){
                if(i>=0){
                  i--;  
                }  
            }    
            
        } else {
            if (currentCol < targetCol) {
                direction = "right";
                x += speed;
                worldX = (int)Math.floor(x);
            } else if (currentCol > targetCol) {
                direction = "left";
                x -= speed;
                worldX = (int)Math.floor(x);
            } else if (currentRow < targetRow) {
                direction = "down";
                y += speed;
                worldY = (int)Math.floor(y);
            } else if (currentRow > targetRow) {
                direction = "up";
                y -= speed;
                worldY = (int)Math.floor(y);
            }
        }
        
           if(worldY < gp.screenHeight/2){   
             scX = worldX;
             scY = worldY;   
             }
             else if(worldY > gp.screenHeight/2){
             scX = worldX;
                if(worldY < gp.worldHeight - gp.screenHeight/2 ){
                   scY = gp.screenHeight/2;}
                else{
                   scY = gp.screenHeight/2 + (worldY - (gp.worldHeight - gp.screenHeight/2));}
                    
             }
           //CHECK COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
             stationOn = false;
               gp.sChecker.checkStation(this);
            // IF COLLISION FALSSE, PLAYER MOVE
            if(collisionOn = false){
                switch(direction){
             case "up":
                 break;
             case "down":

                 break;
             case "right":

                 break;
             case "left":

                 break;
         } 
            }
            
         //     IF STATION TRUE
            if(stationOn == true){
                gp.gameState = gp.stationState;
            }
           if(goalReach == true){
                gp.gameState = gp.goalReachState;
            }
    
        counter++;
        if(counter > 15){
            if(num ==1){
               num = 2; 
            }
            else if(num == 2){
               num = 1; 
            }
            counter = 0;    
        }

    }
}


    public void draw(Graphics2D g2){
//         g2.setColor(Color.white);
//         g2.fillRect(x, y, gp.size, gp.size);
          
 
         BufferedImage image = null;
         
         switch(direction){
             case "up":
                 if(num==1)
                     image = up1;
                 if(num==2)
                  image = up2;
                 break;
             case "down":
                 if(num==1)
                     image = down1;
                 if(num==2)
                     image = down2;
                 break;
             case "right":
                 if(num==1)
                     image = right1;
                 if(num==2)
                      image = right2;
                 break;
             case "left":
                 if(num==1)
                     image = left1;
                 if(num==2)
                     image = left2;
                 break;
         }
        g2.drawImage(image, scX, scY, gp.size, gp.size, null);
    }
}
