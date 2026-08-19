package eCom_OrderManagmentSystem;


import java.util.ArrayList;

abstract class Product{
    private int productId;
    private String productName;
    private double price;
    private int stockQuantity;
    private String category;

    public Product(int productId, String productName, double price, int stockQuantity, String category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void reduceQty(int qty){

        if(stockQuantity >= qty){
            int newQty = getStockQuantity() - qty;
            setStockQuantity(newQty);


        }





    }
    public void increaseQty(int qty){
        int newQty = getStockQuantity()+ qty;
        setStockQuantity(newQty);

    }

    public String getProductName() {
        return productName;
    }

    public int getProductId() {
        return productId;
    }

    void displayProductDetails(){
        System.out.println("Product ID:"+getProductId());
        System.out.println("Product Name :" +getProductName());
        System.out.println("Price:"+getPrice());
        System.out.println("Stock Quantity:"+getStockQuantity());
        System.out.println("Category :"+getCategory());
        System.out.println("Product type:"+productType());

    }
    abstract double discountRate();
    abstract String productType();


}

class ElectronicsProduct extends Product{
    public ElectronicsProduct(int productId, String productName, double price, int stockQuantity, String category) {
        super(productId, productName, price, stockQuantity, category);
    }

    @Override
    double discountRate() {
        return 0.10;
    }

    @Override
    String productType() {
        return "Electronics";
    }
}
class ClothingProduct extends Product{

    public ClothingProduct(int productId, String productName, double price, int stockQuantity, String category) {
        super(productId, productName, price, stockQuantity, category);
    }

    @Override
    double discountRate() {
        return 0.2;
    }

    @Override
    String productType() {
        return "Cloths";
    }
}
class GroceryProduct extends  Product{
    public GroceryProduct(int productId, String productName, double price, int stockQuantity, String category) {
        super(productId, productName, price, stockQuantity, category);
    }

    @Override
    double discountRate() {
        return 0.05;
    }

    @Override
    String productType() {
        return "Grocery";
    }
}

class Customer{

    private ShoppingCart shoppingCart;
    private int customerId;
    private String customerName;
    private String email;
    private String address;






    public Customer(int customerId, String customerName, String email, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        shoppingCart = new ShoppingCart(this);
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}

interface ShopService{
    void addProduct(Product product);
    void removeProduct(int productId);

}
interface CartService{
    void addToCart(int productId, int qty);
}
class ShoppingCart implements CartService{
    private Customer customer;

    private ArrayList<CartItems> cartItems;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.cartItems = new ArrayList<>();

    }

    @Override
    public void addToCart(int productId, int qty) {
        Product product = Shop.findProduct(productId);

        if(product.getStockQuantity() < qty){
            System.out.println("not enough Stock");
            return;
        }


        CartItems item =  new CartItems(product, qty);
        cartItems.add(item);
        product.reduceQty(qty);

        System.out.println(product.getProductName() +"Added to cart");




    }
}
class CartItems{

    private int cartId;
    private Product product ;
    private int qty;
    private int cartNextId;


    public CartItems( Product product, int qty) {

        this.cartId =cartNextId++;
        this.product = product;
        this.qty = qty;
    }





    public Product getProduct() {
        return product;
    }

    public int getQty() {
        return qty;
    }
}

class Shop implements ShopService{

    static  ArrayList<Product> products = new ArrayList<>();
    static Product findProduct(int productId){
        for(Product pr: products){
            if(pr.getProductId() == productId){
                return pr;
            }

        }
        return null;

    }
    @Override


    public void addProduct(Product product) {

        if(findProduct(product.getProductId())== null){
            products.add(product);
        }else{
            System.out.println("Duplicate Product Id Found");
        }

    }

    @Override
    public void removeProduct(int productId) {
        Product pr = findProduct(productId);
        if(pr != null){
            products.remove(pr);
        }else{
            System.out.println("Invalid Product Id");
        }
    }
}

class Invoice{
    private double price;
    private double tax;
    private  double deliveryFee;
    private int qty;
    private double discount;
    private double total;

    public Invoice(double price, double tax, double deliveryFee, int qty, double total, double discount) {
        this.price = price;
        this.tax = tax;
        this.deliveryFee = deliveryFee;
        this.discount = discount;
        this.qty = qty;
        this.total = total;

    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public int getQty() {
        return qty;
    }

    public double getTotal() {
        return total;
    }

    public double getDiscount() {
        return discount;
    }
}

class PrintInvoice{

    public static void print(Invoice invoice){
        System.out.println("===============================");
        System.out.println("===========INVOICE=============");
        System.out.println("===============================");
        System.out.println("Price:"+ invoice.getPrice());
        System.out.println("tax:"+invoice.getTax());
        System.out.println("Delivery Fee:"+invoice.getDeliveryFee());
        System.out.println("Quantity :"+ invoice.getQty());
        System.out.println("Total Amount :"+invoice.getTotal());
        System.out.println("===============================");

    }



}

class Calculator{

    final static double TAX_RATE = 0.05;
    final static  double DELIVERY_FEE = 300;

    public Invoice calculate(Product product, int qty){
        double price = qty * product.getPrice();
        double tax = price * TAX_RATE;
        double discount = price * product.discountRate();
        double total = price+ tax+ DELIVERY_FEE - discount;

        return new Invoice(price, tax, DELIVERY_FEE, qty, total, discount);


    }

}

interface OrderService{
    void order(Customer customer, int orderId, int qty, Product product);
}
class Order{
     private int orderId;
     private Customer customer;
     private Invoice invoice;
     private Product product;
     private int qty;

    public Order(int orderId, Customer customer, Invoice invoice, Product product, int qty) {
        this.orderId = orderId;
        this.customer = customer;
        this.invoice = invoice;
        this.product = product;
        this.qty = qty;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public int getQty() {
        return qty;
    }

    public Product getProduct() {
        return product;
    }
}