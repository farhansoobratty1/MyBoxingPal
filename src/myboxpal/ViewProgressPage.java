package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ViewProgressPage {

    public JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewProgressPage window = new ViewProgressPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewProgressPage() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - View Progress");
        
        JLabel titleLabel = new JLabel("View Progress");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(250, 30, 200, 40);
        frame.getContentPane().add(titleLabel);
        
        JButton btnWeightData = new JButton("Weight Data");
        btnWeightData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WeightDataPage weightData = new WeightDataPage();
                weightData.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnWeightData.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnWeightData.setBackground(new Color(173, 216, 230));
        btnWeightData.setBounds(225, 120, 250, 50);
        btnWeightData.setFocusPainted(false);
        frame.getContentPane().add(btnWeightData);
        
        JButton btnRoundCompletion = new JButton("Round Completion Data");
        btnRoundCompletion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RoundCompletionDataPage roundCompletion = new RoundCompletionDataPage();
                roundCompletion.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnRoundCompletion.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRoundCompletion.setBackground(new Color(255, 182, 193));
        btnRoundCompletion.setBounds(225, 190, 250, 50);
        frame.getContentPane().add(btnRoundCompletion);
        
        JButton btnWorkoutDistribution = new JButton("Workout Distribution");
        btnWorkoutDistribution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WorkoutDistributionPage workoutDist = new WorkoutDistributionPage();
                workoutDist.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnWorkoutDistribution.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnWorkoutDistribution.setBackground(new Color(221, 160, 221));
        btnWorkoutDistribution.setBounds(225, 260, 250, 50);
        frame.getContentPane().add(btnWorkoutDistribution);
        
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
    }
}