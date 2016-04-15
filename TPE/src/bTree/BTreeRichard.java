package bTree;

public class BTreeRichard {
	public void burstTree(int magnitude, Integer[] values, Integer[] children) {
		
		//Split the root - create 2 new nodes and populate
		BTreeNode leftChild = new BTreeNode(magnitude);
		BTreeNode rightChild = new BTreeNode(magnitude);
		
		//Fill the nodes
		for(int i = 0, k = magnitude+1; i < magnitude && k <= magnitude*2;) {
			leftChild.setValues(values[i], i);
			rightChild.setValues(values[k], i);
		}
		
		//Set new references on root
		children[0] = leftChild.getValue(0);
		children[1] = rightChild.getValue(1);
		
		
		}
		
	}