package bTree;

public interface BTreeInterface {
	
	/** Adds an Object value to the B-Tree.
	 * @param Object - A value of Object type.
	 * @param o - The name of the Object variable.
	 * @return -  Returns 'true' if the Object value o has been successfully added.  
	 */
	boolean insert(Object o);
	
	/** Adds a file of Object values to the B-Tree.
	 * @param String - The full name of the file as a String.
	 * @param filename - The name of the file containing the Object values.
	 * @return -  Returns 'true' if the Object values have been successfully added.  
	 */
	boolean insert(String filename);
	
	/** Removes the Object value o from the B-Tree.
	 * @param Object - A value of Object type.
	 * @param o - The name of the Object variable.
	 * @return -  Returns 'true' if the Object value o has been successfully removed.  
	 */
	boolean delete(Object o);
	
	/** Checks wether a specific Object value is contained within the B-Tree.
	 * @param Object - A value of Object type.
	 * @param o - The name of the Object variable.
	 * @return - Returns 'true' if Object o has been found withing the B-Tree.
	 */
	boolean contains(Object o);
	
	/** Retuns the size of the B-Tree as an int value.
	 * @return - The size of the B-Tree as an int value.
	 */
	int size();
	
	/** Returns the height of the B-Tree as an int value.
	 * @return The height of the B-Tree as an int value.
	 */
	int height();
	
	/** Returns the max Object value contained within the B-Tree.
	 * @return The max Object value contained within the B-Tree.
	 */
	Object getMax();
	
	/** Returns the min Object value contained within the B-Tree.
	 * @return The min Object value contained within the B-Tree.
	 */
	Object getMin();
	
	/** Returns 'true' if the B-Tree contains no elements.
	 * @return Returns 'true' if the B-Tree contains no elements.
	 */
	boolean isEmpty();
	
	/** Adds all Object values from 'otherTree' to the current B-Tree.
	 * @param otherTree - The name of the B-Tree to be added from.
	 */
	void addAll(BTree otherTree);
	
	/** Prints all Object values contained within the B-Tree by using the preorder transversion tactic. 
	 */
	void printPreorder();
	
	/** Prints all Object values contained within the B-Tree by using the inorder transversion tactic. 
	 */
	void printInorder();
	
	/** Prints all Object values contained within the B-Tree by using the postorder transversion tactic. 
	 */
	void printPostorder();
	
	/** Prints all Object values contained within the B-Tree by using the levelorder transversion tactic. 
	 */
	void printLevelorder();
}
