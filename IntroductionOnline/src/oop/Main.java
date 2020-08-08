package oop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import oop.Payment.Good;
import oop.textfile.TextFile;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        testTextFile();
        testPayment();
        testCalendar();
    }

    /**
     * Task 1. Creating Text File class by Directory and Abstract class
     * @throws IOException
     */

    public static void testTextFile() throws IOException {
        System.out.println("-----------------------------------------------");
        System.out.println("[Task 1] Work with text file class: ");
        TextFile textFile = new TextFile("C:\\Users\\Антон\\Desktop\\Epam\\IntroductionOnline\\src\\oop\\textfile\\test\\","test.txt");
        textFile.dir.create();
        textFile.createFile();
        textFile.dir.print();
        textFile.addText("Add some text\n");
        textFile.addText("Add more text in line\n");
        textFile.addText("And more and more\n");
        textFile.addText("Some text\n");
        textFile.print();
        textFile.delete();
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Payment class
     */

    public static void testPayment() {
        System.out.println("[Task 2] Payment class: ");
        Payment payment = new Payment();
        payment.addGood(new Good("Pepsi", 10),1);
        payment.addGood(new Good("Cola", 9),1);
        payment.addGood(new Good("Beer", 30),20);
        payment.addGood(new Good("Chips", 5),3);
        payment.printPayment();
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 3. Calendar with weekends and holidays
     */

    public static void testCalendar() throws ParseException {
        System.out.println("[Task 2] Calendar class: ");
        CalendarHolidays calendarHolidays = new CalendarHolidays();
        calendarHolidays.addWeekend(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-07"));
        calendarHolidays.addWeekend(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-08"));
        calendarHolidays.addWeekend(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-14"));
        calendarHolidays.addWeekend(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-15"));
        calendarHolidays.addWeekend(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-21"));
        calendarHolidays.addWeekend(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-22"));
        calendarHolidays.addHoliday(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"), "Happy New Year");
        calendarHolidays.addHoliday(new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-14"), "My Birthday");
        calendarHolidays.addHoliday(new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-01"), "World. Work. May");
        calendarHolidays.addHoliday(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"), "Day of Children");

        System.out.println("Holidays: ");
        calendarHolidays.printHolidays();
        System.out.println("Weekends: ");
        calendarHolidays.printWeekends();
        System.out.println("-----------------------------------------------");

    }

}
