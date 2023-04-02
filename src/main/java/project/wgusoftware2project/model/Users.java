package project.wgusoftware2project.model;

/** Users class created to store rows extracted from
 * User Table data from MySQL database.
 */
public class Users {
    int userId;
    String userName;
    String password;

    /** Users constructor to store rows extracted from
     * User Table data from MySQL database.
     @param userId Integer storing user_Id column of MySQL table row.
     @param userName String storing user_Name column of MySQL table row.
     @param password String storing password column of MySQL table row.
     */
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

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
