public class ShapeTester {
    public static void main(String[] args) {
        Circle c = new Circle();

        c.setRadius(1);
        System.out.printf("Radius: 1, Area: %.2f, Circumference: %.2f\n", c.area(), c.circumference());

        for (int i = 2; i <= 10; i++) {
            c.setRadius(i);
            System.out.printf("Radius: %d, Area: %.2f, Circumference: %.2f\n", i, c.area(), c.circumference());
        }
    }
}
