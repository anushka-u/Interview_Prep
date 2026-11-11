package FactoryDesignPattern.Solution;

public class transportServiceWithoutFactory {
//It violates Factory design pattern as it is tightly coupled with the concrete classes.
// If we want to add a new transport type,
// we will have to modify this class which violates the Open/Closed principle.
// and it has the object creation logic in the service class which violates the Single Responsibility Principle.

    public void bookTransport(String transportType){
        Transport transport;

        if(transportType.equalsIgnoreCase("CAR")){
            transport = new Car();
        }
        else if(transportType.equalsIgnoreCase("BUS")) {
            transport = new Bus();
        }else if(transportType.equalsIgnoreCase("TRUCK")) {
            transport = new Truck();
        }else{
            throw new IllegalArgumentException("Invalid transport type: " + transportType);
        }

        transport.drive();
    }
}
