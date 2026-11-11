package FactoryDesignPattern.Solution;

public class Truck implements Transport {
    @Override
    public void drive() {
        System.out.println("Truck is being driven.");
    }
}
