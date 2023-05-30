package model;

public class Users {
    private int userID;
    private String userName;
    private String userEmail;
    private int userPhone;
    private String userAddress;

    public Users(int userID, String userName, String userEmail, int userPhone, String userAddress) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public Users() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "User [id=" + userID + ", name=" + userName + ", phone=" + userPhone + ", address=" + userAddress + ", email=" + userEmail + "]";
    }

}

