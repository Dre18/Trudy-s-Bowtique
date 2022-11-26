package StockManagement;
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
    private JTextField Total;
    private JButton delete;

  
    public Stock() {
        super(new GridLayout(2, 1));
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        // pnlDisplay.setBounds(150, 300, 15, 30);
        pnlDisplay.setBackground(Color.LIGHT_GRAY);
        ilist = loadItems("StockList.dat");
        String[] columnNames = { "Items", "Quantity","Total Stock"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(ilist);

        table.setPreferredScrollableViewportSize(new Dimension(500, ilist.size() * 15 + 50));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        add(scrollPane);

        Item = new JTextField(10);
        Quantity = new JTextField(10);
        Total = new JTextField(10);
        
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
        // sortLastName.addActionListener(new AddSortName());
        // sortLastName.setBackground(Color.lightGray);
        // sortByAcc.addActionListener(new AddSortAcc());
        // sortByAcc.setBackground(Color.lightGray);
        // update.addActionListener(new update());
        // update.setBackground(Color.green);
        // delete.addActionListener(new removePerson());
        delete.setBackground(Color.red);

       
        pnlCommand.add(delete, BorderLayout.CENTER);

        pnlCommand.add(update, BorderLayout.CENTER);

        pnlCommand.add(pnlDisplay, BorderLayout.PAGE_START);
        add(pnlCommand);

    }

    
    private ArrayList<StockManagement.Item> loadItems(String string) {
        return null;
    }


    private void addToStock(Item i) {
        String[] item = { "" + i.getItem()};
        model.addRow(item);
    }

    void createAndShowGUI() {
        JFrame frame = new JFrame("Trudy's Bowtique");
        Stock newContentPane = new Stock();
        frame.setPreferredSize(frame.getToolkit().getScreenSize());
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);

    }

    
    public void addItem(Item I) {
        ilist.add(I);
        addToStock(I);

    }

    
    private ArrayList<Item> loadStock(String pfile) {
        Scanner pscan = null;
        ArrayList<Item> ilist = new ArrayList<Item>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0];
                int stockID = Integer.parseInt(nextLine[1]);
                Item I = new Item(name,stockID);
                ilist.add(I);

            }

            pscan.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "System Error");
        }
        return ilist;
    }

    
    private void showTable(ArrayList<Item> ilist) {
        if (ilist.size() > 0) {
            for (Item i : ilist) {
                {
                    addToStock(i);
                }
            }
        }

    }

    /**
     * The CloseButtonListener class implements the ActionListener interface, which
     * means that it must
     * have an actionPerformed method.
     * 
     * The actionPerformed method is called when the user clicks the close button.
     * 
     * The actionPerformed method simply exits the program.
     */
    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    // private class removeItem implements ActionListener {
    //     public void actionPerformed(ActionEvent k) {
    //         if (k.getSource() == delete) {
    //             int i = table.getSelectedRow();
    //             for (Item i : ilist) {
    //                 if (i.getItem() == Integer.parseInt(table.getValueAt(i, 3).toString()))
    //                     ilist.remove(p);
    //                 model.setRowCount(0);
    //                 showTable(ilist);
    //             }
    //             String value = table.getModel().getValueAt(i, 4).toString();

    //             try {
    //                 FileReader fr = new FileReader("Bankaccount.dat");
    //                 BufferedReader br = new BufferedReader(fr);

    //                 Scanner pscan = new Scanner(new File("Bankaccount.dat"));
    //                 while (pscan.hasNext()) {
    //                     String[] nextLine = pscan.nextLine().split(" ");
    //                     if (nextLine[6].equals(value)) {

    //                     }
    //                     br.close();
    //                 }
    //             }

    //             catch (IOException e) {
    //             }
    //         }

    //     }
    // }

    /**
     * It's a GUI that allows the user to update a record in a table.
     * </code>
     */
    // private class update implements ActionListener {
    //     public void actionPerformed(ActionEvent n) {
    //         if (n.getSource() == update) {
    //             int i = table.getSelectedRow();
    //             if (Name.getText().isEmpty() == false) {
    //                 for (Person p : Alist) {
    //                     if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
    //                         Person U = new Person(Name.getText(), p.getAddress(), p.getTRN(), p.getOccupation(),
    //                                 p.getMonthlySalary(), p.getDOB(), p.getAccountType(), p.getBankBranch());
    //                         Alist.remove(p);
    //                         Alist.add(U);
    //                         model.setRowCount(0);
    //                         showTable(Alist);
    //                     }
    //                 }
    //             }

    //             if (Address.getText().isEmpty() == false) {
    //                 for (Person p : Alist) {
    //                     if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
    //                         Person U = new Person(p.getName(), Address.getText(), p.getTRN(), p.getOccupation(),
    //                                 p.getMonthlySalary(), p.getDOB(), p.getAccountType(), p.getBankBranch());
    //                         Alist.remove(p);
    //                         Alist.add(U);
    //                         model.setRowCount(0);
    //                         showTable(Alist);
    //                     }

    //                 }
    //             }

    //             if (Occupation.getText().isEmpty() == false) {
    //                 for (Person p : Alist) {
    //                     if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
    //                         Person U = new Person(p.getName(), p.getAddress(), p.getTRN(), Occupation.getText(),
    //                                 p.getMonthlySalary(), p.getDOB(), p.getAccountType(), p.getBankBranch());
    //                         Alist.remove(p);
    //                         Alist.add(U);
    //                         model.setRowCount(0);
    //                         showTable(Alist);
    //                     }

    //                 }
    //             }
    //             if (MonthlySalary.getText().isEmpty() == false) {
    //                 for (Person p : Alist) {
    //                     if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
    //                         Person U = new Person(p.getName(), p.getAddress(), p.getTRN(), p.getOccupation(),
    //                                 Double.parseDouble(MonthlySalary.getText()), p.getDOB(), p.getAccountType(),
    //                                 p.getBankBranch());
    //                         Alist.remove(p);
    //                         Alist.add(U);
    //                         model.setRowCount(0);
    //                         showTable(Alist);
    //                     }

    //                 }
    //             }
    //             if (DOB.getText().isEmpty() == false) {
    //                 for (Person p : Alist) {
    //                     if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
    //                         String[] DOB1 = DOB.getText().split("/");
    //                         int[] dob = new int[DOB1.length];
    //                         for (int k = 0; k < DOB1.length; k++) {
    //                             int s = Integer.parseInt(DOB1[k]);
    //                             dob[k] = s;
    //                         }
    //                         Person U = new Person(p.getName(), p.getAddress(), p.getTRN(), p.getOccupation(),
    //                                 p.getMonthlySalary(), dob, p.getAccountType(), p.getBankBranch());
    //                         Alist.remove(p);
    //                         Alist.add(U);
    //                         model.setRowCount(0);
    //                         showTable(Alist);
    //                     }

    //                 }
    //             }
    //             if (AccountTYpe.getText().isEmpty() == false) {
    //                 for (Person p : Alist) {
    //                     if (p.getName().equals(table.getModel().getValueAt(i, 1))) {
    //                         Person U = new Person(p.getName(), p.getAddress(), p.getTRN(), p.getOccupation(),
    //                                 p.getMonthlySalary(), p.getDOB(), AccountType.valueOf(AccountTYpe.getText()),
    //                                 p.getBankBranch());
    //                         Alist.remove(p);
    //                         Alist.add(U);
    //                         model.setRowCount(0);
    //                         showTable(Alist);
    //                     }

    //                 }
    //             }

    //         }

    //     }
    // }

    /**
     * It's a class that implements an ActionListener and Comparator.
     * It sorts the members of the bank in order of last name.
     */
    // private class AddSortName implements ActionListener, Comparator<Person> {

    //     public void actionPerformed(ActionEvent m) {
    //         if (m.getSource() == sortLastName) {
    //             // Collections.reverse(Alist);
    //             model.setRowCount(0);
    //             Collections.sort(Alist, new AddSortName());
    //             showTable(Alist);

    //         }
    //     }

    //     public int compare(Person p1, Person p2) {
    //         return p1.getLastName().compareTo(p2.getLastName());
    //     }

    // }

    /**
     * It's a class that implements an ActionListener and Comparator.
     * It sorts the members of the bank in account number order.
     */
    // public class AddSortAcc implements ActionListener, Comparator<AccountDetails> {

    //     public void actionPerformed(ActionEvent n) {
    //         if (n.getSource() == sortByAcc) {
    //             model.setRowCount(0);
    //             Collections.sort(Alist, new AddSortAcc());
    //             showTable(Alist);

    //         }
    //     }

    //     public int compare(AccountDetails a1, AccountDetails a2) {
    //         return a1.getAccountNO() - a2.getAccountNO();
    //     }
    // }

    /**
     * It's a class that implements ActionListener and has a method that creates a
     * new Receipt object
     * when the receipt button is clicked.
     */
    // private class ViewReceipt implements ActionListener {
    //     public void actionPerformed(ActionEvent R) {
    //         if (R.getSource() == receipt) {

    //             AccountListing AL = new AccountListing();
    //             Receipt x = new Receipt(0, AL);
    //         }
    //     }

    // }
}
