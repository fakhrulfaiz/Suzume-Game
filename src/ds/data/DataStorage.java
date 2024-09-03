 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.data;

import ds.tile.MapTile;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class DataStorage implements Serializable{
      
        int worldX;
        int worldY;
        int scX;
        int scY;
        int x, y ;
        int stationCount;
        String direction;
        boolean hasEnded;
        MapTile[][] a = new MapTile[40][20]; ;
}
