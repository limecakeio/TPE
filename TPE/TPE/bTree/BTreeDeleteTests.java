/**
 * 
 */
package bTree;

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

		/**
		 * Delete a value in a leaf which will cause no changes [Simplest case]
		 */
//
//		println("Deleting 19 [SIMPLEST CASE]");
//		t1.printLevelorder();
//		t1.delete(new Integer(19));
//		t1.printLevelorder();

		/** Delete a value in a leaf which will cause a right-rebalance */
		println("\nTesting rebalance [Right rotation]:");
		t1.printLevelorder();
		println("\n Deleting 26");
		t1.delete(new Integer(26));
		println("Result:");
		t1.printLevelorder();
//
//		/** Delete a value in a leaf which will cause a left-rebalance */
//		println("\n\nInserting a value to manipulate a left-balance [25]");
//		t2.insert(new Integer(25));
//		t2.printLevelorder();
//		println("\n\n Deleting 19");
//		t2.delete(new Integer(19));
//		t2.printLevelorder();
		
//		/**Delete a leaf-value, causing two children to merge together*/
//		println("DELETE CAUSING A CHILD TO CONSUME ANOTHER");
//		t2 = new BTree(1);
//		t2.insert("btreedelete2.txt");
//		t2.printLevelorder();
//		t2.delete(new Integer(6));
//		t2.printLevelorder();
//		t2.delete(new Integer(4));
//		t2.printLevelorder();
		
		
		
//
//		/**
//		 * Delete a value in a leaf which will cause the parent value to
//		 * replace it
//		 */
//		println("\n\nDeleting 3 to force parent replacement");
//		t2.delete(new Integer(3));
//		// t2.printLevelorder();
//
//		/**
//		 * Delete a value in a leaf which is the largest in its branch but not
//		 * THE largest causing it to be replaced with the corresponding root
//		 * value
//		 */
//		println("\n\nDeleting 6 to force root replacement");
//		t2.delete(new Integer(6));
//		// t2.printLevelorder();
	}

//	@Test
//	public void deleteValueInRootTest() {
//		/**
//		 * Deleting value in root, causing one replacement from corresponding
//		 * leaf and ending in balanced condition
//		 */
//		println("\n\nSTART DELETE FROM ROOT");
//		println("\n\n BEFORE DELETE");
//		t1.insert(new Integer(20));
//		t1.printLevelorder();
//		println("\n\n POST DELETE");
//		t1.delete(new Integer(7));
//		t1.printLevelorder();
//
//		/**
//		 * Deleting value in root, causing one replacement from corresponding
//		 * leaf and ending in a position where the leaf will have to be
//		 * re-balanced
//		 */
//		println("\n\nSTART DELETE FROM ROOT WITH SUBSEQUENT REBALANCE OF LEAF");
//		t1.delete(new Integer(19));
//		t1.printLevelorder();
//		
//	}

	@Test
	public void deleteInKingLeafTest() {
		/**
		 * CASE: Deleting a value from the largest leaf in the tree causing an
		 * underflow.
		 * 
		 * Test once with another value in the tree which will balance
		 * everything[no merge].
		 */

		/**
		 * Test once with causing an underflow throughout the tree causing a
		 * merge
		 */
	}

}
