package APP.OrderManagement;
import java.awt.LayoutManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ViewportLayout;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.table.DefaultTableModel;

import APP.StockManagement.Stock;

import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;    
import java.awt.event.*;

public class Order extends JFrame implements ActionListener{
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel toppanel;
    private JFrame ordWindow;
    private JTextArea detailspanel;
    private JPanel bottompanel;
    private static final String file= "OrderList.dat";
    private ArrayList<OrdItem> orderList;
    private JMenuBar optionBar;
	
	JMenuItem addRecord;
	JMenuItem editRecord;
	JMenuItem delRecord;
	JMenu Options;
    JMenu sortRecord;
    JMenuItem sortByLastName;
	JMenuItem sortByOrdNum;
	JMenuItem sortByDeadline;
	JMenuItem sortByCompleted;
	JMenuItem sortByIncomplete;
    public Order() {
        

        toppanel=new JPanel();  
        ordWindow = new JFrame();
       	ordWindow.setBackground(Color.pink);

        toppanel.setBounds(0,0,1500,800);    
        
        toppanel.setLayout(new GridLayout(0,2));
        detailspanel = new JTextArea();
        detailspanel.setLayout(new FlowLayout());
        detailspanel.setBackground(Color.LIGHT_GRAY); 
        optionBar = new JMenuBar();   
        
        	sortRecord = new JMenu("Sort By ");
            sortByOrdNum = new JMenuItem("Order Number");
            sortByDeadline = new JMenuItem("Deadline");
            sortByCompleted = new JMenuItem("Completed Order(s)");
            sortByIncomplete = new JMenuItem("Incomplete Order(s)");

			sortRecord.add(sortByOrdNum);
			sortRecord.add(sortByDeadline);
			sortRecord.add(sortByCompleted);
			sortRecord.add(sortByIncomplete);
			Options = new JMenu("Option");
			addRecord = new JMenuItem("New Order");
			editRecord = new JMenuItem("Edit Order");
			delRecord = new JMenuItem("Remove Order");
        	Options.add(addRecord);
			Options.add(editRecord);
			Options.add(delRecord);
            optionBar.add(Options);
			optionBar.add(sortRecord);

			sortRecord.addActionListener(this);
            addRecord.addActionListener(this);
			editRecord.addActionListener(this);
			delRecord.addActionListener(this);
        this.setJMenuBar(optionBar);
        this.add(toppanel, BorderLayout.CENTER); 
                // f.setSize(400,400);    
                // f.setLayout(null);  
                 Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = size.width;
                    int height = size.height;
                    this.setSize(width, height);
                    this.setLocationRelativeTo(null);  
                this.setVisible(true);
                orderList = loadItems(file);
                String[] columnNames = { "Order No.", "Customer's Name", "Status of Order", "DeadLine" };
                model = new DefaultTableModel(columnNames, 0);
                table = new JTable(model);
                showTable(orderList);
                table.setPreferredScrollableViewportSize(new Dimension(500, orderList.size() * 15 + 50));
                table.setFillsViewportHeight(true);
                scrollPane = new JScrollPane(table);
                toppanel.add(scrollPane);
                toppanel.add(detailspanel); 
                detailspanel.setMargin(new InsetsUIResource(20, 20, 20, 20));
                detailspanel.setText("Click on an order to see its details displayed here.");
                table.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        Point point = e.getPoint();
                        int row = table.rowAtPoint(point);
                        String str = table.getValueAt(row, 0).toString();
                        detailspanel.setText(str);

                    }
                }); 
    }

    public ArrayList<OrdItem> loadItems(String pfile){
        Scanner pscan = null;
        ArrayList<OrdItem> orderList = new ArrayList<OrdItem>();

        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String data=pscan.nextLine();
                String[] nextLine = data.split(" ");
                int ordnum = Integer.parseInt(nextLine[0]);
                String name = nextLine[1].replace("_"," ");
                String status = nextLine[2];
				String date = nextLine[3];
                String phonenum=nextLine[4];
                String addr = nextLine[5].replace("_"," ");
                String descrip = nextLine[6].replace("_"," ");
                String cost = nextLine[7];                
			OrdItem O = new OrdItem(ordnum, name, addr, date, status,descrip,  phonenum, cost) ;
        orderList.add(O);
                

            }

            pscan.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "System Error");
        }
        return orderList;
    }

    public void addToTable(OrdItem i) {
        String[] item = { ""+i.getOrdnum(),  i.getName(), i.getStatus_2(), i.getDeadline()};
        model.addRow(item);
    }

    private void showTable(ArrayList<OrdItem> orderList) {
        if (orderList.size() > 0) {
            for (OrdItem i : orderList) {
                {
                    addToTable(i);
                }
            }
        }

    }

public void createAndShowGUI() {
	Frame f=new Frame("ActionListener Example");  
		
		addRecord.setBounds(50,100,60,30);  
		
		f.setSize(400,400);  
		f.setLayout(null);  
		f.setVisible(true);

}

private class Orderpanel extends JFrame implements ActionListener{
     
    // Components of the Form
    private Container a;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel mobile;
    private JTextField t_mob;

    private JLabel Descrp;
    private JTextArea t_Descrp;

    private JLabel cost;
    private JTextField t_cost;

    private JLabel dline;
    private JTextField t_dline;

    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JLabel add;
    private JTextArea tadd;
    private JCheckBox term;
    private JButton sub;
    private JButton Done;
    private JCheckBox tout;
    private JLabel res;
    private JTextArea resadd;
 

    public Orderpanel(){
        setTitle("New Order Form");
        setBounds(300, 10, 900, 900);
        setResizable(false);
        a = getContentPane();
        JPanel b = new JPanel();
        b.setLayout(new GridLayout(0,2));
        JPanel c = new JPanel();
        c.setLayout(null);
        JPanel d = new JPanel();
        d.setLayout(null);

        title = new JLabel("NEW ORDER");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(1200, 30);
        a.add(title, BorderLayout.NORTH);
 
        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);
 
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);
 
        mobile = new JLabel("Mobile (xxx-xxx-xxxx)");
        mobile.setFont(new Font("Arial", Font.PLAIN, 18));
        mobile.setSize(200, 20);
        mobile.setLocation(100, 150);
        c.add(mobile);
 
        t_mob = new JTextField();
        t_mob.setFont(new Font("Arial", Font.PLAIN, 15));
        t_mob.setSize(150, 20);
        t_mob.setLocation(300, 150);
        c.add(t_mob);

        dline = new JLabel("Deadline (dd/mm/yyyy)");
        dline.setFont(new Font("Arial", Font.PLAIN, 18));
        dline.setSize(200, 20);
        dline.setLocation(100, 200);
        c.add(dline);
 
        t_dline = new JTextField();
        t_dline.setFont(new Font("Arial", Font.PLAIN, 15));
        t_dline.setSize(100, 20);
        t_dline.setLocation(300, 200);
        c.add(t_dline);
 
        add = new JLabel("Address");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(100, 250);
        c.add(add);
 
        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(250, 50);
        tadd.setLocation(200, 250);
        tadd.setLineWrap(true);
        c.add(tadd);

        Descrp = new JLabel("Order Description");
        Descrp.setFont(new Font("Arial", Font.PLAIN, 18));
        Descrp.setSize(200, 40);
        Descrp.setLocation(50, 320);
        c.add(Descrp);
 
        t_Descrp = new JTextArea();
        t_Descrp.setFont(new Font("Arial", Font.PLAIN, 15));
        t_Descrp.setSize(250, 150);
        t_Descrp.setLocation(220, 320);
        t_Descrp.setLineWrap(true);
        c.add(t_Descrp);

        cost = new JLabel("Cost:");
        cost.setFont(new Font("Arial", Font.PLAIN, 20));
        cost.setSize(100, 20);
        cost.setLocation(100, 500);
        c.add(cost);
 
        t_cost = new JTextField();
        t_cost.setFont(new Font("Arial", Font.PLAIN, 15));
        t_cost.setSize(85, 30);
        t_cost.setLocation(200, 500);
        c.add(t_cost);
 
        Done = new JButton("Done");
        Done.setFont(new Font("Arial", Font.PLAIN, 15));
        Done.setSize(100, 20);
        Done.setLocation(270, 600);
        Done.addActionListener(this);
        c.add(Done);
    
        
        JLabel res  = new JLabel("reduce stock by");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(300, 25);
        res.setLocation(100, 100);
        d.add(res);

 
        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(200, 100);
		
        d.add(resadd);
         
        b.add(c);
        b.add(d);
        a.add(b, BorderLayout.CENTER);
        setVisible(true);
		
    
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() ==Done){
			FileWriter f;
			try {
				f = new FileWriter(file, true);
			
                    BufferedWriter b = new BufferedWriter(f);
                    PrintWriter w = new PrintWriter(b);
					OrdItem o = new OrdItem(tname.getText(), t_dline.getText(), tadd.getText(), t_Descrp.getText(), t_mob.getText(), t_cost.getText());
                    w.println(o.getOrdnum() + " " + o.getName().replace(" ", "_") + " " + o.getStatus_2() + " "
                            + o.getDeadline() + " " +o.getPhonenum()+" "+ o.getAddr().replace(" ", "_").replace("\n", "_") + " " + o.getOrdDescrip().replace(" ","_").replace("\n", "_") + " " + o.getCost());
                    
					w.flush();
					w.close();
					b.close();
					f.close();
                    orderList.add(o);
					this.setVisible(false);
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "Something went wrong");;
				}
				model.setRowCount(0);
                orderList=loadItems(file);
                showTable(orderList);
		}
		
	}
	
}


@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource()==addRecord){
		Orderpanel o =new Orderpanel();
	}
	if (e.getSource()==sortByOrdNum){

	Collections.sort(orderList, new Comp());
	model.setRowCount(0);
	showTable(orderList);
}  

    if (e.getSource()==sortByDeadline){
		Collections.sort(orderList, new CompD3());
		model.setRowCount(0);
		showTable(orderList);
		

    }
    if (e.getSource()==sortByCompleted){
		Collections.sort(orderList, new CompD2());
		model.setRowCount(0);
		showTable(orderList);
    }
	
   
}

private class Comp implements Comparator<OrdItem>
{
    @Override
    public int compare(OrdItem o1, OrdItem o2) {
        // TODO Auto-generated method stub
        return o1.getOrdnum()- (o2.getOrdnum());
    }
}
private class CompD2 implements Comparator<OrdItem>
{

    @Override
    public int compare(OrdItem o1, OrdItem o2) {
        return o1.getStatus_2().compareTo(o2.getStatus_2());
    }
}
private class CompD3 implements Comparator<OrdItem> 
{
    @Override
    public int compare(OrdItem o1, OrdItem o2) {
        // TODO Auto-generated method stub
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date date1;
		Date date2;
		int n;
		
			// date1= f.parse(o1.getDeadline());
		
			// date2=f.parse(o2.getDeadline());

		return 0;	
		
		//return date1.compareTo((date2));
    }
}
}

