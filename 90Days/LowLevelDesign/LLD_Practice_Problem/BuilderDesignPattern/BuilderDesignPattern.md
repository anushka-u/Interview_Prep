# Builder Design Pattern

**Type:** Creational Design Pattern

## 1. What?

Builder Pattern separates complex object construction from the actual object, allowing objects to be created step by step.

**Remember:** Builder → Set Values → build() → Final Object.

## 2. Problem Without Builder

When a class has many optional fields:

- Multiple constructors become difficult to maintain.
- Constructor parameter order becomes confusing.
- Passing unnecessary `null` values reduces readability.

This is known as the **Telescoping Constructor Problem**.

## 3. Solution

Create a separate Builder class responsible for preparing the object's data.

Typical structure:

- `Employee` → Actual object.
- `Employee.Builder` → Holds field values and builds the object.

## 4. Important Code Concepts

**Builder constructor:** Initializes mandatory fields.

**Optional methods:** Set optional fields and return `this` to support method chaining.

```java
public Builder email(String email) {
    this.email = email;
    return this;
}
```

**build() method:** Creates and returns the final object.

```java
public Employee build() {
    return new Employee(this);
}
```

**Employee constructor:** Receives Builder and copies its values.

```java
private Employee(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.email = builder.email;
}
```

## 5. Example Usage

```java
Employee employee = new Employee.Builder(101, "Rahul")
        .email("rahul@gmail.com")
        .department("Engineering")
        .build();
```

## 6. Benefits

- Avoids multiple constructors.
- Improves readability.
- Supports optional parameters.
- Allows step-by-step object construction.
- Can enforce validation before object creation.
- Works well with immutable objects.

## 7. Interview Questions

**Q1. Why use Builder instead of constructors?**

Builder avoids telescoping constructors and makes object creation more readable when there are many optional fields.

**Q2. Does Builder eliminate constructors?**

No. The final object is still created through a constructor, usually receiving the Builder object.

**Q3. Why does each Builder method return `this`?**

To enable method chaining.

**Q4. What does build() do?**

It creates the final object using the values stored in the Builder.

**Q5. Is Builder useful for immutable objects?**

Yes. The object can have final fields and no setters, making it immutable after construction.

**Q6. Why is the Telescoping Constructor Pattern problematic? What issues can it create apart from code maintenance?**

**Answer:**

The Telescoping Constructor Pattern creates multiple overloaded constructors to support different combinations of optional parameters.

Apart from maintenance difficulties, it creates these problems:

1. **Poor readability:** It's difficult to understand which value represents which parameter when calling a constructor.
2. **Parameter order dependency:** Passing arguments in the wrong order can lead to incorrect object initialization.
3. **Unnecessary null values:** We may need to pass `null` for optional parameters that we don't want to initialize.
4. **Poor scalability:** Adding more optional attributes can increase the number of constructor overloads.

Builder solves these problems by allowing us to set optional attributes individually through readable method calls.

**Example:**

```java
Employee employee = new Employee.Builder(101, "Rahul")
        .email("rahul@gmail.com")
        .department("Engineering")
        .build();
```
## 8. Memory Trick

`Builder → Set Required/Optional Values → build() → Object`

**One-line interview answer:**

"Builder is a creational design pattern that separates object construction from its representation and allows complex objects to be created step by step."