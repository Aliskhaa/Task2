
import java.util.ArrayList;
import java.util.List;

class Department{
    private String title;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Department(String title) {
        this.title = title;
    }

    public List<Employee> findEmployeeByName(String name) {
        List<Employee> result = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                result.add(e);
            }
        }
        if (result.isEmpty()) {
            throw new RuntimeException("Сотрудник не найден.");
        }
        return result;
    }

    public void invite(Employee employee) {
        for (Employee e: employees) {
            if(e.getId()==employee.getId()) {
                throw new RuntimeException("Пользователь уже существует.");
            }
        }
        employees.add(employee);
    }

    public void uninvite(int id) {
        employees.removeIf(e -> e.getId() == id);
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

    public String getName() {return name;}
    public int getId() {return id;}
    public String getJobTitle() {return jobTitle;}

    public abstract double calculateSalary();
}


class Manager extends Employee {
    public Manager(String name, String jobTitle) {
        super(name, jobTitle);
    }

    @Override
    public double calculateSalary() {
        return 4000;
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
        return 3000 + projectCount * 10;
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