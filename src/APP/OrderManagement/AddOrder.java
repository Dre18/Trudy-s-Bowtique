package APP.OrderManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AddOrder {{
	
	FileWriter write;
	//PrintWriter printTo;
	
	//Scanner scantofile = new Scanner(System.in);
	//private void createFile()
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


	/*try {
	     File myObj = new File("Movies.txt");
	     Scanner scantofile = new Scanner(myObj);
	     while (scantofile.hasNextLine()) {
	    	 String data = scantofile.nextLine();
	    	 System.out.println(data);
	    	 }
	     scantofile.close();
	} catch (FileNotFoundException e) {
		System.out.println("An error occurred.");
		e1.printStackTrace();
	}	*/
	

	
	
	
	
	
	//FileWriter writer;
	
/*	public void fileCreate() {
		orderFile = new File("order_database.txt");
		
		if(orderFile.exists()) {
			System.out.println("File Exist!");
			System.out.println("Enter New Order\n");
			System.out.println("Order Number: ");
			int ordnum = scan.nextInt();
			System.out.println("\nCustomer Last Name: ");
			String lname = scan.next();
			System.out.println("\nCustomer First Name: ");
			String fname = scan.next();
			System.out.println("\nContact Number: ");
			String conNum = scan.next();
			
			System.out.println(ordnum + "," + lname + "," + fname + "," + conNum);
		} else {
			System.out.println("File not available!!!");
			
		}
	}*/
	
	
	//writer = new FileWriter("orders.txt");

}
