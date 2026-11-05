// Graphic Interface
interface Graphic {
    double calculateArea();
    double calculatePerimeter();
}


// Circle Class
class Circle implements Graphic {
    private double radius;

    public Circle(double radius) {
        if (!Double.isFinite(radius) || radius < 0)
            throw new IllegalArgumentException("Dimensions must be finite and non-negative.");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}


// Rectangle Class
class Rectangle implements Graphic {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (!Double.isFinite(width) || width < 0 || !Double.isFinite(height) || height < 0)
            throw new IllegalArgumentException("Dimensions must be finite and non-negative.");
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}


// Main Class
public class Main {
    public static void main(String[] args) {

        Circle circle = new Circle(5);
        System.out.println("The area of a circle with a radius of 5 is " + circle.calculateArea());
        System.out.println("The perimeter of a circle with a radius of 5 is " + circle.calculatePerimeter());


        Rectangle rectangle = new Rectangle(4, 6);
        System.out.println("The area of a rectangle with a width of 4 and a height of 6 is " + rectangle.calculateArea());
        System.out.println("The perimeter of a rectangle with a width of 4 and a height of 6 is " + rectangle.calculatePerimeter());
    }
}
