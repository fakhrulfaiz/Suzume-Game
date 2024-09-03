/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.tile;

import ds.assignment.GamePanel;
import ds.assignment.MapFormation;
import ds.assignment.PixelReading;
import ds.assignment.ShortestPath;
import ds.entity.Entity;
import ds.entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class TileManager implements Serializable{
    public MapTile[][] a; // Maptile class
    GamePanel gp;
    public Tile[] tile;
    MapFormation map = new MapFormation();
 //   public int[][] mapTile;
    public int[][] originalMap;
    public List<int[]> path;
    boolean haseSetPathColor = false;
    public boolean updatedDrawPath;
    Color randomColor;
 //   public boolean[][] visited;
    public TileManager(GamePanel gp){
        
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
//        mapTile = new int[gp.maxWorldCol][gp.maxWorldRow];
         a = new MapTile[gp.maxWorldRow][gp.maxWorldCol];
         originalMap = map.getRealMap();
         
         loadMap();
         loadPath();
}
    
    
    public final void loadMap(){
        for(int row = 0 ; row < a.length ; row++){
            for(int col = 0 ; col < a[0].length ; col++){
               a[row][col] = new MapTile(); 
               a[row][col].mapTile = originalMap[row][col]; 
            }
        }
    
    }
    public final void loadPath(){
        path = new ArrayList<>();
        for(int[] coordinate : gp.player.path){
            path.add(coordinate);
        }
    
    }
    
    public final void getTileImage(){
        try{
            
            tile[0] = new Tile();
            tile[0].collision = false;
            tile[0].image = ImageIO.read(getClass().getResource("/res/tile/road01.png")) ;
            
            
            tile[1] = new Tile();
            tile[1].collision = true;
            tile[1].image = ImageIO.read(getClass().getResource("/res/tile/tree3.png")) ;
            
            tile[2] = new Tile();
            tile[2].collision = false;
            tile[2].station = true;
            tile[2].image = ImageIO.read(getClass().getResource("/res/tile/checkpoint2.png")) ;
            
            tile[3] = new Tile();
            tile[3].collision = false;
            tile[3].image = ImageIO.read(getClass().getResource("/res/tile/door.png")) ;
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/res/tile/suzume-background.jpg")) ;
        
            
        
        }catch(IOException e){
            
        }
    }
    public void draw(Graphics2D g2){
//        g2.drawImage( tile[0].image, 0, 0, gp.size, gp.size, null);
//        g2.drawImage( tile[1].image, 48, 0, gp.size, gp.size, null);
//        g2.drawImage( tile[2].image, 96, 0, gp.size, gp.size, null);
        
        int worldCol = 0;
        int worldRow = 0;
//        int x =0;
//        int y = 0;
       
        while(worldCol < gp.maxWorldCol && worldRow <  gp.maxWorldRow){
            int tileNum = a[worldRow][worldCol].mapTile;
            
            int worldX = worldCol * gp.size;
            int worldY = worldRow * gp.size;
            int screenX  = worldX - gp.player.worldX + gp.player.scX;
            int screenY  = worldY - gp.player.worldY + gp.player.scY; 
              
            g2.drawImage( tile[tileNum].image, screenX, screenY, gp.size, gp.size, null);
            worldCol++;


            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;


            }
        }
    }
    public void drawPath(Graphics2D g2){
      // Draw path
    if(gp.isAutoSimulation){
      for (int[] coordinate : path) {
        int worldX = coordinate[1] * gp.size + gp.size / 2 - gp.player.worldX + gp.player.scX;
        int worldY = coordinate[0] * gp.size + gp.size / 2 - gp.player.worldY + gp.player.scY;
        
       drawCircle(g2,worldX,worldY);
    }   
    }else{
        
        if(updatedDrawPath){
        for(int i = 0; i<gp.pFinder.pathList.size();i++){
        int worldX = gp.pFinder.pathList.get(i).col * gp.size + gp.size / 2 - gp.player.worldX + gp.player.scX;
        int worldY = gp.pFinder.pathList.get(i).row * gp.size + gp.size / 2 - gp.player.worldY + gp.player.scY;    
        drawCircle(g2,worldX,worldY);
        }    
         
        }
      }
    }
    public void drawCircle(Graphics2D g2, int worldX,int worldY){
         // Set random color
        if(!haseSetPathColor){
            Random rand= new Random();
            float red = rand.nextFloat() * 0.1f; // Adjust the range as needed
            float green = rand.nextFloat() * 0.1f;
            float blue = rand.nextFloat() * 0.1f;
	    randomColor = new Color(red, green, blue);
            haseSetPathColor = true;     
        }
        
        
        // Draw small circle at center
        int size = 10; // Adjust the size as needed
        int circleX = worldX - size / 2;
        int circleY = worldY - size / 2;
        g2.setColor(randomColor);
        g2.fillOval(circleX, circleY, size, size);
    }
   
    
    }
//    public void draw(Graphics2D g2){
//
//        
//        int worldCol = 0;
//        int worldrow = 0;
//
//       
//        while(worldCol < gp.maxWorldCol && worldrow <  gp.maxWorldCol){
//            int tileNum = mapTile[worldrow][worldCol];
//            
//            int worldX = worldCol* gp.size;
//            int worldY = worldrow* gp.size;
//            int screenX =worldX - gp.player.worldX + gp.player.scX;
//            int screenY =worldY - gp.player.worldY + gp.player.scY;
//            g2.drawImage( tile[tileNum].image, screenX, screenY, gp.size, gp.size, null);
//            worldCol++;
//
//            
//            if(worldCol == gp.maxCol){
//                worldCol = 0;
//                worldrow++;
//
//            }
//        }
//    }

