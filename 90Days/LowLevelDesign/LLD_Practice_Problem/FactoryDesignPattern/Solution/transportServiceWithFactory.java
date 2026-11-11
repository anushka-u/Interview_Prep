package FactoryDesignPattern.Solution;

public class transportServiceWithFactory {

    private final TransportFactory transportFactory;

    public transportServiceWithFactory(TransportFactory transportFactory){
        this.transportFactory = transportFactory;
    }
    public void bookTransport(String transportType){
        Transport transport = transportFactory.createTransport(transportType);
        transport.drive();
    }
}
