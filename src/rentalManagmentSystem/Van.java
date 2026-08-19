package rentalManagmentSystem;

class Van extends Vehicle {
    public Van(int vehicleId, String brand, String model, double dailyRate, boolean isAvailable) {
        super(vehicleId, brand, model, dailyRate, isAvailable);
    }

    @Override
    String vehicleType() {
        return "Van";
    }

    @Override
    double discountRate() {
        return 0.15;
    }

    @Override
    int discountDaysLimit() {
        return 15;
    }
}
