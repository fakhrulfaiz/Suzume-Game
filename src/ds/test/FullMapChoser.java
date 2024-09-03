/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds.test;
import java.util.List;
import java.util.Random;
public class FullMapChoser {

    public static void main(String[] args) {
        FirstSearch CompMap = new FirstSearch();
        CompMap.ImageReader();

        List<List<String>> ShortestPathsCollection = ShortestPath.FindShortestPaths(CompMap.FullMap());

        Random rnd = new Random();
        List<String> path = ShortestPathsCollection.get(rnd.nextInt(ShortestPathsCollection.size()));

        System.out.println("The chosen shortest path is: \n" + path);
        
        int[][] MatrixNew = AllAboutMatrix.GenNewMatrix(path);

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(MatrixNew[i][j] + " ");
            }
            System.out.println();
        }
    } 
}
