

public class SignUp {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    
    public SignUp(String name, String email, String password, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public boolean signUp() {
        boolean isSignedUp = false;
        // Validate input details
        if (isValidInput()) {
            // Call the signup api
            isSignedUp = registerUser();
        }
        return isSignedUp;
    }
    
    private boolean isValidInput() {
        // Validate input details
        return true;
    }
    
    private boolean registerUser() {
        // Call the signup api
        return true;
    }
}