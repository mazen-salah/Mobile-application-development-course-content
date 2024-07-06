import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Item {
    String name;
    int quantity;
    boolean availability;
    
    Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.availability = quantity > 0;
    }
}

public class CartApp {

    public static void main(String[] args) {
        Map<String, Item> cart = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("Enter item name (or 'exit' to stop): ");
            String itemName = scanner.nextLine();
            
            if (itemName.equalsIgnoreCase("exit")) {
                break;
            }
            
            System.out.print("Enter item quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume the remaining newline
            
            Item item = new Item(itemName, quantity);
            cart.put(itemName, item);
            
            // Check if quantity is zero and update availability
            if (item.quantity == 0) {
                item.availability = false;
            }
        }
        
        // Display cart items and their availability status
        System.out.println("Cart Items:");
        for (Item item : cart.values()) {
            System.out.println("Item: " + item.name + ", Quantity: " + item.quantity + ", Available: " + item.availability);
        }

        scanner.close();
    }
}
