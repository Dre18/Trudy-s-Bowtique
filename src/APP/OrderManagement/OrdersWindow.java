package APP.OrderManagement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class OrdersWindow extends JFrame implements ActionListener{
	
	AddOrder add;
	
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
	
	 
	
	OrdersWindow(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		
		this.add(textOrderNum);
		this.add(textLname);
		this.add(textFname);
		this.add(textContact);
		this.add(textAddress);
		this.add(textOrderItem);
		this.add(textComments);
		this.add(textStatus);
		
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
			//add = new AddOrder();
			
			/*JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setSize(500,500);
			frame.setLayout(new FlowLayout());
			
			
			textOrderNum = new JTextField("Order Number");
			textLname = new JTextField();
			textFname = new JTextField();
			textContact = new JTextField();
			textAddress = new JTextField();
			textOrderItem = new JTextField();
			textComments = new JTextField();
			textStatus = new JTextField();
			
			textOrderNum.setBounds(20,220,100,25);			
			textLname.setBounds(20,250,100,25);
			textFname.setBounds(20,280,100,25);
			textContact.setBounds(20,310,100,25);
			textAddress.setBounds(20,340,100,25);
			textOrderItem.setBounds(20,370,100,25);
			textComments.setBounds(20,400,100,25);
			textStatus.setBounds(20,430,100,25);
			
			frame.add(textOrderNum);
			frame.add(textLname);
			frame.add(textFname);
			frame.add(textContact);
			frame.add(textAddress);
			frame.add(textOrderItem);
			frame.add(textComments);
			frame.add(textStatus);
			
			this.setVisible(true);*/
		}
		
		if(e.getSource()==editRecord) {
			System.out.println("Order Editted");
		}
		
		if(e.getSource()==delRecord) {
			System.out.println("Order Removed");
		}
		
	}

}