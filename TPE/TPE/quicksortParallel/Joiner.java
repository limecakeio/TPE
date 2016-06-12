package quicksortParallel;

/**
 * @author Konstantin Pelevin
 * @author Richard Vladimirskij
 * 
 * TPE SS2016 - Joiner
 * Joins ThreadFactory Objects for sequential execution.
 * */

public class Joiner extends Thread {
	private ThreadFactory tf;
	
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
