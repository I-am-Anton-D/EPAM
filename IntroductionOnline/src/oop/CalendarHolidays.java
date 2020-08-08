package oop;

import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;

/**
 * Task 3. Calendar with holidays and weekend. If class Special Day have name - it's holiday, else - weekend;
 */
public class CalendarHolidays {
    class SpecialDay {
        private String name;
        private Date date;

        public SpecialDay(Date date) {
            this.date = date;
            this.name = "";
        }

        public SpecialDay(Date date, String name) {
            this.name = name;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return name.isEmpty() ? new SimpleDateFormat("yyyy-MM-dd").format(date) :
            new SimpleDateFormat("yyyy-MM-dd").format(date) + " - " + name;
        }
    }

    AbstractList<SpecialDay> specialDays = new ArrayList<>();

    public void addWeekend(Date date) {
        specialDays.add(new SpecialDay(date));
    }

    public void addHoliday(Date date, String name) {
        specialDays.add(new SpecialDay(date, name));
    }

    public void printWeekends() {
        specialDays.stream().filter(specialDay -> specialDay.getName().isEmpty()).forEach(System.out::println);
    }

    public void printHolidays() {
        specialDays.stream().filter(specialDay -> !specialDay.getName().isEmpty()).forEach(System.out::println);
    }

    public void printAll() {
        specialDays.forEach(System.out::println);
    }
}
