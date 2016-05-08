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

	public BTree(Object o, int magnitude){
		root = new BTreeNode(o, magnitude);
		this.magnitude = magnitude;
	}

	// CORE INTERFACE METHODS
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insert(Object o){
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
					insertionSort(pointer.getValues());

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
				else if (((Comparable) pointer.getValue(i)).compareTo(o) == -1){

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
				else if (((Comparable) pointer.getValue(i)).compareTo(o) == 1){

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
				else if (((Comparable) pointer.getValue(i)).compareTo(o) == 0){
					println("Error: Value already present.");
					return false;
				}
			}
		}
		// tree is empty
		else {
			pointer = new BTreeNode(o, magnitude);
			root = pointer;
			success = true;
		}
		return success;
	}

	public boolean insert(String filename) {return true;}
//	public boolean insert(String filename) {
//		
//		Object value = new Object();
//		
//		// check: file is present & readable
//		if (isFilePresent(filename) && (isFileReadable(filename))){
//			Object inputFile = openInputFile(filename);
//
//			while (!isEndOfInputFile(inputFile)){
//				value = new Object(readInt(inputFile));
//
//				// attempt to insert value into tree
//				if (insert(value) == false){
//					println("Failed to insert: " + Integer.transformInteger(value) + ".");
//				}
//				// check: read-away delimiter characters
//				if (!isEndOfInputFile(inputFile)){
//					readChar(inputFile);
//				}
//			}
//			closeInputFile(inputFile);
//			return true;
//		}
//		else {
//			println("Error: Input file \"" + filename +  "\" is either unreadable or does not exist.");
//			return false;
//		}
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean contains(Object o){
		
		BTreeNode parent = root;
		
		int result = 0;
		int i = 0;
		int validRange = 0;
		boolean found = false;
		boolean end = false;
		
		do {
			boolean nullNext = false;
			result = ((Comparable)o).compareTo(parent.getValue(i));
			
			if (parent.getValue(i+1) == null) {
				nullNext = true;
			}
			
			if(result == 0)
				found = true;
			
			else if(result == -1) {
				validRange = BTree.establishRange(parent.getChild(i).getValues());
				for(int j = 0; j < validRange; j++) {
					if(((Comparable)o).compareTo(parent.getChild(i).getValue(j)) == 0) {
					found = true;
				}
				}
				if(!found)
					parent = parent.getChild(i);
				i = 0;
			}

			else if(result == 1 && nullNext) {
				validRange = BTree.establishRange(parent.getChild(i+1).getValues());
				for(int j = 0; j < validRange; j++) {
					if(((Comparable)o).compareTo(parent.getChild(i+1).getValue(j)) == 0) {
					found = true;
					}
				}
				
				if(!found)
					parent = parent.getChild(i+1);
				i = 0;
			}

			else if(!nullNext) {
				i++;
			}
			
			if(parent.getChild(0) == null) //If we reached a leaf, element is not in tree.
				end = true;
		}
		while(i < magnitude*2 && !found && !end);
		
		return found;
	}
	
	public int size(){

		BTreeNode pointer = root;
		BTreeNode[] storage = new BTreeNode[nodeCount()];
		return size(pointer, storage);
	}
	private int size(BTreeNode pointer, BTreeNode[] storage){

		int i = 0;
		int j = 0;
		int k = 0;
		int size = 0;
		boolean skip = false;

		// add first node: root
		if (pointer != null){	
			storage[0] = pointer;

			// queue functionality
			for (i = 0; i != storage.length; i++){
				for (j = 0; j != magnitude*2; j++){

					// for each target-element print...
					if (storage[i].getValue(j) != null){
						storage[i].getValue(j);
						size++;

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
		return size;
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
					if (nodeCount(pointer.getChild(i+1),0) > nodeCount(pointer.getChild(i),0)){
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

	public Object getMax(){

		int i = 0;
		BTreeNode pointer = root;
		Object maxValue = new Integer(-1);

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

	public Object getMin(){

		BTreeNode pointer = root;
		Object minValue = new Integer(-1);

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

	public void addAll(BTree otherTree) {
	BTreeNode pointer = otherTree.root;
	addAll(pointer);
	}
	
	public void addAll(BTreeNode pointer) {
		if (pointer != null){

			// get data: current node
			Object[] values = pointer.getValues();
			BTreeNode[] children = pointer.getChildren();

			// first: get all current node values 
			for (int i = 0; i < values.length -1; i++){
				if (values[i] != null) 
					insert(values[i]);
			}
			// second: repeat for each child of the node
			for (int i = 0; i < children.length -1; i++){
				addAll(pointer.getChild(i));
			}
		}
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
			Object[] values = pointer.getValues();
			BTreeNode[] children = pointer.getChildren();

			// first: get all current node values 
			for (int i = 0; i < values.length -1; i++){
				if (values[i] != null)
					System.out.print((values[i]).toString() + ", ");
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
			Object[] values = pointer.getValues();
			BTreeNode[] children = pointer.getChildren();

			// first: visit left-most node
			printInorder(pointer.getChild(0));

			// second: visit smallest parent node value
			if (values[0] != null){
				print((values[0]).toString() + ", ");
			}

			// third: repeat strategy for bigger elements
			for (int i = 1; i < children.length -1; i++){

				// get remaining values of the node
				if (values[i] != null){
					print((values[i]).toString() + ", ");
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
			Object[] values = pointer.getValues();
			BTreeNode[] children = pointer.getChildren();

			// first: try to enter all children nodes
			for (int i = 0; i < children.length -1; i++){
				printPostorder(pointer.getChild(i));
			}
			// second: get all current node values
			for (int i = 0; i < values.length -1; i++){
				if (values[i] != null)
					System.out.print((values[i]).toString() + ", ");
			}
		}
	}

	public void printLevelorder(){

		// get data: root & future storage
		BTreeNode pointer = root;
		BTreeNode[] storage = new BTreeNode[nodeCount()]; 

		// baton-pass values
		printLevelorder(pointer, storage);
	}
	private void printLevelorder(BTreeNode pointer, BTreeNode[] storage){

		int i = 0;
		int j = 0;
		int k = 0;
		Object target;
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
						System.out.print((target).toString() + ", ");

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
	private int nodeCount(){

		BTreeNode pointer = root;
		int nodes = 0;

		// return total nodes by recursion
		return nodeCount(pointer, nodes);
	}
	private int nodeCount(BTreeNode pointer, int nodes){

		// check: tree contains elements
		if (pointer != null){
			nodes++;

			// repeat process: visit each child
			for (int i = 0; i != pointer.getValues().length; i++){
				nodes = nodeCount(pointer.getChild(i), nodes);
			}
		}
		return nodes;
	}
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
		root = newRoot;

		//Update child's references to grandchildren
		for(int i = magnitude+1; i <= (magnitude*2)+1; i++) {
			rightChild.setChild(root.getChild(0).getChild(i), i-(magnitude+1));
			root.getChild(0).setChild(null, i);
		}
	}


	private boolean criteriaCheck(BTreeNode pointer){
		Object [] values = pointer.getValues();

		// check last array element
		if (values[values.length-1] == null)
			return true;
		return false;
	}

	private int leafCheck(BTreeNode parent, int child){

		int i = child-1;
		BTreeNode[] children = parent.getChildren();

		// check: left sister leaves
		while (i >= 0){
			// get values: current child
			Object[] values = children[i].getValues();
			// check: neighbor leaf can take further elements
			if (values[values.length-1] == null && values[values.length-2] == null){
				return i;
			}
			i--;
		}

		i = child+1;

		// check: right sister leaves
		while (i != magnitude*2 && children[i] != null){
			// get values: current child
			Object[] values = children[i].getValues();
			// check: neighbor leaf can take further elements
			if (values[values.length-1] == null && values[values.length-2] == null){
				return i;
			}
			i++;
		}
		return -1;
	}

	private void rebalance(BTreeNode pointer, BTreeNode parent, int child, int neighbourLeaf){

		// check: rabalance to the left
		if (neighbourLeaf < child){
			
			for (int i = neighbourLeaf; i < child; i++){

				boolean set = false;
				Object parentValue = parent.getValue(i);
				Object childValue = parent.getChild(i+1).getValue(0);

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
					Object x = parent.getChild(i+1).getValue(k);
					Object y = parent.getChild(i+1).getValue(k+1);
					parent.getChild(i+1).setValue(y, k);
					parent.getChild(i+1).setValue(x, k+1);
				}
			}
		}
		else {

			for (int i = neighbourLeaf; i > child; i--){

				boolean set = false;
				Object parentValue = parent.getValue(i-1);
				Object childValue = parent.getChild(i-1).getValue((magnitude*2));

				// set values to 'null' before swapping
				parent.setValue(null, i-1);
				parent.getChild(i-1).setValue(null, (magnitude*2));

				for (int j = 0; j < pointer.getValues().length-1 && !set; j++){
					if (parent.getChild(i).getValue(j) == null){

						// search and set position for parent value (from the top to the left) 
						parent.getChild(i).setValue(parentValue, j);
						insertionSort(parent.getChild(i).getValues());
						set = true;
					}
				}
				// place child value inside parent node (from the left to the top)
				parent.setValue(childValue, i-1);
			}
		}
	}

	private void burstTreeLeaf(int magnitude, BTreeNode parent, BTreeNode brokenLeaf) {

		//Arrange a new node for all elements > magnitude
		BTreeNode newLeaf = splitNode(brokenLeaf);

		//Placing middle-value into parent-node
		Object mValue = brokenLeaf.getValue(magnitude);

		brokenLeaf.setValue(null, magnitude); // Remove middle-value

		//Insert middle value into parent
		popValue(parent, newLeaf, mValue);		

		while(!criteriaCheck(parent)) {

			if(!criteriaCheck(root)){
				burstRoot();
			}
			else {
				// Split the parent
				newLeaf = splitNode(parent);

				//Get the middle value
				mValue = parent.getValue(magnitude);

				//Locate and set the new parent node
				parent = locateParentNode(mValue);

				//Insert middle value into parent
				int childPointer = popValue(parent, newLeaf, mValue);

				//Update child's references to grandchildren
				for (int i = magnitude+1; i <= (magnitude*2)+1; i++) {
					newLeaf.setChild(parent.getChild(childPointer).getChild(i), i-(magnitude+1));
					parent.getChild(childPointer).setChild(null, i);
				}
			}
		}
	}
	
	/**THE FOLLOWING METHODS ASSIST ALL BURST METHODS*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BTreeNode locateParentNode(Object query) {
		//Enter the tree
		BTreeNode newParent = root;

		int result = 0;
		int validRange = 0;
		int i = 0;
		boolean found = false;
	
		do {
			boolean nullNext = false;
			result = ((Comparable)query).compareTo(newParent.getValue(i));

			if (newParent.getValue(i+1) == null) {
				nullNext = true;
			}

			if(result == -1) {
				validRange = BTree.establishRange(newParent.getChild(i+1).getValues());
				for(int j = 0; j < validRange; j++) {
					if(((Comparable)query).compareTo(newParent.getChild(i).getValue(j)) == 0) {
						newParent.getChild(i).setValue(null, j); // Remove located value
						found = true;
					}
				}
				if(!found)
					newParent = newParent.getChild(i);
				i = 0;
			}

			else if(result == 1 && nullNext) {
				validRange = BTree.establishRange(newParent.getChild(i+1).getValues());
				for(int j = 0; j < validRange; j++) {
					if(((Comparable)query).compareTo(newParent.getChild(i+1).getValue(j)) == 0) {
						newParent.getChild(i+1).setValue(null, j); // Remove located value
						found = true;
					}
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

	private int popValue(BTreeNode parent, BTreeNode newLeaf, Object mValue) {
		int pos = 0;
		while(parent.getValue(pos) != null) {
			pos++;
		}
		parent.setValue(mValue, pos);
		insertionSort(parent.getValues());

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
		return pointer-1;
	}
	
	public boolean delete(Object o){
		if(contains(o)) {
			BTreeNode pointer = root;
			if(!delete(pointer, o)) {
				return false;
			}
			return true;
		}
			
		return false;
	}
	private boolean delete(BTreeNode parent, Object o) {
		parent = getParent(o);
		BTreeNode child = getChild(parent, o);
		boolean fixed = false;
		
		erase(child, o);
		
		//Deleted from leaf
		if(child.getChild(0) == null) {
			shiftValues(child.getValues());
			fixed = childrenCheck(parent);
			if(!fixed) {
				restructureTree(parent);
			}
		}

		else if(child == root) {
			treatRoot(root);
		}
		else {
			treatNode(parent);
		}
		return true;
	}
	
	private void restructureTree (BTreeNode parent) {
		
		if(parent == root) {
				//Child is a node
				if(parent.getChild(0).getChild(0) != null) {
					treatNode(parent);
				}
				//Child is a leaf
				else {
					treatLeaf(parent);	
				}	
		}
		//Parent of a node
		else {
			if(parent.getChild(0).getChild(0) != null)
				treatNode(parent);
			//Parent of a leaf
			else
				treatLeaf(parent);
		}
	}
	
	private void treatRoot(BTreeNode parent) {
		println("Treating the root");
		int oPos = freeSpot(root.getValues());
		Object leafVal = leafEquivalentValue(parent, oPos);
		BTreeNode leaf = leafEquivalentNode(parent, oPos);
		
		//Change parent to leaf parent for later processing
		parent = getParent(leafVal);
		
		//Fill the gap with its leaf equivalent
		root.setValue(leafVal, oPos);
		
		//Delete the value from its leaf
		erase(leaf, leafVal);
		shiftValues(leaf.getValues());
		
		//Check the leaf we took from
		if(!minCheck(leaf.getValues()))
			restructureTree(parent);
	}
	
	private void treatNode(BTreeNode parent) {
		println("Treating via a node parent");
		BTreeNode child = parent.getChild(getOffendingNodePosition(parent));
		println("Offending Child was" + getOffendingNodePosition(parent));
		int oPos = freeSpot(child.getValues());
		println("Offending Value position was: " + oPos);
		
		//See if the child is a leaf-parent so it can try to take largest value from smaller child
		if(child.getChild(0).getChild(0) == null) {
			if(abundanceCheck(child.getChild(oPos).getValues())) {
				Object[] grandChildValues = child.getChild(oPos).getValues();
				//Fill the void
				child.setValue(lastElement(grandChildValues), oPos);
				//Remove the duplicate
				erase(child.getChild(oPos), lastElement(grandChildValues));
				if(!childrenCheck(child))
					treatLeaf(child);
				return;
			}
		}
			
		Object leafVal = leafEquivalentValue(child, oPos);
		BTreeNode leaf = leafEquivalentNode(child, oPos);
		
		println("Leaf Value was: " + Integer.transformInteger(leafVal));
		
		//Fill the gap with its leaf equivalent
		child.setValue(leafVal, oPos);
		
		//Delete the value from its leaf
		
		erase(leaf, leafVal);
		shiftValues(leaf.getValues());
		
		//Check the leaf
		if(!minCheck(leaf.getValues())) {
			if(child.getChild(0).getChild(0) != null) {
				parent = child.getChild(oPos+1);
				println("Who is child? " + Integer.transformInteger(child.getValue(0)));
				println("Who is parent after first step? " + Integer.transformInteger(parent.getValue(0)));
			
			while(parent.getChild(0).getChild(0) != null)
				parent = parent.getChild(0);
			}
			else {
			parent = child;
			}
			println("Who is parent? " + Integer.transformInteger(parent.getValue(0)));
			restructureTree(parent);
		}
	}
	
	private int getOffendingPosition(BTreeNode child) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void treatLeaf(BTreeNode parent) {
		println("Treating via leaf parent");
		
		if(!rebalanceLeafs(parent)) {
			println("Failed rebalance.");
			println("We have a childcount of: " +childCount(parent));
			println("And a value count of: " + valueCount(parent.getValues()));
			
			//Attempt to merge the last two children together
			if(childCount(parent) > magnitude+1) {
				println("More kids than parents");
				int largeChild = childCount(parent)-1;
				int smallChild = largeChild-1;
				Object[] sChild, lChild;
				sChild = parent.getChild(smallChild).getValues();
				lChild = parent.getChild(largeChild).getValues();
				int valCount = valueCount(parent.getChild(largeChild).getValues());
				
				//Last child has no values, remove it and push largest parent value into previous leaf
				if(lChild[0] == null) {
					println("Child has no values");
					//Get the parent's largest value
					Object lastElement = lastElement(parent.getValues());
					int elementPos = getElementPosition(parent.getValues(), lastElement);
					parent.getChild(elementPos).setValue(lastElement, freeSpot(parent.getChild(elementPos).getValues()));
					erase(parent, lastElement);
					parent.setChild(null, largeChild);
					return;
				}
				
				//Copy the corresponding parent's value into smaller child and remove it
				sChild[freeSpot(sChild)] = parent.getValue(smallChild);
				erase(parent, parent.getValue(smallChild));
		
				//Copy all the values from the larger child into the smaller one
				for(int i = valCount-1; i >= 0; i--) {
					sChild[freeSpot(sChild)] = lChild[i];
				}
				
				//Get rid of larger child's reference
				parent.setChild(null, largeChild);
				
				//Attempt to rebalance
				rebalanceLeafs(parent);
				return;
			}
			
			//Try to replace with immediate parent
			int cPos = getOffendingChildPosition(parent);
			BTreeNode child = parent.getChild(cPos);
			
			if(parent.getValue(cPos) != null) {
				Object replacementVal = parent.getValue(cPos);
				
				//Place parent value into child
				child.setValue(replacementVal, freeSpot(child.getValues()));
				
				if(parent == root) {
					erase(root, replacementVal);
				}
				//Set new parent a level above previous one
				else {
				parent = getParent(replacementVal);
				deepErase(parent, replacementVal);
				}
				
				if(!childrenCheck(parent))
					restructureTree(parent);
				
				return;
			}
			
			//Leaf is the largest in its branch
			else {
				//Ensure we are not in the tree's last leaf and 
				//replace the value with its corresponding exchange node value
				if(((Comparable) parent.getValue(0)).compareTo(lastElement(root.getValues())) == -1) {
					
					Object eVal;
					//If we have m = 1, child could be empty
					if(child.getValue(0) == null)
						eVal = lastElement(parent.getValues());
					else
						eVal = lastElement(child.getValues());
					
					println("eVal is: " + Integer.transformInteger(eVal));
					
					
					BTreeNode exchangeNode = exchangeEquivalent(eVal);
					
					int ePos = 0;
					
					//Get the position at which we are exchanging
					while(((Comparable) eVal).compareTo(exchangeNode.getValue(ePos)) == 1)
						ePos++;
					
					
					//Take the root's corresponding value and add it to the end of the leaf
					child.setValue(exchangeNode.getValue(ePos), freeSpot(child.getValues()));
					
					//Set the new parent and remove the duplicate value
					parent = getParent(exchangeNode.getValue(ePos));
					println("What is the new parent? " + Integer.transformInteger(parent.getValue(0)));
					erase(exchangeNode, exchangeNode.getValue(ePos));

					restructureTree(parent);
				}
					
				// We are in the tree's largest leaf
				else {
					//See if any of the leafs in the tree can give up a value
					int abundantValue = abundantSearch();
					println("Abundant Value is: " +abundantValue + " Min is now " + Integer.transformInteger(getMin()) + " Max is now " + Integer.transformInteger(getMax()));
					
					if(abundantValue != -1) {
						Object aVal = new Integer(abundantValue);
						rotateToLastLeaf(aVal); 
					}
					else{
						cutBranch();
					}		
				}
			}
			}
		}

	
	/**HELPFUL METHODS TO REALISE DELETE*/
	
	/**Performs a branch-spanning right-rotation to move an abundant value within a leaf into the tree's last leaf*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void rotateToLastLeaf(Object aVal) {
		println("WHEN THE METHOD IS CALLED AVAL IS: " + Integer.transformInteger(aVal));
		BTreeNode parent = getParent(aVal);
		int cAmount = childCount(parent);
		int cPos = getChildPosition(parent, aVal);
		println("Pre rotate we have aVal in the child " + cPos);
		
		
		//Rotate the abundance to a (sub) branch's last leaf 
		rotateRight(parent, cPos, cAmount-1);
		
		//Get the leaf with the abundance for further processing
		BTreeNode child = parent.getChild(cAmount-1);
		
		//Update the abundant value post-rotate
		aVal = lastElement(child.getValues());
		
		//Locate exchange-node to cross branches
		BTreeNode eNode = root;
		boolean found = false;
		boolean path = false;

		int p = 0, pResult = 0;

		while(!found) {
			p = 0;
			path = false;
			
			//Find a path to down
			while(!path) {
				pResult = ((Comparable) aVal).compareTo(eNode.getValue(p));
				println("Comparing [parent] " + Integer.transformInteger(aVal) + " with " + Integer.transformInteger(eNode.getValue(p)) + " resulting in " + pResult);
				
				if(eNode.getValue(p) == null)
					path = true; //End of array, take the last path
				else if(pResult == 0)
					return; //We have found ourselves and are finished
				else if(pResult == 1)
					p++;
				else if(pResult == -1)
					path = true;
			}
			
			
			if(eNode.getValue(p) != null) {
				int i = 0;
				while(eNode.getChild(p).getValue(i) != null) {
				pResult = ((Comparable) aVal).compareTo(eNode.getChild(p).getValue(i));
				if(pResult == 0)
					return;
				else if(pResult == 1) {
					found = true;
					i++;	
				}
				else if(pResult == -1) {
					eNode = eNode.getChild(p); // Become the child
					found = false;
					break;
				}
				}
				
			}
			else {
				//For now we are the largest element, become the child
				eNode = eNode.getChild(p);
				int i = 0;
				while(eNode.getValue(i) != null) {
				pResult = ((Comparable) aVal).compareTo(eNode.getValue(i));
				if(pResult == 0)
					return;
				else if(pResult == 1) {
					i++;	
				}	
				else if(pResult == -1) {
					found = true;
					p = i;
					break;
				}
				}
			}	
		}
		
		//Inject the abundant value into the exchange node
		eNode.setValue(aVal, freeSpot(eNode.getValues()));
		Integer.insertionSort(eNode.getValues());
		erase(child, aVal);
		
		//aVal is now the one we pushed aside, move it into the smallest leaf in its left branch
		println("p is now: " + p);
		aVal = eNode.getValue(p+1);
		println("aVal has been changed to: " + Integer.transformInteger(aVal));
		child = eNode.getChild(p+1);
		while(child.getChild(0) != null)
			child = child.getChild(0);
		
		//Place the value into the leaf
		child.setValue(aVal, freeSpot(child.getValues()));
		Integer.insertionSort(child.getValues());
		println("The leaf deposited in contains: " + Integer.transformInteger(child.getValue(0)) + " as the first element post deposit, the next element is: " + Integer.transformInteger(child.getValue(1)));
		
		
		//Remove the duplicate from the root and restore the order
		erase(eNode, aVal);
		shiftValues(eNode.getValues());
		
		println("The previous eNode contains: " + Integer.transformInteger(eNode.getValue(0)) + " as the first element post deposit, the next element is: " + Integer.transformInteger(eNode.getValue(1)));
		println("aVal is now: " + Integer.transformInteger(aVal));
		
		aVal = child.getValue(lastElementPosition(child.getValues()));
		
		rotateToLastLeaf(aVal);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void erase(BTreeNode node, Object o){
		boolean removed = false;
		int i = 0;
		while(!removed) {
			if(((Comparable) node.getValue(i)).compareTo(o) == 0) {
				node.setValue(null, i);
				removed = true;
			}
			i++;
		}
	}
	
	/**Finds and erases a value within a node's direct child [do not use if child is a leaf]*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void deepErase(BTreeNode node, Object replacementVal) {
		boolean erased = false;
		int i = 0;
		while(!erased) {
			if(node.getChild(i) != null) {
				for(int j = 0; i < magnitude*2 && !erased && node.getChild(i).getValue(j) != null; j++) {
					if(((Comparable) node.getChild(i).getValue(j)).compareTo(replacementVal) == 0) {
					node.getChild(i).setValue(null, j);
					erased = true;
				}
			}
				i++;
			}
			break;
		}
	}
	
	/**Returns the amount of values in an array*/
	private int valueCount(Object[] objects) {
		int i = 0;
		while(objects[i] != null)
			i++;
		return i;
	}
	
	/**Shift along values to ensure all null-spaces are at the end of an array*/
	private void shiftValues(Object[] objects) {
		for(int i = 0; i < magnitude*2; i++) {
			if(objects[i] == null && objects[i+1] != null) {
				objects[i] = objects[i+1];
				objects[i+1] = null;
			}
		}
	}
	
	/**Counts the amount of children a parent node has*/
	private int childCount(BTreeNode parent) {
		int i = 0;
		while(parent.getChild(i) != null)
			i++;
		return i;
	}
	
	/**Checks a node's children for an underflow*/
	private boolean childrenCheck(BTreeNode parent) {
			int i = 0;
			
			if(parent == root)
				if(!minCheck(root.getValues()))
					return false;
			
			while(i <= magnitude*2 && parent.getChild(i) != null) {	
				if(!minCheck(parent.getChild(i).getValues())){
					return false;
					}
				i++;
		}
		return true;
	}
	
	/**Checks if the amount of values in a node are within the required criteria*/
	private boolean minCheck(Object[] objects) {
		int i = 0;
		while(i < magnitude) {
			if(objects[i] == null)
				return false;
			i++;
		}
		return true;
	}
	
	/**Returns the parent-node of a child that contains the query value*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BTreeNode getParent(Object o) {
		BTreeNode newParent = root;
		int result = 0;
		int i = 0;
		boolean found = false;
		do {
			boolean nullNext = false;
			
			result = ((Comparable) o).compareTo(newParent.getValue(i));

			if (newParent.getValue(i+1) == null) {
				nullNext = true;
			}
			
			if(result == 0)
				return newParent;

			else if(result == -1) {
				for(int j = 0; j < magnitude*2; j++) {
					if(((Comparable) o).compareTo(newParent.getChild(i).getValue(j)) == 0) {
						found = true;
					}
				}
				if(!found)
					newParent = newParent.getChild(i);
				i = 0;
			}

			else if(result == 1 && nullNext) {
				for(int j = 0; j < magnitude*2; j++) {
					if(((Comparable) o).compareTo(newParent.getChild(i+1).getValue(j)) == 0) {
						found = true;
					}
				}

				if(!found) {
					newParent = newParent.getChild(i+1);
				}
				i = 0;
			}

			else if(!nullNext) {
				i++;
			}
		}
		while(i < magnitude*2 && !found); //Value we seek can never be in a temporary node.
		return newParent;
	}
	
	/**Returns the child-node that contains the query-value*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private BTreeNode getChild(BTreeNode parent, Object o) {
		BTreeNode child = root;
		boolean found = false;
		Object val = new Integer(0);
		int i = 0;
		while(!found) {
			
			//If the value is not in any of the sub-arrays then the child is the root.
			if(parent.getChild(i) == null) {
				child = root;
				return child;
			}
			
			for(int j = 0; j < magnitude*2; j++) {
				val = parent.getChild(i).getValue(j);
				if(val != null && ((Comparable) val).compareTo(o) == 0) {
				found = true;
				child = parent.getChild(i);
				}
			}
			i++;
		}
		return child;
	}
	
	/**Returns the child's array position in a node which contains a specific value*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int getChildPosition(BTreeNode parent, Object aVal) {
		int i = 0;
		
		while(i <= magnitude*2) {
			for(int j = 0; j < magnitude*2; j++) {
				if(parent.getChild(i).getValue(j) != null && ((Comparable) parent.getChild(i).getValue(j)).compareTo(aVal) == 0) {
					return i;
				}
			}
			i++;
		}
		return -1;
	}

	/**Returns the position of an offending child-node in a parent-node*/
	private int getOffendingChildPosition(BTreeNode parent) {
		int i = 0;
		while(i <= magnitude*2) {
			if(parent.getChild(i) == null || !minCheck(parent.getChild(i).getValues())){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/**Returns the offending position in a value array [intended for nodes]*/
	private int getOffendingNodePosition(BTreeNode parent) {
		BTreeNode child;
		int vAmount = 0;
		int cAmount = 0;
		int i = 0;
		while(parent.getChild(i) != null) {
			child = parent.getChild(i);
			vAmount = valueCount(child.getValues());
			cAmount = childCount(child);
			if(cAmount != vAmount+1)
				return i;
			
			i++;
		}
		return i;
	}
	
	/**Returns the array position of a specified value in an array*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int getElementPosition(Object[] objects, Object lastElement) {
		int pos = 0;
		while(objects[pos] != null) {
			if(((Comparable) objects[pos]).compareTo(lastElement) == 0)
				return pos;
			pos++;
			
		}
		return -1;
	}
	
	/**Returns the corresponding root position of a leaf*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BTreeNode exchangeEquivalent(Object eVal) {
		//Locate exchange-node to cross branches
				BTreeNode eNode = root;
				boolean found = false;
				boolean path = false;

				int p = 0, pResult = 0;

				while(!found) {
					p = 0;
					path = false;
					
					//Find a path to go down
					while(!path) {
						pResult = ((Comparable) eVal).compareTo(eNode.getValue(p));
						
						if(eNode.getValue(p) == null)
							path = true; //End of array, take the last path
						else if(pResult == 1)
							p++;
						else if(pResult == -1)
							path = true;
					}
					
					
					if(eNode.getValue(p) != null) {
						int i = 0;
						while(eNode.getChild(p).getValue(i) != null) {
							pResult = ((Comparable) eVal).compareTo(eNode.getChild(p).getValue(i));
							if(pResult == 1) {
								found = true;
								i++;	
							}
							else if(pResult == -1) {
							eNode = eNode.getChild(p); // Become the child
							found = true;
							break;
						}
						}
						
					}
					else {
						//For now we are the largest element, become the child
						eNode = eNode.getChild(p);
						
						int i = 0;
						while(eNode.getValue(i) != null) {
						pResult = ((Comparable) eVal).compareTo(eNode.getValue(i));
						if(pResult == 1) {
							i++;	
						}	
						else if(pResult == -1) {
							found = true;
						}
						}
					}	
				}
		return eNode;
	}
	
	/**Returns the leaf equivalent value of a root value*/
	private Object leafEquivalentValue(BTreeNode node, int oPos){
		BTreeNode leaf = node.getChild(oPos+1);
		while(leaf.getChild(0) != null) {
			leaf = leaf.getChild(0);
		}
		return leaf.getValue(0);
	}
	
	/**Returns the leaf equivalent node of a root value*/
	private BTreeNode leafEquivalentNode(BTreeNode node, int oPos) {
		BTreeNode leaf = node.getChild(oPos+1);
		while(leaf.getChild(0) != null) {
			leaf = leaf.getChild(0);
		}
		return leaf;
	}
	
	/**Checks if a value array contains more than "m" values*/
	private boolean abundanceCheck(Object[] objects) {
		if(objects[magnitude] != null)
			return true;
		return false;	
	}
	
	/**Returns the index-position of the first unoccupied space in a value array*/
	private int freeSpot(Object[] objects) {
		int i = 0;
		while (i < magnitude*2) {
			if(objects[i] != null)
				i++;
			else
				break;
		}
		return i;
	}
	
	/**Returns the index-position last element in an array*/
	private int lastElementPosition(Object[] grandChildValues) {
		int i = 0;
		while (i < magnitude*2) {
			if(grandChildValues[i+1] != null)
				i++;
			else
				break;
		}
		return i;
	}
	
	/**Returns the last element in a value array*/
	private Object lastElement(Object[] grandChildValues) {
		Object element = grandChildValues[lastElementPosition(grandChildValues)];
		return element;
	}

	/**Attempts to correct an underflow in a leaf with a value from a neighbor*/
	private boolean rebalanceLeafs(BTreeNode parent) {
		int cPosition = getOffendingChildPosition(parent);
		//Look at the right neighbors
		for(int i = cPosition-1; i >= 0; i--) {
			if(abundanceCheck(parent.getChild(i).getValues())){
				rotateRight(parent, i, cPosition);
				return true;
			}
		}
		//Look at the left neighbors
		for(int i = cPosition + 1; i <= magnitude*2; i++) {
			if(parent.getChild(i) == null){
				return false;
			}
			else if(abundanceCheck(parent.getChild(i).getValues())){
				rotateLeft(parent, i, cPosition);
				return true;
			}
		}
		return false;
	}
	
	/**Performs a left-rotation for a given interval of iterations*/
	private void rotateLeft(BTreeNode parent, int from, int to){
		Object lValue, pValue = new Integer(0);
		for(int i = from; i > to; i--) {
			lValue = parent.getChild(i).getValue(0);
			pValue = parent.getValue(i-1);
			
			println("Parent value[PreRotate]: " + Integer.transformInteger(pValue));
			println("Leaf value[PreRotate]: " + Integer.transformInteger(lValue));
			
			//Place parent value into left-node
			parent.getChild(i-1).setValue(pValue, freeSpot(parent.getChild(i-1).getValues()));
			
			//Replace parent value with right leaf value
			parent.setValue(lValue, i-1);
			
			//Remove right leaf value
			parent.getChild(i).setValue(null, 0);
			shiftValues(parent.getChild(i).getValues());
			
		}
	}
	
	/**Performs a right-rotation for a given interval of iterations*/
	public void rotateRight(BTreeNode parent, int from, int to){
		Object lValue, pValue = new Integer(0);
		
		for(int i = from; i < to; i++) {
			lValue = lastElement(parent.getChild(i).getValues());
			pValue = parent.getValue(i);
			
			println("Parent value[PreRotate]: " + Integer.transformInteger(pValue));
			println("Leaf value[PreRotate]: " + Integer.transformInteger(lValue));
			
			//Place parent value into right-node
			parent.getChild(i+1).setValue(pValue, freeSpot(parent.getChild(i+1).getValues()));
			Integer.insertionSort(parent.getChild(i+1).getValues());
			
			//Replace parent value with left leaf value
			parent.setValue(lValue, i);
			
			//Remove left leaf value
			parent.getChild(i).setValue(null, lastElementPosition(parent.getChild(i).getValues()));
			
		}
	}
	
	/**Searches all leafs for an abundant value and returns it. Returns -1 if none found*/
	private int abundantSearch(){

		// get data: root & future storage
		BTreeNode pointer = root;
		BTreeNode[] storage = new BTreeNode[nodeCount()];
		int[] storage_2 = new int[nodeCount()*(magnitude*2)];
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		Object target;
		boolean skip = false;

		// add first node: root
		if (pointer != null){	
			storage[0] = pointer;

			for (i = 0; i != storage.length; i++){
				for (j = 0; j != magnitude*2; j++){

					// for each target-element print...
					if (storage[i].getValue(j) != null){
						target = storage[i].getValue(j);
						storage_2[l] = Integer.transformInteger(target);
						l++;

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
						storage_2[l] = -1;
						l++;
					}
				}
				skip = false;// here
			}
			// reset
			i = storage_2.length-(magnitude*2)-1;
			j = (magnitude*2)-1;
			k = 0;
			l = 0;

			// search: m+1
			while (!(storage_2[i+1] + "").equals(getMin().toString())){
				j = (magnitude*2)-1;
				while (j != magnitude-1){

					// check: find m+1 value
					if (storage_2[i] != -1){
						return storage_2[i];
					}
					i--;
					j--;
				}
				i -= magnitude;
			}		
		}
		return -1;
	}
	
	/**Removes a tree's last branch re-inserts the values*/
	private void cutBranch(){
		// get: essential data
		int counter = 0; 
		boolean found = false;
		BTreeNode parent = root;
		BTreeNode pointer = root;
		Object[] storage = new Object[nodeCount()*(magnitude*2)];

		// check: root gets cut
		if (pointer.getValue(1) != null){

			// get: last root element (!= null)
			for (int i = pointer.getValues().length -1; !found; i--){
				if (pointer.getValue(i) != null){
					
					found = true;
					storage[counter] = pointer.getValue(i);
					pointer = pointer.getChild(i+1);
					parent.setValue(null, i);
					parent.setChild(null, i+1);
					counter++;
				}
			}
			// copy-cut: remaining children
			chop(pointer, storage, counter);
		}
		// check: root gets cut away completely
		else { 
			// copy-cut: root and children
			for (int i = 0; i <= magnitude*2; i++){
				
			storage[counter] = pointer.getValue(i);
			counter++;
			counter = chop(pointer.getChild(i+1), storage, counter);
			}
			// update root
			root = parent.getChild(0);
		}
		// last step: insert chopped values
		counter = 0;
		while (storage[counter] != null){
			insert(storage[counter]);
			counter++;
		}
	}
	
	/**Assists cutBranch*/
	private int chop(BTreeNode pointer, Object[] storage, int counter){
		if (pointer != null){

			// get data: current node
			Object[] values = pointer.getValues();

			// first: get all current node values 
			for (int i = 0; i < values.length -1; i++){
				if (values[i] != null){
					storage[counter] = values[i];
					counter++;
				}
			}
			// second: repeat for each child of the node
			for (int i = 0; i <= magnitude*2; i++){
				counter = chop(pointer.getChild(i), storage, counter);
			}
		}
		return counter;
	}
	
	// EXTRACTED INTEGER METHODS
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void insertionSort (Object[] toSort) {
		for(int i = 1; i < establishRange(toSort); i++){
			Object m = toSort[i];
			int j = i;
			
			
			while(j > 0 && (((Comparable) toSort[j-1]).compareTo(m) == 1)) {
				if(((Comparable)toSort[j-1]).compareTo(m) == 1) {
					toSort[j] = toSort[j-1];
					j--;
				}
			}
			toSort[j] = m;
		}
	}
	
	public static int establishRange(Object[] array) {
		int i = 0;
		while(i < array.length) {
			if(array[i] == null)
				return i;
			i++;
		}
		return i;
	}
}
