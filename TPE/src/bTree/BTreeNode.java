package bTree;

public class BTreeNode {

	private Integer [] values;
	private BTreeNode [] childrenNodes;

	// SETUP BTREE NODE
	BTreeNode(int magnitude){

		// array length: (magnitude x2)+1
		values = new Integer [(magnitude*2)+1];
		childrenNodes = new BTreeNode [(magnitude*2)+2];
	
		setNullReferences(childrenNodes);
		
	}

	BTreeNode(Integer value, int magnitude){

		// array length: (magnitude x2)+1
		values = new Integer [(magnitude*2)+1];
		childrenNodes = new BTreeNode [(magnitude*2)+2];
		
		// set values
		values[0] = value;
		setNullReferences(childrenNodes);
	}

	// BTREE NODE MTHODS
	public void setValues(Integer value, int index){
		values[index] = value;
	}

	public Integer getValue(int index){
		return values[index];
	}
	
	public int getValuesLength(){
		return values.length;
	}
	
	public BTreeNode getChild(int index){
		return childrenNodes[index];
	}
	
	// SPECIAL
	private BTreeNode[] setNullReferences(BTreeNode[] children){
		
		for(int i = 0; i < children.length; i++){
			children [i] = null;
		}
		return children;
	}

}
