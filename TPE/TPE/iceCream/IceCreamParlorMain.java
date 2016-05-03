package iceCream;

public class IceCreamParlorMain {

	public static void main(String[] args){
		
		// set up: different ice-cream parlor locations
		IceCreamParlor[] localParlor = new IceCreamParlor[3];
		localParlor[0] = new ICP_Mannheim();
		localParlor[2] = new ICP_Hochdeutsch();
		
		
		/* Order your ice-cream in different locations!
		 * Local Parlors: [0] = Mannheim
		 * Local Parlors: [1] = 
		 * Local Parlors: [2] = Hochdeutsch
		 * 
		 * Supported ice-cream types are:
		 * "Spaghettieis", "Bananasplit", "Nussbecher".
		 */
		
		localParlor[0].order("Spaghettieis");
		//localParlor[1].order("Spaghettieis");
		//localParlor[2].order("Spaghettieis");
	}

}
