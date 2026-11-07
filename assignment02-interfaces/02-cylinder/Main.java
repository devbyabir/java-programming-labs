// Interface A
interface A {
    double P = 3.14; // Constant for Pi
    double area();
}

// Interface B
interface B {
    void setColor(String c);
}

// Interface C
interface C extends A, B {
    double volume();
}


// Cylinder Class
class Cylinder implements C {
    private double radius;
    private double height;
    private String color;

    public Cylinder(double radius, double height) {
        if (!Double.isFinite(radius) || radius < 0 || !Double.isFinite(height) || height < 0)
            throw new IllegalArgumentException("Dimensions must be finite and non-negative.");
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double area() {
        return 2 * P * radius * (radius + height); // Surface Area = 2πr(r + h)
    }

    @Override
    public double volume() {
        return P * radius * radius * height; // Volume = πr²h
    }

    @Override
    public void setColor(String c) {
        this.color = c;
    }

    public String getColor() {
        return color;
    }
}


// Main Class
public class Main {
    public static void main(String[] args) {
        Cylinder cylinder = new Cylinder(5, 10);
        cylinder.setColor("red");

        System.out.println("Color: " + cylinder.getColor());
        System.out.println("Surface Area: " + cylinder.area());
        System.out.println("Volume: " + cylinder.volume());
    }
}
