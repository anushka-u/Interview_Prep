package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;

    private List<AccountStatus> observers = new ArrayList<>();

    public  BankAccount(double balance) {
        this.balance = balance;
    }

    public void addObserver(AccountStatus observer) {
        observers.add(observer);
    }

    public  void removeObserver(AccountStatus observer) {
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(AccountStatus obs : observers){
            obs.updateStatus(balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        notifyObservers();
    }
}
