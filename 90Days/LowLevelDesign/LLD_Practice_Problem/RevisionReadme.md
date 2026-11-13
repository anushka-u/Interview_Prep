# Java Design Patterns — Revision README

## 1. Simple Factory Pattern

**Category:** Creational Design Pattern (commonly used, but not an official GoF pattern)

### What is it?

Simple Factory separates object creation logic from business logic. It provides a central Factory class that decides which concrete object to create based on the input.

**Remember:** Input → Factory → Concrete Object

### Why do we need it?

Suppose a payment application supports UPI, Credit Card and Net Banking.

Without a Factory, the service class may contain multiple `if-else` conditions to create different payment objects.

This creates:
- Mixed object creation and business logic.
- Increasing conditional statements.
- Poor maintainability.
- Tight coupling with concrete implementations.

### Java Example

```java
interface Payment {
    void pay();
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("UPI Payment");
    }
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card Payment");
    }
}

class PaymentFactory {

    public Payment createPayment(String type) {

        if (type.equalsIgnoreCase("UPI")) {
            return new UpiPayment();
        }

        if (type.equalsIgnoreCase("CARD")) {
            return new CardPayment();
        }

        throw new IllegalArgumentException("Invalid payment type");
    }
}

class PaymentService {

    private final PaymentFactory factory;

    PaymentService(PaymentFactory factory) {
        this.factory = factory;
    }

    public void processPayment(String type) {
        Payment payment = factory.createPayment(type);
        payment.pay();
    }
}
```

### Important Points

- Factory handles object creation.
- Service handles business logic.
- Concrete classes implement a common interface.
- Service depends on the abstraction rather than concrete classes.
- Adding a new type may require modifying the Factory's conditional logic.

### Interview Definition

Simple Factory is a creational pattern that centralizes object creation in a separate class and returns the appropriate concrete implementation based on the given input.

---

## 2. Factory Method Pattern

**Category:** Creational Design Pattern (GoF)

### What is it?

Factory Method defines a method for creating objects but allows subclasses to decide which concrete class should be instantiated.

**Remember:** Factory Interface/Abstract Class → Concrete Factory → Concrete Object

### Why do we need it?

When different implementations