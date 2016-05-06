/**
 * 
 */
package crapPack;

import static gdi.MakeItSimple.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Richard Vladimirskij
 * @author Konstantin Pelevin
 *
 */
public class BTreeDeleteTests {
	BTree t1;
	BTree t2;

	
	@Before
	public void setUp() throws Exception {
		t1 = new BTree(1);
		t1.insert("btreedelete.txt");
		t2 = new BTree(1);
		t2.insert("btreedelete.txt");
	}
	
	@Test
	public void deleteValueInLeafTest() {
		
		/**Delete a value in a leaf which will cause no changes [Simplest case]*/
		
		t1.printLevelorder();
		t1.delete(new Integer(19));
		t1.printLevelorder();
		
		/**Delete a value in a leaf which will cause a right-rebalance*/
		println("\nTesting rebalance [Right rotation]:");
		t2.printLevelorder();
		println("\n Deleting 26");
		t2.delete(new Integer(26));
		println("Result:");
		t2.printLevelorder();
		
		/**Delete a value in a leaf which will cause a left-rebalance*/
		println("\n\nInserting a value to manipulate a left-balance [25]");
		t2.insert(new Integer(25));
		t2.printLevelorder();
		println("\n\n Deleting 19");
		t2.delete(new Integer(19));
		t2.printLevelorder();
		
		/**Delete a value in a leaf which will cause a the parent value to replace it*/
		println("\n\nDeleting 3 to force parent replacement");
		t2.delete(new Integer(3));
		t2.printLevelorder();
		
		/**Delete a value in a leaf which is the largest in its branch but not THE largest
		 * causing it to be replaced with the corresponding root value*/
		println("\n\nDeleting 6 to force root replacement");
		t2.delete(new Integer(6));
		t2.printLevelorder();
		
	
	}
	

	@Test
	public void deleteInKingLeafTest() {
	/**CASE: Deleting a value from the largest leaf in the tree causing an underflow.
	 
	 * Test once with another value in the tree which will balance everything[no merge]. */
		
	 
	 /** Test once with causing an underflow throughout the tree causing a merge*/
	}

}
