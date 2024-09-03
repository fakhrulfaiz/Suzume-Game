/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import ds.entity.Entity;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author User
 */
public class StationChecker {
       GamePanel gp;
       public Stack<int[]> stations;
       public int[] previous;
       public int[] current;

    public StationChecker(GamePanel gp) {
       this.gp = gp;
       stations = new Stack<>();
       previous = new int[2];
       current = new int[2];
    }
    
    public void checkStation(Entity entity){
        if(gp.endState == gp.loseState ){
         int Row = previous[0];
         int Col = previous[1];
        gp.tileM.a[Row][Col].visited = false; 
        }
        int currRow = entity.worldX + entity.solidArea.x;
        int currCol = entity.worldY + entity.solidArea.y;

         
         int Col = currRow/gp.size;
         int Row = currCol/gp.size;
         int tileNum = gp.tileM.a[Row][Col].mapTile;
          if(gp.endState == gp.loseState && previous[0] == Row && previous[1] == Col){
           int r = current[0];
           int c = current[1];
          gp.tileM.a[r][c].visited = false; 
        }
          if(gp.tileM.a[Row][Col].mapTile == 3 && gp.tileM.a[Row][Col].visited == false){
              entity.goalReach = true;
          }
         
         if(gp.tileM.tile[tileNum].station == true && gp.tileM.a[Row][Col].visited == false){
             gp.stationCount++;
             System.out.println("Stations visited = " + gp.stationCount );
             int[] item = {Row,Col};
             stations.push(item);
             entity.stationOn = true;
             gp.tileM.a[Row][Col].visited = true;
          
         }
    }
}
