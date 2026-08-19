package universityRegistrationSystem;


import java.util.ArrayList;

abstract class Course{
    private int courseId;
    private String courseName;
    private double courseFee;
    private int duration;


    public Course(int courseId, String courseName, double courseFee, int duration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.duration = duration;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public int getDuration() {
        return duration;
    }
    abstract String courseType();

    void displayCourseDetails(){
        System.out.println("Course ID : "+ getCourseId());
        System.out.println("Course Name :"+ getCourseName());
        System.out.println("Course Type:"+courseType());
        System.out.println("Course Fee :"+getCourseFee());
        System.out.println("Course Duration Months:"+getDuration());
    }
}

class TheoryCourse extends Course{
    public TheoryCourse(int courseId, String courseName, double courseFee, int duration) {
        super(courseId, courseName, courseFee, duration);
    }

    @Override
    String courseType() {
        return "Theory Course";
    }
}
class LabCourse extends Course{
    public LabCourse(int courseId, String courseName, double courseFee, int duration) {
        super(courseId, courseName, courseFee, duration);
    }

    @Override
    String courseType() {
        return "";
    }
}
class ResearchCourse extends Course{
    public ResearchCourse(int courseId, String courseName, double courseFee, int duration) {
        super(courseId, courseName, courseFee, duration);
    }

    @Override
    String courseType() {
        return "";
    }
}

class Student{
    private int studentId;
    private String studentName;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    void displayStudentDetails(){
        System.out.println("Student ID:"+getStudentId());
        System.out.println("Student Name :"+getStudentName());
    }
}

class Invoice{
    private double courseFee;
    private double documentFee;
    private double tax;
    private double registrationFee;
    private double total;


    public Invoice(double courseFee, double documentFee, double tax, double registrationFee, double total) {
        this.courseFee = courseFee;
        this.documentFee = documentFee;
        this.tax = tax;
        this.registrationFee = registrationFee;
        this.total =total;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public double getDocumentFee() {
        return documentFee;
    }

    public double getTax() {
        return tax;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public double getTotal() {
        return total;
    }
}
class BillPrint{

    public static void printInvoice(Invoice invoice){
        System.out.println("Course Fee :"+ invoice.getCourseFee());
        System.out.println("Document Fee :"+ invoice.getDocumentFee());
        System.out.println("Registration Fee :"+invoice.getRegistrationFee());
        System.out.println("Tax :"+invoice.getTax());
        System.out.println("Total :"+ invoice.getTotal());

    }


}
class BillCalculator{

    private static final double TAX_RATE =0.10 ;
    private static final double REGISTRATION_FEE = 2000;
    private static final double DOCUMENT_FEE = 500;

    public Invoice calculate(Course course){
        double price= course.getCourseFee();
        double tax = price * TAX_RATE;
        double total = price+ tax + REGISTRATION_FEE+ DOCUMENT_FEE;

        return new Invoice(price, DOCUMENT_FEE, tax, REGISTRATION_FEE, total);


    }
}

interface  UniversityService{

void addCourse(Course course);
void searchCourse(int courseId);
void removeCourse(int courseId);


}
interface StudentService{
    void register(Student student, int courseId);
}

class Registration{

    private Student student;
    private Course course;
    private Invoice invoice;

    public Registration(Student student, Course course, Invoice invoice) {
        this.student = student;
        this.course = course;
        this.invoice = invoice;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
class University implements UniversityService, StudentService{

    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Registration> registrations = new ArrayList<>();

    BillCalculator calculator = new BillCalculator();

    private Course course;
    private Invoice invoice;
    private Student student;

    @Override
    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course Added Success");
    }

    private Course findCourse(int courseId){
        for(Course cr: courses){
            if(cr.getCourseId() == courseId){
                return cr;
            }
        }
        throw new IllegalArgumentException("Invalid Course Id");

    }

    @Override
    public void searchCourse(int courseId) {
        Course course = findCourse(courseId);
        course.displayCourseDetails();

    }

    @Override
    public void removeCourse(int courseId) {
        Course course = findCourse(courseId);
        courses.remove(course);
        System.out.println("Course Removed ");
    }

    @Override
    public void register(Student student, int courseId) {
        Course course = findCourse(courseId);
        Invoice invoice = calculator.calculate(course);

        Registration registration = new Registration(student, course, invoice);
        registrations.add(registration);

        System.out.println("==========");
        System.out.println("Registration Success");
        student.displayStudentDetails();
        System.out.println("-----------------");
        course.displayCourseDetails();
        System.out.println("-------------------");

        BillPrint.printInvoice(invoice);







    }
}
class CourseFactory{

    public static Course createCourse(String type, int id, String name, double fee, int duration){
        switch (type.toLowerCase()){
            case("theory"):
                return new TheoryCourse(id, name, fee, duration);
            case("lab"):
                return new LabCourse(id, name, fee, duration);
            case("research"):
                return new ResearchCourse(id, name, fee, duration);
        }
        throw new IllegalArgumentException("Invalid Course type");

    }

}
class StudentFactory{

    public static Student getStudent(){
        return new Student(01, "Diluna");
    }



}

class Main{
    public static void main(String[] args) {
        University uni = new University();
        uni.addCourse(CourseFactory.createCourse("theory", 001, "Maths for ICT", 12000, 6));
        uni.addCourse(CourseFactory.createCourse("lab", 002, "Networking", 23000, 24));

        Student student = StudentFactory.getStudent();

        uni.register(student, 001);




    }
}