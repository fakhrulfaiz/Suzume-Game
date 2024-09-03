/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author User
 */
public class MyButton {
    public int x,y ,width, height;
    public String text = "";
    private Rectangle bounds;
    public boolean clicked = false;
    public boolean isX = false;
    public boolean isEngine = false;
    public boolean mouseOver ;
    public boolean isDrawed = false;
    public MyButton(String text, int x, int y, int width, int height){
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        initBounds();
    }
        
    public MyButton(int x, int y, int width, int height){
       // this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        initBounds();
    }
    public MyButton(){
       
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    private void initBounds(){
        this.bounds = new Rectangle( x,  y,  width,  height);
    }
    public void setText(String text) {
        this.text = text;
    }
    
  public void draw(Graphics2D g2){
      
    // Constants for corner rounding
    int arcWidth = 10;
    int arcHeight = 10;
    
    // Blurring effect
    drawButtonsBody(g2,arcWidth, arcHeight);
    
    
    // Border
    g2.setColor(Color.white);
    g2.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    
    // Text

    drawText(g2);
    
}

  public void drawButtonsBody(Graphics2D g2,int arcWidth,int arcHeight){

      if(mouseOver == true){
       g2.setColor(new Color(0, 0, 0,100));
      }
      else{
      g2.setColor(new Color(0, 0, 0, 80));   
      }
      g2.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
      
  }
  public void draw1(Graphics2D g2) {
    // Constants for corner rounding
      
    
    // Blurring effect
    drawBody(g2);
 //   g2.setColor(new Color(0, 0, 0, 50));
  //  g2.fillRect(x, y, width, height);

    // Border
    g2.setColor(Color.white);
    g2.drawRect(x, y, width, height);
    
}
  public void drawBody(Graphics2D g2){
      if(mouseOver == true){
       g2.setColor(new Color(0, 0, 0,200));
      }
      else{
      g2.setColor(new Color(0, 0, 0, 80));   
      }
      g2.fillRect(x, y, width, height);
  }
  

  public void drawText(Graphics2D g2) {
    FontMetrics fontMetrics = g2.getFontMetrics();
    int textWidth = fontMetrics.stringWidth(text);
    int textHeight = fontMetrics.getHeight();
    int centerX = x + (width - textWidth) / 2;
    int centerY = y + (height)/2 + textHeight/2 - 10;

    g2.drawString(text, centerX, centerY);
}
    public Rectangle getBounds(){
        return bounds;
    }
     public void setMouseOver(boolean mouseOver){
       this.mouseOver = mouseOver ;
    }
  
    
   
    
    
    
    
}
