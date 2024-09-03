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
public class NodeButton extends JButton implements ActionListener{
    
    NodeButton parent;
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
  
    
    public NodeButton(int row, int col){
        this.col = col;
        this.row = row;
        
        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
         setFocusable(false);
    }
    public void setAsStart(){
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        start = true;
    }
    public void setAsGoal(){
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        goal = true;
    }
    public void setAsSolid(){
        setBackground(Color.black);
        setForeground(Color.white);

        solid = true;
    }
    public void setAsOpen(){
        open = true;
    }
     public void setAsChecked(){
        if(start == false && goal == false){
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        checked = true;
    }
     public void setAsPath(){
        setBackground(Color.green);
            setForeground(Color.black); 
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(solid== false){
            setAsSolid(); 
        }else{
            solid = false;
             setBackground(Color.white);
        setForeground(Color.black);
        }
       
    }
}