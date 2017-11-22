package pe.edu.upc.rapidbar.models;

public class UserLogin {
    private String id;
    private String name;
    private String userName;
    private String userType;

    public UserLogin(String id, String name, String userName, String userType) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.userType = userType;
    }

    public UserLogin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
