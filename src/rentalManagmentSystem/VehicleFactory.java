package rentalManagmentSystem;

class VehicleFactory {

    public static Vehicle createVehicle(String type, int id, String brand, String model, double dailyRate, boolean isAvailable){

        switch (type.toLowerCase()){
            case ("car"):
                return new Car(id,brand,model,dailyRate, isAvailable);
            case("bike"):
                return new Bike(id,brand,model,dailyRate, isAvailable);
            case("van"):
                return new Van(id,brand,model,dailyRate, isAvailable);

        }
        throw new IllegalArgumentException("Invalid Vehicle type!!");
    }
}
