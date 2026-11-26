package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;

public class RoundCompletionDataPage {

    public JFrame frame;
    private JComboBox<String> timePeriodCombo;
    private JPanel chartPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RoundCompletionDataPage window = new RoundCompletionDataPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RoundCompletionDataPage() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Round Completion Data");
        
        JLabel titleLabel = new JLabel("Round Completion Data");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(280, 20, 350, 40);
        frame.getContentPane().add(titleLabel);
        
        JLabel lblTimePeriod = new JLabel("Time Period:");
        lblTimePeriod.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTimePeriod.setBounds(50, 80, 100, 25);
        frame.getContentPane().add(lblTimePeriod);
        
        timePeriodCombo = new JComboBox<String>();
        timePeriodCombo.addItem("1 Week");
        timePeriodCombo.addItem("1 Month");
        timePeriodCombo.addItem("3 Months");
        timePeriodCombo.addItem("1 Year");
        timePeriodCombo.addItem("All Time");
        timePeriodCombo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        timePeriodCombo.setBounds(160, 80, 150, 25);
        frame.getContentPane().add(timePeriodCombo);
        
        JButton btnGenerateChart = new JButton("Generate Chart");
        btnGenerateChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateRoundChart();
            }
        });
        btnGenerateChart.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGenerateChart.setBackground(new Color(144, 238, 144));
        btnGenerateChart.setBounds(330, 80, 150, 25);
        frame.getContentPane().add(btnGenerateChart);
        
        chartPanel = new JPanel();
        chartPanel.setBounds(50, 130, 800, 370);
        frame.getContentPane().add(chartPanel);
        
        JButton btnBackToDashboard = new JButton("Back to Dashboard");
        btnBackToDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = new Dashboard();
                dashboard.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnBackToDashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBackToDashboard.setBounds(350, 520, 200, 35);
        frame.getContentPane().add(btnBackToDashboard);
        
        generateRoundChart();
    }
    
    private void generateRoundChart() {
        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            // Updated filename
            BufferedReader br = new BufferedReader(new FileReader(LoginPage.currentUsername + "_trainingData.csv"));
            String line;
            
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length >= 4 && !data[3].isEmpty()) {
                    try {
                        Integer rounds = Integer.parseInt(data[3]);
                        String date = data[0];
                        dataset.addValue(rounds, "Rounds", date);
                    } catch(NumberFormatException e) {
                        // Skip invalid entries
                    }
                }
            }
            br.close();
            
            JFreeChart chart = ChartFactory.createBarChart(
                "Rounds Completed Over Time",
                "Date",
                "Rounds Completed",
                dataset
            );
            
            ChartPanel panel = new ChartPanel(chart);
            chartPanel.removeAll();
            chartPanel.setLayout(new java.awt.BorderLayout());
            chartPanel.add(panel);
            chartPanel.validate();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}