package Authentication;

public class Auth {
    private final int userID;
    private String username;
    private String email;
        private String contactNumber;
    public Auth(int userID, String username, String email, String contactNumber){
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public int getUserID() {
        return userID;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
