/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds.test;

import java.util.List;

public class Test {
     public static void main(String[] args) {
        FirstSearch Map = new FirstSearch();
        Map.ImageReader();
        int pathsMapPiece1 = Map.bfs(Map.getMapPiece1());
        int pathsMapPiece2 = Map.bfs(Map.getMapPiece2());
        int pathsMapPiece3 = Map.bfs(Map.getMapPiece3());
        int pathsMapPiece4 = Map.bfs(Map.getMapPiece4());
        
        System.out.println("Map Piece 1:");
        Map.DispMatrix(Map.getMapPiece1());
        System.out.println("Number of possible paths (pass through 3 stations):" + pathsMapPiece1);
        
        System.out.println("\nMap Piece 2:");
        Map.DispMatrix(Map.getMapPiece2());
        System.out.println("Number of possible paths (pass through 3 stations): " + pathsMapPiece2);
        
        System.out.println("\nMap Piece 3:");
        Map.DispMatrix(Map.getMapPiece3());
        System.out.println("Number of possible paths (pass through 3 stations): " + pathsMapPiece3);
         
        System.out.println("\nMap Piece 4:");
        Map.DispMatrix(Map.getMapPiece4());
        System.out.println("Number of possible paths (pass through 3 stations): " + pathsMapPiece4);
        
        System.out.println("\nCombined Map:");
        Map.DispMatrix(Map.FullMap());

        int NumFullMap = Map.countPaths(Map.FullMap());
        System.out.println("Number of possible paths (pass through 4 stations) : " + NumFullMap);


        FirstSearch CompMap = new FirstSearch();
        CompMap.ImageReader();

        List<List<String>> ShortestPathsCollection = ShortestPath.FindShortestPaths(CompMap.FullMap());

        System.out.println("Minimum steps required : " + ShortestPathsCollection.get(0).size());
        System.out.println("Total possible shortest paths : " + ShortestPathsCollection.size());
        System.out.println("All possible shortest paths direction:");
        int Pcount=1;
        for (List<String> path : ShortestPathsCollection) {
            System.out.print("Direction number "+Pcount+" is :");
            System.out.println(path);
            Pcount++;
        }
    }
}
