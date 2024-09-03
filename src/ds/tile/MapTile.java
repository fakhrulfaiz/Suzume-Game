/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.tile;

import ds.assignment.GamePanel;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class MapTile implements Serializable {
    public int mapTile;
    public boolean visited;

    public MapTile() {
        visited = false;
    }
}
