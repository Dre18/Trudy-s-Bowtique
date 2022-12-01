package APP.OrderManagement;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.Date;
public class OrdItem{
        private String name;
        private String[] status = {"Incomplete", "Completed"};
        private String addr;
        private String ordDescrip;
        private String cost;
        private static int count;
        private int ordnum;
        private String phonenum;
       private Date date;
       private String deadline;
        private String status_2;

        public OrdItem(int ordnum, String name, String addr, String date, String status_2, String ordDescrip, String phonenum, String cost) {
            this.ordnum=ordnum;
            this.name = name;
            if (status_2.equals(status[0]) ||status_2.equals(status[1]) ){
                this.status_2 = status_2;
            }
            this.deadline= date;
            this.addr=addr;
            this.ordDescrip=ordDescrip;
            this.phonenum=phonenum;
            this.cost=cost;
    
        }
        public OrdItem(String name, String deadline, String addr, String ordDescrip, String phonenum, String cost) {
            
    
            this.date= new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.deadline= deadline;
            this.ordnum=1 + calcOrdernum("OrderList.dat");
            this.name = name;
            this.addr=addr;
            this.ordDescrip=ordDescrip;
            this.phonenum=phonenum;
            this.cost=cost;
            this.status_2=status[0];

    
        }

        public int calcOrdernum(String pfile){
            java.util.Scanner pscan = null; 
            int i =0;
            try {
                pscan = new java.util.Scanner(new File(pfile));
                while (pscan.hasNext()) {
                    String[] nextLine = pscan.nextLine().split(" ");
                    int num = Integer.parseInt(nextLine[0]);
                    if (num>i){
                        i= num;
                    }
                }
                pscan.close();
             } catch (IOException e) {
                }
            
            return i;
        }
        public OrdItem(String name2, int stockID) {
        }
    
        public String getName() {
            return name;
        }

        public String getAddr() {
            return addr;
        }

        public String getOrdDescrip() {
            return ordDescrip;
        }

        public String getPhonenum() {
            return this.phonenum;
        }

        public String getDeadline() {
            return deadline;
        }

        public String getCost() {
            return cost;
        }
         public int getOrdnum() {
            return ordnum;
        }
        public String getStatus_2() {
            return status_2;
        }

        
    }


