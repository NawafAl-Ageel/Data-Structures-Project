package lasttimeinshallah;

import java.time.*;

public class Event implements Comparable<Event> {

	private String title;
	private String location;
	private String contactName;
	private String eventDate;

	private LocalDateTime EventTime;
	
	private Contact con;

	// Constructor
	public Event(String title, String contactName, int year, int month, int dayOfMonth, int hour, int minute,
			String location) {

		this.title = title;
		this.location = location;
		
		Contact EventContact =LinkedList.searchByAnyTerm(contactName);
		if(EventContact==null) {
			System.out.println("The Contact doesn't exist !");
			return;
		}
		else if(!EventContact.HasEvent()) {
			System.out.println("The contact has already Scheduled an Event !");
			return;
		}
		con = EventContact;
		

		EventTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
	}

	public void printEvent() {
		System.out.println("Event title: " + title);
		System.out.println("Event is with the contact: " + con.getName());
		System.out.println("Event date: " + getMonth()+"/"+getDay()+"/"+getYear()+" in Time : "+getHour()+":"+getMinute());
		System.out.println("Event location: " + location);
		System.out.println();
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
		return EventTime.getYear();
	}

	public int getMonth() {
		return EventTime.getMonthValue();
	}

	public int getDay() {
		return EventTime.getDayOfMonth();
	}

	public int getHour() {
		return EventTime.getHour();
	}

	public int getMinute() {
		return EventTime.getMinute();
	}

	public int compareTo(Event o) {
		return this.title.compareTo(o.title);
	}

}
