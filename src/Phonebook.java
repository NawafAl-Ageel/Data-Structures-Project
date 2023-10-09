import java.util.*;

public class Phonebook {
	
	LinkedList contactsList = new LinkedList();
	ArrayList<Event> eventsList = new ArrayList<>();
	
	public void addContact() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Contact's name: ");
		String givenName = input.nextLine();
		System.out.println("Enter Phone Number: ");
		String givenPhoneNumber = input.nextLine();
		System.out.println("Enter Email Address: ");
		String givenEmailAddress = input.nextLine();
		System.out.println("Enter Address: ");
		String givenAddress = input.nextLine();
		System.out.println("Enter Birthday:");
		String givenBirthday = input.nextLine();
		System.out.println("Enter notes: ");
		String givenNotes = input.nextLine();
		Contact contact = new Contact(givenName, givenPhoneNumber, givenEmailAddress, givenAddress, givenBirthday, givenNotes);
		if(contactsList.addContact(contact))
			System.out.println("Contact added successfully!");
			System.out.println();
	}
	
	
	
	public void searchContact() {
		Scanner input = new Scanner(System.in);
		String searchedCriteria = null;
		System.out.println("Enter search criteria: ");
		System.out.println("1. Name");
		System.out.println("2. Phone Number");
		System.out.println("3. Email Address");
		System.out.println("4. Address");
		System.out.println("5. Birthday");
		int choice = input.nextInt();
		input.nextLine(); // to finish the line

		switch(choice) {
		case 1: System.out.println("Enter the Contact's name: ");  searchedCriteria = input.next(); break;
		case 2: System.out.println("Enter Phone Number: ");		 searchedCriteria = input.next(); break;
		case 3:	System.out.println("Enter Email Address: ");searchedCriteria = input.next(); break;
		case 4:	System.out.println("Enter Address: ");		 searchedCriteria = input.next(); break;
		case 5:	System.out.println("Enter Birthday:");	 searchedCriteria = input.next();	break;
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
			String givenContactName = input.nextLine();
			
			for (Event event : eventsList) {
				if(event.getContactName().equalsIgnoreCase(givenContactName)) {
					event.printEvent();
				}					
			}
			break;
			
		case 2:
			System.out.println("Enter the event title: ");
			String givenTitle = input.nextLine();
			for(Event event : eventsList) {
				if(event.getTitle().equalsIgnoreCase(givenTitle))
					event.printEvent();
			}
			break;
			
			default: System.out.println("Wrong number choosed!");
		}				
	}
	
	public void printContactsByFirstName() {
		Scanner input = new Scanner(System.in);
		String firstName = input.nextLine();	
		contactsList.printContactsByFirstName(firstName);
	}
	
	
	public void printAllEventsAlphanetically() {
		for(Event event : eventsList) {
			event.printEvent();
		}
	}
	
	public void runPhoneBook() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to the Linked Tree PhoneBook!");
		while(true) {
			System.out.println("Please choose an option:");
			System.out.println("1. Add a contact");
			System.out.println("2. Search for a contact");
			System.out.println("3. Delete a contact");
			System.out.println("4. Schedule an event");
			System.out.println("5. Print event details");
			System.out.println("6. Print contacts by first name");
			System.out.println("7. Print all events alphabetically");
			System.out.println("8. Exit");
			System.out.println("");
			
			int choice = input.nextInt();
			input.nextLine(); //to finish the line
			switch(choice) {
			case 1: addContact(); break;
			case 2: searchContact(); break;
			case 3: deleteContact(); break;
			case 4: scheduleEvent(); break;
			case 5: printEventDetails(); break;
			case 6: printContactsByFirstName(); break;
			case 7: printAllEventsAlphanetically(); break;
			case 8: 
				System.out.println("Goodbye");
				System.exit(0);		
				break;
				
				default:
					System.out.println("Wrong number choosed!");
			}
		}
		
	}
		
}