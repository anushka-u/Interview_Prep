# Strategy Design Pattern

## 1. What is Strategy Design Pattern?

**Definition:**

Strategy Design Pattern is a Behavioral Design Pattern that defines a family of algorithms, encapsulates each algorithm into a separate class, and makes them interchangeable at runtime.

### In simple words

Suppose we are developing a Banking Application that supports multiple payment methods:

- UPI Payment
- Credit Card Payment
- Net Banking Payment

Every payment method has a different way of processing payments, but the final objective is the same: **Making a Payment.**

Instead of writing all payment logic inside one service class, we create separate classes for each payment method and define a common interface.

This allows us to switch between different payment methods without modifying the main service class.

---

## 2. Problem Without Strategy Pattern

Initially, we might implement payment processing using multiple `if-else` conditions.

```java
public class PaymentService {

    public void makePayment(String paymentType, double amount) {

        if (paymentType.equals("UPI")) {
            System.out.println("UPI Payment: " + amount);
        }
        else if (paymentType.equals("CARD")) {
            System.out.println("Card Payment: " + amount);
        }
        else if (paymentType.equals("NET_BANKING")) {
            System.out.println("Net Banking Payment: " + amount);
        }
    }
}
```

### Problems with this approach

1. Every new payment method requires modifying the existing PaymentService class.
2. PaymentService becomes tightly coupled with different payment implementations.
3. The class becomes difficult to maintain as the number of payment methods increases.
4. Testing individual payment algorithms becomes more difficult.

**Solution:** Use Strategy Design Pattern to separate payment algorithms into individual classes.

---

## 3. How Strategy Pattern Works

The main idea is to:

1. Create a common interface representing the payment strategy.
2. Create separate classes implementing that interface.
3. Each class provides its own implementation of the payment algorithm.
4. The main service class depends on the interface instead of concrete implementations.
5. Pass the required strategy to the service depending on the payment method selected.

### Class Structure

```text
                  PaymentStrategy
                       (Interface)
                           |
              -------------------------
              |           |           |
          UpiPayment  CardPayment  NetBankingPayment
              |           |           |
              -------------------------
                           |
                      PaymentService
                       (Context)
```

---

## 4. Java Implementation

### Step 1: Create a Common Interface

**PaymentStrategy.java**

```java
public interface PaymentStrategy {

    void pay(double amount);

}
```

This interface defines a common contract for all payment methods.

Every class implementing PaymentStrategy must provide its own implementation of the `pay()` method.

### Step 2: Create UPI Payment Implementation

**UpiPayment.java**

```java
public class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println(
            "Payment processed through UPI: " + amount
        );

    }
}
```

### Step 3: Create Card Payment Implementation

**CardPayment.java**

```java
public class CardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println(
            "Payment processed through Card: " + amount
        );

    }
}
```

### Step 4: Create Net Banking Implementation

**NetBankingPayment.java**

```java
public class NetBankingPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println(
            "Payment processed through Net Banking: " + amount
        );

    }
}
```

Each class implements the same interface but provides a different payment implementation.

### Step 5: Create PaymentService (Context Class)

**PaymentService.java**

```java
public class PaymentService {

    private final PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {

        this.paymentStrategy = paymentStrategy;

    }

    public void makePayment(double amount) {

        paymentStrategy.pay(amount);

    }
}
```

**Important concepts:**

- PaymentService is the Context class.
- It maintains a reference to the PaymentStrategy interface.
- It does not depend directly on UpiPayment, CardPayment, or NetBankingPayment.
- It delegates payment processing to the selected strategy.

### Step 6: Main Class

**Main.java**

```java
public class Main {

    public static void main(String[] args) {

        // Selecting UPI Payment
        PaymentStrategy strategy = new UpiPayment();

        PaymentService service = new PaymentService(strategy);

        service.makePayment(5000);

        // Switching to Card Payment
        strategy = new CardPayment();

        service = new PaymentService(strategy);

        service.makePayment(10000);

    }
}
```

### Output

```text
Payment processed through UPI: 5000.0
Payment processed through Card: 10000.0
```

---

## 5. Understanding the Important Components

| Component | Responsibility |
|---|---|
| PaymentStrategy | Common interface defining the payment contract |
| UpiPayment | Implements UPI payment logic |
| CardPayment | Implements Card payment logic |
| NetBankingPayment | Implements Net Banking payment logic |
| PaymentService | Context class that uses the selected strategy |

### What is the Context Class?

The Context class is the class that uses the selected strategy to perform a particular operation.

In our example, PaymentService is the Context class.

It does not need to know how UPI, Card, or Net Banking processes payments internally.

It only knows that every payment strategy provides a `pay()` method.

---

## 6. How Does Runtime Strategy Selection Work?

Suppose the customer selects UPI as the payment method.

```java
PaymentStrategy strategy = new UpiPayment();
```

Now, if the customer selects Card Payment:

```java
PaymentStrategy strategy = new CardPayment();
```

The reference type remains the same, but the actual implementation changes.

This is possible because all concrete payment classes implement the same interface.

**Important:** The strategy is selected by providing the required implementation to PaymentService. In a real application, a selector, factory, or dependency injection mechanism can decide which implementation to provide.

---

## 7. What Happens When a New Payment Method Is Introduced?

Suppose tomorrow we introduce Wallet Payment.

We simply create a new class:

**WalletPayment.java**

```java
public class WalletPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println(
            "Payment processed through Wallet: " + amount
        );

    }
}
```

Now we can use it:

```java
PaymentStrategy strategy = new WalletPayment();

PaymentService service = new PaymentService(strategy);

service.makePayment(2000);
```

We do not need to modify the existing PaymentService class.

This makes the application easier to extend and maintain.

---

## 8. Relationship with SOLID Principles

### Open/Closed Principle (OCP)

A class should be open for extension but closed for modification.

In our example:

- We can introduce new payment methods by creating new strategy classes.
- We do not need to modify the existing PaymentService class.

### Dependency Inversion Principle (DIP)

High-level classes should depend on abstractions rather than concrete implementations.

In our example:

- PaymentService is a high-level class.
- PaymentStrategy is an abstraction.
- PaymentService depends on PaymentStrategy instead of directly depending on UpiPayment or CardPayment.

---

## 9. Real-World Backend Use Cases

Strategy Pattern can be useful when an application supports multiple interchangeable business algorithms.

Examples:

1. **Payment Processing:** UPI, Card, Net Banking, Wallet.
2. **Loan Interest Calculation:** Different interest calculation algorithms for different loan products.
3. **Discount Calculation:** Different discount strategies for different customer categories.
4. **Tax Calculation:** Different tax calculation rules based on region.
5. **Notification Delivery:** Email, SMS, Push Notification, when delivery behaviour is selected as a strategy.

The important condition is that the implementations represent alternative behaviours or algorithms that can be selected when needed.

---

## 10. Advantages

- Reduces tight coupling between the Context class and concrete implementations.
- Avoids large and growing conditional statements.
- Makes algorithms interchangeable.
- Improves code maintainability and testability.
- Supports the Open/Closed Principle.
- Makes it easier to introduce new strategies.

## 11. Disadvantages

- Introduces additional classes and interfaces.
- Can increase code complexity for very simple use cases.
- The application needs a mechanism to select the appropriate strategy.

---

## 12. Strategy Pattern vs Factory Pattern

| Strategy Pattern | Factory Pattern |
|---|---|
| Focuses on selecting and using an algorithm. | Focuses on object creation. |
| Encapsulates different behaviours. | Encapsulates object creation logic. |
| Allows interchangeable strategies. | Creates objects without exposing all creation details. |

**Can we use both together?**

Yes. A Factory can create the appropriate Strategy implementation, and the Context class can use that strategy.

---

## 13. Important Interview Questions

**Q1. What is Strategy Design Pattern?**

It is a Behavioral Design Pattern that encapsulates a family of algorithms into separate classes and makes them interchangeable.

**Q2. Why do we use Strategy Pattern?**

To avoid tight coupling between the Context class and concrete implementations and to make different algorithms interchangeable.

**Q3. What is a Context class?**

A Context class is the class that maintains a reference to the strategy interface and delegates the required operation to the selected strategy.

**Q4. Can we change the strategy at runtime?**

Yes. We can provide a different strategy implementation to the Context class at runtime.

**Q5. Which SOLID principles does Strategy Pattern support?**

Primarily Open/Closed Principle and Dependency Inversion Principle.

**Q6. Is Strategy Pattern a Creational or Behavioral Pattern?**

It is a Behavioral Design Pattern because it focuses on how objects interact and delegate responsibilities.

**Q7. Can Strategy Pattern eliminate all if-else conditions?**

No. It helps remove conditional logic used to select between different algorithms from the Context class. A separate selection mechanism may still use conditions.

**Q8. Can Strategy Pattern and Factory Pattern work together?**

Yes. Factory can select and create a concrete strategy, while Strategy defines how the selected algorithm is executed.

---

## 14. Key Takeaways

Remember these points:

1. Strategy Pattern belongs to Behavioral Design Patterns.
2. It defines a family of interchangeable algorithms.
3. A common interface defines the contract.
4. Each concrete class provides its own implementation.
5. The Context class depends on the interface, not concrete classes.
6. We can introduce new strategies without modifying the existing Context class.
7. Strategy selection can happen at runtime.

### One-Line Interview Revision

**Strategy Pattern allows us to encapsulate different algorithms into separate classes, define a common interface, and switch between their implementations without tightly coupling the Context class to concrete implementations.**