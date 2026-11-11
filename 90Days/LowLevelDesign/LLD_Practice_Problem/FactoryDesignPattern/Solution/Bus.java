package FactoryDesignPattern.Solution;

public class Bus implements Transport {


    @Override
    public void drive(){
        IO.print("Bus is being driven.");
    }
}
