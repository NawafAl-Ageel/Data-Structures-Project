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
		current = head;
		while (current != null) {
			if (current.data.getPhoneNumber().equalsIgnoreCase(anyTerm)
					|| current.data.getName().equalsIgnoreCase(anyTerm)
					|| current.data.getEmailAddress().equalsIgnoreCase(anyTerm)
					|| current.data.getAddress().equalsIgnoreCase(anyTerm)
					|| current.data.getBirthday().equalsIgnoreCase(anyTerm)) {
				System.out.println("Contact found");
				return current.data;
			}
			current = current.next;
		}
		return null;
	}

	public void printContactsByFirstName(String firstName) {
		current = head;

		while (current != null) {
			String[] firstN = current.data.getName().split(" ");
			if (firstN[0].equalsIgnoreCase(firstName)) {
				current.data.printContactInfo();
			}
			current = current.next;
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
		previous = null; // Previous node to keep track of the node before the one to delete.
		current = head; // Start from the first node in the linked list.

		while (current != null) {
			if (current.data.getName().equalsIgnoreCase(name)) {
				// Found the contact to delete.
				if (current == head) {
					head = head.next;
					if (!current.data.HasEvent()) {
						deleteEvent(current.data.getEvent());
					}
					// System.out.println("\n"+head.data+"\n");
					// If the contact to delete is the first node.
				} else {
					if (!current.data.HasEvent()) {
						deleteEvent(current.data.getEvent());
					}
					// If the contact to delete is not the first node.
					previous.next = current.next;
				}
				System.out.println("Contact deleted: " + name);

				return true;
			}

			previous = current;
			current = current.next;
		}
		return false;
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
		currentE = headE;
		if (isEmptyE()) {
			System.out.println("no elements !");
			return;
		} else {
			while (currentE != null) {
				currentE.data.printEvent();
				currentE = currentE.next;
			}
			System.out.println("Printed!");
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

