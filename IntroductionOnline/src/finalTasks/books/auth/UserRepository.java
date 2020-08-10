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

    public static final String JSON_PATH = "src\\finalTasks\\books\\res\\users.json";
    private final ArrayList<User> users = new ArrayList<>();

    public UserRepository()  {
        if (!getJsonFile().isEmpty()) {
            JSONParser parser = new JSONParser();
            JSONArray jsonData = null;
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

    public void addUser(User user) throws IOException {
        if (getUserByEmail(user.getEmail())==null) {
            users.add(user);
            saveToFile();
        }
    }

    public User getUserById(int id) {
        return users.stream().filter(u->u.getId()==id).findFirst().orElse(null);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUserByEmail(String email) {
        return users.stream().filter(u->u.getEmail().equals(email)).findFirst().orElse(null);
    }

    private void saveToFile() throws IOException {
        JSONArray jsonArray = new JSONArray();
        users.forEach(u->{
            jsonArray.add(transformUserToJson(u));
        });
        Files.writeString(Path.of(JSON_PATH),jsonArray.toJSONString());
    }

    private JSONObject transformUserToJson(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("name", user.getName());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("admin", user.isAdmin());
        jsonObject.put("password", getMD5(user.getPassword()));
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

    public String getAdminEmail() {
        User user = users.stream().filter(User::isAdmin).findFirst().orElse(null);
        if (user!=null) {
            return user.getEmail();
        }
        return "";
    }
}
