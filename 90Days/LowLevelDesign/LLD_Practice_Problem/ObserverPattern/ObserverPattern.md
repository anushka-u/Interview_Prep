# Observer Design Pattern

## 1. What is the Observer Design Pattern?

**Definition:**

The Observer Design Pattern is a behavioral design pattern that establishes a one-to-many dependency between objects. Whenever the state of one object (Subject) changes, all its registered dependent objects (Observers) are automatically notified.

### In simple words

Imagine one object whose state keeps changing, and multiple other objects need to know whenever that change happens.

Instead of making the main object directly communicate with every dependent object, we introduce the Observer Pattern.

The main object maintains a list of observers and notifies them whenever its state changes.

### Remember this

- Subject = The object whose state changes.
- Observer = The object that wants to receive updates.
- Notification = The mechanism through which the Subject informs its registered Observers about a change.

---

## 2. Real-world example: Banking Application

Imagine we are developing a banking application.

Whenever a customer deposits money into their bank account, they should receive notifications through different channels.

For example:

- SMS Notification
- Email Notification
- WhatsApp Notification
- Push Notification

Suppose a customer deposits ₹10,000.

The account balance changes, and all registered notification services should receive the updated balance.

### Without Observer Pattern

We might write something like this inside the BankAccount class:

```java
public class BankAccount {

    public void deposit(double amount) {

        // Update balance

        sendSMS();
        sendEmail();
        sendWhatsApp();
    }
}
```

### Problems with this approach

1. BankAccount is directly dependent on multiple notification classes.
2. Adding a new notification channel requires modifying BankAccount.
3. The class starts accumulating multiple responsibilities.
4. It becomes harder to maintain and extend.

### With Observer Pattern

We separate notification responsibilities into independent Observer classes.

The BankAccount becomes the Subject.

SMS, Email, WhatsApp and Push Notification become Observers.

Whenever the account balance changes, BankAccount notifies all registered observers.

**Important:** BankAccount does not need to know how SMS, Email or WhatsApp notifications are implemented.

It only needs to know that every Observer follows a common contract.

---

## 3. Components of Observer Pattern

There are two primary components.

### A. Subject (Publisher)

The Subject is the object whose state changes.

Its responsibilities are:

- Maintain a list of registered observers.
- Register new observers.
- Remove observers when they no longer need updates.
- Notify all registered observers whenever its state changes.

In our example:

`BankAccount` is the Subject.

### B. Observer (Subscriber)

An Observer is an object that wants to receive updates from the Subject.

Its responsibilities are:

- Implement the common Observer interface.
- Define what should happen when it receives an update.

In our example:

- SMSNotification
- EmailNotification
- WhatsAppNotification

All three are Observers.

### Mapping

| Observer Pattern | Banking Application |
|---|---|
| Subject | BankAccount |
| Observer Interface | AccountObserver |
| Concrete Observer | SMSNotification |
| Concrete Observer | EmailNotification |
| Concrete Observer | WhatsAppNotification |
| Register | addObserver() |
| Unregister | removeObserver() |
| Notify | notifyObservers() |
| Update | update() |

---

## 4. Project Structure

```text
ObserverPattern/
│
├── AccountObserver.java
├── SMSNotification.java
├── EmailNotification.java
├── WhatsAppNotification.java
├── BankAccount.java
└── Main.java
```

---

## 5. Complete Java Implementation

### Step 1: Create Observer Interface

**AccountObserver.java**

```java
public interface AccountObserver {

    void update(double balance);

}
```

Why do we need an interface?

Because all notification classes should follow a common contract.

Every Observer must implement the `update()` method.

However, the implementation of that method can be different for every Observer.

### Step 2: Create Concrete Observers

**SMSNotification.java**

```java
public class SMSNotification implements AccountObserver {

    @Override
    public void update(double balance) {

        System.out.println(
            "SMS: Your updated balance is Rs. " + balance
        );
    }
}
```

**EmailNotification.java**

```java
public class EmailNotification implements AccountObserver {

    @Override
    public void update(double balance) {

        System.out.println(
            "Email: Your updated balance is Rs. " + balance
        );
    }
}
```

**WhatsAppNotification.java**

```java
public class WhatsAppNotification implements AccountObserver {

    @Override
    public void update(double balance) {

        System.out.println(
            "WhatsApp: Your updated balance is Rs. " + balance
        );
    }
}
```

### Important observation

All three classes implement the same interface:

`AccountObserver`

But each class provides its own implementation of:

`update(double balance)`

This is an example of runtime polymorphism.

The Subject can work with the interface reference without knowing the actual concrete Observer class.

### Step 3: Create the Subject

**BankAccount.java**

```java
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;

    private final List<AccountObserver> observers =
            new ArrayList<>();

    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Register an observer
    public void addObserver(AccountObserver observer) {

        observers.add(observer);
    }

    // Unregister an observer
    public void removeObserver(AccountObserver observer) {

        observers.remove(observer);
    }

    // Notify all registered observers
    private void notifyObservers() {

        for (AccountObserver observer : observers) {

            observer.update(balance);
        }
    }

    // Change account balance
    public void deposit(double amount) {

        if (amount <= 0) {

            throw new IllegalArgumentException(
                "Deposit amount must be positive"
            );
        }

        balance += amount;

        System.out.println(
            "Amount deposited: Rs. " + amount
        );

        notifyObservers();
    }

    public double getBalance() {

        return balance;
    }
}
```

### Understanding the important methods

| Method | Responsibility |
|---|---|
| addObserver() | Registers a new Observer |
| removeObserver() | Removes an Observer |
| notifyObservers() | Notifies all registered Observers |
| deposit() | Changes the account balance and triggers notification |
| update() | Defines the Observer's reaction to an update |

### Why List of AccountObserver?

Notice this declaration:

```java
private final List<AccountObserver> observers =
        new ArrayList<>();
```

We are storing interface references rather than concrete class references.

We are NOT maintaining separate lists for SMS, Email and WhatsApp.

Why?

Because BankAccount should not be tightly coupled to concrete notification implementations.

It should only depend on the common abstraction.

This allows us to introduce new Observer implementations without changing the Subject.

### Step 4: Execute the application

**Main.java**

```java
public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(5000);

        AccountObserver sms = new SMSNotification();

        AccountObserver email = new EmailNotification();

        AccountObserver whatsapp =
                new WhatsAppNotification();

        // Register observers
        account.addObserver(sms);
        account.addObserver(email);
        account.addObserver(whatsapp);

        // Deposit money
        account.deposit(10000);
    }
}
```

### Expected Output

```text
Amount deposited: Rs. 10000.0

SMS: Your updated balance is Rs. 15000.0

Email: Your updated balance is Rs. 15000.0

WhatsApp: Your updated balance is Rs. 15000.0
```

---

## 6. Understand the Complete Execution Flow

Let's understand what happens internally.

**Step 1: Create the Subject**

```java
BankAccount account = new BankAccount(5000);
```

A BankAccount object is created with an initial balance of ₹5,000.

**Step 2: Create Observers**

```java
AccountObserver sms = new SMSNotification();

AccountObserver email = new EmailNotification();

AccountObserver whatsapp =
        new WhatsAppNotification();
```

Three different Observer objects are created.

**Step 3: Register Observers**

```java
account.addObserver(sms);
account.addObserver(email);
account.addObserver(whatsapp);
```

All three observers are registered with the BankAccount.

Internally, the Subject maintains these references in its list.

**Step 4: Change Subject State**

```java
account.deposit(10000);
```

The balance changes from ₹5,000 to ₹15,000.

**Step 5: Notify Observers**

```java
notifyObservers();
```

The Subject iterates through its registered Observer list.

**Step 6: Execute Observer Behaviour**

```java
observer.update(balance);
```

Java invokes the actual implementation based on the runtime object:

- SMSNotification.update()
- EmailNotification.update()
- WhatsAppNotification.update()

All registered observers receive the update.

---

## 7. What happens when we add a new Observer?

Suppose the business introduces Push Notifications.

We create a new class.

**PushNotification.java**

```java
public class PushNotification implements AccountObserver {

    @Override
    public void update(double balance) {

        System.out.println(
            "Push Notification: Balance updated to Rs. "
            + balance
        );
    }
}
```

Now register it:

```java
account.addObserver(new PushNotification());
```

That's it!

We don't need to modify the BankAccount class.

We don't need to modify SMSNotification, EmailNotification or WhatsAppNotification either.

We simply introduce a new Observer implementation.

### Which design principle does this demonstrate?

**Open/Closed Principle (OCP)**

Software entities should be:

- Open for extension.
- Closed for modification.

We extend the functionality by introducing a new Observer instead of modifying the existing Subject implementation.

---

## 8. Important Concepts to Remember

### A. Loose Coupling

The Subject does not depend on concrete Observer classes.

For example, BankAccount does not need to know whether the notification is SMS, Email or WhatsApp.

It only interacts with the `AccountObserver` interface.

This reduces coupling between classes.

### B. Runtime Polymorphism

Consider:

```java
AccountObserver observer = new SMSNotification();
```

The reference type is `AccountObserver`, but the actual object is `SMSNotification`.

When we execute:

```java
observer.update(balance);
```

Java invokes the implementation of the actual runtime object.

This is runtime polymorphism.

### C. Dynamic Registration

Observers can be registered or removed at runtime.

```java
account.addObserver(sms);

account.removeObserver(email);
```

Once an Observer is removed, it will no longer receive future notifications from that Subject.

### D. One-to-Many Relationship

One Subject can have multiple Observers.

In our example:

One BankAccount → Multiple Notification Services.

This is the fundamental relationship established by the Observer Pattern.

---

## 9. Common Mistakes and Things to Be Careful About

These points are particularly important during interviews and implementation.

### 1. Directly creating concrete Observers inside Subject

Avoid:

```java
SMSNotification sms = new SMSNotification();

EmailNotification email = new EmailNotification();
```

inside BankAccount.

Why?

Because this creates tight coupling between Subject and concrete Observer implementations.

Prefer:

```java
List<AccountObserver> observers;
```

### 2. Forgetting to register an Observer

Creating an Observer object does not automatically register it with the Subject.

For example:

```java
AccountObserver sms = new SMSNotification();
```

This only creates an object.

To receive notifications, it must be registered:

```java
account.addObserver(sms);
```

### 3. Forgetting to notify after a state change

Updating the balance alone does not notify anyone.

The Subject must invoke:

```java
notifyObservers();
```

after the state change.

### 4. Forgetting to remove an Observer

If an Observer no longer needs updates, it should be unregistered.

Otherwise, the Subject continues to hold its reference and may continue sending notifications.

This can also contribute to memory leaks if the Subject lives longer than the Observer.

### 5. Assuming Observer execution is asynchronous

In our implementation:

```java
for (AccountObserver observer : observers) {

    observer.update(balance);
}
```

The notifications execute synchronously, one after another, on the calling thread.

This means a slow SMS or Email operation can delay subsequent notifications.

Observer Pattern itself does not guarantee asynchronous execution.

If asynchronous notification is required, we need an appropriate execution mechanism such as an executor, message queue or event-processing infrastructure.

### 6. Assuming every Observer must receive every possible event

The Subject notifies the observers registered with it.

If different event types are required, we can design the notification contract to carry event information, rather than assuming every update represents the same event.

---

## 10. Important Design Considerations for Production Code

Our example demonstrates the classic Observer Pattern. A real banking system would need additional care.

1. **Duplicate registrations:** The current ArrayList allows the same Observer to be registered multiple times. If that is undesirable, use a Set or check before registering.

2. **Thread safety:** ArrayList is not thread-safe. Concurrent registration, removal and notification need appropriate synchronization or a safe observer-list strategy.

3. **Failure isolation:** If one Observer throws an exception, the current notification loop may stop before reaching the remaining observers. Production implementations should decide how to isolate failures.

4. **External notifications:** Real SMS, email and WhatsApp delivery should generally be handled through reliable external services or asynchronous messaging infrastructure, rather than making the account transaction wait for every network call.

5. **Sensitive information:** Banking notifications should follow security and privacy requirements. Avoid exposing complete account details or sensitive information in notifications.

---

## 11. Observer Pattern vs Strategy Pattern vs Factory Pattern

This is an important comparison because all three patterns solve different problems.

| Pattern | Main Purpose | Key Question |
|---|---|---|
| Factory | Object creation | Which object should I create? |
| Strategy | Interchangeable algorithms or behaviours | Which behaviour should I execute? |
| Observer | Object communication and notification | Which objects should be notified when state changes? |

### Factory Pattern

Separates object creation logic from the client.

Example: Creating UPI, Card or Net Banking payment objects.

### Strategy Pattern

Encapsulates interchangeable algorithms or behaviours behind a common interface.

Example: Executing UPI, Card or Net Banking payment behaviour.

### Observer Pattern

Establishes a one-to-many dependency between a Subject and its registered Observers.

Example: Notifying SMS, Email and WhatsApp services when an account balance changes.

**Remember:** These patterns can coexist in the same application. They are not alternatives to one another.

---

## 12. Observer Pattern vs Publish-Subscribe

These concepts are related, but they are not exactly the same.

| Observer Pattern | Publish-Subscribe |
|---|---|
| Subject maintains references to Observers. | Publisher generally publishes events through a broker or event channel. |
| Subject directly notifies registered Observers. | Publisher and Subscriber are usually decoupled through a messaging intermediary. |
| Often used for in-process communication. | Commonly used for distributed event communication. |
| Observers are generally registered with the Subject. | Subscribers subscribe to topics or event channels. |

For example:

- Our BankAccount implementation is a classic Observer Pattern.
- A banking system publishing account events to Kafka topics, with independent consumers, is closer to Publish-Subscribe architecture.

**Important interview point:** Observer Pattern and event-driven architecture are related concepts, but they are not interchangeable terms.

---

## 13. Common Interview Questions

### Q1. What problem does Observer Pattern solve?

It solves the problem of notifying multiple dependent objects whenever the state of one object changes, without tightly coupling the Subject to concrete dependent objects.

### Q2. Why do we need an Observer interface?

It provides a common contract for all Observers.

The Subject interacts with the abstraction rather than depending on individual notification implementations.

### Q3. Can we add a new Observer without modifying the Subject?

Yes. We create a new class implementing the Observer interface and register its object with the Subject.

### Q4. Can one Subject have multiple Observers?

Yes. Observer Pattern establishes a one-to-many relationship between the Subject and its registered Observers.

### Q5. Can an Observer be removed at runtime?

Yes. We provide an unregister or remove method that removes the Observer from the Subject's registered list.

### Q6. Is Observer Pattern synchronous or asynchronous?

The pattern itself does not mandate either. Our basic Java implementation is synchronous because the Subject directly invokes each Observer's update method.

### Q7. What happens if an Observer throws an exception?

In our simple implementation, an exception can interrupt the notification loop. Production implementations should handle observer failures according to the application's requirements.

### Q8. Which SOLID principle is supported by Observer Pattern?

It supports loose coupling and extensibility and can help achieve the Open/Closed Principle.

---

## 14. Interview-Ready Explanation

If an interviewer asks you to explain Observer Pattern, you can answer:

"Observer is a behavioral design pattern that establishes a one-to-many dependency between objects.

Whenever the state of a Subject changes, all its registered Observers are automatically notified.

For example, in a banking application, whenever a customer deposits money, the BankAccount object updates its balance and notifies registered notification services such as SMS, Email and WhatsApp.

We create a common Observer interface, which all notification services implement. The BankAccount class maintains a list of Observer references and provides methods to register, unregister and notify them.

The biggest advantage is loose coupling. BankAccount does not depend on concrete notification implementations. We can introduce new notification channels without modifying the existing Subject class."

---

## 15. Quick Revision: The Things You Must Remember

Before an interview, revise these points:

- [ ] Observer is a Behavioral Design Pattern.
- [ ] It establishes a one-to-many relationship.
- [ ] Subject is the object whose state changes.
- [ ] Observer is the object that receives updates.
- [ ] Subject maintains a list of registered Observers.
- [ ] Register, unregister and notify are the main Subject operations.
- [ ] All concrete Observers implement a common Observer interface.
- [ ] Runtime polymorphism allows the Subject to invoke different Observer implementations.
- [ ] Loose coupling is one of the major benefits.
- [ ] Adding a new Observer should not require modifying the Subject.
- [ ] The basic implementation is synchronous.
- [ ] Observer Pattern is different from Publish-Subscribe.

---

## Final One-Line Revision

**Observer Pattern = One Subject + Multiple Observers + Automatic notification whenever the Subject's state changes.**

### Remember the banking example

BankAccount is the Subject.

SMS, Email, WhatsApp and Push Notifications are Observers.

When the account balance changes, all registered Observers receive the update.

That's the Observer Design Pattern.