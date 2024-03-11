import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String title;
    private String isbn;
    private Date date;
    private Date deadline;

    public Loan(String username, String email, String title, String isbn, Date date, Date deadline) {
        this.username = username;
        this.email = email;
        this.title = title;
        this.isbn = isbn;
        this.date = date;
        this.deadline = deadline;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTitle() {
        return this.title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Date getDate() {
        return this.date;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setIsbn(String newISBN){
        this.isbn = newISBN;
    }

    public void setUsername(String newUsername){
        this.username = newUsername;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Loan{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", date=" + sdf.format(date) +
                ", deadline=" + sdf.format(deadline) +
                '}';
    }
}