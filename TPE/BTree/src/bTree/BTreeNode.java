package bTree;

public class BTreeNode {

	private Integer [] values;
	private BTreeNode [] children;

	// SETUP BTREE NODE
	BTreeNode(int magnitude){

		// array length: (magnitude x2)+1
		values = new Integer [(magnitude*2)+1];
		children = new BTreeNode [(magnitude*2)+2];
	}

	BTreeNode(Integer value, int magnitude){

		// array length: (magnitude x2)+1
		values = new Integer [(magnitude*2)+1];
		children = new BTreeNode [(magnitude*2)+2];
		
		// set values
		values[0] = value;
	}

	// BTREE NODE MTHODS
	public void setValue(Integer value, int index){
		values[index] = value;
	}

	public Integer getValue(int index){
		return values[index];
	}
	
	public Integer[] getValues(){
		return values;
	}
	
	public void setChild (BTreeNode reference, int index){
		children[index] = reference;
	}
	
	public BTreeNode getChild(int index){
		return children[index];
	}
	
	public BTreeNode[] getChildren(){
		return children;
	}
	
	// SPECIAL
	public void printnode(){
		Integer[] storage = values;
		
		for (int i = 0; i != storage.length; i++){
			if (values[i] == null){
				System.out.print("null, ");
			}
			else {
				System.out.print(Integer.transformInteger(storage[i]) +  ", ");
			}
		}
		System.out.println("");
	}

}
