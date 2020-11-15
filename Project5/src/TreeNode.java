/**
 * The purpose of TreeNode is to serve as the node 
 * structure for the MorseCodeTree
 * 
 * I swear I have not copied any code
 * @author Nicholas A. Baker
 *
 * @param <T>
 */
public class TreeNode<T> {
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;
	
	/**
	 * Accepts the data as a parameter
	 * @param dataNode
	 */
	public TreeNode(T dataNode) {
		data = dataNode;
		left = null;
		right = null;
	}
	
	/**
	 * Creates this node as a copy of the one 
	 * provided by the user
	 * @param node
	 */
	public TreeNode(TreeNode<T> node) {
		data = node.getData();
		
		/*
		 * Not sure how to approach the left and right since 
		 * we are making a copy of the root node and not the others.
		 */
		left = node.getLeft();
		right = node.getRight();
	}
	
	/**
	 * Returns the data of this node
	 * @return the data contained in this node
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Returns the reference to the left child of this node.
	 * @return the reference to the left child of this node
	 */
	public TreeNode<T> getLeft(){
		return left;
	}
	
	/**
	 * Sets the left child of this node to the one provided by the user
	 * @param node
	 */
	public void setLeft(TreeNode<T> node) {
		left = node;
	}
	
	/**
	 * Returns the reference to the right child of this node.
	 * @return the reference to the right child of this node
	 */
	public TreeNode<T> getRight(){
		return right;
	}

	/**
	 * Sets the right child of this node to the one provided by the user
	 * @param node
	 */
	public void setRight(TreeNode<T> node) {
		right = node;
	}

	public void setData(T newData) {
		data = newData;
		
	}
}
