# Chain of Responsibility – Practice Problems

## Objective

Practice the Chain of Responsibility Design Pattern through Java-based machine coding problems.

For each problem:
- Identify the handlers.
- Define the common handler contract.
- Implement the abstract base handler.
- Implement concrete handlers.
- Configure the chain.
- Handle requests according to the given requirements.

**Important:** Do not modify predefined classes or method signatures unless explicitly mentioned.

---

# Problem 1: Payment Approval System

**Difficulty:** Easy

### Problem Statement

Design a banking payment approval system using the Chain of Responsibility pattern.

A payment request must pass through different approval levels. Each approver has a maximum approval limit.

The system contains three approvers:

1. **JuniorApprover**
    - Can approve payments up to ₹10,000.
2. **ManagerApprover**
    - Can approve payments up to ₹50,000.
3. **DirectorApprover**
    - Can approve payments up to ₹1,00,000.

If an approver cannot approve the requested amount, the request must be forwarded to the next approver.

If no approver can approve the payment, the payment must be rejected.

### Requirements

- Define a common `Approver` interface.
- Implement `BaseApprover` to store the next approver.
- Implement `setNext()` and `getNext()`.
- Implement a shared forwarding helper.
- Each concrete approver must define its own approval limit.
- Configure the chain in `PaymentApproval`.
- Do not allow an unhandled payment to be approved.

### Predefined Structure

```java
interface Approver {
    void setNext(Approver next);
    Approver getNext();
    String approve(int amount);
}

abstract class BaseApprover implements Approver {
    // TODO: Store next approver
    // TODO: Implement setNext() and getNext()
    // TODO: Implement shared forwarding helper
}

class JuniorApprover extends BaseApprover {
    // TODO
}

class ManagerApprover extends BaseApprover {
    // TODO
}

class DirectorApprover extends BaseApprover {
    // TODO
}

class PaymentApproval {
    private Approver head;

    // TODO: Configure chain
    // TODO: Submit payment request
}
```

### Expected Behaviour

| Input Amount | Expected Result |
|---|---|
| 5,000 | Junior approved |
| 25,000 | Manager approved |
| 75,000 | Director approved |
| 1,50,000 | Payment rejected |

### Edge Cases

- Zero amount
- Negative amount
- Amount exceeding the highest approval limit
- Empty approval chain

---

# Problem 2: Customer Support Ticket System

**Difficulty:** Easy–Medium

### Problem Statement

Design a customer support ticket system where customer complaints are processed by different support levels.

The system contains three handlers:

1. **BasicSupport**
    - Handles `PASSWORD`
    - Handles `LOGIN`
2. **TechnicalSupport**
    - Handles `BUG`
    - Handles `APP_CRASH`
3. **SeniorSupport**
    - Handles `PAYMENT_FAILURE`
    - Handles `SECURITY_BREACH`

Each handler must check whether it can resolve the incoming issue.

If the issue matches its supported category, the handler must add a resolution message to a shared output list.

After processing, the handler must always forward the request to the next handler.

If no handler supports the issue, the output list should remain empty.

### Requirements

- Define a common `SupportHandler` interface.
- Implement `BaseSupportHandler`.
- Store the next handler in the base class.
- Implement a shared `forward()` helper.
- Each concrete handler must define its supported issue types.
- All handlers must use the same output list.
- Every handler must forward the request, even after resolving it.

### Predefined Structure

```java
interface SupportHandler {
    void setNext(SupportHandler next);
    SupportHandler getNext();
    void handle(String issue, List<String> result);
}

abstract class BaseSupportHandler implements SupportHandler {
    // TODO: Store next handler
    // TODO: Implement setNext() and getNext()
    // TODO: Implement protected forward()
}

class BasicSupport extends BaseSupportHandler {
    // TODO
}

class TechnicalSupport extends BaseSupportHandler {
    // TODO
}

class SeniorSupport extends BaseSupportHandler {
    // TODO
}

class SupportRouter {
    // TODO: Build chain
    // TODO: Submit issue
}
```

### Expected Behaviour

```text
Input: LOGIN
Output: [BasicSupport resolved LOGIN]

Input: APP_CRASH
Output: [TechnicalSupport resolved APP_CRASH]

Input: SECURITY_BREACH
Output: [SeniorSupport resolved SECURITY_BREACH]

Input: UNKNOWN
Output: []
```

### Edge Cases

- Unsupported complaint
- Empty chain
- Null issue
- Multiple handlers in the chain

---

# Problem 3: Expense Claim Validation

**Difficulty:** Medium

### Problem Statement

Design an expense claim validation system using Chain of Responsibility.

An employee submits an expense claim. Before approval, the claim must pass through three validation stages.

The system contains three validators:

1. **AmountValidator**
    - Expense amount must be greater than zero.
2. **ReceiptValidator**
    - A receipt must be attached.
3. **ManagerValidator**
    - If the expense amount exceeds ₹20,000, manager approval is mandatory.

A claim must pass all validation stages to be approved.

If any validator rejects the claim, processing must stop immediately.

If all validations pass, the claim should be approved.

### Requirements

- Define a common `ClaimValidator` interface.
- Implement `BaseClaimValidator`.
- Store the next validator.
- Implement a shared forwarding helper.
- Each concrete validator must implement its own validation rule.
- Stop processing when validation fails.
- Approve the claim only after all validators pass.

### Predefined Structure

```java
interface ClaimValidator {
    void setNext(ClaimValidator next);
    ClaimValidator getNext();
    String validate(ExpenseClaim claim);
}

abstract class BaseClaimValidator implements ClaimValidator {
    // TODO: Store next validator
    // TODO: Implement setNext() and getNext()
    // TODO: Implement shared forwarding helper
}

class AmountValidator extends BaseClaimValidator {
    // TODO
}

class ReceiptValidator extends BaseClaimValidator {
    // TODO
}

class ManagerValidator extends BaseClaimValidator {
    // TODO
}

class ExpenseClaim {
    int amount;
    boolean receiptAttached;
    boolean managerApproved;
}

class ClaimProcessor {
    // TODO: Configure chain
    // TODO: Process expense claim
}
```

### Expected Behaviour

| Amount | Receipt | Manager Approval | Expected Result |
|---:|---|---|---|
| -500 | Yes | Yes | Rejected |
| 5,000 | No | Yes | Rejected |
| 30,000 | Yes | No | Rejected |
| 30,000 | Yes | Yes | Approved |
| 10,000 | Yes | No | Approved |

### Edge Cases

- Negative amount
- Zero amount
- Missing receipt
- Missing manager approval
- Empty validation chain

---

# Problem 4: HTTP Request Middleware

**Difficulty:** Medium

### Problem Statement

Design an API Gateway middleware pipeline using Chain of Responsibility.

Every incoming API request must pass through multiple middleware components before reaching the application.

The system contains three middleware handlers:

1. **AuthenticationMiddleware**
    - Checks whether the authentication token is valid.
2. **AuthorizationMiddleware**
    - Checks whether the user has the required role.
3. **RateLimitMiddleware**
    - Checks whether the user has exceeded the allowed request limit.

A request should proceed to the next middleware only if the current middleware validation succeeds.

If any middleware rejects the request, processing must stop immediately.

If all middleware validations pass, the request should be accepted.

### Requirements

- Define a common `Middleware` interface.
- Implement `BaseMiddleware`.
- Store the next middleware.
- Implement a shared forwarding helper.
- Each middleware must implement its own validation logic.
- Stop forwarding when validation fails.
- Return the final request status.

### Predefined Structure

```java
interface Middleware {
    void setNext(Middleware next);
    Middleware getNext();
    String check(ApiRequest request);
}

abstract class BaseMiddleware implements Middleware {
    // TODO: Store next middleware
    // TODO: Implement setNext() and getNext()
    // TODO: Implement shared forwarding helper
}

class AuthenticationMiddleware extends BaseMiddleware {
    // TODO
}

class AuthorizationMiddleware extends BaseMiddleware {
    // TODO
}

class RateLimitMiddleware extends BaseMiddleware {
    // TODO
}

class ApiRequest {
    String token;
    String role;
    int requestCount;
}

class ApiGateway {
    // TODO: Configure middleware chain
    // TODO: Process API request
}
```

### Expected Behaviour

| Token Valid | Role Authorized | Within Rate Limit | Expected Result |
|---|---|---|---|
| No | Yes | Yes | Authentication failed |
| Yes | No | Yes | Authorization failed |
| Yes | Yes | No | Rate limit exceeded |
| Yes | Yes | Yes | Request accepted |

### Edge Cases

- Null request
- Empty token
- Invalid role
- Request count at the exact limit
- Empty middleware chain

---

# Practice Checklist

Track your progress as you solve each problem.

- [ ] Problem 1 – Payment Approval System
- [ ] Problem 2 – Customer Support Ticket System
- [ ] Problem 3 – Expense Claim Validation
- [ ] Problem 4 – HTTP Request Middleware

## Self-Review Questions

After implementing each problem, answer these questions:

1. Why did we create an interface?
2. Why is an abstract base handler useful?
3. What information does each handler store?
4. Who configures the chain?
5. Does the handler process the request, forward it, or both?
6. When should forwarding stop?
7. What happens when the next handler is null?
8. How does this design reduce coupling?

## Learning Goal

By completing these four problems, understand the different ways Chain of Responsibility can be implemented:

- Single handler processes the request.
- Multiple handlers process the same request.
- Sequential validation with early rejection.
- Middleware pipeline with conditional forwarding.

**Rule:** Try implementing each problem independently before looking at any solution.
