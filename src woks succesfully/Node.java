public class Node{
	public Event data;
	public Node next;
	

	public Node() {
		data =null;
		next =null;
		
	}
	
	public Node(Event data) {
		next=null;
		this.data = data;
	}
	
}
