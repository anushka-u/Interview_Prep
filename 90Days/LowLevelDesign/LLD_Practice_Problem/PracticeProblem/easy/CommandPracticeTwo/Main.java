package PracticeProblem.easy.CommandPracticeTwo;

public class Main {
    public static void main(String[] args) {

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        Command lightCommand = new LightCommand(light);
        Command fanCommand = new FanCommand(fan);
        Command acCommand = new ACCommand(ac);

        lightCommand.execute();
        fanCommand.execute();
        lightCommand.undo();
        fanCommand.undo();
        acCommand.execute();
        acCommand.undo();
    }
}