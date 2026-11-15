# Behavioral Design Patterns – Java

## 1. What are Behavioral Design Patterns?

### Definition

Behavioral Design Patterns are design patterns that focus on how objects communicate, interact, and distribute responsibilities among themselves.

In simple words:

- **Creational Patterns:** Deal with how objects are created.
- **Structural Patterns:** Deal with how objects are connected or structured.
- **Behavioral Patterns:** Deal with how objects communicate and work together.

### Understanding with a Banking Application Example

Imagine you are developing a banking application where a customer initiates a payment.

Several objects need to communicate with each other:

- `PaymentService`
- `PaymentProcessor`
- `NotificationService`
- `TransactionLogger`

Now, consider the following questions:

- Should `PaymentService` directly control everything?
- Should objects notify each other when something changes?
- Should we encapsulate different payment algorithms?
- How should responsibilities be distributed among these objects?

Behavioral Design Patterns help us solve these problems by defining better ways for objects to communicate and collaborate.

---

## 2. Types of Behavioral Design Patterns

There are **11 classic Gang of Four (GoF) Behavioral Design Patterns**.

| # | Design Pattern | What It Does |
|---|---|---|
| 1 | Strategy | Allows us to switch between different algorithms. |
| 2 | Observer | Notifies dependent objects when something changes. |
| 3 | State | Changes object behavior based on its current state. |
| 4 | Command | Encapsulates a request as an object. |
| 5 | Chain of Responsibility | Passes a request through multiple handlers. |
| 6 | Template Method | Defines the skeleton of an algorithm. |
| 7 | Iterator | Provides a way to traverse a collection. |
| 8 | Mediator | Manages communication between multiple objects. |
| 9 | Memento | Saves and restores an object's previous state. |
| 10 | Visitor | Adds operations to objects without changing their classes. |
| 11 | Interpreter | Defines rules for interpreting a language or expressions. |

---

## 3. Behavioral Design Patterns – Quick Revision

| Pattern | Key Concept |
|---|---|
| Strategy | Interchangeable algorithms |
| Observer | One-to-many notification |
| State | Behavior depends on state |
| Command | Request as an object |
| Chain of Responsibility | Sequential request handling |
| Template Method | Fixed algorithm structure |
| Iterator | Sequential traversal |
| Mediator | Centralized communication |
| Memento | State restoration |
| Visitor | New operations without modifying existing classes |
| Interpreter | Expression and grammar interpretation |

---

## 4. Learning Approach

Each design pattern will be studied using the following structure:

1. Definition
2. Problem statement – Why do we need this pattern?
3. Real-world example
4. Banking application example
5. Class diagram and object relationships
6. Java implementation
7. Step-by-step code explanation
8. Advantages and disadvantages
9. When to use and when not to use
10. Interview questions and practical scenarios

---

## 5. Important Notes

- An application can use multiple design patterns simultaneously.
- Design patterns are reusable solutions to commonly occurring software design problems.
- We should not forcefully implement a design pattern when a simpler solution is sufficient.
- The primary objective is to write maintainable, flexible, reusable, and loosely coupled code.

---
