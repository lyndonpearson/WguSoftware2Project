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

    /** Method to return Users object's userId field
     @return userId Integer storing userId field.
     */
    public int getUserId() {
        return userId;
    }

    /** Method to set Users object's userId field
     @param userId Integer assigned to userId field.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Method to return Users object's password field
     @return password String storing password field.
     */
    public String getPassword() {
        return password;
    }

    /** Method to return Users object's userName field
     @return userName String storing userName field.
     */
    public String getUserName() {
        return userName;
    }

    /** Override toString method to print/return
     * the userId casted to String to views instead of the Users object.
     */
    @Override
    public String toString(){
        return (Integer.toString(userId));
    }
}
