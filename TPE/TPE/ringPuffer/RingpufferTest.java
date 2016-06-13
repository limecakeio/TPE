package ringPuffer;

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

}
