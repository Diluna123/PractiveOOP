package rentalManagmentSystem;

import java.time.LocalDate;
import java.util.ArrayList;

class RentalManger implements RentalService {

    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<Rental> rentals = new ArrayList<>();

    @Override
    public void addVehicle(Vehicle vehicle) {

        for(Vehicle vh: vehicles){
            if(vh.getVehicleId()== vehicle.getVehicleId()){
                System.out.println("Duplicate Vehicle ID found!!");
                return;
            }

        }
        vehicles.add(vehicle);
        System.out.println("Vehicle Added Success!!");


    }

    private Vehicle findVehicle(int vehicleId){
        for(Vehicle vh: vehicles){
            if(vh.getVehicleId() == vehicleId){
                return vh;
            }

        }
        throw new IllegalArgumentException("Invalid Vehicle ID");

    }

    @Override
    public void removeVehicle(int vehicleId) {
        Vehicle vehicle = null;
        try{
           vehicle = findVehicle(vehicleId);
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        vehicles.remove(vehicle);
        System.out.println("Vehicle Removed Success!!");
    }

    @Override
    public void searchVehicle(int vehicleId) {

        try{
           Vehicle vehicle = findVehicle(vehicleId);
           vehicle.displayVehicleDetails();

        }catch (Exception e){
            System.out.println("Cant find any Vehicle with this vehicle ID");
        }
    }

    @Override
    public void rentVehicle(Customer customer, int vehicleId, int days) {

        try{
            Vehicle vehicle = findVehicle(vehicleId);
            LocalDate date = LocalDate.now();
            if(!vehicle.isAvailable()){
                System.out.println("Vehicle Already Rented!!");
                return;

            }

            int newRentalId  = rentals.size() + 1;

            Rental rental = new Rental(newRentalId, customer, vehicle, date , days );
            Invoice invoice = InvoiceCalculator.calculator(rental);
            InvoicePrinter.print(invoice);
            rentals.add(rental);
            vehicle.setAvailable(false);
            rental.displayRentalDetails();







        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
