package threads;

/* THREADS - RACE CONDITION
 * 
 * Scenario
 * -------- 
 * In medieval times, noble knights would often perform heroic deeds to bathe in honor and glory
 * (and, as usual, try to impress the local beautiful princess). Since a life of a knight was not free of danger,
 * freindly knights would sometimes join in groups or teams - after all, the productivity of performance would rise 
 * to levels umeasurable! To share the news of a great deeds performed, these noble teams would often suffer from 
 * a slight communication problem, telling tales of their adventures either at once or in different locations 
 * without sync. Thus, such behavior would often result in a race conditions and leave the "Great Deeds Counter" 
 * in unpredictable and unforseen conditions.
 */

class GreatDeeds {
	private int greatDeedsCounter = 0;

	// constructor: storytime
	public GreatDeeds(){
		greatDeedsCounter = 0;
	}

	// update: great deeds counter
	public void tellAnotherTale(String name){
		setDeeds(getDeeds()+1);
		System.out.println(name + ": 'Tis how I tell ye! Another deed we most noble performed!"
				+ " Good folk, that makes " + getDeeds() + " deeds.");
	}

	// getter & setter
	public void setDeeds(int deeds){
		greatDeedsCounter = deeds;
	}
	public int getDeeds(){
		return greatDeedsCounter;
	}
}

public class Example_SafetyHazard {
	public static void main(String[] args){

		// actors: noble knights & the story object
		GreatDeeds storytime = new GreatDeeds();
		String knight1 = "Sir Edmund";
		String knight2 = "Sir Farlic";

		System.out.println("Noble knights will try and count all the great deeds they've performed."
				+ "\nEach time a single knight tells a story the \"Great Deed Counter\" is increased by one."
				+ "\nBoth knights will try and access the counter simultaneously, resulting in an odd overall result."
				+ "\n\nPlease consider running the program several times to experience the random output effect.\n");

		new Thread(new Runnable(){ 
			public void run(){
				// Sir Edmund Thread: 100 times
				for (int i = 0; i != 100; i++){
					try {
						Thread.sleep(1);
						storytime.tellAnotherTale(knight1); }
					catch (InterruptedException e){
						continue;}continue;}}}).start();

		new Thread(new Runnable(){ 
			public void run(){
				// Sir Farlic Thread: 100 times
				for (int i = 0; i != 100; i++){
					try {
						Thread.sleep(1);
						storytime.tellAnotherTale(knight2); }
					catch (InterruptedException e){
						continue;}continue;}}}).start();
	}
}
