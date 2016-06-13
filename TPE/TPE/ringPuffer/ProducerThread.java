package ringPuffer;

import java.util.Random;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 * 
 * ProducerThread - writes values into a Ringbuffer.
 * */


public class ProducerThread extends Thread {
	
	private Ringpuffer rp;
	private TimerThread t;
	private Random rnd;
	private int sleep;
	
	ProducerThread(Ringpuffer rp, TimerThread t, int sleep, String name) {
		super(name);
		this.rp = rp;
		this.t = t;
		this.sleep = sleep;
		rnd = new Random();
		start();
	}
	
	public void run() {
		while(t.running) {
			int val = rnd.nextInt(55);
			try {
				rp.put(val);
				System.out.println(this.getName() + " wrote: " + val);
				sleep(sleep);
			} 
			catch (InterruptedException e) {
				System.err.println(this.getName() + ": Interrupt detected.");
			} 
		}
		interrupt();
		System.out.println(this.getName() + " has finished run().");
	}

}
