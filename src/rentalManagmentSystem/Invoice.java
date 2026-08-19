package rentalManagmentSystem;

class Invoice {

    private double rentalPrice;
    private double tax;
    private  double discount;
    private  double seqDeposit;
    private double total;

    public Invoice(double rentalPrice, double tax, double discount, double seqDeposit, double total) {
        this.rentalPrice = rentalPrice;
        this.tax = tax;
        this.discount = discount;
        this.seqDeposit = seqDeposit;
        this.total = total;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public double getTax() {
        return tax;
    }

    public double getDiscount() {
        return discount;
    }

    public double getSeqDeposit() {
        return seqDeposit;
    }

    public double getTotal() {
        return total;
    }
}
