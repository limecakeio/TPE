package ringPuffer;

public class Ringpuffer {

private Object[] rp;
private int in = 0, out = 0;
private int inRuns = 0;
private int outRuns = 0;
private int time;
private TimerThread t;
private ConsumerThread[] ct;
private ProducerThread[] pt;


Ringpuffer(int size, int cons, int prods, int time) {
	rp = new Object[size];
	ct = new ConsumerThread[cons];
	pt = new ProducerThread[prods];
	this.time = time;
}

/**
 * Returns the first object in the queue.
 * @throws RingpufferException if the queue is full or if it is empty.
 * */
public synchronized Object get() throws InterruptedException {
	Object val = new Object();

	//If consumer wants to over take producer it has to wait.
	while(rp[out] == null || (inRuns <= outRuns && out == in)) {
		wait();
	}
		
	val = rp[out];
	
	out = (out+1) % rp.length;
	
	if(out == 0)
		outRuns++;
	
	notifyAll();
	
	return val;
}

/**Adds a new Object to the end of the queue and overwrites already read ones.
 * @throws InterruptedException if the thread was interrupted. */
public synchronized void put(Object o) throws InterruptedException {
	
	//Producer is always ahead of of consumer, so it cannot lap the other.
	while(inRuns > outRuns && in == out)
		wait();
	
	rp[in] = o;
	
	in = (in+1) % rp.length;	
	
	if(in == 0)
		inRuns++;
	
	notifyAll();
}

public void setConsumer(int pos, int sleep, String name) {
	ct[pos] = new ConsumerThread(this, t, sleep, name);
}

public void setProducer(int pos, int sleep, String name) {
	pt[pos] = new ProducerThread(this, t, sleep, name);
}

public void startTimer() {
	//TODO Turn seconds into minutes
	t = new TimerThread(time);
	t.start();
	System.out.println("Timer has been started with a " + time + " countdown.");
}

}
