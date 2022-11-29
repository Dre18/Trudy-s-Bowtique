package APP.StockManagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private JButton addItem;
    private JButton delete;

    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private ArrayList<Item> ilist;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField Item;
    private JTextField Quantity;
    private JPanel pnl;
   
    private static final String file= "StockList.dat";
 
  
    public Stock() {
        super(new GridLayout(2, 1));
        pnl=this;
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        // pnlDisplay.setBounds(150, 300, 15, 30);
        pnlDisplay.setBackground(Color.LIGHT_GRAY);
        ilist = loadStock(file);
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
        addItem = new JButton("Add Item");
        addItem.addActionListener(new AddItemButtonListener());
        addItem.setBackground(Color.GREEN);
        pnlDisplay.add(new JLabel(" "));
        pnlDisplay.add(addItem);

        delete = new JButton("Delete Item");
        close = new JButton("Close");
        update = new JButton("Update Item");
        
        
        
        
        update.addActionListener(new UpdateButtonListener());
        delete.addActionListener(new DeleteButtonListener());
        close.addActionListener(new CloseButtonListener());
         
       
        update.setBackground(Color.ORANGE);
        close.setBackground(Color.lightGray);
        delete.setBackground(Color.red);
        
        pnlCommand.add(update, BorderLayout.CENTER);
        pnlCommand.add(delete, BorderLayout.CENTER);
        pnlCommand.add(close, BorderLayout.CENTER);
        pnlCommand.add(pnlDisplay, BorderLayout.PAGE_START);
        add(pnlCommand);

    }
    private ArrayList<Item> loadStock(String pfile){
        Scanner pscan = null;
        ArrayList<Item> ilist = new ArrayList<Item>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0];
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


    
    private void showTable(ArrayList<Item> ilist) {
        if (ilist.size() > 0) {
            for (Item i : ilist) {
                {
                    addToTable(i);
                }
            }
        }
    }
    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    private class AddItemButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            }
        }

    
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            if (a.getSource()==delete){
                int row = table.getSelectedRow();
                String val ="";
                for (Item i : ilist) {
                    if (i.getItemName().equals(table.getValueAt(row, 0))){
                        val=i.getItemName();
                        removeRecord(val);
                        ilist.remove(i);
                        model.setRowCount(0);
                        showTable(ilist);
                        break;
               
                    }   
                }
            }
        }
        public void removeRecord(String val){
            String tempfile = "temp.dat";
            String currentline;
            File oldfile= new File(file);
            File newfile = new File(tempfile);
            try {
                FileWriter fw = new FileWriter(tempfile, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                
                while ((currentline =br.readLine()) != null) {
                    String[] data = currentline.split(" ");
                    if (!(data[0].equals(val))) {
                         pw.println(currentline);
                    }
                    
                }
                pw.flush();
                pw.close();
                br.close();
                fr.close();
                fw.close();
                bw.close();

                oldfile.delete();
                File temp = new File(file);
                newfile.renameTo(temp);
            }

            catch (IOException IO) {
            }
        }
    }
    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }
   
    }


