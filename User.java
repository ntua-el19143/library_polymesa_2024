  import java.io.*;
import java.util.*;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String email;
    private boolean isAdmin;


    // THEN I WILL MAKE IT PRIVATE, I WANT IT FOR DEBYGGING !!
    public List<Loan> activeLoans;

    public User(String username, String password, String firstName, String lastName, String idNumber, String email, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.isAdmin = isAdmin;

        this.activeLoans = new ArrayList<>();
    }

    

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }

    public String getIdNumber() {
        return this.idNumber;
    }
    public String getEmail() {
        return this.email;
    }

    public boolean getisAdmin() {
        return isAdmin;
    }

    public List<Loan> getActiveLoans() {
        if (activeLoans == null) {
            activeLoans = new ArrayList<>();
        }
        return activeLoans;
    }

    public void addActiveLoan(Loan loan) {
        activeLoans.add(loan);
    }

    public void removeActiveLoan(Loan loan) {
        this.activeLoans.remove(loan);
    }

    public void easyUpdate(String newPassword, String newFirstName, String newLastName, String newIdNumber, boolean newisAdmin){
        this.password = newPassword;
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.idNumber = newIdNumber;
        this.isAdmin = newisAdmin;
    }
    public void hardUpdate(String newUsername, String newPassword, String newFirstName, String newLastName, String newIdNumber, String newEmail, boolean newisAdmin){
        this.username = newUsername;
        this.password = newPassword;
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.idNumber = newIdNumber;
        this.email = newEmail;
        this.isAdmin = newisAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                '}';
    }
}


