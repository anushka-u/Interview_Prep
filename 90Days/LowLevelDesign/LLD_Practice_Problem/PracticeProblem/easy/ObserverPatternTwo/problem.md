1. Create an interface Subscriber

- Method: void onVideoUploaded(String channelName, String videoTitle);

2. Create two observer classes
- EmailSubscriber
    - Store received notifications in a List<String>.
    - Format: "Email: Java Tutorial uploaded on CodeWithMe"
    - Add a method List<String> getNotifications().
- MobileSubscriber
    - Store received notifications in a List<String>.
    - Format: "Mobile: Java Tutorial uploaded on CodeWithMe"
    - Add a method List<String> getNotifications().
3. Implement YouTubeChannelPublisher (Subject)

It should have:
- List<Subscriber> subscribers
- subscribe(Subscriber subscriber)
- unsubscribe(Subscriber subscriber)
- uploadVideo(String videoTitle) — notify all subscribers.
- subscriberCount() — return the number of subscribers.
  Expected behaviour