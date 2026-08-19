package rentalManagmentSystem;

class Car extends Vehicle {

    public Car(int vehicleId, String brand, String model, double dailyRate, boolean isAvailable) {
        super(vehicleId, brand, model, dailyRate, isAvailable);
    }

    @Override
    String vehicleType() {
        return "Car";
    }

    @Override
    double discountRate() {
        return 0.10;
    }

    @Override
    int discountDaysLimit() {
        return 7;
    }
}
