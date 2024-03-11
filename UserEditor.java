import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class UserEditor {
  private List<User> users;
  private Set<String> uniqueIdNumbers;
  private Set<String> uniqueEmails;
  private Set<String> uniqueUsernames;

  public UserEditor() {
      this.users = new ArrayList<>();
      this.uniqueIdNumbers = new HashSet<>();
      this.uniqueEmails = new HashSet<>();
      this.uniqueUsernames = new HashSet<>();
  }

  public void registerUser(User user) {
     
      if (uniqueIdNumbers.contains(user.getIdNumber()) && uniqueIdNumbers != null) {
          System.out.println("User with Id Number " + user.getIdNumber() + " already exists.");
      } 
      else if (uniqueEmails.contains(user.getEmail()) && uniqueEmails != null){
          System.out.println("User with Email " + user.getEmail() + " already exists.");
      }
      else if (uniqueUsernames.contains(user.getUsername()) && uniqueUsernames != null){
          System.out.println("User with Username " + user.getUsername() + " already exists.");
      }
      else {
          users.add(user);
          uniqueIdNumbers.add(user.getIdNumber());
          uniqueEmails.add(user.getEmail());
          uniqueUsernames.add(user.getUsername());
          serializeUsers();
      }
  }

  public List<User> getUsers() {
      return new ArrayList<>(users);
  }

  public User getUserByUsername(String username) {
      return users.stream()
              .filter(user -> user.getUsername().equals(username))
              .findFirst()
              .orElse(null);
  }

  public User getUserByEmail(String email) {
      return users.stream()
              .filter(user -> user.getEmail().equals(email))
              .findFirst()
              .orElse(null);
  }

  public void removeUserLoansList(User user){
      List<Loan> activeLoansCopy = new ArrayList<>(user.getActiveLoans());
      //System.out.println(activeLoansCopy);
      for (Loan loan : activeLoansCopy) {
          user.removeActiveLoan(loan); 
      }
  }

  public void removeUsersActiveLoansByIsbn(String isbn){
      // Remove book from active loans of users
      for (User user : getUsers()) {
          user.getActiveLoans().removeIf(loan -> loan.getIsbn().equals(isbn));
          
      }
  }

  public void removeUser(User user){
      uniqueIdNumbers.remove(user.getIdNumber());
      uniqueEmails.remove(user.getEmail());
      uniqueUsernames.remove(user.getUsername());
      users.remove(user);
  }
  
  public void modifiedBookForAllUsers(String oldISBN, String newISBN, String newTitle) {
      for (User user : getUsers()) {
          for (Loan loan : user.getActiveLoans()) {
              if (loan.getIsbn().equals(oldISBN)) {
                  loan.setIsbn(newISBN);
                  loan.setTitle(newTitle);
              }
          }
      }
  }

  public List<String> loanedIsbns(User user){
      
      List<String> isbns = new ArrayList<String>();
      
      for (Loan loan : user.getActiveLoans()) {
          isbns.add(loan.getIsbn());
      }
      return isbns;
  }

  public void updateUser(String newUsername, String newEmail, String oldUsername){
      User user = getUserByUsername(oldUsername);
      for (Loan loan : user.getActiveLoans()) {
          if (loan.getUsername().equals(oldUsername)) {
              loan.setUsername(newUsername);
              loan.setEmail(newEmail);
          }
      }
  }
      
  public void serializeUsers() {
      try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./medialab/users.ser"))) {
          outputStream.writeObject(users);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public void deserializeUsers() {
      File file = new File("./medialab/users.ser");
      
      if (file.exists()) {
          try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
              users = (List<User>) inputStream.readObject();
              // Populate uniqueIsbns set based on existing books
              uniqueIdNumbers.clear(); // Clear existing set
              uniqueEmails.clear();
              uniqueUsernames.clear();

              uniqueIdNumbers.addAll(users.stream().map(User::getIdNumber).collect(Collectors.toSet()));
              uniqueEmails.addAll(users.stream().map(User::getEmail).collect(Collectors.toSet()));
              uniqueUsernames.addAll(users.stream().map(User::getUsername).collect(Collectors.toSet()));
          } catch (IOException | ClassNotFoundException e) {
              e.printStackTrace();
          }
      } else {
          System.out.println("File user.ser does not exist. You have to serialize first!!");
      }
  }
}

