package CommandDesignPattern;


public class Main {

    static void main() {
        BankInvoker bankInvoker = new BankInvoker();
        BankAccountReciever bankAccount = new BankAccountReciever(1000.0);
        IO.println();
        BankCommand depositCommand = new DepositCommand(bankAccount, 500.0);
        bankInvoker.executeCommand(depositCommand);
        IO.println();
        BankCommand withdrawCommand = new WithdrawCommand(bankAccount, 200.0);
        bankInvoker.executeCommand(withdrawCommand);
    }
}
