/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

/**
 *
 * @author User
 */
import ds.data.LeaderboardEntry;
import ds.data.LeaderboardDataStorage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MainMenu extends JFrame {
    private final static int WIDTH = 300;
    private final static int HEIGHT = 200;
    public String difficulty;
    String username;
    private JTextField usernameField , signupUsernameField;
    private JPasswordField passwordField, signupPasswordField;
    private JPasswordField confirmPasswordField;
    JFrame signUpFrame;
     JPanel mainMenuPanel;
     JButton signUpButton, loginButton, playerLeaderboardButton, confirmPasswordButton;
     JLabel usernameLabel, passwordLabel, confirmPasswordLabel;
    private Map<String, String> userDatabase; // For simplicity, we'll store the user data in a HashMap
   
    MainMenu() {
        setLocationRelativeTo(null);
        initializeUserDatabase();
        createComponents();
        
    }

    private void createComponents() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");

        mainMenuPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        usernameLabel = new JLabel(" Username:");
        usernameField = new JTextField();

        passwordLabel = new JLabel(" Password:");
        passwordField = new JPasswordField();



        signUpButton = new JButton(" Sign Up");
        signUpButton.addActionListener(new SignUpButtonPanel());
        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());

        playerLeaderboardButton = new JButton(" Leaderboard");
        playerLeaderboardButton.addActionListener(new PlayerLeaderboardButtonListener());

        mainMenuPanel.add(usernameLabel);
        mainMenuPanel.add(usernameField);
        mainMenuPanel.add(passwordLabel);
        mainMenuPanel.add(passwordField);
 
        mainMenuPanel.add(signUpButton);
        mainMenuPanel.add(loginButton);
        mainMenuPanel.add(playerLeaderboardButton);

        add(mainMenuPanel);
    }

   private void saveUser(String username, String password) {
    userDatabase.put(username, password);
    saveUserDatabaseToFile(); // Save the updated userDatabase to the file
}

private void initializeUserDatabase() {
    userDatabase = new HashMap<>();
    loadUserDatabaseFromFile(); // Load the userDatabase from the file
}


   private boolean isUserRegistered(String username, String password) {
       System.out.println(username);
     String storedPassword = null;
       System.out.println(userDatabase.toString());
     if(userDatabase.containsKey(username)){  

     storedPassword = userDatabase.get(username);
     }
       
    return storedPassword != null && storedPassword.equals(password);
}

   private static class PlayerLeaderboardButtonListener implements ActionListener {

    public PlayerLeaderboardButtonListener() {
    }

    @Override
public void actionPerformed(ActionEvent e) {
    JFrame frame = new JFrame("Leaderboard");
     JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Tic Tac Toe", createTicTacToeLeaderboardPanel());
        tabbedPane.addTab("Simulation", createSimulationLeaderboardPanel());

        frame.getContentPane().add(tabbedPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
      

    // Set the location of the leaderboard frame
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    int frameWidth = frame.getWidth();
    int frameHeight = frame.getHeight();
    int frameX = (screenWidth - frameWidth) / 2;
    int frameY = (screenHeight - frameHeight) / 2;
    frame.setLocation(frameX, frameY);

    // Make the frame visible after all the components are added
    frame.setVisible(true);
}

    
    private static JPanel createTicTacToeLeaderboardPanel() {
       
       

        String filename = "leaderboard.dat";
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Read the LeaderboardDataStorage object from the leaderboard.dat file
            LeaderboardDataStorage dataStorage = (LeaderboardDataStorage) ois.readObject();
            // Get the Tic Tac Toe leaderboard entries
            java.util.List<LeaderboardEntry> leaderboard = dataStorage.getLeaderboard();
            // Sort the leaderboard based on the score
            leaderboard.sort(Comparator.comparingInt(LeaderboardEntry::getScore).reversed());
              JPanel leaderboardPanel = new JPanel(new GridLayout(leaderboard.size() + 1, 6));
             // Create a Font with increased size
           // Create a Font with increased size
            Font labelFont = new Font(Font.DIALOG, Font.PLAIN, 16);
            // Add labels for the leaderboard column names
            addStyledLabel(leaderboardPanel, "Name", labelFont);
            addStyledLabel(leaderboardPanel, "Win", labelFont);
            addStyledLabel(leaderboardPanel, "Draw", labelFont);
            addStyledLabel(leaderboardPanel, "Lose", labelFont);
            addStyledLabel(leaderboardPanel, "Score", labelFont);
            addStyledLabel(leaderboardPanel, "Last Save", labelFont);

            // Process the leaderboard entries as needed
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("|   Name   |   Win   |   Draw   |   Lose   |   Score   |       Last Save     |");
            System.out.println("-----------------------------------------------------------------------------");
            for (LeaderboardEntry entry : leaderboard) {
                String name = entry.getName();
                int win = entry.getWin();
                int draw = entry.getDraw();
                int lose = entry.getLose();
                int score = entry.getScore();
                String time = entry.getTimeSaved();
                addStyledLabel(leaderboardPanel, name, labelFont);
                addStyledLabel(leaderboardPanel, String.valueOf(win), labelFont);
                addStyledLabel(leaderboardPanel, String.valueOf(draw), labelFont);
                addStyledLabel(leaderboardPanel, String.valueOf(lose), labelFont);
                addStyledLabel(leaderboardPanel, String.valueOf(score), labelFont);
                addStyledLabel(leaderboardPanel, time, labelFont);
                System.out.printf("|%-9s |%-8d |%-9d |%-9d |%-10d |%-20s |\n",
                        name,
                        win,
                        draw,
                        lose,
                        score,
                        time);
                System.out.println("-----------------------------------------------------------------------------");
            }
   
            // Set the preferred size of the leaderboard panel
            leaderboardPanel.setPreferredSize(new Dimension(1000, 300));
             System.out.println("Tic Tac Toe leaderboard data loaded and sorted successfully."); 
             return leaderboardPanel;
        } catch (FileNotFoundException ex) {
            // Handle file not found exception
        } catch (IOException ex) {
            // Handle IO exception
        } catch (ClassNotFoundException ex) {
            // Handle class not found exception
        }

        return null;
    }

    private static JPanel createSimulationLeaderboardPanel() {
        String filename = "gameLeaderboard.dat";
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            System.out.println("Yooo");
            // Read the LeaderboardDataStorage object from the leaderboard.dat file
            LeaderboardDataStorage dataStorage = (LeaderboardDataStorage) ois.readObject();
            // Get the Tic Tac Toe leaderboard entries
            java.util.List<LeaderboardEntry> leaderboard = dataStorage.getLeaderboard();
            // Sort the leaderboard based on the score
            leaderboard.sort(Comparator.comparingInt(LeaderboardEntry::getScore).reversed());
              JPanel leaderboardPanel = new JPanel(new GridLayout(leaderboard.size() + 1, 5));
             // Create a Font with increased size
           // Create a Font with increased size
            Font labelFont = new Font(Font.DIALOG, Font.PLAIN, 16);
            // Add labels for the leaderboard column names
            addStyledLabel(leaderboardPanel, "Name", labelFont);
            addStyledLabel(leaderboardPanel, "Win", labelFont);
            addStyledLabel(leaderboardPanel, "Lose", labelFont);
            addStyledLabel(leaderboardPanel, "Score", labelFont);
            addStyledLabel(leaderboardPanel, "Difficulty", labelFont);

        
            for (LeaderboardEntry entry : leaderboard) {
                String name = entry.getName();
                int win = entry.getWin();
                int lose = entry.getLose();
                int score = entry.getScore();
                String difficuly = entry.getDifficulty();
                addStyledLabel(leaderboardPanel, name, labelFont);
                addStyledLabel(leaderboardPanel, String.valueOf(win), labelFont);
                addStyledLabel(leaderboardPanel, String.valueOf(lose), labelFont);
                addStyledLabel(leaderboardPanel, String.valueOf(score), labelFont);
                addStyledLabel(leaderboardPanel, difficuly, labelFont);
            }
   
            // Set the preferred size of the leaderboard panel
            leaderboardPanel.setPreferredSize(new Dimension(1000, 300));
             System.out.println("Tic Tac Toe leaderboard data loaded and sorted successfully."); 
             return leaderboardPanel;
        } catch (FileNotFoundException ex) {
            // Handle file not found exception
        } catch (IOException ex) {
            // Handle IO exception
        } catch (ClassNotFoundException ex) {
            // Handle class not found exception
        }

        return null;
    }

    private static void addStyledLabel(JPanel panel, String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        panel.add(label);
    }
}
      
private class SignUpButtonPanel implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        signUpFrame = new JFrame("Sign Up");
        signUpFrame.setSize(350, 200);
        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel signUpPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        usernameLabel = new JLabel(" Username:");
        signupUsernameField = new JTextField();

        passwordLabel = new JLabel(" Password:");
        signupPasswordField = new JPasswordField();

        confirmPasswordLabel = new JLabel(" Confirm Password:");
        confirmPasswordField = new JPasswordField();

         signUpButton = new JButton(" Sign Up");
        signUpButton.addActionListener(new SignUpButtonListener());

        signUpPanel.add(usernameLabel);
        signUpPanel.add(signupUsernameField);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(signupPasswordField);
        signUpPanel.add(confirmPasswordLabel);
        signUpPanel.add(confirmPasswordField);
        signUpPanel.add(signUpButton);

        signUpFrame.add(signUpPanel);
        signUpFrame.setVisible(true);
    }
} 
    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = signupUsernameField.getText();
            String password = new String(signupPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(MainMenu.this, "Please enter a valid username and password.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(MainMenu.this, "Passwords do not match. Please try again.");
                return;
            }

            if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(MainMenu.this, "Username already exists. Please choose a different username.");
                return;
            }

            saveUser(username, password);
            JOptionPane.showMessageDialog(MainMenu.this, "User registered successfully!");
//            usernameField.setText("");
//            passwordField.setText("");
//            confirmPasswordField.setText("");
            signUpFrame.dispose();

        }
    }

private class LoginButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (isUserRegistered(username, password)) {
            MainMenu.this.username = username;
            JOptionPane.showMessageDialog(MainMenu.this, "Login successful!");
            dispose();

            openChooseMode();
        } else {
            JOptionPane.showMessageDialog(MainMenu.this, "Invalid username or password. Please try again.");
        }
    }
}

private void openChooseMode() {
    JDialog chooseModeDialog = new JDialog(this, "Choose Mode", Dialog.ModalityType.APPLICATION_MODAL);
    chooseModeDialog.setSize(WIDTH, HEIGHT);
    chooseModeDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    chooseModeDialog.setLocationRelativeTo(null);

    JPanel setGamePanel = new JPanel(new GridLayout(2, 1, 10, 10));

    JButton simulationButton = new JButton("Play");
    simulationButton.addActionListener(new ModePanel("Simulation", chooseModeDialog));

    JButton tttButton = new JButton("TicTacToe");
    tttButton.addActionListener(new ModePanel("TicTacToe", chooseModeDialog));

    setGamePanel.add(simulationButton);
    setGamePanel.add(tttButton);

    chooseModeDialog.add(setGamePanel);
    chooseModeDialog.setVisible(true);
}

private class ModePanel implements ActionListener {
    private final String mode;
    JDialog chooseModeDialog;
    private ModePanel(String mode, JDialog chooseModeDialog) {
        this.mode = mode;
        this.chooseModeDialog = chooseModeDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chooseModeDialog.dispose();
        openChooseDifficulty(mode);
    }
}

private void openChooseDifficulty(String mode) {
    JDialog chooseDifficultyDialog = new JDialog(this, "Choose Difficulty", Dialog.ModalityType.APPLICATION_MODAL);
    chooseDifficultyDialog.setSize(WIDTH, HEIGHT);
    chooseDifficultyDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    chooseDifficultyDialog.setLocationRelativeTo(null);

    JPanel difficultyPanel = new JPanel(new GridLayout(3, 1, 10, 10));

    JButton easyButton = new JButton("Easy");
    easyButton.addActionListener(new IsNewGamePanel(mode, "Easy", chooseDifficultyDialog));

    JButton mediumButton = new JButton("Medium");
    mediumButton.addActionListener(new IsNewGamePanel(mode, "Medium", chooseDifficultyDialog));

    JButton hardButton = new JButton("Hard");
    hardButton.addActionListener(new IsNewGamePanel(mode, "Hard", chooseDifficultyDialog));

    difficultyPanel.add(easyButton);
    difficultyPanel.add(mediumButton);
    difficultyPanel.add(hardButton);

    chooseDifficultyDialog.add(difficultyPanel);
    chooseDifficultyDialog.setVisible(true);
}
private class IsNewGamePanel implements ActionListener {
    private final String mode;
    private final String difficulty;
    
    JDialog chooseDifficultyDialog;
    private IsNewGamePanel(String mode, String difficulty, JDialog chooseDifficultyDialog) {
        this.mode = mode;
         this.difficulty = difficulty;
        this.chooseDifficultyDialog = chooseDifficultyDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chooseDifficultyDialog.dispose();
        openChooseGame(mode ,difficulty);
    }
}
private void openChooseGame(String mode,String difficulty) {
    JDialog chooseGameDialog = new JDialog(this, "Choose Difficulty", Dialog.ModalityType.APPLICATION_MODAL);
    chooseGameDialog.setSize(WIDTH, HEIGHT);
    chooseGameDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    chooseGameDialog.setLocationRelativeTo(null);

    JPanel choosePanel = new JPanel(new GridLayout(2, 1, 10, 10));

    JButton continueButton = new JButton("Continue");
    continueButton.addActionListener(new EnterGame(mode ,difficulty, "continue", chooseGameDialog));

    JButton newGameButton = new JButton("New Game");
    newGameButton.addActionListener(new EnterGame( mode, difficulty, "new", chooseGameDialog));

    

    choosePanel.add(continueButton);
    choosePanel.add(newGameButton);
 

    chooseGameDialog.add(choosePanel);
    chooseGameDialog.setVisible(true);
}

private class EnterGame implements ActionListener {
    private final String mode;
    private final String difficulty;
    private final String game; 
    private final JDialog dialog;

    public EnterGame(String mode, String difficulty,String game, JDialog dialog) {
        this.mode = mode;
        this.difficulty = difficulty;
        this.game = game;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isFileExist( username,  difficulty) && mode.equalsIgnoreCase("Simulation") && game.equalsIgnoreCase("continue")) {
                JOptionPane.showMessageDialog(MainMenu.this, "No data saved. Start New Game.");
           }
        else{
           dialog.dispose();

        Main.main(new String[]{difficulty, username, mode, game});  
        }
       
    }
}
    public  boolean isFileExist(String username, String difficulty) {
      String filename = username + "_" + difficulty + ".dat"; 
         System.out.println("Filename = " + filename);
        File file = new File(filename);
        return file.exists();
    }

   

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
    }


 private void saveUserDatabaseToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("userData.txt")))) {
            for (Map.Entry<String, String> entry : userDatabase.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserDatabaseFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("userData.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userDatabase.put(parts[0], parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {
        
        }
    } 
    }
 








               
