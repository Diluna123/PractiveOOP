package hospitalManagmentSystem_HashMap;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;

abstract class Patient {

    private int patientId ;
    private String name;
    private String nic;
    private String email;
    private String department;
    private double billAmount;

    public Patient(int patientId, String name, String nic, String email, String department, double billAmount) {
        this.patientId = patientId;
        this.name = name;
        this.nic = nic;
        this.email = email;
        this.department = department;
        this.billAmount = billAmount;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    void displayPatientDetails(){
        System.out.println("ID :"+ getPatientId());
        System.out.println("Name :"+ getName());
        System.out.println("NIC :"+getNic());
        System.out.println("Email :"+getEmail());
        System.out.println("Department:"+getDepartment());
        System.out.println("Bill Amount:"+getBillAmount());

    }


}
interface PatientService{
    void addPatient(Patient patient);
    void removePatient(int id);
    void findPatient(int id);
    void updatePatient(int id, String name, String nic, String email, String department, double amount);
    void displayPatients();

}

class PatientManager implements PatientService{

    HashMap<Integer, Patient> patients = new HashMap<>();
    HashMap<String, ArrayList<Patient>> patientsByDepartment = new HashMap<>();




    @Override
    public void addPatient(Patient patient) {
        Patient existPatient = patients.putIfAbsent(patient.getPatientId(), patient);

        String department = patient.getDepartment().toLowerCase();

        if(existPatient != null){
            System.out.println("Duplicate ID Found");
            return;

        }

        patientsByDepartment.putIfAbsent(department, new ArrayList<>());
        patientsByDepartment.get(department).add(patient);
        System.out.println("Employee Added Success");


    }



    @Override
    public void removePatient(int id) {


        Patient patient = patients.get(id);
        if(patient == null){
            System.out.println("Patient not founded");
            return;

        }
        ArrayList<Patient> patientArrayList = patientsByDepartment.get(patient.getDepartment().toLowerCase());
        patientArrayList.remove(patient);
        patients.remove(id);
        if(patientArrayList.isEmpty()){
            patientsByDepartment.remove(patient.getDepartment());


        }
        System.out.println("Patient removed Success");






    }

    @Override
    public void findPatient(int id) {
        Patient patient = patients.get(id);
        if(patient == null){
            System.out.println("Patient not funded!!");
            return;
        }
        patient.displayPatientDetails();

    }

    @Override
    public void updatePatient(int id, String name, String nic, String email, String department, double amount) {
        Patient patient = patients.get(id);
        if(patient == null){
            System.out.println("Invalid Patient id");
            return;
        }

        String oldDepartment = patient.getDepartment().toLowerCase();
        String newDepartment = department.toLowerCase();

        ArrayList<Patient> oldList = patientsByDepartment.get(oldDepartment);
        oldList.remove(patient);

        if(oldList.isEmpty()){
            patientsByDepartment.remove(oldDepartment);
        }
        patient.setName(name);
        patient.setNic(nic);
        patient.setEmail(email);
        patient.setDepartment(department);
        patient.setBillAmount(amount);

        patientsByDepartment.putIfAbsent(newDepartment, new ArrayList<>());
        patientsByDepartment.get(newDepartment).add(patient);

        System.out.println("Patient Added Success!!");




    }

    @Override
    public void displayPatients() {

        for(Patient patient : patients.values()){
            patient.displayPatientDetails();
            System.out.println("------------");

        }

    }
}