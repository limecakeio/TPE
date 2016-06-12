package threads;

/* THREADS - DEADLOCK
 * 
 * Scenario
 * --------
 * In medieval times, noble knights would challenge each other to a duel 
 * for the beautiful princess' hand by throwing down the gauntlet to their rivals feet. 
 * Once a gauntlet is thrown down, the challenger must wait for the response of the challenged knight
 * who, in return, is to pick up the gauntlet and accept the challenge or walk away in shame and disgrace.
 * 
 * However, this tradition does not take into account that two knights might throw down the gauntlet simultaneously. 
 * Unable to respond to the challenger while challenging the challenger themselves, the two noble knights end up in 
 * a deadlock situation and the beautiful princess dies of old age and boredom.
 */

class Example_DeadLock {

	static class Knight {
		private String name;

		// constructor: Knight
		public Knight(String name){
			this.name = name;
		}
		// throw the gauntlet: challnge another noble knight.
		public synchronized void throwGauntlet(Knight opponent){
			System.out.println("I, " + name + " hereby throw down the gauntlet to thee, " + opponent.getName() + "!");
			System.out.println("Respond to my challnge or taint thy honor in shame and disgrace!");
		}
		// pick up the gauntlet: respond to the challenge of another noble knight.
		public synchronized void pickupGauntlet(Knight opponent){
			System.out.println("I, " + name + " hereby accept thy challnge, " + opponent.getName() + "!");
			System.out.println("Stand and fight, " + opponent.getName() + "!");
		}
		public String getName(){
			return name;
		}
	}
	// Sir Edmund throws down the gauntlet before Sir Farlic.
	static class EdmundsGauntlet extends Thread {
		public void run(){
			synchronized (knight1){
				knight1.throwGauntlet(knight2);
				try { 
					Thread.sleep(100); 
				}
				catch (InterruptedException e){}
				synchronized (knight2){
					knight2.pickupGauntlet(knight1);
				}
			}
		}
	}
	// Sir Farlic throws down the gauntlet before Sir Edmund.
	static class FarlicsGauntlet extends Thread {
		public void run(){
			synchronized (knight2){
				knight2.throwGauntlet(knight1);
				try { 
					Thread.sleep(100); 
				}
				catch (InterruptedException e){}
				synchronized (knight1){
					knight1.pickupGauntlet(knight2);
				}
			}
		}
	}

	// Noble contestants for the beautiful princess' hand
	public static Knight knight1 = new Knight("Sir Edmund");
	public static Knight knight2 = new Knight("Sir Farlic");

	public static void main(String[] args){
		EdmundsGauntlet duel_01 = new EdmundsGauntlet();
		FarlicsGauntlet duel_02 = new FarlicsGauntlet();
		duel_01.start();
		duel_02.start();    
	}
}

