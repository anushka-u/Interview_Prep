# Prototype Design Pattern

**Type:** Creational Design Pattern

## 1. What is Prototype Pattern?

Prototype is a creational design pattern that creates new objects by copying an existing, pre-configured object instead of constructing and initializing a new object from scratch.

**Remember:**

`Existing Configured Object → Clone → New Object → Customize if Required`

## 2. Why do we need Prototype Pattern?

Consider a document editor application.

A user creates a document containing:
- Font styles
- Page layout
- Margins
- Headers and footers
- Tables and formatting
- Other complex configurations

Now suppose the user wants to create 100 similar documents with the same formatting.

Without Prototype:
- Initialize a new document.
- Configure its formatting.
- Set margins, styles and layouts.
- Repeat the initialization for every document.

With Prototype:
- Create and configure one document.
- Use it as a prototype.
- Clone it whenever a similar document is required.
- Customize the content of each cloned document.

This avoids repeatedly performing complex initialization.

## 3. How does it work?

1. Create an object with the required configuration.
2. Keep that object as a prototype.
3. Clone the prototype to create new objects.
4. Customize the cloned objects wherever required.

## 4. Java Implementation

Java provides the `Cloneable` marker interface and `clone()` method for implementing basic cloning.

```java
class Document implements Cloneable {

    String font;
    String pageLayout;
    int margin;

    Document(String font, String pageLayout, int margin) {
        this.font = font;
        this.pageLayout = pageLayout;
        this.margin = margin;
    }

    @Override
    public Document clone() {
        try {
            return (Document) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    void display() {
        System.out.println(font + " " + pageLayout + " " + margin);
    }
}
```

**Usage:**

```java
public class Main {
    public static void main(String[] args) {

        // Existing configured document
        Document prototype =
            new Document("Arial", "A4", 20);

        // Create copies
        Document doc1 = prototype.clone();
        Document doc2 = prototype.clone();

        // Customize one copy
        doc2.font = "Times New Roman";

        prototype.display();
        doc1.display();
        doc2.display();
    }
}
```

Output:

```text
Arial A4 20
Arial A4 20
Times New Roman A4 20
```

Each cloned document has its own primitive and String field values.

## 5. Shallow Copy vs Deep Copy

This is one of the most important concepts in Prototype Pattern.

Suppose Document also contains an object called `Formatting`, which contains nested configuration.

### A. Shallow Copy

Shallow copy creates a new top-level object but copies the references of nested objects.

This means:
- A new Document object is created.
- Primitive fields are copied by value.
- Immutable objects such as String can safely be shared.
- Mutable nested objects are shared between original and cloned objects.

Example:

```java
class Formatting {
    String fontColor;

    Formatting(String fontColor) {
        this.fontColor = fontColor;
    }
}

class Document implements Cloneable {
    Formatting formatting;

    @Override
    public Document clone() {
        try {
            return (Document) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
```

Usage:

```java
Document original = new Document();
original.formatting = new Formatting("Black");

Document cloned = original.clone();

cloned.formatting.fontColor = "Blue";
```

Now both original and cloned documents will have Blue as their font color.

**Why?**

Because both objects refer to the same Formatting object.

`Shallow Copy → New Parent Object + Shared Nested References`

### B. Deep Copy

Deep copy creates a new top-level object and independent copies of its nested mutable objects.

Example:

```java
@Override
public Document clone() {
    try {
        Document cloned = (Document) super.clone();

        cloned.formatting =
            new Formatting(this.formatting.fontColor);

        return cloned;

    } catch (CloneNotSupportedException e) {
        throw new AssertionError(e);
    }
}
```

Now:

```java
Document original = new Document();
original.formatting = new Formatting("Black");

Document cloned = original.clone();

cloned.formatting.fontColor = "Blue";
```

Output:

```text
Original: Black
Cloned: Blue
```

The original remains unchanged because both documents have separate Formatting objects.

**Deep Copy → New Parent Object + Independent Nested Objects**

### Difference between Shallow and Deep Copy

| Shallow Copy | Deep Copy |
|---|---|
| Creates a new top-level object | Creates a new top-level object |
| Copies nested object references | Copies nested mutable objects |
| Nested mutable objects are shared | Nested mutable objects are independent |
| Easier to implement | Requires additional copying logic |
| Changes to shared nested objects affect both | Changes remain independent |

**Important:** Deep copying must account for the entire relevant object graph. For nested collections, their elements may also need copying. Immutable objects can safely be shared.

## 6. When should we use Prototype?

Use Prototype when:

- Object creation involves complex initialization.
- Existing objects have reusable configurations.
- Many similar objects need to be created.
- Reconstructing objects from scratch is expensive.
- The client does not need to know the concrete class used to create the copy.

## 7. Limitations / Objections

Prototype is not suitable for every situation.

1. **Unique fields:** IDs, names and other unique values may need to be regenerated after cloning.
2. **Setter dependency:** If most fields need to be changed after cloning, the benefit of Prototype decreases.
3. **Shallow copy problem:** Nested mutable objects may be shared unintentionally.
4. **Deep copy complexity:** Maintaining deep-copy logic can become difficult for complex object graphs.
5. **Overengineering:** For simple objects, constructors or Builder Pattern may be a better choice.

Do not use Prototype just to avoid writing constructors.

## 8. Prototype vs Other Creational Patterns

| Pattern | Main Purpose |
|---|---|
| Factory | Decides which concrete object to create |
| Builder | Constructs complex objects step by step |
| Singleton | Ensures one shared instance |
| Prototype | Creates new objects by cloning existing objects |

### 9. Interview Definition

Prototype is a creational design pattern that allows objects to be created by cloning existing prototype instances, avoiding repeated complex initialization.

**Memory Trick:**

`Configure Once → Clone Multiple Times → Customize When Needed`

### 10. Important Interview Questions

1. What is Prototype Design Pattern?
2. Why do we use Prototype instead of creating objects from scratch?
3. What is the difference between shallow copy and deep copy?
4. What is the purpose of Java's Cloneable interface?
5. What happens if we use `super.clone()`?
6. How do we handle unique fields such as IDs in cloned objects?
7. What are the limitations of Prototype Pattern?
8. When would Builder Pattern be a better choice than Prototype?

###  What is Cloneable?

- Cloneable is a marker interface.
- It does not declare a clone() method.
- It tells Java that this class permits cloning through Object.clone().
- If a class doesn't implement Cloneable and calls super.clone(), Java throws CloneNotSupportedException.

**Interview definition:**
"Cloneable is a marker interface in Java that indicates that an object's class allows cloning using Object's clone method."