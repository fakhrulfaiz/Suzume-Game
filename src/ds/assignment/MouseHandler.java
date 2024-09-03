/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author User
 */
public class MouseHandler implements MouseListener,MouseMotionListener {
    
    GamePanel gp;
    TicTacToePanel tttp;
    public MouseHandler(GamePanel gp){
        this.gp = gp;
    }
    public MouseHandler(TicTacToePanel tttp){
        this.tttp = tttp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            
            if(Main.mode.equals("simulation")){
                
                if(gp.gameState == gp.titleState){
                    gp.ui.mouseClickedTitleScreen(e.getX(), e.getY());
                }
                
               gp.ui.mouseClickedSimulMode(e.getX(), e.getY());
            if(gp.gameState == gp.stationState ){
                
                 gp.tttM.playerMove(e.getX(), e.getY());
               }  
            }
            else{
                    
                if(tttp.gameState == tttp.playState && tttp.gameState != tttp.titleState){
                 tttp.tttM.playerMove(e.getX(), e.getY());
               }
               tttp.ui.mouseClickedTTTMode(e.getX(), e.getY());    
            }  
         }
              
             
        }
    

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     }

    @Override
    public void mouseEntered(MouseEvent e) {
             
    }

    @Override
    public void mouseExited(MouseEvent e) {
     }

    @Override
    public void mouseDragged(MouseEvent e) {
         }

    @Override
    public void mouseMoved(MouseEvent e) {
    
       if(Main.mode.equals("simulation")){
           gp.ui.mouseMovedSimul(e.getX(),  e.getY()); 
        }
       else{
          tttp.ui.mouseMovedTTT(e.getX(),  e.getY());
               
            }
    }
    
}
