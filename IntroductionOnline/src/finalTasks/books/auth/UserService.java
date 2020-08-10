package finalTasks.books.auth;


import finalTasks.books.book.Book;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.simple.parser.ParseException;

/**
 * Service for user
 */

public class UserService {

    private boolean auth = false;
    private boolean admin = false;
    UserRepository userRepository = new UserRepository();

    public void login(String email, String password) throws ParseException {

        User user = userRepository.getUserByEmail(email);
        if (user != null && user.getPassword().equals(UserRepository.getMD5(password))) {
            auth = true;
            admin = user.isAdmin();
        }
    }

    public boolean isAuth() {
        return auth;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void logout() {
        auth = false;
        admin = false;
    }

    public void sentToAll(String name) {
        userRepository.getUsers().forEach(u->sentEmail(u.getEmail(), name));
    }

    public void sentBookToAdmin(Book book) {
        String email = userRepository.getAdminEmail();
        sentEmail(email, book.toString());
    }

    /**
     * Sent Email
     *
     * There is normal implementation, but need real mail host, login and password to work
     * @param email to sent
     * @param name of book
     */



    private void sentEmail(String email, String name) {
        return;

        //        String to = email;
//        String from = "sender@abc.com";
//        String host = "127.0.0.1";
//
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//
//        Session session = Session.getDefaultInstance(properties);
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("Added new book"); // subject line
//            message.setText("In library added new book: " + name);
//               Transport.send(message); System.out.println("Email Sent successfully....");
//
//        } catch (MessagingException mex){ mex.printStackTrace(); }
    }
}
