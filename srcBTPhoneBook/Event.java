
import java.time.LocalDateTime;

public class Event implements Comparable<Event> {

	private String title;
	private String location;
	private String contactName;
	private String eventDate;

	private LocalDateTime eventTime;
	
	private Contact con;

	// Constructor
	public Event(String title, String contactName, int year, int month, int dayOfMonth, int hour, int minute,
			String location) {

		this.title = title;
		this.location = location;
		Contact EventContact =ContactBST.searchByAnyTerm(contactName);
		if(EventContact==null) {
			System.out.println("The Contact doesn't exist !");
			return;
		}
		else if(!EventContact.HasEvent()) {
			System.out.println("The contact has already Scheduled an Event !");
			return;
		}
		this.contactName= EventContact.getName();
		con = EventContact;
		eventTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
	}
	
	
	public void printEvent() {
		System.out.println("Event title: " + title);
		System.out.println("Event is with the contact: " + con.getName());
		System.out.println("Event date: " + getMonth()+"/"+getDay()+"/"+getYear()+" in Time : "+getHour()+":"+getMinute());
		System.out.println("Event location: " + location);
		System.out.println();
	}
	
	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}

	public String getContactName() {
		return contactName;
	}

	public int getYear() {
		return eventTime.getYear();
	}

	public int getMonth() {
		return eventTime.getMonthValue();
	}

	public int getDay() {
		return eventTime.getDayOfMonth();
	}

	public int getHour() {
		return eventTime.getHour();
	}

	public int getMinute() {
		return eventTime.getMinute();
	}

	public int compareTo(Event o) {
		return this.title.toLowerCase().compareTo(o.title.toLowerCase());
	}
}