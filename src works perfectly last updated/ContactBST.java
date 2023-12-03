
import java.util.*;
public class ContactBST {

	static BSTNode root;
	BSTNode current;

	public ContactBST() {
		root = current = null;

	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public Contact retrieve() {
		return current.data;
	}

	public boolean findkey(int tkey) { //log n
	    BSTNode p = root, q = root;      //1

	    if (empty())                     //1
	        return false;               //1

	    while (p != null) {              //log n
	        q = p;                       //log n
	        if (p.key == tkey) {         //log n
	            current = p;             //log n
	            return true;             //log n
	        } else if (tkey < p.key)     //log n
	            p = p.left;              //log n
	        else
	            p = p.right;             //log n
	    }

	    current = q;                     //1
	    return false;                    //1
	} // Overalllog n

	public Contact searchKey(int tkey) { //log n
	    BSTNode p = root, q = root;          //1

	    if (empty())                          //1
	        return null;                     //1

	    while (p != null) {                   //log n
	        q = p;                            //log n
	        if (p.key == tkey) {              //log n
	            current = p;                  //log n
	            return p.data;                //log n
	        } else if (tkey < p.key)          //log n
	            p = p.left;                   //log n
	        else
	            p = p.right;                  //log n
	    }

	    current = q;                          //1
	    return null;                          //1
	}

	public boolean insertContact(Contact contact) {
		if (insert(contact.contactKey, contact)) // log n
			return true;    //1
		return false;       //1
	}

	private boolean insert(int k, Contact val) {
	    // O(1)
	    if (empty()) {                                 // O(1)
	        root = new BSTNode(val.contactKey, val);   // O(1)
	    }
	    BSTNode p, q = current;                         // O(1)

	    int key = root.data.compareTo(val);             // O(1)
	    val.setContactKey(key);                         // O(1)

	    if (findkey(key)) {                             // O(log n) 
	        current = q;                                // O(1)
	        return false; 							    // O(1)
	    }

	    p = new BSTNode(key, val);                      // O(1)
	    if (empty()) {                                  // O(1)
	        root = current = p;                        // O(1)
	        return true;                               // O(1)
	    } else {
	        if (k < current.key)                        // O(1)
	            current.left = p;                       // O(1)
	        else
	            current.right = p;                      // O(1)
	        current = p;                                // O(1)
	        return true;                               // O(1)
	    }
	} // Overall O(log n)

	public boolean removeContact(Contact contact) {
	    // O(1)
	    if (contact == null) {
	        return false; // O(1)
	    }

	    // O(1)
	    if (contact.getEvent() != null && contact.getEvent().isEvent()) {
	    	
	        contact.getEvent().deleteContactFromEvent(contact.getName()); // O(m)
	        
	        if (removeKey(contact.contactKey)) {  // O(log n)
	            return true; // O(1)
	        }
	        return false; // O(1)
	    }

	    
	    if (!contact.HasEvent()) {       // O(1)

	        LinkedList.delete(contact.getEvent()); // O(m)
	    }

	    if (removeKey(contact.contactKey)) {  // O(log n) 
	        return true; // O(1)
	    }
	    return false; // O(1)
	} // Overall O(m) + O(log n)

	public boolean removeKey(int k) {
	    int k1 = k;                              //1
	    BSTNode p = root;                        //1
	    BSTNode q = null;                        //1
	    while (p != null) {                      //log n
	        if (k1 < p.key) {                    //log n
	            q = p;                          //log n
	            p = p.left;                     //log n
	        } else if (k1 > p.key) {             //log n
	            q = p;                          //log n
	            p = p.right;                    //log n
	        } else {
	            if ((p.left != null) && (p.right != null)) { //log n
	                BSTNode min = p.right;                   //log n
	                q = p;                                 //log n
	                while (min.left != null) {              //log n
	                    q = min;                            //log n
	                    min = min.left;                     //log n
	                }
	                p.key = min.key;                       //log n
	                p.data = min.data;                     //log n
	                k1 = min.key;                          //log n
	                p = min;                               //log n
	            }
	            if (p.left != null) {                      //log n
	                p = p.left;                           //log n
	            } else {
	                p = p.right;                          //log n
	            }
	            if (q == null) {                          //1
	                root = p;                            //1
	            } else {
	                if (k1 < q.key) {                     //1
	                    q.left = p;                      //1
	                } else {
	                    q.right = p;                     //1
	                }
	            }
	            current = root;                          //1
	            return true;                            //1
	        }
	    }
	    return false;                                 //1
	}
///////////////////////////// chat GPT made ////////////////////////////////////	
	public static void setRoot(BSTNode newRoot) {
        root = newRoot;
    }

    public static Contact searchByAnyTerm(String term) {
        return searchByTerm(root, term);
    }

    private static Contact searchByTerm(BSTNode node, String term) {
        if (node == null) {
            return null;
        }

        Contact contact = node.data;
        if (contactMatchesTerm(contact, term)) {
            return contact;
        }

        // Recursively search left and right subtrees
        Contact leftResult = searchByTerm(node.left, term);
        Contact rightResult = searchByTerm(node.right, term);

        // Return the first non-null result
        return leftResult != null ? leftResult : rightResult;
    }

    private static boolean contactMatchesTerm(Contact contact, String term) {
        // Check if any contact property contains the search term
        return contactContainsTerm(contact, term);                  //1
    }

    private static boolean contactContainsTerm(Contact contact, String term) {
        return contact.getName().toLowerCase().equalsIgnoreCase(term.toLowerCase()) ||         //1
                contact.getPhoneNumber().toLowerCase().equalsIgnoreCase(term.toLowerCase()) ||
                contact.getEmailAddress().toLowerCase().equalsIgnoreCase(term.toLowerCase()) ||
                contact.getAddress().toLowerCase().equalsIgnoreCase(term.toLowerCase()) ||
                contact.getBirthday().toLowerCase().equalsIgnoreCase(term.toLowerCase()) ||
                contact.getNotes().toLowerCase().equalsIgnoreCase(term.toLowerCase());
    }

	
	public void printContactsByFirstName(String firstName) {
	    printContactsByFirstName(root, firstName);  // n
	}

	private void printContactsByFirstName(BSTNode node, String firstName) {
	    if (node != null) {         //n
	        printContactsByFirstName(node.left, firstName);         //n
	        Contact contact = node.data;             //1
	        if (contact.getName().toLowerCase().startsWith(firstName.toLowerCase())) { //1
	            contact.printContactInfo();                                            //1
	        }
	        printContactsByFirstName(node.right, firstName);                   //n
	    }
	}


}
