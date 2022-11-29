package APP.StockManagement;

public class Item{
        private String name;
        private int stockID;
    
        
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
       
