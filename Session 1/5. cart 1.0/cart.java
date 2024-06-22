/**
 * The `Category` enum represents the category of the item.
 * It can have values A, B, C, or D.
 * an enum is a special "class" that represents a group of constants (unchangeable variables).
 * To create an enum, use the enum keyword (instead of class or interface), and separate the constants with a comma.
 */
enum Category {A, B, C, D}


/**
 * The `cart` class represents an item in a shopping cart.
 * It contains information such as the name, quantity, availability, price, and category of the item.
 */
public class cart {
    String name;
    int quantity;
    boolean isAvailable;
    float price;
    Category category;
    
    public static void main(String[] args) {
        // Create a new cart item called `cart` 
        // note that the class name is uppercase (Cart), while the object name is lowercase (cart)
        cart cart = new cart();
        
        // Set the properties of the cart item
        cart.name = "Item 1";
        cart.quantity = 2;
        cart.isAvailable = true;
        cart.price = 10.0f;
        cart.category = Category.A;

        // Print the details of the cart item
        System.out.println("Name: " + cart.name);
        System.out.println("Quantity: " + cart.quantity);
        System.out.println("Is available: " + cart.isAvailable);
        System.out.println("Price: " + cart.price);
        System.out.println("Category: " + cart.category);

    }
}