/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.ai;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class Node{
    
    Node parent;
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean checked;
    boolean open;
  
    
    public Node(int row, int col){
        this.col = col;
        this.row = row;
        
        
    }
    public void setAsStart(){
        
        start = true;
    }
    public void setAsGoal(){
        
        goal = true;
    }
    public void setAsSolid(){
  

        solid = true;
    }
    public void setAsOpen(){
        open = true;
    }
     public void setAsChecked(){
        if(start == false && goal == false){
      
        }
        checked = true;
    }
     public void setAsPath(){
   
     }
   
}
