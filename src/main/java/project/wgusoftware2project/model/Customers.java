package project.wgusoftware2project.model;

/** Customers class created to store rows extracted from
 * Customer Table data from MySQL database.
 */
public class Customers {
    int customerID;
    String customerName;
    String address;
    String postalCode;
    String phone;
    String divisionId;
    String state;

    /** Customers constructor to store rows extracted from
     * Customer Table data from MySQL database.
     @param customerID Integer storing customer_ID column of MySQL table row.
     @param customerName String storing name column of MySQL table row.
     @param address String storing address column of MySQL table row.
     @param postalCode String storing postal column of MySQL table row.
     @param phone String storing phone column of MySQL table row.
     @param divisionId String storing division_ID column of MySQL table row.
     @param state String storing state column of MySQL table row.
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone, String divisionId, String state) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.state = state;
    }


    @Override
    public String toString() {
        return (Integer.toString(customerID));
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public String getState() {
        return state;
    }

}