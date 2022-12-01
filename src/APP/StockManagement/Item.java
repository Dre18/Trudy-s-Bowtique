package APP.StockManagement;

public class Item{
        private String name;
        private int quantity;
    
        
        public Item(String name, int quantity) {
            
    
            this.name = name;
            this.quantity = quantity;
    
        }
    
        
        /** 
         * @return String
         */
        public String getItemName() {
            return name;
        }
    
        
        /** 
         * @return int
         */
        public int getItemQuantity() {
            return quantity;
        }

        
        /** 
         * @param x
         */
        public void changeQuantity(int x){
                this.quantity -= x;
       
    
        }
        
    }
       
