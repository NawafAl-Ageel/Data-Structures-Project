package final1;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Event implements Comparable<Event> {
    Scanner input = new Scanner(System.in);
    private String title;
    private String location;

    LocalDateTime localTime = LocalDateTime.now();
    private LocalDateTime EventTime;

    // Linked list nodes
    private static Node head;
    private static Node current;
    private static Node Previous;

    // Constructor
    public Event(String title, String contactName, int year, int month, int dayOfMonth, int hour, int minute, String location) {

        this.title = title;
        this.location = location;

        // Add contact to the event
        addContact(LindkedList.searchContactByName(contactName));

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

    // Add a contact to the event
    public static void addContact(Contact newContactInEvent) {
        Node newContact = new Node(newContactInEvent);
        if (head == null) {
            head = new Node(newContactInEvent);
        } else {
            current = head;
            Previous = null;
            while (current != null) {
                if (current.data.getName().compareTo(newContact.data.getName()) == 0) {
                    System.out.println("The contact is already in the Event!");
                    return;
                } else {
                    Previous = current;
                    current = current.next;
                }
            }
            Previous.next = current.next;
            current = current.next;
        }
    }

    // Print all contacts in this event
    public static void PrintAllContactsInThisEvent() {
        current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("All Contacts Have Been Printed!");
    }

    // Print event details
    public static void printEvent() {
        
    }

    // Delete the event
    public static boolean deleteEvent() {
        head = null;
        return true;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static void main(String[] args) {
        LocalDateTime local = LocalDateTime.now();
        LocalDateTime EventTime = LocalDateTime.of(1000, 9, 10, 10, 20);

        System.out.println(local);
        System.out.println(EventTime);
    }

	@Override
	public int compareTo(Event o) {
		return this.title.compareTo(o.title);
	}
}
