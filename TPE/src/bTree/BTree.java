package bTree;

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

		// -- 1 -- 
		preinsert(o);

		return false;
	}

	public boolean insert(String filename) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(Integer o) {
		// TODO Auto-generated method stub
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

	public Integer getMax() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getMin() {
		// TODO Auto-generated method stub
		return null;
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
				}
				skip = false;// here
			}
		}
	}

	// SPECIAL METHODS

	// public for testing
	public boolean preinsert(Integer o){

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
							burstRoot(magnitude, pointer);
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
							}
						}
					}
				}
				// check stored value: o > value[i]
				else if ((pointer.getValue(i)).compareTo(o) == -1){

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
		// testing
		pointer.printnode();
		// testing
		return success;
	}

	private void burstRoot(int magnitude, BTreeNode node){
		
		// split the root: create 2 new nodes and populate
		BTreeNode newRoot = new BTreeNode(magnitude);
		BTreeNode rightChild = new BTreeNode(magnitude);

		// set new root
		newRoot.setValue(node.getValue(magnitude), 0);
		node.setValue(null, magnitude);

		// fill the right node
		for (int i = magnitude+1; i < node.getValues().length; i++){
			rightChild.setValue(node.getValue(i), i-(magnitude+1));
			node.setValue(null, i);
		}

		// set new references
		newRoot.setChild(node, 0);
		newRoot.setChild(rightChild, 1);

		// set newroot as actual new root (pun slighly intended)
		root = newRoot;
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
		BTreeNode newLeaf = new BTreeNode(magnitude);
		
		//Insert elements into the new node and remove from source
		for(int i = magnitude+1; i <= magnitude*2; i++) {
			newLeaf.setValue(brokenLeaf.getValue(i), i-magnitude+1);
			brokenLeaf.setValue(null, i);
		}
		
		//Placing middle-value into parent-node
		Integer mValue = brokenLeaf.getValue(magnitude);
		brokenLeaf.setValue(null, magnitude); // Remove middle-value
		
		int pos = 0;
		for(int i = 0; i <= magnitude*2; i++) {
			if(parent.getValue(i) != null) {
				pos += i++;
			}
		}
		parent.setValue(mValue, pos);
		Integer.insertionSort(parent.getValues());
		
		//Point the reference to the new leaf via its corresponding value
		pos = 0;
		boolean found = false;
		while(pos <= magnitude*2 && !found) {
			if(parent.getValue(pos) != mValue) {
				pos++;
			}
		found = true;
		}
		
		parent.setChild(newLeaf, pos+1);
		
		criteriaCheck(parent);
	}
}
