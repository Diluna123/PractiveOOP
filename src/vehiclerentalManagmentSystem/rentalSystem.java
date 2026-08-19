package vehiclerentalManagmentSystem;

import java.util.ArrayList;

abstract class Vehicle{
    private int vehicleId;
    private String vehicleName;
    private double pricePerDay;
    private boolean isRented;

    public Vehicle(int vehicleId, String vehicleName, double pricePerDay) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.pricePerDay = pricePerDay;
        this.isRented = false;

        if(getVehicleId() <= 0){
            throw new IllegalArgumentException("Enter Valid Vehicle ID");
        }
        if(getPricePerDay() < 0){
            throw  new IllegalArgumentException("Price must be Positive");
        }



    }

    public void displayVehicleDetails() {

            System.out.println("-------------------------------------");

            System.out.println("Vehicle ID: "+ getVehicleId());
            System.out.println("Vehicle Name :"+ getVehicleName());
            System.out.println("Vehicle Type :" + vehicleType());
            System.out.println("Insurance Rate :  "+insuranceRate());
            System.out.println("Is Rented :"+  isRented());


    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isRented() {
        return isRented;
    }

    abstract String vehicleType();

    abstract  double insuranceRate();


}
class Car extends Vehicle{
    public Car(int vehicleId, String vehicleName, double pricePerDay) {
        super(vehicleId, vehicleName, pricePerDay);
    }

    @Override
    double insuranceRate() {
        return 0.10;
    }

    @Override
    String vehicleType() {
        return "Car";
    }


}
class Bike extends Vehicle{
    public Bike(int vehicleId, String vehicleName, double pricePerDay) {
        super(vehicleId, vehicleName, pricePerDay);
    }

    @Override
    public void setRented(boolean rented) {
        super.setRented(rented);
    }

    @Override
    String vehicleType() {
        return "Bike";
    }

    @Override
    double insuranceRate() {
        return 0.05;
    }
}

class Van extends Vehicle{
    public Van(int vehicleId, String vehicleName, double pricePerDay) {
        super(vehicleId, vehicleName, pricePerDay);
    }

    @Override
    String vehicleType() {
        return "Van";
    }

    @Override
    double insuranceRate() {
        return 0.15;
    }
}

class BillCalculator{
    public static Bill calculate(Vehicle v, int days){
        double rent  = v.getPricePerDay()* days;
        double insurance = rent* v.insuranceRate();
        double finalTotal = rent+ insurance;
        return new Bill(rent, insurance, finalTotal);
    }

}





class RentalCompany implements RentalService{

    ArrayList<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void addVehicle(Vehicle vehicle) {

        for(Vehicle vh: vehicles){
            if(vh.getVehicleId() == vehicle.getVehicleId()){
                System.out.println("Duplicate Vehicle ID");
                return;
            }
        }


        vehicles.add(vehicle);
        System.out.println(vehicle.getVehicleName() +" Added Success");
    }





    public Vehicle findVehicle(int id){
        for(Vehicle vehicle: vehicles){
            if(vehicle.getVehicleId() == id){
                return vehicle;
            }
        }
        return null;

    }

    @Override
    public void searchVehicle(int id) {
        Vehicle vh = findVehicle(id);
        if(vh != null){
           vh.displayVehicleDetails();

        }else{
            System.out.println("Not Found Vehicle");
        }

    }

    @Override
    public void removeVehicle(int id) {
        Vehicle vh = findVehicle(id);
        if(vh !=null){
            vehicles.remove(vh);
            System.out.println("Vehicle Removed : "+ vh.getVehicleName());
        }else{
            System.out.println("Vehicle Not Found");
        }
    }

    @Override
    public void rentVehicle(int id) {
        Vehicle vh = findVehicle(id);
        if(vh != null){
            if(!vh.isRented()){
                vh.setRented(true);
                System.out.println("------------------");
                System.out.println("Rented Success!! : "+ vh.getVehicleName());
                System.out.println("------------------");

            }else{
                System.out.println("Already rented this Vehicle");
                return;
            }
        }
    }


    @Override
    public void returnVehicle(int id, int days) {
        Vehicle vh = findVehicle(id);
        if(vh != null){
            if(!vh.isRented()){
                System.out.println("This Vehicle isn't Rented");
                return;

            }
            vh.setRented(false);
            System.out.println("Vehicle Returned :"+vh.getVehicleName());
            Bill bill = BillCalculator.calculate(vh, days);
            calculateRantBill(bill);




        }
    }


    private void calculateRantBill(Bill bill) {
        System.out.println("==========================");
        System.out.println("Sub Total : "+bill.getRent());
        System.out.println("Insurance : "+bill.getInsurance());
        System.out.println("Total Bill :"+ bill.getTotal());
        System.out.println("==========================");




    }
}

class Bill{
    private double rent;
    private double insurance;
    private double total;

    public Bill(double rent, double insurance, double total) {
        this.rent = rent;
        this.insurance = insurance;
        this.total = total;
    }

    public double getRent() {
        return rent;
    }

    public double getInsurance() {
        return insurance;
    }

    public double getTotal() {
        return total;
    }
}

interface RentalService {
    void addVehicle(Vehicle vehicle);

    void searchVehicle(int id);
    void removeVehicle(int id);
    void rentVehicle(int id);


    void returnVehicle(int id, int days);





}

class Main{
    public static void main(String[] args) {
        RentalCompany rent = new RentalCompany();

        rent.addVehicle(new Car(001, "FIT", 6000));
        rent.addVehicle(new Bike(002, "Dio", 2300));

        rent.returnVehicle(002, 10);
        rent.rentVehicle(002);
        rent.returnVehicle(002, 10);








    }
}