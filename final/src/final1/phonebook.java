package lasttimeinshallah;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.*;

public class Phonebook {
	LocalDateTime localTime;
	Scanner input;
	LinkedList Linked;

	public Phonebook() {
		input = new Scanner(System.in);
		Linked = new LinkedList();
		localTime = LocalDateTime.now();

	}

	public void addContact() {
		input = new Scanner(System.in);

		String givenName;
		boolean containsAlphabets = false;
		do {
			System.out.println("Enter the Contact's name: ");
			givenName = input.nextLine();
			containsAlphabets = Pattern.matches(".*[a-zA-Z].*", givenName);
			if (!containsAlphabets) {
				System.out.println("Name must be a String !");
			}
			if (givenName.length() < 4) {
				System.out.println("Name must be 4 charcters long !");
			}
		} while (!containsAlphabets || givenName.length() < 4);

		String givenPhoneNumber = null;
		boolean isDigit = false;
		do {
			isDigit = true;
			System.out.println("Enter Phone Number: ");
			givenPhoneNumber = input.nextLine();

			if (givenPhoneNumber.length() != 10) {
				System.out.println("Number must be of length 10!");
				continue; // Continue to the next iteration of the loop.
			}

			for (int i = 0; i < givenPhoneNumber.length(); i++) {
				char c = givenPhoneNumber.charAt(i);
				if (!Character.isDigit(c)) {
					isDigit = false;
					break; // Exit the loop if a non-digit character is found.
				}
			}

			if (!isDigit) {
				System.out.println("The Phone number must be a number!");
			}

		} while (givenPhoneNumber.length() != 10 || !isDigit);

		System.out.println("Enter Email Address: ");
		String givenEmailAddress = input.nextLine();
		System.out.println("Enter Address: ");
		String givenAddress = input.nextLine();
		System.out.println("Enter Birthday:(MM/DD/YYYY)");
		String givenBirthday = input.nextLine();
		System.out.println("Enter notes: ");
		String givenNotes = input.nextLine();
		Contact contact = new Contact(givenName, givenPhoneNumber, givenEmailAddress, givenAddress, givenBirthday,
				givenNotes);
		if (Linked.addContact(contact))
			System.out.println("Contact added successfully!");
		System.out.println();

	}

	public void searchContact() {
		input = new Scanner(System.in);
		String searchedCriteria = null;
		int choice = 0;
		do {
			System.out.println("Enter search criteria: ");
			System.out.println("1. Name");
			System.out.println("2. Phone Number");
			System.out.println("3. Email Address");
			System.out.println("4. Address");
			System.out.println("5. Birthday");
			try {
				choice = input.nextInt();
			} catch (Exception erorr) {
				System.out.println("Sorry but you have to choose between these criterias !");
				input.nextLine();
			}
		} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);

		switch (choice) {
		case 1:
			System.out.println("Enter the Contact's name: ");
			searchedCriteria = input.next();
			break;
		case 2:
			System.out.println("Enter Phone Number: ");
			searchedCriteria = input.next();
			break;
		case 3:
			System.out.println("Enter Email Address: ");
			searchedCriteria = input.next();
			break;
		case 4:
			System.out.println("Enter Address: ");
			searchedCriteria = input.next();
			break;
		case 5:
			System.out.println("Enter Birthday:");
			searchedCriteria = input.next();
			break;
		}
		Contact searchedContact = Linked.searchByAnyTerm(searchedCriteria);
		if (searchedContact != null) {
			searchedContact.printContactInfo();
		} else {
			System.out.println("Unfound Contact !");
		}
		input.nextLine();
	}

	public void deleteContact() {
		input = new Scanner(System.in);
		System.out.println("Enter the contact's name that you want to delete: ");
		String deletingName = input.nextLine();

		if (Linked.deleteContact(deletingName)) {
			System.out.println("Contact has been deleted");
		} else {
			System.out.println("Contact was not found");
		}
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

		return new int[] { year, month, day, hour, minutes };
	}

	
	public void scheduleEvent() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter event title: ");
		String eventTitle = input.nextLine();

		System.out.println("Enter contact name: ");
		String contactName = input.nextLine();

		Contact scheduledContact = Linked.searchByAnyTerm(contactName);
		if (scheduledContact == null) {
			System.out.println("Contact was not found");
			return;
		}

		int resultDateTime[];
		int year = 0;
		int month = 0;
		int dayOfMonth = 0;
		int hour = 0;
		int minute = 0;
		String eventDateAndTime = null;
		do {
			try {
				System.out.println("Enter event date and time (MM/DD/YYYY HH:MM)");
				eventDateAndTime = input.nextLine();
				resultDateTime = splitDateTime(eventDateAndTime);
				year = resultDateTime[0];
				month = resultDateTime[1];
				dayOfMonth = resultDateTime[2];
				hour = resultDateTime[3];
				minute = resultDateTime[4];

			} catch (Exception e) {
				System.out.println("Enter right value");
				input.nextLine();
			}

		} while (month > 12 || dayOfMonth > 31 || hour > 24 || minute > 60 || eventDateAndTime.length() < 16
				|| localTime.getYear() > year || (localTime.getYear() == year && month < localTime.getDayOfMonth()));

		System.out.println("Enter event location: ");
		String eventLocation = input.nextLine();

		Event event = new Event(eventTitle, contactName, year, month, dayOfMonth, hour, minute, eventLocation);
		scheduledContact.addEvent(event);
		Linked.addEvent(event);
		System.out.println("!Event have been Scheduled!");
		System.out.println();
	}

	public void printEventDetails() {
		input = new Scanner(System.in);
		int choice = 0;

		do {
			System.out.println("Enter search criteria: ");
			System.out.println("1. Contact name: ");
			System.out.println("2. Event title: ");

			try {
				choice = input.nextInt();

			} catch (Exception e) {
				System.out.println("Enter the Right choice please !");
				input.nextLine();
			}

		} while (choice != 1 && choice != 2);

		switch (choice) {
		case 1:
			System.out.println("Enter the contact's name: ");
			String givenContactName = input.next();
			Contact ContactE = Linked.searchByAnyTerm(givenContactName);
			if (ContactE != null && !ContactE.HasEvent()) {
				ContactE.getEvent().printEvent();
			} else {
				System.out.println("Event or Contact not found !");
			}
			break;
		case 2:
			System.out.println("Enter the event title: ");
			String givenTitle = input.next();
			Event Eventy = LinkedList.searchBytitle(givenTitle);
			if (Eventy != null) {
				Eventy.printEvent();
			} else {
				System.out.println("No Event 've been found !");
			}
			input.nextLine();
			break;
		}

	}

	public void printContactsByFirstName() {
		System.out.println("Enter the first name:");
		input = new Scanner(System.in);
		String firstName = input.nextLine();
		Linked.printContactsByFirstName(firstName);
		System.out.println("finished !");
	}

	public void printAllEventsAlphanetically() {
		Linked.PrintAllEvents();
	}

	public void runPhoneBook() {
		input = new Scanner(System.in);
		System.out.println("Welcome to the Linked Tree PhoneBook!");
		int choice = 0;
		do {

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
			try {
				choice = input.nextInt();
			} catch (Exception erorr) {
				System.out.println("# Enter the Right choice please # \n");
				input.nextLine();
			}
			switch (choice) {
			case 1:
				addContact();
				break;
			case 2:
				searchContact();
				break;
			case 3:
				deleteContact();
				break;
			case 4:
				scheduleEvent();
				break;
			case 5:
				printEventDetails();
				break;
			case 6:
				printContactsByFirstName();
				break;
			case 7:
				printAllEventsAlphanetically();
				break;
			case 8:
				System.out.println("Goodbye");

			}
		} while (choice != 8);
		input.close();
	}

	public static void main(String[] args) {
		Phonebook app = new Phonebook();
		app.runPhoneBook();
	}
}
