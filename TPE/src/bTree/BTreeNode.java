package bTree;

public class BTreeNode {

	private Integer [] values;
	private BTreeNode [] childrenNodes;

	// SETUP BTREE NODE
	BTreeNode(int magnitude){

		// array length: (magnitude x2)+1
		values = new Integer [(magnitude*2)+1];
		childrenNodes = new BTreeNode [(magnitude*2)+2];
	}

	BTreeNode(Integer value, int magnitude){

		// array length: (magnitude x2)+1
		values = new Integer [(magnitude*2)+1];
		childrenNodes = new BTreeNode [(magnitude*2)+2];
		
		// set values
		values[0] = value;
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
	public void printnode(){
		Integer[] storage = values;
		
		for (int i = 0; i != storage.length; i++){
			if (values[i] == null){
				System.out.print("null, ");
			}
			else {
				System.out.print(storage[i].transformInteger(storage[i]) +  ", ");
			}
		}
		System.out.println("");
	}

}
