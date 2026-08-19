package hotelResurvationSystem;

import java.util.ArrayList;
import java.time.LocalDate;

abstract class Room{

    private int roomNumber;
    private boolean isBooked;



    public Room(int roomNumber, boolean isBooked) {
        this.roomNumber = roomNumber;
        this.isBooked = isBooked;


    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    void displayRoomDetails(){
        System.out.println("Room Number :"+getRoomNumber());
        System.out.println("Is Booked"+ isBooked());
        System.out.println("Room Price:"+roomPrice());
        System.out.println("Room type :"+roomType());
    }


    abstract double roomPrice();

    abstract String roomType();


}

class StandardRoom extends Room{
    public StandardRoom(int roomNumber, boolean isBooked) {
        super(roomNumber, isBooked);
    }

    @Override
    double roomPrice() {
        return 5000;
    }

    @Override
    String roomType() {
        return "Standard Room";
    }
}
class DeluxeRoom extends Room{
    public DeluxeRoom(int roomNumber, boolean isBooked) {
        super(roomNumber, isBooked);
    }

    @Override
    double roomPrice() {
        return 10000;
    }

    @Override
    String roomType() {
        return "Deluxe Room";
    }
}
class SuiteRoom extends  Room{
    public SuiteRoom(int roomNumber, boolean isBooked) {
        super(roomNumber, isBooked);
    }

    @Override
    double roomPrice() {
        return 8000;
    }

    @Override
    String roomType() {
        return "Suite Room";
    }
}

interface HotelService{
    void addRoom(Room room);
    void removeRoom(int roomNumber);
    void searchRoom(int roomNumber);

}

class Hotel implements HotelService{

    ArrayList<Room> rooms = new ArrayList<>();

    public Room findRoom(int roomNumber){
        for(Room rm: rooms){
            if(rm.getRoomNumber() == roomNumber){
                return rm;
            }

        }
        throw new IllegalArgumentException("Invalid Room number");
    }

    @Override
    public void addRoom(Room room) {
        for(Room rm: rooms){
            if(rm.getRoomNumber() ==  room.getRoomNumber()){
                System.out.println("Duplicate Room Number found!!!");
                return;
            }

        }
        rooms.add(room);
        System.out.println("Room Added Success");

    }

    @Override
    public void removeRoom(int roomNumber) {
        Room room = findRoom(roomNumber);
        if(room.isBooked()){
            System.out.println("Booked Room Can not  be Removed");
            return;
        }
        rooms.remove(room);

    }

    @Override
    public void searchRoom(int roomNumber) {
        try{
            Room room = findRoom(roomNumber);
            room.displayRoomDetails();


        }catch (Exception e){
            System.out.println("Cant find any room with this room number");

        }

    }
}

interface GuestService{
    void bookRoom(Guest guest, int roomNumber, int days);
    void cancelBooking(int roomNumber);
}
class HotelManager implements GuestService{
    ArrayList<Booking> bookings = new ArrayList<>();
    InvoiceCalculator invoiceCalculator = new InvoiceCalculator();
    InvoicePrinter printer = new InvoicePrinter();
    private Hotel hotel;

    public HotelManager(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void bookRoom(Guest guest, int roomNumber, int days) {
        Room room = null;
        try{
            room = hotel.findRoom(roomNumber);

        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        LocalDate currentDate = LocalDate.now();

        if(room.isBooked()){
            System.out.println("Room Already Booked");
            return;
        }
        room.setBooked(true);


        Booking booking = new Booking(room, guest, days, currentDate);
        bookings.add(booking);
        System.out.println("Room Booked :");
        booking.displayBookingDetails();
        Invoice invoice = invoiceCalculator.calculate(booking);
        printer.print(invoice);






    }
    private Booking findBooking(int roomNumber){
        for(Booking bk: bookings){
            if(bk.getRoom().getRoomNumber() == roomNumber){
                return bk;
            }
        }
        throw new IllegalArgumentException("Can not find any booking");
    }

    @Override
    public void cancelBooking(int roomNumber) {
        Booking booking = findBooking(roomNumber);
        bookings.remove(booking);
        booking.getRoom().setBooked(false);
        System.out.println("Booking Canceled");



    }
}

class Invoice{
    private double price;
    private double tax;
    private double discount;
    private double total;

    public Invoice(double price, double tax, double discount, double total) {
        this.price = price;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }
}

class InvoiceCalculator{

    final static double TAX_RATE = 0.05;
    final static double DISCOUNT_RATE = 0;


    public Invoice calculate(Booking booking){
        double price = booking.getRoom().roomPrice() * booking.getDays();
        double tax = price * TAX_RATE;
        double discount = price * DISCOUNT_RATE;
        double total = price + tax - discount;

        return new Invoice(price, tax, discount, total );






    }

}

class InvoicePrinter{

    public void print(Invoice invoice){
        System.out.println("=================================");
        System.out.println("--------------INVOICE-------------");
        System.out.println("=================================");
        System.out.println("Price For Days :"+ invoice.getPrice());
        System.out.println("TAX : " +invoice.getTax());
        System.out.println("Discount :"+ invoice.getDiscount());
        System.out.println("Total :"+ invoice.getTotal());
        System.out.println("=================================");




    }

}


class Guest{
    private int guestId;
    private String guestName;
    private String nic;
    private String contact;

    public Guest(int guestId, String guestName, String nic, String contact) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.nic = nic;
        this.contact = contact;
    }

    public int getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getNic() {
        return nic;
    }

    public String getContact() {
        return contact;
    }

    void displayGuestDetails(){
        System.out.println("Guest ID:"+getGuestId());
        System.out.println("Guest Name:"+ getGuestName());
        System.out.println("NIC:"+getNic());
        System.out.println("Contact :"+ getContact());

    }
}

class Booking{

    private Room room;
    private Guest guest;
    private  int days;
    private LocalDate bookedDate;

    public Booking(Room room, Guest guest, int days, LocalDate bookedDate) {
        this.room = room;
        this.guest = guest;
        this.days = days;
        this.bookedDate = bookedDate;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public int getDays() {
        return days;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }
    void displayBookingDetails(){
        System.out.println("Room Number  :"+ room.getRoomNumber());
        System.out.println("Guest Name : "+ guest.getGuestName());
        System.out.println("Nic:"+ guest.getNic());
        System.out.println("Contact :"+ guest.getContact());
        System.out.println("Booking Date :"+ getBookedDate());

    }
}

class RoomFactory{

    public static Room createRoom(String type, int roomNumber){
        return switch (type.toLowerCase()) {
            case ("stand") -> new StandardRoom(roomNumber, false);
            case ("deluxe") -> new DeluxeRoom(roomNumber, false);
            case ("suit") -> new SuiteRoom(roomNumber, false);
            default -> throw new IllegalArgumentException("Invalid room type");
        };

    }



}

class GuestFactory{

    public static Guest createguest( int id, String name, String nic, String contact){
        return new Guest(id, name, nic, contact);
    }

}

class Main{
    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        hotel.addRoom(RoomFactory.createRoom("stand", 3));
        HotelManager manager = new HotelManager(hotel);
        Guest guest = GuestFactory.createguest(001, "Diluna", "200425502467", "0764393083");
        Guest guest1 = new Guest(002, "Sithija", "200425667876", "0776545678");


        manager.bookRoom(guest, 3, 5);
        manager.bookRoom(guest1, 3, 5);










    }
}