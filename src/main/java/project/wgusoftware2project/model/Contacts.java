package project.wgusoftware2project.model;

/** Contacts class created to store rows extracted from
 * Contacts Table data from MySQL database.
 */
public class Contacts {
    int contactId;
    String contactName;
    String email;

    /** Contacts constructor to store rows extracted from
     * Contacts Table data from MySQL database.
     @param contactId Integer storing contact_ID column of MySQL table row.
     @param contactName String storing title name of MySQL table row.
     @param email String storing description email of MySQL table row.
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /** Method to return Contacts object's contactId field
     @return contactId Integer storing contactId field.
     */
    public int getContactId() {
        return contactId;
    }

    /** Override toString method to print/return
     * the contactId to views instead of the Contacts object.
     */
    @Override
    public String toString(){
        return (Integer.toString(contactId));
    }
}
