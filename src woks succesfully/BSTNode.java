public class BSTNode {

	public int key;
	public Contact data;
	public BSTNode left;
	public BSTNode right;

	public BSTNode(int k, Contact val) {
		key = k;
		data = val;
		left = right = null;
	}

	public BSTNode(int key, Contact val, BSTNode left, BSTNode right) {
		this.key = key;
		this.data = val;
		this.left = left;
		this.right = right;
	}
	
	public int getKey() {
		return key;
	}
}
