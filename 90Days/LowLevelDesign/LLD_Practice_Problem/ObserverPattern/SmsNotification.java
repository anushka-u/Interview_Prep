package ObserverPattern;

public class SmsNotification implements AccountStatus {

    @Override
    public void updateStatus(double balance) {
        IO.println("SMS Notification: Your account balance is " + balance);
    }
}
