 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class PixelReading {
     public boolean up , down, left, right ;
     File inputFile1, inputFile2, inputFile3, inputFile4;       
      BufferedImage image1, image2, image3, image4;
     int height;
     int width;  
     int[][] map;

     GraphAlgoritm graph;

    
    public PixelReading(){
              
              readInputImage();
              graph = new GraphAlgoritm();

    }
    
    private void readInputImage(){
         inputFile1 = new File("C:\\Users\\User\\Desktop\\UM\\DS\\Assignment\\image 1.png");
              inputFile2 = new File("C:\\Users\\User\\Desktop\\UM\\DS\\Assignment\\image 2.png");
              inputFile3 = new File("C:\\Users\\User\\Desktop\\UM\\DS\\Assignment\\image 3.png");
              inputFile4 = new File("C:\\Users\\User\\Desktop\\UM\\DS\\Assignment\\image 4.png");
             
         try {
             image1 = ImageIO.read(inputFile1);
             image2 = ImageIO.read(inputFile2);
             image3 = ImageIO.read(inputFile3);
             image4 = ImageIO.read(inputFile4);
         } catch (IOException ex) {
             
         }
    }
    public int getHeight(){
        return 40;
    }
    public int getWidth(){
        return 20;
    }
     
   public List<List<int[]>> getPath() {
    map = getRealMap();
    List<List<int[]>> paths = graph.bfs(map, 3, 0, 0);
    
    for (List<int[]> path : paths) {
        for (int[] node : path) {
            System.out.print(Arrays.toString(node) + " ");
        }
        System.out.println();
    }
    
    System.out.println("Longest Path Length: " + paths.size());
    return paths;
}

    public List<int[]> getShortestPath() {
    List<List<int[]>> paths = getPath();
        System.out.println(paths.size());

 
    return paths.get(0);
}
//    public List<int[]> getLongestPath() {
//    List<List<int[]>> paths = getAllPossiblePaths();
//    List<int[]> longestPath = null;
//    int longestPathLength = Integer.MIN_VALUE;
//    
//    // Iterate over all paths and find the shortest one
//    for (List<int[]> path : paths) {
//        if (path.size() > longestPathLength) {
//            longestPath = path;
//            longestPathLength = path.size();
//            System.out.println("\n Longest" + paths.size());
//        }
//    }
//    
//    return longestPath;
//}
//       public List<List<int[]>> getAllPossiblePaths() {
//
//        map = getRealMap();     
//        System.out.println(Arrays.toString(map));
//       
//        graph.allPossiblePaths(map,3, 0, 0 );
//        return graph.paths;
//    
//    
//}

     public int[][] getRealMap(){

        int[][] map1 = getImage1();
        int[][] map2 = getImage2();
        int[][] map3 = getImage3();
        int[][] map4 = getImage4();
        
      // Perform depth-first search for each map piece
        int paths1Count = graph.dfs(map1, 3, 0, 0, 0);
        int paths2Count = graph.dfs(map2, 3, 0, 0, 0);
        int paths3Count = graph.dfs(map3, 3, 0, 0, 0);
        int paths4Count = graph.dfs(map4, 3, 0, 0, 0);
        
        System.out.println("Number of possible paths for map piece 1: " + paths1Count);
        System.out.println("Number of possible paths for map piece 2: " + paths2Count);
        System.out.println("Number of possible paths for map piece 3: " + paths3Count);
        System.out.println("Number of possible paths for map piece 4: " + paths4Count);

         // Combine the map pieces
    int[][] realMap = new int[map1.length + map2.length][map1[0].length + map3[0].length];
    for (int i = 0; i < map1.length; i++) {
        for (int j = 0; j < map1[0].length; j++) {
            if(map1[i][j] == 3){
                realMap[i][j] = 1;
            }
            else
                realMap[i][j] = map1[i][j];
        }
    }
    for (int i = 0; i < map2.length; i++) {
        for (int j = 0; j < map2[0].length; j++) {
             if(map1[i][j] == 3){
                realMap[i][map1[0].length + j] = 1;
            }
            else
                realMap[i][map1[0].length + j] = map2[i][j];
        }
    }
    for (int i = 0; i < map3.length; i++) {
        for (int j = 0; j < map3[0].length; j++) {
            if(map1[i][j] == 3){
                realMap[map1.length + i][j] = 1;
            }
            else
                realMap[map1.length + i][j] = map3[i][j];
        }
    }
    for (int i = 0; i < map4.length; i++) {
        for (int j = 0; j < map4[0].length; j++) {
            realMap[map1.length + i][map3[0].length + j] = map4[i][j];
        }
    }
    try{
            height = realMap.length; // number of rows
            width = realMap[0].length; // number of columns in the first row

     //Convert the pixel value to 0-3 and write to text file
            FileWriter fw = new FileWriter(inputFile1.getParent() + "\\map.txt");
            BufferedWriter bw = new BufferedWriter(fw);
           
             for (int i = 0; i < height; i++) {
                  for (int j = 0; j < width; j++) {
                    bw.write(realMap[i][j] + " ");
                }
                bw.newLine();
            }
            bw.close();
    }catch(IOException e){
        System.out.println("Error");  
    }
     int realPathsCount = graph.dfs2(realMap, 3, 0, 0, 0);
         System.out.println("All possible path for real map: " + realPathsCount);
    return realMap;
        
     } 
     public int[][] getImage1 (){
             int[][] pixels = null;
         try {
            
             // Get the dimensions of the image
             int height1 = image1.getHeight();
             int width1 = image1.getWidth();
             pixels = new int[height1][width1];
             
             
              // Convert the input image to grayscale by iterating over each pixel and setting its gray value
              for (int x = 0; x < width1; x++) {   
                for (int y = 0; y < height1; y++) {
                  // Get the color of the pixel at the current position
                     Color pixelColor = new Color(image1.getRGB(x, y));
                     int red = pixelColor.getRed();
                     int green = pixelColor.getGreen();
                     int blue = pixelColor.getBlue();
                     int grayValue = (red + green + blue) / 3;
 
                     pixels[y][x] = grayValue;
               }
                 }
              //Convert the pixel value to 0-3 and write to text file
            FileWriter fw = new FileWriter(inputFile1.getParent() + "\\image1.txt");
            BufferedWriter bw = new BufferedWriter(fw);
           
             for (int i = 0; i < height1; i++) {
                  for (int j = 0; j < width1; j++) {
                    pixels[i][j] = pixels[i][j] / 64;
                    bw.write(pixels[i][j] + " ");
                }
                bw.newLine();
            }
            bw.close();
     
         } catch (IOException ex) {  
        }
         
         
         return pixels;
    }
     public int[][] getImage2 (){
             int[][] pixels = null;
         try {
            
             // Get the dimensions of the image
             int height2 = image2.getHeight();
             int width2 = image2.getWidth();
             pixels = new int[height2][width2];
             
             
              // Convert the input image to grayscale by iterating over each pixel and setting its gray value
              for (int x = 0; x < width2; x++) {   
                for (int y = 0; y < height2; y++) {
                  // Get the color of the pixel at the current position
                     Color pixelColor = new Color(image2.getRGB(x, y));
                     int red = pixelColor.getRed();
                     int green = pixelColor.getGreen();
                     int blue = pixelColor.getBlue();
                     int grayValue = (red + green + blue) / 3;
 
                     pixels[y][x] = grayValue;
               }
                 }
              //Convert the pixel value to 0-3 and write to text file
            FileWriter fw = new FileWriter(inputFile1.getParent() + "\\image2.txt");
            BufferedWriter bw = new BufferedWriter(fw);
           
             for (int i = 0; i < height2; i++) {
                  for (int j = 0; j < width2; j++) {
                    pixels[i][j] = pixels[i][j] / 64;
                    bw.write(pixels[i][j] + " ");
                }
                bw.newLine();
            }
            bw.close();
     
         } catch (IOException ex) {  
        }
         
         
         return pixels;
    }       
     public int[][] getImage3 (){
             int[][] pixels = null;
         try {
            
             // Get the dimensions of the image
             int height3 = image3.getHeight();
             int width3 = image3.getWidth();
             pixels = new int[height3][width3];
             
             
              // Convert the input image to grayscale by iterating over each pixel and setting its gray value
              for (int x = 0; x < width3; x++) {   
                for (int y = 0; y < height3; y++) {
                  // Get the color of the pixel at the current position
                     Color pixelColor = new Color(image3.getRGB(x, y));
                     int red = pixelColor.getRed();
                     int green = pixelColor.getGreen();
                     int blue = pixelColor.getBlue();
                     int grayValue = (red + green + blue) / 3;
 
                     pixels[y][x] = grayValue;
               }
                 }
              //Convert the pixel value to 0-3 and write to text file
            FileWriter fw = new FileWriter(inputFile1.getParent() + "\\image3.txt");
            BufferedWriter bw = new BufferedWriter(fw);
           
             for (int i = 0; i < height3; i++) {
                  for (int j = 0; j < width3; j++) {
                    pixels[i][j] = pixels[i][j] / 64;
                    bw.write(pixels[i][j] + " ");
                }
                bw.newLine();
            }
            bw.close();
     
         } catch (IOException ex) {  
        }
         
         
         return pixels;
    } 
     public int[][] getImage4 (){
             int[][] pixels ;
             // Get the dimensions of the image
             int height4 = image4.getHeight();
             int width4 = image4.getWidth();
             pixels = new int[height4][width4];
             // Convert the input image to grayscale by iterating over each pixel and setting its gray value
             for (int x = 0; x < width4; x++) {
                 for (int y = 0; y < height4; y++) {
                     // Get the color of the pixel at the current position
                     Color pixelColor = new Color(image4.getRGB(x, y));
                     int red = pixelColor.getRed();
                     int green = pixelColor.getGreen();
                     int blue = pixelColor.getBlue();
                     int grayValue = (red + green + blue) / 3;
 
                     pixels[y][x] = grayValue;
                 }
             }
             //Convert the pixel value to 0-3 and write to text file
//            FileWriter fw = new FileWriter(inputFile1.getParent() + "\\image3.txt");
//            BufferedWriter bw = new BufferedWriter(fw);
//
            for (int i = 0; i < height4; i++) {
                for (int j = 0; j < width4; j++) {
                pixels[i][j] = pixels[i][j] / 64;
        // bw.write(pixels[i][j] + " ");
    }
    //  bw.newLine();
}
//  bw.close();
         
         
         return pixels;
    }
     
     
}



