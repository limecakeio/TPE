package bTree;

public class BTreeNode {

	private Integer [] values;
	private BTreeNode [] children;

	// SETUP BTREE NODE
	BTreeNode(int magnitude){

		// array length: (magnitude x2)+1
		values = new Integer [(magnitude*2)+1];
		children = new BTreeNode [(magnitude*2)+1];
		
		setChildren(children);
		
	}

	BTreeNode(int magnitude, Integer value){

		// array length: (magnitude x2)+1
		values = new Integer [(magnitude*2)+1];
		children = new BTreeNode [(magnitude*2)+1];
		
		// set values
		values[0] = value;
		setChildren(children);
	}

	// BTREE NODE MTHODS
	public void setValues(Integer value, int index){
		values [index] = value;
	}

	public Integer getValues(int index){
		return values[index];
	}
	
	public BTreeNode getChild(int index){
		return children[index];
	}
	
	// SPECIAL
	private BTreeNode[] setChildren(BTreeNode[] children){
		
		for(int i = 0; i < children.length; i++){
			children [i] = null;
		}
		return children;
	}

}
