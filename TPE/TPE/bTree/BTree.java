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

	public boolean insert(String filename) {
		
		Integer value = new Integer(0);
		
		// check: file is present & readable
		if (isFilePresent(filename) && (isFileReadable(filename))){
			Object inputFile = openInputFile(filename);

			while (!isEndOfInputFile(inputFile)){
				value = new Integer(readInt(inputFile));

				// attempt to insert value into tree
				if (insert(value) == false){
					println("Failed to insert: " + Integer.transformInteger(value) + ".");
				}
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
		
		BTreeNode parent = root;
		
		int result = 0;
		int i = 0;
		int validRange = 0;
		boolean found = false;
		boolean end = false;
		
		do {
			boolean nullNext = false;
			result = o.compareTo(parent.getValue(i));
			
			if (parent.getValue(i+1) == null) {
				nullNext = true;
			}
			
			if(result == 0)
				found = true;
			
			else if(result == -1) {
				validRange = Integer.establishRange(parent.getChild(i).getValues());
				for(int j = 0; j < validRange; j++) {
					if(o.compareTo(parent.getChild(i).getValue(j)) == 0) {
					found = true;
				}
				}
				if(!found)
					parent = parent.getChild(i);
				i = 0;
			}

			else if(result == 1 && nullNext) {
				validRange = Integer.establishRange(parent.getChild(i+1).getValues());
				for(int j = 0; j < validRange; j++) {
					if(o.compareTo(parent.getChild(i+1).getValue(j)) == 0) {
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

	public void addAll(BTree otherTree) {
	BTreeNode pointer = otherTree.root;
	addAll(pointer);
	}
	
	public void addAll(BTreeNode pointer) {
		if (pointer != null){

			// get data: current node
			Integer[] values = pointer.getValues();
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
				print(Integer.toString(values[0]) + ", ");
			}

			// third: repeat strategy for bigger elements
			for (int i = 1; i < children.length -1; i++){

				// get remaining values of the node
				if (values[i] != null){
					print(Integer.toString(values[i]) + ", ");
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
		BTreeNode[] storage = new BTreeNode[nodeCount()]; 

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
		Integer [] values = pointer.getValues();

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
			Integer[] values = children[i].getValues();
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
			Integer[] values = children[i].getValues();
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
		else {

			for (int i = neighbourLeaf; i > child; i--){

				boolean set = false;
				Integer parentValue = parent.getValue(i-1);
				Integer childValue = parent.getChild(i-1).getValue((magnitude*2));

				// set values to 'null' before swapping
				parent.setValue(null, i-1);
				parent.getChild(i-1).setValue(null, (magnitude*2));

				for (int j = 0; j < pointer.getValues().length-1 && !set; j++){
					if (parent.getChild(i).getValue(j) == null){

						// search and set position for parent value (from the top to the left) 
						parent.getChild(i).setValue(parentValue, j);
						Integer.insertionSort(parent.getChild(i).getValues());
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
	public BTreeNode locateParentNode(Integer query) {
		//Enter the tree
		BTreeNode newParent = root;

		int result = 0;
		int validRange = 0;
		int i = 0;
		boolean found = false;
	
		do {
			boolean nullNext = false;
			result = query.compareTo(newParent.getValue(i));

			if (newParent.getValue(i+1) == null) {
				nullNext = true;
			}

			if(result == -1) {
				validRange = Integer.establishRange(newParent.getChild(i+1).getValues());
				for(int j = 0; j < validRange; j++) {
					if(query.compareTo(newParent.getChild(i).getValue(j)) == 0) {
						newParent.getChild(i).setValue(null, j); // Remove located value
						found = true;
					}
				}
				if(!found)
					newParent = newParent.getChild(i);
				i = 0;
			}

			else if(result == 1 && nullNext) {
				validRange = Integer.establishRange(newParent.getChild(i+1).getValues());
				for(int j = 0; j < validRange; j++) {
					if(query.compareTo(newParent.getChild(i+1).getValue(j)) == 0) {
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
		return pointer-1;
	}
	
	public boolean delete(Integer o){
		if(contains(o)) {
			BTreeNode pointer = root;
			println("Value is in tree. Attempting delete.");
			if(!delete(pointer, o)) {
				println("Delete screwed up!");
				return false;
			}
			return true;
		}
			
		return false;
	}
	private boolean delete(BTreeNode parent, Integer o) {
		parent = getParent(o);
		BTreeNode child = getChild(parent, o);
		boolean fixed = false;
		
		erase(child, o);
		
		//Delete from leaf
		if(child.getChild(0) == null)
			shiftValues(child.getValues());

		if(child != root)
			fixed = childrenCheck(parent);
		else
			fixed = minCheck(child.getValues());

		if(!fixed)
			restructureTree(parent);
		return true;
	}
	
	private void restructureTree (BTreeNode parent) {
		
		if(parent == root) {
			//Check if root and child are the same
			if(!minCheck(parent.getValues())) {
				treatRoot(root);
			}
			else {
				//Child is a node
				if(parent.getChild(0) != null) {
					treatNode(root);
				}
				//Child is a leaf
				else {
					treatLeaf(root);	
				}	
			}	
		}
		//Parent of node
		else {
			if(parent.getChild(0).getChild(0) != null)
				treatNode(parent);
			//Parent of leaf
			else
				treatLeaf(parent);
		}
	}
	
	private void treatRoot(BTreeNode parent) {
		println("Treating the root");
		int oPos = getOffendingPosition(parent.getValues());
		Integer leafVal = leafEquivalentValue(oPos);
		BTreeNode leaf = leafEquivalentNode(oPos);
		
		//Change parent to leaf parent for later processing
		parent = getParent(leafVal);
		
		//Fill the gap with its leaf equivalent
		root.setValue(leafVal, oPos);
		
		//Delete the value from its leaf
		erase(leaf, leafVal);
		shiftValues(leaf.getValues());
		
		//Check the leaf
		if(!minCheck(leaf.getValues()))
			restructureTree(parent);
	}
	
	private void treatNode(BTreeNode parent) {
		println("Treating via a node parent");
		
		BTreeNode child = parent.getChild(getOffendingChildPosition(parent));
		int oPos = getOffendingPosition(child.getValues());
		
		//See if the child is a leaf-parent so it can try to take largest value from smaller child
		if(child.getChild(0).getChild(0) == null) {
			if(abundanceCheck(child.getChild(oPos).getValues())) {
				Integer[] grandChildValues = child.getChild(oPos).getValues();
				//Fill the void
				child.setValue(lastElement(grandChildValues), oPos);
				//Remove the duplicate
				erase(child.getChild(oPos), lastElement(grandChildValues));
				if(!childrenCheck(child))
					treatLeaf(child);
				return;
			}
		}
			
		Integer leafVal = leafEquivalentValue(oPos);
		BTreeNode leaf = leafEquivalentNode(oPos);
		
		//Change parent to leaf parent for later processing
		parent = getParent(leafVal);
		
		//Fill the gap with its leaf equivalent
		child.setValue(leafVal, oPos);
		
		//Delete the value from its leaf
		erase(leaf, leafVal);
		shiftValues(leaf.getValues());
		
		//Check the leaf
		if(!minCheck(leaf.getValues()))
			restructureTree(parent);
	}
	
	private void treatLeaf(BTreeNode parent) {
		println("Treating via leaf parent");
		
		if(!rebalanceLeafs(parent)) {
			
			//Attempt to merge the last two children together
			if(childCount(parent) > magnitude+1) {
				int largeChild = childCount(parent)-1;
				int smallChild = largeChild-1;
				Integer[] sChild, lChild;
				sChild = parent.getChild(smallChild).getValues();
				lChild = parent.getChild(largeChild).getValues();
				int valCount = valueCount(parent.getChild(largeChild).getValues());
				
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
				Integer replacementVal = parent.getValue(cPos);
				
				//Place parent value into child
				child.setValue(replacementVal, freeSpot(child.getValues()));
		
				//Set new parent a level above previous one
				parent = getParent(replacementVal);
				deepErase(parent, replacementVal);
				
				if(!childrenCheck(parent))
					restructureTree(parent);
				return;
			}
			
			//Leaf is the largest in its branch
			else {
				//Ensure we are not in the tree's last leaf and 
				//replace the value with it's corresponding root value
				if(!containsMax(child.getValues())) {
					
					int rPos = rootEquivalent(lastElement(child.getValues()));
					
					//Take the root's corresponding value and add it to the end of the leaf
					child.setValue(root.getValue(rPos), freeSpot(child.getValues()));
					
					//Remove the duplicate value from the root
					erase(root, root.getValue(rPos));
					
					parent = root;
					restructureTree(parent);
				}
					
				// We are in the tree's largest leaf
				else {
					int abundantValue = abundantSearch();
					BTreeNode target = getParent(getMax());
					
					if(abundantValue != -1) {
						int cAmount, pos;
						Integer aVal = new Integer(abundantValue);
						Integer highestVal;
						
						
						do {
						parent = getParent(aVal);
						cAmount = childCount(parent);
						cPos = getChildPosition(parent, aVal);
						
						//Ensure the abundance moves to a branches' last leaf
						rotateRight(parent, cPos, cAmount-1);
						child = parent.getChild(cAmount-1);
						highestVal = lastElement(child.getValues());
						
						
						//As long as we are not in the last branch we rotate the abundance to the next one
						if(parent.getChild(cPos +1 ) != null) {
							root.setValue(highestVal, freeSpot(root.getValues()));
							Integer.insertionSort(root.getValues());
							erase(child, highestVal);

							//Place the next highest value, into its smaller branch
							pos = getElementPosition(root.getValues(), highestVal)+1;
							highestVal = root.getValue(pos);

							//Climb down the tree to the required leaf
							child = root.getChild(pos);
							while(child.getChild(0) != null)
								child = child.getChild(0);

							//Place the value
							child.setValue(highestVal, freeSpot(child.getValues()));
							Integer.insertionSort(child.getValues());

							//Remove the duplicate from the root and restore the order
							erase(root, highestVal);
							shiftValues(root.getValues());

							aVal = highestVal;
						}

						}
						while(!minCheck(target.getValues()));
					}
					else{
						println("CUT THE TREE!!!!");
					}		
				}
			}
			}
		}

	
	/**HELPFUL METHODS TO REALISE DELETE*/
	
	
	private void erase(BTreeNode node, Integer o){
		boolean removed = false;
		int i = 0;
		while(!removed) {
			if(node.getValue(i).compareTo(o) == 0) {
				println("Succesfull compare");
				node.setValue(null, i);
				removed = true;
			}
			i++;
		}
	}
	
	/**Finds and erases a value within a node's direct child [do not use if child is a leaf]*/
	private void deepErase(BTreeNode node, Integer o) {
		boolean erased = false;
		int i = 0;
		while(!erased) {
			if(node.getChild(i) != null) {
				for(int j = 0; i < magnitude*2 && !erased && node.getChild(i).getValue(j) != null; j++) {
					if(node.getChild(i).getValue(j).compareTo(o) == 0) {
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
	private int valueCount(Integer[] values) {
		int i = 0;
		while(values[i] != null)
			i++;
		return i;
	}
	
	/**Shift along values to ensure all null-spaces are at the end of an array*/
	private void shiftValues(Integer[] values) {
		for(int i = 0; i < magnitude*2; i++) {
			if(values[i] == null && values[i+1] != null) {
				values[i] = values[i+1];
				values[i+1] = null;
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
			while(i < magnitude*2 && parent.getChild(i) != null) {
				if(!minCheck(parent.getChild(i).getValues())){
					return false;
					}
			i++;
		}
		return true;
	}
	
	/**Checks if the amount of values in a node are within the required criteria*/
	private boolean minCheck(Integer[] values) {
		int i = 0;
		while(i < magnitude) {
			if(values[i] == null)
				return false;
			i++;
		}
		return true;
	}
	
	/**Checks if deleting a value will cause the leaf to have an underflow*/
	private boolean preDeleteMinCheck(BTreeNode node) {
		if(node.getValue(magnitude) != null)
			return true;
		return false;
	}
	
	/**Returns the parent-node of a child that contains the query value*/
	private BTreeNode getParent(Integer query) {
		BTreeNode newParent = root;
		int result = 0;
		int i = 0;
		boolean found = false;
		println("LOOKING FOR PARENT");
		do {
			boolean nullNext = false;
			
			result = query.compareTo(newParent.getValue(i));

			if (newParent.getValue(i+1) == null) {
				nullNext = true;
			}
			
			if(result == 0)
				return newParent;

			else if(result == -1) {
				for(int j = 0; j < magnitude*2; j++) {
					if(query.compareTo(newParent.getChild(i).getValue(j)) == 0) {
						found = true;
					}
				}
				if(!found)
					newParent = newParent.getChild(i);
				i = 0;
			}

			else if(result == 1 && nullNext) {
				for(int j = 0; j < magnitude*2; j++) {
					if(query.compareTo(newParent.getChild(i+1).getValue(j)) == 0) {
						found = true;
					}
				}

				if(!found) {
					newParent = newParent.getChild(i+1);
					println("Going down");
				}
				i = 0;
			}

			else if(!nullNext) {
				i++;
			}
		}
		while(i < magnitude*2 && !found); //Value we seek can never be in a temporary node.
		println("Parent found and set");
		return newParent;
	}
	
	/**Returns the child-node that contains the query-value*/
	private BTreeNode getChild(BTreeNode parent, Integer o) {
		BTreeNode child = root;
		boolean found = false;
		Integer val = new Integer(0);
		int i = 0;
		while(!found) {
			
			//If the value is not in any of the sub-arrays then the child is the root.
			if(parent.getChild(i) == null) {
				child = root;
				return child;
			}
			
			for(int j = 0; j < magnitude*2; j++) {
				val = parent.getChild(i).getValue(j);
				if(val != null && val.compareTo(o) == 0) {
				found = true;
				child = parent.getChild(i);
				}
			}
			i++;
		}
		println("Successfully set the child");
		return child;
	}
	
	/**Returns the child's array position in a node which contains a specific value*/
	private int getChildPosition(BTreeNode parent, Integer o) {
		int i = 0;
		
		while(i <= magnitude*2) {
			for(int j = 0; j < magnitude*2; j++) {
				if(parent.getChild(i).getValue(j) != null && parent.getChild(i).getValue(j).compareTo(o) == 0) {
					return i;
				}
			}
			i++;
		}
		return -1;
	}

	/**Returns an offending child-node*/
	private BTreeNode getOffendingChild(BTreeNode parent) {
		BTreeNode child = parent.getChild(getOffendingChildPosition(parent));
		return child;
	}
	
	/**Returns the position of an offending child-node in a parent-node*/
	private int getOffendingChildPosition(BTreeNode parent) {
		int i = 0;
		while(i < magnitude*2) {
			if(!minCheck(parent.getChild(i).getValues()) || parent.getChild(i) == null){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/**Returns the offending position in a value array [intended for nodes]*/
	private int getOffendingPosition(Integer[] values) {
		int i = 0;
		while(values[i] != null) {
			i++;
		}
		return i;
	}
	
	/**Returns the array position of a specified value in an array*/
	private int getElementPosition(Integer[] values, Integer o) {
		int pos = 0;
		while(values[pos] != null) {
			if(values[pos].compareTo(o) == 0)
				return pos;
			pos++;
			
		}
		return -1;
	}
	
	/**Returns the corresponding root position of a leaf*/
	private int rootEquivalent(Integer o) {
		for(int i = 0; i < magnitude*2; i++) {
			if(root.getValue(i).compareTo(o) == 1) {
				return i;
			}
		}
		return -1;
	}
	
	/**Returns the leaf equivalent value of a root value*/
	private Integer leafEquivalentValue(int oPos){
		BTreeNode leaf = root.getChild(oPos+1);
		while(leaf.getChild(0) != null) {
			leaf = leaf.getChild(0);
		}
		return leaf.getValue(0);
	}
	
	/**Returns the leaf equivalent node of a root value*/
	private BTreeNode leafEquivalentNode(int oPos) {
		BTreeNode leaf = root.getChild(oPos+1);
		while(leaf.getChild(0) != null) {
			leaf = leaf.getChild(0);
		}
		return leaf;
	}
	
	/**Checks if a value array contains more than "m" values*/
	private boolean abundanceCheck(Integer[] values) {
		if(values[magnitude] != null)
			return true;
		return false;
		
	}
	
	/**Checks if a value array contain's the trees max-value*/
	private boolean containsMax(Integer[] values) {
		Integer val = this.getMax();

		for(int i = 0; i < magnitude*2; i++){
			if(values[i] != null && values[i].compareTo(val) == 0)
				return true;	
		}
		return false;
	}
	
	/**Returns the index-position of the first unoccupied space in a value array*/
	private int freeSpot(Integer[] values) {
		int i = 0;
		while (i < magnitude*2) {
			if(values[i] != null)
				i++;
			else
				break;
		}
		return i;
	}
	
	/**Returns the index-position last element in an array*/
	private int lastElementPosition(Integer[] values) {
		int i = 0;
		while (i < magnitude*2) {
			if(values[i+1] != null)
				i++;
			else
				break;
		}
		return i;
	}
	
	/**Returns the last element in a value array*/
	private Integer lastElement(Integer[] values) {
		Integer element = values[lastElementPosition(values)];
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
		for(int i = cPosition + 1; i < magnitude*2; i++) {
			if(abundanceCheck(parent.getChild(i).getValues())){
				rotateLeft(parent, i, cPosition);
				return true;
			}
		}
		return false;
	}
	
	/**Performs a left-rotation for a given interval of iterations*/
	private void rotateLeft(BTreeNode parent, int from, int to){
		Integer lValue, pValue = new Integer(0);
		println("Attempting ROTATE LEFT: ");
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
		Integer lValue, pValue = new Integer(0);
		
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
		Integer target;
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
			j = 0;
			k = 0;
			l = 0;

			// search: m+1
			while (storage_2[i] != Integer.transformInteger(getMin())){
				j = (magnitude*2)-1;
				while (j != magnitude-1){

					// check: find m+1 value
					if (storage_2[i] != -1){
						return storage_2[i];
					}
					i--;
					j--;
				}
			}		
		}
		return -1;
	}
	
	private void collapseTree(BTree tree){}
	
	
}
