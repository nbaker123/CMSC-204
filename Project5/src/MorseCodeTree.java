/**
 * MorseCodeTree serves as the main structure for
 * MorseCodeConverter, which uses it to convert strings from
 * Morse code to English.
 * 
 * I swear I have not copied any code
 * @author Nicholas A. Baker
 */

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface{

	private TreeNode<String> root = new TreeNode("");
	
	public MorseCodeTree() {
		buildTree();
	}
	
	/**
	 * Returns the reference to the root of this tree.
	 * @return the reference to the root of this tree.
	 */
	@Override
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * Sets the reference of the root to the specified node.
	 * @param newNode: the node which will become the new root.
	 */
	@Override
	public void setRoot(TreeNode newNode) {
		
		//Assuming that the TreeNode contains a String and nothing else
		root = new TreeNode(newNode);
	}

	/**
	 * Attempts to insert a node with the specified data into the tree.
	 * This method attempts to do so by calling the addNode() method.
	 * 
	 * @param
	 * code: the data to be added in Morse code; must be a String.
	 * result: the letter to be added into the tree; must be a String or char.
	 * 
	 *  @return this tree after the insertion
	 */
	@Override
	public LinkedConverterTreeInterface insert(Object code, Object result) {
		
		//Assuming that result is the letter for the code
		addNode(root, code, result);
		return this;
	}

	/**
	 * Inserts a node with the specified data into the tree. The recursive algorithm
	 * involved works as follows:
	 * 
	 * 1. Checks if the current root has nothing in it and places the node in there
	 * if that is true (This is for cases where the code is longer than the actual
	 * path to an empty node, since this ia a public method and people can easily
	 * make that kind of input).
	 * 
	 * 2. Checks if the code string length is less than or equal to 0 and returns
	 * if that is true.
	 *
	 * 3. Checks if the code string length is equal to 1. If it is, then the node will
	 * be stored in the left child of the root if the singular character is a "." or to
	 * the right if it is a "-".
	 * 
	 * 4. Otherwise, the code string has more than one character. If the first character
	 * is a ".", then the left child will be given a new node with "nothing123" 
	 * (indicating nothing in that node), and the method will call 
	 * itself with this left child as the root, the code with everything 
	 * but the first character as the code, and the letter which remains the same. 
	 * If it is a "-", then the same thing happens except it goes to the right child instead.
	 * 
	 * @param
	 * root: The actual root of the tree. During recursion, it is the current node
	 * being checked.
	 * 
	 * code: the letter in Morse code. this is used to move through the tree.
	 * 
	 * letter: The letter being added to the tree.
	 */
	@Override
	public void addNode(TreeNode root, Object code, Object letter) {
		
		//code and letter must be converted to Strings for this to work
		String codeString = (String) code;
		String letterString = (String) letter;
		
		//This algorithm is explained in the javadoc comment
		if(root.getData().equals("nothing123")) root.setData(letterString);
		else {
			if(codeString.length() <= 0) return;
			else if(codeString.length() == 1) {
				if(codeString.charAt(0) == '.') {
					root.setLeft(new TreeNode(letterString));
				}
				else if(codeString.charAt(0) == '-') {
					root.setRight(new TreeNode(letterString));
				}
				else return;
			}
			else {
				if(codeString.charAt(0) == '.') {
					if(root.getLeft() == null) root.setLeft(new TreeNode("nothing123"));
					addNode(root.getLeft(), codeString.substring(1), letter);
				}
				else if(codeString.charAt(0) == '-') {
					if(root.getRight() == null) root.setRight(new TreeNode("nothing123"));
					addNode(root.getRight(), codeString.substring(1), letter);
				}
			}
		}
		
	}

	/**
	 * Returns the element contained by the node in the tree, which
	 * is found using the specified code. Calls the recursive method fetchNode().
	 * 
	 * @param code: A Morse code String that is used to traverse the tree
	 * and find the node that corresponds to it.
	 * 
	 * @return the data within the node found by the specified code.
	 */
	@Override
	public Object fetch(String code) {
		return fetchNode(root, code);
	}

	/**
	 * 
	 */
	@Override
	public Object fetchNode(TreeNode root, Object code) {
		
		//code must be converted to a String and trimmed for this to work well.
		String codeString = ((String) code).trim();
		
		//This algorithm is explained in the javadoc comment
		if(root == null) return "";
		else {
			if(codeString.length() <= 0) return root.getData();
			else if(codeString.length() == 1) {
				if(codeString.charAt(0) == '.') return fetchNode(root.getLeft(), "");
				else if(codeString.charAt(0) == '-') return fetchNode(root.getRight(), "");
				else return "";
			}
			else {
				if(codeString.charAt(0) == '.')
					return fetchNode(root.getLeft(), codeString.substring(1));
				else if(codeString.charAt(0) == '-')
					return fetchNode(root.getRight(), codeString.substring(1));
			}
		}
		return "";
	}

	/**
	 * This operation is not supported in MorseCodeTree
	 * 
	 * @param data
	 */
	@Override
	public LinkedConverterTreeInterface delete(Object data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This operation is not supported for MorseCodeTree");
	}

	/**
	 * This operation is not supported in MorseCodeTree.
	 * @param update
	 */
	@Override
	public LinkedConverterTreeInterface update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This operation is not supported for MorseCodeTree");
	}

	/**
	 * Constructs the tree to be used in MorseCodeConverter
	 */
	@Override
	public void buildTree() {
		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}

	/**
	 * Converts this MorseCodeTree into an ArrayList of strings containing
	 * each String element in the tree in LNR order. Uses the LNROutputTraversal method.
	 * 
	 * @return an ArrayList of strings containing each String element in the tree.
	 */
	@Override
	public ArrayList toArrayList() {
		ArrayList<String> result = new ArrayList<String>();
		LNRoutputTraversal(root, result);
		return result;
	}

	/**
	 * Stores each element from this tree into an ArrayList in LNR order.
	 * The algorithm to do this goes as follows:
	 * 
	 * 1. The method calls itself with the left child of the root as the new root
	 * if the left child of the root is not null.
	 * 
	 * 2. Adds the data from the current root to the list
	 * 
	 * 3. Performs step one again but with he right child.
	 * 
	 * @param
	 * root: The actual root of the tree. During recursion, it is the current node
	 * being checked, and added after the method is called with its left child as the root.
	 * 
	 * list: The ArrayList being used to store the values in LNR order.
	 */
	@Override
	public void LNRoutputTraversal(TreeNode root, ArrayList list) {
		if(root.getLeft() != null) LNRoutputTraversal(root.getLeft(), list);
		list.add(list.size(), root.getData());
		if(root.getRight() != null) LNRoutputTraversal(root.getRight(), list);
	}

}
