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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertInteger() {
		assertNull(t1);
		assertTrue(t1.insert(new Integer(0)));
		assertTrue(t1.insert(new Integer(1)));
		assertTrue(t1.insert(new Integer(2)));
		assertTrue(t1.insert(new Integer(3)));
		
		assertNotNull(t1);
		assertFalse(t1.insert(new Integer(2))); // Element already in tree
		
		
	}

	@Test
	public void testInsertString() {
		t1.insert("btree.txt");
		t2.insert("btree.txt");
		t3.insert("btree.txt");
		
		//Physical state tests
		//assertEquals(t1.height(), 3);
		assertEquals(t2.height(), 3);
		//assertEquals(t3.height(), 3);
		
		t2.printLevelorder();
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
