package final1;
//Every thing here is not official it's just for clarification 
public class LindkedList {

	// i made current and previous to make it easier to me for implementing add contact
	private Node head;
	
	private Node current;
	private Node  Previous;
	
	//is Empty
	public boolean isEmpty() {
		return head==null;
	}
	
	
	
	
	//this method is made to add Contacts in the Right place
	//O(n)
	public void addContact(Contact newCon) {
		
		Node newNode = new Node(newCon);
		//first case is to make sure if the List is empty or not 
		if(isEmpty()) {
		head = newNode;
		}
		//if it's not we start implementing the addContact 
		current=head;
		 Previous =null;
		//compare to is to add the Contact in the Right place so When ((When Str.compareTo(str1) is negative this means that we have to add before it 
		while(current!=null&&current.data.compareTo(newCon)<0) {
			if(current.data.compareTo(newCon)==0||current.data.getPhoneNumber().equals(newCon.getPhoneNumber())) {
				System.out.println("the name is Already exist !");
			}
			 Previous=current;
			 current=current.next;
		}
		//this happen when we don't Enter the While loop Cuz the name is higher in place ((we should add it before it))
		 if (Previous == null) {
             newNode.next = head;
             head = newNode;
         } else {
             newNode.next = current;
             Previous.next = newNode;
         }
	}

	
	
	public boolean search(String name) {
		Node tmp = head;
		while(tmp.next!=null) {
			if(tmp.data.getName().equalsIgnoreCase(name))
				return true;
			tmp=tmp.next;}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
