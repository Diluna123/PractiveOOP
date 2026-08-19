package rideBookingSystem;


abstract class Customer implements RideService{

    private int cutomerId;
    private String customerName;
    private int distance;

    public Customer(int cutomerId, String customerName, int distance) {
        this.cutomerId = cutomerId;
        this.customerName = customerName;
        this.distance = distance;
    }

    public int getCutomerId() {
        return cutomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public void calculateFare(){
        System.out.println("Your Distance: "+ getDistance()+"Km");
        double ridefee = getRideFeeRate() * getDistance();
        System.out.println("Fee : Rs. " + ridefee);

        if(getDistance() > 20){
            double discount = (ridefee * 10) / 100;
            double newRideFee = ridefee - discount;
            System.out.println("Fee after 10% Discount : Rs. " +  newRideFee);

        }
    }

    abstract double getRideFeeRate();
}

interface RideService{
    void calculateFare();

    void rideType();
}

class BikeRide extends Customer{
    public BikeRide(int cutomerId, String customerName, int distance) {
        super(cutomerId, customerName, distance);
    }

    @Override
    public void rideType() {
        System.out.println("Ride Type : Bike Ride");
    }

    @Override
    double getRideFeeRate() {
        return 100;
    }
}
class CarRide extends Customer{
    public CarRide(int cutomerId, String customerName, int distance) {
        super(cutomerId, customerName, distance);
    }

    @Override
    double getRideFeeRate() {
        return 250;
    }

    @Override
    public void rideType() {
        System.out.println("Ride Type : Car Ride");

    }
}

class LuxuryRide extends Customer{
    public LuxuryRide(int cutomerId, String customerName, int distance) {
        super(cutomerId, customerName, distance);
    }

    @Override
    double getRideFeeRate() {
        return 500;
    }

    @Override
    public void rideType() {
        System.out.println("Ride Type : Luxury Ride");


    }
}

class Main{

    public static  void main (String args []){

        Customer[] customers = {
                new BikeRide(100, "Diluna", 10),
                new CarRide(101, "Sithija ", 30),
                new LuxuryRide(101, "Sulakshana", 20)




        };

        for(Customer cus : customers){
            cus.rideType();
            cus.calculateFare();
            System.out.println("-------------------------");
        }
    }


}