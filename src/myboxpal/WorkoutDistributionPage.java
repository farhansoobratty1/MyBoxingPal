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
import java.util.HashMap; // Imports the HashMap class from Java utilities
import java.awt.event.ActionEvent;
import java.awt.Color;
import org.jfree.chart.ChartFactory; // Imports the ChartFactory class for creating charts
import org.jfree.chart.ChartPanel; // Imports ChartPanel for displaying charts in Swing
import org.jfree.chart.JFreeChart; // Imports the main JFreeChart class
import org.jfree.data.general.DefaultPieDataset; // Imports dataset class for pie charts
import org.jfree.chart.plot.PiePlot; // Imports PiePlot for customizing pie chart appearance
import org.jfree.chart.labels.StandardPieToolTipGenerator; // Imports tooltip generator for pie charts

public class WorkoutDistributionPage {

    public JFrame frame;
    private JComboBox<String> timePeriodCombo;
    private JPanel chartPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WorkoutDistributionPage window = new WorkoutDistributionPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public WorkoutDistributionPage() {
    	initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Workout Distribution");
        
        JLabel titleLabel = new JLabel("Workout Distribution");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(300, 20, 300, 40);
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
                generateDistributionChart();
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
        
        generateDistributionChart();
    }
    
    private void generateDistributionChart() {
        try {
            DefaultPieDataset dataset = new DefaultPieDataset(); // Creates a dataset to store pie chart data
            HashMap<String, Integer> workoutCount = new HashMap<String, Integer>(); // HashMap to count workout types
            
            BufferedReader br = new BufferedReader(new FileReader(LoginPage.currentUsername + "_trainingData.csv"));
            String line;
            
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length >= 2) {
                    String workoutType = data[1]; // Extracts workout type from CSV
                    workoutCount.put(workoutType, workoutCount.getOrDefault(workoutType, 0) + 1); // Increments count for this workout type
                }
            }
            br.close();
            
            for(String key : workoutCount.keySet()) { // Loops through each workout type
                dataset.setValue(key, workoutCount.get(key)); // Adds workout type and count to the dataset
            }
            
            JFreeChart chart = ChartFactory.createPieChart( // JFreeChart - Creates a pie chart using the factory method
                "Workout Distribution", // Chart title
                dataset, // Dataset containing the data
                true, // Include legend
                true, // Generate tooltips
                false // Don't generate URLs
            );
            
            PiePlot plot = (PiePlot) chart.getPlot(); // JFreeChart - Gets the plot object to customize it
            plot.setToolTipGenerator(new StandardPieToolTipGenerator( // Sets custom tooltip format
                "{0}: {1} ({2})", // Format: WorkoutType: Count (Percentage)
                java.text.NumberFormat.getNumberInstance(), // Number format for count
                java.text.NumberFormat.getPercentInstance() // Percentage format
            ));
            
            ChartPanel panel = new ChartPanel(chart); // Creates a panel to display the chart
            panel.setToolTipText(""); // Enables tooltips on the panel
            
            chartPanel.removeAll(); // Clears any existing chart
            chartPanel.setLayout(new java.awt.BorderLayout()); // Sets layout manager
            chartPanel.add(panel); // Adds the chart panel
            chartPanel.validate(); // Validates the layout
            chartPanel.repaint(); // Repaints to display the new chart
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}