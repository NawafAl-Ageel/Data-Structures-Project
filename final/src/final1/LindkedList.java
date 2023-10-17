package lasttimeinshallah;

public class LinkedList {

	public static Node<Contact> head;
	public static Node<Contact> current;
	public static Node<Contact> previous;

	public static Node<Event> headE;
	public static Node<Event> currentE;
	public static Node<Event> previousE;

	public boolean isEmpty() {
		return head == null;
	}

	public boolean isEmptyE() {
		return headE == null;
	}
	private boolean periodConflict(LocalDateTime newEventTime) { 
        	Node<Event> currentE = headE; //1
        	while (currentE != null) { //n+1
            		if (newEventTime.isEqual(currentE.data.getEventTime())) { //n
                return true; //1
            }
            currentE = currentE.next; //n
        }
        return false; //1
	}
	public boolean addContact(Contact newCon) {
		Node newNode = new Node(newCon);
		// first case is to make sure if the List is empty or not
		if (isEmpty()) {
			head = newNode;
		} else {
			// if it's not we start implementing the addContact
			current = head;
			previous = null;
			// compare to is to add the Contact in the Right place so When ((When
			// Str.compareTo(str1) is negative this means that we have to add before it
			while (current != null && current.data.compareTo(newCon) < 0) {
				if (current.data.compareTo(newCon) == 0) {
					// ||current.data.getPhoneNumber().equals(newCon.getPhoneNumber())
					System.out.println("the name is Already exist !");
					return false;
				}
				previous = current;
				current = current.next;
			}
			// this happen when we don't Enter the While loop Cuz the name is higher in
			// place ((we should add it before it))
			if (previous == null) {
				newNode.next = head;
				head = newNode;
			} else {
				newNode.next = current;
				previous.next = newNode;
			}
		}
		return true;
	}

	public static Contact searchByAnyTerm(String anyTerm) {
		current = head; //1
		while (current != null) { //n+1
			if (current.data.getPhoneNumber().equalsIgnoreCase(anyTerm) 
					|| current.data.getName().equalsIgnoreCase(anyTerm) 
					|| current.data.getEmailAddress().equalsIgnoreCase(anyTerm)
					|| current.data.getAddress().equalsIgnoreCase(anyTerm)
					|| current.data.getBirthday().equalsIgnoreCase(anyTerm)) { //n
				System.out.println("Contact found"); //1
				return current.data; //1
			}
			current = current.next; //n
		}
		return null; //1
	}

	public void printContactsByFirstName(String firstName) {
		current = head; //1

		while (current != null) { //n+1
			String[] firstN = current.data.getName().split(" "); //n
			if (firstN[0].equalsIgnoreCase(firstName)) { //n
				current.data.printContactInfo(); //n
			} //n
			current = current.next; //n
		}
	}

	public boolean addEvent(Event newEvent) {
		Node newNode = new Node(newEvent);
		// first case is to make sure if the List is empty or not
		if (isEmptyE()) {
			headE = newNode;
		} else {
			// if it's not we start implementing the addContact
			currentE = headE;
			previousE = null;
			// compare to is to add the Contact in the Right place so When ((When
			// Str.compareTo(str1) is negative this means that we have to add before it
			while (currentE != null && currentE.data.compareTo(newEvent) < 0) {
				if (currentE.data.compareTo(newEvent) == 0) {
					// ||current.data.getPhoneNumber().equals(newCon.getPhoneNumber())
					System.out.println("the Event is Already exist !");
					return false;
				}
				previousE = currentE;
				currentE = currentE.next;
			}
			// this happen when we don't Enter the While loop becouse the name is higher in
			// place ((we should add it before it))
			if (previousE == null) {
				newNode.next = headE;
				headE = newNode;
			} else {
				newNode.next = currentE;
				previousE.next = newNode;
			}
		}
		return true;
	}

	public boolean deleteContact(String name) {
		previous = null; // Previous node to keep track of the node before the one to delete. /1
		current = head; // Start from the first node in the linked list. /1

		while (current != null) { //n+1
			if (current.data.getName().equalsIgnoreCase(name)) { //n
				// Found the contact to delete.
				if (current == head) { //n
					head = head.next; //n
					if (!current.data.HasEvent()) { //n
						deleteEvent(current.data.getEvent()); //n
					}
					// System.out.println("\n"+head.data+"\n");
					// If the contact to delete is the first node.
				} else { //n
					if (!current.data.HasEvent())  { //n
						deleteEvent(current.data.getEvent()); //n
					}
					// If the contact to delete is not the first node.
					previous.next = current.next;//n
				}
				System.out.println("Contact deleted: " + name); //n

				return true; //1
			}

			previous = current; //n
			current = current.next; //n
		}
		return false; //1
	}

	private void deleteEvent(Event del) { 
		previousE = null; // Previous node to keep track of the node before the one to delete.
		currentE = headE; // Start from the first node in the linked list.

		while (currentE != null) {
			if (currentE.data == del) {
				// Found the contact to delete.
				if (currentE == headE) {
					headE = headE.next;
					// System.out.println("\n"+head.data+"\n");
					// If the contact to delete is the first node.
				} else {
					// If the contact to delete is not the first node.
					previousE.next = currentE.next;
				}
			}
			previousE = currentE;
			currentE = currentE.next;
		}

	}

	public void PrintAllEvents() {
		currentE = headE; //1
		if (isEmptyE()) { //1
			System.out.println("no elements !"); //1
			return; //1
		} else { //1
			while (currentE != null) { //n+1
				currentE.data.printEvent(); //n
				currentE = currentE.next; //n
			}
			System.out.println("Printed!"); //1
		}
	}

	public static Event searchBytitle(String title) {
		currentE = headE;
		while (currentE != null) {
			if (currentE.data.getTitle().equalsIgnoreCase(title)) {

				return currentE.data;
			}
			currentE = currentE.next;
		}
		return null;
	}

}

