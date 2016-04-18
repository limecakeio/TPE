package bTree;

public class BTreeRichard {
	public void burstTree(int magnitude, Integer[] values, BTreeNode[] children) {
		
		//Split the root - create 2 new nodes and populate
		BTreeNode leftChild = new BTreeNode(magnitude);
		BTreeNode rightChild = new BTreeNode(magnitude);
		
		//Fill the nodes
		for(int i = 0; i < magnitude; i++) {
			leftChild.setValues(values[i], i);
			rightChild.setValues(values[i+magnitude+1], i+magnitude+1);
		}
		
		//Set new references on root
		children[0] = leftChild;
		children[1] = rightChild;
		
		
		}
		
	}