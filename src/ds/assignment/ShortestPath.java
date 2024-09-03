/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;
import java.util.*;
public class ShortestPath {
    private static final int[][] movement = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final String[] direction = {"Up", "Down", "Left", "Right"};

    public static List<List<String>> FindShortestPaths(int[][] map) {
        List<List<String>> shortestPaths = new ArrayList<>();
        Queue<SPNode> queue = new LinkedList<>();
        HashSet<String> FirstPosition = new HashSet<>();
        FirstPosition.add("0,0");
        queue.offer(new SPNode(0, 0, 0, FirstPosition, null));

        int ShortestDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            SPNode curr = queue.poll();

            if (curr.dist > ShortestDist) {
                continue;
            }

            if (map[curr.row][curr.col] == 3) {
                if (curr.dist < ShortestDist) {
                    shortestPaths.clear();
                    ShortestDist = curr.dist;
                }

                List<String> path = new ArrayList<>();
                SPNode node = curr;
                while (node.parent != null) {

                    int RowDirection = node.row - node.parent.row;
                    int ColDirection = node.col - node.parent.col;
                    for (int i = 0; i < movement.length; i++) {
                        if (movement[i][0] == RowDirection && movement[i][1] == ColDirection) {
                            path.add(direction[i]);
                            break;
                        }
                    }
                    node = node.parent;
                }
                Collections.reverse(path);
                shortestPaths.add(path);
                continue;
            }

            for (int i = 0; i < movement.length; i++) {
                int newRow = curr.row + movement[i][0];
                int newCol = curr.col + movement[i][1];
                String key = newRow + "," + newCol;

                if (isValid(map, newRow, newCol) && !curr.visited.contains(key)) {
                    HashSet<String> newVisited = new HashSet<>(curr.visited);
                    newVisited.add(key);
                    queue.offer(new SPNode(newRow, newCol, curr.dist + 1, newVisited, curr));
                }
            }
        }
        return shortestPaths;
    }
    
   
    private static boolean isValid(int[][] map, int height, int width) {
        if (height < 0 || height >= map.length || width < 0 || width >= map[0].length) {
            return false;
        }
        int value = map[height][width];
        return value != 1;
    }
     public static List<List<int[]>> getShortestPathCoordinates(int[][] map) {
        List<List<String>> shortestPaths = FindShortestPaths(map);
        List<List<int[]>> coordinates = new ArrayList<>();
        for (List<String> path : shortestPaths) {
            List<int[]> pathCoordinates = new ArrayList<>();
            int row = 0, col = 0;
            pathCoordinates.add(new int[]{row, col});
            for (String direction : path) {
                if (direction.equals("Up")) {
                    row--;
                } else if (direction.equals("Down")) {
                    row++;
                } else if (direction.equals("Left")) {
                    col--;
                } else if (direction.equals("Right")) {
                    col++;
                }
                pathCoordinates.add(new int[]{row, col});
            }
            coordinates.add(pathCoordinates);
        }
        return coordinates;
       
    }
     public static List<int[]> getRandomShortestPath(int[][] map) {
            List<List<int[]>> coordinates = getShortestPathCoordinates(map);           
             Random random = new Random();
          return coordinates.get(random.nextInt(coordinates.size()));
    } 
}



