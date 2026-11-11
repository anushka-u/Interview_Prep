package BuilderDesignPattern;

public class EmployeeService {

    public static void main(String[] args) {

        Employee employee = new EmployeeBuilder().id(1)
                .designation("Software Engineer")
                .email("john@1908.com")
                .salary(100000)
                .position("Developer")
                .build();

        System.out.println(employee);
    }
}
