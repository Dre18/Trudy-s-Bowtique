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
    private ArrayList<Item> ilist;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField Item;
    private JTextField Quantity;
    private JButton delete;
    
 
  
    public Stock() {
        super(new GridLayout(2, 1));
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        // pnlDisplay.setBounds(150, 300, 15, 30);
        pnlDisplay.setBackground(Color.LIGHT_GRAY);
        ilist = loadStock("StockList.dat");
        String[] columnNames = { "Items", "Quantity"};
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
    private ArrayList<Item> loadStock(String pfile){
        Scanner pscan = null;
        ArrayList<Item> ilist = new ArrayList<Item>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNextLine()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0];
                System.out.print("something went wrong");
                int quantity = Integer.parseInt(nextLine[1]);
                Item item = new Item(name, quantity);
                ilist.add(item);

            }

            pscan.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "System Error");
        }
        return ilist;
    }

   public void addItem(Item I) {
        ilist.add(I);
        addToTable(I);

    }
    
    public void reduceStock(String item_name, int c){
        //reduces the stock qauntity based on order made
        for (Item i: ilist){
            if (item_name==i.getItemName() ){
            int retval= i.getItemQuantity() - c;
            i.changeQuantity(retval);
            }
        }
    }
    
    public void addToTable(Item i) {
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


    
    private void showTable(ArrayList<Item> ilist2) {
        if (ilist2.size() > 0) {
            for (Item i : ilist2) {
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
