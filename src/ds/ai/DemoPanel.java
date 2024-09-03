/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.ai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class DemoPanel extends JPanel{
    final int maxCol = 30;
    final int maxRow = 22;
    final int nodeSize = 34;
    
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;
    NodeButton[][] node = new NodeButton[maxRow][maxCol];
    NodeButton startNode, goalNode, currentNode;
    ArrayList<NodeButton> openList = new ArrayList<>();
    ArrayList<NodeButton> checkList = new ArrayList<>();
    boolean goalReached;
  
    public DemoPanel(){
        
        this.setPreferredSize(new Dimension(screenWidth,screenHeight ));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow,maxCol));
        this.addKeyListener(new KeyHandler(this));
        setFocusable(true);
       for(int row = 0; row < maxRow; row++){
           for(int col = 0; col < maxCol; col++){
               node[row][col] = new NodeButton(row,col);
               this.add(node[row][col]);
           }
       }
       
       setStartNode(18,1);
       setGoalNode(1,27);
       
       
//       setSolidNode(3,3);
//       setSolidNode(3,4);
//       setSolidNode(3,5);
//       setSolidNode(3,6);
//       setSolidNode(3,7);
//       setSolidNode(4,7);
//       setSolidNode(5,7);
//       setSolidNode(6,7);
//       setSolidNode(6,8);
//       setSolidNode(6,9);
//       setSolidNode(6,9);
//       setSolidNode(7,9);
//       setSolidNode(7,10);
//       setSolidNode(7,11);
//       setSolidNode(7,12);
//       setSolidNode(8,12);
       setCostOnNodes();
       
    }
    
    private void setStartNode(int row,int col){
        node[row][col].setAsStart();
        startNode = node[row][col];
        currentNode =startNode;
    }
    private void setGoalNode(int row,int col){
        node[row][col].setAsGoal();
        goalNode = node[row][col];
      
    }
    private void setSolidNode(int row,int col){
        node[row][col].setAsSolid();
       
      
    }
    private void setCostOnNodes(){
        for(int row = 0; row < maxRow; row++){
           for(int col = 0; col < maxCol; col++){
              
               getCost(node[row][col]);
           }
       }
        
        
    }
     public void resetNodes(){
        for(int row = 0; row < maxRow; row++ ){
            for(int col = 0; col < maxCol; col++ ){
            node[row][col].open = false;
            node[row][col].checked = false;
            node[row][col].solid = false; 
            
          }           
        }
        openList.clear();
        checkList.clear();
        goalReached = false;
    
    }
    private void getCost(NodeButton node){
        //The distance from start node
        int xDist = Math.abs(node.col - startNode.col);
        int yDist = Math.abs(node.row - startNode.row);
        node.gCost = xDist + yDist;
        //The distance from goal node
        xDist = Math.abs(node.col - goalNode.col);
        yDist = Math.abs(node.row - goalNode.row);
        node.hCost = xDist + yDist;
        // Total Cost
        node.fCost = node.gCost + node.hCost;
        
        if(node!= startNode && node!= goalNode){
           node.setText("<html><font color='red'>F:</font> " + node.fCost + "<br><font color='green'>G:</font> " + node.gCost + "</html>");
        }
        
        
    }
   public void search() {
    new Thread(() -> {
        while (!goalReached) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkList.add(currentNode);
            openList.remove(currentNode);

            // open up   
            if (row - 1 > 0)
                openNode(node[row - 1][col]);
            // open left
            if (col - 1 > 0)
                openNode(node[row][col - 1]);
            // open down
            if (col + 1 < maxCol)
                openNode(node[row][col + 1]);
            // open right
            if (row + 1 < maxRow) {
                openNode(node[row + 1][col]);
            }

            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int k = 0; k < openList.size(); k++) {

                if (openList.get(k).fCost < bestNodefCost) {
                    bestNodeIndex = k;
                    bestNodefCost = openList.get(k).fCost;
                } else if (openList.get(k).fCost == bestNodefCost) {
                    if (openList.get(k).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = k;
                    }
                }
            }

            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }

            try {
                Thread.sleep(7); // Delay of 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }).start();
}

    public void openNode(NodeButton node){
        if(node.open == false && node.checked ==false && node.solid == false){
           node.setAsOpen();
           node.parent = currentNode;
           openList.add(node);
        }
          
    }
    
    private void trackThePath(){
       NodeButton current = goalNode;
       
       while(current != startNode){
           current = current.parent;
           
           if(current != startNode){
             current.setAsPath();
           }
           
       }
    }
    
    
}
