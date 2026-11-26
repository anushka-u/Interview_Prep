# State Design Pattern — Basics & ATM Example

## 1. Definition

The **State Design Pattern** is a behavioral design pattern in which an object's behaviour changes when its internal state changes.

Instead of writing multiple `if-else` or `switch` statements to handle different states, we encapsulate state-specific behaviour into separate classes.

The main object, called the **Context**, maintains a reference to its current State and delegates operations to that State.

### One-line Definition

**State Pattern = Encapsulate state-specific behaviour into separate classes and let the Context delegate operations to its current State object.**

---

## 2. Why Do We Need State Pattern?

Consider an ATM.

The same operation can behave differently depending on the current state:

- If no card is inserted, withdrawal is not allowed.
- If a card is inserted but the PIN is not verified, withdrawal is not allowed.
- If the PIN is verified, withdrawal is allowed.

Without the State pattern, we might write multiple `if-else` conditions in the ATM class.

As the number of states and operations increases, the code becomes difficult to maintain.

With the State pattern, each state handles its own behaviour.

---

## 3. Components of State Pattern

| Component | Responsibility | ATM Example |
|---|---|---|
| State Interface | Defines common operations | ATMState |
| Concrete State | Implements state-specific behaviour | IdleState, CardInsertedState, AuthenticatedState |
| Context | Maintains current State and delegates operations | ATM |
| Client | Creates and uses the Context | ATMMain |

**Important:** State Pattern has a Context, not an Invoker.

The Invoker belongs to the Command Pattern. In this ATM example, the ATM itself is the Context.

---

## 4. ATM Example — Understanding the Flow

### States

We have three states:

1. `IdleState` — No card is inserted.
2. `CardInsertedState` — Card is inserted, but PIN is not verified.
3. `AuthenticatedState` — PIN is successfully verified.

### State Transition Diagram

```text
       insertCard()
 IDLE ----------------> CARD_INSERTED
                            |
                            | enterPin(valid PIN)
                            v
                       AUTHENTICATED
                            |
                            | ejectCard()
                            v
                           IDLE
```

Also, ejecting the card from `CardInsertedState` takes the ATM back to `IdleState`.

### Behaviour of Each State

| Current State | insertCard() | enterPin() | withdraw() | ejectCard() |
|---|---|---|---|---|
| Idle | Accept | Reject | Reject | Reject |
| CardInserted | Reject | Validate PIN | Reject | Return to Idle |
| Authenticated | Reject | Reject | Allow | Return to Idle |

### Example Execution

Assume:
- Valid PIN: `1234`
- Account balance: 1000
- ATM cash available: 500

| Step | Operation | Expected Behaviour |
|---|---|---|
| 1 | withdraw(100) | Reject — card not inserted |
| 2 | insertCard() | Move to CardInsertedState |
| 3 | withdraw(100) | Reject — PIN not verified |
| 4 | enterPin("0000") | Reject — invalid PIN |
| 5 | enterPin("1234") | Move to AuthenticatedState |
| 6 | withdraw(100) | Successful withdrawal |
| 7 | ejectCard() | Move to IdleState |

---

## 5. ATM — Java Starter Template

**Your task:** Complete all TODOs. Do not add an Invoker.

The `ATM` class is the Context. Each Concrete State decides how an operation should behave in that state.

```java
import java.util.Objects;

// 1. STATE INTERFACE
interface ATMState {
    void insertCard();
    void enterPin(String pin);
    void withdraw(double amount);
    void ejectCard();
}

// 2. CONTEXT
class ATM {

    private ATMState currentState;
    private final String validPin;
    private double accountBalance;
    private double cashAvailable;

    public ATM(String validPin, double accountBalance, double cashAvailable) {
        this.validPin = validPin;
        this.accountBalance = accountBalance;
        this.cashAvailable = cashAvailable;

        this.currentState = new IdleState(this);
    }

    public void setState(ATMState state) {
        // TODO 1: Update current state
    }

    public void insertCard() {
        // TODO 2: Delegate to current state
    }

    public void enterPin(String pin) {
        // TODO 3: Delegate to current state
    }

    public void withdraw(double amount) {
        // TODO 4: Delegate to current state
    }

    public void ejectCard() {
        // TODO 5: Delegate to current state
    }

    // Helper methods for Concrete States

    public boolean isValidPin(String pin) {
        return Objects.equals(validPin, pin);
    }

    public boolean canWithdraw(double amount) {
        return amount > 0
                && amount <= accountBalance
                && amount <= cashAvailable;
    }

    public void completeWithdrawal(double amount) {
        // TODO 6: Update account balance and ATM cash
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public double getCashAvailable() {
        return cashAvailable;
    }
}

// 3. CONCRETE STATE: IDLE
class IdleState implements ATMState {

    private final ATM atm;

    public IdleState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        // TODO 7: Accept card and transition to CardInsertedState
    }

    @Override
    public void enterPin(String pin) {
        // TODO 8: Reject PIN entry
    }

    @Override
    public void withdraw(double amount) {
        // TODO 9: Reject withdrawal
    }

    @Override
    public void ejectCard() {
        // TODO 10: Handle no-card scenario
    }
}

// 4. CONCRETE STATE: CARD INSERTED
class CardInsertedState implements ATMState {

    private final ATM atm;

    public CardInsertedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        // TODO 11: Reject second card
    }

    @Override
    public void enterPin(String pin) {
        // TODO 12: Validate PIN
        // TODO 13: Move to AuthenticatedState on success
        // TODO 14: Remain in current state on failure
    }

    @Override
    public void withdraw(double amount) {
        // TODO 15: Reject withdrawal before authentication
    }

    @Override
    public void ejectCard() {
        // TODO 16: Eject card and move to IdleState
    }
}

// 5. CONCRETE STATE: AUTHENTICATED
class AuthenticatedState implements ATMState {

    private final ATM atm;

    public AuthenticatedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        // TODO 17: Reject second card
    }

    @Override
    public void enterPin(String pin) {
        // TODO 18: Handle PIN entry after authentication
    }

    @Override
    public void withdraw(double amount) {
        // TODO 19: Validate withdrawal amount
        // TODO 20: Perform withdrawal when valid
        // TODO 21: Keep ATM authenticated after withdrawal
    }

    @Override
    public void ejectCard() {
        // TODO 22: End session and move to IdleState
    }
}

// 6. CLIENT
public class ATMMain {

    public static void main(String[] args) {

        ATM atm = new ATM("1234", 1000.0, 500.0);

        // TODO 23: Test all ATM operations

        // Try withdrawal without card
        // Insert card
        // Try withdrawal without PIN
        // Enter invalid PIN
        // Enter valid PIN
        // Withdraw money
        // Check balance
        // Eject card
    }
}
```

---

## 6. Expected Test Scenarios

- [ ] ATM starts in IdleState.
- [ ] Withdrawal without inserting a card is rejected.
- [ ] PIN entry without a card is rejected.
- [ ] Inserting a card moves ATM to CardInsertedState.
- [ ] Withdrawal before PIN verification is rejected.
- [ ] Invalid PIN does not authenticate the user.
- [ ] Valid PIN moves ATM to AuthenticatedState.
- [ ] Successful withdrawal updates balance and ATM cash.
- [ ] Negative and zero withdrawal amounts are rejected.
- [ ] Insufficient balance is handled.
- [ ] Insufficient ATM cash is handled.
- [ ] Successful withdrawal does not automatically eject the card.
- [ ] Ejecting the card returns ATM to IdleState.

---

## 7. Important Points to Remember

- Context maintains the current State reference.
- State Interface defines common operations.
- Concrete States implement state-specific behaviour.
- Concrete States can trigger transitions by updating the Context's State.
- The Client interacts with the Context instead of directly calling Concrete State methods.

**Remember:** The Context knows which State is active. The active State decides how the object should behave.