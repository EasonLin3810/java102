public class Polymorphism {

    // Point class for representing coordinates
    public static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    // Circle class to represent a Circle
    public static class Circle {
        private double cx, cy, radius;

        public Circle(double cx, double cy, double radius) {
            this.cx = cx;
            this.cy = cy;
            this.radius = radius;
        }

        public double getCx() {
            return cx;
        }

        public double getCy() {
            return cy;
        }

        public double getRadius() {
            return radius;
        }

        // Method to calculate the circle from three points
        public static Circle fromPoints(Point p1, Point p2, Point p3) {
            double x1 = p1.getX(), y1 = p1.getY();
            double x2 = p2.getX(), y2 = p2.getY();
            double x3 = p3.getX(), y3 = p3.getY();

            // Calculating the determinant
            double d = 2 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
            double cx = ((x1 * x1 + y1 * y1) * (y2 - y3) + (x2 * x2 + y2 * y2) * (y3 - y1) + (x3 * x3 + y3 * y3) * (y1 - y2)) / d;
            double cy = ((x1 * x1 + y1 * y1) * (x3 - x2) + (x2 * x2 + y2 * y2) * (x1 - x3) + (x3 * x3 + y3 * y3) * (x2 - x1)) / d;

            double radius = Math.sqrt((cx - x1) * (cx - x1) + (cy - y1) * (cy - y1));
            return new Circle(cx, cy, radius);
        }

        public static void main(String[] args) {
            Point p1 = new Point(1, 1);
            Point p2 = new Point(5, 1);
            Point p3 = new Point(3, 4);

            Circle circle = Circle.fromPoints(p1, p2, p3);
            System.out.println("Center: (" + circle.getCx() + ", " + circle.getCy() + ")");
            System.out.println("Radius: " + circle.getRadius());
        }
    }

    // Shape interface with area and perimeter methods
    interface Shape {
        double area();
        double perimeter();
    }

    // RightTriangle class implementing Shape interface
    public static class RightTriangle implements Shape {
        private Point corner;  // Corner where the right angle is
        private double side1;  // Length of the first side (parallel to x-axis or y-axis)
        private double side2;  // Length of the second side (parallel to x-axis or y-axis)

        // Constructor to initialize the right triangle with a corner and two side lengths
        public RightTriangle(Point corner, double side1, double side2) {
            this.corner = corner;
            this.side1 = side1;
            this.side2 = side2;
        }

        // Implement the area method from the Shape interface
        @Override
        public double area() {
            return 0.5 * side1 * side2;
        }

        // Implement the perimeter method from the Shape interface
        @Override
        public double perimeter() {
            return side1 + side2 + Math.sqrt(side1 * side1 + side2 * side2);  // Using Pythagorean theorem for the hypotenuse
        }

        // Static method to check if two RightTriangle objects are similar
        public static boolean similar(RightTriangle t1, RightTriangle t2) {
            // Check if the ratios of corresponding sides are equal
            double ratio1 = t1.side1 / t2.side1;
            double ratio2 = t1.side2 / t2.side2;
            return ratio1 == ratio2;  // The triangles are similar if the ratios of the sides are equal
        }

        public static void main(String[] args) {
            // Create two right triangles
            RightTriangle t1 = new RightTriangle(new Point(0, 0), 3, 4);
            RightTriangle t2 = new RightTriangle(new Point(1, 1), 6, 8);
            RightTriangle t3 = new RightTriangle(new Point(2, 2), 5, 5);

            // Test the area and perimeter methods
            System.out.println("Area of t1: " + t1.area());
            System.out.println("Perimeter of t1: " + t1.perimeter());

            // Test if the triangles are similar
            System.out.println("Are t1 and t2 similar? " + RightTriangle.similar(t1, t2));  // Expected: true
            System.out.println("Are t1 and t3 similar? " + RightTriangle.similar(t1, t3));  // Expected: false
        }
    }

    @Override
    public String toString() {
        return "Polymorphism []";
    }
}