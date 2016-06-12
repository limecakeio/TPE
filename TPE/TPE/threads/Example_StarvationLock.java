package threads;

/* THREADS - STARVATION LOCK
 * 
 * Scenario
 * --------
 * In medieval times, when the challenges of the gauntlet and the beast were both in vain, noble knights had one
 * last trump to conquer the beautiful princess' hand. Educated in both, the swordplay and speechcraft, they would 
 * seek out the fair princess in question and feed her honey-sweet words in order to melt and win her heart.
 * 
 * Since this was an all decisive battle, noble knights would abandon their customs of nobility and courtsey.
 * Without wasting a single breath they would try to besiege the beautiful princess - all at the same time. 
 * The princess, on the other hand, would not be able to listen to all greedy and desperate contestants but 
 * one at a time. The chance (or social rank) would decide the lucky man while others would fall on deaf ears, 
 * waiting for their turn and possibly resulting in starvation lock.
 */

public class Example_StarvationLock {

	public static class ThePrincessHeart {
		static String favourite;

		// constructor: the princess' heart
		public ThePrincessHeart(String name){
			favourite = name;
		}

		synchronized public static void tryWinningHerHeart(String contestant){

			// overload the beautiful princess' heart
			for (int i = 0; i != Integer.MAX_VALUE; i++){
				ThePrincessHeart.setFavourite(contestant);
			}
			System.out.println("Princess: My, " + ThePrincessHeart.getFavourite() + ", thy words are honey-sweet!");
		}
		public static void setFavourite(String name){
			favourite = name;
		}
		public static String getFavourite(){
			return favourite;
		}
	}

	// the noble contestants for the beautiful princess' hand
	static String contestant1 = "Sir Prattlelot";
	static String contestant2 = "Sir Edmund";
	static String contestant3 = "Sir Farlic";

	public static void main(String[] args){

		System.out.println("Trying to conquer the beautiful princess' heart is a lengthy procedure."
				+ "\nPlease wait...\n");
		System.out.println("First off, the contestants will intoduce themselves. All of them are threads."
				+ "\nThey will run simultaneously, trying to acces a the princess' heart via a synchronized method."
				+ "\nOnce the attention of the princess is in the posession of a contestant, all others are forced"
				+ "\nto wait for their chance. Afterwrds another round begins, with a high probability that the same "
				+ "\nthread will have a higher priority and access the princess' heart again.\n\n");

		new Thread(new Runnable(){ 
			public void run(){
				System.out.println(contestant1 + ": 'Tis I, " + contestant1 + ", my fair lady!");
				while(true)
					ThePrincessHeart.tryWinningHerHeart(contestant1); }}).start();
		new Thread(new Runnable(){ 
			public void run(){
				System.out.println(contestant2 + ": 'Tis I, " + contestant2 + ", my fair lady!");
				while(true)
					ThePrincessHeart.tryWinningHerHeart(contestant2); }}).start();
		new Thread(new Runnable(){ 
			public void run(){
				System.out.println(contestant3 + ": 'Tis I, " + contestant3 + ", my fair lady!\n");
				while(true)
					ThePrincessHeart.tryWinningHerHeart(contestant3); }}).start();
	}
}
