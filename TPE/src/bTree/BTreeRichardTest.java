package bTree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BTreeRichardTest {
	BTree t1 = new BTree(1);
	BTree t2 = new BTree(2);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		t1 = new BTree(1);
		t2 = new BTree(2);
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void compareToTest() {
//		Integer a = new Integer(10);
//		Integer b = new Integer(13);
//		Integer c = new Integer(300);
//		Integer d = new Integer(300);
//		Integer e = null;
//		assertEquals((a.compareTo(b)), -1);
//		assertEquals((c.compareTo(d)), 0);
//		assertEquals((d.compareTo(a)), 1);
//		assertEquals((d.compareTo(e)), 1);
//	}
//	
//	@Test
//	public void insertionSortTest() {
//		Integer[] unsorted = {new Integer(10), new Integer(9), new Integer(7), new Integer(23), new Integer(12), new Integer(1), null};
//		assertEquals(Integer.toString(unsorted), "10 9 7 23 12 1 0");
//		Integer.insertionSort(unsorted);
//		assertEquals(Integer.toString(unsorted), "1 7 9 10 12 23 0");
//	}
//	
//	@Test
//	public void burstRootTest() {
//		System.out.println(t1.insert(new Integer(1)));
//		System.out.println(t1.insert(new Integer(3)));
//		System.out.println(t1.insert(new Integer(8)));
//		System.out.println(t1.insert(new Integer(19)));
//		System.out.println(t1.insert(new Integer(23)));
//	}
//	@Test
//	public void leafCheckTest() {
//		System.out.println("BEGIN LEAF CHECK");
//		t1.insert(new Integer(10));
//		t1.insert(new Integer(20));
//		t1.insert(new Integer(30));
//		t1.insert(new Integer(40));
//		t1.insert(new Integer(50));
//		t1.insert(new Integer(60));
//		t1.insert(new Integer(70));
//		t1.insert(new Integer(80));
//		t1.insert(new Integer(90));
//		System.out.println("END LEAF CHECK");
//	}
	@Test
	public void treeData(){
		System.out.println("Transversion & misc. data test");
//		t1.insert(new Integer(10));
//		t1.insert(new Integer(20));
//		t1.insert(new Integer(30));
//		t1.insert(new Integer(40));
//		t1.insert(new Integer(50));
//		t1.insert(new Integer(60));
//		t1.insert(new Integer(70));
//		t1.insert(new Integer(80));
//		t1.insert(new Integer(90));
//		t1.insert(new Integer(100));
//		t1.insert(new Integer(110));
//		t1.insert(new Integer(120));
//		t1.insert(new Integer(130));
//		t1.insert(new Integer(140));
//		t1.insert(new Integer(150));
//		t1.insert(new Integer(160));
//		t1.insert(new Integer(170));
//		t1.insert(new Integer(180));
//		t1.insert(new Integer(190));
//		t1.insert(new Integer(200));
//		t1.insert(new Integer(210));
//		t1.insert(new Integer(220));
//		t1.insert(new Integer(230));
//		t1.insert(new Integer(240));
//		t1.insert(new Integer(250));
//		
		t2.insert("btree.txt");

		System.out.println("\nInorder-Strategy");
		t2.printInorder();
		System.out.println("\nPreorder-Startegy");
		t2.printPreorder();
		System.out.println("\nPostorder-Strategy");
		t2.printPostorder();
		System.out.println("\nLevelorder-Strategy");
		t2.printLevelorder();

		System.out.println("\n\nThe tree has a height of: " + t2.height());
		System.out.println("The tree contains: " + t1.size() + " nodes. ");
		System.out.println("Minimal value contained within the tree: " + Integer.transformInteger(t1.getMin()));
		System.out.println("Maximal value contained within the tree: " + Integer.transformInteger(t1.getMax()));
		
		System.out.println("\nThe tree contains the value 10: " + t1.contains(new Integer(10)));
		System.out.println("The tree contains the value 100: " + t1.contains(new Integer(100)));
	}}
	
//	@Test
//	public void locateParentNodeTest() {
////		t1.insert(new Integer(10));
////		t1.insert(new Integer(20));
////		t1.insert(new Integer(30));
////		t1.insert(new Integer(40));
////		t1.insert(new Integer(50));
////		t1.insert(new Integer(60));
////		t1.insert(new Integer(70));
////		t1.insert(new Integer(80));
////		t1.insert(new Integer(90));
////		t1.insert(new Integer(100));
////		t1.insert(new Integer(110));
////		t1.insert(new Integer(120));
//	
//		
//		Integer testVal = new Integer(30);
//		BTreeNode testNode = t1.locateParentNode(testVal);
//		
//		System.out.println("\nTEST LOCATE PARENT - Parent contains:");
//		for(int i = 0; i <= 4; i++) {
//			System.out.println(Integer.transformInteger(testNode.getValue(i)));
//		}
//		testNode.printnode();
//	}
//}
