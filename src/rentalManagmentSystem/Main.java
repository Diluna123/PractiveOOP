package rentalManagmentSystem;

class Main {

    public static void main(String[] args) {

        RentalManger rentalManger = new RentalManger();


        rentalManger.addVehicle(VehicleFactory.createVehicle("car", 01, "Honda", "FIT",7000, true));
        Customer customer = new Customer(001, "Diluna", "200425502467", "0764393083");

        rentalManger.rentVehicle(customer, 01, 8);


    }

}
