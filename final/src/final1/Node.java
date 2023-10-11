package lasttimeinshallah;

public class Node<t> {
	
	public t data;
	public Node<t> next;
	

	public Node() {
		data =null;
		next =null;
		
	}
	
	public Node(t data) {
		next=null;
		this.data = data;
	}
	
}
