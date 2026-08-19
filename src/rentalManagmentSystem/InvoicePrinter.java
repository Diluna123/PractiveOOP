package rentalManagmentSystem;

class InvoicePrinter {

    public static void print(Invoice invoice){
        System.out.println("============================");
        System.out.println("----------Invoice------------");
        System.out.println("============================");
        System.out.println("Rental Price:"+invoice.getRentalPrice());
        System.out.println("TAX:"+invoice.getTax());
        System.out.println("Discount :"+ invoice.getDiscount());
        System.out.println("Sq Deposit:"+invoice.getSeqDeposit());
        System.out.println("Total :"+ invoice.getTotal());
        System.out.println("=======================");

    }


}
