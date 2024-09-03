/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.ai;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author User
 */
public class KeyHandler implements KeyListener{

    public DemoPanel dp;
    
    public KeyHandler(DemoPanel dp){
        this.dp = dp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ENTER){
            
        if(dp.goalReached == false ){
           dp.search();   
        } 
        else{
            dp.resetNodes();
        }
     
            System.out.println("search");
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
