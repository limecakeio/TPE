package bTree;

public class BTreeRichard {
	public void burstTreeLeaf(int magnitude, BTreeNode parent, BTreeNode brokenLeaf) {
		
		//Arrange a new node for all elements > magnitude
		BTreeNode newLeaf = new BTreeNode(magnitude);
		
		//Insert elements into the new node and remove from source
		for(int i = magnitude+1; i < magnitude*2; i++) {
			newLeaf.setValues(brokenLeaf.getValue(i), i-magnitude+1);
			brokenLeaf.setValues(null, i);
		}
		
		//Placing middle-value into parent-node
		Integer mValue = brokenLeaf.getValue(magnitude);
		brokenLeaf.setValues(null, magnitude); // Reset middle-value
		
		int pos = 0;
		for(int i = 0; i < magnitude; i++) {
			if(parent.getChild(i) != null)
				pos += i++;
		}
		parent.setValues(mValue, pos);
		
		Integer.insertionSort(parent.getValues());
		
		criteriaCheck(parent);
		
	}
	}