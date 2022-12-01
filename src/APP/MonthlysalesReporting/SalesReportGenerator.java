package APP.MonthlysalesReporting;
import java.io.*;
import java.util.Scanner;

import javax.swing.text.Document;

public class SalesReportGenerator {
	private static String ON;
	private static String LN;
	private static String FN;
	private static String CN;
	private static String Addr;
	private static String OI;
	private static String Comments;
	private static String Status;
	private static int numm=0;
	private static int count=0;
	
	
	public static void getInfo() {
		numm++;
		
		String temp=Integer.toString(numm);
		try {
			int cost2;
	  		Scanner mReader = new Scanner(new File("OrderList.dat"));//Takes the name of the file that has all the orders,waiting on file to be built in orders class 
	  		Scanner mWriter = new Scanner(new File("SalesReport.txt"));//Creates new file which will only have the sales
	  		FileWriter myWriter = new FileWriter("SalesReport"+temp+".txt");//Will use to write to file
	  		myWriter.write("********MONTHLY REPORT******* \n");
	  		
			while (mReader.hasNextLine()) {
				
	    		String [] mdata = mReader.nextLine().split(" ");
				String ordnum = mdata[0];
				String name = mdata[1].replace("_"," ");
				String status = mdata[2];
				String date = mdata[3];
				String phonenum = mdata[4];
				String addr =mdata[5].replace("_"," ").replace("~","\n\t    ");
				String descrip = mdata[6].replace("_"," ").replace("~","\n\t    ");
				String cost = mdata[7]; 
				// System.out.println(mdata);
	    		if(Status=="Sold") {
	    			count++;
					
	    			String num=Integer.toString(count);
	    			String txt="Sale #: "+num+" Order #: "+ordnum+" by: "+ name + " " + "Purchase: "+ descrip +" Cost: " + cost +"\n";
					cost2 = Integer.parseInt(cost.replace("$", ""));
					
					
	    			myWriter.write(txt);
	    		}
	    		
	    		// APP.NotificationsandEvents.Notification;
	  		}
	  		// int totalCost2 = cost2;
	  		// myWriter.write("Total Monthly Sale: " + totalCost2 );
			myWriter.flush();
	  		mReader.close();
	  		mWriter.close();
	  		myWriter.close();
		} catch (FileNotFoundException e) {
	  		System.out.println("File not found");
	  		e.printStackTrace();
		}
		catch (IOException e) {
	  		System.out.println("An error occurred.");
	  		e.printStackTrace();
		
		}
		catch (Exception o) {
			System.out.println(o.getMessage());
			// e.printStackTrace();
	  
	  }
    }
}
