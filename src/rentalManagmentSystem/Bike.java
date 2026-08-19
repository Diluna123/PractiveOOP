package rentalManagmentSystem;

class Bike extends Vehicle {
    public Bike(int vehicleId, String brand, String model, double dailyRate, boolean isAvailable) {
        super(vehicleId, brand, model, dailyRate, isAvailable);
    }

    @Override
    String vehicleType() {
        return "Bike";
    }

    @Override
    double discountRate() {
        return 0.05;
    }

    @Override
    int discountDaysLimit() {
        return 5;
    }
}
