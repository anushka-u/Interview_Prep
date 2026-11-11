package FactoryDesignPattern.Solution;

public class Car implements Transport {
    @Override
    public void drive() {
        System.out.println("Car is being driven.");
    }
}
