package finalTasks.books.book;

import java.io.IOException;
import java.util.ArrayList;

public class BookService {
    BookRepository bookRepository = new BookRepository();

    public void addBook(Book book) throws IOException {
        bookRepository.addBook(book);
    }

    public ArrayList<Book> getBooks(int offset, int limit) {
        if (offset<0) return null;
        if (offset + limit> bookRepository.size()-1) {
            limit = bookRepository.size()-1-offset;
        }
        ArrayList<Book> result = new ArrayList<>();
        for (int i = offset; i <=offset+limit ; i++) {
            result.add(bookRepository.getBooks().get(i));
        }
        return result;
    }

    public int getSize() {
        return bookRepository.size();
    }

    public Book getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    public void saveBook(Book book) throws IOException {
        bookRepository.saveBook(book);
    }

    public ArrayList<Book> search(String query) {
        return  bookRepository.search(query);
    }



}
