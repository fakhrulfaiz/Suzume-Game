/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author User
 */
public class KeyHandle implements KeyListener{
    
    public boolean up , down, left, right ;
    public final GamePanel gp;
    
    public KeyHandle(GamePanel gp){
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            up = true;
        }
        if(code == KeyEvent.VK_S){
            down = true; 
        }
        if(code == KeyEvent.VK_A){
            left = true; 
        }
        if(code == KeyEvent.VK_D){
            right = true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
         
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            up = false;
        }
        if(code == KeyEvent.VK_S){
            down = false; 
        }
        if(code == KeyEvent.VK_A){
            left = false; 
        }
        if(code == KeyEvent.VK_D){
            right = false;
        }
    }
    
}
