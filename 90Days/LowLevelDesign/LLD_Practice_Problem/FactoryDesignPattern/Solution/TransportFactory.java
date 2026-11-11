package FactoryDesignPattern.Solution;


public class TransportFactory {

    public Transport createTransport(String transportType){
        if(transportType.equalsIgnoreCase("CAR")){
            return new Car();
        }else if(transportType.equalsIgnoreCase("BUS")) {
            return new Bus();
        }else if(transportType.equalsIgnoreCase("TRUCK")) {
            return new Truck();
        }else{
            throw new IllegalArgumentException("Invalid transport type: " + transportType);
        }
    }
}
