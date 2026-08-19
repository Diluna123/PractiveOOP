package practiceDesignPatterns;



abstract class Payament implements PaymentServices{
    @Override
    public void pay( double ammount) {
        System.out.println("Method : "+ paymentType() +" | ammount : "+ ammount);

    }

    abstract String paymentType();
}

interface PaymentServices{
    void pay(double ammount);
}

class CreditCardPayment extends Payament{
    @Override
    String paymentType() {
        return "Credit Card";
    }
}

class CashPayment extends Payament{
    @Override
    String paymentType() {
        return "Cash Payment";
    }
}

class BankTransferPayment extends Payament{
    @Override
    String paymentType() {
        return "BankTransferPayment";
    }
}
class PaypalPayment extends Payament{
    @Override
    String paymentType() {
        return "PaypalPayment";
    }
}

class PaymentFatory{
    public Payament getPamentType(String type){
        if(type.equalsIgnoreCase("card")){
            return new CreditCardPayment();
        }
        if(type.equalsIgnoreCase("cash")){
            return new CashPayment();
        }
        if(type.equalsIgnoreCase("bank")){
            return new BankTransferPayment();
        }
        if(type.equalsIgnoreCase("paypal")){
            return new PaypalPayment();

        }

        return null;
    }
}

class Main{
    public static void main(String[] args) {
        PaymentFatory paymentFatory = new PaymentFatory();
        Payament payament = paymentFatory.getPamentType("card");
        payament.pay(5000);
    }


}

