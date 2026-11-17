package PracticeProblem.easy.factoryMethod;

// Shape products are pre-implemented. Do not change them.
interface Shape {
    String name();

    double area();

    static double round2(double value) {
        return (long) (value * 100.0 + 0.5) / 100.0;
    }
}

class Circle implements Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String name() {
        return "Circle";
    }

    @Override
    public double area() {
        return Shape.round2(3.14159265358979 * radius * radius);
    }
}

class Rectangle implements Shape {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String name() {
        return "Rectangle";
    }

    @Override
    public double area() {
        return width * height;
    }
}

class Triangle implements Shape {
    private final double base;
    private final double height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public String name() {
        return "Triangle";
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }
}

// Implement the ShapeCreator abstract class here.
abstract class ShapeCreator{

    private int buildCount =  0;
    public abstract String kind();
    public abstract Shape createShape();

    public Shape build(){
        Shape shape = createShape();
        buildCount++;
        return shape;
    }

    public double measure(){
        Shape shape = build();
        return shape.area();
    }

    public String describeShape() {
        Shape shape = build();
        return String.format("%s with area: %.2f",
                shape.name(), shape.area());
    }

    public int builtCount(){
        return buildCount;
    }
}
// Implement the CircleCreator class here.
class CircleCreator extends ShapeCreator{
    @Override
    public Shape createShape(){
        return new Circle(5);
    }

    @Override
    public String kind(){
        return "circle";
    }
}

// Implement the RectangleCreator class here.
class RectangleCreator extends ShapeCreator{
    @Override
    public Shape createShape(){
        return new Rectangle(4,6);
    }

    @Override
    public String kind(){
        return "rectangle";
    }
}
// Implement the TriangleCreator class here.
class TriangleCreator extends ShapeCreator{
    @Override
    public Shape createShape(){
        return new Triangle(3,8);
    }

    @Override
    public String kind(){
        return "triangle";
    }
}
// ShapeWorks is pre-implemented. Do not change it.
class ShapeWorks {
    private final ShapeCreator circle = new CircleCreator();
    private final ShapeCreator rectangle = new RectangleCreator();
    private final ShapeCreator triangle = new TriangleCreator();
    private ShapeCreator active = circle;

    public ShapeWorks() {
    }

    public boolean setCreator(String kind) {
        if (kind.equals("circle")) {
            active = circle;
            return true;
        }
        if (kind.equals("rectangle")) {
            active = rectangle;
            return true;
        }
        if (kind.equals("triangle")) {
            active = triangle;
            return true;
        }
        return false;
    }

    public String currentCreator() {
        return active.kind();
    }

    public double area() {
        return active.measure();
    }

    public String describe() {
        return active.describeShape();
    }

    public int createdCount() {
        return circle.builtCount() + rectangle.builtCount() + triangle.builtCount();
    }
}