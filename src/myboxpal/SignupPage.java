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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SignupPage {

    public JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignupPage window = new SignupPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SignupPage() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Sign Up");
        
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        titleLabel.setBounds(180, 30, 250, 40);
        frame.getContentPane().add(titleLabel);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(90, 110, 120, 25);
        frame.getContentPane().add(lblUsername);
        
        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usernameField.setBounds(230, 110, 250, 30);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(90, 160, 120, 25);
        frame.getContentPane().add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordField.setBounds(230, 160, 250, 30);
        frame.getContentPane().add(passwordField);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblConfirmPassword.setBounds(90, 210, 150, 25);
        frame.getContentPane().add(lblConfirmPassword);
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        confirmPasswordField.setBounds(230, 210, 250, 30);
        frame.getContentPane().add(confirmPasswordField);
        
        JButton btnSignup = new JButton("Sign Up");
        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSignup();
            }
        });
        btnSignup.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSignup.setBackground(new Color(60, 179, 113));
        btnSignup.setForeground(Color.WHITE);
        btnSignup.setBounds(230, 270, 120, 40);
        frame.getContentPane().add(btnSignup);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WelcomePage welcomePage = new WelcomePage();
                welcomePage.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancel.setBounds(360, 270, 120, 40);
        frame.getContentPane().add(btnCancel);
        
        JLabel lblAlreadyHaveAccount = new JLabel("Already have an account?");
        lblAlreadyHaveAccount.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblAlreadyHaveAccount.setBounds(200, 330, 150, 20);
        frame.getContentPane().add(lblAlreadyHaveAccount);
        
        JButton btnGoToLogin = new JButton("Login");
        btnGoToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnGoToLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnGoToLogin.setForeground(new Color(100, 149, 237));
        btnGoToLogin.setBackground(Color.WHITE);
        btnGoToLogin.setBorderPainted(false);
        btnGoToLogin.setBounds(350, 330, 70, 20);
        frame.getContentPane().add(btnGoToLogin);
    }
    
    private void handleSignup() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        // Validation
        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Please fill in all fields!", 
                "Signup Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(username.contains(",")) {
            JOptionPane.showMessageDialog(frame, 
                "Username cannot contain commas!", 
                "Signup Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(username.length() < 3) {
            JOptionPane.showMessageDialog(frame, 
                "Username must be at least 3 characters long!", 
                "Signup Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(password.length() < 6) {
            JOptionPane.showMessageDialog(frame, 
                "Password must be at least 6 characters long!", 
                "Signup Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame, 
                "Passwords do not match!", 
                "Signup Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if username already exists
        if(usernameExists(username)) {
            JOptionPane.showMessageDialog(frame, 
                "Username already exists! Please choose another.", 
                "Signup Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Save to file
        try {
            saveUser(username, password);
            JOptionPane.showMessageDialog(frame, 
                "Account created successfully! You can now login.", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            LoginPage loginPage = new LoginPage();
            loginPage.frame.setVisible(true);
            frame.dispose();
            
        } catch(IOException e) {
            JOptionPane.showMessageDialog(frame, 
                "Error creating account: " + e.getMessage(), 
                "Signup Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean usernameExists(String username) {
        try {
            File file = new File("users.txt");
            if(!file.exists()) {
                return false;
            }
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length > 0 && data[0].equals(username)) {
                    br.close();
                    return true;
                }
            }
            br.close();
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    private void saveUser(String username, String password) throws IOException {
        File file = new File("users.txt"); // Creates File object for users.txt
        
        if(!file.exists()) { // Checks if the file exists
            file.createNewFile(); // Creates the file if it doesn't exist
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)); // Creates BufferedWriter with FileWriter in append mode (true)
        bw.write(username + "," + password); // Writing to Text File - Writes the username and password separated by a comma
        bw.newLine(); // Adds a new line after the entry
        bw.close(); // Closes the BufferedWriter to save changes and free resources
    }
}