package BuilderDesignPattern;


import lombok.Getter;

@Getter
public class Employee {

    private final int id;
    private final String name;
    private final String designation;
    private final String email;
    private final double salary;
    private final String position;

    Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.designation = builder.designation;
        this.email = builder.email;
        this.salary = builder.salary;
        this.position = builder.position;
    }
}
