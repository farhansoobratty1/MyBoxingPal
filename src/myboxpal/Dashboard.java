package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Dashboard {

    public JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dashboard window = new Dashboard();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Dashboard() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Dashboard");
        
        JLabel welcomeLabel = new JLabel("Welcome to MyBoxingPal, " + LoginPage.currentUsername + "!");
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        welcomeLabel.setBounds(135, 30, 520, 40);
        frame.getContentPane().add(welcomeLabel);
        
        JButton btnLogWorkout = new JButton("Log Workout");
        btnLogWorkout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogWorkoutPage logWorkout = new LogWorkoutPage();
                logWorkout.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnLogWorkout.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogWorkout.setBackground(new Color(144, 238, 144));
        btnLogWorkout.setBounds(212, 110, 250, 50);
        btnLogWorkout.setFocusPainted(false);
        frame.getContentPane().add(btnLogWorkout);
        
        JButton btnViewProgress = new JButton("View Progress");
        btnViewProgress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewProgressPage viewProgress = new ViewProgressPage();
                viewProgress.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnViewProgress.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnViewProgress.setBackground(new Color(255, 218, 185));
        btnViewProgress.setBounds(212, 170, 250, 50);
        frame.getContentPane().add(btnViewProgress);
        
        JButton btnViewHistory = new JButton("View History");
        btnViewHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SessionHistoryPage sessionHistory = new SessionHistoryPage();
                sessionHistory.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnViewHistory.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnViewHistory.setBackground(new Color(221, 160, 221));
        btnViewHistory.setBounds(212, 230, 250, 50);
        frame.getContentPane().add(btnViewHistory);
        
        JLabel lastSessionLabel = new JLabel("Last Session: " + getLastSessionDate());
        lastSessionLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lastSessionLabel.setBounds(230, 310, 277, 50);
        frame.getContentPane().add(lastSessionLabel);
    }
    
    private String getLastSessionDate() {
        try {
            // Updated filename
            BufferedReader br = new BufferedReader(new FileReader(LoginPage.currentUsername + "_trainingData.csv"));
            String lastLine = "";
            String line;
            while((line = br.readLine()) != null) {
                if(!line.trim().isEmpty()) {
                    lastLine = line;
                }
            }
            br.close();
            if(!lastLine.isEmpty()) {
                String[] data = lastLine.split(",");
                return data[0];
            }
        } catch(Exception e) {
            return "No sessions yet";
        }
        return "No sessions yet";
    }
}