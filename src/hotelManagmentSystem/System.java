package hotelManagmentSystem;

import java.util.ArrayList;

abstract class Room{

    private int roomId;
    private int roomNumber;
    private double pricePerNight;
    private boolean isBooked;

    public Room(int roomId, int roomNumber, double pricePerNight) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isBooked = false;

        if(getRoomId() < 0  || getRoomNumber() < 0 || getPricePerNight() <0){
            throw new IllegalArgumentException("Values mus be Positive");
        }


    }

    abstract String roomType();

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

}

interface HotelService{

    void addRoom(Room room);
    void displayRoomDetails();

}
interface RoomService{
    void bookRoom(int roomNumber);

    void checkOut(int roomNumber, int days);




    void searchRoom(int roomNumber);

}



class Hotel implements HotelService, RoomService{
    ArrayList<Room> rooms = new ArrayList<>();
    @Override
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println(room.getRoomNumber()+ " was added successfully");

    }

    @Override
    public void bookRoom(int roomNumber) {
        if(roomNumber < 0){
            System.out.println("Enter Valid Room number");
            return;
        }

        for(Room room:rooms){

            if(room.getRoomNumber() == roomNumber){
                room.setBooked(true);
                System.out.println("===================");
                System.out.println("Room Number :"+roomNumber + " was booked Success!!");
                System.out.println("===================");


            }
        }



    }

    @Override
    public void searchRoom(int roomNumber) {
        for(Room room: rooms){
            if (room.getRoomNumber() == roomNumber){
                System.out.println("Room ID :" + room.getRoomId());
                System.out.println("Room Number : " + room.getRoomNumber());
                System.out.println("Room Type :" + room.roomType());
                System.out.println("IS BOOKED :" + room.isBooked());
                System.out.println("===============================");

            }
        }
    }

    @Override
    public void checkOut(int roomNumber, int days) {

        for(Room room: rooms){
            if(room.getRoomNumber() == roomNumber){
                if(room.isBooked()){
                    double bill = room.getPricePerNight() * days;
                    System.out.println("Total Ammount : "+ bill);
                    room.setBooked(false);
                }else{
                    System.out.println("Didnr Booked this Room "+ roomNumber);
                }
            }
        }



    }

    @Override
    public void displayRoomDetails() {
        for (Room room : rooms) {
            System.out.println("Room ID :" + room.getRoomId());
            System.out.println("Room Number : " + room.getRoomNumber());
            System.out.println("Room Type :" + room.roomType());
            System.out.println("IS BOOKED :" + room.isBooked());
            System.out.println("===============================");
        }
    }
}

class StandardRoom extends Room{
    public StandardRoom(int roomId, int roomNumber, double pricePerNight) {
        super(roomId, roomNumber, pricePerNight);
    }

    @Override
    String roomType() {
        return "Standers";
    }
}

class DeluxeRoom extends Room{
    public DeluxeRoom(int roomId, int roomNumber, double pricePerNight) {
        super(roomId, roomNumber, pricePerNight);
    }

    @Override
    String roomType() {
        return "Deluxe";
    }
}

class SuiteRoom extends Room{
    public SuiteRoom(int roomId, int roomNumber, double pricePerNight) {
        super(roomId, roomNumber, pricePerNight);
    }

    @Override
    String roomType() {
        return "SuiteRoom";
    }
}



class Main{
    public static void main(String[] args) {
        Hotel h = new Hotel();
        h.addRoom(new StandardRoom(0001, 234, 4500));
        h.addRoom(new StandardRoom(004, 540, 3400));
        h.addRoom(new DeluxeRoom(004, 500, 5000));
        h.addRoom(new SuiteRoom(007, 540, 8000));






        h.bookRoom(234);

        h.checkOut(500, 10);






    }
}

