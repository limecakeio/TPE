package ringPuffer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RingpufferTest {
	Ringpuffer r1;
	Ringpuffer r2;
	Ringpuffer r3;

	@Before
	public void setUp() throws Exception {
		r1 = new Ringpuffer(3, 2, 3, 5);
	}
	
	@Test
	public void testConsumerSlowerProducerFaster() throws InterruptedException {
		r1.startTimer();
		r1.setConsumer(0, 2000, "Consumer 1");
		r1.setConsumer(1, 3000, "Consumer 2");
		r1.setProducer(0, 1000, "Producer 1");
		r1.setProducer(1, 750, "Producer 2");
		r1.setProducer(2, 500, "Producer 3");
	}
	
	@Test
	public void testProducerSlowerConsumerFaster() throws InterruptedException {
		r1.startTimer();
		r1.setConsumer(0, 750, "Consumer 1");
		r1.setConsumer(1, 500, "Consumer 2");
		r1.setProducer(0, 1000, "Producer 1");
		r1.setProducer(1, 2000, "Producer 2");
		r1.setProducer(2, 3000, "Producer 3");	
	}
	
	@Test
	public void testProducerConsumerEqualTime() throws InterruptedException {
		r1.startTimer();
		r1.setConsumer(0, 1000, "Consumer 1");
		r1.setConsumer(1, 1000, "Consumer 2");
		r1.setProducer(0, 1000, "Producer 1");
		r1.setProducer(1, 1000, "Producer 2");
		r1.setProducer(2, 1000, "Producer 3");	
	}
//	
//	@Test(expected = RingpufferException.class)
//	public void testGetOneTooMany() throws RingpufferException, InterruptedException {
//			for(int i = 0; i <= 5; i++)
//				r1.get();
//	}
//	
//	@Test(expected = RingpufferException.class)
//	public void testPutOneTooMany() throws RingpufferException, InterruptedException {
//			r1.put(new Integer(66));
//	}
//	
//	@Test
//	public void testRead1Add1() throws RingpufferException, InterruptedException {
//		r1.get();
//		r1.put(new Integer(66));
//		String result = r1.get() + "";
//		assertEquals("55", result);
//		r1.get();
//		r1.get();
//		r1.get();
//		result = r1.get() + "";
//		assertEquals("66", result);	
//	}
//	
//	@Test(expected = RingpufferException.class)
//	public void testFillHalfReadHalfPlusOne() throws RingpufferException, InterruptedException {
//		r3.get();
//		r3.get();
//		r3.get();
//		r3.get();
//	}
//	
//	@Test
//	public void testAlternatingFillAndRead() throws RingpufferException, InterruptedException {
//		r3.get();
//		r3.get();
//		r3.put(22);
//		r3.put(99);
//		r3.get();
//		r3.get();
//		r3.put(1);
//		r3.put(2);
//		r3.put(3);
//		r3.put(4);
//		r3.get();
//		r3.get();
//		r3.get();
//		r3.get();
//		String result = r3.get() +"";
//		assertEquals("4", result);
//	}

}
