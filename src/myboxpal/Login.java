package myboxpal;

public class Login {
    private String username; // Private variable that can only be accessed within this class
    private String password; // Private variable to protect password data
    
    public Login(String username, String password) { // Public constructor to create Login objects
        this.username = username; // Assigns the parameter to the private username field
        this.password = password; // Assigns the parameter to the private password field
    }
    
    public String getUn() { // Public getter method to retrieve username
        return username; // Returns the private username variable
    }
    
    public String getPwd() { // Public getter method to retrieve password
        return password; // Returns the private password variable
    }
}
