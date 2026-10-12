import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {
    private String fullName;
    private Integer age;
    private String department;
    private Double salary;

    public Employee(String fullName, Integer age, String department, Double salary) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}

public class Task3 {
    public static void solveTask3() {
        ArrayList<Employee> employees = Stream.of(
                new Employee("Иванов Иван Иванович", 28, "IT", 100000.0),
                new Employee("Петров Петр Петрович", 35, "IT", 150000.0),
                new Employee("Рубцова Анастасия Михайловна", 42, "HR", 80000.0),
                new Employee("Казаков Алексей Робертович", 31, "IT", 120000.0),
                new Employee("Осипова Ольга Александровна", 25, "HR", 90000.0),
                new Employee("Голубева Анна Игоревна", 45, "HR", 110000.0)
        ).collect(Collectors.toCollection(ArrayList::new));

        String dept = "HR";
        double averageSalary = employees.stream()
                .filter(emp -> emp.getDepartment().equals(dept))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.printf("Cредняя ЗП в отделе %s: %.2f%n", dept, averageSalary);
    }
}
