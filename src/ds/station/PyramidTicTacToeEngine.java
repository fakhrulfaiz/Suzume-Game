/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.station;

import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.assignment.TicTacToePanel;
import ds.ui.PyTTTButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author User
 */
import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.assignment.TicTacToePanel;
import ds.ui.PyTTTButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PyramidTicTacToeEngine {
    private static final String LABEL_X = "X"; //player
    private static final String LABEL_O = "O"; //engine
    private String PLAYER ;
    private String OPPONENT ;
     PyTTTButton[][] pyTTT;
     GamePanel gp;
     TicTacToePanel tttp;
     int maxDepth;
    PyramidTicTacToeEngine(GamePanel gp) {
       this.gp = gp;
    }

    PyramidTicTacToeEngine(TicTacToePanel tttp) {
     this.tttp = tttp;
    }
    public int[] makeMove(PyTTTButton[][] pyTTT, String difficulty) {
        if(Main.mode.equals("simulation")){
           if(!gp.pyTTT.xTurn){
             OPPONENT = LABEL_O;
             PLAYER = LABEL_X;
           }
           else{
            OPPONENT = LABEL_X;
             PLAYER = LABEL_O;   
           }
        }else{
           if(!tttp.pyTTT.xTurn){
             OPPONENT = LABEL_O;
             PLAYER = LABEL_X;
           }
           else{
            OPPONENT = LABEL_X;
             PLAYER = LABEL_O;   
           } 
        }
        if (difficulty.equals("hard")) {
         maxDepth = 10;    
         }
         else if (difficulty.equals("medium")) {
         maxDepth = 3;    
         }
         else{
         maxDepth = 1;      
         }
        // Hard Difficulty: Random Move

    
      
      List<int[]> emptyCells = getEmptyCells(pyTTT);        
    int[] bestMove = {-1, -1};
    int bestScore = Integer.MIN_VALUE;
     int score = Integer.MIN_VALUE;
     int i = 0;
    for (int[] cell : emptyCells) {
        int row = cell[0];
        int col = cell[1];
        pyTTT[row][col].text = PLAYER;
        score = minimax(pyTTT, 0,false);
        pyTTT[row][col].text = "";
       
        if(checkEngineWin(pyTTT, row, col)){

            return cell;
        }
        System.out.println(difficulty);
        System.out.println("score " +i+ "= " + score);
        
      if (score >= bestScore) {
          
                    System.out.println("cell =" + Arrays.toString(cell));
                    bestScore = score;
                    bestMove[0] = row;
                    bestMove[1] = col;
                }
      i++;
            }
       
//    if(score == bestScore){
//       Random random = new Random();
//     return emptyCells.get(random.nextInt(emptyCells.size())); 
//    }

    return bestMove;


        // Medium Difficulty: Slightly Optimal Move
//         List<int[]> emptyCells =  getEmptyCells(pyTTT); 
//        if (difficulty.equals("medium")) {
//            for (int[] cell : emptyCells) {
//                int row = cell[0];
//                int col = cell[1];
//                
//                
//                pyTTT[row][col].text = OPPONENT;
//                if (checkWin(pyTTT, OPPONENT)) {
//                    pyTTT[row][col].text = "";                    
//                    return cell;
//                  }
//                pyTTT[row][col].text = PLAYER;
//                if (checkWin(pyTTT, PLAYER)) {
//                    pyTTT[row][col].text = "";                    
//                    return cell;
//                  }
//                
//             
//                
//                
//                pyTTT[row][col].text = "";
//            }
//            Random random = new Random();
//            return emptyCells.get(random.nextInt(emptyCells.size()));
//        }
//
//        // Easy Difficulty 
//        if (difficulty.equals("easy")) {
//         List<int[]> adjacentEmptyCells = getAdjacentEmptyCells(pyTTT);
//    if (!adjacentEmptyCells.isEmpty()) {
//        for(int [] cell : adjacentEmptyCells){
//                int row = cell[0];
//                int col = cell[1];
//                
//                pyTTT[row][col].text = OPPONENT;
//                if (checkWin(pyTTT, OPPONENT)) {
//                    pyTTT[row][col].text = "";                    
//                    return cell;
//                  }
//                pyTTT[row][col].text = PLAYER;
//                if (checkWin(pyTTT, PLAYER)) {
//                    pyTTT[row][col].text = "";                    
//                    return cell;
//                  }
//                
//               
//                pyTTT[row][col].text = ""; 
//        }
//        Random random = new Random();
//        return adjacentEmptyCells.get(random.nextInt(adjacentEmptyCells.size()));
//    } else {
//        Random random = new Random();
//        return emptyCells.get(random.nextInt(emptyCells.size()));
//    }
//}
//
//        int[] move = {-1,-1};
//        return move;
    }
    private boolean checkEngineWin(PyTTTButton[][] pyTTT, int row, int col){
        pyTTT[row][col].text = OPPONENT;
        if (checkWin(pyTTT, OPPONENT) && !isBoardFull(pyTTT)) {
         pyTTT[row][col].text = "";   
        return true;
    }
     pyTTT[row][col].text = ""   ;
     return false;
    }
  
    
      private int evaluate(PyTTTButton[][] pyTTT, int depth) {

    Random random = new Random();
    int randomInt = random.nextInt(depth + 1) ;
    if(maxDepth - randomInt == 0 ){
        return random.nextInt(11) ;
    }
    else{
       if (checkWin(pyTTT, PLAYER)) {
        return 15;  
       } else if (checkWin(pyTTT, OPPONENT)) {
        return 9; 
      } else {
        return 0;  // It's a draw
      }  
    }
        
    
}
   private int minimax(PyTTTButton[][] pyTTT, int depth, boolean isMaximizing) {
        if (checkWin(pyTTT,PLAYER)) {
            
            
            return 10 - depth;
        } else if (checkWin(pyTTT,OPPONENT)) {
            return 7 - depth;
        } else if (isBoardFull(pyTTT)) {
            return 0;
        }
        
        if(depth == maxDepth){
            evaluate(pyTTT, depth);
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;

           for (int[] cell : getEmptyCells(pyTTT)) {
               int row = cell[0];
                int col = cell[1];
                pyTTT[row][col].text = PLAYER;
                int score = minimax(pyTTT, depth + 1, false);
                pyTTT[row][col].text = "";

                bestScore = Math.max(score, bestScore);
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int[] cell : getEmptyCells(pyTTT)) {
                int row = cell[0];
                int col = cell[1];
                pyTTT[row][col].text = OPPONENT;
                int score = minimax(pyTTT, depth + 1, true);
                pyTTT[row][col].text = "";
          

                bestScore = Math.min(score, bestScore);
            }

            return bestScore;
        }
    }

        private List<int[]> getEmptyCells(PyTTTButton[][] pyTTT) {
        // return a list of indices representing empty cells on the board
        // ...
        List<int[]> emptyCells = new ArrayList<>();
                for (int i = 0; i < pyTTT.length; i++) {
            for (int j = 0; j < pyTTT[i].length; j++) {
               if(pyTTT[i][j].clicked == false && pyTTT[i][j].text.equals("") && pyTTT[i][j].isEnabled){
                 int[] board = {i,j};
                 emptyCells.add(board);
               }
            }
        }
        return emptyCells;
    }

    public boolean checkWin(PyTTTButton[][] pyTTT,String player) {
     // check rows
    for (int row = 0; row < 3; row++) {
         for (int col = 0; col < 3; col++) {
        if (pyTTT[row][col].text.equals(player) &&
                pyTTT[row][col+1].text.equals(player) &&
                pyTTT[row][col+2].text.equals(player)) {
            return true;
        }
         }
    }
    
    // check columns
    
   if (pyTTT[0][2].text.equals(player) &&
                pyTTT[1][2].text.equals(player) &&
                pyTTT[2][2].text.equals(player)) {
            return true;
        }
   
   //check diagonal
     if(pyTTT[0][2].text.equals(player) && pyTTT[0][2].isEnabled &&
                   pyTTT[1][1].text.equals(player) && pyTTT[1][1].isEnabled &&
                   pyTTT[2][0].text.equals(player) && pyTTT[2][0].isEnabled){
             return true;      
                }
     
     if(pyTTT[0][2].text.equals(player) && pyTTT[0][2].isEnabled &&
                   pyTTT[1][3].text.equals(player) && pyTTT[1][3].isEnabled &&
                   pyTTT[2][4].text.equals(player) && pyTTT[2][4].isEnabled){
             return true;        
                }
    

    
    // no winner
    return false;

}
private boolean isBoardFull(PyTTTButton[][] pyTTT) {

        for (int i = 0; i < pyTTT.length; i++) {
        for (int j = 0; j < pyTTT[0].length; j++) {
           if( !pyTTT[i][j].text.equals("") && pyTTT[i][j].isEnabled){
           return false;
           }
        }
    }

        return true;
}
}
