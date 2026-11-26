package easy.ObserverPatternTwo;

import java.util.ArrayList;
import java.util.List;

public class YouTubePublisher {

    private String name;
    private String title;
    private List<Subscriber> subscribers = new ArrayList<>();

    public YouTubePublisher(String name) {
        this.name = name;
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unSubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadVideo(String title){
        this.title = title;
        notifySubscribers();
    }

    public void notifySubscribers(){
        for(Subscriber s :  subscribers){
            s.onVideoUpload(name,title);
        }
    }

}
