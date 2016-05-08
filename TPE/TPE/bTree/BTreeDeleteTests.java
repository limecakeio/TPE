/**
 * 
 */
package bTree;

import static gdi.MakeItSimple.*;
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
	BTree t3;
	public final String divider = "\n------------------------------------\n";

	@Before
	public void setUp() throws Exception {
		t1 = new BTree(1);
		t2 = new BTree(2);
		Integer o1 = new Integer(15);
		Integer o2 = new Integer(17);
		Integer o3 = new Integer(5);
		Integer o4 = new Integer(13);
		Integer o5 = new Integer(28);
		Integer o6 = new Integer(55);
		Integer o7 = new Integer(90);
		Integer o8 = new Integer(2);
		Integer o9 = new Integer(19);
		Integer o10 = new Integer(23);
		Integer o11 = new Integer(91);
		Integer o12 = new Integer(22);
		
		t1.insert(o1);
		t1.insert(o2);
		t1.insert(o3);
		t1.insert(o4);
		t1.insert(o5);
		t1.insert(o6);
		t1.insert(o7);
		t1.insert(o8);
		t1.insert(o9);
		t1.insert(o10);
		t1.insert(o11);
		t1.insert(o12);
		
		t2.insert(o1);
		t2.insert(o2);
		t2.insert(o3);
		t2.insert(o4);
		t2.insert(o5);
		t2.insert(o6);
		t2.insert(o7);
		t2.insert(o8);
		t2.insert(o9);
		t2.insert(o10);
		t2.insert(o11);
		t2.insert(o12);
	}
	
	@Test
	public void deleteValueInRootTest() {
		/**
		 * Deleting value in root, will cause the value to be replaced by a corresponding leaf-value
		 *
		 *CASE 1: Deleting first value [17], replacement [19].
		 *CASE 2: Deleting a value which is not the first [17], replacement [55].
		 */
		println("DELETE IN ROOT TESTS ");
		println(divider);
		println("CASE 1: Deleting first value [17], replacement [19].");
		t1.delete(new Integer(17));
		println(divider);
		println("Tree at beginning: ");
		t1.printLevelorder();
		println(divider);
		println("Result: ");
		t1.printLevelorder();
		println(divider);
		
		
		println("CASE 2: Deleting a value which is not the first [17], replacement [55].");
		println(divider);
		println("Tree at beginning: ");
		t2.printLevelorder();
		t2.delete(new Integer(28));
		println(divider);
		println("Result: ");
		t2.printLevelorder();
		println(divider);
		
	}
	@Test
	public void deleteValueInNodeTest() {
		/**
		 * Deleting value in node[not leaf], will cause the value to be replaced by a corresponding leaf-value
		 *
		 *CASE 1: Deleting first value [13], replacement [5].
		 *CASE 2: Deleting a value which is not the first [90], replacement [55].
		 */
		println("DELETE IN NODE TESTS ");
		println(divider);
		println("CASE 1: Deleting first value [13], replacement [5] ");
		println(divider);
		println("Tree at beginning: ");
		t1.printLevelorder();
		t1.delete(new Integer(13));
		println(divider);
		println("Result: ");
		t1.printLevelorder();
		println(divider);
		
		println("CASE 2: Deleting a value which is not the first [90], replacement [55].");
		println(divider);
		println("Tree at beginning: ");
		t1.printLevelorder();
		t1.delete(new Integer(90));
		println(divider);
		println("Result: ");
		t1.printLevelorder();
		println(divider);
	}

	@Test
	public void deleteValueInLeafTest() {

		/**
		 * CASE 1: Delete a value in leaf which will cause no changes. Value [22]
		 * CASE 2: Delete a value in leaf which will cause a right-rebalance. Value [19]
		 * CASE 3: Delete a value which will cause a leaf to be absorbed. Value [91]
		 * CASE 4: Delete a value which will cause a left-rebalance. Value [15]
		 */
		
		println("DELETE IN LEAF TESTS ");
		println(divider);
		println("CASE 1: Delete a value in leaf which will cause no changes. Value [22]");
		println(divider);
		println("Tree at beginning: ");
		t1.printLevelorder();
		t1.delete(new Integer(22));
		println(divider);
		println("Result: ");
		t1.printLevelorder();
		println(divider);
		
		println("CASE 2: Delete a value in leaf which will cause a right-rebalance. Value [19]");
		println(divider);
		println("Tree at beginning: ");
		t1.printLevelorder();
		t1.delete(new Integer(19));
		println(divider);
		println("Result: ");
		t1.printLevelorder();
		println(divider);
		
		println("CASE 3: Delete a value which will cause a leaf to be absorbed. Value [91]");
		println(divider);
		println("Tree at beginning: ");
		t1.printLevelorder();
		t1.delete(new Integer(91));
		println(divider);
		println("Result: ");
		t1.printLevelorder();
		println(divider);
		
		println("CASE 4: Delete a value which will cause a left-rebalance. Value [15]");
		println(divider);
		println("Tree at beginning: ");
		t1.printLevelorder();
		t1.delete(new Integer(15));
		println(divider);
		println("Result: ");
		t1.printLevelorder();
		println(divider);

	}

}
