package finalTasks.notepad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Repository for notes. CRUD.
 */

public class NoteRepository {

    private static final String JSON_PATH = "src\\finalTasks\\notepad\\notes.json"; //Path to Json
    private static final String PATTERN ="yyyy-MM-dd" ; //Pattern for date
    private final ArrayList<Note> notes = new ArrayList<>(); //main array list of notes

    /**
     * Load notes from JSON and soring it by date
     */
    public NoteRepository() {
        if (!getJsonFile().isEmpty()) {
            JSONParser parser = new JSONParser();
            JSONArray jsonData;
            try {
                jsonData = (JSONArray) parser.parse(getJsonFile());
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            jsonData.forEach(json -> {
                JSONObject jsonObject = (JSONObject) json;
                String subject = (String) jsonObject.get("subject");
                String email = (String) jsonObject.get("email");
                Date date = getDateByString((String) jsonObject.get("date"));
                String msg = (String) jsonObject.get("msg");
                notes.add(new Note(subject,date,email,msg));
            });
            notes.sort(Comparator.comparing(Note::getDate).reversed());
        }
    }

    /**
     * Add new note and sorting
     */
    public void create(Note note) {
        notes.add(note);
        notes.sort(Comparator.comparing(Note::getDate).reversed());
    }

    /**
     * Delete some note
     */

    public void delete(Note note) {
        notes.remove(note);
    }

    /**
     * Update some note
     */

    public void update(Note note) {
        for (int i = 0; i <notes.size() ; i++) {
            if (notes.get(i).getId() == note.getId()) {
                notes.set(i,note);
                return;
            }
        }
    }

    /**
     * Return size of notes
     */

    public int getSize(){
        return notes.size();
    }

    /**
     * Get arraylist of notes with specific offset and limit
     * @param offset left border
     * @param limit count of notes
     */

    public ArrayList<Note> getNotes(int offset, int limit) {
        if (offset<0) return null;
        return (ArrayList<Note>) notes.stream().skip(offset).limit(limit).collect(Collectors.toList());
    }

    /**
     * Searching notes by specific predicate
     * @param predicate some predicate function
     */

    public ArrayList<Note> search(Predicate<Note> predicate) {
        return (ArrayList<Note>)notes.stream().filter(predicate)
            .collect(
                Collectors.toList());
    }

    /**
     * Predicate by email
     */

    public Predicate<Note> getPredicateByMail(String mail) {
        return note -> note.getEmail().equals(mail);
    }

    /**
     * Predicate by subject
     */

    public Predicate<Note> getPredicateBySubject(String subject) {
        return note -> note.getSubject().equals(subject);
    }

    /**
     * Predicate by some query in message
     */

    public Predicate<Note> getPredicateByMsg(String query) {
        return note -> note.getMsg().contains(query);
    }

    /**
     * Predicate by date
     */

    public Predicate<Note> getPredicateByDate(String date) {
        return note -> getStringByDate(note.getDate()).equals(date);
    }

    /**
     * Sort notes by comparator
     * @param comparator specific comparator
     */
    public void sort (Comparator<Note> comparator) {
        notes.sort(comparator);
    }

    /**
     * Getting note by id
     * @param id - specific id
     */

    public Note getNoteById(int id) {
        return notes.stream().filter(note -> note.getId()==id).findFirst().orElse(null);
    }

    /**
     * Save arraylist ot JSON file before exit
     */
    public void saveToFile() {
        JSONArray jsonArray = new JSONArray();
        notes.forEach(b -> {
            jsonArray.add(transformUserToJson(b));
        });
        try {
            Files.writeString(Path.of(JSON_PATH), jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transform object Note to JSON object
     */

    private JSONObject transformUserToJson(Note note) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subject", note.getSubject());
        jsonObject.put("date", getStringByDate(note.getDate()));
        jsonObject.put("email", note.getEmail());
        jsonObject.put("msg", note.getMsg());
        return jsonObject;
    }

    /**
     * Transform date to string by pattern
     */

    public static Date getDateByString(String s) {
        Date date = null;
        try {
            date = new SimpleDateFormat(PATTERN).parse(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Transform string to date by pattern
     */

    public static String getStringByDate(Date date) {
        return new SimpleDateFormat(PATTERN).format(date);
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

}
