public class ERyder {
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;

    private int bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    private int totalUsageInMinutes;
    private double totalFare;
    }

    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.LINKED_ACCOUNT = "guest_user";
        this.LINKED_PHONE_NUMBER = "00000000000"

        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;

        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }
    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven,
                  String linkedAccount, String linkedPhoneNumber) {
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;

        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;

        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }
    
    public void ride() {
        if (this.isAvailable && this.batteryLevel > 0) {
            System.out.println("The bike is available for riding!");
        } else {
            System.out.println("The bike is not available for riding!");
        }
    }

    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Is Available: " + isAvailable );
        System.out.println("Total KM Driven: " + kmDriven + " km");
        System.out.println("------------------------");
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Invalid battery level! Must be between 0 and 100. Set to 0 by default.");
            this.batteryLevel = 0;
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }

    public int getTotalUsageInMinutes(){
        return totalUsageInMinutes;
    }
    public double getTotalFare(){
        return totalFare;
    }

    private double calculateFare(int usageInMinutes) {
        double fare = BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
        this.totalFare = fare;
        this.totalUsageInMinutes = usageInMinutes;
        return fare;
    }

    public void printRideDetails(int usageInMinutes) {
        double totalFare = calculateFare(usageInMinutes);
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Linked Phone Number: " + LINKED_PHONE_NUMBER);
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Usage in Minutes: " + usageInMinutes + " minutes");
        System.out.println("Total Fare: " + totalFare + " EUR");
        System.out.println("===============================\n");
    }