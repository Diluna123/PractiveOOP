package uniCourseRegistration;

abstract class Course implements Payment{
        private int courseId;
        private String courseName;
        private String studentName;
        private double creditHours;

        private static final double SCHOLARSHIP_LIMIT = 50000;
        private static final double SCHOLARSHIP_RATE = 0.10;


        public Course(int courseId, String courseName, String studentName, double creditHours){
             this.courseId = courseId;
             this.courseName = courseName;
             this.studentName = studentName;
             if(creditHours <=0){
                 throw new IllegalArgumentException("Credit hours must be positive");

             }
             this.creditHours = creditHours;


        }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getCreditHourse() {
        return creditHours;
    }

    public void displayCourseDetails(){
        System.out.println("Course ID: "+ getCourseId());
        System.out.println("Course Name : "+ getCourseName());
        System.out.println("Student Name: "+ getStudentName());
        System.out.println("Credit Hours : "+ getCreditHourse());
        System.out.println("Course Level : "+ courseLevel());

    }
    abstract String courseLevel();
    abstract double getFeeRate();



    @Override
    public void calculateFee() {

        if(getCreditHourse() <= 0){
            System.out.println("Invalid Credit Hourse");
            return;
        }

        if(getCreditHourse() > 15){
            System.out.println("Overload Student");
            return;
        }

        double baseFee = getCreditHourse() * getFeeRate();
        double discount= 0;
        System.out.println("Original Fee : "+ baseFee);
        if(baseFee > SCHOLARSHIP_LIMIT){
            discount  = baseFee * SCHOLARSHIP_RATE;
            System.out.println("Scholarship : " + discount);
        }else{
            System.out.println("Scholarship : Not Eligible");

        }
        double finalFee = baseFee - discount;


        System.out.println("Final Fee : "+ finalFee);
    }
}

interface Payment{

    void calculateFee();
    void paymentMethod();
}

class UndergraduateCourse extends Course{

    public UndergraduateCourse(int courseId, String courseName, String studentName, double creditHours) {
        super(courseId, courseName, studentName, creditHours);
    }

    @Override
    double getFeeRate() {
        return 5000;
    }

    @Override
    public void paymentMethod() {
        System.out.println("Payment Method : Cash ");

    }

    @Override
    String courseLevel() {
        return "Level 5";
    }
}

class PostgraduateCourse extends Course{
    public PostgraduateCourse(int courseId, String courseName, String studentName, double creditHours) {
        super(courseId, courseName, studentName, creditHours);
    }

    @Override
    double getFeeRate() {
        return 8000;
    }

    @Override
    String courseLevel() {
        return "Level 7";
    }

    @Override
    public void paymentMethod() {
        System.out.println("Payment Method : Bank Transfer ");


    }
}

class OnlineCourse extends Course{
    public OnlineCourse(int courseId, String courseName, String studentName, double creditHours) {
        super(courseId, courseName, studentName, creditHours);
    }

    @Override
    double getFeeRate() {
        return 3000;
    }

    @Override
    String courseLevel() {
        return "Online Certification";
    }

    @Override
    public void paymentMethod() {
        System.out.println("Payment Method : Credit Card ");


    }
}


class Main{

    public static void main (String [] args){
        Course[] courses = {
                new UndergraduateCourse(001, "Undergraduate Course", "Diluna Sithija", 15),
                new PostgraduateCourse(002, "Postgraduate Course", "Sithun Sankalpa", 5),
                new OnlineCourse(003, "Online Course", "Sunil Kumara", 12),


        };

        for(Course c: courses){
            c.displayCourseDetails();
            c.calculateFee();
            c.paymentMethod();
            System.out.println("-----------------------------------------");
        }



    }


}