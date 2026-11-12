# Singleton Design Pattern

**Type:** Creational Design Pattern

## 1. What?

Singleton ensures that a class has only one instance throughout the application and provides a global access point to that instance.

**Remember:** Singleton → One Class → One Instance → Shared Access.

## 2. Problem Without Singleton

Suppose multiple services need access to the same application configuration.

Without Singleton:
- Each service might create its own configuration object.
- Multiple instances may lead to inconsistent shared state.
- Object creation and resource management may become unnecessary overhead.

## 3. Solution

Singleton restricts object creation to one instance and provides a common access point.

Typical structure:

- `private constructor` → Prevents external object creation.
- `static instance` → Stores the single instance.
- `getInstance()` → Provides access to the instance.

## 4. Java Implementation – Bill Pugh Singleton

```java
public class ConfigurationManager {

    private ConfigurationManager() {
    }

    private static class Holder {
        private static final ConfigurationManager INSTANCE =
                new ConfigurationManager();
    }

    public static ConfigurationManager getInstance() {
        return Holder.INSTANCE;
    }
}
```

## 5. Important Code Concepts

**Private constructor:** Prevents other classes from creating objects directly.

**Static instance:** Maintains the single instance.

**Static getInstance():** Allows access without creating an object of the class.

**Static Holder class:** Supports lazy initialization and thread safety.

## 6. Example Usage

```java
ConfigurationManager obj1 =
        ConfigurationManager.getInstance();

ConfigurationManager obj2 =
        ConfigurationManager.getInstance();

System.out.println(obj1 == obj2); // true
```

Both references point to the same object.

## 7. Benefits

- Ensures a single instance.
- Provides a shared access point.
- Supports lazy initialization.
- Avoids unnecessary object creation.
- Useful for shared configuration managers and registries.

## 8. Interview Questions

**Q1. Why is the Singleton constructor private?**

To prevent other classes from creating instances using the `new` keyword.

**Q2. Why is getInstance() static?**

So that we can access the instance without creating an object of the class.

**Q3. What is lazy initialization?**

Creating the instance only when it is first requested.

**Q4. Is Singleton thread-safe?**

It depends on the implementation. The Bill Pugh Holder implementation is thread-safe.

**Q5. What is the difference between eager and lazy initialization?**

Eager creates the instance during class initialization, whereas lazy creates it only when required.

**Q6. Is Singleton used in Spring Boot?**

Yes. Spring beans are singleton-scoped by default, meaning one instance per Spring IoC container.

## 9. Memory Trick

`Private Constructor → Static Instance → getInstance() → Same Object`

**One-line interview answer:**

"Singleton is a creational design pattern that restricts a class to a single instance and provides a global access point to that instance."


**Bill Pugh Singleton is a thread-safe, lazy initialization technique that uses a static inner Holder class to create and maintain a single instance of a class. It leverages JVM class-loading guarantees to ensure thread safety without explicit synchronization.**