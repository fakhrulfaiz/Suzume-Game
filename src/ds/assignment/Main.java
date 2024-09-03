package ds.assignment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;

public class Main {
        public static String difficulty;
        static String username;
       public static String mode;
        public static String game;
       public static boolean isNewGame;
     public static void main(String[] args) {
        
        // Check if a difficulty level is provided as a command-line argument
        if (args.length > 0) {
            difficulty = args[0].toLowerCase();
            username = args[1];
            mode = args[2].toLowerCase();
            game = args[3].toLowerCase();
            System.out.println("Username : " + username);
            System.out.println("Difficulty : " + difficulty);
            System.out.println("Game : " + game);
        } else {
            username = "";
            game = "";
           // mode = "simulation";
            
            mode = "tictactoe";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose difficulty level: ");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print("Enter difficulty level (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    difficulty = "easy";
                    break;
                case 2:
                    difficulty = "medium";
                    break;
                case 3:
                    difficulty = "hard";
                    break;
                default:
                    difficulty = "medium"; // Default to medium difficulty
                    break;
            }
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("DS Assignment");
        
        
        GamePanel gamePanel = new GamePanel(difficulty, username);
        TicTacToePanel tttPanel = new TicTacToePanel(difficulty, username); 
        if(mode.equals("simulation")){
         
        frame.add(gamePanel); 
        }else if(mode.equalsIgnoreCase("tictactoe")){
     
           frame.add(tttPanel);
        }
        
       

        // Add a WindowListener to the JFrame to handle the window closing event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!username.equals("") &&  mode.equals("simulation")){
                    gamePanel.saveLoad.save();   
                }
                else if(!username.equals("") &&  mode.equals("tictactoe")){
                    tttPanel.saveLoad.save();
                }
          
                frame.dispose(); // Dispose the JFrame
                System.exit(0);
            }
        });
        
          frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.NORMAL); // Set the window state to normal (not minimized)
        frame.setVisible(true);
        if(mode.equals("simulation")){
         gamePanel.startThread();
        }else if(mode.equalsIgnoreCase("tictactoe")){
         tttPanel.startThread();
         
        }

        
    }


        
    }

    
    
     
