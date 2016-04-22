package bTree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BTreeInterfaceTest {
	BTree t1 = new BTree(1);
	BTree t2 = new BTree(2);
	BTree t3 = new BTree(3);
	BTree t20 = new BTree(20);

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
		t3 = new BTree(3);
		t20 = new BTree(20);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertInteger() {
	}

	@Test
	public void testInsertString() {
		t2.insert("btree50.txt");
		t2.printLevelorder();
		System.out.println("\n\nThe tree has a height of: " + t2.height());
		System.out.println("The tree contains: " + t2.size() + " nodes. ");
		System.out.println("Minimal value contained within the tree: " + Integer.transformInteger(t2.getMin()));
		System.out.println("Maximal value contained within the tree: " + Integer.transformInteger(t2.getMax()));
		
	}

//	@Test
//	public void testContains() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSize() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testHeight() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetMax() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetMin() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsEmpty() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPrintPreorder() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPrintInorder() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPrintPostorder() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPrintLevelorder() {
//		fail("Not yet implemented");
//	}

}
