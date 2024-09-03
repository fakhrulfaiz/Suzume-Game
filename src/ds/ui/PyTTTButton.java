/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.ui;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author User
 */
public class PyTTTButton extends MyButton{
    public boolean isEnabled = true;
    public PyTTTButton(String text, int x, int y, int width, int height) {
        super(text, x, y, width, height);
     
    }
     public PyTTTButton( int x, int y, int width, int height) {
        super( x, y, width, height);
      
    }
     public void setEnabled(boolean isEnabled){
         this.isEnabled = isEnabled;
     }


    @Override
      public void draw1(Graphics2D g2) {
    // Constants for corner rounding
      
    
    // Blurring effect
    drawBody(g2);
    g2.setColor(Color.white);
    g2.drawRect(x, y, width, height);
    
}
    @Override
  public void drawBody(Graphics2D g2){
      if(mouseOver == true){
       g2.setColor(new Color(0, 0, 0,200));
      }
      else{
      g2.setColor(new Color(0, 0, 0, 80));   
      }
      g2.fillRect(x, y, width, height);
  } 
}
