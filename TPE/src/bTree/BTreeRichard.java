package bTree;

public class BTreeRichard {
	private void burstTree(int magnitude, BTreeNode node){
		//Split the root - create 2 new nodes and populate
				BTreeNode newRoot = new BTreeNode(magnitude);
				BTreeNode rightChild = new BTreeNode(magnitude);
				
				//Set new root
				newRoot.setValues(node.values[magnitude], 0);
				node.values[magnitude] = null;
				
				//Fill the right node
				node.values[magnitude] = null;
				for(int i = magnitude+1; i < node.values.length; i++) {
					rightChild.setValues(node.values[i], magnitude+1-i);
					System.out.println("R: " + Integer.toString(rightChild.getValue(magnitude+1-i)));
					node.values[i] = null;
				}

				//Set new references
				newRoot.setChild(node, 0);
				newRoot.setChild(rightChild, 1);
	}
	
	}