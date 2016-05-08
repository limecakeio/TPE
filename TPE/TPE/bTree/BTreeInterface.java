package bTree;

public interface BTreeInterface {
	
	/** Adds an Integer value to the B-Tree.
	 * @param Object - A value of Integer type.
	 * @param o - The name of the Integer variable.
	 * @return -  Returns 'true' if the Integer value o has been successfully added.  
	 */
	boolean insert(Integer o);
	
	/** Adds a file of Integer values to the B-Tree.
	 * @param String - The full name of the file as a String.
	 * @param filename - The name of the file containing the Integer values.
	 * @return -  Returns 'true' if the Integer values have been successfully added.  
	 */
	boolean insert(String filename);
	
	/** Removes the Integer value o from the B-Tree.
	 * @param Object - A value of Integer type.
	 * @param o - The name of the Integer variable.
	 * @return -  Returns 'true' if the Integer value o has been successfully removed.  
	 */
	boolean delete(Integer o);
	
	/** Checks wether a specific Integer value is contained within the B-Tree.
	 * @param Object - A value of Integer type.
	 * @param o - The name of the Integer variable.
	 * @return - Returns 'true' if Integer o has been found withing the B-Tree.
	 */
	boolean contains(Integer o);
	
	/** Retuns the size of the B-Tree as an int value.
	 * @return - The size of the B-Tree as an int value.
	 */
	int size();
	
	/** Returns the height of the B-Tree as an int value.
	 * @return The height of the B-Tree as an int value.
	 */
	int height();
	
	/** Returns the max Integer value contained within the B-Tree.
	 * @return The max Integer value contained within the B-Tree.
	 */
	Integer getMax();
	
	/** Returns the min Integer value contained within the B-Tree.
	 * @return The min Integer value contained within the B-Tree.
	 */
	Integer getMin();
	
	/** Returns 'true' if the B-Tree contains no elements.
	 * @return Returns 'true' if the B-Tree contains no elements.
	 */
	boolean isEmpty();
	
	/** Adds all Integer values from 'otherTree' to the current B-Tree.
	 * @param otherTree - The name of the B-Tree to be added from.
	 */
	void addAll(BTree otherTree);
	
	/** Prints all Integer values contained within the B-Tree by using the preorder transversion tactic. 
	 */
	void printPreorder();
	
	/** Prints all Integer values contained within the B-Tree by using the inorder transversion tactic. 
	 */
	void printInorder();
	
	/** Prints all Integer values contained within the B-Tree by using the postorder transversion tactic. 
	 */
	void printPostorder();
	
	/** Prints all Integer values contained within the B-Tree by using the levelorder transversion tactic. 
	 */
	void printLevelorder();
}
