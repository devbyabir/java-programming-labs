public class Circle {

    private int radius;

    public Circle() { }

    public Circle(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius must be non-negative.");
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double circumference() {
        return 2 * Math.PI * radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius must be non-negative.");
        this.radius = radius;
    }
}
