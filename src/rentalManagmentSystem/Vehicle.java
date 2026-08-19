package rentalManagmentSystem;

abstract  class Vehicle {

    private int vehicleId;
    private String brand;
    private String model;
    private double dailyRate;
    private boolean isAvailable;


    public Vehicle(int vehicleId, String brand, String model, double dailyRate, boolean isAvailable) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.isAvailable = isAvailable;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    void displayVehicleDetails(){
        System.out.println("Vehicle ID:"+getVehicleId());
        System.out.println("Brand:"+getBrand());
        System.out.println("Model:"+getModel());
        System.out.println("Vehicle Type:"+vehicleType());
        System.out.println("Is Available :"+isAvailable());

    }

    abstract String vehicleType();
    abstract double discountRate();
    abstract int discountDaysLimit();


}


