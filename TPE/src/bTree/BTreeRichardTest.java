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

}