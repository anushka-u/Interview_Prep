# Command Design Pattern

## 1. What is the Command Design Pattern?

**Definition:**

The Command Design Pattern is a Behavioral Design Pattern that encapsulates a request as an object, allowing us to parameterize clients with different requests and separate the object that invokes an operation from the object that performs it.

**In simple words:**

Command Pattern converts an action or request into an independent object.

Instead of directly calling a method on an object, we create a Command object that knows which operation needs to be performed and delegates that operation to the appropriate Receiver.

### Why do we need it?

Imagine developing a banking application where customers can perform multiple operations:

- Deposit money.
- Withdraw money.
- Check account balance.

A simple implementation would directly call the respective methods:

```java
account.deposit(5000);
account.withdraw(2000);
account.checkBalance();
```

This works perfectly for a basic application.

However, as the application grows, we might need additional capabilities:

- Execute requests independently.
- Queue requests for later execution.
- Maintain a history of executed operations.
- Support undo functionality.
- Separate the request sender from the actual business logic.

Command Pattern helps us achieve this separation by representing each request as an object.

---

## 2. Real-World Example: Banking Application

Let's develop a banking application where customers can perform three operations:

1. Deposit money.
2. Withdraw money.
3. Check account balance.

We will use the Command Design Pattern to implement these operations.

## 3. Components of Command Design Pattern

There are five important components:

| Component | Responsibility |
|---|---|
| Client | Creates concrete command objects and connects them with the Receiver. |
| Command | Defines a common interface, usually containing an `execute()` method. |
| Concrete Command | Implements the Command interface and delegates the operation to the Receiver. |
| Receiver | Contains and executes the actual business logic. |
| Invoker | Triggers the command without knowing the details of the operation. |

### 3.1 Command Interface

The Command interface defines a common method that every concrete command must implement.

```java
public interface BankCommand {
    void execute();
}
```

Here, `execute()` represents the action that needs to be performed.

### 3.2 Receiver – BankAccount

The Receiver is responsible for performing the actual business operation.

In our example, `BankAccount` is the Receiver.

```java
public class BankAccount {

    private double balance;

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {

        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }
}
```

**Important points:**

- BankAccount contains the actual business logic.
- It does not need to know which command is being invoked.
- It does not depend on the Invoker.
- It simply performs the operation requested by the Concrete Command.

### 3.3 Concrete Commands

Concrete Commands implement the `BankCommand` interface.

Each command represents a specific request and knows which Receiver method needs to be called.

**A. DepositCommand**

```java
public class DepositCommand implements BankCommand {

    private final BankAccount account;
    private final double amount;

    public DepositCommand(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deposit(amount);
    }
}
```

Responsibility:

- Stores the BankAccount reference.
- Stores the deposit amount.
- Calls the Receiver's `deposit()` method.

**B. WithdrawCommand**

```java
public class WithdrawCommand implements BankCommand {

    private final BankAccount account;
    private final double amount;

    public WithdrawCommand(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.withdraw(amount);
    }
}
```

Responsibility:

- Stores the BankAccount reference.
- Stores the withdrawal amount.
- Calls the Receiver's `withdraw()` method.

**C. BalanceCommand**

```java
public class BalanceCommand implements BankCommand {

    private final BankAccount account;

    public BalanceCommand(BankAccount account) {
        this.account = account;
    }

    @Override
    public void execute() {
        account.checkBalance();
    }
}
```

Responsibility:

- Stores the BankAccount reference.
- Calls the Receiver's `checkBalance()` method.

### 3.4 Invoker – BankInvoker

The Invoker is responsible for triggering the command.

It does not need to know whether the incoming command is Deposit, Withdraw, or Balance.

It only knows that the command implements the `BankCommand` interface and provides an `execute()` method.

```java
public class BankInvoker {

    public void executeCommand(BankCommand command) {
        command.execute();
    }
}
```

**Important:**

The Invoker does not contain the actual banking business logic.

It simply triggers the command.

### 3.5 Client – Main Class

The Client creates the Receiver, Concrete Commands, and Invoker.

```java
public class Main {

    public static void main(String[] args) {

        // Creating Receiver
        BankAccount account = new BankAccount();

        // Creating Invoker
        BankInvoker invoker = new BankInvoker();

        // Creating Concrete Commands
        BankCommand deposit =
                new DepositCommand(account, 5000);

        BankCommand withdraw =
                new WithdrawCommand(account, 2000);

        BankCommand balance =
                new BalanceCommand(account);

        // Executing Commands
        invoker.executeCommand(deposit);
        invoker.executeCommand(withdraw);
        invoker.executeCommand(balance);
    }
}
```

**Output:**

```text
Deposited: 5000.0
Withdrawn: 2000.0
Current Balance: 3000.0
```

---

## 4. Execution Flow

Let's understand how the request travels through the different components.

```text
                  CLIENT
                    |
                    | Creates a Command
                    |
                    v
              BANK INVOKER
                    |
                    | executeCommand()
                    |
                    v
             BANK COMMAND
                    |
                    | execute()
                    |
                    v
           CONCRETE COMMAND
                    |
                    | Calls Receiver method
                    |
                    v
              BANK ACCOUNT
                    |
                    | Performs actual logic
                    |
                    v
               OPERATION
```

### Example: Deposit Operation

1. Client creates a `DepositCommand` object.
2. The command stores the BankAccount reference and deposit amount.
3. Client passes the command to the Invoker.
4. Invoker calls `command.execute()`.
5. The actual object is `DepositCommand`, so Java runtime polymorphism invokes its `execute()` implementation.
6. DepositCommand calls `account.deposit(amount)`.
7. BankAccount performs the actual deposit operation.

### Important Observation

Suppose we change the command:

```java
BankCommand command =
        new DepositCommand(account, 5000);
```

To:

```java
BankCommand command =
        new WithdrawCommand(account, 2000);
```

The Invoker remains unchanged.

```java
invoker.executeCommand(command);
```

This is possible because the Invoker depends on the common `BankCommand` interface rather than concrete command classes.

---

## 5. Strategy Pattern vs Command Pattern

Both patterns are Behavioral Design Patterns.

Both can use interfaces, concrete implementations, and runtime polymorphism.

However, their intentions are different.

| Strategy Pattern | Command Pattern |
|---|---|
| Encapsulates interchangeable algorithms or behaviours. | Encapsulates a request as an object. |
| Focuses on selecting which algorithm to use. | Focuses on representing and managing requests. |
| Different strategies provide different ways to perform a task. | Different commands represent different actions. |
| Example: UPI, Card, Net Banking payment strategies. | Example: Deposit, Withdraw, Check Balance commands. |
| Helps change the behaviour used by a Context. | Helps decouple the request invoker from the request receiver. |

### Understanding with Banking Examples

**Strategy Pattern:**

Imagine a customer wants to make a payment.

The application can select:

- UPI Payment Strategy.
- Card Payment Strategy.
- Net Banking Strategy.

The objective is to select an interchangeable payment algorithm.

**Command Pattern:**

Imagine a customer wants to perform a banking operation.

The application creates:

- Deposit Command.
- Withdraw Command.
- Balance Command.

The objective is to represent the requested action as an independent object.

**Remember:**

Strategy answers:

*Which algorithm or behaviour should I use?*

Command answers:

*Which request or action should I execute?*

---

## 6. Advantages of Command Pattern

1. **Loose Coupling:** Invoker does not depend on concrete command implementations or Receiver details.

2. **Request Encapsulation:** Each request is represented as an independent object.

3. **Extensibility:** New commands can be introduced without changing the Invoker.

4. **Separation of Responsibilities:** Client, Invoker, Command, and Receiver have separate responsibilities.

5. **Flexible Execution:** Commands can be passed around and managed independently.

6. **Future Extensions:** Supports features such as command history, queueing, scheduling, and undo when designed accordingly.

---

## 7. When Should We Use Command Pattern?

Command Pattern is useful when:

- We want to represent requests as independent objects.
- We want to decouple the object sending a request from the object performing it.
- We need to queue or schedule requests.
- We want to maintain a history of executed operations.
- We need to implement undo or redo functionality.

---

## 8. Important Interview Questions

**Q1. What is the Command Design Pattern?**

Command Pattern encapsulates a request as an object, separating the object that invokes the operation from the object that performs it.

**Q2. What is the responsibility of the Receiver?**

The Receiver performs the actual business logic. In our example, BankAccount is the Receiver.

**Q3. Does the Invoker know about concrete commands?**

No. The Invoker depends on the Command interface and calls its `execute()` method.

**Q4. What is the responsibility of a Concrete Command?**

It represents a specific request and delegates the operation to the appropriate Receiver method.

**Q5. Can we add a new command without modifying the Invoker?**

Yes. We can introduce another class implementing the Command interface. The Invoker can execute it without knowing its concrete type.

**Q6. How is Command Pattern different from Strategy Pattern?**

Strategy encapsulates interchangeable algorithms, whereas Command encapsulates requests as objects.

**Q7. Is the Invoker responsible for executing business logic?**

No. It only triggers the command. The Receiver performs the actual business operation.

**Q8. Is an Invoker interface mandatory?**

No. An Invoker can be a concrete class or an existing component that calls the Command's `execute()` method.

---

## 9. Future Extensions – To Be Implemented Separately

We will extend this basic implementation in separate exercises.

- [ ] Undo functionality using an `undo()` method.
- [ ] Command history.
- [ ] Queueing commands for later execution.
- [ ] Retry and failure handling.

These are not part of our current basic implementation. We will build them incrementally to understand how Command Pattern supports these requirements.

---

## 10. Quick Revision

| Term | Remember |
|---|---|
| Command | Represents a request as an object. |
| Receiver | Performs the actual business operation. |
| Concrete Command | Decides which Receiver method to call. |
| Invoker | Triggers the command through `execute()`. |
| Client | Creates and configures commands. |

**One-line revision:**

Command Pattern encapsulates requests as objects, allowing the Invoker to trigger operations without knowing their implementation details, while Concrete Commands delegate the actual work to the Receiver.
