package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList; // imports LinkedList
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class LoginPage {

    public JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public static LinkedList<Login> users = new LinkedList<Login>(); // Creates a new LinkedList
    public static String currentUsername = "";


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage window = new LoginPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginPage() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Login");
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("boxingglove1.png"));
        lblNewLabel_1.setBounds(361, 16, 681, 597);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel titleLabel = new JLabel("MyBoxingPal");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        titleLabel.setBounds(200, 30, 200, 40);
        frame.getContentPane().add(titleLabel);
        
        JLabel subtitleLabel = new JLabel("Login to Your Account");
        subtitleLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
        subtitleLabel.setBounds(210, 70, 180, 20);
        frame.getContentPane().add(subtitleLabel);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(120, 130, 100, 25);
        frame.getContentPane().add(lblUsername);
        
        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usernameField.setBounds(230, 130, 220, 30);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(120, 185, 100, 25);
        frame.getContentPane().add(lblPassword);
        
        passwordField = new JPasswordField(); // JPasswordField - Creates a password input field that hides characters
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Sets font for the password field
        passwordField.setBounds(230, 185, 220, 30); // Positions the password field
        frame.getContentPane().add(passwordField); // Adds password field to the frame
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    fileRead(); // File Reading - Reads all users from the file into the users list
                } catch (IOException e1) {
                    System.out.println(e1.getMessage()); // Prints error message if file reading fails
                }
                
                boolean flag = false; // Flag to track if login is successful
                String uname = usernameField.getText(); // Gets the username entered by the user
                String pname = new String(passwordField.getPassword()); // Gets the password entered by the user
                
                for(int i = 0; i < users.size(); i++) { // Linear search - search through all users
                    if(uname.equals(users.get(i).getUn()) && // Checks if username matches
                       pname.equals(users.get(i).getPwd())) { // Checks if password matches
                        flag = true; // Sets flag to true if credentials match
                        currentUsername = uname; // Stores the current logged-in username
                        break; // Exits the loop as user is found
                    }
                }
                
                if(flag == true) { // If credentials were correct
                    Dashboard dashboard = new Dashboard(); // Opens the Dashboard
                    dashboard.frame.setVisible(true);
                    frame.dispose(); // Closes the login window
                } else { // If credentials were incorrect
                    JOptionPane.showMessageDialog(null, // Shows error message dialog
                        "Username or Password is incorrect", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogin.setBackground(new Color(100, 149, 237));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBounds(230, 250, 100, 40);
        frame.getContentPane().add(btnLogin);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WelcomePage welcomePage = new WelcomePage();
                welcomePage.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBack.setBounds(350, 250, 100, 40);
        frame.getContentPane().add(btnBack);
        
        JLabel lblDontHaveAccount = new JLabel("Don't have an account?");
        lblDontHaveAccount.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDontHaveAccount.setBounds(200, 330, 150, 20);
        frame.getContentPane().add(lblDontHaveAccount);
        
        JButton btnGoToSignup = new JButton("Sign Up");
        btnGoToSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignupPage signupPage = new SignupPage();
                signupPage.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnGoToSignup.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnGoToSignup.setForeground(new Color(60, 179, 113));
        btnGoToSignup.setBackground(Color.WHITE);
        btnGoToSignup.setBorderPainted(false);
        btnGoToSignup.setBounds(350, 330, 80, 20);
        frame.getContentPane().add(btnGoToSignup);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("boxingglove2.png"));
        lblNewLabel.setBounds(132, 19, 86, 69);
        frame.getContentPane().add(lblNewLabel);
    }
    
    public static void fileRead() throws IOException {
        users.clear(); // Clears existing users list to avoid duplicates
        File file = new File("users.txt"); // File Reading - Creates a File object pointing to users.txt
        
        if(!file.exists()) { // Checks if the file exists
            file.createNewFile(); // Creates the file if it doesn't exist
            return; // Exits the method 
        }
        
        FileReader fr = new FileReader(file); // Creates a FileReader to read from the file
        BufferedReader br = new BufferedReader(fr); // Wraps FileReader in BufferedReader for efficient reading
        String line = br.readLine(); // File Searching - Reads first line from the file
        while(line != null) { // Loops through each line until end of file
            String[] data = line.split(","); // Splits the line by comma to separate username and password
            if(data.length >= 2) { // Checks if the line contains both username and password
                Login l1 = new Login(data[0], data[1]); // Creates a Login object with username and password
                users.add(l1); // Adds the Login object to the users LinkedList
            }
            line = br.readLine(); // Reads the next line
        }
        br.close(); // Closes the BufferedReader to free system resources
    }
}