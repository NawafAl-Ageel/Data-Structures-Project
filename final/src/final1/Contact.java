package lasttimeinshallah;

public class Contact implements Comparable<Contact> {

    // Contact properties
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String birthday;
    private String notes;

    private Event event;

    // Constructor
    public Contact(String name, String phoneNumber, String emailAddress, String address, String birthday, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
        this.event = null; // Initialize event to null
    }

    // Method to add an event to the contact
    public boolean addEvent(Event newEvent) {
        if (this.event == null) {
            this.event = newEvent;
            return true;
        } else {
            System.out.println("The contact already has an event.");
            return false;
        }
    }

    // Compare contacts based on their names
    public int compareTo(Contact other) {
        return this.name.toLowerCase().compareTo(other.name.toLowerCase());
    }

    // Method to print contact information
    public void printContactInfo() {
        System.out.println();
        System.out.println("Contact's Name: " + this.getName());
        System.out.println("Contact's Phone Number: " + this.getPhoneNumber());
        System.out.println("Contact's Email Address: " + this.getEmailAddress());
        System.out.println("Contact's Birthday: " + this.getBirthday());
        System.out.println("Contact's Address: " + this.getAddress());
        System.out.println("Contact's Notes: " + this.getNotes());
        System.out.println();
    }

    // Getters for contact properties
    public String getName() {
        return name;
    }

    public Event getEvent() {
        return event;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getNotes() {
        return notes;
    }

	public boolean HasEvent() {
    	return event==null;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress
                + ", address=" + address + ", birthday=" + birthday + ", notes=" + notes + "]";
    }
}
	
	
	
	

}
