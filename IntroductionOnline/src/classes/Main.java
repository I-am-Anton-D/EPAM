package classes;

import static utils.Utils.*;

import classes.airline.Airline;
import classes.airline.AirlinesHolder;
import classes.airline.TypeOFAirline;
import classes.airline.WeekDay;
import classes.books.Book;
import classes.books.BooksHolder;
import classes.books.TypeOfBinding;
import classes.сustomer.Customer;
import classes.сustomer.CustomersHolder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

/**
 * Main class fot testing
 */
public class Main {

    public static void main(String[] args) {
        task1Test(3, 5);
        task2Test(1, 2);
        testStudentClass();
        testTrainClass(101);
        counterTest(1, 5, 3);
        timeClassTest(12, 13, 14);
        triangleClassTest();
        testCustomersClasses();
        testBookClass();
        testAirlineClass();
    }

    /**
     * Task 1. Create Test1 class with 2 variables and some specific method
     *
     * @param a - first variable in class
     * @param b - second variable in class
     */

    public static void task1Test(int a, int b) {
        System.out.println("-----------------------------------------------");
        System.out.println("[Task 1] Test1 class");
        Test1 test1 = new Test1(a, b);
        System.out.println(test1);
        System.out.println("Sum = " + test1.sum());
        System.out.println("Value of grater var = " + test1.compare());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Create Test2 class with 2 variables and some specific constructors
     *
     * @param a - first variable in class
     * @param b - second variable in class
     */

    public static void task2Test(int a, int b) {
        System.out.println("[Task 2] Test2 class");
        Test2 test1 = new Test2(a, b);
        System.out.println("After calling constructor with parameters: " + test1);
        Test2 test2 = new Test2();
        System.out.println("After calling constructor(default) without parameters: " + test2);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 3. Generate array of student class and output only with 9 or 10 grade
     */

    public static void testStudentClass() {
        System.out.println("[Task 3] Work with student class");
        System.out.println("Array of students:");

        Student[] students = new Student[10];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student("StudentName" + i, "GroupNumber" + i % 2, getRandomIntsArray(1, 10, 5));
            System.out.println("Student " + i + " " + students[i].toString());
        }

        System.out.println("Students with grade 9 or 10: ");
        for (Student student : students) {
            for (int i = 0; i < student.performance.length; i++) {
                if (student.performance[i] == 9 || student.performance[i] == 10) {
                    System.out.println(student.getName() + " from " + student.getNumberOfGroup());
                }
            }
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 4. Work with train class
     */

    public static void testTrainClass(int trainNumber) {
        Random random = new Random();
        System.out.println("[Task 4] Work with train class");
        System.out.println("Array of trains:");
        Train[] trains = new Train[5];
        for (int i = 0; i < 5; i++) {
            int r = random.nextInt(3);
            trains[i] = new Train((r == 0 ? "Moscow" : r == 1 ? "Piter" : "Minsk"), 100 + i,
                new Date(System.currentTimeMillis() + random.nextInt(10000000)));
            System.out.println(trains[i]);
        }

        System.out.println("Sort by train number");
        Arrays.stream(trains).sorted(Comparator.comparingInt(Train::getNumber))
            .forEach(e -> System.out.println(e.toString()));

        System.out.println("Get train info by number " + trainNumber);
        boolean searched = false;
        for (Train train : trains) {
            if (train.getNumber() == trainNumber) {
                System.out.println(train.toString());
                searched = true;
                break;
            }
        }
        if (!searched) {
            System.out.println("Train with specific number do not found");
        }

        System.out.println("Sort trains by destination and departure time");
        Arrays.sort(trains, Comparator.comparing(Train::getDestination).thenComparing(Train::getDepartureTime));
        for (Train train : trains) {
            System.out.println(train.toString());
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 5. Counter test
     */

    public static void counterTest(int min, int max, int current) {
        System.out.println("[Task 5] Work with counter class");
        System.out.println("Create counter by default: " + new Counter().toString());
        Counter counter = new Counter(min, max, current);
        System.out.println("Create counter with parameters: " + counter.toString());
        System.out.println("Get current value  = " + counter.getCurrent());
        System.out.println("Increment = " + counter.inc());
        System.out.println("Increment again = " + counter.inc());
        System.out.println("Increment again = " + counter.inc());
        System.out.println("Decrement = " + counter.dec());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 6. Working with Time class
     */

    public static void timeClassTest(int hour, int minutes, int seconds) {
        System.out.println("[Task 6] Work with time class");
        Time time = new Time(hour, minutes, seconds);
        System.out.println("Create new time instance: " + time.toString());
        time.modify(1, -10, 120);
        System.out.println("After modified: " + time.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 7. Working with triangle
     */

    public static void triangleClassTest() {
        System.out.println("[Task 7] Work with triangle class");
        Triangle triangle = new Triangle(3D, 30, 5D);
        System.out.println("Created triangle by 2 sides and angel between: " + triangle.toString());
        System.out.println("Square of triangle = " + triangle.getSquare());
        System.out.println("Perimeter of triangle = " + triangle.getPerimeter());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 8. Customer and customers holder class
     */

    public static void testCustomersClasses() {
        Random random = new Random();
        System.out.println("[Task 8] Task about customers");
        CustomersHolder customersHolder = new CustomersHolder(5);
        for (int i = 0; i <= customersHolder.getSize(); i++) {
            customersHolder.addElement(
                new Customer("Name" + random.nextInt(10) , "LastName" + random.nextInt(3), "MiddleName" + random.nextInt(100), "Address" + i, 1000 + i, 5000 + i));
        }
        System.out.println("Array of customers:");
        customersHolder.print();
        System.out.println("Sort array by name:");
        customersHolder.sortAndPrintCustomers();
        System.out.println("Customers with filter for credit cart number");
        customersHolder.filterByCreditCartNumber(1000,1003);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 9. Book class and books holder
     */

    public static void testBookClass() {
        System.out.println("[Task 9] Task about books");
        BooksHolder booksHolder = new BooksHolder(6);
        booksHolder.addElement(new Book("Alpha", new String[]{"Ivan", "Alex", "Max"},"Pub1",1981,250,300,
            TypeOfBinding.SOFT));
        booksHolder.addElement(new Book("Beta", new String[]{"Ivan"},"Pub2",1982,350,310,
            TypeOfBinding.SOLID));
        booksHolder.addElement(new Book("Gamma", new String[]{"Alex", "Max"},"Pub3",1985,550,3200,
            TypeOfBinding.SOFT));
        booksHolder.addElement(new Book("Theta", new String[]{"Max"},"Pub2",1983,250,300,
            TypeOfBinding.SOFT));
        booksHolder.addElement(new Book("Omega", new String[]{"Anton", "Steven"},"Pub2",1986,250,300,
            TypeOfBinding.SOLID));
        booksHolder.addElement(new Book("Lama", new String[]{"Ivan", "Alex", "Max"},"Pub3",1980,250,300,
            TypeOfBinding.SOFT));

        System.out.println("Array of books: ");
        booksHolder.print();
        System.out.println("Filter by author: " );
        booksHolder.filterByAuthor("Ivan");
        System.out.println("Filter by publisher: " );
        booksHolder.filterByPublisher("Pub2");
        System.out.println("Filter by year: " );
        booksHolder.filterByYear(1983);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 10. Airline class and airline holder
     */

    private static void testAirlineClass() {
        Random random = new Random();
        System.out.println("[Task 10] Task about airlines");
        AirlinesHolder airlinesHolder = new AirlinesHolder(6);
        airlinesHolder.addElement(new Airline("Moscow",1001, TypeOFAirline.TYPE1,  new Date(System.currentTimeMillis() + random.nextInt(10000000)),
            WeekDay.Monday));
        airlinesHolder.addElement(new Airline("Moscow",1002, TypeOFAirline.TYPE2,  new Date(System.currentTimeMillis() + random.nextInt(10000000)),
            WeekDay.Monday));
        airlinesHolder.addElement(new Airline("Piter",1005, TypeOFAirline.TYPE3,  new Date(System.currentTimeMillis() + random.nextInt(10000000)),
            WeekDay.Monday));
        airlinesHolder.addElement(new Airline("Minsk",1011, TypeOFAirline.TYPE1,  new Date(System.currentTimeMillis() + random.nextInt(10000000)),
            WeekDay.Sunday));
        airlinesHolder.addElement(new Airline("Minsk",1231, TypeOFAirline.TYPE1,  new Date(System.currentTimeMillis() + random.nextInt(10000000)),
            WeekDay.Saturday));
        airlinesHolder.addElement(new Airline("Minsk",1111, TypeOFAirline.TYPE3,  new Date(System.currentTimeMillis() + random.nextInt(10000000)),
            WeekDay.Monday));

        System.out.println("Array of airlines: ");
        airlinesHolder.print();
        System.out.println("Filter by destination: ");
        airlinesHolder.filterByDestination("Moscow");
        System.out.println("Filter by day of week: ");
        airlinesHolder.filterByDayOfWeek(WeekDay.Monday);
        System.out.println("Filter by day of week and time: ");
        airlinesHolder.filterByDayAndTime(WeekDay.Monday, new Date(System.currentTimeMillis() + random.nextInt(10000000)));
        System.out.println("-----------------------------------------------");
    }
}
