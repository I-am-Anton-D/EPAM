package finalTasks.books.book;

/**
 * Class for book entity
 */

public class Book {
    private int id;
    private String name;
    private String desc;
    private String author;
    private boolean ebook;
    private String link;


    /**
     * Creating new book. id = -1 - is's flag to autoincrement
     */
    public Book() {
        this.id = -1;
    }

    /**
     * Normal full constructor for book
     * @param id of book. Need to be unique
     * @param name of book
     * @param desc short description of book
     * @param author ot the book
     * @param ebook flag of electronic book
     * @param link to the ebook. Empty if book real
     */

    public Book(int id, String name, String desc, String author, boolean ebook, String link) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.ebook = ebook;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isEbook() {
        return ebook;
    }

    public void setEbook(boolean ebook) {
        this.ebook = ebook;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", desc='" + desc + '\'' +
            ", author='" + author + '\'' +
            ", ebook=" + ebook +
            '}';
    }
}
