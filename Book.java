
import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String publisher;
    private String ISBN; 
    private int publicationDate; // Changed to Date type for accurate date handling
    private String category;
    private int totalCopies;
    private double rating;

    public Book(String title, String author, String publisher, String ISBN, int publicationDate, String category, int totalCopies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.publicationDate = publicationDate;
        this.category = category;
        this.totalCopies = totalCopies;
        this.rating = 0;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public double getRating() {
        return rating;
    }

    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
