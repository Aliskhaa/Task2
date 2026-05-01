import java.util.*;

class Department {
    private String title;
    private final List<Employee> employees = new ArrayList<>();
    private final Map<Integer, Employee> employeeById = new HashMap<>();

    public Department(String title) {
        this.title = title;
    }

    public void invite(Employee employee) {

        if (employeeById.containsKey(employee.getId())) {
            throw new RuntimeException("Пользователь уже существует.");
        }

        employees.add(employee);
        employeeById.put(employee.getId(), employee);
    }

    public void uninvite(int id) {
        employees.removeIf(e -> e.getId()==id);
        employeeById.remove(id);
    }

    public Employee findEmployeeById(int id) {
        Employee employee = employeeById.get(id);

        if (employee == null) {
            throw new RuntimeException("Пользователь не найден.");
        }

        return employee;
    }

    public List<Employee> findEmployeeByName(String name) {
        List<Employee> result = new ArrayList<>();

        for (Employee e: employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                result.add(e);
            }
        }

        return result;
    }
}



abstract class Employee {

    private int id;
    private String name;
    private String jobTitle;
    private static int counter = 1;

    public Employee(String name, String jobTitle) {
        this.id = counter++;
        this.name = name;
        this.jobTitle = jobTitle;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getJobTitle() {return jobTitle;}

    public abstract double calculateSalary();
}


class Manager extends Employee {

    public Manager(String name, String jobTitle) {
        super(name, jobTitle);
    }

    @Override
    public double calculateSalary() {
        return 5000;
    }
}


class Developer extends Employee {
    private int projectCount;

    public Developer(String name, String jobTitle, int projectCount) {
        super(name, jobTitle);
        this.projectCount = projectCount;
    }

    @Override
    public double calculateSalary() {
        return 4000 + projectCount * 2;
    }
}


class Intern extends Employee {

    public Intern(String name, String jobTitle) {
        super(name, jobTitle);
    }

    @Override
    public double calculateSalary() {
        return 800;
    }
}
