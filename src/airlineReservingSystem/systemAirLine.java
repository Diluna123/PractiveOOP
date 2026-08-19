package airlineReservingSystem;

import java.util.ArrayList;
import java.util.function.BinaryOperator;


abstract class Flight{


    private int flightId;
    private int flightNumber;
    private String destination;
    private double price;
    private int availableSeats;

    


    public Flight(int flightId, int flightNumber, String destination, double price, int availableSeats) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.price = price;
        this.availableSeats = availableSeats;

    }

    public void reservingSeat(){
        availableSeats--;
    }
    public void releaseSeat(){
        availableSeats++;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getFlightId() {
        return flightId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public double getPrice() {
        return price;
    }



    void displayFlightDetails(){
        System.out.println("Flight ID: "+ getFlightId());
        System.out.println("Flight Number: "+ getFlightNumber());
        System.out.println("Flight Destination: "+ getDestination());
        System.out.println("Price : "+ getPrice());

        System.out.println("-------------------");
        System.out.println("Baggage Limit : "+baggageLimit());
        System.out.println("Flight Type :"+ flightType());
        System.out.println("Meal Type :"+mealType());

    }


    abstract String flightType();
    abstract double baggageLimit();
    abstract String mealType();


}

class Passenger{
    private int passengerId;
    private String passengerName;

    public Passenger(String passengerName, int passengerId) {
        this.passengerName = passengerName;
        this.passengerId = passengerId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }
}

class DomesticFlight extends Flight{


    public DomesticFlight(int flightId, int flightNumber, String destination, double price, int availableSeats) {
        super(flightId, flightNumber, destination, price, availableSeats);
    }

    @Override
    String flightType() {
        return "Domestic Flight";
    }

    @Override
    double baggageLimit() {
        return 20;
    }

    @Override
    String mealType() {
        return "Snack";
    }


}
class InternationalFlight extends Flight{
    public InternationalFlight(int flightId, int flightNumber, String destination, double price, int availableSeats) {
        super(flightId, flightNumber, destination, price, availableSeats);
    }

    @Override
    String flightType() {
        return "International Flight";
    }

    @Override
    double baggageLimit() {
        return 30;
    }

    @Override
    String mealType() {
        return "Full Meal";
    }


}
class BusinessFlight extends Flight{
    public BusinessFlight(int flightId, int flightNumber, String destination, double price, int availableSeats) {
        super(flightId, flightNumber, destination, price, availableSeats);
    }

    @Override
    String flightType() {
        return "Business Flight";
    }

    @Override
    double baggageLimit() {
        return 40;
    }

    @Override
    String mealType() {
        return "Premium Meal";
    }


}

interface AirlineService{
    void addFlight(Flight flight);
    void searchFlight(int flightId);
    void removeFlight(int flightId);


}


class Bill{
    private double price;
    private double tax;
    private double total;

    public Bill(double price, double tax, double total) {
        this.price = price;
        this.tax = tax;
        this.total = total;
    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    void printBill(){
        System.out.println("=================================");
        System.out.println("Price:"+getPrice());
        System.out.println("Tax:"+ getTax());
        System.out.println("Total :"+getTotal());
        System.out.println("=================================");



    }
}

class BillCalculator{


    public Bill calculate(Flight flight){
        double price = flight.getPrice();
        double tax= price * 0.10;
        double total = price+ tax;

        return new Bill(price, tax, total);
    }



}

class Booking {

    private Passenger passenger;
    private Flight flight;
    private Bill bill;
    private String seatNumber;

    public Booking(Passenger passenger, Flight flight, Bill bill, String seatNumber) {
        this.passenger = passenger;
        this.flight = flight;
        this.bill = bill;
        this.seatNumber = seatNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Bill getBill() {
        return bill;
    }

    public String getSeatNumber() {
        return seatNumber;
    }



}

interface PassengerService{
    void bookSheet(Passenger passenger, int flightNumber, String seatNumber);
    void cancelBooking(int flightNumber);

}






class Airline implements AirlineService, PassengerService {

    ArrayList<Flight> flights = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    BillCalculator calculator = new BillCalculator();


    @Override
    public void addFlight(Flight flight) {
        flights.add(flight);
    }


    private Flight findFlight(int flightId){
        for(Flight flight: flights){
            if(flight.getFlightId() == flightId || flight.getFlightNumber() == flightId){
                return flight;

            }
        }
        throw new IllegalArgumentException("Cant find Flight");



    }

    @Override
    public void searchFlight(int flightId) {
        Flight fl = findFlight(flightId);
        fl.displayFlightDetails();

    }

    @Override
    public void removeFlight(int flightId) {
        flights.remove(findFlight(flightId));
    }

    @Override
    public void bookSheet(Passenger passenger, int flightNumber, String seatNumber) {
        Flight flight = findFlight(flightNumber);

        if(flight.getAvailableSeats() <= 0){
            System.out.println("no Available Seats");
            return;
        }
        flight.reservingSeat();

        Bill bill = calculator.calculate(flight);

        Booking booking = new Booking(passenger, flight, bill, seatNumber);

        bookings.add(booking);

        System.out.println("==========================");
        System.out.println("Booking Success");
        System.out.println("Passenger : " + passenger.getPassengerName());
        System.out.println("Flight : " + flight.getFlightNumber());
        System.out.println("Seat : " + seatNumber);
        System.out.println("==========================");

        bill.printBill();

    }

    @Override
    public void cancelBooking(int flightNumber) {



    }
}

class FlightFactory{

    public static Flight createFlight(String type, int id, int flightNumber, String destination, double price, int availableSeats){
        return switch (type.toLowerCase()) {
            case ("domestic") -> new DomesticFlight(id, flightNumber, destination, price, availableSeats);
            case ("international") -> new InternationalFlight(id, flightNumber, destination, price, availableSeats);
            case ("business") -> new BusinessFlight(id, flightNumber, destination, price, availableSeats);
            default -> throw new IllegalArgumentException("Invalid Flight Type");
        };
    }

}

class Main{
    public static void main(String[] args) {
        Airline airline = new Airline();

        airline.addFlight(FlightFactory.createFlight("domestic", 1, 1001, "Colombo", 15000, 20));
        airline.addFlight(FlightFactory.createFlight("international", 2, 1002, "Dubai", 35000, 50));

        System.out.println("==============================");
        Passenger passenger = new Passenger("Diluna", 01);

        airline.bookSheet(passenger, 1001, "A1");
        System.out.println("==================");

        airline.searchFlight(1001);

    }
}