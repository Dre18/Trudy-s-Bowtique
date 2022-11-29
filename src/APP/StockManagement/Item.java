package APP.StockManagement;

public class Item{
        private String name;
        private int stockID;
    
        // A constructor that takes in the parameters and sets them to the variables.
        public Item(String name,int stockID) {
            super();
    
            this.name = name;
            this.stockID = stockID;
    
        }
    
        public String getItem() {
            return name;
        }
    
        public int getStockID() {
            return stockID;
        }

    
    
        public String toString() {
            String str = "";
            return str;
        }
    
        
        
    }
       
