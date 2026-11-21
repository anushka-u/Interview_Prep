# Factory Method vs Abstract Factory

## 1. Factory Method Pattern

**One-liner:** Defines a method for creating a single product, allowing subclasses to decide which concrete product to instantiate.

- Focuses on creating one product type.
- Uses a Creator and Concrete Creators.
- Concrete Creators override the factory method.
- Example: `WindowsButtonFactory` creates `WindowsButton`.

## 2. Abstract Factory Pattern

**One-liner:** Provides an interface for creating families of related products without specifying their concrete classes.

- Focuses on creating a family of related products.
- Uses an Abstract Factory and Concrete Factories.
- Each Concrete Factory creates a compatible family of products.
- Example: `WindowsFactory` creates `WindowsButton` and `WindowsCheckbox`.

## Key Differences

| Factory Method | Abstract Factory |
|---|---|
| Creates a single product type | Creates a family of related products |
| One factory method in the Creator | Multiple creation methods in the factory interface |
| Concrete Creators | Concrete Factories |
| Uses inheritance and method overriding | Uses composition and factory interfaces |

## Easy Way to Remember

- **Factory Method = One product creation.**
- **Abstract Factory = Family of related product creation.**

### Real-world Example

- Factory Method: Creating a Windows Button or Mac Button through different concrete creators.
- Abstract Factory: Creating a complete Windows UI family (Button, Checkbox, Menu) or Mac UI family.

**Important:** Both patterns encapsulate object creation. The key difference is whether we are creating an individual product or a family of compatible products.