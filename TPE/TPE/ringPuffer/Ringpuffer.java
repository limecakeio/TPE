package ringPuffer;

public class Ringpuffer extends Thread {
private Object[] rp;
private boolean ready;
private int in, out, size;
private int inRuns = 0;
private int outRuns = 0;

Ringpuffer() {}

Ringpuffer(int size) {
	this.size = size;
	rp = new Object[size];
	ready = true;
	in = 0;
	out = 0;
}

/**
 * */
public void setReady(boolean val) {
	ready = val;
}

public boolean getReady() {
	return ready;
}

/**
 * Returns the first object in the queue.
 * @throws RingpufferException if the queue is full or if it is empty.
 * */
public Object get() throws RingpufferException {
	Object val;

	//Ringpuffer is empty
	if(rp[out] == null || (inRuns <= outRuns && out == in))
		throw new RingpufferException("Ringbuffer is empty");
		
	val = rp[out];
	out = (out+1) % rp.length;
	
	if(out == 0)
		outRuns++;
	
	return val;
}

/**Adds a new Object to the end of the queue
 * @throws RingpufferException if the Ringpuffer is full */
public void put(Object o) throws RingpufferException {
	
	//Ringbuffer is full
	if(inRuns > outRuns && in == out)
			throw new RingpufferException("Ringbuffer is full");
	
	rp[in] = o;
	
	in = (in+1) % rp.length;
	
	if(in == 0)
		inRuns++;	
}

}
