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

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int height() {
		// TODO Auto-generated method stub
		return 0;
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
			System.out.print(values[0] + ", ");
			}
			
			// third: repeat strategy for bigger elements
			for (int i = 1; i < children.length -1; i++){
				
				// get remaining values of the node
				if (values[i] != null){
					System.out.print(values[i] + ", ");
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

	public void printLevelorder() {
		// TODO Auto-generated method stub
		
	}
	
	// SPECIAL METHODS
	
	// public for testing
	public boolean preinsert(Integer o){
		
		int i = 0;
		boolean success = false;
		BTreeNode parent = null;
		BTreeNode pointer = root;

		// tree contains elements
		if (pointer != null){
			
			// search and insert
			while (!success){

				// check stored value: empty
				if (pointer.getValue(i) == null){
					pointer.setValues(o, i);
					Integer.insertionSort(pointer.getValues());
					
					// check node: healthy
					if (criteriaCheck(pointer)){
						success = true;
					}
					// check node: overload
					else {
						// case: root
						if (parent == null){
							 burstTree(magnitude, pointer.getValues(), pointer.getChildren());
							 success = true;
						}

					}
				}
				// check stored value: o > value[i]
				else if ((pointer.getValue(i)).compareTo(o) == 1){
					
					// check right child: not present
					if (pointer.getChild(i+1) == null){
						i++;
					}
					// check right child: descend
					else {
						pointer = pointer.getChild(i+1);
						i = 0;
					}
				}
				// check stored value: o < value[i]
				else if ((pointer.getValue(i)).compareTo(o) == -1){

					// check left child: not present
					if (pointer.getChild(i) == null){
						i++;
					}
					// check left child: descend
					else {
						pointer = pointer.getChild(i);
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
	
	private void burstTree(int magnitude, Integer[] values, BTreeNode[] children){
	}

	private boolean criteriaCheck(BTreeNode pointer){
		Integer [] values = pointer.getValues();
		
		// check last array element
		if (values[values.length-1] == null)
			return true;
		return false;
	}
	
	private boolean leafCheck(BTreeNode pointer){
		Integer [] values = pointer.getValues();
		
		// check two last array elements
		if (values[values.length-1] == null && values[values.length-2] == null)
			return true;
		return false;
	}
}
