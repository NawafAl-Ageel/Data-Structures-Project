

import java.time.*;
import java.util.Scanner;

public class Event implements Comparable<Event> {
    Scanner input = new Scanner(System.in);
    private String title;
    private String location;
    private String contactName;
    private String eventDate;
  
    LocalDateTime localTime = LocalDateTime.now();
    private LocalDateTime EventTime;

    // Linked list nodes
    static Node head;
    static Node current;
     static Node Previous;

    // Constructor
    public Event(String title, String contactName, int year, int month, int dayOfMonth, int hour, int minute, String location) {

        this.title = title;
        this.location = location;

        // Add contact to the event
        addContact(LinkedList.searchContactByName(contactName));

        // Validate event date and time
        while (month > 12 || dayOfMonth > 31 || hour > 24 || minute > 60 || localTime.getYear() > year
                || (localTime.getYear() == year && month < localTime.getDayOfMonth())) {

            System.out.println("Sorry, invalid date or time.");
            System.out.print("Enter year: ");
            year = input.nextInt();
            System.out.print("Enter month: ");
            month = input.nextInt();
            System.out.print("Enter dayOfMonth: ");
            dayOfMonth = input.nextInt();
            System.out.print("\nEnter hour: ");
            hour = input.nextInt();
            System.out.print("Enter minute: ");
            minute = input.nextInt();
        }
        EventTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    public String getTitle() {
    	return title;
    }
    
    public String getLocation() {
        return location;
    }
    
    public Object getYear() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getMonth() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getContactName() {
		return contactName;
	}
	
	public String getEventDate() {
		return eventDate;
	}
	
	@Override
	public int compareTo(Event o) {
		return this.title.compareTo(o.title);
	}

	
	
    
    
//    // Add a contact to the event
 public static boolean addContact(Contact newContactInEvent) {
////        Node newContact = new Node(newContactInEvent);
////        if (head == null) {
////            head = new Node(newContactInEvent);
////        } else {
////            current = head;
////            Previous = null;
////            while (current != null) {
////                if (current.data.getName().compareTo(newContact.data.getName()) == 0) {
////                    System.out.println("The contact is already in the Event!");
////                    return false;        // edited
////                } else {
////                    Previous = current;
////                    current = current.next;
////                }
////            }
////            Previous.next = current.next;
////            current = current.next;
////        }
      return true;                //edited
   }

    // Print all contacts in this event
    

    // Print event details
    public void printEvent() {
        System.out.println("Event title: "+ title);
        System.out.println("Event is with the contact: "+ contactName);
        System.out.println("Event location: "+ location);
        System.out.println("Event date: "+ eventDate);
    }

    // Delete the event
    public static boolean deleteEvent() {
        head = null;
        return true;
    }

    // Getters and Setters

    public void setTitle(String title) {
        this.title = title;
    }

    

    public void setLocation(String location) {
        this.location = location;
    }

  

	
	
}