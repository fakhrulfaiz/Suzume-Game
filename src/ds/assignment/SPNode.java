/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;
import java.util.HashSet;
public class SPNode {
    int row;
    int col;
    int dist;
    HashSet<String> visited;
    SPNode parent;

    SPNode(int row, int col, int dist, HashSet<String> visited, SPNode parent) {
        this.row = row;
        this.col = col;
        this.dist = dist;
        this.visited = visited;
        this.parent = parent;
    }
}
