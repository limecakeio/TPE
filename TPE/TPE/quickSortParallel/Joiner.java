package quickSortParallel;

public class Joiner extends Thread {
	private ThreadFactory tf;
	Joiner(){}
	
	Joiner(ThreadFactory tf){
		this.tf = tf;
		start();
	}
	
	public void run() {
		try {
			tf.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
