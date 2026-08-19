package HospitalManagmentSystem;

import java.lang.classfile.instruction.SwitchCase;
import java.util.ArrayList;

abstract class Patient{

    private int  PatientId;
    private String patientName;
    private int days;
    private boolean isAdmited;

    public Patient(int PatientId, String patientName, int days, boolean isAdmited) {
        this.PatientId = PatientId;
        this.patientName = patientName;
        this.days = days;
        this.isAdmited = isAdmited;
    }

    public int getPatientId() {
        return PatientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getDays() {
        return days;
    }

    public boolean isAdmited() {
        return isAdmited;
    }

    public void setAdmited(boolean admited) {
        isAdmited = admited;
    }

    void displayPatientDetails(){
        System.out.println("Patient ID:"+ getPatientId());
        System.out.println("Patient Name:"+ getPatientName());
        System.out.println("Days:"+ getDays());
        System.out.println("Admited or not:" +" "+ isAdmited());
        System.out.println("Patient Type:"+ patientType());
        System.out.println("Room Type:"+ roomType());

    }

    abstract String patientType();
    abstract String roomType();
    abstract double roomRate();

}

interface PaymentService{
    void pay();
    void paymentMethod();
}
interface PatientService{
    void addPatient(Patient patient);
    void searchPatient(int id);
    void admitPatient(int id);
    void dischargePatient(int id);
    void removePatient(int id);



}
class Bill{
    private double roomCharge;
    private double doctorCharge;
    private double medicineCharge;
    private double tax;
    private double discount;
    private double total;

    public Bill(double roomCharge, double doctorFee, double medicineFee, double tax, double discount, double total) {
        this.roomCharge = roomCharge;
        this.doctorCharge = doctorFee;
        this.medicineCharge = medicineFee;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }

    public double getRoomCharge() {
        return roomCharge;
    }

    public double getDoctorCharge() {
        return doctorCharge;
    }

    public double getMedicineCharge() {
        return medicineCharge;
    }

    public double getTax() {
        return tax;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }
}

class BillCalculator{
    public static Bill calculator(Patient patient){
        double roomCharge = patient.getDays() * patient.roomRate();
        double doctorFee = 1000;
        double medicineFee = 0;
        double tax = (roomCharge+ doctorFee+ medicineFee )* 0.80;

        double subTotal = roomCharge+ doctorFee+ medicineFee + tax;
        double discount = 0;

        if(subTotal > 100000){
            discount = subTotal * 0.10;
        }
        double total = subTotal - discount;

        return(new Bill(roomCharge, doctorFee, medicineFee, tax, discount, total));

    }
}




class Hospital implements PatientService{

    ArrayList<Patient> patients = new ArrayList<>();

    public void printBill(Bill bill){
        System.out.println("==================");
        System.out.println("Room Fee:"+ bill.getRoomCharge());
        System.out.println("Doctor Charge :"+ bill.getDoctorCharge());
        System.out.println("Medicine Fee:"+ bill.getMedicineCharge());
        System.out.println("Tax:"+ bill.getTax());
        System.out.println("Discount:"+ bill.getDiscount());
        System.out.println("Total:"+bill.getTotal());
        System.out.println("==================================");
    }


    @Override
    public void addPatient(Patient patient) {
        for(Patient ph: patients){
            if(ph.getPatientId() == patient.getPatientId()){
                System.out.println("Duplicate Patient ID ");
                return;
            }
        }
        patients.add(patient);
        System.out.println("Patient Admitted Success");

    }

    private Patient findPatient(int id){
        for(Patient pa: patients){
            if(pa.getPatientId() == id){
                return pa;
            }
        }

        throw new IllegalArgumentException("cant find any patient from this id");

    }

    @Override
    public void searchPatient(int id) {
        Patient pa = findPatient(id);
        pa.displayPatientDetails();

    }

    @Override
    public void admitPatient(int id) {
        Patient pa = findPatient(id);
        if(!pa.isAdmited()){
            pa.setAdmited(true);
            System.out.println("Patient Admitted Success");

        }else{
            System.out.println("Patient Already Admitted before");
        }

    }


    @Override
    public void dischargePatient(int id) {
        Patient pa = findPatient(id);
        if(pa.isAdmited()){
            pa.setAdmited(false);
            Bill bill = BillCalculator.calculator(pa);
            printBill(bill);




        }else{
            System.out.println("Patient Already Discharged!!");
        }
    }

    @Override
    public void removePatient(int id) {
        Patient pa = findPatient(id);
        patients.remove(pa);
        System.out.println("Patient Removed");
    }
}

class NormalPatient extends Patient{
    public NormalPatient(int PatientId, String patientName, int days, boolean isAdmited) {
        super(PatientId, patientName, days, isAdmited);
    }



    @Override
    String patientType() {
        return "NormalPatient";
    }

    @Override
    String roomType() {
        return "";
    }

    @Override
    double roomRate() {
        return 5000;
    }
}

class patientType extends Patient{
    public patientType(int PatientId, String patientName, int days, boolean isAdmited) {
        super(PatientId, patientName, days, isAdmited);
    }

    @Override
     String patientType() {
        return "patientType";
    }

    @Override
    String roomType() {
        return "";
    }

    @Override
    double roomRate() {
        return 15000;
    }
}

class EmergencyPatient extends Patient{
    public EmergencyPatient(int PatientId, String patientName, int days, boolean isAdmited) {
        super(PatientId, patientName, days, isAdmited);
    }

    @Override
    String patientType() {
        return "EmergencyPatient";
    }

    @Override
    String roomType() {
        return "";
    }

    @Override
    double roomRate() {
        return 25000;
    }
}
class PatientFatory{

    public static Patient getPatient(String type, int id, String patientName, int days){
        switch (type){
            case "normal":
                return new NormalPatient(id, patientName, days, false );
            case "emergency":
                return  new EmergencyPatient(id, patientName, days, false );





        }

        throw new IllegalArgumentException("Unautharized Patient ");
    }
}

class Main{
    public static void main(String[] args) {
        Hospital hs = new Hospital();
        PatientFatory factory = new PatientFatory();
        hs.addPatient(factory.getPatient("normal", 001, "Sunil", 2));








    }
}