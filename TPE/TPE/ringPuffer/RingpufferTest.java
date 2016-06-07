package ringPuffer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RingpufferTest {
	Ringpuffer r1 = new Ringpuffer();
	Ringpuffer r2 = new Ringpuffer();
	Ringpuffer r3 = new Ringpuffer();

	@Before
	public void setUp() throws Exception {
		r1.put(new Integer(23));
		r1.put(new Integer(55));
		r1.put(new Integer(12));
		r1.put(new Integer(32));
		r1.put(new Integer(27));
		
		r3.put(new Integer(23));
		r3.put(new Integer(55));
		r3.put(new Integer(12));
	}

	@Test(expected = RingpufferException.class)
	public void testGetEmpty() throws RingpufferException {
				r2.get();
	}
	
	@Test
	public void testGetFirst() throws RingpufferException {
		String result = "";
		result = r1.get() + "";
		assertEquals("23", result);
	}
	
	@Test
	public void testGetAll() {
		String result = "";
		try {
			for(int i = 0; i < 5; i++)
				result += r1.get() + " ";
		} catch (RingpufferException e) {
		}
		assertEquals("23 55 12 32 27 ", result);
	}
	
	@Test(expected = RingpufferException.class)
	public void testGetOneTooMany() throws RingpufferException {
			for(int i = 0; i <= 5; i++)
				r1.get();
	}
	
	@Test(expected = RingpufferException.class)
	public void testPutOneTooMany() throws RingpufferException {
			r1.put(new Integer(66));
	}
	
	@Test
	public void testRead1Add1() throws RingpufferException {
		r1.get();
		r1.put(new Integer(66));
		String result = r1.get() + "";
		assertEquals("55", result);
		r1.get();
		r1.get();
		r1.get();
		result = r1.get() + "";
		assertEquals("66", result);	
	}
	
	@Test(expected = RingpufferException.class)
	public void testFillHalfReadHalfPlusOne() throws RingpufferException {
		r3.get();
		r3.get();
		r3.get();
		r3.get();
	}
	
	@Test
	public void testAlternatingFillAndRead() throws RingpufferException {
		r3.get();
		r3.get();
		r3.put(22);
		r3.put(99);
		r3.get();
		r3.get();
		r3.put(1);
		r3.put(2);
		r3.put(3);
		r3.put(4);
		r3.get();
		r3.get();
		r3.get();
		r3.get();
		String result = r3.get() +"";
		assertEquals("4", result);
	}

}
