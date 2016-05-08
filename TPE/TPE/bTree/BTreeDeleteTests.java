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
	public final String divider = "\n------------------------------------\n";

	@Before
	public void setUp() throws Exception {
		t1 = new BTree(1);
		t1.insert("btreedelete.txt");
		t2 = new BTree(1);
		t2.insert("btree.txt");
	}

//	@Test
//	public void deleteValueInLeafTest() {
//
//		/**
//		 * Delete a value in a leaf which will cause no changes [Simplest case]
//		 */
//
//		println("Deleting 19 [SIMPLEST CASE]");
//		t1.printLevelorder();
//		t1.delete(new Integer(19));
//		t1.printLevelorder();
//		println(divider);
//
//		/** Delete a value in a leaf which will cause a right-rebalance */
//		println("\nTesting rebalance [Right rotation]:");
//		t1.printLevelorder();
//		println("\n Deleting 26");
//		t1.delete(new Integer(26));
//		println("Result:");
//		t1.printLevelorder();
//		println(divider);
//
//		/** Delete a value in a leaf which will cause a left-rebalance */
//		println("\n\nInserting a value to manipulate a left-balance [25]");
//		t1.insert(new Integer(25));
//		t1.printLevelorder();
//		println("\n\n Deleting 3");
//		t1.delete(new Integer(3));
//		t1.printLevelorder();
//		println(divider);
//		
//		/**Delete a leaf-value, causing an excess parent value to be placed into its child */
//		println("DELETE CAUSING AN EXCESS PARENT VALUE TO BE PUT INTO ITS CHILD");
//		t1.printLevelorder();
//		println("\n\n Deleting 25");
//		t1.delete(new Integer(25));
//		t1.printLevelorder();
//		println(divider);
//		
//		/**Delete a leaf-value, causing 2 leafs to merge*/
//		t1 = new BTree(1);
//		t1.insert("btreedelete.txt");
//		println("DELETING LEAF VALUE CAUSING MERGE WITH NEW TREE");
//		t1.printLevelorder();
//		println("\n\nDeleting 19 and 4 to setup the case");
//		t1.delete(new Integer(19));
//		t1.delete(new Integer(4));
//		println("\n\nDeleting 3 to merge leafs");
//		t1.delete(new Integer(3));
//		t1.printLevelorder();
//		println(divider);
//		
//		
//		/**
//		 * Delete a value in a leaf which will cause the parent value to
//		 * replace it 
//		 */
//		println("\n\nUsing previous tree");
//		t1.printLevelorder();
//		println("\n\nDeleting 23 to setup the test.");
//		t1.delete(new Integer(23));
//		t1.printLevelorder();
//		println("\n\nDeleting 6 expecting -> 7, 26.");
//		t1.delete(new Integer(6));
//		t1.printLevelorder();
//		println(divider);
//		

//
//		/**
//		 * Delete a value in a leaf which is the largest in its branch but not
//		 * THE largest causing it to be replaced with the corresponding root
//		 * value
//		 */
//		println("\n\nDeleting 6 to force root replacement");
//		t2.delete(new Integer(6));
//		// t2.printLevelorder();
//	}

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
		 * Test once with another value in the tree which is not part of the same branch and which
		 * will balance everything.
		 */
		println("Setup new large tree with multiple branches");
		t2.printLevelorder();
		println("\n\nDeleting 99, 168, 132, 101 to have all the leaves in the last branch balanced on m");
		t2.delete(new Integer(99));
		t2.delete(new Integer(168));
		t2.delete(new Integer(132));
		t2.delete(new Integer(101));
		t2.printLevelorder();
		println("\n\nDeleting 66 to disrupt the balance and force the tree to take an abundant leaf-value from another branch");
		t2.delete(new Integer(66));
		t2.printLevelorder();
		println(divider);
		println("\n\nDeleting 64 to disrupt the balance and force the tree to take an abundant leaf-value from another branch");
		t2.delete(new Integer(64));
		t2.printLevelorder();
		println(divider);
		println("\n\nDeleting 58 to disrupt the balance and force the tree to take an abundant leaf-value from another branch");
		t2.delete(new Integer(58));
		t2.printLevelorder();
		println(divider);
		println("\n\nDeleting 56 to disrupt the balance and break the tree causing it to remove the last branch - since our root only has a single value a new root will be assigned.");
		t2.delete(new Integer(56));
		t2.printLevelorder();
		

		/**
		 * Test once with causing an underflow throughout the tree causing a
		 * merge
		 */
	}

}
