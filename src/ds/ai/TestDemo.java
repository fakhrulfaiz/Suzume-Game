/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.ai;

import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class TestDemo {
  
    public static void main(String[] args) {
         JFrame window = new JFrame();
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setResizable(false);
         window.add(new DemoPanel());
         
         window.pack();
         window.setLocationRelativeTo(null);
         window.setVisible(true);
    }
}
