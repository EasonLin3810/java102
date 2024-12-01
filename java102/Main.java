/** pratice problems 
Write a static method for the Point class that takes as an argument an array of Point objects, and returns the center of mass, as a Point
Make a static method in Main that takes an array of LibraryItem objects and returns them all to the library.
Make a static method in Main that takes an array of LibraryItem objects and outputs an ArrayList of LibraryItem objects with all of the available items from the input array.git add .
*/
import java.util.ArrayList;
public class Main {
    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Point centerOfMass(Point[] points) {
        int sumx = 0;
        int sumy = 0;
        for (Point point : points) {
            sumx += point.x;
            sumy += point.y;
        }
        int centerx = sumx / points.length;
        int centery = sumy / points.length;

        return new Point(centerx, centery);
    }

    interface LibraryItem {
        void returnItem(); 
        boolean isAvailable(); 
    }

    static class Book implements LibraryItem {
        private String title;
        private boolean isReturned;

        public Book(String title) {
            this.title = title;
            this.isReturned = false; // Initially, the book is checked out
        }

        @Override
        public void returnItem() {
            if (!isReturned) {
                System.out.println("Returning book: " + title);
                isReturned = true;
            } else {
                System.out.println("This book is already returned: " + title);
            }
        }

        @Override
        public boolean isAvailable() {
            return isReturned; 
        }

        public String getTitle() {
            return title;
        }
    }

    static class Magazine implements LibraryItem {
        private String title;
        private boolean isReturned;

        public Magazine(String title) {
            this.title = title;
            this.isReturned = false; 
        }

        @Override
        public void returnItem() {
            if (!isReturned) {
                System.out.println("Returning magazine: " + title);
                isReturned = true;
            } else {
                System.out.println("This magazine is already returned: " + title);
            }
        }

        @Override
        public boolean isAvailable() {
            return isReturned; 
        }

        public String getTitle() {
            return title;
        }
    }

    public static void returnAll(LibraryItem[] items) {
        for (LibraryItem item : items) {
            item.returnItem(); 
        }
    }

    public static ArrayList<LibraryItem> availableItems(LibraryItem[] items) {
        ArrayList<LibraryItem> availableItemsList = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.isAvailable()) {  
                availableItemsList.add(item);
            }
        }
        return availableItemsList; 
    }

    public static void main(String[] args) {
        Point[] points = {
            new Point(9, 10),
            new Point(7, 8),
            new Point(9, 10)
        };
        Point center = centerOfMass(points);
        System.out.println("Center of Mass: (" + center.x + ", " + center.y + ")");

        LibraryItem[] items = new LibraryItem[4];
        items[0] = new Book("The Catcher in the Rye");
        items[1] = new Book("1984");
        items[2] = new Magazine("National Geographic");
        items[3] = new Magazine("Time");

        returnAll(items);

        ArrayList<LibraryItem> available = availableItems(items);

    
        System.out.println("\nAvailable items:");
        for (LibraryItem item : available) {
            if (item instanceof Book) {
                System.out.println("Book: " + ((Book) item).getTitle());
            } else if (item instanceof Magazine) {
                System.out.println("Magazine: " + ((Magazine) item).getTitle());
            }
        }
    }
}
