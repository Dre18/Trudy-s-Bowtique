import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import AuthenticationandAuthorization.UserAuth;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * GROUP Members...
 * 
 * 
 * 
 * 
 * 
 *  */

public class SystemDisplay extends JPanel {

    private JButton Close;
    public JButton logIn;
    private JTable table;
    private Color panelColor;

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                SystemDisplay newContentPane = new SystemDisplay();
                    newContentPane.setOpaque(true);
                    JFrame frame = new JFrame("Trudy's Bowtique");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(newContentPane);
                    frame.pack();
                    frame.setVisible(true);

                    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = size.width;
                    int height = size.height;
                    frame.setSize(width, height);
                    frame.setLocationRelativeTo(null);

                } catch (Exception d) {
                    JOptionPane.showInputDialog("System Error");

                }

            }
        });
    }

    /**
     * 
     * The above code is creating a GUI for the main screen with displays the
     * companies logo .
     */
    public SystemDisplay() {

        JPanel CommandPanel = new JPanel();
        JPanel displayPanel = new JPanel();
      
        displayPanel.setPreferredSize(displayPanel.getToolkit().getScreenSize());
        displayPanel.setLayout(null);
        panelColor = new Color(123, 154, 239);
        displayPanel.setBackground(panelColor);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        logIn = new JButton("Login");
        logIn.setBounds(300, 430, 200, 68);
        logIn.setBackground(Color.lightGray);


        Close = new JButton("Close");
        Close.setBounds(800, 430, 200, 68);
        Close.setBackground(Color.lightGray);

        Close.addActionListener(new CloseButtonListener());
        logIn.addActionListener(new LoginButtonListener());
    

        // try {
        //     BufferedImage Trudy = ImageIO.read(new File("Image.png"));

        //     bank = new JLabel(new ImageIcon(Trudy));

        //     bank.setBounds(400, 50, 500, 300);
        //     displayPanel.add(bank);
        // } catch (IOException e) {
        //     e.getMessage();
        // }

        //JLabel welcomeMessage = new JLabel("Welcome to Montgomery Bank");
       // welcomeMessage.setFont(new Font("Verdana", Font.BOLD, 15));
       // welcomeMessage.setBounds(560, 350, 600, 68);

       // displayPanel.add(welcomeMessage);
        
        displayPanel.add(logIn);
        displayPanel.add(Close);

        add(CommandPanel);
        add(displayPanel);
        //getContentPane().add(BorderLayout.CENTER, panel);add(displaypanel);

    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    /**
     * It creates a new account from the data gathered from
     * the Createccount class.
     */
    // private class CreateAccButtonListener implements ActionListener {
    //     public void actionPerformed(ActionEvent f) {
    //         if (f.getSource() == createAcc) {
    //             try {
    //                 AccountListing newContentPane = new AccountListing();
    //                 newContentPane.setOpaque(true);
    //                 CreateAccount frame = new CreateAccount(newContentPane);
    //                 // newContentPane.setOpaque(true);
    //                 frame.setPreferredSize(new Dimension(500, 600));
    //                 frame.setResizable(true);
    //                 frame.pack();
    //                 frame.setVisible(true);

    //             } catch (Exception g) {

    //                 JOptionPane.showMessageDialog(createAcc, "System Error");
    //             }

    //         }
    //     }
    // }

    /**
     * It's a class that creates a new frame when the user clicks the login button.
     * 
     */
    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logIn) {

                try {
                    SystemDisplay newContentPane = new SystemDisplay();
                    UserAuth frame = new UserAuth();
                    newContentPane.setOpaque(true);
                    frame.setTitle("Admin Login");
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