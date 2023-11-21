import java.time.LocalDateTime;

public class LinkedList{
	public Node head;
		
	public boolean addEvent(Event newEvent) {
		Node newNode = new Node(newEvent);
		Node current=head;
		Node previous=null;
		// first case is to make sure if the List is empty or not
		if (isEmpty()) {
			head = newNode;
		} else {
			// if it's not we start implementing the addContact
			current = head;
			previous = null;
			// compare to is to add the Contact in the Right place so When ((When
			// Str.compareTo(str1) is negative this means that we have to add before it
			while (current != null && current.data.compareTo(newEvent) < 0) {
				if (current.data.compareTo(newEvent) == 0) {
					// ||current.data.getPhoneNumber().equals(newCon.getPhoneNumber())
					System.out.println("the Event is Already exist !");
					return false;
				}
				previous = current;
				current = current.next;
			}
			if(periodConflict(newEvent.getEventTime())) {
				System.out.println("there is a conflict with another event ");
				return false;
			}
			// this happen when we don't Enter the While loop becouse the name is higher in
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
	
	private boolean periodConflict(LocalDateTime newEventTime) {
        Node current = head;
        while (current != null) {
            if (newEventTime.isEqual(current.data.getEventTime())) {
                return true;
            }
            current = current.next;
        }
        return false;
	}
	
	
	public boolean isEmpty() {
		return head == null;
	}
		
	public void delete(Event val) {
		if(head.data==val) {
			head=head.next;
			return;
	}		
		Node current = head;
		Node previous = null;
				
		while(current.next!=null) {
			if(current.data==val) {
				previous.next=current.next;
				return;
			}
			previous=current;
			current=current.next;
		}
		
		if(current.data==val) {
			previous.next=current.next;
			return;
		}		
	}
	
	public void PrintAllEvents() {
		Node current = head;
		if (isEmpty()) {
			System.out.println("no elements !");
			return;
		} else {
			while (current != null) {
				current.data.printEvent();
				current = current.next;
			}
			System.out.println("Printed!");
		}
	}
	
	public Event searchBytitle(String title) {
		Node current = head;
		while (current != null) {
			if (current.data.getTitle().equalsIgnoreCase(title)) {

				return current.data;
			}
			current = current.next;
		}
		return null;
	}	
}
