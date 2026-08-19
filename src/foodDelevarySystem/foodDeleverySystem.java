package foodDelevarySystem;

import java.util.ArrayList;

abstract class FoodItem{

    private int id;
    private String name;
    private double price;

    public FoodItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;

        if(getPrice() < 0){
            throw new IllegalArgumentException("Price mus be Positive");
        }


    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    abstract String getFoodType();


}
class Pizza extends FoodItem{
    public Pizza(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    String getFoodType() {
        return "Pizza";
    }
}

class Burger extends FoodItem{
    public Burger(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    String getFoodType() {
        return "Burger";
    }
}

class Drink extends FoodItem{
    public Drink(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    String getFoodType() {
        return "Drink";
    }
}



class Order implements Customer{

    private ArrayList<FoodItem> foodItems = new ArrayList<>();

    @Override
    public void addFood(FoodItem food) {
        foodItems.add(food);

        System.out.println(food.getName() + " Added Success!!");
    }

    @Override
    public void displayOrder() {
        for(FoodItem food: foodItems){
            System.out.println("Item ID: "+ food.getId());
            System.out.println("Name : "+food.getName());
            System.out.println("Food Type: "+ food.getFoodType());
            System.out.println("Price : "+food.getPrice());
            System.out.println("===================================");

        }
    }

    @Override
    public void calculateBill() {
        double total = 0;
        for(FoodItem food: foodItems){
            total += food.getPrice();


        }
        System.out.println("Total Bill :Rs. "+ total);
    }

    @Override
    public void removeItem(int id) {
        FoodItem found = null;
        for(FoodItem food: foodItems){
            if(food.getId() == id){
                found = food;
                break;
            }
        }
        if(found != null){
            foodItems.remove(found);
            System.out.println(found.getName() + " removed");


        }else{
            System.out.println("Item Not Found");
        }


    }
}


interface Customer{
    void addFood(FoodItem food);
    void displayOrder();
    void calculateBill();
    void removeItem(int id);





}
class Main{
    public static void main(String[] args) {
        Order order = new Order();
        order.addFood(new Pizza(001, "Chicken Pizza Larg", 2300));
        order.addFood(new Burger(002, "Chicken Burger", 1200));
        order.addFood(new Drink(003, "Chocolate Milk Shake ",970 ));

        order.displayOrder();
        order.calculateBill();
        order.removeItem(002);
        System.out.println("---------------------------------");

        order.displayOrder();
        order.calculateBill();


    }
}