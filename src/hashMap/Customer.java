package hashMap;

import javax.sql.rowset.serial.SerialArray;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Customer {

    private int customerId;
    private String customerName;
    private String email;

    public Customer(int customerId, String customerName, String email) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email =email;


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



}

class CustomerManager{

    private final HashMap<Integer, Customer> customers = new HashMap<>();
    private final HashMap<String, ArrayList<Customer>> customerByDomain = new HashMap<>();


    public void addCustomer(Customer customer){

//        if(customers.containsKey(customer.getCustomerId())){
//            System.out.println("Duplicate Id Founded!");
//            return;
//        }
//        customers.put(customer.getCustomerId(), customer);

        Customer customer1 = customers.putIfAbsent(customer.getCustomerId(), customer);
        if(customer1 == null){
            System.out.println("Data Added Success");
            return;


        }
        System.out.println("Duplicate ID Found" + customer1.getCustomerName());



    }
    public void displayCustomers(){
        for(Map.Entry<Integer, Customer> entry :customers.entrySet()){
            Customer customer = entry.getValue();

            System.out.println("Customer Id :"+ entry.getKey());
            System.out.println("Customer Name:"+customer.getCustomerName());
            System.out.println("Email :"+customer.getEmail());
            System.out.println("------------------------");

        }
    }

    public Customer findCustomer(int customerId){

      if(!customers.containsKey(customerId)){
          throw new IllegalArgumentException("Customer not found");

      }
      return customers.get(customerId);

    }

    public void removeCustomer(int customerId){
        if(!customers.containsKey(customerId)){
            System.out.println("Cant find any customer with this customer ID");
            return;

        }

        Customer customer = customers.get(customerId);


        String domain = customer.getEmail().split("@")[1];
        customers.remove(customerId);

        ArrayList<Customer> customersList = customerByDomain.get(domain);
        customersList.remove(customer);

        if(customersList.isEmpty()){
            customerByDomain.remove(domain);
        }



    }

    public void updateCustomer(int customerId, String customerName, String email){
        if(customers.containsKey(customerId)){
            Customer updatedCustomer = new Customer(customerId, customerName, email);
            customers.put(customerId, updatedCustomer);
            System.out.println("Updated Success");
            return;

        }
        System.out.println("Invalid Customer Id");




    }

    public void searchCustomerByName(String name){
        for(Customer customer:customers.values()){
            if(customer.getCustomerName() == name){
                System.out.println("Customer Founded!");
                System.out.println("ID :"+ customer.getCustomerId());
                System.out.println("name: "+customer.getCustomerName());
                System.out.println("Email :"+customer.getEmail());
                return;

            }

        }
        System.out.println("Cant find any customer");

    }

    public void searchByEmail(String email){
        for(Customer customer : customers.values()){
            if(customer.getEmail().equalsIgnoreCase(email)){
                System.out.println("Customer Founded!");
                System.out.println("ID :"+ customer.getCustomerId());
                System.out.println("name: "+customer.getCustomerName());
                System.out.println("Email :"+customer.getEmail());
                return;
            }
        }
        System.out.println("Cant find any customer");


    }

    public void setCustomersByDomain(String domain){
        int count = 0;
        for (Customer customer:customers.values()){
            String [] parts = customer.getEmail().split("@");
            if(parts[1].equalsIgnoreCase(domain)){
                count++;



            }

        }
        System.out.println(domain + " Customers:"+count);
    }

    public void addCustomerByDomain(Customer customer){


        String [] parts = customer.getEmail().split("@");
        String domain = parts[1];

        customerByDomain.putIfAbsent(domain, new ArrayList<>());

        customerByDomain.get(domain).add(customer);


//        if(!customerByDomain.containsKey(parts[1])){
//            customerByDomain.put(domain, new ArrayList<>() );
//
//
//        }
//        customerByDomain.get(domain).add(customer);




    }

    public void displayCustomerByDomain(String domain){

        ArrayList<Customer> customers = customerByDomain.get(domain);

        if(customers==null){
            System.out.println("NO Customer found");
            return;
        }

        for(Customer customer: customers){
            System.out.println("ID :"+customer.getCustomerId());
            System.out.println("Name:"+customer.getCustomerName());
            System.out.println("Email :"+customer.getEmail());
            System.out.println("----------------------------");
        }




    }



}


class Main{

    public static void main(String[] args) {
        CustomerManager manager = new CustomerManager();
        manager.addCustomer(new Customer(11, "Diluna", "diluna@gmail.com"));
        manager.addCustomer(new Customer(21, "Sithija", "sithija@yahoo.com"));
        manager.addCustomer(new Customer(31, "sulakshana", "sula@gmail.com"));

        Customer customer = new Customer(5, "testing ", "test@gmail.com");
        Customer customer2 = new Customer(8, "testing 2 ", "test2@gmail.com");


        manager.addCustomerByDomain(customer);
        manager.addCustomerByDomain(customer2);


        manager.setCustomersByDomain("gmail.com");
        manager.displayCustomerByDomain("gmail.com");

    }
}
