# Factory Design Pattern

**Type:** Creational Design Pattern

## 1. What?
Factory Pattern encapsulates object creation logic in a separate class instead of creating objects directly inside business logic.

**Remember:** Separate object creation from object usage.

## 2. Problem Without Factory

```java
if (type.equals("CAR")) {
    transport = new Car();
} else if (type.equals("BIKE")) {
    transport = new Bike();
} else if (type.equals("BUS")) {
    transport = new Bus();
}
```

- Business logic is responsible for object creation.
- Tight coupling with concrete classes.
- Changes are required whenever a new type is introduced.

## 3. Solution With Factory

**Structure:**
- `Transport` → Common interface.
- `Car`, `Bike`, `Bus` → Concrete implementations.
- `TransportFactory` → Creates the required object.
- `TransportService` → Uses the returned object.

```java
class TransportFactory {

    public static Transport createTransport(String type) {
        switch (type.toUpperCase()) {
            case "CAR": return new Car();
            case "BIKE": return new Bike();
            case "BUS": return new Bus();
            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }
}
```

```java
class TransportService {

    public void bookTransport(String type) {
        Transport transport =
                TransportFactory.createTransport(type);

        transport.drive();
    }
}
```

## 4. Benefits

- Centralizes object creation.
- Reduces coupling between client and concrete classes.
- Separates object creation from business logic.
- Makes creation logic easier to maintain.

## 5. Important Interview Points

- Factory is a **Creational Pattern**.
- Client depends on an abstraction, not concrete implementations.
- Factory hides object creation details.
- Adding a new type still requires updating this simple factory.
- Factory Pattern does not automatically eliminate `if-else`.

## 6. One-Line Interview Answer

"Factory Pattern provides a centralized way to create objects while hiding their instantiation details from the client, reducing coupling between object creation and business logic."

**Memory Trick:**

`Client → Factory → Concrete Object → Common Interface`

**Think:** Who creates the object? Factory. Who uses it? Client.



## Important Concepts & Interview Questions

### 1. What is Factory Design Pattern?
Factory Pattern is a creational design pattern that separates object creation from business logic by delegating object creation to a separate factory class.

**Remember:** Factory creates objects; Service uses them.

### 2. Does Factory Pattern eliminate code modifications?
No. When a new vehicle type is introduced, we still need to modify the factory class to handle its creation. However, the service class remains unchanged.

**Remember:** It reduces modifications in business logic, but doesn't eliminate all modifications.

### 3. Why use an Interface instead of concrete classes?
All vehicle classes (Car, Bike, Bus, Truck) implement the common `Transport` interface.

The service depends on `Transport` rather than individual classes.

Benefits:
- Polymorphism
- Loose coupling
- Programming to an interface
- Easier extensibility and maintenance

**Remember:** One interface reference can hold objects of different implementing classes.

### 4. What is the role of the Interface?
An interface defines a common contract that all implementing classes must follow.

For example:
- Car implements Transport.
- Bike implements Transport.
- Bus implements Transport.

The service can call common methods without knowing the actual object type.

### 5. What if a new class doesn't implement Transport?
The service cannot directly use that class through a `Transport` reference.

For example, if Aeroplane doesn't implement `Transport`, we cannot assign its object to a `Transport` variable.

**Remember:** An object must satisfy the expected interface contract.

### 6. Factory Pattern vs Dependency Injection
- Factory Pattern: Responsible for object creation.
- Dependency Injection: Provides required dependencies from outside a class.

They are different concepts but can work together.

### 7. Static Factory vs Instance Factory
- Static factory: Call using the class name; no factory object required.
- Instance factory: Requires a factory object; can be injected and easily mocked.

Neither approach is mandatory for Factory Pattern.

### 8. Can we test Factory and Service separately?
Yes.

- Factory test: Verify that the correct object is created.
- Service test: Mock the factory and transport, then verify that the service calls the factory and invokes `drive()`.

**Mockito keywords:** `@Mock`, `@InjectMocks`, `when().thenReturn()`, `verify()`.

---

## Final Memory Trick

`Interface → Common Contract`

`Factory → Object Creation`

`Service → Business Logic`

`Dependency Injection → Supplies Dependencies`

`Polymorphism → Different Objects, Same Interface`

`Loose Coupling → Depend on Abstractions, Not Concrete Classes`

**Important limitation:** Simple Factory centralizes object creation but may still require modification whenever a new implementation is added. Factory Method Pattern provides a more extensible alternative.