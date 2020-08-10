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

public class BookRepository {

    public static final String JSON_PATH = "src\\finalTasks\\books\\res\\books.json";
    private final ArrayList<Book> books = new ArrayList<>();

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

    public void addBook(Book book) throws IOException {
        if (book.getId()==-1) {
            book.setId(books.size()+1);
        }
        books.add(book);
        saveToFile();
    }

    public Book getBookById(int id) {
        return books.stream().filter(b->b.getId()==id).findFirst().orElse(null);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int size() {
        return books.size();
    }

    private void saveToFile() throws IOException {
        JSONArray jsonArray = new JSONArray();
        books.forEach(b->{
            jsonArray.add(transformUserToJson(b));
        });
        Files.writeString(Path.of(JSON_PATH),jsonArray.toJSONString());
    }

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

    public void saveBook(Book book) throws IOException {
        for (int i = 0; i <books.size() ; i++) {
            if (books.get(i).getId() == book.getId()) {
                books.set(i, book);
                saveToFile();
                return;
            }
        }

    }

    public ArrayList<Book> search(String query) {
        if (query.isEmpty()) return null;
        return (ArrayList<Book>) books.stream().filter(b->b.getName().contains(query) || b.getDesc().contains(query) || b.getAuthor().contains(query)).collect(
            Collectors.toList());
    }
}