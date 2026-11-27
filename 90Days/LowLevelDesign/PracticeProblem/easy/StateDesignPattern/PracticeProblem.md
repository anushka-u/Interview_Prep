# State Design Pattern — Machine Coding Practice

> **Goal:** Practise the State Design Pattern through Easy, Medium and Hard machine-coding problems.

## Practice Roadmap

| Problem | Difficulty | Application |
|---|---|---|
| 1. ATM State Management | Easy | ATM |
| 2. Vending Machine | Medium | Shopping / Retail |
| 3. Elevator Controller | Hard | Building Management |

**Prerequisite:** Understand the State pattern and ATM example from `State-Design-Pattern-Basics.md`.

---

# Problem 1: ATM State Management

**Difficulty:** Easy  
**Pattern:** State

### Problem Statement

Design an ATM system that supports card insertion, PIN verification, cash withdrawal and card ejection.

The ATM's behaviour must change according to its current state.

### Required States

1. `IdleState`
2. `CardInsertedState`
3. `AuthenticatedState`

### Required Operations

```java
void insertCard();
void enterPin(String pin);
void withdraw(double amount);
void ejectCard();
```

### Business Rules

1. A card must be inserted before PIN entry.
2. PIN verification must happen before withdrawal.
3. An invalid PIN must not authenticate the user.
4. Withdrawal is allowed only after successful authentication.
5. Withdrawal must be rejected for zero or negative amounts.
6. Withdrawal must be rejected if the account balance is insufficient.
7. Withdrawal must be rejected if ATM cash is insufficient.
8. A successful withdrawal updates both account balance and ATM cash.
9. The user remains authenticated after a successful withdrawal.
10. Ejecting the card ends the session and returns the ATM to IdleState.

### Acceptance Criteria

- Correct state transitions.
- No withdrawal before authentication.
- Correct balance and cash updates.
- Proper handling of invalid operations.
- No large if-else or switch statement for state-specific behaviour.

### Optional Extension

Allow only three incorrect PIN attempts. After the third failed attempt, introduce a `BlockedState`.

---

# Problem 2: Vending Machine

**Difficulty:** Medium  
**Pattern:** State

## Problem Statement

Design a vending machine that accepts money, allows customers to select products, dispenses products and supports transaction cancellation.

The machine should behave differently based on its current state.

## Required States

1. `NoCoinState` — No money has been inserted.
2. `HasCoinState` — Money is available; a product can be selected.
3. `DispensingState` — A selected product is being dispensed.
4. `OutOfStockState` — No products are available.

## Context

Create a `VendingMachine` class.

It should maintain:

- Product inventory and quantity.
- Inserted money.
- Selected product.
- Current `VendingMachineState` reference.

## Required Operations

```java
void insertMoney(int amount);
void selectProduct(String productId);
void dispense();
void cancelTransaction();
```

You may add a refill operation to the Context.

## Business Rules

1. Money cannot be inserted when the entire machine is out of stock.
2. A product cannot be selected before money is inserted.
3. A product that does not exist or has zero quantity cannot be selected.
4. If inserted money is less than the product price, the transaction remains active.
5. Once enough money is inserted and a valid product is selected, transition to DispensingState.
6. Dispensing reduces inventory by one.
7. After dispensing, clear the selected product and transaction amount.
8. Return to NoCoinState if stock remains.
9. Transition to OutOfStockState if no products remain.
10. Cancelling before dispensing must refund the inserted amount.
11. Cancelling after a completed purchase must not refund the amount.
12. Refilling an empty machine should make it operational again.

## Scenarios to Test

- Select a product without inserting money.
- Insert money and select an unavailable product.
- Insert insufficient money and then add the remaining amount.
- Complete a purchase and verify inventory decreases.
- Cancel before selecting a product.
- Cancel after selecting but before dispensing.
- Purchase the final available product.
- Verify the machine transitions to OutOfStockState.
- Refill the machine and verify it becomes operational again.

## Design Questions

1. Should inventory and inserted money be maintained by the Context or Concrete States?
2. Which State should decide whether cancellation is allowed?
3. Should the machine enter OutOfStockState if one product is unavailable or only when all products are unavailable?

## Expected Design

- `VendingMachine` — Context.
- `VendingMachineState` — State Interface.
- `NoCoinState` — Concrete State.
- `HasCoinState` — Concrete State.
- `DispensingState` — Concrete State.
- `OutOfStockState` — Concrete State.
- `VendingMachineMain` — Client.

---

# Problem 3: Elevator Controller

**Difficulty:** Hard  
**Pattern:** State

## Problem Statement

Design an elevator controller for a building with multiple floors.

The elevator should accept floor requests, move up or down, open and close doors, and support emergency stops.

Its behaviour must depend on the current state.

## Required States

1. `IdleState` — Elevator is stationary with no active movement.
2. `MovingUpState` — Elevator is moving upwards.
3. `MovingDownState` — Elevator is moving downwards.
4. `DoorOpenState` — Elevator is stopped with doors open.
5. `EmergencyStoppedState` — Normal movement is disabled until a safe resume action occurs.

## Context

Create an `Elevator` class.

It should maintain:

- Current floor.
- Minimum and maximum floor.
- Pending floor requests.
- Current direction.
- Current `ElevatorState` reference.
- Any information needed to resume after an emergency stop.

## Required Operations

```java
void requestFloor(int floor);
void moveOneStep();
void openDoors();
void closeDoors();
void emergencyStop();
void resume();
```

You may adjust method names based on your design.

## Movement Policy

Use a simple LOOK-style scheduling policy:

1. Serve requests in the current direction while requests remain in that direction.
2. When no requests remain in that direction, reverse direction if requests remain on the opposite side.
3. If there are no pending requests, move to IdleState.
4. When the elevator reaches a requested floor, stop and open the doors.
5. Once doors close, continue according to the scheduling policy.

A method call to `moveOneStep()` can represent one simulation tick. Real motor or sensor integration is not required.

## Business Rules

1. Requests outside the configured floor range must be rejected.
2. An idle elevator with no requests should remain in IdleState.
3. The elevator must not move while doors are open.
4. Doors cannot be opened while the elevator is moving.
5. Reaching a requested floor removes that request.
6. Multiple requests must be retained.
7. Emergency stop can be triggered from any normal state.
8. While emergency-stopped, normal movement and door operations must be blocked.
9. Resume should select a safe next state based on pending requests.
10. If no pending requests remain after resuming, the elevator should be idle.

## Scenarios to Test

- Request a floor above the current floor.
- Request a floor below the current floor.
- Submit multiple requests in the same direction.
- Submit requests in both directions.
- Verify the LOOK-style direction policy.
- Verify the elevator stops at requested floors.
- Verify doors open at the requested floor.
- Verify the elevator does not move while doors are open.
- Verify movement resumes after doors close.
- Submit an invalid floor request.
- Trigger emergency stop while idle.
- Trigger emergency stop while moving.
- Trigger emergency stop while doors are open.
- Verify normal operations are blocked during emergency stop.
- Resume with pending requests.
- Resume without pending requests.

## Design Questions

1. Should pending requests belong to the Context or individual Concrete States?
2. Who should decide the next direction: the Context, a scheduler or Concrete States?
3. How will you prevent multiple states from independently modifying the current floor and direction?
4. What information should be retained during an emergency stop?
5. How should the elevator handle a request for its current floor?

## Expected Design

- `Elevator` — Context.
- `ElevatorState` — State Interface.
- `IdleState` — Concrete State.
- `MovingUpState` — Concrete State.
- `MovingDownState` — Concrete State.
- `DoorOpenState` — Concrete State.
- `EmergencyStoppedState` — Concrete State.
- `ElevatorMain` — Client.

---

# Recommended Implementation Approach

Follow this order for every problem:

1. Identify all possible states.
2. Identify operations supported by the system.
3. Identify how each operation behaves in each state.
4. Create the State interface.
5. Create the Context class.
6. Implement Concrete State classes.
7. Implement state transitions.
8. Create the Client/main method.
9. Test valid and invalid state transitions.

## Final Checklist

- [ ] Context maintains a current State reference.
- [ ] State interface defines common operations.
- [ ] Every Concrete State has its own behaviour.
- [ ] Context delegates operations to the current State.
- [ ] State transitions update the Context's current State.
- [ ] State-specific logic is not handled by a large if-else/switch block.
- [ ] Invalid operations are handled correctly.
- [ ] All important transitions have been tested.