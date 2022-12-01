package APP.OrderManagement;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
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
			addRecord = new JMenu("New Order");
			editRecord = new JMenu("Edit Order");
			delRecord = new JMenu("Remove Order");
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
    private class MouseListener{
    public void mouseClicked (MouseEvent m){
         int row = table.getSelectedRow();
         String str = table.getValueAt(row, 0).toString();
         JLabel l = new JLabel(str);
         detailspanel.add(l);
    }
}

public void createAndShowGUI() {
	Frame f=new Frame("ActionListener Example");  
		
		addRecord.setBounds(50,100,60,30);  
		
		f.setSize(400,400);  
		f.setLayout(null);  
		f.setVisible(true);

}


@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource()==sortByOrdNum){

	Collections.sort(orderList, new Comp());
	model.setRowCount(0);
}  

    if (e.getSource()==sortByDeadline){
		Collections.sort(orderList, new CompD2());
		model.setRowCount(0);
		

    }
    if (e.getSource()==sortByCompleted){
		Collections.sort(orderList, new CompD3());
		model.setRowCount(0);
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
        // TODO Auto-generated method stub
        return o1.getDeadline().compareTo((o2.getDeadline()));
    }
}
private class CompD3 implements Comparator<OrdItem>
{
    @Override
    public int compare(OrdItem o1, OrdItem o2) {
        // TODO Auto-generated method stub
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		Date date2 = new  Date();
		try {
			date1= f.parse(o1.getDeadline());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			date2=f.parse(o2.getDeadline());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return date1.compareTo((date2));
    }
}
}

