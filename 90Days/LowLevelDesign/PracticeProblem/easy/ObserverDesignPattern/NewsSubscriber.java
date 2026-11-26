package easy.ObserverDesignPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implement interface NewsSubscriber with receive(String headline) and messages()
interface NewsSubscriber{
    void receive(String headline);
    String[] messages();
}
// Implement class EmailSubscriber(name): stores "Email to alice: headline" for every headline it receives
class EmailSubscriber implements NewsSubscriber{
    private final String name;
    private final List<String> messages = new ArrayList<>();

    public EmailSubscriber(String name){
        this.name = name;
    }

    @Override
    public void receive(String headline){
        messages.add("Email to " + name + ": " + headline);
    }

    @Override
    public String[] messages(){
        String[] result = messages.toArray(new String[0]);
        return result;
    }

}

// Implement class SmsSubscriber(name): stores "SMS to bob: headline" for every headline it receives
class SmsSubscriber implements NewsSubscriber{
    private final String name;

    private final List<String> messages = new ArrayList<>();

    public SmsSubscriber(String name){
        this.name = name;
    }

    @Override
    public void receive(String headline){
        messages.add("SMS to "+ name + ": "+ headline);
    }

    @Override
    public String[] messages(){
        return messages.toArray(new String[0]);
    }
}
class NewsPublisher {
    // Subject: holds the subscribers and pushes every headline to them.
    private final List<NewsSubscriber> subscribers = new ArrayList<>();

    public boolean subscribe(NewsSubscriber subscriber) {
        // TODO: Add the subscriber once, in subscription order. Return false for null or a duplicate.
        if(subscriber == null){
            return false;
        }

        if(!subscribers.contains(subscriber)){
            return subscribers.add(subscriber);
        }
        return false;
    }

    public boolean unsubscribe(NewsSubscriber subscriber) {
        // TODO: Remove that same subscriber object. Return whether it was subscribed.
        if(subscribers.contains(subscriber)){
            return subscribers.remove(subscriber);
        }
        return false;
    }

    public int publish(String headline) {
        // TODO: Call receive(headline) on every subscriber in order and return how many were notified.
        // Do not keep headlines here; each subscriber keeps its own messages.
        int count = 0;
        for(NewsSubscriber newsSubscriber: subscribers){
            newsSubscriber.receive(headline);
            count++;
        }
        return count;
    }

    public int subscriberCount() {
        // TODO: Return how many subscribers are registered right now.
        return subscribers.size();
    }
}

// Provided judge-facing facade. Do not modify.
class NewsPublisherFacade {
    private final NewsPublisher publisher = new NewsPublisher();
    private final Map<String, NewsSubscriber> subscribersByName = new HashMap<>();

    public NewsPublisherFacade() {
    }

    public boolean subscribe(String channel, String name) {
        if (subscribersByName.containsKey(name)) {
            return false;
        }
        NewsSubscriber subscriber;
        if (channel.equals("email")) {
            subscriber = new EmailSubscriber(name);
        } else if (channel.equals("sms")) {
            subscriber = new SmsSubscriber(name);
        } else {
            return false;
        }
        subscribersByName.put(name, subscriber);
        return publisher.subscribe(subscriber);
    }

    public boolean unsubscribe(String name) {
        NewsSubscriber subscriber = subscribersByName.remove(name);
        return subscriber != null && publisher.unsubscribe(subscriber);
    }

    public int publish(String headline) {
        return publisher.publish(headline);
    }

    public String[] inbox(String name) {
        NewsSubscriber subscriber = subscribersByName.get(name);
        return subscriber == null ? new String[0] : subscriber.messages();
    }

    public int subscriberCount() {
        return publisher.subscriberCount();
    }
}
