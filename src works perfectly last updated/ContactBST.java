
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

	public boolean findkey(int tkey) { // inorder
		BSTNode p = root, q = root;

		if (empty())
			return false;

		while (p != null) {
			q = p;
			if (p.key == tkey) {
				current = p;
				return true;
			} else if (tkey < p.key)
				p = p.left;
			else
				p = p.right;
		}

		current = q;
		return false;
	}

	public Contact searchKey(int tkey) { // inorder
		BSTNode p = root, q = root;

		if (empty())
			return null;

		while (p != null) {
			q = p;
			if (p.key == tkey) {
				current = p;
				return p.data;
			} else if (tkey < p.key)
				p = p.left;
			else
				p = p.right;
		}

		current = q;
		return p.data;
	}

	public boolean insertContact(Contact contact) {
		if (insert(contact.contactKey, contact))
			return true;
		return false;
	}

	private boolean insert(int k, Contact val) {
		if (empty()) {
			root = new BSTNode(val.contactKey, val);
		}
		BSTNode p, q = current;

		int key = root.data.compareTo(val);
		val.setContactKey(key);

		if (findkey(key)) {
			current = q; // findkey() modified current
			return false; // key already in the BST
		}

		p = new BSTNode(key, val);
		if (empty()) {
			root = current = p;
			return true;
		} else {
			// current is pointing to parent of the new key
			if (k < current.key)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	public boolean removeContact(Contact contact) {
		if(contact.getEvent() != null && contact.getEvent().isEvent()) {
			contact.getEvent().deleteContactFromEvent(contact.getName());
			if (removeKey(contact.contactKey)) {
				return true;
			}
			return false;
		}
		if(!contact.HasEvent())
			LinkedList.delete(contact.getEvent());
		if (removeKey(contact.contactKey)) {
			return true;
		}
		return false;
	}

	public boolean removeKey(int k) {
		int k1 = k;
		BSTNode p = root;
		BSTNode q = null;
		while (p != null) {
			if (k1 < p.key) {
				q = p;
				p = p.left;
			} else if (k1 > p.key) {
				q = p;
				p = p.right;
			} else {
				if ((p.left != null) && (p.right != null)) {
					BSTNode min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;
				}
				if (p.left != null) {
					p = p.left;
				} else {
					p = p.right;
				}
				if (q == null) {
					root = p;
				} else {
					if (k1 < q.key) {
						q.left = p;
					} else {
						q.right = p;
					}
				}
				current = root;
				return true;
			}
		}
		return false;
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
        return contactContainsTerm(contact, term);
    }

    private static boolean contactContainsTerm(Contact contact, String term) {
        return contact.getName().toLowerCase().contains(term.toLowerCase()) ||
                contact.getPhoneNumber().toLowerCase().contains(term.toLowerCase()) ||
                contact.getEmailAddress().toLowerCase().contains(term.toLowerCase()) ||
                contact.getAddress().toLowerCase().contains(term.toLowerCase()) ||
                contact.getBirthday().toLowerCase().contains(term.toLowerCase()) ||
                contact.getNotes().toLowerCase().contains(term.toLowerCase());
    }

///////////////////////////// chat GPT made ////////////////////////////////////	
	
	public void printContactsByFirstName(String firstName) {
	    printContactsByFirstName(root, firstName);
	}

	private void printContactsByFirstName(BSTNode node, String firstName) {
	    if (node != null) {
	        printContactsByFirstName(node.left, firstName);

	        Contact contact = node.data;
	        if (contact.getName().toLowerCase().startsWith(firstName.toLowerCase())) {
	            contact.printContactInfo();
	        }

	        printContactsByFirstName(node.right, firstName);
	    }
	}
/////////////////// ChatGPT made /////////////////

}