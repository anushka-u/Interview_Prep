package PracticeProblem.easy.ObserverPatternTwo;

import PracticeProblem.easy.ObserverPatternTwo.EmailSubscriber;

public class Main {

    public  static void main(String[] args) {

        YouTubePublisher channel =
                new YouTubePublisher("CodeWithMe");

        EmailSubscriber email = new EmailSubscriber();
        MobileSubscriber mobile = new MobileSubscriber();

        channel.subscribe(email);
        channel.subscribe(mobile);

        channel.uploadVideo("Java Tutorial");
        channel.unSubscribe(mobile);

        channel.uploadVideo("Spring Boot Tutorial");
    }
}
