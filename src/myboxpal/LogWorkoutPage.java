package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class LogWorkoutPage {

    public JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogWorkoutPage window = new LogWorkoutPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LogWorkoutPage() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Log Workout");
        
        JLabel titleLabel = new JLabel("Log Workout");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(260, 30, 200, 40);
        frame.getContentPane().add(titleLabel);
        
        JButton btnBoxingLog = new JButton("Boxing Log");
        btnBoxingLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BoxingLogPage boxingLog = new BoxingLogPage();
                boxingLog.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnBoxingLog.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBoxingLog.setBackground(new Color(255, 160, 122));
        btnBoxingLog.setBounds(225, 100, 250, 100);
        btnBoxingLog.setFocusPainted(false);
        frame.getContentPane().add(btnBoxingLog);
        
        JButton btnGymLog = new JButton("Gym Log");
        btnGymLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GymLogPage gymLog = new GymLogPage();
                gymLog.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnGymLog.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnGymLog.setBackground(new Color(135, 206, 250));
        btnGymLog.setBounds(225, 230, 250, 100);
        frame.getContentPane().add(btnGymLog);
        
        JButton btnBackToDashboard = new JButton("Back to Dashboard");
        btnBackToDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = new Dashboard();
                dashboard.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnBackToDashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBackToDashboard.setBounds(250, 360, 200, 35);
        frame.getContentPane().add(btnBackToDashboard);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("benchpress1.png"));
        lblNewLabel.setBounds(417, 25, 63, 50);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("punchingbag.png"));
        lblNewLabel_1.setBounds(230, 30, 27, 40);
        frame.getContentPane().add(lblNewLabel_1);
    }
}