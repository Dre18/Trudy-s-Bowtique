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
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class MainView extends JPanel {

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

        displayPanel.add(Order);
        displayPanel.add(Stock);

        add(CommandPanel);
        add(displayPanel);
    }

    private class StockButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    private class OrderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Order) {

                try {
                    SystemDisplay newContentPane = new SystemDisplay();
                    APP.AuthenticationandAuthorization.UserAuth frame = new UserAuth();
                    newContentPane.setOpaque(true);
                    frame.setTitle("Sign in");
                    frame.setPreferredSize(new Dimension(500, 600));
                    frame.setResizable(true);
                    frame.pack();
                    frame.setVisible(true);

                } catch (Exception d) {

                }

            }

        }

    }

    
}