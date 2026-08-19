package rentalManagmentSystem;

interface RentalService {

    void addVehicle(Vehicle vehicle);
    void removeVehicle(int vehicleId);
    void searchVehicle(int vehicleId);
    void rentVehicle(Customer customer, int vehicleId, int days);




}
