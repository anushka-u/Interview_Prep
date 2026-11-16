package ObserverPattern;

public class WhatsappNotification implements AccountStatus {

    @Override
    public void updateStatus(double balance) {
        IO.println("Whatsapp Notification: Your account balance is " + balance);
    }

}
