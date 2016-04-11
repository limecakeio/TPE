package bTree;

public class BTree implements BTreeInterface{
	
	private BTreeNode root;
	
	// B-TREE SET UP
	
	public BTree(int magnitude){
		root = new BTreeNode(magnitude);
	}
	
	public BTree(int magnitude, Integer o){
		root = new BTreeNode(magnitude, o);
	}
	
	// CORE INTERFACE METHODS
	public boolean insert(Integer o){
		
		BTreeNode pointer = root;
		return insert(o, pointer);
	}
	private boolean insert(Integer o, BTreeNode pointer){
		
		int i = 0;
		Integer [] values = pointer.getValuesFull();
		
		if (values[i] == null){
			pointer.setValues(o, i);
			return true;
		}
		else if (o.compareTo(values[i]) == -1){
				pointer = pointer.getChild(i);
				return insert(o, pointer);
		}
		else if (o.compareTo(values[i]) == 1){
				while (o.compareTo(values[i]) == 1 && i < values.length-1){
					i++;
				}
			pointer = pointer.getChild(i);
			return insert(o, pointer);
		}
		else return false;
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
	
}
