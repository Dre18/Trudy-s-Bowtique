package APP.StockManagement;

public class Item{
        private String name;
        private int quantity;
    
        
        public Item(String name, int quantity) {
            
    
            this.name = name;
            this.quantity = quantity;
    
        }
    
        public String getItemName() {
            return name;
        }
    
        public int getItemQuantity() {
            return quantity;
        }

        public void changeQuantity(int x)
                this.quantity -= x;
       
    
        
        
    }
       
