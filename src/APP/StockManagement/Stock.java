package APP.StockManagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Stock extends JPanel {
    
    private JButton close;
    private JButton update;
    
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private ArrayList<Stock> ilist;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField Item;
    private JTextField Quantity;
    private JButton delete;
    
    private String item_name;
    private int quantity;
  
    public Stock() {
        super(new GridLayout(2, 1));
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        // pnlDisplay.setBounds(150, 300, 15, 30);
        pnlDisplay.setBackground(Color.LIGHT_GRAY);
        ilist = loadStock("StockList.dat");
        String[] columnNames = { "Items", "Quantity", "Critical Level"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(ilist);
        table.setPreferredScrollableViewportSize(new Dimension(50, ilist.size() * 15 + 50));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        Item = new JTextField(10);
        Quantity = new JTextField(10);
        pnlDisplay.setLayout(new GridLayout(4, 0));
        pnlDisplay.add(new JLabel("New Item:"));
        pnlDisplay.add(Item);
        pnlDisplay.add(new JLabel("Amount:"));
        pnlDisplay.add(Quantity);
       
        delete = new JButton("Delete");
        close = new JButton("Close");
        update = new JButton("Update");

        
        close.addActionListener(new CloseButtonListener());
        close.setBackground(Color.lightGray);
        delete.setBackground(Color.red);
        pnlCommand.add(delete, BorderLayout.CENTER);
        pnlCommand.add(update, BorderLayout.CENTER);
        pnlCommand.add(pnlDisplay, BorderLayout.PAGE_START);
        add(pnlCommand);

    }
    private ArrayList<Stock> loadStock(String pfile) {
        Scanner pscan = null;
        ArrayList<Stock> slist = new ArrayList<Stock>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                item_name = nextLine[0];
                quantity = Integer.parseInt(nextLine[1]);
                slist.add(this);

            }

            pscan.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "System Error");
        }
        return slist;
    }

   public void addItem(Stock I) {
        ilist.add(I);
        addToTable(I);

    }

    public int getItemQuantity(){
        return 0;
    }
    
    public String getItemName(){
            return item_name;
    }
    
    public void reduceStock(){
        
    }
    
    public void addToTable(Stock i) {
        String[] item = { i.getItemName(), "" + i.getItemQuantity()};
        model.addRow(item);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Trudy's Bowtique");
        Stock newContentPane = new Stock();
        frame.setPreferredSize(frame.getToolkit().getScreenSize());
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);

    }

    
    

    
    

    
    private void showTable(ArrayList<Stock> ilist2) {
        if (ilist2.size() > 0) {
            for (Stock i : ilist2) {
                {
                    addToTable(i);
                }
            }
        }

    }

    
    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }


}
