public class Main {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder(1000, 80, true, 57);
        ERyder bike2 = new ERyder(1000, 75, true, 120, "user_123", "1234567890");

        System.out.println("--- Bike 1 Ride Details (10 minutes) ---");
        bike1.printRideDetails(10);
        System.out.println("--- Bike 2 Ride Details (20 minutes) ---");
        bike2.printRideDetails(20); 

        System.out.println("--- Test calling calculateFare() ---");
        System.out.println("Error Tip: calculateFare() is private, can't call it directly in Main!");
        System.out.println("Correct Way: Call it inside the ERyder class (e.g., in printRideDetails()).");
    }
}