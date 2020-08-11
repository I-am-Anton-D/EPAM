package finalTasks.notepad;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Note entity
 */
public class Note {
    private int id;
    private String subject;
    private Date date;
    private String email;
    private String msg;
    private static int index = 0;

    public Note() {
        this.id = index++;
    }

    public Note(String subject, Date date, String email, String msg) {
        this.subject = subject;
        this.date = date;
        this.email = email;
        this.msg = msg;
        this.id = index++;

    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Note{" +
            "id = " + id + ", "+
            "subject='" + subject + '\'' +
            ", date=" + new SimpleDateFormat("yyyy-MM-dd").format(date) +
            ", email='" + email + '\'' +
            ", msg='" + msg + '\'' +
            '}';
    }
}
