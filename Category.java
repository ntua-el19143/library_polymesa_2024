import java.io.*;

public class Category implements Serializable {
  private static final long serialVersionUID = 1L;

  private String title;

  public Category(String title) {
      this.title = title;
  }

  public String getTitle() {
      return title;
  }

  public void setTitle(String newTitle){
      this.title = newTitle;
  }
  @Override
  public String toString() {
      return "Category{" +
              "title='" + title + '\'' +
              '}';
  }
}