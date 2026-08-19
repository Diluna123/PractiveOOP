package practice2;



abstract class User implements Notification{

    private int userId;
    private String userName;
    private String message;

    public User(int userId, String userName, String message) {
        this.userId = userId;
        this.userName = userName;
        this.message = message;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    abstract void notificationType();
    abstract  String notifyType();


    @Override
    public void sendNotification() {
        System.out.println("User : " + getUserName() + "|  Message : " + getMessage() + " | "+notifyType()+" notification send Success!! | ");

    }


}

interface Notification{
    void sendNotification();



}

class EmailNotification extends User{

    public EmailNotification(int userId, String userName, String message) {
        super(userId, userName, message);
    }

    @Override
    void notificationType() {
        System.out.println("Notification Type : Email Notification");

    }

    @Override
    String notifyType() {
        return "Email";
    }
}

class SMSNotification extends User{
    public SMSNotification(int userId, String userName, String message) {
        super(userId, userName, message);
    }

    @Override
    void notificationType() {
        System.out.println("Notification Type : SMS Notification");

    }

    @Override
    String notifyType() {
        return "SMS";
    }


}
class PushNotification extends User{
    public PushNotification(int userId, String userName, String message) {
        super(userId, userName, message);
    }

    @Override
    void notificationType() {
        System.out.println("Notification Type : Push Notification");

    }

    @Override
    String notifyType() {
        return "Push";
    }
}


class Main{
    public static void main(String args []){

        User[] users ={
                new EmailNotification(100, "Diluna", "Email Notification"),
                new SMSNotification(101, "Sithija","SMS Notification"),
                new PushNotification(102, "Sulakshana", "This is Push Notification")
        };

        for(User us : users){
            us.notificationType();
            us.sendNotification();

            System.out.println("--------------------");
        }


    }
}