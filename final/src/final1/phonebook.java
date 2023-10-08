package final1;

import java.util.*;

public class Phonebook {
	
	LinkedList contactsList = new LinkedList();
	LinkedList eventsList = new LinkedList();
	
	public void addContact(String name, String phoneNumber, String emailAddress, String address,String birthday, String notes) {
		Contact contact = new Contact(name, phoneNumber, emailAddress, address, birthday, notes);
		contactsList.addContact(contact);
	}
	
	
	
	public void searchContact() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter search criteria: ");
		System.out.println("1. Name");
		System.out.println("2. Phone Number");
		System.out.println("3. Email Address");
		System.out.println("4. Address");
		System.out.println("5. Birthday");
		int choice = input.nextInt();
		input.nextLine(); // to finish the line

		String searchedCriteria = null;
		switch(choice) {
		case 1: System.out.println("Enter the Contact's name: ");  searchedCriteria = input.next();
		case 2: System.out.println("Enter Phone Number: ");		 searchedCriteria = input.next();
		case 3:	System.out.println("Enter Email Address: ");searchedCriteria = input.next();
		case 4:	System.out.println("Enter Address: ");		 searchedCriteria = input.next();
		case 5:	System.out.println("Enter Birthday:");	 searchedCriteria = input.next();			
			default:
				System.out.println("Wrong Number choosed!");
		}
		Contact searchedContact = contactsList.searchByAnyTerm(searchedCriteria);
		contactsList.printContactInfo(searchedContact);
	}
	
	public void deleteContact() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the contact's name that you want to delete: ");
		String deletingName = input.nextLine();
		
		if(contactsList.deleteContact(deletingName))
			System.out.println("Contact has been deleted");
		else
			System.out.println("Contact was not found");
	}
	
	private static int[] splitDateTime(String dateTimeStr) {
        String[] parts = dateTimeStr.split(" ");
        String[] dateParts = parts[0].split("/");
        String[] timeParts = parts[1].split(":");

        int year = Integer.parseInt(dateParts[2]);
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int hour = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        return new int[]{year, month, day, hour, minutes};
    }
	
	public void scheduleEvent() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter event title: ");
		String eventTitle = input.nextLine();
		
		System.out.println("Enter contact name: ");
		String contactName = input.nextLine();
		
		Contact scheduledContact = contactsList.searchContactByName(contactName);
		if (scheduledContact == null ) {
			System.out.println("Contact was not found");
			return;
		}
		
		System.out.println("Enter event date and time (MM/DD/YYYY HH:MM)");
		String eventDateAndTime = input.nextLine();
		System.out.println("Enter event location: ");
		String eventLocation = input.nextLine();
		
		int resultDateTime[] = splitDateTime(eventDateAndTime);
		int year = resultDateTime[0];
		int month = resultDateTime[1];
		int dayOfMonth = resultDateTime[2];
		int hour = resultDateTime[3];
		int minute = resultDateTime[4];
		
		Event event = new Event(eventTitle, contactName, year, month, dayOfMonth, hour, minute, eventLocation);
		scheduledContact.addEvent(event);
		System.out.println("Event have been Scheduled");
	}
	
	public void printEventDetails() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter search criteria: ");
		System.out.println("1. Contact name: ");
		System.out.println("2. Event title: ");

		int choice = input.nextInt();
		input.nextLine(); // to finish the line
		
		switch(choice) {
		case 1:
			System.out.println("Enter the contact's name: ");
			String giveContactName = input.nextLine();
			while(eventsList.current!=null) {
				//if(eventsList.current             there is a problem here)
			}
			
		case 2:
			
			default:
				break;
		}
	}
	
	
	
	
	
}
