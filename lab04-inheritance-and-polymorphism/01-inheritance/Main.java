class Employee {
    protected String empId;
    protected String name;
    protected int age;
    protected double salary;

    public Employee(String empId, String name, int age, double salary) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void printInfo() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
    }
}

class Manager extends Employee {
    private double salary = 8000.0;
    private String responsibilities;

    public Manager(String empId, String name, int age, String responsibilities) {
        super(empId, name, age, 8000.0);
        this.responsibilities = responsibilities;
    }

    @Override
    public void printInfo() {
        System.out.println("Manager ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Responsibilities: " + responsibilities);
    }
}

public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee("E1", "emp1", 25, 5000.0);
        emp1.printInfo();

        System.out.println();

        Manager mng1 = new Manager("M1", "mng1", 40, "Project Management");
        mng1.printInfo();
    }
}
