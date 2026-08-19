package practiceLSP;


abstract class Payment implements CheckOutService{


    @Override
    public void pay(double ammmount) {

        System.out.println("Payment Method: "+ paymentType() + " | Payment Success :" + ammmount);

    }

    abstract String paymentType();
}

interface CheckOutService{
    void pay(double ammount);
}

class CreditCardPayment extends Payment{
    @Override
    String paymentType() {
        return "Credit Card";
    }
}

class CashPayment extends Payment{
    @Override
    String paymentType() {
        return "Cash Payment";
    }
}
class BankTransferPayment extends Payment{
    @Override
    String paymentType() {
        return "Bank Transfer";
    }
}

class  Main{
    public static void main(String[] args) {
        Payment payment = new CashPayment();
        payment.pay(5000);
    }
}