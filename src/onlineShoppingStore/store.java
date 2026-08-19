package onlineShoppingStore;

abstract class Product implements OrderService{
    private int productId;
    private String productName;
    private int quantity;
    private double unitPrice;

    private static final double DISCOUNT_RATE = 0.15;
    private static final double BULK_DISCOUNT_RATE = 0.05;
    private static final double MAX_DISCOUNT_LIMIT = 100000;
    private static final double MAX_BULK_DISCOUNT_LIMIT =5;


    public Product(int productId, String productName, int quantity, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;



        if(getQuantity() <=0){
            throw new IllegalArgumentException("Product quantity must be positive");
        } else if (getUnitPrice()<0) {
            throw new IllegalArgumentException("Unit Price must be Positive");

        }


    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    abstract double taxRate();
    abstract double shippingCharge();
    abstract String ProductCategory();

    void displayProductDetails(){
        System.out.println("Product ID:"+ getProductId());
        System.out.println("Product Name :"+getProductName());
        System.out.println("Unit Price:"+getUnitPrice());
        System.out.println("Quantity : "+getQuantity());
        System.out.println("Product Category :"+ProductCategory());
    }


    @Override
    public boolean isFreeShipping() {
        return false;
    }

    @Override
    public void calculateBill() {
        double subTotal = getQuantity() * getUnitPrice();
        double tax = subTotal * taxRate();





        double shipping = shippingCharge();
        double finalBill = subTotal + tax +shipping;

        double discount = 0;
        double bulkDiscount = 0;

        System.out.println("Subtotal :" +subTotal);
        System.out.println("Tax :"+ tax);
        System.out.println("Shipping :" + shipping);

        if(subTotal > MAX_DISCOUNT_LIMIT){
            discount = subTotal * DISCOUNT_RATE;
            finalBill = finalBill - discount;

        }
        System.out.println("Discount :"+ discount);


        if(getQuantity() >MAX_BULK_DISCOUNT_LIMIT ){
            bulkDiscount = subTotal * BULK_DISCOUNT_RATE;
            System.out.println("Bulk Order");
            System.out.println("Bulk Order Discount :"+ bulkDiscount);
            finalBill = finalBill - bulkDiscount;


        }


        System.out.println("Final Total :"+finalBill);

    }
}

interface OrderService{

    void calculateBill();
    void shippingMethod();
    boolean isFreeShipping();


}

class Electronics extends Product{
    public Electronics(int productId, String productName, int quantity, double unitPrice) {
        super(productId, productName, quantity, unitPrice);
    }

    @Override
    double shippingCharge() {
        return 500;
    }

    @Override
    double taxRate() {
        return 0.15;
    }

    @Override
    public void shippingMethod() {
        System.out.println("Shipping Method : Courier");

    }

    @Override
    String ProductCategory() {
        return "Electronics";
    }
}

class Clothing extends Product{
    public Clothing(int productId, String productName, int quantity, double unitPrice) {
        super(productId, productName, quantity, unitPrice);
    }

    @Override
    String ProductCategory() {
        return "Clothing";
    }

    @Override
    double shippingCharge() {
        return 250;
    }

    @Override
    double taxRate() {
        return 0.05;
    }

    @Override
    public void shippingMethod() {
        System.out.println("Shipping Method: Standard Delivery");
    }
}

class Book extends Product{
    public Book(int productId, String productName, int quantity, double unitPrice) {
        super(productId, productName, quantity, unitPrice);
    }

    @Override
    double shippingCharge() {
        return 150;
    }

    @Override
    double taxRate() {
        return 0;
    }

    @Override
    String ProductCategory() {
        return "Book";
    }

    @Override
    public void shippingMethod() {
        System.out.println("Shipping Method: Postal Service");


    }
}

class Main{

    public static void main (String [] args){
        Product [] products= {
                new Electronics(001, "Laptop", 1, 130000),
                new Clothing(002, "T-shirt", 5, 2000),
                new Book(003, "Java Programming Book", 6, 1200),





        };
        for(Product p:products){
            p.displayProductDetails();
            p.calculateBill();
            p.shippingMethod();
            System.out.println("--------------------------");
        }


    }


}

