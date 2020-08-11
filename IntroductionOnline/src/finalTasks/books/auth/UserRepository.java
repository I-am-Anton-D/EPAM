package finalTasks.books.auth;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Repository for users
 *
 * Users takes from JSON file and store to ArrayList
 *
 */

public class UserRepository {

    public static final String JSON_PATH = "src\\finalTasks\\books\\res\\users.json"; //Path to JSON file
    private final ArrayList<User> users = new ArrayList<>();

    /**
     * Load data from file. Parse JSON file
     */
    public UserRepository()  {
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
                users.add(new User(Math.toIntExact((Long) jsonObject.get("id")), (String) jsonObject.get("name"),
                    (String) jsonObject.get("email"), (Boolean) jsonObject.get("admin"),
                    (String) jsonObject.get("password")));
            });
        }
    }

    /**
     * Add new User. No access from console.
     */

    public void addUser(User user) {
        if (getUserByEmail(user.getEmail())==null) {
            users.add(user);
            saveToFile();
        }
    }

    /**
     * Find user by id
     */

    public User getUserById(int id) {
        return users.stream().filter(u->u.getId()==id).findFirst().orElse(null);
    }


    /**
     * Return array list of users. Using for main sending
     */

    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Getiing user by email
     */

    public User getUserByEmail(String email) {
        return users.stream().filter(u->u.getEmail().equals(email)).findFirst().orElse(null);
    }

    /**
     * Record array list by JSON file
     */

    private void saveToFile()  {
        JSONArray jsonArray = new JSONArray();
        users.forEach(u->{
            jsonArray.add(transformUserToJson(u));
        });
        try {
            Files.writeString(Path.of(JSON_PATH),jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Transform user object to JSON object
     */

    private JSONObject transformUserToJson(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("name", user.getName());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("admin", user.isAdmin());
        jsonObject.put("password", getMD5(user.getPassword()));
        return jsonObject;
    }


    /**
     * Read file
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
     * Encode string ti MD5
     */

    public static String getMD5(String string) {
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert m != null;
        m.reset();

        m.update(string.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder hashtext = new StringBuilder(bigInt.toString(16));

        while (hashtext.length() < 32) {
            hashtext.insert(0, "0");
        }
        return hashtext.toString();
    }

    /**
     * Find fist admin to sent offer of book
     */

    public String getAdminEmail() {
        User user = users.stream().filter(User::isAdmin).findFirst().orElse(null);
        if (user!=null) {
            return user.getEmail();
        }
        return "";
    }
}
