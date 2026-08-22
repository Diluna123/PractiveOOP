package employeeManagmentSystem;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

class Employee {

    private int employeeId;
    private String employeeName;
    private String email;
    private String department;
    private double salary;

    public Employee(int employeeId, String employeeName, String email, String department, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    void displayEmployeeDetails(){
        System.out.println("Employee Id:"+getEmployeeId());
        System.out.println("Employee Name:"+getEmployeeName());
        System.out.println("Email:"+getEmail());
        System.out.println("Department:"+getDepartment());
        System.out.println("Salary:"+getSalary());

    }


}
interface EmployeeService{
    void addEmployee(Employee employee);
    void removeEmployee(int employeeId);
    void findEmployee(int employeeId);
    void displayEmployees();
    void updateEmployee(int employeeId, String name, String email, String department ,double salary);
    void displayDepartmentEmployeeCount();
    void displayEmployeesWithId();
    void findEmployeeBySalary(double salary);
    void findEmployeeBySalaryRange(double minSalary, double maxSalary);
    void findEmployeeByDepartment(String department);
    void findHighestPaidEmployee();
    void findHighestPaidEmployeeByDepartment(String department);
    void displayDepartmentAverageSalary(String department);
    void displayDepartmentSalaryReport(String department);
//    sorting methods
    void displayEmployeeBySalary();
    void displayEmployeeSalaryDescending();
    void displayEmployeeByDepartmentAndSalary();
    void displayEmployeeByDepartmentAndSalaryDescending();
//    sorting Challenges
    void displayTop3HeightsPaidEmployees();
    void displayTopPaidEmployeesByDepartment();
    void displayDepartmentSalaryStatics();


}

class EmployeeManager implements EmployeeService {

    private final HashMap<Integer, Employee> employees = new HashMap<>();

    private final HashMap<String, ArrayList<Employee>> employeesByDepartment = new HashMap<>();


    @Override
    public void addEmployee(Employee employee) {

        Employee existEmployee = employees.putIfAbsent(employee.getEmployeeId(), employee);

        String department = employee.getDepartment().toLowerCase();

        if (existEmployee != null) {
            System.out.println("Duplicate Employee ID Found");
            return;
        }


        employeesByDepartment.putIfAbsent(department, new ArrayList<>());

        employeesByDepartment.get(department).add(employee);
        System.out.println("Employee Added Success!!");


    }

    @Override
    public void removeEmployee(int employeeId) {

        Employee employee = employees.get(employeeId);
        if (employee == null) {
            System.out.println("Not found Employee !!");
            return;
        }

        ArrayList<Employee> employeeArrayList = employeesByDepartment.get(employee.getDepartment().toLowerCase());
        employeeArrayList.remove(employee);
        employees.remove(employeeId);
        if (employeeArrayList.isEmpty()) {
            employeesByDepartment.remove(employee.getDepartment());
        }

        System.out.println("Employee Removed!! ");


    }

    @Override
    public void findEmployee(int employeeId) {

        if (employees.containsKey(employeeId)) {
            Employee employee = employees.get(employeeId);
            employee.displayEmployeeDetails();
            return;
        }
        System.out.println("cant find any employee!!");


    }

    @Override
    public void displayEmployees() {
        for (Employee employee : employees.values()) {
            employee.displayEmployeeDetails();
            System.out.println("-----------------------------");

        }
    }

    @Override
    public void updateEmployee(int employeeId, String name, String email, String department, double salary) {

        Employee employee = employees.get(employeeId);
        if (employee == null) {
            System.out.println("Cant find Employee");
            return;
        }

        String oldDepartment = employee.getDepartment().toLowerCase();
        String newDepartment = department.toLowerCase();

        ArrayList<Employee> oldList = employeesByDepartment.get(oldDepartment);
        oldList.remove(employee);

        if (oldList.isEmpty()) {
            employeesByDepartment.remove(oldDepartment);

        }

        Employee updatedEmploy = new Employee(employeeId, name, email, department, salary);

        employees.put(employeeId, updatedEmploy);

        employeesByDepartment.putIfAbsent(newDepartment, new ArrayList<>());

        employeesByDepartment.get(newDepartment).add(updatedEmploy);


        System.out.println("Employee Updated success!");


    }

    @Override
    public void displayDepartmentEmployeeCount() {
        for(Map.Entry<String, ArrayList<Employee>> entry : employeesByDepartment.entrySet()){
            String department = entry.getKey();
            ArrayList<Employee> employeeArrayList = entry.getValue();
            int count = employeeArrayList.size();

            System.out.println(department+" : " + count + "employees");

        }



    }

    @Override
    public void displayEmployeesWithId() {
        for(Map.Entry<Integer, Employee> entry: employees.entrySet()){
            System.out.println("Employee ID:"+entry.getKey());
            System.out.println("Employee Name:"+entry.getValue().getEmployeeName());


        }

    }

    @Override
    public void findEmployeeBySalary(double salary) {

        for(Map.Entry<Integer, Employee> entry: employees.entrySet()){
            Employee employee = entry.getValue();

            if(employee.getSalary() == salary){
                System.out.println("Employee Found!");
                employee.displayEmployeeDetails();
                return;
            }

        }
        System.out.println("Cant find any Employee");

    }

    @Override
    public void findEmployeeBySalaryRange(double minSalary, double maxSalary) {
        boolean found =false;

        for(Map.Entry<Integer, Employee> entry : employees.entrySet()){

            Employee employee = entry.getValue();
            if(employee.getSalary() >= minSalary && employee.getSalary()<= maxSalary){
                employee.displayEmployeeDetails();
                System.out.println("-------------");
                found = true;
            }
        }

        if(!found){
            System.out.println("Employee not found");
        }

    }

    @Override
    public void findEmployeeByDepartment(String department) {
        for(Map.Entry<Integer, Employee> entry : employees.entrySet()){
            Employee employee = entry.getValue();
            if(employee.getDepartment().equalsIgnoreCase(department)){
                System.out.println("ID:"+employee.getEmployeeId());
                System.out.println("Name:"+employee.getEmployeeName());
                System.out.println("Salary:"+employee.getSalary());
                System.out.println("----------------------");
            }
        }
    }

    @Override
    public void findHighestPaidEmployee() {

        Employee highest = null;
        for(Map.Entry<Integer, Employee> enty : employees.entrySet()){
            Employee employee = enty.getValue();

            if(highest == null || employee.getSalary() >  highest.getSalary()){
                highest = employee;


            }

        }
        if(highest != null){
            System.out.println("Height Paid Employee");
            highest.displayEmployeeDetails();


        }else{
            System.out.println("No Employee Found");

        }

    }

    @Override
    public void findHighestPaidEmployeeByDepartment(String department) {

        ArrayList<Employee> empList = employeesByDepartment.get(department.toLowerCase());

        if(empList == null || empList.isEmpty()){
            System.out.println("No Found Employee in"+department);
            return;
        }
        Employee highest = null ;

        for(Employee employee: empList){
            if(highest==null || employee.getSalary() > highest.getSalary()){
                highest=employee;

            }
        }
        System.out.println("Height Paid Employee in "+ department);
        highest.displayEmployeeDetails();


    }

    @Override
    public void displayDepartmentAverageSalary(String department) {

        ArrayList<Employee> employeeArrayList = employeesByDepartment.get(department.toLowerCase());
        if(employeeArrayList == null || employeeArrayList.isEmpty()){
            System.out.println("Department Not found");
            return;
        }

        double salaryTotal = 0;


        for(Employee employee : employeeArrayList){

            salaryTotal += employee.getSalary();

        }
        double average = salaryTotal/ employeeArrayList.size();
        System.out.println("Department :"+department);
        System.out.println("Employee Count :"+employeeArrayList.size());
        System.out.println("Average Salary :"+ average);



    }

    @Override
    public void displayDepartmentSalaryReport(String department) {

        ArrayList<Employee> empList = employeesByDepartment.get(department.toLowerCase());
        if(empList == null || empList.isEmpty()){
            System.out.println("No employees Found");
            return;
        }
        double totalSalary = 0;
        double averageSalary = 0;
        double highestSalary = empList.get(0).getSalary();
        double lowestSalary = empList.get(0).getSalary();

        for(Employee employee : empList){
            totalSalary+= employee.getSalary();
            if(highestSalary > employee.getSalary()){
                highestSalary = employee.getSalary();

            }
            if(lowestSalary < employee.getSalary()){
                lowestSalary = employee.getSalary();
            }

        }

        averageSalary = totalSalary / empList.size();

        System.out.println("Department:"+ department);
        System.out.println("----------------------------");
        System.out.println("Employee Count:"+empList.size());
        System.out.println("Average Salary:"+averageSalary);
        System.out.println("Highest Salary:"+ highestSalary);
        System.out.println("Lowest Salary :"+ lowestSalary);
        System.out.println("-------------------------------");






    }

    @Override
    public void displayEmployeeBySalary() {



        ArrayList <Employee> sortedEmployee = new ArrayList<>(employees.values());

        sortedEmployee.sort(Comparator.comparingDouble(Employee::getSalary));

        for(Employee employee: sortedEmployee){
            System.out.println(employee.getEmployeeName() + " :-"+ employee.getSalary());
        }




    }

    @Override
    public void displayEmployeeSalaryDescending() {

        ArrayList<Employee> sortedEmployee = new ArrayList<>(employees.values());

        sortedEmployee.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        for(Employee employee : sortedEmployee){
            System.out.println(employee.getEmployeeName() + " :-"+ employee.getSalary());


        }


    }

    @Override
    public void displayEmployeeByDepartmentAndSalary() {
        ArrayList<Employee> sortedEmployeeList = new ArrayList<>(employees.values());


       sortedEmployeeList.sort(Comparator.comparing(Employee::getDepartment).thenComparingDouble(Employee::getSalary));

       for(Employee employee: sortedEmployeeList){
           System.out.println("Department :"+employee.getDepartment());
           System.out.println("------------------------------");
           System.out.println("Name : " + employee.getEmployeeName());
           System.out.println("Salary :" +employee.getSalary());
           System.out.println(" ");
       }




    }

    @Override
    public void displayEmployeeByDepartmentAndSalaryDescending() {

        ArrayList<Employee> sortedEmployeeList = new ArrayList<>(employees.values());

        sortedEmployeeList.sort(Comparator.comparing(Employee::getDepartment).thenComparing(Employee::getSalary, Comparator.reverseOrder()));
        for(Employee employee: sortedEmployeeList){
            System.out.println("Department :"+employee.getDepartment());
            System.out.println("Name :"+ employee.getEmployeeName() + " \nSalary :"+employee.getSalary());
            System.out.println(" ");

        }

    }

    @Override
    public void displayTop3HeightsPaidEmployees() {
        ArrayList<Employee> list =
                new ArrayList<>(employees.values());

        if(list.isEmpty()) {
            System.out.println("No employees found");
            return;
        }

        list.sort(
                Comparator.comparingDouble(Employee::getSalary)
                        .reversed()
        );

        int limit = Math.min(3, list.size());

        for(int i = 0; i < limit; i++) {

            Employee employee = list.get(i);

            System.out.println("Rank: " + (i + 1));
            System.out.println("Name: " + employee.getEmployeeName());
            System.out.println("Department: " + employee.getDepartment());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("----------------------");
        }






    }

    @Override
    public void displayTopPaidEmployeesByDepartment() {

//        for(Map.Entry<String, ArrayList<Employee>> entry : employeesByDepartment.entrySet()){
//            String department = entry.getKey();
//            ArrayList<Employee>  employeeArrayList = entry.getValue();
//
//            employeeArrayList.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
//
//            Employee highestPaidEmployee = employeeArrayList.get(0);
//
//
//            System.out.println("Department" + department);
//            System.out.println("Name :"+ highestPaidEmployee.getEmployeeName());
//            System.out.println("Salary :"+highestPaidEmployee.getSalary());
//
//
//        }

        for(Map.Entry<String, ArrayList<Employee>> entry : employeesByDepartment.entrySet()){
            String department = entry.getKey();
            ArrayList<Employee> employeeArrayList = entry.getValue();

            Employee highestEmployee = null;
            for(Employee employee: employeeArrayList){
                if(highestEmployee == null || employee.getSalary() > highestEmployee.getSalary()){
                    highestEmployee = employee;
                }
            }

            if(highestEmployee == null){
                System.out.println("Employee not found in "+department);
                continue;
            }

            System.out.println("Department :"+ department);
            System.out.println("Name:"+ highestEmployee.getEmployeeName());
            System.out.println("Salary:"+ highestEmployee.getSalary());

        }

    }

    @Override
    public void displayDepartmentSalaryStatics() {

        for(Map.Entry<String, ArrayList<Employee>> entry : employeesByDepartment.entrySet()){

            String department = entry.getKey();
            ArrayList<Employee> employeeArrayList = entry.getValue();

            Employee highest = null;
            Employee lowest = null;
            double departmentTotalSalary = 0;

            for(Employee employee : employeeArrayList){
                departmentTotalSalary += employee.getSalary();
                if(highest == null || employee.getSalary() > highest.getSalary()){
                    highest = employee;
                }
                if(lowest == null || employee.getSalary() < lowest.getSalary()){
                    lowest = employee;
                }

            }
            double averageSalary = departmentTotalSalary /employeeArrayList.size();

            if(highest == null){
                System.out.println("Highest Employee not found !"+ department);
                continue;

            }
            if(lowest == null){
                System.out.println("Lowest Employee not found !"+ department);
                continue;

            }


            System.out.println("================="+department+"======================");
            System.out.println("Employee Count :"+ employeeArrayList.size());
            System.out.println("Total Salary : "+ departmentTotalSalary);
            System.out.println("Average Salary : "+averageSalary);
            System.out.println("Highest Salary : "+highest.getSalary());
            System.out.println("Highest Paid : "+highest.getEmployeeName());
            System.out.println("Lowest Salary :"+lowest.getSalary());
            System.out.println("Lowest Paid : "+ lowest.getEmployeeName());
            System.out.println("-------------------------------");


        }

    }
}

