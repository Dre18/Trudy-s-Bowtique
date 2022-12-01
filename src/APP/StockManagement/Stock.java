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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
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

import APP.NotificationsandEvents.Notification;
import APP.System_User_Interface.MainView;

public class Stock extends JPanel {
    
    private JButton update;
    private JButton addItem;
    private JButton delete;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private static ArrayList<Item> ilist;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField item;
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
        item = new JTextField(10);
        Quantity = new JTextField(10);
        pnlDisplay.setLayout(new GridLayout(4, 0));
        pnlDisplay.add(new JLabel("New Item:"));
        pnlDisplay.add(item);
        pnlDisplay.add(new JLabel("Amount:"));
        pnlDisplay.add(Quantity);
        addItem = new JButton("Add Item");
        addItem.addActionListener(new AddItemButtonListener());
        addItem.setBackground(Color.GREEN);
        pnlDisplay.add(new JLabel(" "));
        pnlDisplay.add(addItem);

        delete = new JButton("Delete Item");
        update = new JButton("Update Item");

        update.addActionListener(new UpdateButtonListener());
        delete.addActionListener(new DeleteButtonListener());
        update.setBackground(Color.ORANGE);
        delete.setBackground(Color.red);
        pnlCommand.add(update, BorderLayout.CENTER);
        pnlCommand.add(delete, BorderLayout.CENTER);
        pnlCommand.add(pnlDisplay, BorderLayout.PAGE_START);
        add(pnlCommand);

//         if((Quantity.getText().isEmpty() == false))
//         {
//         // filereader(Scanner file);
    

        // Scanner pscan;
        // try {
        //     pscan = new Scanner(new File(file));
        //     while (pscan.hasNext()) {
        //         int sum = 0;
        //         String[] nextLine = pscan.nextLine().split(" ");
        //         if (nextLine[0].isEmpty() == false ){
        //             // continue;
        //             String name = nextLine[0];
        //             int quantity = Integer.parseInt(nextLine[1]);
        //             sum = sum + quantity;
        //             Item item = new Item(name, sum);
        //             ilist.add(item);
        //             int a = quantity/2;
        //             int d = quantity - sum;
        //             if(d<= a)
        //             {
        //                APP.NotificationsandEvents.Notification n = new  Notification() ;
        //             }
        //         }
        
        //     }
        //     pscan.close();
        // } catch (FileNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
            
        
    

// }

}
    private ArrayList<Item> loadStock(String pfile){
        Scanner pscan = null;
        ArrayList<Item> ilist = new ArrayList<Item>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                if (nextLine[0]==" " ){
                    continue;
                }
                else{
                String name = nextLine[0];
                int quantity = Integer.parseInt(nextLine[1]);
                 Item item = new Item(name, quantity);
                 ilist.add(item);
                }
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
    public static ArrayList<Item> getItems(){
        return ilist;
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
        JFrame frame = new JFrame("Stock");
        Stock newContentPane = new Stock();
        frame.setPreferredSize(frame.getToolkit().getScreenSize());
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);

    }
    public int createJOptionpane(String str){
        int n = JOptionPane.showConfirmDialog(pnl, str,"Confirmation",JOptionPane.YES_NO_OPTION);
        return n;
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
            if (e.getSource() == update)
            {
                try
                {
                    

                    int irow = table.getSelectedRow();
                if (item.getText().isEmpty() == false) {
                    for (Item i : ilist) {
                        if (i.getItemName().equals(table.getModel().getValueAt(irow, 1))) {
                            Item  item = new Item(i.getItemName(), i.getItemQuantity());
                            ilist.remove(i);
                            ilist.add(item);
                            model.setRowCount(0);
                            showTable(ilist);
                            
                            FileReader File = new FileReader(new File(file));
                            BufferedReader br = new BufferedReader(File);
                            String temp = br.readLine();
                            while (temp != null) {
                            temp = br.readLine();
                            System.out.println(temp);

                            FileInputStream fis = new FileInputStream(file);
                            try(ObjectInputStream objectstream = new ObjectInputStream(fis)){

                                objectstream.readObject();
                            }
                       
                        }
                    }
                }
            }
        }
            catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(pnl, "Quantity Invalid");
            } catch (Exception l) {
                JOptionPane.showMessageDialog(pnl,"Please Close Application\nIf problem persists");
            }
            
        }
}                    
    }

    
    private class AddItemButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==addItem){
                try{
                if (item.getText().isEmpty()){
                JOptionPane.showMessageDialog(pnl, "Incomplete Fields");
                }
                else{
                    String new_item = item.getText().trim().replace(" ", "_");
                    item.setText("");
                    int item_quantity = Integer.parseInt(Quantity.getText().trim());
                    Quantity.setText("");
                    Item I = new Item(new_item, item_quantity);
                    
                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        pw.println(I.getItemName() + " " + I.getItemQuantity());
                        pw.close();
                        bw.close();
                        fw.close();
                    }
                }
                catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(pnl, "Quantity Invalid");
                } catch (IOException f) {
                    JOptionPane.showMessageDialog(pnl, "Something Went Wrong");
                }
                catch (Exception l) {
                    JOptionPane.showMessageDialog(pnl,"Please Close Application\nIf problem persists");
                }
                model.setRowCount(0);
                ilist=loadStock(file);
                showTable(ilist);
                
            }
        }

    }
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            if (a.getSource()==delete){
                int row = table.getSelectedRow();
                String val ="";


               if (createJOptionpane("Are you sure you want to delete this item")==0){
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
   
   
}


