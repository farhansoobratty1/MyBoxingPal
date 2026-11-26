package myboxpal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JTextArea;

public class SessionHistoryPage {
    public JFrame frame;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SessionHistoryPage window = new SessionHistoryPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SessionHistoryPage() {
        initialise();
    }

    private void initialise() {
        frame = new JFrame();
        frame.setBounds(100, 100, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("MyBoxingPal - Session History");
        
        JLabel titleLabel = new JLabel("Session History");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(220, 20, 250, 40);
        frame.getContentPane().add(titleLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 80, 640, 320);
        frame.getContentPane().add(scrollPane);
        
        table = new JTable() {
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                
                try {
                    Object value = getValueAt(rowIndex, colIndex);
                    if (value != null && !value.toString().trim().isEmpty()) {
                        if (colIndex == 6) {
                            tip = "<html><p style='width: 250px;'>" + 
                                  value.toString().replace("\n", "<br>") + 
                                  "</p></html>";
                        } else {
                            String text = value.toString();
                            if (text.length() > 15) {
                                tip = "<html><p style='width: 180px;'>" + 
                                      text.replace("\n", "<br>") + 
                                      "</p></html>";
                            }
                        }
                    }
                } catch (RuntimeException e1) {
                }
                
                return tip;
            }
            
            @Override
            public java.awt.Point getToolTipLocation(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int colIndex = columnAtPoint(p);
                
                // For the notes column (index 6), position tooltip to the left if near right edge
                if (colIndex == 6) {
                    int xPos = p.x;
                    int yPos = p.y + 20;
                    
                    // If mouse is in the right half of the window, show tooltip to the left
                    if (xPos > getWidth() / 2) {
                        xPos = xPos - 270; // Shift left by tooltip width + margin
                    }
                    
                    return new java.awt.Point(xPos, yPos);
                }
                
                return null; // Use default position for other columns
            }
        };
        
        loadSessionData();
        
        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(48);
        table.getColumnModel().getColumn(3).setPreferredWidth(117);
        table.getColumnModel().getColumn(4).setPreferredWidth(55);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);
        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        
        table.getColumnModel().getColumn(6).setCellRenderer(new MultiLineTableCellRenderer());
        
        table.setRowHeight(25);
        
        scrollPane.setViewportView(table);
        
        JButton btnExport = new JButton("Export");
        btnExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportData();
            }
        });
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExport.setBackground(new java.awt.Color(173, 216, 230));
        btnExport.setBounds(200, 420, 150, 35);
        frame.getContentPane().add(btnExport);
        
        JButton btnBackToDashboard = new JButton("Back to Dashboard");
        btnBackToDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = new Dashboard();
                dashboard.frame.setVisible(true);
                frame.dispose();
            }
        });
        btnBackToDashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBackToDashboard.setBounds(360, 420, 180, 35);
        frame.getContentPane().add(btnBackToDashboard);
    }
    
    private void exportData() {
        try {
            String filename = LoginPage.currentUsername + "_trainingData.csv";
            File sourceFile = new File(filename);
            
            if (!sourceFile.exists()) {
                JOptionPane.showMessageDialog(frame, 
                    "No training data found to export!", 
                    "Export Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Training Data");
            fileChooser.setSelectedFile(new File(filename));
            
            int userSelection = fileChooser.showSaveDialog(frame);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File destinationFile = fileChooser.getSelectedFile();
                
                Files.copy(sourceFile.toPath(), destinationFile.toPath(), 
                          StandardCopyOption.REPLACE_EXISTING);
                
                JOptionPane.showMessageDialog(frame, 
                    "Training data exported successfully to:\n" + destinationFile.getAbsolutePath(), 
                    "Export Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, 
                "Error exporting data: " + ex.getMessage(), 
                "Export Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSessionData() {
        String[] columnNames = {"Date", "Workout Type", "Weight", 
                                "Rounds Completed", "PSR (%)", 
                                "Duration (min)", "Notes"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(LoginPage.currentUsername + "_trainingData.csv"));
            String line;
            
            while((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                
                String[] row = new String[7];
                row[0] = data.length > 0 ? data[0] : "";
                row[1] = data.length > 1 ? data[1] : "";
                row[2] = data.length > 2 ? data[2] : "";
                row[3] = data.length > 3 ? data[3] : "";
                row[4] = data.length > 4 ? data[4] : "";
                row[5] = data.length > 5 ? data[5] : "";
                row[6] = data.length > 6 ? data[6] : "";
                
                model.addRow(row);
            }
            br.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        table.setModel(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
    }
    
    class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {
        public MultiLineTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            setFont(table.getFont());
            if (value != null) {
                setText(value.toString());
            } else {
                setText("");
            }
            return this;
        }
    }
}