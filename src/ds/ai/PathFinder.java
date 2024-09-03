/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.ai;

import ds.assignment.GamePanel;
import ds.entity.Entity;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;
    
    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNode();
    }

    private void instantiateNode() {
    
        node = new Node[gp.maxWorldRow][gp.maxWorldCol];
        
        for(int row = 0; row < gp.maxWorldRow; row++ ){
            for(int col = 0; col < gp.maxWorldCol; col++ ){
            node[row][col] = new Node(row,col);           
          }           
        }    
    }
    public void resetNodes(){
        for(int row = 0; row < gp.maxWorldRow; row++ ){
            for(int col = 0; col < gp.maxWorldCol; col++ ){
            node[row][col].open = false;
            node[row][col].checked = false;
            node[row][col].solid = false; 
            
          }           
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    public void setNodes(int startRow, int startCol, int goalRow, int goalCol){

        resetNodes();
        startNode = node[startRow][startCol];
        currentNode = startNode;
        goalNode = node[goalRow][goalCol];
        openList.add(currentNode);
        
         for(int row = 0; row < gp.maxWorldRow; row++ ){
            for(int col = 0; col < gp.maxWorldCol; col++ ){
                
            int tileNum = gp.tileM.a[row][col].mapTile;
            if(gp.tileM.tile[tileNum].collision == true){
                node[row][col].solid = true;
            }    
//            node[row][col].open = false;
//            node[row][col].checked = false;
//            node[row][col].solid = false; 
            getCost(node[row][col]) ;
          
            
            
          }           
        }
        
        
    }
    
//    private void setCostOnNodes(){
//        for(int row = 0; row <  gp.maxWorldRow; row++){
//           for(int col = 0; col < gp.maxWorldCol; col++){
//              
//               getCost(node[row][col]);
//           }
//       }
//    
//    }
    private void getCost(Node node){
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
        
//        if(node!= startNode && node!= goalNode){
//           node.setText("<html><font color='red'>F:</font> " + node.fCost + "<br><font color='green'>G:</font> " + node.gCost + "</html>");
//        }
        
        
    }
    public boolean search(){
        while(goalReached == false){
               
            int col = currentNode.col;
            int row = currentNode.row;
              
               currentNode.checked = true;
               checkList.add(currentNode);
               openList.remove(currentNode);
            //open up   
            if(row-1>0)
                 openNode(node[row-1][col]);
            //open left
            if(col-1>0)
                 openNode(node[row][col-1]);
           // open down
           if(col+1<gp.maxWorldCol)
                 openNode(node[row][col+1]);
            //open right
            if(row+1< gp.maxWorldRow){
            openNode(node[row+1][col]);}
            
            
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            
              for(int k=0;k < openList.size(); k++){
                  
                  if(openList.get(k).fCost < bestNodefCost){
                     bestNodeIndex = k; 
                     bestNodefCost = openList.get(k).fCost;
                  }
                  else if(openList.get(k).fCost == bestNodefCost){
                     if(openList.get(k).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = k; 
                     } 
                  }
              }
    
         if(openList.isEmpty()){
             return true;
         }
                       
              currentNode = openList.get(bestNodeIndex);
               if(currentNode == goalNode){
                 goalReached = true; 
                 trackThePath();
              }
           
       
  
        }
        return goalReached;
    }
    public void openNode(Node node){
        if(node.open == false && node.checked ==false && node.solid == false){
           node.setAsOpen();
           node.parent = currentNode;
           openList.add(node);
        }
          
    }
    
    private void trackThePath(){
       Node current = goalNode;
       
       while(current != startNode){
           pathList.add(0,current);
           current = current.parent;
           
//           if(current != startNode){
//             current.setAsPath();
//           }
           
       }
    }
    
    
}

    

