package CommandDesignPattern;

public class DepositCommand implements BankCommand{

    private BankAccountReciever bankAccountReciever;
    private double amount;

    public DepositCommand(BankAccountReciever bankAccountReciever, double amount){
        this.bankAccountReciever = bankAccountReciever;
        this.amount = amount;
    }

    @Override
    public void execute(){
        bankAccountReciever.deposit(amount);
    }
}
