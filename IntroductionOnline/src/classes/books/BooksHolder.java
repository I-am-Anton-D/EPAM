package classes.books;

import classes.AbstractHolder;
import java.util.Arrays;

/**
 * Books holder class
 */
public class BooksHolder extends AbstractHolder<Book> {

    public BooksHolder(int countOfBooks) {
        super(Book.class, countOfBooks);
    }

    /**
     * Filter books by author name
     */

    public void filterByAuthor(String authorName) {
        for (int i = 0; i < index ; i++) {
            for (int j = 0; j <array[i].getAuthors().length ; j++) {
                if (array[i].getAuthors()[j].equals(authorName)) {
                    System.out.println(array[i].toString());
                }
            }
        }
    }

    /**
     * Filter books by publisher
     */

    public void filterByPublisher(String pubName) {
        Arrays.stream(array).filter(book -> book.getPublisher().equals(pubName)).forEach(System.out::println);
    }

    public void filterByYear(int year) {
        Arrays.stream(array).filter(book -> book.getYearPublication()>year).forEach(System.out::println);
    }

}
