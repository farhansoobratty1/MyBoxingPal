package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class WelcomePage {

    public JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WelcomePage window = new WelcomePage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public WelcomePage() {
        initialise();
    }


    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Welcome");
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("boxingglove1.png"));
        lblNewLabel_1.setBounds(368, 39, 681, 597);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("boxingglove2.png"));
        lblNewLabel.setBounds(112, 42, 86, 69);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel titleLabel = new JLabel("MyBoxingPal");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
        titleLabel.setBounds(180, 50, 250, 50);
        frame.getContentPane().add(titleLabel);
        
        JLabel subtitleLabel = new JLabel("Track Your Boxing Journey");
        subtitleLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
        subtitleLabel.setBounds(180, 100, 250, 30);
        frame.getContentPane().add(subtitleLabel);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnLogin.setBackground(new Color(100, 149, 237));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBounds(184, 176, 200, 50);
        btnLogin.setFocusPainted(false);
        frame.getContentPane().add(btnLogin);
        
        JButton btnSignup = new JButton("Sign Up");
        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignupPage signupPage = new SignupPage();
                signupPage.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnSignup.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSignup.setBackground(new Color(60, 179, 113));
        btnSignup.setForeground(Color.WHITE);
        btnSignup.setBounds(184, 248, 200, 50);
        frame.getContentPane().add(btnSignup);
    }
}