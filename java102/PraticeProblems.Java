/** 
Create a non-static method in Grid called diagonal that returns an ArrayList with the primary diagonal of the grid (from top left to bottom right)
Create a static method inside of Grid that returns the biggest sideLength of any Grid that's been made.
In the Circle class, create a static method that generates a Circle from three points that are on the edge of the circle 
Create a RightTriangle class that implements Shape. The sides of the triangle are necessarily parallel to the x and y axes, but the right angle can be in any corner
*/
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static class Grid<T> {
        private List<List<T>> grid;
        private static int maxSideLength = 0;

        public Grid(int rows, int cols) {
            if (rows != cols) {
                throw new IllegalArgumentException("Grid must be square!");
            }
            grid = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                grid.add(new ArrayList<>());
                for (int j = 0; j < cols; j++) {
                    grid.get(i).add(null);  
                }
            }
            if (rows > maxSideLength) {
                maxSideLength = rows;
            }
        }

        public void set(int row, int col, T value) {
            grid.get(row).set(col, value);
        }

        public T get(int row, int col) {
            return grid.get(row).get(col);
        }

        public ArrayList<T> diagonal() {
            ArrayList<T> diagonalElements = new ArrayList<>();
            int size = grid.size();
            for (int i = 0; i < size; i++) {
                diagonalElements.add(grid.get(i).get(i));  
            }
            return diagonalElements;
        }

        public static int maxSideLength() {
            return maxSideLength;
        }
    }

    public static class Polymorphism {

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

            public static Circle fromPoints(Point p1, Point p2, Point p3) {
                double x1 = p1.getX(), y1 = p1.getY();
                double x2 = p2.getX(), y2 = p2.getY();
                double x3 = p3.getX(), y3 = p3.getY();

                double d = 2 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
                double cx = ((x1 * x1 + y1 * y1) * (y2 - y3) + (x2 * x2 + y2 * y2) * (y3 - y1) + (x3 * x3 + y3 * y3) * (y1 - y2)) / d;
                double cy = ((x1 * x1 + y1 * y1) * (x3 - x2) + (x2 * x2 + y2 * y2) * (x1 - x3) + (x3 * x3 + y3 * y3) * (x2 - x1)) / d;

                double radius = Math.sqrt((cx - x1) * (cx - x1) + (cy - y1) * (cy - y1));
                return new Circle(cx, cy, radius);
            }
        }

        interface Shape {
            double area();
            double perimeter();
        }

        public static class RightTriangle implements Shape {
            private double side1;
            private double side2;

            public RightTriangle(Point corner, double side1, double side2) {
                this.side1 = side1;
                this.side2 = side2;
            }

            @Override
            public double area() {
                return 0.5 * side1 * side2;
            }

            @Override
            public double perimeter() {
                return side1 + side2 + Math.sqrt(side1 * side1 + side2 * side2);
            }

            public static boolean similar(RightTriangle t1, RightTriangle t2) {
                double ratio1 = t1.side1 / t2.side1;
                double ratio2 = t1.side2 / t2.side2;
                return ratio1 == ratio2;
            }
        }

        public static void main(String[] args) {
            Grid<Integer> grid1 = new Grid<>(3, 3);
            grid1.set(0, 0, 1);
            grid1.set(1, 1, 2);
            grid1.set(2, 2, 3);
            ArrayList<Integer> diagonal = grid1.diagonal();
            System.out.println("Diagonal elements of grid1: " + diagonal);
            System.out.println("Max side length of any grid: " + Grid.maxSideLength());

            Point p1 = new Point(1, 1);
            Point p2 = new Point(5, 1);
            Point p3 = new Point(3, 4);
            Circle circle = Circle.fromPoints(p1, p2, p3);
            System.out.println("Circle center: (" + circle.getCx() + ", " + circle.getCy() + ")");
            System.out.println("Circle radius: " + circle.getRadius());

            RightTriangle t1 = new RightTriangle(new Point(0, 0), 3, 4);
            RightTriangle t2 = new RightTriangle(new Point(1, 1), 6, 8);
            System.out.println("Are t1 and t2 similar? " + RightTriangle.similar(t1, t2));
        }
    }
}
