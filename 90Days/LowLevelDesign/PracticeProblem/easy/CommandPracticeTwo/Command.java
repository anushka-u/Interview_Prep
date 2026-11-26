package easy.CommandPracticeTwo;


interface Command {
    void execute();
    void undo();
}

// Provided receiver. Do not modify.
class Light {
    public void turnOn() {
        System.out.println("Light ON");
    }

    public void turnOff() {
        System.out.println("Light OFF");
    }
}

// Provided receiver. Do not modify.
class Fan {
    public void turnOn() {
        System.out.println("Fan ON");
    }

    public void turnOff() {
        System.out.println("Fan OFF");
    }
}

// Provided receiver. Do not modify.
class AirConditioner {
    public void turnOn() {
        System.out.println("AC ON");
    }

    public void turnOff() {
        System.out.println("AC OFF");
    }
}

// TODO: Implement LightCommand
class LightCommand implements Command{

    private Light light;
    public LightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
       light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}
// TODO: Implement FanCommand
class FanCommand implements Command{

    private  Fan fan;

    public  FanCommand(Fan fan) {
        this.fan = fan;
    }
    @Override
    public void execute() {
        fan.turnOn();
    }

    @Override
    public void undo() {
        fan.turnOff();
    }
}
// TODO: Implement ACCommand
class ACCommand implements Command{

    private AirConditioner airConditioner;

    public  ACCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }
    @Override
    public void execute() {
        airConditioner.turnOn();
    }

    @Override
    public void undo() {
        airConditioner.turnOff();
    }
}
