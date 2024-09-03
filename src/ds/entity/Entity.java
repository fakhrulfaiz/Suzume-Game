/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class Entity {
   public int worldX, worldY;
   public int speed , x, y;
   
   public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
   public String direction = "down";
   
   public int counter = 0;
   public int num = 1;
   
   public Rectangle solidArea;
   public boolean collisionOn = false;
   public boolean stationOn = false;
   public boolean goalReach = false;
   
}
