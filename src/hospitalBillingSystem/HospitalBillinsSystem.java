package hospitalBillingSystem;

abstract class Patient implements Payable{


    private int patientId;
    private String patientName;
    private int daysAdmited;

    public Patient(int patientId, String patientName, int daysAdmited) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.daysAdmited = daysAdmited;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getDaysAdmited() {
        return daysAdmited;
    }

    void displayPatientDetails(){
        System.out.println("Patient ID: " + getPatientId() );
        System.out.println("Patient Name: " + getPatientName() );
        System.out.println("Admitted Days: " + getDaysAdmited() );


    }
    @Override
    public void calculateBill(){

        double billPrice = getDaysAdmited() * getBillrate();
        if(billPrice <= 0){
            System.out.println("Invalid Bill");
            return;
        }
        double discount  = 0;

        if(billPrice > 100000){
            discount = billPrice * 0.15;


        }
        double finalBill = billPrice - discount;
        System.out.println("Bill Total : Rs. " + billPrice);
        System.out.println("Disscount : "+ discount);
        System.out.println("Final Bill : " + finalBill);




    }

    abstract double getBillrate();

}

interface Payable{


    void calculateBill();
    void paymentMethod();
}

class NormalPatient extends Patient{

    public NormalPatient(int patientId, String patientName, int daysAdmited) {
        super(patientId, patientName, daysAdmited);
    }

    @Override
    double getBillrate() {
        return 5000;
    }

    @Override
    public void paymentMethod() {
        System.out.println("Payment Method : Cash");


    }
}

class ICU_Patient extends Patient{
    public ICU_Patient(int patientId, String patientName, int daysAdmited) {
        super(patientId, patientName, daysAdmited);
    }

    @Override
    double getBillrate() {
        return 15000;
    }

    @Override
    public void paymentMethod() {
        System.out.println("Payment Method : Card");



    }
}

class EmergencyPatient extends Patient{
    public EmergencyPatient(int patientId, String patientName, int daysAdmited) {
        super(patientId, patientName, daysAdmited);
    }

    @Override
    double getBillrate() {
        return 25000;
    }

    @Override
    public void paymentMethod() {

        System.out.println("Payment Method : Bank Transfer");


    }
}

//class Main{
//    public static void main (String args []){
//
//        Patient[] patients = {
//                new NormalPatient(01, "Diluna", 4),
//                new ICU_Patient(02, "Sithija", 7),
//                new EmergencyPatient(03, "Sunil", 4)
//
//        };
//
//        for(Patient patient: patients){
//            patient.displayPatientDetails();
//            patient.calculateBill();
//            patient.paymentMethod();
//
//            System.out.println("----------------------");
//        }
//
//    }
//
//
//}