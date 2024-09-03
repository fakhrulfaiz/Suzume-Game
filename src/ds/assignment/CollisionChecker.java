/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import ds.entity.Entity;


/**
 *
 * @author User
 */
public class CollisionChecker {
    GamePanel gp;
    
     

    public CollisionChecker(GamePanel gp) {
       this.gp = gp;
    }
     
    

    public void checkTile(Entity entity) {
      
        int leftWorldX = entity.worldX + entity.solidArea.x;
         int rightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
         int topWorldY = entity.worldY + entity.solidArea.y;
         int bottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
         
         int leftCol = leftWorldX/gp.size;
         int rightCol = rightWorldX/gp.size;
         int topRow = topWorldY/gp.size;
         int bottomRow = bottomWorldY/gp.size;
         
         int tileNum1, tileNum2;
     
         switch(entity.direction){
             case "up":
                 topRow = (topWorldY - entity.speed)/gp.size;

                 if(topRow < 0){
                     entity.collisionOn = true;
                     break;
                 }
                 tileNum1 = gp.tileM.a[topRow][leftCol].mapTile;
                 tileNum2 = gp.tileM.a[topRow][rightCol].mapTile;
                
                 if(gp.tileM.tile[tileNum1].collision == true
                         || gp.tileM.tile[tileNum2].collision == true){
                     entity.collisionOn = true;
                 }
                 break;
             case "down":
                    bottomRow = (bottomWorldY + entity.speed)/gp.size;

                    
                  if(bottomRow >= gp.maxWorldRow){
                     entity.collisionOn = true;
                     break;
                 }
                 tileNum1 = gp.tileM.a[bottomRow][leftCol].mapTile;
                 tileNum2 = gp.tileM.a[bottomRow][rightCol].mapTile;
                     
                 if(gp.tileM.tile[tileNum1].collision == true
                         || gp.tileM.tile[tileNum2].collision == true){
                     entity.collisionOn = true;
                 }
                 break;
             case "right":
                    rightCol = (rightWorldX + entity.speed)/gp.size;

                    
                 if(rightCol >= gp.maxWorldCol || bottomRow >= gp.maxWorldRow){
                     entity.collisionOn = true;
                     break;
                 }   
                 tileNum1 = gp.tileM.a[topRow][rightCol].mapTile;
                 tileNum2 = gp.tileM.a[bottomRow][rightCol].mapTile;
                 
                 if(gp.tileM.tile[tileNum1].collision == true
                         || gp.tileM.tile[tileNum2].collision == true){
                     entity.collisionOn = true;
                 }
                 break;
             case "left":
                    leftCol = (leftWorldX - entity.speed)/gp.size;

                    if(leftCol < 0){
                     entity.collisionOn = true;
                     break;
                 }
                 tileNum1 = gp.tileM.a[topRow][leftCol].mapTile;
                 tileNum2 = gp.tileM.a[bottomRow][leftCol].mapTile;
                     
                 if(gp.tileM.tile[tileNum1].collision == true
                         || gp.tileM.tile[tileNum2].collision == true){
                     entity.collisionOn = true;
                 }
                 break;
         }
    }
}
