package easy.ObserverPatternTwo;

public class MobileSubscriber implements Subscriber {

    @Override
    public void onVideoUpload(String name, String title) {
        IO.println("Mobile: "+ title + " uploaded on "+ name);
    }
}
