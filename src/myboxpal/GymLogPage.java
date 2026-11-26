package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class GymLogPage {

    public JFrame frame;
    private JTextField bodyWeightField;
    private JTextField hoursField;
    private JTextField minutesField;
    private JComboBox<String> workoutTypeCombo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GymLogPage window = new GymLogPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GymLogPage() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Gym Log");
        
        JLabel titleLabel = new JLabel("Gym Log");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(280, 30, 106, 40);
        frame.getContentPane().add(titleLabel);
        
        JLabel lblBodyWeight = new JLabel("Body Weight (kg):");
        lblBodyWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblBodyWeight.setBounds(100, 100, 150, 25);
        frame.getContentPane().add(lblBodyWeight);
        
        bodyWeightField = new JTextField();
        bodyWeightField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        bodyWeightField.setBounds(310, 100, 200, 25);
        frame.getContentPane().add(bodyWeightField);
        bodyWeightField.setColumns(10);
        
        JLabel lblWorkoutType = new JLabel("Workout Type:");
        lblWorkoutType.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblWorkoutType.setBounds(100, 150, 150, 25);
        frame.getContentPane().add(lblWorkoutType);
        
        workoutTypeCombo = new JComboBox<String>(); // JComboBox - Creates dropdown menu for workout type selection
        workoutTypeCombo.addItem("Cardio"); // Adds "Cardio" option to the dropdown
        workoutTypeCombo.addItem("Strength"); // Adds "Strength" option
        workoutTypeCombo.addItem("Core"); // Adds "Core" option
        workoutTypeCombo.addItem("Flexibility"); // Adds "Flexibility" option
        workoutTypeCombo.addItem("HIIT"); // Adds "HIIT" option
        workoutTypeCombo.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Sets font for combo box
        workoutTypeCombo.setBounds(310, 150, 200, 25); // Positions the combo box
        frame.getContentPane().add(workoutTypeCombo); // Adds combo box to the frame
        
        JLabel lblWorkoutDuration = new JLabel("Workout Duration:");
        lblWorkoutDuration.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblWorkoutDuration.setBounds(100, 200, 150, 25);
        frame.getContentPane().add(lblWorkoutDuration);
        
        JLabel lblHours = new JLabel("Hours:");
        lblHours.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblHours.setBounds(310, 200, 50, 25);
        frame.getContentPane().add(lblHours);
        
        hoursField = new JTextField();
        hoursField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        hoursField.setBounds(370, 200, 60, 25);
        frame.getContentPane().add(hoursField);
        hoursField.setColumns(10);
        
        JLabel lblMinutes = new JLabel("Minutes:");
        lblMinutes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMinutes.setBounds(445, 200, 60, 25);
        frame.getContentPane().add(lblMinutes);
        
        minutesField = new JTextField();
        minutesField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        minutesField.setBounds(510, 200, 60, 25);
        frame.getContentPane().add(minutesField);
        minutesField.setColumns(10);
        
        JButton btnSaveSession = new JButton("Save Session");
        btnSaveSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame, 
                    "Would you like to save this data?", 
                    "Save Session", 
                    JOptionPane.YES_NO_OPTION);
                
                if(choice == JOptionPane.YES_OPTION) {
                    try {
                        Float bodyWeight = Float.parseFloat(bodyWeightField.getText());
                        String workoutType = (String) workoutTypeCombo.getSelectedItem();
                        Integer hours = Integer.parseInt(hoursField.getText());
                        Integer minutes = Integer.parseInt(minutesField.getText());
                        Integer totalMinutes = hours * 60 + minutes;
                        
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String currentDate = sdf.format(new Date());
                        
                        BufferedWriter bw = new BufferedWriter(new FileWriter(LoginPage.currentUsername + "_trainingData.csv", true));
                        bw.write(currentDate + "," + workoutType + "," + bodyWeight + ",,," + totalMinutes + ",\n");
                        bw.close();
                        
                        JOptionPane.showMessageDialog(frame, "Session saved successfully!");
                        
                        bodyWeightField.setText("");
                        hoursField.setText("");
                        minutesField.setText("");
                        
                    } catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, 
                            "Please enter valid numbers!", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE);
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(frame, 
                            "Error saving data: " + ex.getMessage(), 
                            "Save Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnSaveSession.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSaveSession.setBackground(new Color(144, 238, 144));
        btnSaveSession.setBounds(250, 280, 200, 35);
        frame.getContentPane().add(btnSaveSession);
        
        JButton btnBackToDashboard = new JButton("Back to Dashboard");
        btnBackToDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!bodyWeightField.getText().isEmpty() || 
                   !hoursField.getText().isEmpty() || 
                   !minutesField.getText().isEmpty()) {
                    int choice = JOptionPane.showConfirmDialog(frame, 
                        "You have unsaved data. Save before leaving?", 
                        "Unsaved Data", 
                        JOptionPane.YES_NO_CANCEL_OPTION);
                    
                    if(choice == JOptionPane.CANCEL_OPTION) {
                        return;
                    } else if(choice == JOptionPane.YES_OPTION) {
                        btnSaveSession.doClick();
                    }
                }
                
                Dashboard dashboard = new Dashboard();
                dashboard.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnBackToDashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBackToDashboard.setBounds(250, 340, 200, 35);
        frame.getContentPane().add(btnBackToDashboard);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("benchpress2.png"));
        lblNewLabel.setBounds(232, 30, 60, 40);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("benchpress1.png"));
        lblNewLabel_1.setBounds(384, 30, 60, 40);
        frame.getContentPane().add(lblNewLabel_1);
    }
}