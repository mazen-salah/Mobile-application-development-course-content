public class example2 {
    
}


/**
 * Represents a car with a specific brand, model, and year.
 */
class Car {
    private String brand;
    private String model;
    private int year;

    /**
     * Constructs a car object with the given brand, model, and year.
     * 
     * @param brand the brand of the car
     * @param model the model of the car
     * @param year the year of the car
     */
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    /**
     * Prints the details of the car, including the brand, model, and year.
     */
    public void printDetails() {
        System.out.println("Make: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }

    /**
     * Starts the car's engine.
     */
    public void startEngine() {
        System.out.println("Engine started");
    }

    /**
     * Stops the car's engine.
     */
    public void stopEngine() {
        System.out.println("Engine stopped");
    }

    /**
     * Returns the brand of the car.
     * 
     * @return the brand of the car
     */
    public String getBrand() {
        return brand;
    }
}
