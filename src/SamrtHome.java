

abstract class SmartDevice implements DeviceControl{

    private int deviceId;
    private String deviceName;
    private int powerConsumption;

    public SmartDevice (int deviceId, String deviceName, int powerConsumption){
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.powerConsumption = powerConsumption;

    }

    abstract void deviceType();


}

interface DeviceControl{


    void turnOn();
    void turnOff();




}

class SmartLight extends SmartDevice implements DeviceControl{


    public SmartLight(int deviceId, String deviceName, int powerConsumption) {
        super(deviceId, deviceName, powerConsumption);
    }

    @Override
    public void turnOn() {
        System.out.println("Light turned ON");

    }

    @Override
    public void turnOff() {
        System.out.println("Light turned OFF");

    }

    @Override
    void deviceType() {
        System.out.println("Device Type: Smart Light");
    }
}

class SmartFan extends SmartDevice implements DeviceControl{
    public SmartFan(int deviceId, String deviceName, int powerConsumption) {
        super(deviceId, deviceName, powerConsumption);
    }

    @Override
    void deviceType() {
        System.out.println("Device Type: Smart Fan");

    }


    @Override
    public void turnOn() {
        System.out.println("Fan turned ON");

    }

    @Override
    public void turnOff() {
        System.out.println("Fan turned OFF");

    }
}

class SmartTv extends SmartDevice implements DeviceControl{
    public SmartTv(int deviceId, String deviceName, int powerConsumption) {
        super(deviceId, deviceName, powerConsumption);
    }

    @Override
    public void turnOn() {
        System.out.println("TV turned ON");

    }

    @Override
    public void turnOff() {
        System.out.println("TV turned OFF");


    }

    @Override
    void deviceType() {
        System.out.println("Device Type: Smart TV");

    }
}

class Main{
    public static void main(String args[]){

        SmartDevice[] devises = {
                new SmartLight(100, "Orange Smart Light", 10),

                new SmartFan(101, "Kelvin Smart Fan", 75),
                new SmartTv(102,"Dialog Smart TV", 150)

        };

        for(SmartDevice device : devises){
            device.deviceType();
            device.turnOn();
            device.turnOff();
            System.out.println("---------------------------");
        }

    }
}