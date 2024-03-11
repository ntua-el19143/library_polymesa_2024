
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookEditor {
    private List<Book> books;
    private Set<String> uniqueIsbns;



       public BookEditor() {
        this.books = new ArrayList<>();
        this.uniqueIsbns = new HashSet<>();

    }


    public void addBook(Book book) {
        if (uniqueIsbns.contains(book.getISBN())) {
            System.out.println("Book with ISBN " + book.getISBN() + " already exists.");
        } 
        else {
            books.add(book);
            uniqueIsbns.add(book.getISBN());
            saveBooks();
            System.out.println("Book with title " + book.getTitle() + "succesfully added !");
        }
    }

    public void deleteBook(String ISBN) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN().equals(ISBN)) {
                books.remove(i);
                break;
            }
        }
        saveBooks();
    }

    public void editBook(String ISBN, String title, String author, String publisher, int publicationDate, String category, int totalCopies, double rating) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setPublicationDate(publicationDate);
                book.setCategory(category);
                book.setTotalCopies(totalCopies);
                book.setRating(rating);
                break;
            }
        }
        saveBooks();
    }

    public void saveBooks() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("medialab/books.ser"))) {
            outputStream.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadBooks() {
        File file = new File("medilab/books.ser");

        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                books = (List<Book>) inputStream.readObject();
                // Populate uniqueTitles based on the deserialized categories
                uniqueIsbns = books.stream().map(Book::getISBN).collect(Collectors.toSet());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File books.ser does not exist. You have to serialize first!!");
        }
    }
    public  List<Book> getBooks() {
        return new ArrayList<>(books); // Return a copy of the books list to prevent external modifications
    }
}
