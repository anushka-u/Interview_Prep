# Abstract Factory Design Pattern

## 1. What is Abstract Factory?

**Abstract Factory is a Creational Design Pattern that provides an interface for creating families of related objects without specifying their concrete classes.**

In simple words:

Abstract Factory creates a **family of related objects** using a common factory interface.

**Remember:**

`Abstract Factory → Factory of Related Objects → Product Family`

---

## 2. Real-World Example: Loan Processing System

Imagine we are developing a banking application that supports loan processing in multiple countries, such as India and Mexico.

Each country has its own:
- Loan validation rules.
- Payment processing rules.

For example:

| India | Mexico |
|---|---|
| India Loan Validator | Mexico Loan Validator |
| India Payment Processor | Mexico Payment Processor |

When a customer uses the Indian banking application, we need the Indian loan validator and Indian payment processor.

Similarly, when a customer uses the Mexican banking application, we need the Mexican loan validator and Mexican payment processor.

**The important requirement:** We should not accidentally combine an Indian loan validator with a Mexican payment processor.

This is where the Abstract Factory Pattern helps.

---

## 3. Understanding the Architecture

Our application contains three important parts.

### A. Abstract Factory

`LoanFactory`

This is an interface responsible for creating a family of related objects.

```java
public interface LoanFactory {

    LoanValidator loanValidator();

    PaymentProcessor paymentProcessor();
}
```

It defines which objects a factory should create, but it does not decide which concrete implementations will be returned.

### B. Abstract Products

These are the interfaces representing the products.

**LoanValidator.java**

```java
public interface LoanValidator {
    void loanValidator();
}
```

**PaymentProcessor.java**

```java
public interface PaymentProcessor {
    void paymentProcessor();
}
```

Both products have their own interfaces, allowing different countries to provide their own implementations.

### C. Concrete Products

These are the actual implementations of the abstract product interfaces.

**India Loan Validator**

```java
public class InLoanValidatorRegion implements LoanValidator {

    @Override
    public void loanValidator() {
        IO.print("India Loan Region Validator");
    }
}
```

**India Payment Processor**

```java
public class InPaymentProcessor implements PaymentProcessor {

    @Override
    public void paymentProcessor() {
        IO.print("India Loan Region Payment Processor");
    }
}
```

**Mexico Loan Validator**

```java
public class MxLoanValidatorRegion implements LoanValidator {

    @Override
    public void loanValidator() {
        IO.print("Mexico Loan Region Validator");
    }
}
```

**Mexico Payment Processor**

```java
public class MxPaymentProcessor implements PaymentProcessor {

    @Override
    public void paymentProcessor() {
        IO.print("Mexico Loan Region Payment Processor");
    }
}
```

---

## 4. Concrete Factories

Each country has its own factory.

The factory implements `LoanFactory` and provides the corresponding country-specific products.

### India Factory

```java
public class InLoanValidatorRegionFactory implements LoanFactory {

    @Override
    public LoanValidator loanValidator() {
        return new InLoanValidatorRegion();
    }

    @Override
    public PaymentProcessor paymentProcessor() {
        return new InPaymentProcessor();
    }
}
```

### Mexico Factory

```java
public class MxLoanAndPaymentFactory implements LoanFactory {

    @Override
    public LoanValidator loanValidator() {
        return new MxLoanValidatorRegion();
    }

    @Override
    public PaymentProcessor paymentProcessor() {
        return new MxPaymentProcessor();
    }
}
```

**Important:** Each concrete factory creates a complete family of related products.

- India Factory → India Loan Validator + India Payment Processor.
- Mexico Factory → Mexico Loan Validator + Mexico Payment Processor.

The client does not need to know the concrete implementation classes.

---

## 5. Client Code (Main.java)

The client receives a factory through its constructor.

It uses the abstract factory interface instead of directly creating concrete product objects.

```java
public class Main {

    private final LoanValidator loanValidator;
    private final PaymentProcessor paymentProcessor;

    public Main(LoanFactory loanFactory) {
        this.loanValidator = loanFactory.loanValidator();
        this.paymentProcessor = loanFactory.paymentProcessor();
    }

    public void execute() {
        loanValidator.loanValidator();
        paymentProcessor.paymentProcessor();
    }

    public static void main(String[] args) {

        // India application
        LoanFactory indiaFactory =
                new InLoanValidatorRegionFactory();

        Main indiaApp = new Main(indiaFactory);
        indiaApp.execute();

        // Mexico application
        LoanFactory mexicoFactory =
                new MxLoanAndPaymentFactory();

        Main mexicoApp = new Main(mexicoFactory);
        mexicoApp.execute();
    }
}
```

### Expected Output

```text
India Loan Region Validator
India Loan Region Payment Processor

Mexico Loan Region Validator
Mexico Loan Region Payment Processor
```

---

## 6. How Does the Flow Work?

Let's understand the execution step by step.

**Step 1:** The client decides which country's application it wants to execute.

**Step 2:** It selects the corresponding concrete factory.

For example:

```java
LoanFactory indiaFactory =
        new InLoanValidatorRegionFactory();
```

**Step 3:** The selected factory creates the related products.

```java
indiaFactory.loanValidator();
indiaFactory.paymentProcessor();
```

**Step 4:** The client receives the products through their abstract interfaces.

```java
private final LoanValidator loanValidator;
private final PaymentProcessor paymentProcessor;
```

**Step 5:** The client executes the product operations without knowing their concrete classes.

```java
loanValidator.loanValidator();
paymentProcessor.paymentProcessor();
```

The same client code works with both India and Mexico factories.

---

## 7. Why Do We Need Abstract Factory?

Without Abstract Factory, the client might directly create concrete objects:

```java
LoanValidator validator = new InLoanValidatorRegion();
PaymentProcessor processor = new InPaymentProcessor();
```

The client becomes dependent on concrete implementation classes.

As the application grows to support more countries, managing related object creation can become difficult.

With Abstract Factory:

- Object creation is separated from client logic.
- Related objects are created together.
- Concrete implementations are hidden from the client.
- Switching between product families becomes easier.
- The client depends on interfaces rather than concrete classes.

---

## 8. Simple Factory vs Factory Method vs Abstract Factory

| Pattern | Main purpose |
|---|---|
| Simple Factory | Creates an object based on a condition or input. |
| Factory Method | Lets subclasses decide which concrete product to create. |
| Abstract Factory | Creates families of related products. |

**Remember the difference:**

- Simple Factory → One central factory chooses an object.
- Factory Method → Subclasses decide which object to create.
- Abstract Factory → A factory creates a family of related objects.

---

## 9. Advantages

1. Promotes loose coupling between client code and concrete classes.
2. Makes it easier to switch between product families.
3. Ensures related products are created by the same factory.
4. Supports the Open/Closed Principle when adding new product families.
5. Makes object creation easier to maintain and manage.

## 10. Disadvantages

1. Adding a new product type requires changes to the abstract factory and all concrete factories.
2. It introduces more interfaces and classes.
3. It can be unnecessarily complex for applications with only a few simple objects.

---

## 11. When Should We Use It?

Use Abstract Factory when:

- An application supports multiple regions or countries.
- Each region has its own family of related implementations.
- Related objects must be used together.
- You want to switch between product families without changing client code.

**Example:** A banking application supporting different countries, where each country has its own loan validation and payment processing implementations.

## 12. Interview Revision Questions

1. What is the Abstract Factory Design Pattern?
2. Why is it called a factory of factories?
3. What is a product family?
4. What is the difference between Abstract Factory and Factory Method?
5. What is the responsibility of an abstract factory?
6. What is the responsibility of a concrete factory?
7. How does Abstract Factory help achieve loose coupling?
8. What happens when we introduce a new country?
9. What happens when we introduce a completely new product type?
10. What are the advantages and disadvantages of Abstract Factory?

---

## Final Revision

**Abstract Factory = Creational Design Pattern**

**Purpose:** Creates families of related objects without exposing their concrete implementation classes.

**In our Loan Processing System:**

```text
                 LoanFactory
                 /          \
                /            \
      India Factory       Mexico Factory
           |                    |
           |                    |
     India Products        Mexico Products
