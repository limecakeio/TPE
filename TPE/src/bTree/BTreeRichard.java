package bTree;

public class BTreeRichard {
	
	public BTreeInterface clone(){

		int i = 0;
		int j = 0;
		int k = 0;
		BTree clone;
		Integer target;
		boolean skip = false;
		BTreeNode pointer = root;
		BTreeNode[] storage = new BTreeNode[nodeCount()];

		// add first node: root
		if (pointer != null){	
			storage[0] = pointer;

			// queue functionality
			for (i = 0; i != storage.length; i++){
				for (j = 0; j != magnitude*2; j++){

					// for each target-element print...
					if (storage[i].getValue(j) != null){
						target = storage[i].getValue(j);

						// ... its children are added to the storage.
						if (k != (storage.length)*(magnitude*2)){

							// check: left child of the target	
							if (storage[i].getChild(j) != null && !skip){	
								k++;
								storage[k] = storage[i].getChild(j);
								skip = true;
							}

							// check: target has a right child	
							if (storage[i].getChild(j+1) != null){	
								k++;
								storage[k] = storage[i].getChild(j+1);
							}
						}
					}
				}
				skip = false;// here
			}
			// convert storage: create a clone
			for (i = 0; i != storage.length; i++){
				for (j = 0; j != magnitude*2; j++){
					target = storage[i].getValue(j);
					clone.insert(target);
				}
			}
		}
		return clone;
	}

}