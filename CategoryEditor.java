import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

class CategoryEditor {
    private List<Category> categories;
    private Set<String> uniqueTitles;


    public CategoryEditor() {
        this.categories = new ArrayList<>();
        this.uniqueTitles = new HashSet<>();
    }

    public  void addCategory(Category category) {
        if (uniqueTitles.contains(category.getTitle())) {
            System.out.println("Category with title " + category.getTitle() + " already exists.");
        } 
        else {
            categories.add(category);
            uniqueTitles.add(category.getTitle());
            saveCategories();
        }
    }
    public Set<String> getUniqueTitles(){
        return this.uniqueTitles;
    }
    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public Category getCategoryByCategoryTitle(String catTitle) {
        return categories.stream()
                .filter(category-> category.getTitle().equals(catTitle))
                .findFirst()
                .orElse(null);
    }

    public void removeCategory(Category category) {
        boolean removed = categories.remove(category);
        if (removed) {
            uniqueTitles.remove(category.getTitle());
            saveCategories();
        }
    }

    public void saveCategories() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("medialab/categories.ser"))) {
            outputStream.writeObject(categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadCategories() {
        File file = new File("medialab/categories.ser");

        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                categories = (List<Category>) inputStream.readObject();
                // Populate uniqueTitles based on the deserialized categories
                uniqueTitles = categories.stream().map(Category::getTitle).collect(Collectors.toSet());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File categories.ser does not exist. You have to serialize first!!");
        }
    }
}
