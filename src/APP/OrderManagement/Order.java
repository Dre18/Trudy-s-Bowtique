package APP.OrderManagement;
import java.io.File;

import java.io.IOException;
import java.util.Scanner;


import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Order extends JFrame implements ActionListener{
    
    JMenuBar optionBar;
	JMenu addRecord;
	JMenu editRecord;
	JMenu delRecord;
	JMenu sortRecord;
	JMenuItem sortByLastName;
	JMenuItem sortByOrdNum;
	JMenuItem sortByDeadline;
	JMenuItem sortByCompleted;
	JMenuItem sortByIncomplete;
	JTable table;
	JTextField textOrderNum;
	JTextField textLname;
	JTextField textFname;
	JTextField textContact;
	JTextField textAddress;
	JTextField textOrderItem;
	JTextField textComments;
	JTextField textStatus;

    public Order(){
       OrderFrame();
    }



    
	void AddOrder(){
		try {
			File orderFile = new File("OrdersList.txt");
			if (orderFile.createNewFile()) {
				System.out.println("File created: " + orderFile.getName());
			} else {
				System.out.println("File already exists.");
				Scanner scantofile = new Scanner(orderFile);
				while (scantofile.hasNextLine()) {
					String data = scantofile.nextLine();
					System.out.println(data);
					}
				scantofile.close();
			}
		} catch (IOException e1) {
			System.out.println("An error occurred.");
			e1.printStackTrace();
		}
	}


	void OrderFrame (){
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(500,500);
			this.setLayout(new FlowLayout());
			
			
			
			optionBar = new JMenuBar();
			
			addRecord = new JMenu("New Order");
			editRecord = new JMenu("Edit Order");
			delRecord = new JMenu("Remove Order");
			
			addRecord.addActionListener(this);
			editRecord.addActionListener(this);
			delRecord.addActionListener(this);
			
			sortRecord = new JMenu("Sort Orders");	
			
			sortByLastName = new JMenuItem("Last Name");
			sortByOrdNum = new JMenuItem("Order Number");
			sortByDeadline = new JMenuItem("Deadline");
			sortByCompleted = new JMenuItem("Completed Order(s)");
			sortByIncomplete = new JMenuItem("Incomplete Order(s)");
			
			
			
			
			String[] columnNames = {"Order Number", "Last Name", "First Name", "Contact Number", "Address", "Order Item(s)", "Comments", "Status"};
			
			Object[][] data = {
					{"1", "Hylton", "Joseph", "876-879-1811", "Trench Town, Kingston 2", "1x Key Ring, 3x Bottles", "The Key Ring must be a J shape.", "Pending"},
					{"2", "Dacres", "Jennia", "876-859-7711", "Campion Town, St. Elizabeth", "10x T-Shirt, 10x Design Pillows", "The T-Shirt must have my name.", "Pending"},
					{"3", "Johnson", "Deallia", "876-899-1548", "August Town, Kingston 5", "3x Key Rings, 2x Bottles", "The Key Ring must have the letters D,E and F shapes.", "Pending"},
					
			};
			
			table = new JTable(data, columnNames);
			table.setPreferredScrollableViewportSize(new Dimension(1500,50));
			table.setFillsViewportHeight(true);
			
			JScrollPane scrollPane = new JScrollPane(table);
			
			
			
			
			add(scrollPane);
			
			sortRecord.add(sortByLastName);
			sortRecord.add(sortByOrdNum);
			sortRecord.add(sortByDeadline);
			sortRecord.add(sortByCompleted);
			sortRecord.add(sortByIncomplete);
			
			
			
			optionBar.add(addRecord);
			optionBar.add(editRecord);
			optionBar.add(delRecord);
			optionBar.add(sortRecord);
			
			// this.add(textOrderNum);
			// this.add(textLname);
			// this.add(textFname);
			// this.add(textContact);
			// this.add(textAddress);
			// this.add(textOrderItem);
			// this.add(textComments);
			// this.add(textStatus);
			
			this.setJMenuBar(optionBar);
			
			this.setVisible(true);
		}
		
		public void newWindow() {
			JOptionPane.showMessageDialog(null,"Order Added", "Add Order", JOptionPane.PLAIN_MESSAGE);
			this.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==addRecord) {
				System.out.println("New Order Added");
	}
	}
}
