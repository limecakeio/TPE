package ringPuffer;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 * 
 * ConsumerThread - reads values, written by ProducerThreads from a Ringbuffer.
 * */

public class ConsumerThread extends Thread {
	
	private Ringpuffer rp;
	private TimerThread t;
	private int sleep;
	
	public ConsumerThread(Ringpuffer rp, TimerThread t, int sleep, String name) {
		super(name);
		this.rp = rp;
		this.t = t;
		this.sleep = sleep;
		start();
	}
	
	public void run() {
		while(t.running) {
			try {
				System.out.println(this.getName() + " read: " + rp.get());
				sleep(sleep);
			} 
			catch(InterruptedException e) {}
		}
		System.out.println(this.getName() + " has finished run().");
	}
}
