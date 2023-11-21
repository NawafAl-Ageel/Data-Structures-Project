
import java.time.LocalDateTime;

public class Event implements Comparable<Event> {

	private String title;
    private String location;
    private Contact[] contacts;
    private int numContacts;
    private LocalDateTime eventTime;
    private Contact con;
    private String contactName;

	// Constructor
	public Event(String title, String contactName, int year, int month, int dayOfMonth, int hour, int minute,
			String location) {

		this.title = title;
		this.location = location;
		Contact EventContact =ContactBST.searchByAnyTerm(contactName);
		if(EventContact==null) {
			System.out.println("The Contact doesn't exist !");
			return;
		}
		else if(!EventContact.HasEvent()) {
			System.out.println("The contact has already Scheduled an Event !");
			return;
		}
		this.contactName= EventContact.getName();
		con = EventContact;
		eventTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
	}
	
	public Event(String title, int year, int month, int dayOfMonth, int hour, int minute, String location, Contact[] contacts) {
        this.title = title;
        this.contacts = contacts; // Assuming a maximum of 10 contacts
        this.numContacts = contacts.length;

        // Check if any of the contacts already have an event
        for (Contact contact : contacts) {
            if (contact != null && !contact.HasEvent()) {
                System.out.println("The contact " + contact.getName() + " has already scheduled an event!");
                return;
            }
        }
        this.eventTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        this.location=location;
	}
	
	public void deleteContactFromEvent(String givenName) {
	    if (contacts != null) {
	        for (int i = 0; i < numContacts; i++) {
	            if (contacts[i] != null && contacts[i].getName().equals(givenName)) {
	                // Found the contact with the given name, remove it
	                contacts[i] = null;
	                if (numContacts == 0) {
	                    // If it's the last contact, set the event to null
	                    System.out.println("No contacts left in the event. Deleting the event.");
	                    this.contacts = null;
	                    this.numContacts = 0;
	                    this.eventTime = null;
	                    this.location = null;
	                }
	                numContacts--;
	                System.out.println("Contact " + givenName + " has been removed from the event.");
	                return;
	            }
	        }
	    }
	    System.out.println("Contact " + givenName + " not found in the event.");
	}
	
	public void printEvent() {
		if(contacts == null || numContacts <= 0) {
			System.out.println("all contacts are deleted in the event");
			return;
		}
        System.out.println("Event title: " + title);
        System.out.print("Event is with the contacts: ");
        if (contacts != null) {
            for (int i = 0; i < numContacts; i++) {
            	if (contacts[i] != null) {
                    System.out.print(contacts[i].getName() + " ");
                }
            }
        }
        System.out.println();
        System.out.println("Event date: " + getMonth() + "/" + getDay() + "/" + getYear() + " in Time : " + getHour()
                + ":" + getMinute());
        System.out.println("Event location: " + location);
        System.out.println();
    }
	
	public Contact[] getContacts() {
        return contacts;
    }
	
	public boolean isEvent() {
        // Check if the event has multiple contacts
        return contacts != null && contacts.length > 1;
    }
	
	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}

	public String getContactName() {
		return contactName;
	}

	public int getYear() {
		return eventTime.getYear();
	}

	public int getMonth() {
		return eventTime.getMonthValue();
	}

	public int getDay() {
		return eventTime.getDayOfMonth();
	}

	public int getHour() {
		return eventTime.getHour();
	}

	public int getMinute() {
		return eventTime.getMinute();
	}

	public int compareTo(Event o) {
		return this.title.toLowerCase().compareTo(o.title.toLowerCase());
	}
}