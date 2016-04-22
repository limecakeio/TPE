package bTree;

import static org.junit.Assert.*;
import static gdi.MakeItSimple.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BTreeInterfaceTest {
	BTree t0 = new BTree(0);
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
		t0 = new BTree(0);
		t1 = new BTree(1);
		t2 = new BTree(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertInteger() {
		assertTrue(t1.insert(new Integer(0)));
		assertTrue(t1.insert(new Integer(1)));
		assertTrue(t1.insert(new Integer(2)));
		assertTrue(t1.insert(new Integer(3)));
		assertNotNull(t1);
		assertFalse(t1.insert(new Integer(2))); // Element already in tree
	}

	@Test
	public void testInsertString() {
		assertTrue(t1.insert("btree.txt"));
		assertFalse(t2.insert("btree-fail"));
	}

	@Test
	public void testContains() {
		t2.insert("btree.txt");
		assertTrue(t2.contains(new Integer(23)));
		assertFalse(t2.contains(new Integer(112)));		
	}

	@Test
	public void testSize(){
		assertEquals(t1.size(), 0);
		t1.insert("btree.txt");
		assertEquals(t1.size(), 26);
		
	}
	
	@Test
	public void testHeight() {
		assertEquals(t2.height(), 0);
		t2.insert("btree.txt");
		assertEquals(t2.height(), 3);
	}
	@Test
	public void testGetMax() {
		t1.insert("btree.txt");
		//assertEquals(t0.getMax(), -1);
		assertEquals(Integer.transformInteger(t1.getMax()), 168);
		assertNotEquals(Integer.transformInteger(t1.getMax()), 101);
	}

	@Test
	public void testGetMin() {
		assertEquals(Integer.transformInteger(t2.getMin()), -1);
		t2.insert("btree.txt");
		assertEquals(Integer.transformInteger(t2.getMin()), 1);
		assertNotEquals(Integer.transformInteger(t2.getMin()), 3);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(t1.isEmpty());
		t1.insert("btree.txt");
		assertFalse(t1.isEmpty());
	}

	@Test
	public void testAddAll() {
		t1.insert("btree.txt");
		t2.insert("btreemini.txt");
		t1.addAll(t2);
		assertEquals(t1.size(), 29);
	}

	@Test
	public void testPrintPreorder() {
		t2.insert("btree.txt");
		t2.printPreorder();
	}

	@Test
	public void testPrintInorder() {
		t2.insert("btree.txt");
		//t2.printInorder();
	}

	@Test
	public void testPrintPostorder() {
		//t2.insert("btree.txt");
		//t2.printPostorder();
	}

	@Test
	public void testPrintLevelorder() {
		t2.insert("btree.txt");
		t2.printLevelorder();
	}

}
