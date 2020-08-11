package finalTasks.books.book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Some Crud repository for book entity
 */

public class BookRepository {

    public static final String JSON_PATH = "src\\finalTasks\\books\\res\\books.json"; //path to JSON file
    private final ArrayList<Book> books = new ArrayList<>();

    /**
     * Parse JSON file and save it to array list
     */
    public BookRepository() {
        if (!getJsonFile().isEmpty()) {
            JSONParser parser = new JSONParser();
            JSONArray jsonData;
            try {
                jsonData = (JSONArray) parser.parse(getJsonFile());
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            jsonData.forEach(jsonUser -> {
                JSONObject jsonObject = (JSONObject) jsonUser;
                books.add(new Book(Math.toIntExact((Long) jsonObject.get("id")), (String) jsonObject.get("name"),
                    (String) jsonObject.get("desc"), (String) jsonObject.get("author"),
                    (Boolean) jsonObject.get("ebook"), (String) jsonObject.get("link")));
            });
        }
    }

    /**
     * Adding new book ant try to generate unique id
     */

    public void addBook(Book book) {
        if (book.getId() == -1) {
            book.setId(books.size() + 1);
        }
        books.add(book);
        saveToFile();
    }

    /**
     * Find book by id
     */

    public Book getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    /**
     * Return array list of books
     */

    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Size of library
     */

    public int size() {
        return books.size();
    }

    /**
     * Save array list to JSON file. Using when added new book or modified
     */
    private void saveToFile() {
        JSONArray jsonArray = new JSONArray();
        books.forEach(b -> {
            jsonArray.add(transformUserToJson(b));
        });
        try {
            Files.writeString(Path.of(JSON_PATH), jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transform book object to JSON object
     */

    private JSONObject transformUserToJson(Book book) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", book.getId());
        jsonObject.put("name", book.getName());
        jsonObject.put("desc", book.getDesc());
        jsonObject.put("author", book.getAuthor());
        jsonObject.put("ebook", book.isEbook());
        jsonObject.put("link", book.getLink());
        return jsonObject;
    }

    /**
     * Read JSON file
     */

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(JSON_PATH));
            lines.forEach(builder::append);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * Save info about book after update
     */

    public void saveBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.set(i, book);
                saveToFile();
                return;
            }
        }

    }

    /**
     * Search book by query. Search in name, desc and author
     */

    public ArrayList<Book> search(String query) {
        if (query.isEmpty()) {
            return null;
        }
        return (ArrayList<Book>) books.stream().filter(
            b -> b.getName().contains(query) || b.getDesc().contains(query) || b.getAuthor().contains(query))
            .collect(
                Collectors.toList());
    }
}