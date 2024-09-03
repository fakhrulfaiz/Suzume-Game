/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author User
 */
public class GraphAlgoritm {
      //  List<List<int[]>> paths;
      
    public GraphAlgoritm(){
    //    paths = new ArrayList<>();
       
    }
        
    
   
// Perform depth-first search on a given map to count the number of possible paths
public int dfs(int[][] map, int target, int x, int y, int stationsVisited) {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
        // Out of bounds, return 0
        return 0;
}
        if (map[x][y] == 1 || (map[x][y] == 2 && stationsVisited > 3)) {
        // Hit an obstacle or already visited this station three times, return 0
        return 0;
}
        if (map[x][y] == target) {
// Reached the destination, return 1 if visited exactly three stations, else 0
return stationsVisited == 3 ? 1 : 0;
}
// Mark current pixel as visited
int temp = map[x][y];
map[x][y] = 1;
// Recursively search adjacent pixels
int pathCount = dfs(map, target, x + 1, y, temp == 2 ? stationsVisited + 1 : stationsVisited) +
                dfs(map, target, x - 1, y, temp == 2 ? stationsVisited + 1 : stationsVisited) +
                dfs(map, target, x, y + 1, temp == 2 ? stationsVisited + 1 : stationsVisited) +
                dfs(map, target, x, y - 1, temp == 2 ? stationsVisited + 1 : stationsVisited);
// Mark current pixel as unvisited
map[x][y] = temp;
return pathCount;
}
public int dfs2(int[][] map, int target, int x, int y, int stationsVisited) {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
        // Out of bounds, return 0
        return 0;
}
        if (map[x][y] == 1 || (map[x][y] == 2 && stationsVisited > 4)) {
        // Hit an obstacle or already visited this station four times, return 0
        return 0;
}
        if (map[x][y] == target) {
// Reached the destination, return 1 if visited exactly three stations, else 0
return stationsVisited == 4 ? 1 : 0;
}
// Mark current pixel as visited
int temp = map[x][y];
map[x][y] = 1;
// Recursively search adjacent pixels
int pathCount = dfs(map, target, x + 1, y, temp == 2 ? stationsVisited + 1 : stationsVisited) +
                dfs(map, target, x - 1, y, temp == 2 ? stationsVisited + 1 : stationsVisited) +
                dfs(map, target, x, y + 1, temp == 2 ? stationsVisited + 1 : stationsVisited) +
                dfs(map, target, x, y - 1, temp == 2 ? stationsVisited + 1 : stationsVisited);
// Mark current pixel as unvisited
map[x][y] = temp;
return pathCount;
}

 
public List<List<int[]>> bfs(int[][] map, int target, int startRow, int startCol) {
    

    int[][] visited = new int[map.length][map[0].length];
    Queue<List<int[]>> queue = new LinkedList<>();
    List<int[]> path = new ArrayList<>();
    path.add(new int[]{startRow, startCol});
    queue.offer(path);
    visited[startRow][startCol] = 1;

    List<List<int[]>> paths = new ArrayList<>();

    while (!queue.isEmpty()) {
        List<int[]> currentPath = queue.poll();
        int[] currentPos = currentPath.get(currentPath.size() - 1);
        int row = currentPos[0];
        int col = currentPos[1];

        if (map[row][col] == target) {
            // Reached the destination, add current path to list of paths
            paths.add(currentPath);
            continue; // continue searching for more paths
        }

        // Search adjacent pixels
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (nextRow >= 0 && nextRow < map.length && nextCol >= 0 && nextCol < map[0].length &&
                    visited[nextRow][nextCol] == 0 && map[nextRow][nextCol] != 1) {
                // Pixel is within bounds, not visited, and not an obstacle
                List<int[]> newPath = new ArrayList<>(currentPath);
                newPath.add(new int[]{nextRow, nextCol});
                queue.offer(newPath);
                visited[nextRow][nextCol] = 1;
            }
        }
    }

    return paths;
}

public List<List<int[]>> bfs2(int[][] map, int target, int startRow, int startCol, int stationVisited) {
    
    List<List<int[]>> result = new ArrayList<>();
  // int[][] visited = new int[map.length][map[0].length];
   
    Queue<List<int[]>> queue = new LinkedList<>();
    int pathSize = Integer.MAX_VALUE;
     int[] start = {startRow, startCol};
    queue.offer(Arrays.asList(start));


    List<List<int[]>> paths = new ArrayList<>();

    while (!queue.isEmpty()) {
        List<int[]> path = queue.poll();
        int[] currentPos = path.get(path.size() - 1);
        int row = currentPos[0];
        int col = currentPos[1];

        if (map[row][col] == target ) {
            
            if(path.size() <= pathSize){
            // Reached the destination, add current path to list of paths
            if(stationVisited == 4){        
               result.add(path);
               pathSize = path.size();
            }
            else
                
                continue; // continue searching for more paths
            }
        }
        else{
        
        // Search adjacent pixels
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            int[] neighbors = {nextRow,nextCol};
            if (nextRow >= 0 && nextRow < map.length && nextCol >= 0 && nextCol < map[0].length &&
                    map[nextRow][nextCol] != 1 && stationVisited <= 4) {
                // Pixel is within bounds, not visited, and not an obstacle
                if(map[nextRow][nextCol] == 2){
                    stationVisited++;
                }
                List<int[]> newPath = new ArrayList<>(path);
                newPath.add(neighbors);
                queue.offer(newPath);
             
            }
        }
        }
    }

    return result;
}

}