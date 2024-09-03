/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.station;

import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.assignment.TicTacToePanel;
import ds.ui.MyButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author User10
 */
public class TicTacToeEngineReverse {
    private static final String LABEL_X = "X"; //player
    private static final String LABEL_O = "O"; //engine
    private String PLAYER ;
    private String OPPONENT ;
    private int maxDepth;
     MyButton revTTT;
     GamePanel gp;
     String difficulty;
   TicTacToePanel tttp;
    TicTacToeEngineReverse(GamePanel gp) {
       this.gp = gp;
    }
    TicTacToeEngineReverse(TicTacToePanel tttp) {
       this.tttp = tttp;
    }
    public int[] makeMove(MyButton[][] revTTT, String difficulty) {
        this.difficulty = difficulty;
        List<int[]> emptyCells = getEmptyCells(revTTT);
          if(Main.mode.equals("simulation")){
           if(!gp.revTTT.xTurn){
             OPPONENT = LABEL_O;
             PLAYER = LABEL_X;
           }
           else{
            OPPONENT = LABEL_X;
             PLAYER = LABEL_O;   
           }
        }else{
           if(!tttp.revTTT.xTurn){
             OPPONENT = LABEL_O;
             PLAYER = LABEL_X;
           }
           else{
            OPPONENT = LABEL_X;
             PLAYER = LABEL_O;   
           } 
        }
        // Easy Difficulty: Random Move
         if (difficulty.equals("hard")) {
         maxDepth = 10;    
         }
         else if (difficulty.equals("medium")) {
         maxDepth = 3;    
         }
         else{
         maxDepth = 1;      
         }
//         if (difficulty.equals("hard")) {
      
            int[] bestMove = {-1,-1};
            int bestScore = Integer.MIN_VALUE;
            int i = 0;
            for (int[] cell : emptyCells) {
                int row = cell[0];
                int col = cell[1];
                
                revTTT[row][col].text = PLAYER;
                int score = minimax(revTTT,0, false, maxDepth);
                revTTT[row][col].text = "";
                if(checkEngineLose(revTTT,row,col)){
                    score = -100;
                }
                
//                System.out.println("score " + i + " =" + score);
  
                if(score == bestScore && emptyCells.size() == 2 && i != 0){
                   int a = checkBestMove(revTTT,emptyCells,i,score) ;
  
                   bestMove = emptyCells.get(a);
                }
                else if (score > bestScore) {
                    bestScore = score;
                    bestMove = cell;
                }
                i++;
            }
            
            return bestMove;
//        }
         

//        // Medium Difficulty: Slightly Optimal Move
//         if (difficulty.equals("medium")) {
//            for (int i = 0; i < emptyCells.size(); i++) {
//             
//            int[] cell = emptyCells.get(i);
//            int row = cell[0];
//            int col = cell[1];
//
//            revTTT[row][col].text = PLAYER;
//            if (checkLose(revTTT, PLAYER) && !isBoardFull(revTTT)) {

//             emptyCells.remove(i); // Remove the cell from the list
//                }
//           
//         revTTT[row][col].text = "";
//        }
//         Random random = new Random();
//         int randomIndex = random.nextInt(emptyCells.size());
//         int[] randomCell = emptyCells.get(randomIndex); // Remove and store the randomly selected cell
//         return randomCell;
//         }
//
//
//        // Hard Difficulty: Mostly Optimal Move
//        if (difficulty.equals("easy")) {
//             Random random = new Random();
//            return emptyCells.get(random.nextInt(emptyCells.size()));
//        }
//        int[] move = {-1,-1};
//        return move;
    }
    
    private boolean checkEngineLose(MyButton[][] revTTT, int row, int col){
        revTTT[row][col].text = OPPONENT;
        if (checkLose(revTTT, OPPONENT) && !isBoardFull(revTTT)) {
         revTTT[row][col].text = "";   
        return true;
    }
     revTTT[row][col].text = ""   ;
     return false;
    }
   
    private int minimax(MyButton[][] revTTT, int depth, boolean isMaximizing, int searchDepth) {
    if (checkLose(revTTT, OPPONENT) || checkLose(revTTT, PLAYER) || isBoardFull(revTTT) || depth == searchDepth) {
     return evaluate(revTTT,depth);
 }

    if (isMaximizing) {
        int bestScore = Integer.MIN_VALUE;

      

        for (int[] cell : getEmptyCells(revTTT)) {
            int row = cell[0];
            int col = cell[1];
            revTTT[row][col].text = PLAYER;
            int score = minimax(revTTT, depth + 1, false, searchDepth);
            revTTT[row][col].text = "";

            bestScore = Math.max(score, bestScore);
        }

        return bestScore;
    } else {
        int bestScore = Integer.MAX_VALUE;

        for (int[] cell : getEmptyCells(revTTT)) {
            int row = cell[0];
            int col = cell[1];
            revTTT[row][col].text = OPPONENT;
            int score = minimax(revTTT, depth + 1, true, searchDepth);
            revTTT[row][col].text = "";

            bestScore = Math.min(score, bestScore);
        }

        return bestScore;
    }
}
    private int evaluate(MyButton[][] revTTT, int depth) {

    Random random = new Random();
    int randomInt = random.nextInt(depth + 1) ;
    if(maxDepth - randomInt == 0 && maxDepth != 10){
        return random.nextInt(11) - 5;
    }
    else{
          if (checkLose(revTTT, OPPONENT)) {
        return depth - 5;
    } else if (checkLose(revTTT, PLAYER)) {
        return depth - 9;
    } else if (isBoardFull(revTTT)) {  
        return 0;
      }  
    }
 return 0;        
    
}
 
    

    private List<int[]> getEmptyCells(MyButton[][] revTTT) {
        // return a list of indices representing empty cells on the board
        // ...
        List<int[]> emptyCells = new ArrayList<>();
                for (int i = 0; i < revTTT.length; i++) {
            for (int j = 0; j < revTTT[i].length; j++) {
               if(revTTT[i][j].clicked == false && revTTT[i][j].text.equals("")){
                 int[] board = {i,j};
                 emptyCells.add(board);
               }
            }
        }
        return emptyCells;
    }

    private boolean checkLose(MyButton[][] revTTT,String player) {
         // Check rows
    for (int row = 0; row < 3; row++) {
        if (revTTT[row][0].text.equals(player) &&
                revTTT[row][1].text.equals(player) &&
                revTTT[row][2].text.equals(player)) {
            return true;
        }
    }

    // Check columns
    for (int col = 0; col < 3; col++) {
        if (revTTT[0][col].text.equals(player) &&
                revTTT[1][col].text.equals(player) &&
                revTTT[2][col].text.equals(player)) {
            return true;
        }
    }

    // Check diagonals
    if (revTTT[0][0].text.equals(player) &&
            revTTT[1][1].text.equals(player) &&
            revTTT[2][2].text.equals(player)) {
        return true;
    }

    if (revTTT[0][2].text.equals(player) &&
            revTTT[1][1].text.equals(player) &&
            revTTT[2][0].text.equals(player)) {
        return true;
    }

    // No loser
    return false;
  }
private boolean isBoardFull(MyButton[][] revTTT) {

         List<int[]> empty = getEmptyCells(revTTT);
        return empty.isEmpty();
}

    private int checkBestMove(MyButton[][] revTTT, List<int[]> cell, int a, int score) {
      
        int[] i = cell.get(a);
        int[] j = cell.get(a-1);
        if(score > 0){

         revTTT[i[0]][i[1]].text = PLAYER;
        if(checkLose(revTTT, PLAYER)){
            revTTT[i[0]][i[1]].text = ""; 
            return a-1;
          }
        revTTT[j[0]][j[1]].text = PLAYER; 
        if(checkLose(revTTT, PLAYER)){
            revTTT[j[0]][j[1]].text = "";
            return a;
          }
        }
        else if(score < 0){

         revTTT[i[0]][i[1]].text = OPPONENT; 
        if(checkLose(revTTT, OPPONENT)){

            revTTT[i[0]][i[1]].text = ""; 
            return a-1;
          }
        revTTT[j[0]][j[1]].text = OPPONENT; 
        if(checkLose(revTTT, OPPONENT)){

            revTTT[j[0]][j[1]].text = "";
            return a;
          }
        }
    
        return a;
        
}   
}

