package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class BoxingLogPage {

    public JFrame frame;
    private JTextField punchSuccessRateField;
    private JTextField roundsCompletedField;
    private JTextArea notesArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BoxingLogPage window = new BoxingLogPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BoxingLogPage() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Boxing Log");
        
        JLabel titleLabel = new JLabel("Boxing Log");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(270, 30, 135, 40);
        frame.getContentPane().add(titleLabel);
        
        JLabel lblPunchSuccessRate = new JLabel("Punch Success Rate (%):");
        lblPunchSuccessRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPunchSuccessRate.setBounds(100, 100, 200, 25);
        frame.getContentPane().add(lblPunchSuccessRate);
        
        punchSuccessRateField = new JTextField(); // JTextField - Creates a single-line text input field
        punchSuccessRateField.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Sets the font for the text field
        punchSuccessRateField.setBounds(310, 100, 200, 25); // Positions the text field
        frame.getContentPane().add(punchSuccessRateField); // Adds text field to the frame
        punchSuccessRateField.setColumns(10); // Sets the width of the text field in columns
        
        JLabel lblRoundsCompleted = new JLabel("Rounds Completed:");
        lblRoundsCompleted.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRoundsCompleted.setBounds(100, 150, 200, 25);
        frame.getContentPane().add(lblRoundsCompleted);
        
        roundsCompletedField = new JTextField();
        roundsCompletedField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        roundsCompletedField.setBounds(310, 150, 200, 25);
        frame.getContentPane().add(roundsCompletedField);
        roundsCompletedField.setColumns(10);
        
        JLabel lblNotes = new JLabel("Notes:");
        lblNotes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNotes.setBounds(100, 200, 200, 25);
        frame.getContentPane().add(lblNotes);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(310, 200, 300, 80);
        frame.getContentPane().add(scrollPane);
        
        notesArea = new JTextArea(); // JTextArea - Creates a multi-line text input area for notes
        notesArea.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Sets font for text area
        scrollPane.setViewportView(notesArea); // Places text area inside scroll pane to allow scrolling
        
        JButton btnSaveSession = new JButton("Save Session");
        btnSaveSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame, 
                    "Would you like to save this data?", 
                    "Save Session", 
                    JOptionPane.YES_NO_OPTION);
                
                if(choice == JOptionPane.YES_OPTION) {
                    try {
                        Float punchRate = Float.parseFloat(punchSuccessRateField.getText()); // Parses the punch success rate from text field
                        Integer rounds = Integer.parseInt(roundsCompletedField.getText()); // Parses rounds completed from text field
                        String notes = notesArea.getText(); // Gets notes from text area
                        
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // Creates date formatter
                        String currentDate = sdf.format(new Date()); // Formats current date and time
                        
                        BufferedWriter bw = new BufferedWriter(new FileWriter(LoginPage.currentUsername + "_trainingData.csv", true)); // Opens user-specific file in append mode
                        bw.write(currentDate + ",Boxing,," + rounds + "," + punchRate + ",," + notes + "\n"); // File Writing - Writes workout data with commas separating fields
                        bw.close(); // Closes the writer
                        
                        JOptionPane.showMessageDialog(frame, "Session saved successfully!"); // Shows success message
                        
                        punchSuccessRateField.setText(""); // Clears the punch rate field
                        roundsCompletedField.setText(""); // Clears the rounds field
                        notesArea.setText(""); // Clears the notes area
                        
                    } catch(NumberFormatException ex) { // Catches invalid number input
                        JOptionPane.showMessageDialog(frame, 
                            "Please enter valid numbers for punch success rate and rounds!", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE);
                    } catch(Exception ex) { // Catches any other errors during file writing
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
        btnSaveSession.setBounds(250, 320, 200, 35);
        frame.getContentPane().add(btnSaveSession);
        
        JButton btnBackToDashboard = new JButton("Back to Dashboard");
        btnBackToDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!punchSuccessRateField.getText().isEmpty() || 
                   !roundsCompletedField.getText().isEmpty() || 
                   !notesArea.getText().isEmpty()) {
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
        btnBackToDashboard.setBounds(250, 380, 200, 35);
        frame.getContentPane().add(btnBackToDashboard);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("punchingbag.png"));
        lblNewLabel.setBounds(241, 30, 35, 40);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("punchingbag.png"));
        lblNewLabel_1.setBounds(398, 30, 35, 40);
        frame.getContentPane().add(lblNewLabel_1);
    }
}