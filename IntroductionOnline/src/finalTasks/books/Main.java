package finalTasks.books;

import finalTasks.books.auth.UserService;
import finalTasks.books.book.Book;
import finalTasks.books.book.BookService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

public class Main {

    private static final int LIMIT = 8;
    private static UserService userService = new UserService();
    private static BookService bookService = new BookService();
    private static int offset = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Welcome to HOME BOOK LIBRARY");
        while (true) {
            greeting();
        }
    }

    public static void greeting() throws ParseException, IOException {

        if (!userService.isAuth()) {
            System.out.println("Enter login and password (two strings separating by space): ");
            String input = scanner.nextLine();
            if (!input.contains(" ")) {
                System.out.println("Wrong input");
            } else {
                userService.login(input.split(" ")[0], input.split(" ")[1]);
                if (userService.isAuth()) {
                    System.out
                        .println("You authorized. You status: " + (userService.isAdmin() ? "admin" : "user"));
                } else {
                    System.out.println("Login / password do not match");
                }
            }
        } else {
            System.out.println("[1] List library");
            System.out.println("[2] Search book");
            System.out.println(!userService.isAdmin() ? "[3] Offer book" : "[3] Add book");
            System.out.println("[4] Logout");
            System.out.println("[5] Exit");
            String input = scanner.nextLine();
            int i = 0;
            try {
                i = Integer.parseInt(input);
                if (i < 0 || i > 5) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
            }

            if (i == 1) {
                listLibrary();
            }

            if (i == 2) {
                searchBook();
            }

            if (i == 3) {
                if (userService.isAdmin()) {
                    addBook();
                } else {
                    offerBook();
                }
            }

            if (i == 4) {
                userService.logout();
                System.out.println("You log out");
            }

            if (i == 5) {
                System.exit(0);
            }
        }
    }

    private static void offerBook() {
        Book book = new Book();
        System.out.println("Enter the name of book: ");
        String input = scanner.nextLine();
        book.setName(input);
        System.out.println("Enter description: ");
        input = scanner.nextLine();
        book.setDesc(input);
        System.out.println("Enter author: ");
        input = scanner.nextLine();
        book.setAuthor(input);
        book.setEbook(true);
        System.out.println("Enter a link: ");
        input = scanner.nextLine();
        book.setLink(input);
        System.out.println(book);
        System.out.println("Offer was sent.Thank you");
        userService.sentBookToAdmin(book);
    }

    private static void addBook() throws IOException {
        Book book = new Book();
        System.out.println("Enter the name of book: ");
        String input = scanner.nextLine();
        book.setName(input);
        System.out.println("Enter description: ");
        input = scanner.nextLine();
        book.setDesc(input);
        System.out.println("Enter author: ");
        input = scanner.nextLine();
        book.setAuthor(input);
        System.out.println("It's ebook? 1 - YES, 0 - NO");
        input = scanner.nextLine();
        if (input.equals("1")) {
            book.setEbook(true);
        } else {
            book.setEbook(false);
        }

        if (book.isEbook()) {
            System.out.println("Enter a link: ");
            input = scanner.nextLine();
            book.setLink(input);
        } else {
            book.setLink("");
        }
        bookService.addBook(book);
        System.out.println(book);
        System.out.println("Book saved");
        userService.sentToAll(book.getName());
    }

    private static void searchBook() {
        System.out.println("Enter query: ");
        String input = scanner.nextLine();
        ArrayList<Book> result = bookService.search(input);
        if (result == null || result.size()==0) {
            System.out.println("Book not found");
        } else {
            result.forEach(System.out::println);
        }
        System.out.println("Press Enter to continue");
        scanner.nextLine();
    }

    private static void listLibrary() throws IOException {
        while (true) {
            System.out.println("Offset = " + offset + ", Limit = " + LIMIT);
            bookService.getBooks(offset, LIMIT).forEach(System.out::println);
            System.out
                .println("[1] Next [2] Prev [3] Get Link by id (read) [4] Back to menu " + (userService.isAdmin()
                    ? "[5] Modify by id" : ""));
            String input = scanner.nextLine();
            int i = 0;
            try {
                i = Integer.parseInt(input);
                if (i < 0 || i > 5) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input");
            }

            if (i == 1) {
                if (offset + LIMIT < bookService.getSize() - 1) {
                    offset += LIMIT;
                }
            }

            if (i == 3) {
                getLinkById();
            }

            if (i == 2) {
                if (offset != 0) {
                    offset -= LIMIT;
                }
            }

            if (i == 4) {
                offset = 0;
                break;
            }
            if (i == 5 && userService.isAdmin()) {
                modifyById();
            }

        }
    }

    private static void modifyById() throws IOException {
        System.out.println("Enter id of book: ");
        String input = scanner.nextLine();
        int i = 0;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        Book book = bookService.getBookById(i);
        if (book == null) {
            System.out.println("Book with specific id do not exist");
        } else {
            System.out.println("Enter new name (old name = '" + book.getName() + "'):");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                book.setName(input);
            }
            System.out.println("Enter new description (old description = '" + book.getDesc() + "'):");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                book.setDesc(input);
            }

            System.out.println("Enter new author (old author = '" + book.getAuthor() + "'):");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                book.setAuthor(input);
            }

            System.out.println("Enter new link (old name = '" + book.getLink() + "'):");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                book.setLink(input);
            }
            bookService.saveBook(book);
            System.out.println("Book saved");
        }
        System.out.println("Press Enter to continue");
        scanner.nextLine();
    }

    private static void getLinkById() {
        System.out.println("Enter id of book: ");
        String input = scanner.nextLine();
        int i = 0;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        Book book = bookService.getBookById(i);
        if (book == null) {
            System.out.println("Book with specific id do not exist");
        } else {
            if (book.isEbook()) {
                System.out.println("Book link: " + book.getLink());
            } else {
                System.out.println("Book are real. Not eBook");
            }
        }
        System.out.println("Press Enter to continue");
        scanner.nextLine();
    }

}
