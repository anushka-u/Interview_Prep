# Chain of Responsibility Design Pattern

## 1. Definition

Chain of Responsibility is a behavioral design pattern that allows a request to pass through a chain of multiple handlers. Each handler decides whether it can process the request or forward it to the next handler in the chain.

**In simple words:**

Instead of sending a request directly to a specific handler, we pass it through multiple handlers. Each handler checks whether it can handle the request. If it cannot, it forwards the request to the next handler.

## 2. Real-World Example – Banking Transaction Approval

Imagine a banking application where employees raise transaction approval requests.

The bank has three levels of approval:

| Handler | Approval Limit |
|---|---|
| Manager | Up to ₹1 lakh |
| Senior Manager | Up to ₹5 lakh |
| Director | Above ₹5 lakh |

Suppose an employee raises a transaction approval request for ₹3 lakh.

**Execution flow:**

1. The request first reaches the Manager.
2. The Manager checks whether the transaction amount is within their approval limit.
3. Since ₹3 lakh exceeds the Manager's limit, the Manager forwards the request to the Senior Manager.
4. The Senior Manager checks the transaction amount.
5. Since ₹3 lakh is within their approval limit, the Senior Manager approves the request.
6. The request stops here and does not reach the Director.

**Flow:**

`Client → Manager → Senior Manager → Director`

The request stops as soon as one handler processes it.

## 3. Main Components

### A. Handler

- Defines a common contract for all handlers.
- Maintains a reference to the next handler.
- Provides methods to configure the next handler and process requests.

### B. Concrete Handler

- Implements the Handler interface.
- Checks whether it can process the incoming request.
- If it can, it processes the request.
- Otherwise, it forwards the request to the next handler.

### C. Client

- Creates the handler objects.
- Configures the chain by connecting handlers.
- Sends the request to the first handler.
- Does not need to know which handler will eventually process the request.

## 4. Java Implementation – Customer Support Ticket System

### Problem Statement

Imagine a company has a customer support system with three levels of support executives.

Whenever a customer raises a complaint, it first reaches Level 1 Support.

Each support level can handle specific complaint types. If a handler cannot resolve a complaint, it forwards the request to the next handler.

### Complaint Types

```java
enum ComplaintType {
    PASSWORD_RESET,
    ACCOUNT_ISSUE,
    TECHNICAL_ISSUE,
    APP_ERROR,
    PAYMENT_FAILURE,
    CRITICAL_ISSUE
}
```

### Step 1: Create the Handler Interface

```java
interface SupportHandler {
    void setNextHandler(SupportHandler nextHandler);
    void handleRequest(ComplaintType complaint);
}
```

### Step 2: Implement Level 1 Support

Responsibilities:
- Handles PASSWORD_RESET.
- Handles ACCOUNT_ISSUE.
- Forwards all other complaints to the next handler.

```java
class Level1Support implements SupportHandler {

    private SupportHandler nextHandler;

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(ComplaintType complaint) {

        if (complaint == ComplaintType.PASSWORD_RESET ||
            complaint == ComplaintType.ACCOUNT_ISSUE) {

            System.out.println("Level 1 resolved " + complaint);

        } else if (nextHandler != null) {

            nextHandler.handleRequest(complaint);

        } else {

            System.out.println("Complaint cannot be resolved");
        }
    }
}
```

### Step 3: Implement Level 2 Support

Responsibilities:
- Handles TECHNICAL_ISSUE.
- Handles APP_ERROR.
- Forwards all other complaints to the next handler.

```java
class Level2Support implements SupportHandler {

    private SupportHandler nextHandler;

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(ComplaintType complaint) {

        if (complaint == ComplaintType.TECHNICAL_ISSUE ||
            complaint == ComplaintType.APP_ERROR) {

            System.out.println("Level 2 resolved " + complaint);

        } else if (nextHandler != null) {

            nextHandler.handleRequest(complaint);

        } else {

            System.out.println("Complaint cannot be resolved");
        }
    }
}
```

### Step 4: Implement Level 3 Support

Responsibilities:
- Handles PAYMENT_FAILURE.
- Handles CRITICAL_ISSUE.
- It is the terminal handler in this chain.

```java
class Level3Support implements SupportHandler {

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        // Not required for this terminal handler.
    }

    @Override
    public void handleRequest(ComplaintType complaint) {

        if (complaint == ComplaintType.PAYMENT_FAILURE ||
            complaint == ComplaintType.CRITICAL_ISSUE) {

            System.out.println("Level 3 resolved " + complaint);

        } else {

            System.out.println("Complaint cannot be resolved");
        }
    }
}
```

### Step 5: Configure the Chain in Main (Client)

The Client creates the handler objects and connects them in the required order.

```java
public class Main {

    public static void main(String[] args) {

        Level1Support level1 = new Level1Support();
        Level2Support level2 = new Level2Support();
        Level3Support level3 = new Level3Support();

        // Configure the chain
        level1.setNextHandler(level2);
        level2.setNextHandler(level3);

        // Submit requests through the first handler
        level1.handleRequest(ComplaintType.PASSWORD_RESET);
        level1.handleRequest(ComplaintType.TECHNICAL_ISSUE);
        level1.handleRequest(ComplaintType.PAYMENT_FAILURE);
        level1.handleRequest(ComplaintType.CRITICAL_ISSUE);
    }
}
```

### Expected Output

```text
Level 1 resolved PASSWORD_RESET
Level 2 resolved TECHNICAL_ISSUE
Level 3 resolved PAYMENT_FAILURE
Level 3 resolved CRITICAL_ISSUE
```

## 5. How the Request Travels Through the Chain

Example: PAYMENT_FAILURE

1. Main sends PAYMENT_FAILURE to Level 1.
2. Level 1 checks the complaint type.
3. Level 1 cannot handle it, so it forwards it to Level 2.
4. Level 2 checks the complaint type.
5. Level 2 cannot handle it, so it forwards it to Level 3.
6. Level 3 handles the complaint and prints the resolution message.
7. The request stops here.

**Important:** The Client only knows the first handler. It does not directly call Level 2 or Level 3.

## 6. Important Concepts to Remember

- Every handler maintains a reference to its next handler.
- The Client is responsible for configuring the chain.
- A handler either processes the request or forwards it.
- Handlers communicate through a common interface.
- A handler should not create its next handler using `new`.
- The chain can be reordered or extended without changing the Client's request logic.
- The terminal handler does not need a next handler.
- If no handler can process a request, the system should handle that case explicitly.

## 7. Advantages

1. **Loose Coupling:** The sender does not need to know which handler will process the request.
2. **Flexibility:** Handlers can be added, removed, or reordered.
3. **Single Responsibility:** Each handler is responsible for its own processing logic.
4. **Extensibility:** New handlers can be introduced without modifying the Client.

## 8. Disadvantages

1. A request might not be handled if no handler supports it.
2. A long chain can increase processing time.
3. Debugging can become difficult when requests pass through many handlers.
4. Incorrect chain configuration can cause unexpected behaviour.

## 9. When to Use Chain of Responsibility

Use this pattern when:

- Multiple handlers can potentially process a request.
- The appropriate handler depends on the request's conditions.
- You want to avoid tightly coupling the sender to a specific receiver.
- You want to configure the processing sequence dynamically.

**Practical examples:**
- Banking transaction approvals.
- Customer support escalation.
- Logging frameworks.
- Authentication and authorization middleware.
- Request validation pipelines.

## 10. Chain of Responsibility vs Strategy

| Chain of Responsibility | Strategy |
|---|---|
| Passes a request through multiple handlers. | Selects one interchangeable algorithm or behaviour. |
| Each handler decides whether to process or forward. | The selected strategy performs the operation. |
| Focuses on request handling and escalation. | Focuses on interchangeable algorithms. |
| Example: Support escalation. | Example: UPI, Card, or Net Banking payment. |

## 11. Interview Questions

**Q1. What is Chain of Responsibility?**

It is a behavioral design pattern that passes a request through a chain of handlers until one handler processes it or the chain ends.

**Q2. What are the main components?**

Handler, Concrete Handler, and Client.

**Q3. Who configures the chain?**

The Client configures the chain by connecting each handler to its next handler.

**Q4. Does a handler need to know the entire chain?**

No. Each handler only needs to know its immediate next handler.

**Q5. What happens if no handler can process a request?**

The request reaches the end of the chain and the application handles it as unresolved or unsupported.

**Q6. Why do we use an interface for the next handler?**

To achieve loose coupling. A handler depends on the common contract rather than a specific concrete implementation.

**Q7. Can multiple handlers process the same request?**

Yes. The pattern can be designed so