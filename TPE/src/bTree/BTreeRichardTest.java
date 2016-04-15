package bTree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BTreeRichardTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void compareToTest() {
		Integer a = new Integer(10);
		Integer b = new Integer(13);
		Integer c = new Integer(300);
		Integer d = new Integer(300);
		assertEquals((a.compareTo(b)), -1);
		assertEquals((c.compareTo(d)), 0);
		assertEquals((d.compareTo(a)), 1);
	}
	
	@Test
	public void insertionSortTest() {
		Integer[] unsorted = {new Integer(10), new Integer(9), new Integer(7), new Integer(23), new Integer(12), new Integer(1), null };
		assertEquals(Integer.toString(unsorted), "10 9 7 23 12 1 0");
		Integer.insertionSort(unsorted);
		assertEquals(Integer.toString(unsorted), "1 7 9 10 12 23 0");
	}
	
	@Test
	public void burstTreeTest() {
		BTree t1 = new BTree(2);
		System.out.println(t1.insert(new Integer(1)));
		System.out.println(t1.insert(new Integer(3)));
		System.out.println(t1.insert(new Integer(8)));
		System.out.println(t1.insert(new Integer(19)));
		System.out.println(t1.insert(new Integer(2)));	
	}

}
