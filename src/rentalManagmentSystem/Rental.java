package rentalManagmentSystem;

import java.time.LocalDate;

class Rental {
    private int rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private LocalDate rentalDate;
    private int days;


    public Rental(int rentalId, Customer customer, Vehicle vehicle, LocalDate rentalDate, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDate = rentalDate;
        this.days = days;

    }

    public int getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public int getDays() {
        return days;
    }


    void displayRentalDetails(){
        getCustomer().displayCustomerDetails();
        getVehicle().displayVehicleDetails();


    }
}
