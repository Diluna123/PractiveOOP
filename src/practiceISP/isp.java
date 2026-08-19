package practiceISP;

abstract class Employer{




}

interface AppointmentService{
    void bookAppointment();

    void cancelAppointment();

    void viewAppointments();


}

interface PrescriptionService{
    void createPrescription();

    void updatePrescription();

    void viewPrescription();

}

interface BillingService {

    void generateBill();

    void receivePayment();

    void printReceipt();

}

class Doctor extends Employer implements AppointmentService, PrescriptionService{
    @Override
    public void bookAppointment() {

    }

    @Override
    public void cancelAppointment() {

    }

    @Override
    public void viewAppointments() {

    }

    @Override
    public void createPrescription() {

    }

    @Override
    public void updatePrescription() {

    }

    @Override
    public void viewPrescription() {

    }
}