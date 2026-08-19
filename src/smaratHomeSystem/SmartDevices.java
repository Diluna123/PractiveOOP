package smaratHomeSystem;




abstract class SmartDevice implements RemoteControler{

    abstract String getDeviceName();
    @Override
    public void switchOn(){
        System.out.println(getDeviceName()+"Switched On");
    }
    @Override
    public void switchOff(){
        System.out.println(getDeviceName()+"Switched Off");

    }

}
interface RemoteControler{
    void switchOn();
    void switchOff();

}

class SmartLight extends SmartDevice{

    @Override
    String getDeviceName() {
        return "Smart Light";
    }
}

class SmartFan extends SmartDevice{
    @Override
    String getDeviceName() {
        return "Smart Fan";
    }
}

class SmartTv extends SmartDevice{
    @Override
    String getDeviceName() {
        return "Smart TV";
    }
}

class Room{

    SmartDevice [] smartDevices;
    public Room(){
        smartDevices = new SmartDevice[]{
                new SmartLight(),
                new SmartFan(),
                new SmartTv(),


        };

    }
    public void turnOnALl(){
        for(SmartDevice smartDevice: smartDevices){
            smartDevice.switchOn();
        }
    }


}

class House{
    Room room = new Room();

    public void startHouse(){
        room.turnOnALl();
    }



}

class Main{
    public static void main(String [] args){
        House house = new House();
        house.startHouse();

    }
}
