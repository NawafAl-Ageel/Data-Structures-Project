
public class LinkedList {

	// i made current and previous to make it easier to me for implementing add contact ((so it's by double Linked list))
	private static Node head;
	public static Node current;
	public static Node  Previous;
	
	//is Empty
	public static boolean isEmpty() {
		
		return head==null;
	}
		
	
	//this method is made to add Contacts in the Right place
	//O(n)
	public static boolean addContact(Contact newCon) {
		
		Node newNode = new Node(newCon);
		//first case is to make sure if the List is empty or not 
		if(isEmpty()) {
		head = newNode;
		}
		else {
		//if it's not we start implementing the addContact 
		current=head;
		Previous =null;
		//compare to is to add the Contact in the Right place so When ((When Str.compareTo(str1) is negative this means that we have to add before it 
		while(current!=null&&current.data.compareTo(newCon)<0) {
			if(current.data.compareTo(newCon)==0) {
				//||current.data.getPhoneNumber().equals(newCon.getPhoneNumber())
				System.out.println("the name is Already exist !");
				return false;
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
		return true;
	}
	
	
	
	 //search contacts by name
    public static Contact searchContactByName(String name) {
    	current = head;
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    
    
    
    
    public Contact searchByAnyTerm(String anyTerm) {
    	current = head;
    	while(current!=null) {
    		if(current.data.getPhoneNumber().equals(anyTerm)
    				||current.data.getName().equals(anyTerm)
    				||current.data.getEmailAddress().equals(anyTerm)
    				||current.data.getAddress().equals(anyTerm)
    				||current.data.getBirthday().equals(anyTerm)) {
    			System.out.println("Contact found");
    			return current.data;
    		}
    		current=current.next;
    	}
    	return null;
    	
    }
    
    public boolean deleteContact(String name) {
        Previous = null; // Previous node to keep track of the node before the one to delete.
        current = head; // Start from the first node in the linked list.
        
        
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name)) {
                // Found the contact to delete.
                if (current == head) {
                	System.out.println(head.data.getName());
                	head = head.next;
                	//System.out.println("\n"+head.data+"\n");                	
                    // If the contact to delete is the first node.                   
                } else {
                    // If the contact to delete is not the first node.
                    Previous.next=current.next;
                }
                System.out.println("Contact deleted: " + name);
                return true;
            }

            Previous = current;
            current = current.next;
        }
        return false;
    }
    
    	public String getFirstName(String fullName) {
    		String resultName="";
    		char firstName[] = new char[15]; // we assumed there are no firstnames that can exceed 15 characters
    		char[] fullNameOfChars = fullName.toCharArray();
    		for(int i=0; i<15; i++) {
    			if(fullNameOfChars[i]!=' ') {
    				firstName[i]=fullNameOfChars[i];
    				resultName+=fullNameOfChars[i];
    			}else 
    				return resultName;
    		}
    		return resultName;
    	}
    	
    	public void printContactInfo(Contact con){
    		System.out.println();
    		System.out.println("Contact's Name: "+con.getName());
    		System.out.println("Contact's Phone Number: "+con.getPhoneNumber());
    		System.out.println("Contact's EmailAddress: "+con.getEmailAddress());
    		System.out.println("Contact's Birthday: "+con.getBirthday());
    		System.out.println("Contact's Address: "+con.getAddress());
    		System.out.println("Contact's Notes: "+con.getNotes());   
    		System.out.println();
    	}
        
//        public void printContactsByFirstName(String firstName) {
//        	current = head;
//        	boolean flag = true;
//        	char[] ArrayOfName = firstName.toCharArray();
//        	while(current!=null){
//        		char[] ArrayOfCur = current.data.getName().toCharArray();
//        		for(int i=0; i<15;i++) {
//        			if((i >= ArrayOfName.length ||i>= ArrayOfCur.length ||ArrayOfCur[i]!=ArrayOfName[i])){ 
//        				if(ArrayOfCur[i]!=' ')
//        					flag = true;
//        					break;
//        				}
//        					
//        				flag = false;
//        				break;
//        			}   
//        		}
//        		if(flag)
//        			printContactInfo(current.data);
//        		Previous = current;
//        		current = current.next;		
//        	}
        
        public void printContactsByFirstName(String firstName) {
            current = head;

            while (current != null) {
                char[] ArrayOfName = firstName.toCharArray();
                char[] ArrayOfCur = current.data.getName().toCharArray();
                boolean flag = true;

                // Compare the first name characters
                for (int i = 0; i < ArrayOfName.length; i++) {
                    if (i >= ArrayOfCur.length || ArrayOfCur[i] != ArrayOfName[i]) {
                        flag = false;
                        break;
                    }
                }

                if (flag && (ArrayOfCur.length == ArrayOfName.length || ArrayOfCur[ArrayOfName.length] == ' ')) {
                    printContactInfo(current.data);
                }

                current = current.next;
            }
        }
        
   
	public void PrintAllContacts() {
		 current = head ;
		 if(isEmpty()) {
			 System.out.println("no elements !");
			 return;
		 }
		 else {
			 while(current!=null) {
				 System.out.println(current.data.toString());
				 current=current.next;
			 }
			 System.out.println("Printed!");
		 }
	}
	
	
}
