/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

/**
 *
 * @author User
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.imageio.ImageIO;

public class MapFormation {
     private final int[][] Directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public MapFormation(){
        
    }

    public int[][] getRealMap() {
        //image file
        String[] imagePaths = {"image 1.png", "image 2.png", "image 3.png", "image 4.png"};

        int[][] combinedGrid = null;
        int combinedHeight ;
        int combinedWidth;

        for (String imagePath : imagePaths) {
            try {
                // Read image from file
                String imageName = "/res/map/" + imagePath;
     //           File imageFile = new File(imageName);
                BufferedImage image = ImageIO.read(getClass().getResource(imageName));
                
                // Get the image dimensions
                int width = image.getWidth();
                int height = image.getHeight();

                // Extract grayscale value from RGB pixel
                System.out.println("Image: " + imagePath);
                int[][] grid = new int[height][width];
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int pixel = image.getRGB(x, y);
                        int grayscaleValue = (pixel >> 16) & 0xFF;
                        int convertedValue = grayscaleValue / 64; // Convert grayscale value to range 0-3
                        System.out.print(convertedValue + " ");
                        grid[y][x] = grayscaleValue / 64; // Image grid to combine
                    }
                    System.out.println(); // Print a new line after each row
                }
                int pathCount = countPaths(grid, height, width);
                System.out.println("Total Paths: " + pathCount);
                System.out.println(); // Add an extra new line for separation between images

                // Combine the grids into their respective sections
                if (combinedGrid == null) {
                    combinedHeight = height * 2; //row multiply 2
                    combinedWidth = width * 2; //column multiply 2
                    combinedGrid = new int[combinedHeight][combinedWidth];
                }
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (pathCount == 16) {
                            combinedGrid[i][j] = grid[i][j];                    // Top left section
                            combinedGrid[19][9] = 1; //Change 3 to 1
                        }
                        if (pathCount == 41) {
                            combinedGrid[i][j + width] = grid[i][j];            // Top right section
                            combinedGrid[19][19] = 1; //Change 3 to 1
                        }
                        if (pathCount == 38) {
                            combinedGrid[i + height][j] = grid[i][j];           // Bottom left section
                            combinedGrid[39][9] = 1; //Change 3 to 1
                        }
                        if (pathCount == 27) {
                            combinedGrid[i + height][j + width] = grid[i][j];   // Bottom right section
                        }
                    }
                }
            
            } catch (Exception e) {
                System.out.println("Error reading image: " + e.getMessage());
            }
        }
              
        // Print the combined image
        System.out.println("Combined Image:");
        for (int i = 0; i < combinedGrid.length; i++) {
            for (int j = 0; j < combinedGrid[0].length; j++) {
                System.out.print(combinedGrid[i][j] + " ");
            }
            System.out.println();
        }
        
           int allPathCount = countPaths(combinedGrid,combinedGrid.length, combinedGrid[0].length);  
                System.out.println("Number of all possible path [exactly four stations]: " + allPathCount);
                AnswerDecryption decrypt = new AnswerDecryption();
                System.out.println("Answer Decryption : " + decrypt.getDecryptedNumber());
          return combinedGrid;

    }

    private int countPaths(int[][] grid, int height, int width) {
       
        
        if(width == 20 && height == 40){
           boolean[][] visited = new boolean[height][width]; 
           return dfs(grid, 0, 0, 0, visited);
        }
        else{
          // int[][] visited = new int[height][width]; 
          boolean[][] visited = new boolean[height][width];
           return bfs(grid, visited);
        }    
        
    }
    
  
   
      public int bfs(int[][] grid,boolean[][] visited) {         
          
        Queue<int[]> queue = new LinkedList<>();        
        visited[0][0] = true;
        queue.offer(new int[]{0, 0, 0, 0});

        List<boolean[][]> visitedList = new ArrayList<>();
        visitedList.add(visited);

        int pathCount = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            int stationVisited = curr[2];
            int visitedIndex = curr[3];
            boolean[][] currVisited = visitedList.get(visitedIndex);

            if (grid[row][col] == 3 && stationVisited == 3) {
                pathCount++;
                continue;
            }

            for (int[] direction : Directions) {
                int newCol = col + direction[0];
                int newRow = row + direction[1];

                if (newCol < 0 || newCol >= grid[0].length || newRow < 0 || newRow >= grid.length
                        || grid[newRow][newCol] == 1 || currVisited[newRow][newCol])
                    continue;

                int newVisitedStations = stationVisited;
                if (grid[newRow][newCol] == 2 && stationVisited < 3) {
                    newVisitedStations++;
                } else if (grid[newRow][newCol] == 2) {
                    continue; 
                }

                if (grid[newRow][newCol] == 3 && newVisitedStations < 3)
                    continue;

                boolean[][] newVisited = new boolean[grid.length][grid[0].length];
                for (int i = 0; i < grid.length; i++) {
                    System.arraycopy(currVisited[i], 0, newVisited[i], 0, grid[0].length);
                }
                newVisited[newRow][newCol] = true;

                visitedList.add(newVisited);
                int newVisitedIndex = visitedList.size() - 1;
                queue.offer(new int[]{newRow, newCol, newVisitedStations, newVisitedIndex});
            }
        }
        return pathCount;
    }
       
     
     public int dfs(int[][] combinedGrid, int row, int col, int stationVisited, boolean[][] visited) {
        if (col < 0 || col >= combinedGrid[0].length || row < 0 || row >= combinedGrid.length
                || combinedGrid[row][col] == 1 || stationVisited > 4)
            return 0;

        if (visited[row][col])
            return 0;

        if (combinedGrid[row][col] == 2)
            stationVisited++;

        if (stationVisited == 4 && combinedGrid[row][col] == 3)
            return 1;

        visited[row][col] = true;
        int pathCount = 0;

        for (int[] direction : Directions) {
            int newCol = col + direction[0];
            int newRow = row + direction[1];
            pathCount += dfs(combinedGrid, newRow, newCol, stationVisited, visited);
        }

        visited[row][col] = false;

        if (combinedGrid[row][col] == 2)
            stationVisited--;
        return pathCount;
    }

}
