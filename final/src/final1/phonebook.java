package final1;

import java.util.*;

public class Phonebook {
	public static void main(String[] args) {
	LinkedList l = new LinkedList();
	Scanner input = new Scanner(System.in);
	int choice = 0;
	
	do {
		System.out.println("");
		System.out.println("Welcome to the Linked Tree Phonebook!");
		System.out.println();
		System.out.println("Pleas choose an option");
		System.out.println("1- Add a Contact");
		System.out.println("2- Search for a contact");
		System.out.println("3- Delete a contact");
		System.out.println("4- Schesule an event");
		System.out.println("5- Print event details");
		System.out.println("6- Print contact by first name");
		System.out.println("7- Print all events alphabetically");
		System.out.println("8- Exit");
		choice = input.nextInt();
		
		switch(choice) {
		case 1:
			System.out.println("Enter the contact's name: ");
			String givenName = input.next();
			System.out.println("Enter the contact's number: ");
			String givenNumber = input.next();
			System.out.println("Enter the contact's email address: ");
			String givenEmail = input.next();
			System.out.println("Enter the contact's address: ");
			String givenAddress = input.next();
			System.out.println("Enter the contact's birthday: ");
			String givenBirthday = input.next();
			System.out.println("Enter any notes for the contact");
			String givenNotes = input.next();
			
			Contact c = new Contact(givenName, givenNumber, givenEmail, givenAddress, givenBirthday, givenNotes);			
			l.addContact(c);
			if(l.searchByAnyTerm(givenName).getName()==givenName)
				System.out.println("contact succesfully added");
		
		case 2:
			System.out.println("Enter search criteria: ");
			System.out.println("1. Name");
			System.out.println("2. Phone Number");
			System.out.println("3. Email Address");
			System.out.println("4. Address");
			System.out.println("5. Birthday");
			int Secondarychoice = input.nextInt();
			
			switch(Secondarychoice) {
			case 1: System.out.println("Enter the Contact's name: "); String searchedName = input.next();
			if(l.searchByAnyTerm(searchedName).getName()==searchedName) System.out.println("Contact is included");
			
			case 2: System.out.println("Enter Phone Number: "); String searchedNumber = input.next();
			if(l.searchByAnyTerm(searchedNumber).getName()==searchedNumber) System.out.println("Contact is included");
			
			case 3: System.out.println("Enter Email Address: "); String searchedEmail = input.next();
			if(l.searchByAnyTerm(searchedEmail).getName()==searchedEmail) System.out.println("Contact is included");
			
			case 4: System.out.println("Enter Address: "); String searchedAddress = input.next();
			if(l.searchByAnyTerm(searchedAddress).getName()==searchedAddress) System.out.println("Contact is included");
			
			case 5: System.out.println("Enter Birthday:"); String searchedBirthday = input.next();
			if(l.searchByAnyTerm(searchedBirthday).getName()==searchedBirthday) System.out.println("Contact is included");
			}
			
		case 3: 
			System.out.println("Enter contact's name, Phone Number, Email Address, Address or Birthday: ");
			String givenInfo = input.next();
			l.deleteContact(l.searchByAnyTerm(givenInfo).getName());
			
		case 4:
			System.out.println("Enter even title:");
			String givenEventTitle = input.next();
			System.out.println("Enter contact's name: ");
			String givenEventName = input.next();
			System.out.println("Enter event date and time: ");
			String givenEventDate = input.next();
			System.out.println("Enter event location: ");
			String givenEventLocation = input.next();
			Event e = new Event(givenEventTitle, givenEventName, 2023, 10, 17, 11, 59, givenEventLocation);	
			
		case 5: //To be continued..
		case 6:	//To be continued..
		case 7:	//To be continued..
		case 8:	//To be continued..
		}
		
	}
	while(true);
		
	}
}
