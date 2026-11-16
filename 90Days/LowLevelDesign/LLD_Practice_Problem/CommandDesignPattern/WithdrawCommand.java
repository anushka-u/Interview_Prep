package CommandDesignPattern;

import ObserverPattern.BankAccount;
import lombok.With;

public class WithdrawCommand implements BankCommand {

    private BankAccountReciever bankAccountReciever;
    private double amount;

    public WithdrawCommand(BankAccountReciever bankAccountReciever, double amount) {
        this.bankAccountReciever = bankAccountReciever;
        this.amount = amount;
    }

    @Override
    public void execute(){
        bankAccountReciever.withdraw(amount);
    }
}
