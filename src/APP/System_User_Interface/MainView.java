package APP.System_User_Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import APP.AuthenticationandAuthorization.UserAuth;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class MainView extends JPanel {

    public JButton report;
    public JButton Stock;
    public JButton Order;
    private JTable table;
    private Color panelColor;

    

   public MainView() {

        JPanel CommandPanel = new JPanel();
        JPanel displayPanel = new JPanel();
        displayPanel.setPreferredSize(displayPanel.getToolkit().getScreenSize());
        displayPanel.setLayout(null);
        panelColor = new Color(123, 154, 239);
        displayPanel.setBackground(panelColor);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        Order = new JButton("Order");
        Order.setBounds(300, 430, 200, 68);
        Order.setBackground(Color.lightGray);
        Order.addActionListener(new OrderButtonListener());
        
        Stock = new JButton("Stock");
        Stock.setBounds(700, 430, 200, 68);
        Stock.setBackground(Color.lightGray);
        Stock.addActionListener(new StockButtonListener());
        
        report = new JButton (" Monthly Sales Report");
        report.setBounds(1000, 430, 200, 68);
        report.setBackground(Color.lightGray);
        report.addActionListener(new ReportButtonListener());

        displayPanel.add(Order);
        displayPanel.add(Stock);
        displayPanel.add(report);

        add(CommandPanel);
        add(displayPanel);
    }
    
    private class ReportButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}  

    private class StockButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            APP.StockManagement.Stock stock = new APP.StockManagement.Stock();
            
            stock.createAndShowGUI();
            SystemDisplay newContentPane = new SystemDisplay();
            newContentPane.setOpaque(false);
            
            
        }

    }

    private class OrderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Order) {

                try {
                    
                    SystemDisplay newContentPane = new SystemDisplay();
                    MainView mainView = new MainView();
                    APP.OrderManagement.Order frame = new APP.OrderManagement.Order();
                    newContentPane.setOpaque(false);
                    mainView.setOpaque(false);
                    frame.setTitle("Order");
                    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setResizable(true);
                    frame.pack();
                    frame.setVisible(true);

                } catch (Exception d) {

                }

            }

        }

    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Trudy's Bowtique");
        MainView newContentPane = new MainView();
        frame.setPreferredSize(frame.getToolkit().getScreenSize());
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);

    }

    
}