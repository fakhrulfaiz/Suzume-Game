/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.station;

/**
 *
 * @author User
 */
import ds.assignment.GamePanel;
import ds.assignment.Main;
import ds.assignment.TicTacToePanel;
import ds.ui.MyButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToe5x5Engine {
    private static final String LABEL_X = "X"; //player
    private static final String LABEL_O = "O"; //engine
    private String PLAYER ;
    private String OPPONENT ;
     MyButton[][] ttt5x5;
     GamePanel gp;
     TicTacToePanel tttp;
     int maxDepth;
    TicTacToe5x5Engine(GamePanel gp) {
       this.gp = gp;
    }

    TicTacToe5x5Engine(TicTacToePanel tttp) {
     this.tttp = tttp;
    }
    public int[] makeMove(MyButton[][] ttt5x5, String difficulty) {
        if(Main.mode.equals("simulation")){
           if(!gp.ttt5x5.xTurn){
             OPPONENT = LABEL_O;
             PLAYER = LABEL_X;
           }
           else{
            OPPONENT = LABEL_X;
             PLAYER = LABEL_O;   
           }
        }else{
           if(!tttp.ttt5x5.xTurn){
             OPPONENT = LABEL_O;
             PLAYER = LABEL_X;
           }
           else{
            OPPONENT = LABEL_X;
             PLAYER = LABEL_O;   
           } 
        }
        
        // Hard Difficulty: Random Move

      maxDepth = 20;
      
     if (difficulty.equals("hard")) {
        List<int[]> emptyCells = getEmptyCells(ttt5x5);
        List<int[]> adjacentEmptyCells = getAdjacentEmptyCells(ttt5x5);
        if(emptyCells.size() == 24 || emptyCells.size() == 25){
         
         
          if (!adjacentEmptyCells.isEmpty() ) {
             Random random = new Random();
        return adjacentEmptyCells.get(random.nextInt(adjacentEmptyCells.size())); 
          }
          else {
            
        Random random = new Random();
        return emptyCells.get(random.nextInt(emptyCells.size()));
             }
        }
        
        int[] bestMove = {-1, -1};
        int bestScore = Integer.MIN_VALUE;
       int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int score = Integer.MIN_VALUE;
        int i = 0;
       
        for (int[] cell : adjacentEmptyCells) {
          
              int row = cell[0];
              int col = cell[1];
            if(checkEngineWin(ttt5x5, row,  col)){
                return cell;
            }
            ttt5x5[row][col].text = PLAYER;
             score = minimax(ttt5x5,adjacentEmptyCells, 0, alpha, beta, false);
            // score = minimax(ttt5x5, adjacentEmptyCells, 0, false);
            ttt5x5[row][col].text = "";

            if(score == 100){
              
//                ttt5x5[row][col].text = PLAYER;
//                if (checkWin(ttt5x5, PLAYER)) {
//                 ttt5x5[row][col].text = "";   
//                 int a = checkBestMove(ttt5x5,emptyCells,i ,PLAYER);                      
//                bestMove[0] = emptyCells.get(a)[0];
//                bestMove[1] = emptyCells.get(a)[1]; 
//                  }
//                ttt5x5[row][col].text = OPPONENT;
//                if (checkWin(ttt5x5, OPPONENT)) {
//                 ttt5x5[row][col].text = "";   
//                int a = checkBestMove(ttt5x5,emptyCells,i ,OPPONENT);                      
//                bestMove[0] = emptyCells.get(a)[0];
//                bestMove[1] = emptyCells.get(a)[1];   
//               
//                  }
                bestScore = score;
                bestMove[0] = row;
                bestMove[1] = col;
                
           
               
            }
            else if (score >= bestScore) {
                bestScore = score;
                bestMove[0] = row;
                bestMove[1] = col;
            } 
           
             
            
//            alpha = Math.max(alpha, bestScore);
//            if (beta <= alpha) {              
//                break; // Beta cutoff
//            }   
            
          i++;  
        }

        if (score == bestScore) {
            Random random = new Random();
            return emptyCells.get(random.nextInt(emptyCells.size()));
        }

        return bestMove;
    }

        // Medium Difficulty: Slightly Optimal Move
         List<int[]> emptyCells =  getEmptyCells(ttt5x5); 
        if (difficulty.equals("medium")) {
            for (int[] cell : emptyCells) {
                int row = cell[0];
                int col = cell[1];
                
               
                ttt5x5[row][col].text = OPPONENT;
                if (checkWin(ttt5x5, OPPONENT)) {
                    ttt5x5[row][col].text = "";                    
                    return cell;
                  }
                ttt5x5[row][col].text = PLAYER;
                if (checkWin(ttt5x5, PLAYER)) {
                    ttt5x5[row][col].text = "";                    
                    return cell;
                  }
                
             
                
                
                ttt5x5[row][col].text = "";
            }
            Random random = new Random();
            return emptyCells.get(random.nextInt(emptyCells.size()));
        }

        // Easy Difficulty 
        if (difficulty.equals("easy")) {
         List<int[]> adjacentEmptyCells = getAdjacentEmptyCells(ttt5x5);
    if (!adjacentEmptyCells.isEmpty()) {
        for(int [] cell : adjacentEmptyCells){
                int row = cell[0];
                int col = cell[1];
                
                ttt5x5[row][col].text = OPPONENT;
                if (checkWin(ttt5x5, OPPONENT)) {
                    ttt5x5[row][col].text = "";                    
                    return cell;
                  }
                ttt5x5[row][col].text = PLAYER;
                if (checkWin(ttt5x5, PLAYER)) {
                    ttt5x5[row][col].text = "";                    
                    return cell;
                  }
                
               
                ttt5x5[row][col].text = ""; 
        }
        Random random = new Random();
        return adjacentEmptyCells.get(random.nextInt(adjacentEmptyCells.size()));
    } else {
        Random random = new Random();
        return emptyCells.get(random.nextInt(emptyCells.size()));
    }
        }
//}

        int[] move = {-1,-1};
        return move;
    }
    
   private int minimax(MyButton[][] ttt5x5,List<int[]> adjacentEmptyCells, int depth, int alpha, int beta, boolean isMaximizing) {
    //private int minimax(MyButton[][] ttt5x5,List<int[]> adjacentEmptyCells ,int depth, boolean isMaximizing) {    
     if (depth == maxDepth || checkWin(ttt5x5, PLAYER) || checkWin(ttt5x5, OPPONENT) || isBoardFull(ttt5x5)) {
        // Return evaluation score or utility value at leaf nodes
        return evaluate(ttt5x5,depth);
    }

    if (isMaximizing) {
        int bestScore = Integer.MIN_VALUE;

      //  List<int[]> emptyCells = getEmptyCells(ttt5x5);
  //      List<int[]> adjacentEmptyCells = getAdjacentEmptyCells(ttt5x5);
        for (int[] cell : adjacentEmptyCells) {
            int row = cell[0];
            int col = cell[1];
           if(ttt5x5[row][col].text.equals("")){
                     ttt5x5[row][col].text = PLAYER;
           int score = minimax(ttt5x5,adjacentEmptyCells, depth + 1, alpha, beta, false);
           // int score = minimax(ttt5x5,adjacentEmptyCells, depth + 1, false);
            ttt5x5[row][col].text = "";

            bestScore = Math.max(score, bestScore);  
           }
               
            alpha = Math.max(alpha, bestScore);

            if (beta <= alpha) {
                break; // Beta cutoff
            }
        }

        return bestScore;
    } else {
        int bestScore = Integer.MAX_VALUE;

//        List<int[]> emptyCells = getEmptyCells(ttt5x5);
//         List<int[]> adjacentEmptyCells = getAdjacentEmptyCells(ttt5x5);
        for (int[] cell : adjacentEmptyCells) {
            int row = cell[0];
            int col = cell[1];
            if(ttt5x5[row][col].text.equals("")){
                   ttt5x5[row][col].text = OPPONENT;
           int score = minimax(ttt5x5,adjacentEmptyCells, depth + 1, alpha, beta, true);
          //  int score = minimax(ttt5x5, adjacentEmptyCells,depth + 1, true);
            ttt5x5[row][col].text = "";

            bestScore = Math.min(score, bestScore);   
            }
         
           
            
           
            beta = Math.min(beta, bestScore);

            if (beta <= alpha) {
                break; // Alpha cutoff
            }
        }

        return bestScore;
    }
}
   private boolean checkEngineWin(MyButton[][] ttt5x5,int row, int col){
       
       ttt5x5[row][col].text = OPPONENT;
       if(checkWin(ttt5x5, OPPONENT)){
        ttt5x5[row][col].text = "";
        return true;
       }
       ttt5x5[row][col].text = "";
       return false;
   }
private int evaluate(MyButton[][] ttt5x5, int depth) {
    // Assuming checkWin method returns true, determine the winner
    
     if (checkWin(ttt5x5, PLAYER)) {

        return 20 -depth; // Player wins, return a high positive score
    } else if (checkWin(ttt5x5, OPPONENT)) {
  
        return depth - 20; // Opponent wins, return a high negative score
    } else if(isBoardFull(ttt5x5)){
        return 0; // It's a draw, return a neutral score
    }else{
        return -5;
    }
    
}

        // Helper method to get adjacent empty cells
private List<int[]> getAdjacentEmptyCells(MyButton[][] ttt5x5) {
    List<int[]> adjacentEmptyCells = new ArrayList<>();
    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    for (int i = 0; i < ttt5x5.length; i++) {
        for (int j = 0; j < ttt5x5[i].length; j++) {
            if (!ttt5x5[i][j].clicked && ttt5x5[i][j].text.equals("")) {
                for (int k = 0; k < 8; k++) {
                    int newRow = i + dx[k];
                    int newCol = j + dy[k];
                    if (isValidCell(ttt5x5, newRow, newCol) && !ttt5x5[newRow][newCol].text.equals("")) {
                        int[] cell = {i, j};
                        adjacentEmptyCells.add(cell);
                        break;
                    }
                }
            }
        }
    }
    return adjacentEmptyCells;
}


// Helper method to check if cell indices are within the valid range
private boolean isValidCell(MyButton[][] ttt5x5, int row, int col) {
    return row >= 0 && row < ttt5x5.length && col >= 0 && col < ttt5x5[row].length;
}
        private List<int[]> getEmptyCells(MyButton[][] ttt5x5) {
        // return a list of indices representing empty cells on the board
        // ...
        List<int[]> emptyCell = new ArrayList<>();
                for (int i = 0; i < ttt5x5.length; i++) {
            for (int j = 0; j < ttt5x5[i].length; j++) {
               if(ttt5x5[i][j].clicked == false && ttt5x5[i][j].text.equals("")){
                 int[] board = {i,j};
                 emptyCell.add(board);
               }
            }
        }
        return emptyCell;
    }

    public boolean checkWin(MyButton[][] ttt5x5,String player) {
    // check rows
    for (int row = 0; row < 5; row++) {
         for (int col = 0; col < 3; col++) {
        if (ttt5x5[row][col].text.equals(player) &&
                ttt5x5[row][col+1].text.equals(player) &&
                ttt5x5[row][col+2].text.equals(player)) {
            return true;
        }
         }
    }
    
    // check columns
    
     for (int col = 0; col < 5; col++) {
         for (int row = 0; row < 3; row++) {
        if (ttt5x5[row][col].text.equals(player) &&
                ttt5x5[row+1][col].text.equals(player) &&
                ttt5x5[row+2][col].text.equals(player)) {
            return true;
        }
      }
     }
    
    // check diagonals
     for (int row = 0; row < 3; row++) {
         for (int col = 0; col < 3; col++) {
        if (ttt5x5[row][col].text.equals(player) &&
                ttt5x5[row+1][col+1].text.equals(player) &&
                ttt5x5[row+2][col+2].text.equals(player)) {
            return true;
         }
       }
    }
     for (int row = 0; row < 3; row++) {
         for (int col = 4; col > 1; col--) {
        if (ttt5x5[row][col].text.equals(player) &&
                ttt5x5[row+1][col-1].text.equals(player) &&
                ttt5x5[row+2][col-2].text.equals(player)) {
            return true;
         }
       }
    }
    for (int row = 4; row < 1; row--) {
         for (int col = 0; col < 3; col++) {
        if (ttt5x5[row][col].text.equals(player) &&
                ttt5x5[row-1][col+1].text.equals(player) &&
                ttt5x5[row-2][col+2].text.equals(player)) {
            return true;
         }
       }
    } 
       for (int row = 4; row < 1; row--) {
         for (int col = 4; col > 1; col--) {
        if (ttt5x5[row][col].text.equals(player) &&
                ttt5x5[row-1][col-1].text.equals(player) &&
                ttt5x5[row-2][col-2].text.equals(player)) {
            return true;
         }
       }
    }

    
    // no winner
    return false;
}

private boolean isBoardFull(MyButton[][] ttt5x5) {
     
        for (int i = 0; i < ttt5x5.length; i++) {
        for (int j = 0; j < ttt5x5[0].length; j++) {
           if( !ttt5x5[i][j].text.equals("")){
           return false;
           }
        }
    }

        return true;
}
 private int checkBestMove(MyButton[][] ttt5x5, List<int[]> cell, int a, String player) {
      
        int[] i = cell.get(a);
        int[] j = cell.get(a-1);
        if(player.equals(OPPONENT)){

         ttt5x5[i[0]][i[1]].text = OPPONENT;
        if(checkWin(ttt5x5, OPPONENT)){
            ttt5x5[i[0]][i[1]].text = ""; 
            return a-1;
          }
        ttt5x5[j[0]][j[1]].text = OPPONENT; 
        if(checkWin(ttt5x5, OPPONENT)){
            ttt5x5[j[0]][j[1]].text = "";
            return a;
          }
        }
        else if(player.equals(PLAYER)){

         ttt5x5[i[0]][i[1]].text = PLAYER; 
        if(checkWin(ttt5x5, PLAYER)){

            ttt5x5[i[0]][i[1]].text = ""; 
            return a-1;
          }
        ttt5x5[j[0]][j[1]].text = PLAYER; 
        if(checkWin(ttt5x5, PLAYER)){

            ttt5x5[j[0]][j[1]].text = "";
            return a;
          }
        }
    
        return a;
        
}
}
