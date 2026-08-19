package rentalManagmentSystem;

import java.awt.image.TileObserver;

class InvoiceCalculator {
    private final static double TAX_RATE = 0.05;
    private final static double SEQ_DEPOSIT = 5000;

    static Invoice calculator(Rental rental){
        double discount =0;



        double rentalPrice = rental.getVehicle().getDailyRate() * rental.getDays();
        double tax = rentalPrice * TAX_RATE;

        if(rental.getDays() > rental.getVehicle().discountDaysLimit()){
            discount = rentalPrice * rental.getVehicle().discountRate();


        }
        double total = rentalPrice + tax + SEQ_DEPOSIT - discount;


        return new Invoice(rentalPrice, tax, discount, SEQ_DEPOSIT, total);











    }





}
