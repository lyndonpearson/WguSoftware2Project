package project.wgusoftware2project.model;

public class Users {
    int userId;
    String userName;
    String password;

    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString(){
        return (Integer.toString(userId));
    }
}
