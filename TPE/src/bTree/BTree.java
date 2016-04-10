package bTree;

public class BTree implements BTreeInterface{
	
	private BTreeNode root;
	
	// B-TREE SET UP
	
	public BTree(){
		// TODO Auto-generated constructor stub
	}
	
	public BTree(Integer o){
		// TODO Auto-generated constructor stub
	}
	
	// CORE INTERFACE METHODS
	
	public boolean insert(Integer o) {
		// TODO Auto-generated method stub
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
	
}
