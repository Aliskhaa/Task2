import java.util.ArrayList;

class Department {
    private String name;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void invite(Employee employee) {
        for (Employee e : employees) {
            if (e.getId()== employee.getId()) {
                throw new RuntimeException("Пользователь уже существует.");
            }
        }
        employees.add(employee);
    }

    public void uninvite(int id) {
        employees.removeIf(e -> e.getId() == id);
    }

    public Employee findEmployeeByName(String name) {
        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        throw new RuntimeException("Пользователь не найден.");
    }

    public Employee getHighPaid() {
        Employee best = null;

        for (Employee e : employees) {
            if (best == null || e.calculateSalary() > best.calculateSalary()) {
                best = e;
            }
        }
        return best;
    }
}



abstract class Employee {
    protected int id;
    protected String name;
    protected String job;
    private static int counter = 1;

    public Employee(String name, String job) {
        this.id = counter++;
        this.name = name;
        this.job = job;
    }

    public abstract double calculateSalary();

    public String getName() {return name;}
    public int getId() {return id;}

    public void showEmployeeInfo() {
        System.out.println("id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Job: " + job);
    }
}


class Manager extends Employee {

    public Manager(String name, String job) {
        super(name, job);
    }

    @Override
    public double calculateSalary() {
        return 3000;
    }
}

class Developer extends Employee {

    private double projectCount;
    public Developer(String name, String job, double projectCount) {
        super(name, job);
        this.projectCount = projectCount;
    }

    @Override
    public double calculateSalary() {
        return 2000 + projectCount * 300;
    }
}

class Intern extends Employee {

    public Intern(String name, String job) {
        super(name, job);
    }

    @Override
    public double calculateSalary() {
        return 800;
    }
}