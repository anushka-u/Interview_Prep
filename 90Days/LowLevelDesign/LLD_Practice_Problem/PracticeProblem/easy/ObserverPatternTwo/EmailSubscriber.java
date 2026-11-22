package PracticeProblem.easy.ObserverPatternTwo;

public class EmailSubscriber implements Subscriber {

    @Override
    public void onVideoUpload(String name, String title) {
        IO.println("Email: "+ title + " uploaded on "+ name);
    }
}
