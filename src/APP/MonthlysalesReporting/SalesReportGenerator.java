package APP.MonthlysalesReporting;
import java.io.*;
import java.util.Scanner;

public class SalesReportGenerator {
	private String ON;
	private String LN;
	private String FN;
	private String CN;
	private String Addr;
	private String OI;
	private String Comments;
	private String Status;
	private static int numm=0;
	private int count=0;
	
	
	public void getInfo() {
		numm++;
		String temp=Integer.toString(numm);
		try {
	  		Scanner mReader = new Scanner(new File("unNamed.txt"));//Takes the name of the file that has all the orders,waiting on file to be built in orders class 
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
	    		
	    		
	  		}
	  		//System.out.println(mdata);
	  		
	  		mReader.close();
	  		mWriter.close();
	  		myWriter.close();
		} catch (FileNotFoundException e) {
	  		System.out.println("An error occurred.");
	  		e.printStackTrace();
		}
		catch (IOException e) {
	  		System.out.println("An error occurred.");
	  		e.printStackTrace();
		
		}
    }
}
