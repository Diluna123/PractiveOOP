package rentalManagmentSystem;

class Customer {

    private int customerId;
    private String customerName;
    private String nic;
    private String contact;

    public Customer(int customerId, String customerName, String nic, String contact) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.nic = nic;
        this.contact = contact;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getNic() {
        return nic;
    }

    public String getContact() {
        return contact;
    }

    void displayCustomerDetails(){
        System.out.println("Customer ID : "+ getCustomerId());
        System.out.println("Customer Name  : "+ getCustomerName());
        System.out.println("NIC : "+getNic());
        System.out.println("Contact :"+getContact());

    }
}
