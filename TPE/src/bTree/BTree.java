package bTree;

import static gdi.MakeItSimple.*;

public class BTree implements BTreeInterface{

	private int magnitude;
	private BTreeNode root;

	// B-TREE SET UP

	public BTree(int magnitude){
		root = null;
		this.magnitude = magnitude;
	}

	public BTree(Integer o, int magnitude){
		root = new BTreeNode(o, magnitude);
		this.magnitude = magnitude;
	}

	// CORE INTERFACE METHODS
	public boolean insert(Integer o){
		int i = 0;
		int child = 0;
		int neighbourLeaf = -1;
		boolean success = false;
		BTreeNode parent = null;
		BTreeNode pointer = root;

		// tree contains elements
		if (pointer != null){

			// search and insert
			while (!success){

				// check stored value: empty
				if (pointer.getValue(i) == null){
					pointer.setValue(o, i);
					Integer.insertionSort(pointer.getValues());

					// check node: healthy
					if (criteriaCheck(pointer)){
						success = true;
					}
					// check node: overload
					else {
						// case: root
						if (parent == null){
							burstRoot();
							success = true;
						}
						// case: leaf
						else {
							// first step: check neighbor leaf
							neighbourLeaf = leafCheck(parent, child);

							// neighbour leaves have free storage: re-arrange values 
							if (neighbourLeaf != -1){
								rebalance(pointer, parent, child, neighbourLeaf);
								success = true;
							}
							// neighbor leaves are full: burst the leaf
							else {
								burstTreeLeaf(magnitude, parent, pointer);
								success = true;
							}
						}
					}
				}
				// check stored value: o > value[i]
				else if ((pointer.getValue(i)).compareTo(o) == -1){
					
					// check stored value: o > value[i+1]
					if (pointer.getValue(i+1) != null){
						i++;
					}
					else {
						// check right child: not present
						if (pointer.getChild(i+1) == null){
							i++;
						}
						// check right child: descend
						else {
							parent = pointer;
							pointer = pointer.getChild(i+1);
							child = i+1;
							i = 0;
						}
					}
				}
				// check stored value: o < value[i]
				else if ((pointer.getValue(i)).compareTo(o) == 1){

					// check left child: not present
					if (pointer.getChild(i) == null){
						i++;
					}
					// check left child: descend
					else {
						parent = pointer;
						pointer = pointer.getChild(i);
						child = i;
						i = 0;
					}
				}
				// check stored value: o == value[i]
				else if ((pointer.getValue(i)).compareTo(o) == 0){
					// we do something here... println?
				}
			}
		}
		// tree is empty
		else {
			pointer = new BTreeNode(o, magnitude);
			root = pointer;
		}	
		return success;
	}

	public boolean insert(String filename) {
		Integer value = new Integer(0);

		// check: file is present & readable
		if (isFilePresent(filename) && (isFileReadable(filename))){
			Object inputFile = openInputFile(filename);

			while (!isEndOfInputFile(inputFile)){
				value = new Integer(readInt(inputFile));

				// attempt to insert value into tree
				if(insert(value) == true)
					println("Inserted: " + Integer.transformInteger(value) + ".");
				else
					println("Failed to insert: " + Integer.transformInteger(value) + ".");

				// check: read-away delimiter characters
				if (!isEndOfInputFile(inputFile)){
					readChar(inputFile);
				}
			}
			closeInputFile(inputFile);
			return true;
		}
		else {
			println("Error: Input file \"" + filename +  "\" is either unreadable or does not exist.");
			return false;
		}
	}

	public boolean contains(Integer o){
		BTreeNode pointer = root;
		
		// pass values for recursion
		return contains(pointer, o);
	}
	private boolean contains(BTreeNode pointer, Integer value){
		return false;
	}

	public int size(){
		
		BTreeNode pointer = root;
		int nodes = 0;
		
		// return total nodes by recursion
		return size(pointer, nodes);
	}
	private int size(BTreeNode pointer, int nodes){
		
		// check: tree contains elements
		if (pointer != null){
			nodes++;
			
			// repeat process: visit each child
			for (int i = 0; i != pointer.getValues().length; i++){
				nodes = size(pointer.getChild(i), nodes);
			}
		}
		return nodes;
	}

	public int height(){
		
		int height = 0;
		int counter = 0;
		BTreeNode pointer = root;
		
		// check: tree contains elements
		if (pointer != null){
			height++;
			while (pointer != null){

				// search the 'biggest' child
				for (int i = 0; i != pointer.getValues().length-1; i++){
					if (size(pointer.getChild(i+1),0) > size(pointer.getChild(i),0)){
						counter = i+1;
					}
				}
				pointer = pointer.getChild(counter);
				counter = 0;
				
				// avoid counting: child == null
				if (pointer != null){
				height++;
				}
			}
			
		}
		return height;
	}

	public Integer getMax(){
		
		int i = 0;
		BTreeNode pointer = root;
		Integer maxValue = new Integer(-1);
		
		// check: node is empty
		while (pointer != null){
			
			// check: find max value
			while (pointer.getValue(i) != null && i != magnitude*2){
				maxValue = pointer.getValue(i);
				i++;
			}
			pointer = pointer.getChild(i);
			i = 0;
		}
		return maxValue;
	}

	public Integer getMin(){

		BTreeNode pointer = root;
		Integer minValue = new Integer(-1);

		// check: node is empty
		while (pointer != null){
			minValue = pointer.getValue(0);
			pointer = pointer.getChild(0);
		}
		return minValue;
	}

	public boolean isEmpty(){
		return root == null;
	}

	public boolean addAll(BTree otherTree) {
		// TODO Auto-generated method stub
		return false;
	}

	/* There is no defined convention for pre-, in- or postorder transversion strategy 
	 * for working with a B-Tree. We define them as following:
	 * 
	 * (1) Preorder
	 * ------------
	 * While using the preorder transversion strategy, we first enter and look up all
	 * of the values contained within the node and then attempt to repeat the process for 
	 * each child of the given node.
	 * 
	 * (2) Inorder
	 * ------------
	 * While using the inorder transversion strategy, we first enter and look up all of the 
	 * values contained within the left-most node and then enter the smallest value in the node 
	 * above (parent). The process is then repeated for each child of the parent node, while we work
	 * our way from the bottom to the top. 
	 * 
	 * (3) Postorder
	 * -------------
	 * While using the postorder transversion strategy, we first attempt to look up and enter
	 * all of the children nodes referenced within the given node before entering the given node itself.
	 */

	public void printPreorder(){

		BTreeNode pointer = root;
		printPreorder(pointer);
	}
	private void printPreorder(BTreeNode pointer){
		if (pointer != null){

			// get data: current node
			Integer[] values = pointer.getValues();
			BTreeNode[] children = pointer.getChildren();

			// first: get all current node values 
			for (int i = 0; i < values.length -1; i++){
				if (values[i] != null)
					System.out.print(Integer.toString(values[i]) + ", ");
			}
			// second: repeat for each child of the node
			for (int i = 0; i < children.length -1; i++){
				printPreorder(pointer.getChild(i));
			}
		}
	}

	public void printInorder(){
		
		BTreeNode pointer = root;
		printInorder(pointer);
	}
	private void printInorder(BTreeNode pointer){
		if (pointer != null){

			// get data: current node
			Integer[] values = pointer.getValues();
			BTreeNode[] children = pointer.getChildren();

			// first: visit left-most node
			printInorder(pointer.getChild(0));

			// second: visit smallest parent node value
			if (values[0] != null){
				System.out.print(Integer.toString(values[0]) + ", ");
			}

			// third: repeat strategy for bigger elements
			for (int i = 1; i < children.length -1; i++){

				// get remaining values of the node
				if (values[i] != null){
					System.out.print(Integer.toString(values[i]) + ", ");
				}
				// get remaining children of the node
				printInorder(pointer.getChild(i));
			}
		}
	}

	public void printPostorder(){

		BTreeNode pointer = root;
		printPostorder(pointer);
	}
	private void printPostorder(BTreeNode pointer){
		if (pointer != null){

			// get data: current node
			Integer[] values = pointer.getValues();
			BTreeNode[] children = pointer.getChildren();

			// first: try to enter all children nodes
			for (int i = 0; i < children.length -1; i++){
				printPostorder(pointer.getChild(i));
			}
			// second: get all current node values
			for (int i = 0; i < values.length -1; i++){
				if (values[i] != null)
					System.out.print(Integer.toString(values[i]) + ", ");
			}
		}
	}

	public void printLevelorder(){
		
		// get data: root & future storage
		BTreeNode pointer = root;
		BTreeNode[] storage = new BTreeNode[size()]; 

		// baton-pass values
		printLevelorder(pointer, storage);
	}
	private void printLevelorder(BTreeNode pointer, BTreeNode[] storage){
		
		int i = 0;
		int j = 0;
		int k = 0;
		Integer target;
		boolean skip = false;

		// add first node: root
		if (pointer != null){	
			storage[0] = pointer;

			// queue functionality
			for (i = 0; i != storage.length; i++){
				for (j = 0; j != magnitude*2; j++){
				
				// for each target-element print...
				if (storage[i].getValue(j) != null){
				target = storage[i].getValue(j);
				System.out.print(Integer.toString(target) + ", ");

				// ... its children are added to the storage.
				if (k != (storage.length)*(magnitude*2)){

					// check: left child of the target	
					if (storage[i].getChild(j) != null && !skip){	
						k++;
						storage[k] = storage[i].getChild(j);
						skip = true;
					}

					// check: target has a right child	
					if (storage[i].getChild(j+1) != null){	
						k++;
						storage[k] = storage[i].getChild(j+1);
					}
				}
					}
				else {
					System.out.print("XX, ");
				}
				}
				skip = false;// here
			}
		}
	}

	// SPECIAL METHODS
	private void burstRoot(){
		
		// split the root: create 2 new nodes and populate
		BTreeNode newRoot = new BTreeNode(magnitude);
		BTreeNode rightChild = splitNode(root);

		// set new root
		newRoot.setValue(root.getValue(magnitude), 0);
		root.setValue(null, magnitude);

		// set new references
		newRoot.setChild(root, 0);
		newRoot.setChild(rightChild, 1);

		// set newroot as actual new root (pun slighly intended)
		root = newRoot;
		
		//Update child's references to grandchildren
		for(int i = magnitude+1; i <= (magnitude*2)+1; i++) {
			rightChild.setChild(root.getChild(0).getChild(i), i-(magnitude+1));
			root.getChild(0).setChild(null, i);
		}
	}


	private boolean criteriaCheck(BTreeNode pointer){
		Integer [] values = pointer.getValues();

		// check last array element
		if (values[values.length-1] == null)
			return true;
		return false;
	}

	private int leafCheck(BTreeNode parent, int child){

		int i = child-1;
		BTreeNode[] children = parent.getChildren();

		while (i >= 0){
			// get values: current child
			Integer[] values = children[i].getValues();
			// check: neighbor leaf can take further elements
			if (values[values.length-1] == null && values[values.length-2] == null){
				return i;
			}
			i--;
		}
		return -1;
	}
	
	private void rebalance(BTreeNode pointer, BTreeNode parent, int child, int neighbourLeaf){
		
		for (int i = neighbourLeaf; i < child; i++){

			boolean set = false;
			Integer parentValue = parent.getValue(i);
			Integer childValue = parent.getChild(i+1).getValue(0);
			
			// set values to 'null' before swapping
			parent.setValue(null, i);
			parent.getChild(i+1).setValue(null, 0);

			for (int j = 0; j < pointer.getValues().length-1 && !set; j++){
				if (parent.getChild(i).getValue(j) == null){

					// search and set position for parent value (from the top to the left) 
					parent.getChild(i).setValue(parentValue, j);
					set = true;
				}
			}
			// place child value inside parent node (from the left to the top)
			parent.setValue(childValue, i);
			
			// sort away: first 'null' value
			for (int k = 0; k < pointer.getValues().length-1; k++){
				Integer x = parent.getChild(i+1).getValue(k);
				Integer y = parent.getChild(i+1).getValue(k+1);
				parent.getChild(i+1).setValue(y, k);
				parent.getChild(i+1).setValue(x, k+1);
			}
		}
	}
	
private void burstTreeLeaf(int magnitude, BTreeNode parent, BTreeNode brokenLeaf) {
		
	//Arrange a new node for all elements > magnitude
	BTreeNode newLeaf = splitNode(brokenLeaf);
	
	//Placing middle-value into parent-node
	Integer mValue = brokenLeaf.getValue(magnitude);
	brokenLeaf.setValue(null, magnitude); // Remove middle-value
	
	//Insert middle value into parent
	popValue(parent, newLeaf, mValue);		
		
		while(!criteriaCheck(parent)) {
			
			if(!criteriaCheck(root)){
				burstRoot();
			}
			else {
			// Split the parent
			newLeaf = splitNode(brokenLeaf);
			
			//Get the middle value
			mValue = parent.getValue(magnitude);
			parent.setValue(null, magnitude); // Remove middle-value
			
			//Locate and set the new parent node
			parent = locateParentNode(mValue);
			
			//Insert middle value into parent
			int childPointer = popValue(parent, newLeaf, mValue);
			
			//Update child's references to grandchildren
			for(int i = magnitude; i <= (magnitude*2)+1; i++) {
				newLeaf.setChild(parent.getChild(childPointer).getChild(i), i-magnitude);
				parent.getChild(childPointer).setChild(null, i);
			}
		}
		}
	}
/**THE FOLLOWING METHODS ASSIST ALL BURST METHODS*/
public BTreeNode locateParentNode(Integer query) {
	//Enter the tree
	BTreeNode newParent = root;
	
	int result = 0;
	int i = 0;
	boolean found = false;
	boolean nullNext = false;
	do {
		result = query.compareTo(newParent.getValue(i));
		
		if (newParent.getValue(i+1) == null) {
			nullNext = true;
		}
			
		if(result == -1) {
			for(int j = 0; j < magnitude*2; j++) {
				if(query.compareTo(newParent.getChild(i).getValue(j)) == 0);
				found = true;
			}
			if(!found)
				newParent = newParent.getChild(i);
			i = 0;
		}
		
		else if(result == 1 && nullNext) {
			for(int j = 0; j < magnitude*2; j++) {
				if(query.compareTo(newParent.getChild(i+1).getValue(j)) == 0);
				found = true;
			}
			if(!found)
				newParent = newParent.getChild(i+1);
			i = 0;
		}

		else if(!nullNext) {
		i++;
		}
	}
	while(i < magnitude*2 && !found); //Value we seek can never be in a temporary node.
	
	return newParent;
}

private BTreeNode splitNode(BTreeNode brokenLeaf) {
	BTreeNode newLeaf = new BTreeNode(magnitude);
	
	for(int i = magnitude+1; i <= magnitude*2; i++) {
		newLeaf.setValue(brokenLeaf.getValue(i), i-(magnitude+1));
		brokenLeaf.setValue(null, i);
	}
	return newLeaf;
}

private int popValue(BTreeNode parent, BTreeNode newLeaf, Integer mValue) {
	int pos = 0;
	while(parent.getValue(pos) != null) {
			pos++;
	}
	parent.setValue(mValue, pos);
	Integer.insertionSort(parent.getValues());
	
	//Point the reference to the new leaf via its corresponding value
	pos = 0;
	boolean found = false;
	while(!found) {
		if(parent.getValue(pos) != mValue)
			pos++;
		else 
			found = true;
	}
	
	int pointer = pos+1;
	
	// Check that reference-field is not already occupied
	if(parent.getChild(pointer) != null) {
		while (parent.getChild(pointer) != null) {
			pointer++;
		}
		
		while(pointer > pos+1) {
			BTreeNode a = parent.getChild(pointer);
			BTreeNode b = parent.getChild(pointer-1);
			parent.setChild(a, pointer-1);
			parent.setChild(b,pointer);
			pointer--;
		}
		
	}
	parent.setChild(newLeaf, pointer);
	return pointer;
}
}
