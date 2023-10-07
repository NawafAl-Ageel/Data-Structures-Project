package final1;

public class Contact implements Comparable<Contact>{
	
	
	
	private String name;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String birthday;
    private String notes;
    
    
    
    public NodeE current;
    public NodeE head;
    public NodeE prevoius;
    
 // Constructor
    public Contact(String name, String phoneNumber, String emailAddress, String address, String birthday, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
    }
    
    private boolean isEmpty() {
    	return head==null;
    }
    
    
    public boolean addEvent(Event newEvent) {
    	
		if(!periodConflict(newEvent)) {
			NodeE newE =new NodeE(newEvent);
			
			
			if(isEmpty()) {
				head= newE;
			}
			else {
			
			current = head;
			prevoius=null;
			while(current!=null&&current.data.getTitle().compareTo(newE.data.getTitle())<0) {
				prevoius=current;
				current=current.next;
			}
			if (prevoius == null) {
	             newE.next = head;
	             head = newE;
	         } else {
	             newE.next = current;
	             prevoius.next = newE;
	         }
			}
			return true;
		}
		else {
			return false;
		}
    }
    
    private boolean periodConflict(Event newEvent) {
    	current=head;
    	while(current!=null) {
    		if(current.data.getYear()==newEvent.getYear()
    				&&current.data.getMonth()==newEvent.getMonth()
    				&&current.data.getDayOfMonth()==newEvent.getDayOfMonth()
    				&&current.data.getHourOfDayIn24()==newEvent.getHourOfDayIn24()
    				&&current.data.getMinute()==newEvent.getMinute())
    		return true;
    	}
    	return false;
    }
    
   
	@Override
	public int compareTo(Contact other) {
		return this.name.compareTo(other.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress
				+ ", address=" + address + ", birthday=" + birthday + ", notes=" + notes + "]";
	}
	
	
	
	
	

}
