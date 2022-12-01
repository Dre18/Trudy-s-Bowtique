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
	  		Scanner mReader = new Scanner(new File("OrderList.dat"));//Takes the name of the file that has all the orders,waiting on file to be built in orders class 
	  		Scanner mWriter = new Scanner(new File("SalesReport.txt"));//Creates new file which will only have the sales
	  		FileWriter myWriter = new FileWriter("SalesReport"+temp+".txt");//Will use to write to file
	  		myWriter.write("********MONTHLY REPORT******* \n");
	  		
			while (mReader.hasNextLine()) {
	    		String [] mdata = mReader.nextLine().split("-");
	    		ON = mdata[0];
	    		LN = mdata[1];
	    		FN = mdata[2];
	    		CN = mdata[3];
	    		Addr = mdata[4];
	    		OI = mdata[5];
	    		Comments = mdata[6];
	    		Status = mdata[7];
	    		if(Status=="Sold") {
	    			count++;
	    			String num=Integer.toString(count);
	    			String txt="Sale #: "+num+" Order #: "+ON+" by"+FN+" "+LN+" Purchase: "+OI+"\n";
	    			myWriter.write(txt);
	    		}
	    		
	    		// APP.NotificationsandEvents.Notification;
	  		}
	  		//System.out.println(mdata);
	  		
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
    }
}
