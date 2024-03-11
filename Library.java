

public class Library {


    private BookEditor bookEditor;
    private CategoryEditor categoryEditor;
    public Library(){
        this.bookEditor = new BookEditor();
        this.categoryEditor = new CategoryEditor();


    }
    public  void initialization() {
        // Initialize BookEditor and CategoryEditor
      BookEditor bookEditor = new BookEditor();
      CategoryEditor categoryEditor = new CategoryEditor();
    // Adding some categories
        Category c = new Category("Fiction");
       categoryEditor.addCategory(c);
       Category c2 = new Category("Science");
     categoryEditor.addCategory(c2);
        // Adding some books
 Book b1 = new Book("The Hobbit", "J.R.R. Tolkien", "George Allen & Unwin", "9780547928227", 1937, "Fiction", 5);
 bookEditor.addBook(b1);
        Book b2 = new Book("A Brief History of Time", "Stephen Hawking", "Bantam Books", "9780553380163", 1988, "Science", 3);
bookEditor.addBook(b2);
        // Save books to the medialab/books.ser
        bookEditor.loadBooks();
        categoryEditor.loadCategories();
        System.out.println("/nBooks now in store:");
        bookEditor.getBooks().forEach(System.out::println);
        System.out.println("\nCategories in Library now:");
        categoryEditor.getCategories().forEach(System.out::println);

    }
    public static void main(String[] args){
        Library library = new Library();
        library.initialization();
    }
}

// Assuming Book, BookEditor, and CategoryEditor classes are defined and corrected as previously discussed
