package project.wgusoftware2project.model;

public class Contacts {
    int contactId;
    String contactName;
    String email;

    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    public int getContactId() {
        return contactId;
    }

    @Override
    public String toString(){
        return (Integer.toString(contactId));
    }
}
