package classes.books;

import java.util.Arrays;

/**
 * Book class
 */

public class Book {
    private int id;
    private String name;
    private String[] authors;
    private String publisher;
    private int yearPublication;
    private int countOfSheets;
    private int price;
    private TypeOfBinding typeOfBinding;
    private static int counter = 1;

    public Book(String name, String[] authors, String publisher, int yearPublication, int countOfSheets,
        int price,
        TypeOfBinding typeOfBinding) {
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.yearPublication = yearPublication;
        this.countOfSheets = countOfSheets;
        this.price = price;
        this.typeOfBinding = typeOfBinding;
        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public int getCountOfSheets() {
        return countOfSheets;
    }

    public void setCountOfSheets(int countOfSheets) {
        this.countOfSheets = countOfSheets;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TypeOfBinding getTypeOfBinding() {
        return typeOfBinding;
    }

    public void setTypeOfBinding(TypeOfBinding typeOfBinding) {
        this.typeOfBinding = typeOfBinding;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Book.counter = counter;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", authors=" + Arrays.toString(authors) +
            ", publisher='" + publisher + '\'' +
            ", yearPublication=" + yearPublication +
            ", countOfSheets=" + countOfSheets +
            ", price=" + price +
            ", typeOfBinding=" + typeOfBinding +
            '}';
    }
}
