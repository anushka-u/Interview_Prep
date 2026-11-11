package BuilderDesignPattern;

public class EmployeeBuilder {


    int id;
    String name;
    String designation;
    String email;
    double salary;
    String position;


    public EmployeeBuilder id(int id) {this.id = id; return this;}

    public  EmployeeBuilder name(String name) {this.name = name; return this;}

    public  EmployeeBuilder designation(String designation) {this.designation = designation; return this;}

    public  EmployeeBuilder email(String email) {this.email = email; return this;}

    public  EmployeeBuilder salary(double salary) {this.salary = salary; return this;}

    public  EmployeeBuilder position(String position) {this.position = position; return this;}

    public Employee build(){
        return new Employee(this);
    }

}
