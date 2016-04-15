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

	public void printPreorder() {
		// TODO Auto-generated method stub
		
	}

	public void printInorder() {
		// TODO Auto-generated method stub
		
	}

	public void printPostorder() {
		// TODO Auto-generated method stub
		
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
					
					// check node: overload
					if (criteriaCheck(pointer)){
						success = true;
					}
					else {
						// case: root
						if (parent == null){
							 //do stuff here
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
	
	private boolean criteriaCheck(BTreeNode pointer){
		Integer [] values = pointer.getValues();
		
		// check last array element
		if (values[values.length-1] == null)
			return true;
		return false;
	}
	
	private boolean leafCheck(BTreeNode pointer){
		Integer [] values = pointer.getValues();
		
		// check last array element
		if (values[values.length-1] == null && values[values.length-1] == null)
			return true;
		return false;
	}
}
