package project.wgusoftware2project.model;

public class Customers {
    int customerID;
    String customerName;
    String address;
    String postalCode;
    String phone;
    String divisionId;
    String state;

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