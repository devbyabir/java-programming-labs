import java.util.Random;

interface Insurable {
    double calculatePremium();
}

class Employee implements Insurable {
    protected String id;
    protected String name;
    protected int age;
    protected double salary;

    public Employee(String id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public double calculatePremium() {
        return salary * 0.1;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + "\nName: " + name + "\nAge: " + age + "\nSalary: " + salary + "\nPremium: " + calculatePremium();
    }
}

class Manager extends Employee {
    private String responsibilities;

    public Manager(String id, String name, int age, double salary, String responsibilities) {
        super(id, name, age, salary);
        this.responsibilities = responsibilities;
    }

    @Override
    public double calculatePremium() {
        return salary * 0.15 + age * 5;
    }

    @Override
    public String toString() {
        return "Manager ID: " + id + "\nName: " + name + "\nAge: " + age + "\nSalary: " + salary +
               "\nResponsibilities: " + responsibilities + "\nPremium: " + calculatePremium();
    }
}

public class Main {
    public static void main(String[] args) {
        Insurable[] employees = new Insurable[5];
        Random random = new Random();

        for (int i = 0; i < employees.length; i++) {
            if (random.nextBoolean()) {
                employees[i] = new Employee("E" + (i + 1), "Employee" + (i + 1), 25 + random.nextInt(40), 5000.0);
            } else {
                employees[i] = new Manager("M" + (i + 1), "Manager" + (i + 1), 25 + random.nextInt(50), 8000.0, "Project Management" + (i + 1));
            }
        }

        for (Insurable employee : employees) {
            System.out.println(employee);
            System.out.println("-------------------");
        }
    }
}
